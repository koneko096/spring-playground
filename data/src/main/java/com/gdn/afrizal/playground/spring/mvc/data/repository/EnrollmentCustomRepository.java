package com.gdn.afrizal.playground.spring.mvc.data.repository;

import com.gdn.afrizal.playground.spring.mvc.model.Enrollment;

import java.util.List;

public interface EnrollmentCustomRepository {
  List<Enrollment> findByStudentId(Long studentId);
  List<Enrollment> findByCourseId(Long courseId);
  Enrollment findByStudentIdAndCourseId(Long studentId, String courseId);
  List<Enrollment> insertBulk(List<Enrollment> enrollments);
}
