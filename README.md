# SpringBoot MongoDB 

> Project Open Source created to share collaboratively experiences with MongoDB and Spring Boot 

> Each step was described through the branches, see each branch to understand the process.


### Converter BigDecimal to Double MongoDB
------------------------------------------


1. **BigDecimalToDoubleConverter**

```java
package com.example.springbootmongo.config;

import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;
 
public class BigDecimalToDoubleConverter implements Converter<BigDecimal, Double> {
 
    @Override
    public Double convert(BigDecimal source) {
        return source.doubleValue();
    }
}   
```

2. **DoubleToBigDecimalConverter**

```java
package com.example.springbootmongo.config;

import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;
 
 
public class DoubleToBigDecimalConverter implements Converter<Double, BigDecimal> {
 
    @Override
    public BigDecimal convert(Double source) {
        return new BigDecimal(source);
    }
}
```

3. **MongoConfiguration**

```java
package com.example.springbootmongo.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import java.util.Arrays;

@Configuration
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
    public Mongo mongo() throws Exception {
        return new MongoClient(mongoHost, mongoPort);
    }


    /**
     * Returns the list of custom converters that will be used by the MongoDB template
     *
     **/
    public CustomConversions customConversions() {
        return new CustomConversions(Arrays.asList(new DoubleToBigDecimalConverter(), new BigDecimalToDoubleConverter()));
    }

}
```


> See the branch **setup** to understand the setup MongoDB

[Reference](http://ufasoli.blogspot.com.br/2017/06/custom-converter-for-mongodb-and-spring.html)   