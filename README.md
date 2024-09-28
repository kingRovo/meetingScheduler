# Meeting Scheduler

A Spring Boot application for scheduling meetings with collision detection.

## Features

- Schedule meetings with multiple attendees
- Detect scheduling conflicts for users and rooms
- Manage users and meeting rooms
- RESTful API for easy integration

## Technologies

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or later
- Maven 3.6+
- PostgreSQL 12+

### Installation

1. Clone the repository:
   ```
   git clone https://github.com/kingRovo/meetingScheduler.git
   ```

2. Configure the database connection in `src/main/resources/application.properties`:
   ```
   spring.datasource.url=jdbc:postgresql://localhost:5432/meeting_scheduler
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. Build the project:
   ```
   mvn clean install
   ```

4. Run the application:
   ```
   mvn spring-boot:run
   ```

The application will start and be available at `http://localhost:8080`.

## Usage

You can interact with the application using HTTP requests to the API endpoints. Use tools like cURL, Postman, or any HTTP client to send requests.

Example of scheduling a meeting:

```bash
curl -X POST http://localhost:8080/api/meetings \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Team Meeting",
    "startTime": "1727495602000",
    "endTime": "1727499202000",
    "organizerId": "123e4567-e89b-12d3-a456-426614174000",
    "attendeeIds": ["123e4567-e89b-12d3-a456-426614174001", "123e4567-e89b-12d3-a456-426614174002"],
    "roomId": "123e4567-e89b-12d3-a456-426614174003"
  }'
```

## API Endpoints

- POST `/api/meetings`: Schedule a new meeting
- GET `/api/meetings/{id}`: Get details of a specific meeting

## Database Schema

The application uses the following main entities:

- `User`: Represents a user who can organize or attend meetings
- `Meeting`: Represents a scheduled meeting
- `Room`: Represents a meeting room

Refer to the JPA entity classes in the `model` package for detailed schema information.


