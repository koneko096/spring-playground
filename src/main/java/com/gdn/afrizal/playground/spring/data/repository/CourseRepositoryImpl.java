package com.gdn.afrizal.playground.spring.data.repository;

import com.gdn.afrizal.playground.spring.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class CourseRepositoryImpl implements CourseCustomRepository {
  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public Course findByCourseId(String courseId) {
    final Query query = new Query(where("id").is(courseId));
    return this.mongoTemplate.findOne(query, Course.class);
  }

  public List<Course> findCoursesByProgramId(Long programId) {
    final Query query = new Query(where("programId").is(programId));
    return this.mongoTemplate.find(query, Course.class);
  }

  @Override
  public List<Course> findCoursesByCourseIds(List<String> courseIds) {
    final Query query = new Query(where("id").in(courseIds));
    return this.mongoTemplate.find(query, Course.class);
  }
}
