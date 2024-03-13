# Variable for Docker image name.
IMAGE_NAME := cinema_app

# Main Makefile commands.
default:
	cat ./Makefile
# Cleaning the project using Maven.
clean:
	./mvnw clean
# Packaging the project using Maven.
package:
	./mvnw package
# Stopping and starting the PostgreSQL container using Docker Compose.
db:
	docker compose down
	docker compose -f docker-compose-db.yaml up -d
# Building the Docker image and starting the application and PostgreSQL containers.
start: clean package db
	docker build -t $(IMAGE_NAME) .
	docker compose -f docker-compose.yaml up -d
	docker compose -f docker-compose.yaml exec app ./wait-for-it.sh --timeout=300 --strict -- http://localhost:8080/actuator/health
# Stopping and restarting the containers.
clean-start: stop start
# Stopping containers using Docker Compose.
stop:
	docker compose down
