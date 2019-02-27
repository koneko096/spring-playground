package com.gdn.afrizal.playground.spring.web.controller;

import com.gdn.afrizal.playground.spring.data.repository.CourseRepository;
import com.gdn.afrizal.playground.spring.data.repository.EnrollmentRepository;
import com.gdn.afrizal.playground.spring.data.repository.StudentAccountRepository;
import com.gdn.afrizal.playground.spring.data.repository.StudentRepository;
import com.gdn.afrizal.playground.spring.dto.StudentBulkEnrollment;
import com.gdn.afrizal.playground.spring.dto.StudentEnrollment;
import com.gdn.afrizal.playground.spring.dto.StudentRegistration;
import com.gdn.afrizal.playground.spring.dto.validation.ValidationError;
import com.gdn.afrizal.playground.spring.model.Enrollment;
import com.gdn.afrizal.playground.spring.model.Student;
import com.gdn.afrizal.playground.spring.model.StudentAccount;
import com.gdn.afrizal.playground.spring.service.EnrollmentService;
import com.gdn.afrizal.playground.spring.web.config.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
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

    private static final String STUDENT_ID = "studentId";

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentAccountRepository studentAccountRepository;

    @Autowired
    EnrollmentService enrollmentService;

//    private Long getStudentId() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
//            return null;
//        }
//        String currentUserName = authentication.getName();
//        StudentAccount account = studentAccountRepository.findByUsername(currentUserName);
//        return account != null ? account.getStudentId() : null;
//    }

    private ValidationError createValidationError(MethodArgumentNotValidException e) {
        return ValidationError.fromBindingErrors(e.getBindingResult());
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
    public ValidationError handleException(MethodArgumentNotValidException exception) {
        return createValidationError(exception);
    }

    @RequestMapping(value = Routes.ENROLLMENT_OF_STUDENT, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentEnrollment> getEnrollments(@PathVariable(STUDENT_ID) Long studentId) {
        return enrollmentService.findByStudentId(studentId);
    }

    @RequestMapping(value = Routes.ENROLLMENT_OF_STUDENT_COURSE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getEnrollment(@PathVariable(STUDENT_ID) Long studentId,
        @PathVariable("courseId") String courseId) {
      StudentEnrollment studentEnrollment =
          enrollmentService.findByStudentIdAndCourseId(studentId, courseId);
      if (studentEnrollment == null) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(studentEnrollment, HttpStatus.OK);
    }

    @RequestMapping(value = Routes.STUDENT, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getStudent(@PathVariable(STUDENT_ID) Long studentId) {
      Student student = studentRepository.findByStudentId(studentId);
      if (student == null) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = Routes.REGISTER_STUDENT, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
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

    @RequestMapping(value = Routes.PROGRAM_COURSES, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCourseOfProgram(@PathVariable("programId") Long programId) {
        return new ResponseEntity<>(courseRepository.findCoursesByProgramId(programId), HttpStatus.OK);
    }

    @RequestMapping(value = Routes.PROGRAM_STUDENTS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getStudentsOfProgram(@PathVariable("programId") Long programId) {
      return new ResponseEntity<>(studentRepository.findByProgramId(programId), HttpStatus.OK);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = Routes.ENROLL_STUDENTS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity enrollStudents(@RequestBody StudentBulkEnrollment bulkEnrollment) {
        List<Enrollment> enrollments = enrollmentService.enrollStudents(
            bulkEnrollment.getCourseId(),
            bulkEnrollment.getStudentIds());
        if (enrollments == null) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        bulkEnrollment.setEnrollments(enrollments);
        return new ResponseEntity<>(enrollments, HttpStatus.OK);
    }

    private Long composeIdNum(Long programId, int year) {
        return (programId * 100000) + (year % 100 * 1000) +
            (studentRepository.countByProgramId(programId) + 1);
    }
}
