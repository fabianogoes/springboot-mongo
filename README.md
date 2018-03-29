# SpringBoot MongoDB 

> Project Open Source created to share collaboratively experiences with MongoDB and Spring Boot 

> Each step was described through the branches, see each branch to understand the process.

### Setup
---------

**Parent**

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.5.9.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
```	



**MongoDB Dependency**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```

**Configuration**

```yaml
spring:
  application:
    name: springboot-mongo
  data:
    mongodb:
      host: localhost
      port: 27017
      database: test
```      

### Converter BigDecimal to Double MongoDB
------------------------------------------

**Parent**

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.0.0.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
```

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

import com.example.springbootmongo.converter.BigDecimalToDoubleConverter;
import com.example.springbootmongo.converter.DoubleToBigDecimalConverter;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
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
```

### Aggregation
---------------

```java
package com.example.springbootmongo.repository.impl;

import com.example.springbootmongo.dto.TopSaleDTO;
import com.example.springbootmongo.model.Sale;
import com.example.springbootmongo.repository.SaleRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class SaleRepositoryImpl implements SaleRepositoryCustom {

    @Autowired
    private MongoTemplate mongo;

    @Override
    public List<TopSaleDTO> topSale() {

        GroupOperation group = group(fields()
                .and("description", "description"))
                .count().as("count")
                .sum("total").as("sum");

        SortOperation sort = sort(Sort.Direction.DESC, "count");

        ProjectionOperation project = new ProjectionOperation()
                .and("_id").as("description")
                .and("sum").as("sum")
                .and("count").as("count");

        Aggregation aggregation = newAggregation(
                group,
                project,
                sort,
                limit(5)
        );

        AggregationResults<TopSaleDTO> groupResults = mongo.aggregate(
                aggregation,
                Sale.class,
                TopSaleDTO.class
        );

        return groupResults.getMappedResults();
    }

}
```

---

> See the branch **setup** to understand the setup MongoDB

[Reference](http://ufasoli.blogspot.com.br/2017/06/custom-converter-for-mongodb-and-spring.html)   
