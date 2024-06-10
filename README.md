# SportCourt

## Description

This is a Springboot backend application that provides a REST API for managing tennis club's
courts and reservations.

## Setup

To start this application you just need to run the following command:

```
mvn spring-boot:run
```

The seeding will be done automatically when the application starts. If you want to turn off the seeding you can change:
`
spring.jpa.defer-datasource-initialization=false
`
in application.properties.

## API Documentation

The API documentation is available at the following URL:

```
http://localhost:8080/swagger-ui.html
```