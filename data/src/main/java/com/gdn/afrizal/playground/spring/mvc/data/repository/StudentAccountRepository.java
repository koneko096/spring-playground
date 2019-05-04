package com.gdn.afrizal.playground.spring.mvc.data.repository;

import com.gdn.afrizal.playground.spring.mvc.model.StudentAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentAccountRepository extends StudentAccountCustomRepository, MongoRepository<StudentAccount, Long> {
}
