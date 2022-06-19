# urap
User Registration and Accounting Project for Spring Boot (Test task)
REST API Documentation http://localhost:8080/swagger-ui.html

Steps/recommendations to deploy application:
1. (Optional - if there were some old volumes used in Docker for Postgres DB) removing old volumes.
Call from docker directory:
    docker-compose down --volumes
2. Create image with dockerfile (from home directory):
    mvn install
    docker build -t urap .
3. Run container with docker-compose (from home directory):
    docker-compose -f .\docker\docker-compose.yml up