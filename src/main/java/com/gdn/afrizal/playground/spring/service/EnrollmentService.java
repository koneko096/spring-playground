package com.gdn.afrizal.playground.spring.service;

import com.gdn.afrizal.playground.spring.model.Enrollment;

import java.util.List;

public interface EnrollmentService {
  List<Enrollment> enrollStudents(String courseId, List<String> studentIds);
}
