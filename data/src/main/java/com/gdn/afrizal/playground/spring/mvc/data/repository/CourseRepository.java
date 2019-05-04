package com.gdn.afrizal.playground.spring.mvc.data.repository;

import com.gdn.afrizal.playground.spring.mvc.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends CourseCustomRepository, MongoRepository<Course, Long> {
}
