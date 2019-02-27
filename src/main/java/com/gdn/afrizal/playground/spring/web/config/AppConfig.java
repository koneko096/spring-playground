package com.gdn.afrizal.playground.spring.web.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@PropertySource("classpath:application.properties")
@EnableWebMvc
@EnableMongoRepositories(basePackages = "com.gdn.afrizal.playground.spring.data.repository")
@ComponentScan(basePackages = "com.gdn.afrizal.playground.spring")
public class AppConfig extends AbstractMongoConfiguration {

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
}
