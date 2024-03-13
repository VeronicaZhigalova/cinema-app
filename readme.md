# Project Overview:

## Description:
The cinema management application is designed to optimize various operations related to clients, movies, reservations, seats, and showtimes. It provides functionalities for client registration, ticket reservation, and history management. Administrators can view client lists, manage movie schedules, and oversee reservations.

## Key Features:

### Client Registration and Management:
- Efficient handling of client registration and management.
### Movie Creation and Schedule Management:
- Creation and management of movie schedules.
### Ticket and Seat Operations:
- Seamless operations related to tickets and seats.
### Reservation Creation, Modification, and Cancellation:
- Flexible features for creating, modifying, and canceling reservations.
### Cinema Schedule Management:
- Reliable management of the cinema schedule.

## Technologies Used:

### Spring Boot:
- Used for building robust and scalable Java-based applications.
- Facilitates the development of a microservices architecture.
### Spring Data JPA:
- Simplifies data access using the Java Persistence API (JPA).
- Allows seamless integration with relational databases.
### Hibernate:
- Object-relational mapping framework integrated with Spring Data JPA.
- Facilitates database operations using Java objects.
### Flyway:
- Used for database migration to manage and version database schemas.
- Ensures consistency across different environments.
### Maven:
- Build automation tool for managing project dependencies.
- Simplifies the build process and project management.

## Additional Resources:

### Flyway Callbacks:
- Leveraged Flyway callbacks for custom actions during the migration process.
- Callbacks provide hooks to execute code before or after database migrations.
### Maven Repository:
- Utilized Maven Repository to manage project dependencies.
- Ensured easy access to libraries and frameworks needed for the application.

## Links:
- Flyway Callback Concept (Flyway Documentation) 
   - Callback concept - Flyway - Product Documentation (red-gate.com)
- Maven Repository (Dependency Management)
   - Maven Repository: Search/Browse/Explore (mvnrepository.com)

## Project Structure:
The project follows a modular structure with controllers, services, repositories, and entities to organize and separate concerns. The use of Spring Boot enables the development of a RESTful API, while Spring Data JPA simplifies database interactions. Flyway ensures smooth database migrations, and Maven manages project dependencies for a streamlined development process.

## Instructions for Running the Application:
- Step 1: 
  - Running Containers Using Docker Compose:
Open the docker-compose.yaml file.
Click "Start" (or execute the command docker-compose up -d in the terminal).
This will start the PostgreSQL and Spring Boot application containers.

- Step 2:
  - Running the Spring Boot Application:
Navigate to the CinemaAppApplication directory.
Run the Spring Boot application.

- Step 3: 
  - Testing:
Now that the application is running, you can proceed to testing.
Integration tests are located in the integration package inside src/test.
Unit tests are in the respective packages for services in src/test.

## Note:
- Ensure that Docker is installed on your computer.
- Preconfigure environment variables for connecting Spring Boot to PostgreSQL in docker-compose.yaml.
- If necessary, change ports in docker-compose.yaml and corresponding settings in Spring Boot.

# Challenges and Time Spent:

## Challenges:
### Coordinating Container Interaction:
- Required proper configuration of interaction between Spring Boot and PostgreSQL containers.
### Ensuring Correct Database Schema Initialization:
- Needed to ensure the correct initialization of the database schema when the container starts.
### Configuring Environment Variables:
- Required correct configuration of environment variables for seamless integration.
## Time Spent:
- The project was developed over the course of one week.
- Several hours were dedicated each day to project creation, environment setup, and problem-solving.
## Note:
- Intensive use of Docker Compose, Makefile, and related tools streamlined development.
- Docker usage provided isolation and consistency across different environments.

## Assistance Sought from Google:
Throughout the project, I referred to Google for guidance on resolving challenges related to Docker Compose, Spring Boot, Flyway, and Maven. I utilized online resources, documentation, and community forums to overcome specific issues and enhance my understanding of these technologies.
