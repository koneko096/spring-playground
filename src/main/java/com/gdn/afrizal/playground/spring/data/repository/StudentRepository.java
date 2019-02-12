package com.gdn.afrizal.playground.spring.data.repository;

import com.gdn.afrizal.playground.spring.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends StudentCustomRepository, MongoRepository<Student, Long> {
}
