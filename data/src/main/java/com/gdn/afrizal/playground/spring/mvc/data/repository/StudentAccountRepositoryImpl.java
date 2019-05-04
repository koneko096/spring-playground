package com.gdn.afrizal.playground.spring.mvc.data.repository;

import com.gdn.afrizal.playground.spring.mvc.model.StudentAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class StudentAccountRepositoryImpl implements StudentAccountCustomRepository {
  @Autowired
  private MongoTemplate mongoTemplate;

  public StudentAccount findByUsername(String username) {
    final Query query = new Query(where("username").is(username).and("active").is(true));
    return this.mongoTemplate.findOne(query, StudentAccount.class);
  }

  public StudentAccount findByUsernameAndPassword(String username, String password) {
    final Query query = new Query(where("username").is(username)
        .and("password").is(password)
        .and("active").is(true));
    return this.mongoTemplate.findOne(query, StudentAccount.class);
  }
}
