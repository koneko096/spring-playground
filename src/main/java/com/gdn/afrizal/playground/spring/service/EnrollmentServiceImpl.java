package com.gdn.afrizal.playground.spring.service;

import com.gdn.afrizal.playground.spring.data.repository.EnrollmentRepository;
import com.gdn.afrizal.playground.spring.model.Enrollment;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EnrollmentServiceImpl implements EnrollmentService {
  @Autowired
  EnrollmentRepository enrollmentRepository;

  public List<Enrollment> enrollStudents(final String courseId, List<String> studentIds) {
    final Date enrollmentDate = Calendar.getInstance().getTime();
    return enrollmentRepository.insertBulk(studentIds.stream()
        .map(sid -> createEnrollment(courseId, sid, enrollmentDate))
        .collect(Collectors.toList()));
  }

  private Enrollment createEnrollment(String courseId, String sid, Date enrollmentDate) {
    return Enrollment.builder()
        .active(true)
        .courseId(courseId)
        .enrollmentDate(enrollmentDate)
        .studentId(Long.parseLong(sid))
        .build();
  }
}
