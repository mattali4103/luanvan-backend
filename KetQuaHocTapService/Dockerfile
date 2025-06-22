FROM openjdk:17
WORKDIR /app
COPY ./target/KetQuaHocTapService-0.0.1-SNAPSHOT.jar /app
COPY ./src/main/resources/application.properties /app/config/application.properties

# Make sure the config directory is created
RUN mkdir -p /app/config

# Set the environment variable for the config directory
ENV SPRING_CONFIG_LOCATION=file:/app/config/

# Expose the port on which the application will run
EXPOSE 8089
CMD ["java", "-jar", "KetQuaHocTapService-0.0.1-SNAPSHOT.jar"]