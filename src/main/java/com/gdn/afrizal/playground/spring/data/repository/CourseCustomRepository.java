package com.gdn.afrizal.playground.spring.data.repository;

import com.gdn.afrizal.playground.spring.model.Course;

import java.util.List;

public interface CourseCustomRepository {
  List<Course> findCoursesByProgramId(Long programId);
}
