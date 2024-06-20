Personalized Data Service
=======================
This project is an e-commerce application that allows retrieving products by shopper ID with optional filters such as category and brand. It utilizes Spring Boot and Spring Data JPA for backend functionality.


## Components

- **config**: Contains configuration classes for the application, such as Swagger configuration.

- **controller**: REST controllers for handling HTTP requests and responses.

- **dto**: Data Transfer Objects for transferring data between layers of the application.

- **exception**: Custom exception classes for handling specific errors in the application.

- **model**: Entity classes representing data models in the database.

- **repository**: Spring Data JPA repositories for database operations.

- **service**: Service layer classes that contain business logic and interact with repositories.

- **specification**: Specifications for constructing dynamic queries using Spring Data JPA.

## Usage

- **Prerequisites**: Ensure you have Java 17 and Maven installed on your machine.
- **Build**: Use Maven to build the project:
```commandline
mvn clean install
```
- **Run**: Start the Spring Boot application:
```commandline
mvn spring-boot:run
```
- **Swagger API documentation**: `http://localhost:8080/swagger-ui/index.html#/`

## Testing
- Use Maven to run tests:
```commandline
mvn test
```
