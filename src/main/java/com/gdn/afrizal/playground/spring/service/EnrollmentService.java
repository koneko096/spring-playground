package com.gdn.afrizal.playground.spring.service;

import com.gdn.afrizal.playground.spring.dto.StudentEnrollment;
import com.gdn.afrizal.playground.spring.model.Enrollment;

import java.util.List;

public interface EnrollmentService {
  List<StudentEnrollment> findByStudentId(Long studentId);
  StudentEnrollment findByStudentIdAndCourseId(Long studentId, String courseId);
  List<Enrollment> enrollStudents(String courseId, List<Long> studentIds);
}
