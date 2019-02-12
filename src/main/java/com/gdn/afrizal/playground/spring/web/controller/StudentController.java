package com.gdn.afrizal.playground.spring.web.controller;

import com.gdn.afrizal.playground.spring.data.repository.EnrollmentRepository;
import com.gdn.afrizal.playground.spring.data.repository.StudentAccountRepository;
import com.gdn.afrizal.playground.spring.data.repository.StudentRepository;
import com.gdn.afrizal.playground.spring.dto.StudentEnrollment;
import com.gdn.afrizal.playground.spring.dto.StudentRegistration;
import com.gdn.afrizal.playground.spring.dto.validation.ValidationError;
import com.gdn.afrizal.playground.spring.model.Enrollment;
import com.gdn.afrizal.playground.spring.model.Student;
import com.gdn.afrizal.playground.spring.model.StudentAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentAccountRepository studentAccountRepository;

    private Long getStudentId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        String currentUserName = authentication.getName();
        StudentAccount account = studentAccountRepository.findByUsername(currentUserName);
        return account != null ? account.getStudentId() : null;
    }

    private ValidationError createValidationError(MethodArgumentNotValidException e) {
        return ValidationError.fromBindingErrors(e.getBindingResult());
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
    public ValidationError handleException(MethodArgumentNotValidException exception) {
        return createValidationError(exception);
    }

    @RequestMapping(value = "/enrollment/{studentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Enrollment> getEnrollments(@PathVariable("studentId") Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    @RequestMapping(value = "/enrollment/{studentId}/{courseId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Enrollment> getEnrollment(@PathVariable("studentId") Long studentId,
        @PathVariable("courseId") Long courseId) {
        return new ResponseEntity<Enrollment>(
            enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId), HttpStatus.OK);
    }

    @RequestMapping(value = "/student/{studentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getStudent(@PathVariable("studentId") Long studentId) {
        return new ResponseEntity<Student>(
            studentRepository.findByStudentId(studentId), HttpStatus.OK);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/student/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerStudent(@RequestBody @Valid StudentRegistration registration) {
        Date entryDate = GregorianCalendar.getInstance().getTime();
        Student student = Student.builder()
            .id(composeIdNum(registration.getProgramId(), entryDate.getYear()))
            .active(true)
            .nationalId(registration.getNationalId())
            .facultyId(registration.getFacultyId())
            .programId(registration.getProgramId())
            .entryDate(entryDate)
            .build();
        Student newStudent = studentRepository.save(student);
        if (newStudent == null) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        StudentAccount studentAccount = StudentAccount.builder()
            .studentId(newStudent.getId())
            .active(true)
            .email(registration.getEmail())
            .createdAt(entryDate)
            .modifiedAt(entryDate)
            .username(registration.getUsername())
            .password(registration.getPassword())
            .build();
        StudentAccount newAccount = studentAccountRepository.save(studentAccount);
        if (newAccount == null) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(registration, HttpStatus.OK);
    }

    private Long composeIdNum(Long programId, int year) {
        return (programId * 100000) + (year % 100 * 1000) +
            (studentRepository.countByProgramId(programId) + 1);
    }
    //
//    @DeleteMapping("/customers/{id}")
//    public ResponseEntity deleteCustomer(@PathVariable Long id) {
//
//        if (null == customerDAO.delete(id)) {
//            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity(id, HttpStatus.OK);
//
//    }
//
//    @PutMapping("/customers/{id}")
//    public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
//
//        customer = customerDAO.update(id, customer);
//
//        if (null == customer) {
//            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity(customer, HttpStatus.OK);
//    }
}
