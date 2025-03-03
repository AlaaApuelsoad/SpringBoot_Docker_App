# Spring Boot Application with Docker & PostgreSQL

## Overview
This project demonstrates how to containerize a Spring Boot application and connect it to a PostgreSQL database using Docker and Docker Compose.

## Technologies Used
- **Spring Boot**: Backend framework for Java
- **Docker**: Containerization tool
- **Docker Compose**: Manages multi-container applications
- **PostgreSQL**: Relational database

---
## **Docker Concepts**
### **1. Docker**
Docker is a platform for developing, shipping, and running applications inside containers. It allows applications to run consistently across different environments.

### **2. Docker Image**
An image is a lightweight, stand-alone, and executable package that includes everything needed to run a piece of software, including the code, runtime, libraries, and dependencies.

### **3. Docker Container**
A container is an instance of a Docker image that runs as a separate process on a host system. Containers provide isolation and portability.

### **4. Dockerfile**
A `Dockerfile` is a script that contains instructions for building a Docker image.

### **5. Docker Compose**
Docker Compose is a tool for defining and running multi-container Docker applications using a YAML configuration file.

### **6. Docker Volume**
A volume is a persistent storage mechanism that allows data to persist beyond the lifecycle of a container.

### **7. Docker Network**
A Docker network is a virtual network that allows Docker containers to communicate with each other, as well as with the host system and external networks. It helps manage the communication between containers in a secure and isolated manner.

---
## **Project Structure**
```
├── Dockerfile
├── docker-compose.yml
├── src/
│   ├── main/
│   │   ├── java/com/example/DockerApp/
│   │   │   ├── DockerAppApplication.java
│   │   ├── resources/
│   │   │   ├── application.properties
└── README.md
```

---
## **Dockerfile**
```dockerfile
# Use OpenJDK as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy JAR file into container
COPY target/DockerApp.jar app.jar

# Expose application port
EXPOSE 8080

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]
```

---
## **Docker Compose Configuration**
Create a `docker-compose.yml` file to define services for the Spring Boot app and PostgreSQL database.

```yaml
version: '3.8'

services:
  postgres_db:
    image: postgres:latest
    container_name: postgres_container
    restart: always
    environment:
      POSTGRES_DB: demo
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: 0000
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

  spring_app:
    build: .
    container_name: spring_boot_container
    depends_on:
      - postgres_db
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres_db:5432/demo
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: 0000

volumes:
  postgres_data:
```

---
## **Spring Boot Database Configuration**
Edit `src/main/resources/application.properties` to configure PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://postgres_db:5432/demo
spring.datasource.username=postgres
spring.datasource.password=0000
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
```

---
## **Running the Application**

### **Step 1: Build the Spring Boot Application**
```sh
mvn clean package
```

### **Step 2: Start the Application Using Docker Compose**
```sh
docker-compose up -d --build
```

### **Step 3: Verify Running Containers**
```sh
docker ps
```
You should see two running containers: `spring_boot_container` and `postgres_container`.

### **Step 4: Access PostgreSQL Database**
1. Open a terminal and enter the PostgreSQL container:
```sh
docker exec -it postgres_container psql -U postgres
```
2. List all databases:
```sql
\l
```

### **Step 5: Stop and Remove Containers**
To stop containers:
```sh
docker-compose down
```

To remove all Docker volumes (including database data):
```sh
docker-compose down -v
```

---
## **Conclusion**
By using Docker and Docker Compose, we containerized our Spring Boot application and connected it to a PostgreSQL database. This setup makes deployment consistent and scalable.

---
## **Useful Commands**
| Command | Description |
|---------|-------------|
| `docker build -t spring-docker-demo .` | Build Docker image |
| `docker images` | List all Docker images |
| `docker run -p 8080:8080 spring-docker-demo` | Run the container |
| `docker ps` | Show running containers |
| `docker logs spring_boot_container` | View logs |
| `docker-compose up -d` | Start all services in background |
| `docker-compose down` | Stop and remove containers |
| `docker volume ls` | List volumes |

### 🎯 **Now your Spring Boot app is running inside Docker with PostgreSQL! 🚀**

