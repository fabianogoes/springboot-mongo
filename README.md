# SpringBoot MongoDB 

> Project Open Source created to share collaboratively experiences with MongoDB and Spring Boot 

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

