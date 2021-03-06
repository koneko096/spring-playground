package com.gdn.afrizal.playground.spring.mvc.service;

import com.gdn.afrizal.playground.spring.mvc.dto.StudentEnrollment;
import com.gdn.afrizal.playground.spring.mvc.model.Enrollment;

import java.util.List;

public interface EnrollmentService {
  List<StudentEnrollment> findByStudentId(Long studentId);
  StudentEnrollment findByStudentIdAndCourseId(Long studentId, String courseId);
  List<Enrollment> enrollStudents(String courseId, List<Long> studentIds);
}
