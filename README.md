# Accounts Service

## Overview

The Accounts Service is a Kotlin Spring Boot Application that utilizes various technologies to provide a robust and
secure account management system. It employs Hibernate for database interaction, PostgreSQL as the database, JUnit 5 for
testing, JWT Tokens and Spring Security for authentication, and Logback and Log4J for logging purposes. The architecture
follows the Spring Boot principles for easy development and deployment.

## Features

- **Kotlin & Spring Boot**: Leveraging the power and simplicity of Kotlin and the Spring Boot framework for rapid
  development and deployment.
- **Hibernate & PostgreSQL**: Utilizing Hibernate for ORM (Object-Relational Mapping) to interact with a PostgreSQL
  database.
- **JWT Tokens & Spring Security**: Ensuring secure authentication using JWT Tokens and Spring Security mechanisms.
- **Scalability**: Designed for horizontal scalability, allowing the system to scale by spinning up additional instances
  as needed.
- **Docker Support**: Includes a Dockerfile for easy containerization, enabling seamless deployment across various
  environments.
- **Architecture & Design**: Implemented the Spring Boot Architecture (Presentation, Business, Persistence and Database
  Layer)

## Usage

To use the Accounts Service, follow these steps:

1. Clone the repository to your local machine.
2. Ensure you have JDK 17 installed.
3. Set up a PostgreSQL database and configure the application.yml file with the appropriate database connection details.
4. Build the application using Gradle Kotlin.
5. Run the application using the generated JAR file or through your IDE.

This repository includes a Postman collection named accounts-service.postman_collection, which contains a set of endpoints useful for testing the accounts service.

## Outstanding Items

While the service is functional, there are still some areas for improvement and additional features to be implemented:

1. **JWT Token Integration**: JWT implemented and returning token on successful login. However token passed for other
   endpoints not implemented yet.
2. **Enhanced Testing**: Improve testing by adding more tests and improving coverage
3. **Report Query Optimization**: Revisit and optimize the report query for improved performance and efficiency. (
   10minute delay assumption not catered for currently)
