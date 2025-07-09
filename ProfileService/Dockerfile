FROM openjdk:17
WORKDIR /app
COPY ./target/ProfileService-0.0.1-SNAPSHOT.jar /app
COPY ./src/main/resources/application-docker.properties /app/config/application.properties

# Make sure the config directory is created
RUN mkdir -p /app/config

# Set the environment variable for Spring profile
ENV SPRING_PROFILES_ACTIVE=docker

# Expose the port
EXPOSE 8083
CMD ["java", "-jar", "ProfileService-0.0.1-SNAPSHOT.jar"]