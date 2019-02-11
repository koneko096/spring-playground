package com.gdn.afrizal.playground.spring.data.repository;

import com.gdn.afrizal.playground.spring.model.Enrollment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnrollmentRepository extends MongoRepository<Enrollment, Long> {
}
