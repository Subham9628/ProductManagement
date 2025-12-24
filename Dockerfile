# ===============================
# BUILD STAGE
# ===============================
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /build

# copy pom first (better cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# copy source code
COPY src ./src

# build jar
RUN mvn clean package -DskipTests

# ===============================
# RUNTIME STAGE
# ===============================
FROM eclipse-temurin:17-jre
WORKDIR /app

# copy jar from build stage
COPY --from=build /build/target/*.jar app.jar

# expose spring boot port
EXPOSE 8080

# run application
ENTRYPOINT ["java", "-jar", "app.jar"]
