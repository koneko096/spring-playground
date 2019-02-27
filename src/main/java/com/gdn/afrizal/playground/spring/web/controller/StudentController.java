package com.gdn.afrizal.playground.spring.web.controller;

import com.gdn.afrizal.playground.spring.data.repository.CourseRepository;
import com.gdn.afrizal.playground.spring.dto.StudentBulkEnrollment;
import com.gdn.afrizal.playground.spring.dto.StudentEnrollment;
import com.gdn.afrizal.playground.spring.dto.StudentRegistration;
import com.gdn.afrizal.playground.spring.dto.validation.ValidationError;
import com.gdn.afrizal.playground.spring.model.Enrollment;
import com.gdn.afrizal.playground.spring.model.Student;
import com.gdn.afrizal.playground.spring.service.EnrollmentService;
import com.gdn.afrizal.playground.spring.service.StudentService;
import com.gdn.afrizal.playground.spring.web.config.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;

@RestController
public class StudentController {

    private static final String STUDENT_ID = "studentId";
    private static final String PROGRAM_ID = "programId";
    private static final String COURSE_ID = "courseId";

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentService studentService;

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
                                        @PathVariable(COURSE_ID) String courseId) {
        StudentEnrollment studentEnrollment = enrollmentService.findByStudentIdAndCourseId(studentId, courseId);
        if (studentEnrollment == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentEnrollment, HttpStatus.OK);
    }

    @RequestMapping(value = Routes.STUDENT, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getStudent(@PathVariable(STUDENT_ID) Long studentId) {
        Student student = studentService.findByStudentId(studentId);
        if (student == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = Routes.REGISTER_STUDENT, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerStudent(@RequestBody @Valid StudentRegistration registration) {
        return new ResponseEntity<>(studentService.registerStudent(registration), HttpStatus.OK);
    }

    @RequestMapping(value = Routes.PROGRAM_COURSES, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCourseOfProgram(@PathVariable(PROGRAM_ID) Long programId) {
        return new ResponseEntity<>(courseRepository.findCoursesByProgramId(programId), HttpStatus.OK);
    }

    @RequestMapping(value = Routes.PROGRAM_STUDENTS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getStudentsOfProgram(@PathVariable(PROGRAM_ID) Long programId) {
        return new ResponseEntity<>(studentService.findByProgramId(programId), HttpStatus.OK);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = Routes.ENROLL_STUDENTS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity enrollStudents(@RequestBody @Valid StudentBulkEnrollment bulkEnrollment) {
        List<Enrollment> enrollments = enrollmentService.enrollStudents(
                bulkEnrollment.getCourseId(),
                bulkEnrollment.getStudentIds());
        if (enrollments == null) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        bulkEnrollment.setEnrollments(enrollments);
        return new ResponseEntity<>(enrollments, HttpStatus.OK);
    }
}
