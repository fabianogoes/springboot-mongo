package com.example.springbootmongo.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;

@Configuration
@EnableMongoAuditing
public class MongoConfiguration {

    //MongoDB properties read from the application.yaml configuration file (to handle different profiles)
    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private int mongoPort;

    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongo(), mongoDatabase);
        MappingMongoConverter mongoMapping = (MappingMongoConverter) mongoTemplate.getConverter();
        mongoMapping.setCustomConversions(customConversions()); // tell mongodb to use the custom converters
        mongoMapping.afterPropertiesSet();
        return mongoTemplate;
    }

    /**
     * Configure the MongoDB client
     *
     **/
    @Bean
    public MongoClient mongo() throws Exception {
        return new MongoClient(mongoHost, mongoPort);
    }


    /**
     * Returns the list of custom converters that will be used by the MongoDB template
     *
     **/
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(
                Arrays.asList(
                        new DoubleToBigDecimalConverter(),
                        new BigDecimalToDoubleConverter()
                )
        );
    }

}
