
## Entities

- **Class**: Represents a gym class, with fields for name, capacity, instructor, and members.
- **Instructor**: Represents an instructor, can teach multiple classes.
- **Member**: Represents a gym member, can enroll in classes.
- **Locker**: Represents a locker assigned to members.

## Controllers

- `ClassController`
- `InstructorController`
- `MemberController`
- `LockerController`

Each controller exposes REST endpoints for managing the corresponding entity.

## Repositories

- `ClassRepository`
- `InstructorRepository`
- `MemberRepository`
- `LockerRepository`

These are Spring Data JPA repositories for database operations.

## Getting Started

### Prerequisites

- Java 17+
- Maven
- MySQL

### Setup

1. **Clone the repository**

   ```sh
   git clone <your-repo-url>
   cd GymFitness
   ```

2. **Configure the Database**

   Create a MySQL database named `gym`:

   ```sql
   CREATE DATABASE gym;
   ```

   Update `src/main/resources/application.properties` with your MySQL username and password:

   ```
   spring.datasource.username=YOUR_USERNAME
   spring.datasource.password=YOUR_PASSWORD
   ```

3. **Build and Run**

   ```sh
   mvn spring-boot:run
   ```

   The application will start on [http://localhost:8088](http://localhost:8088).

### API Usage

You can interact with the API using tools like Postman or curl. Example endpoints:

- `GET /classes` - List all classes
- `POST /members` - Add a new member
- `PUT /lockers/{id}` - Update locker info
- etc.

### Useful Documentation

- [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Spring Data JPA Guide](https://spring.io/guides/gs/accessing-data-jpa/)
- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)

## Customization

- Change the server port in `application.properties` (`server.port=8088`)
- Enable SQL logging by setting `spring.jpa.show-sql=true`

## License

This project is for educational purposes.
