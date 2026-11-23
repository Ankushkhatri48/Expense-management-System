# Stage 1: Build JAR using Java 25 JDK & Maven
FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /app

# Use Google's Maven Central mirror to avoid 429 Too Many Requests from repo.maven.apache.org
RUN mkdir -p /root/.m2 && echo '<settings><mirrors><mirror><id>google-maven-central</id><name>GCS Maven Central mirror</name><url>https://maven-central.storage-download.googleapis.com/maven2/</url><mirrorOf>central</mirrorOf></mirror></mirrors></settings>' > /root/.m2/settings.xml

COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests -Dhttp.agent="Maven-Build/3.9"

# Stage 2: Lightweight Runtime Image
FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xmx384m", "-jar", "app.jar"]
