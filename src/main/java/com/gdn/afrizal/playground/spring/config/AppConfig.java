package com.gdn.afrizal.playground.spring.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static java.util.Collections.singletonList;

@Configuration
@EnableWebMvc
@EnableMongoRepositories(basePackages = "com.gdn.afrizal.playground.spring.data.repository")
@ComponentScan(basePackages = "com.gdn.afrizal.playground.spring")
public class AppConfig extends AbstractMongoConfiguration {
  @Override
  @Bean
  public MongoClient mongoClient() {
    return new MongoClient(singletonList(new ServerAddress("127.0.0.1", 27017)),
        singletonList(MongoCredential.createCredential("name", getDatabaseName(), "pwd".toCharArray())));
  }

  @Override
  public String getDatabaseName() {
    return "spring-pg";
  }
}
