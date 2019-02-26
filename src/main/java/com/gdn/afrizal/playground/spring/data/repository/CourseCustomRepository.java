package com.gdn.afrizal.playground.spring.data.repository;

import com.gdn.afrizal.playground.spring.model.Course;

import java.util.List;

public interface CourseCustomRepository {
  Course findByCourseId(String courseId);
  List<Course> findCoursesByProgramId(Long programId);
  List<Course> findCoursesByCourseIds(List<String> courseIds);
}
