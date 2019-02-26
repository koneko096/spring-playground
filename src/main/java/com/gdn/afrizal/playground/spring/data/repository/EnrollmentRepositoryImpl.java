package com.gdn.afrizal.playground.spring.data.repository;

import com.gdn.afrizal.playground.spring.model.Enrollment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class EnrollmentRepositoryImpl implements EnrollmentCustomRepository {
  @Autowired
  private MongoTemplate mongoTemplate;

  public List<Enrollment> findByStudentId(Long studentId) {
    final Query query = new Query(where("studentId").is(studentId));
    return this.mongoTemplate.find(query, Enrollment.class);
  }

  public List<Enrollment> findByCourseId(Long courseId) {
    final Query query = new Query(where("courseId").is(courseId));
    return this.mongoTemplate.find(query, Enrollment.class);
  }

  public Enrollment findByStudentIdAndCourseId(Long studentId, Long courseId) {
    final Query query = new Query(where("studentId").is(studentId).and("courseId").is(courseId));
    return this.mongoTemplate.findOne(query, Enrollment.class);
  }

  public List<Enrollment> insertBulk(List<Enrollment> enrollments) {
    return new ArrayList(this.mongoTemplate.insert(enrollments, Enrollment.class));
  }
}
