package com.gdn.afrizal.playground.spring.mvc.data.repository;

import com.gdn.afrizal.playground.spring.mvc.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@CacheConfig(cacheNames = "courses")
public class CourseRepositoryImpl implements CourseCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    @Cacheable
    public Course findByCourseId(String courseId) {
        final Query query = new Query(where("id").is(courseId));
        return this.mongoTemplate.findOne(query, Course.class);
    }

    @Override
    @Cacheable
    public List<Course> findCoursesByProgramId(Long programId) {
        final Query query = new Query(where("programId").is(programId));
        return this.mongoTemplate.find(query, Course.class);
    }

    @Override
    @Cacheable
    public List<Course> findCoursesByCourseIds(List<String> courseIds) {
        final Query query = new Query(where("id").in(courseIds));
        return this.mongoTemplate.find(query, Course.class);
    }
}
