FROM openjdk:17
WORKDIR /app
COPY ./target/user-service-0.0.1-SNAPSHOT.jar /app
COPY ./src/main/resources/application-docker.properties /app/config/application.properties

# Make sure the config directory is created
RUN mkdir -p /app/config

# Set the environment variable for Spring profile
ENV SPRING_PROFILES_ACTIVE=docker

# Expose the port
EXPOSE 8088
CMD ["java", "-jar", "user-service-0.0.1-SNAPSHOT.jar"]