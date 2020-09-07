# Spring Boot WebFlux
```
Etherscan open API call
API key required on Request
``` 
# Log4j2
```
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-web</artifactId>
   <exclusions>
      <!-- exclude logback , add log4j2 -->
      <exclusion>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-logging</artifactId>
      </exclusion>
   </exclusions>
</dependency>
```
```
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```
```
log4j2.xml
```