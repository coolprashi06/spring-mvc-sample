package com.prashast.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mapping.model.FieldNamingStrategy;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.support.IsNewStrategyFactory;

import java.util.Set;

@Configuration
@EnableMongoRepositories("com.prashast.repository")
@PropertySource("classpath:application.properties")
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Autowired
    private Environment environment;

    @Override
    protected String getMappingBasePackage() {
        return "com.prashast.repository";
    }

    @Override
    protected String getDatabaseName() {
        return environment.getProperty("mongo.database");
        //return "appDB";
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(environment.getProperty("mongo.host"));
        //return new MongoClient("127.0.0.1");
    }

    @Override
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return super.mongoTemplate();
    }

    @Override
    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        return super.mongoDbFactory();
    }

    @Override
    @Bean
    public MongoMappingContext mongoMappingContext() throws ClassNotFoundException {
        return super.mongoMappingContext();
    }

    @Override
    @Bean
    public IsNewStrategyFactory isNewStrategyFactory() throws ClassNotFoundException {
        return super.isNewStrategyFactory();
    }

    @Override
    @Bean
    public MappingMongoConverter mappingMongoConverter() throws Exception {
        return super.mappingMongoConverter();
    }

    @Override
    @Bean
    public CustomConversions customConversions() {
        return super.customConversions();
    }

    @Override
    @Bean
    protected FieldNamingStrategy fieldNamingStrategy() {
        return super.fieldNamingStrategy();
    }

    @Override
    protected Set<Class<?>> getInitialEntitySet() throws ClassNotFoundException {
        return super.getInitialEntitySet();
    }
}
