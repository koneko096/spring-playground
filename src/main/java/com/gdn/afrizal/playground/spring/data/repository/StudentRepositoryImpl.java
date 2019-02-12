package com.gdn.afrizal.playground.spring.data.repository;

import com.gdn.afrizal.playground.spring.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class StudentRepositoryImpl implements StudentCustomRepository {
  @Autowired
  private MongoTemplate mongoTemplate;

  public Student findByStudentId(Long studentId) {
    final Query query = new Query(where("studentId").is(studentId).and("active").is(true));
    return this.mongoTemplate.findOne(query, Student.class);
  }

  public List<Student> findByProgramId(Long programId) {
    final Query query = new Query(where("programId").is(programId).and("active").is(true));
    return this.mongoTemplate.find(query, Student.class);
  }

  public Long countByProgramId(Long programId) {
    final Query query = new Query(where("programId").is(programId).and("active").is(true));
    return this.mongoTemplate.count(query, Student.class);
  }
}
