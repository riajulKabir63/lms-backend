# 1. Start with a tiny, pre-configured Linux operating system that has Java 17 installed
FROM eclipse-temurin:17-jre

# 2. Tell Docker where you want to work inside this new mini-server
WORKDIR /app

# 3. Copy newly created JAR file from your Mac into the Docker container
COPY target/LMSF-0.0.1-SNAPSHOT.jar app.jar

# 4. Tell Docker what port this service uses
EXPOSE 8081

# 5. The exact command to start the application
ENTRYPOINT ["java", "-jar", "app.jar"]