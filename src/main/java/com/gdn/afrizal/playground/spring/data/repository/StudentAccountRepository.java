package com.gdn.afrizal.playground.spring.data.repository;

import com.gdn.afrizal.playground.spring.model.StudentAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentAccountRepository extends MongoRepository<StudentAccount, Long> {
}
