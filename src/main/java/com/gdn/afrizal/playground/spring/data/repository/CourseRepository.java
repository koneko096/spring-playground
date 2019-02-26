package com.gdn.afrizal.playground.spring.data.repository;

import com.gdn.afrizal.playground.spring.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends CourseCustomRepository, MongoRepository<Course, Long> {
}
