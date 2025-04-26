# User Service - Food Ordering Microservice System

A standalone Spring Boot microservice responsible for user registration, authentication, and role-based authorization within a scalable cloud-deployable food ordering platform.

## 📦 Tech Stack:
- Java 21
- Spring Boot 3
- Spring Security 6
- JWT-based authentication
- Role-based access control (ADMIN / USER)
- PostgreSQL
- MapStruct
- Lombok

## 📌 Features:
- User registration with role assignment
- Secure login with JWT token generation
- Protected endpoints via JWT token validation
- Role-based endpoint access (USER / ADMIN)
- Swagger UI integration for API testing
- Clean modular architecture ready for microservices expansion

## 🚀 How to Run:
- Install and run PostgreSQL
- Update `src/main/resources/application.properties` with your DB creds
- Run:
  ```bash
  mvn clean install
  mvn spring-boot:run
