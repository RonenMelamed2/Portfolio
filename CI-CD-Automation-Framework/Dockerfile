# ---- BASE IMAGE ----
# Use the official OpenJDK 17 (LTS) slim image, industry standard for Java projects
FROM eclipse-temurin:17-jdk-jammy AS build

# ---- ENVIRONMENT SETUP ----
# Set working directory inside the container
WORKDIR /app

# ---- DEPENDENCIES (MAVEN) ----
# Install Maven, curl (needed for some Maven plugins), and clean up to keep the image small
RUN apt-get update \
  && apt-get install -y maven curl \
  && apt-get clean \
  && rm -rf /var/lib/apt/lists/*

# ---- PROJECT SETUP ----
# Copy only the pom.xml first to take advantage of Docker layer caching (speeds up rebuilds)
COPY pom.xml ./

# Download all dependencies; this is re-used unless pom.xml changes. saves 2-5 minutes per build
RUN mvn dependency:go-offline

# Copy the rest of the project sources
COPY . .

# Build and test the project (RUN can be removed if you want to test only at runtime)
# This command is here for Docker build-time test runs; if you prefer, use ENTRYPOINT instead.
RUN mvn clean test

# ---- DEFAULT EXECUTION (OPTIONAL) ----
# When running the container (docker run ...), run all tests again (can override in Jenkins)
CMD ["mvn", "clean", "test"]

# ---- END OF DOCKERFILE ----
# Summary:
# - Starts from Java 17 slim image
# - Installs Maven
# - Downloads dependencies for quick rebuilds
# - Copies all sources and runs tests
# - By default, runs all TestNG tests when the container starts

