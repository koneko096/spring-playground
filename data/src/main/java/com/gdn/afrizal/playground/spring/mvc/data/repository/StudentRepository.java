package com.gdn.afrizal.playground.spring.mvc.data.repository;

import com.gdn.afrizal.playground.spring.mvc.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends StudentCustomRepository, MongoRepository<Student, Long> {
}
