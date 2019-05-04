package com.gdn.afrizal.playground.spring.mvc.web.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@PropertySource("classpath:application.properties")
@EnableWebMvc
@EnableCaching
@EnableMongoRepositories(basePackages = "com.gdn.afrizal.playground.spring.mvc.data.repository")
@ComponentScan(basePackages = "com.gdn.afrizal.playground.spring")
public class AppConfig extends AbstractMongoConfiguration {

    private static final String COURSES = "courses";
    private static final String STUDENTS = "students";
    private static final String ENROLLMENTS = "enrollments";

  @Value("${spring.data.mongodb.database}")
  private String databaseName;

  @Value("${spring.data.mongodb.uri}")
  private String uri;


  @Override
  public MongoClient mongoClient() {
    return new MongoClient(new MongoClientURI(uri));
  }

  @Override
  protected String getDatabaseName() {
    return databaseName;
  }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(STUDENTS, COURSES, ENROLLMENTS);
    }
}
