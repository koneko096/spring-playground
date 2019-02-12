package com.gdn.afrizal.playground.spring.data.repository;

import com.gdn.afrizal.playground.spring.model.Enrollment;

import java.util.List;

public interface EnrollmentCustomRepository {
  List<Enrollment> findByStudentId(Long studentId);
  List<Enrollment> findByCourseId(Long courseId);
  Enrollment findByStudentIdAndCourseId(Long studentId, Long courseId);
}
