# TaskFlow - GH-200 demo app

Small Spring Boot 3 MVC + Thymeleaf task manager used as the sample project for the
GitHub Actions (GH-200) sessions. Data is held in memory, so there is nothing to provision.

## Stack
- Java 17, Maven, Spring Boot 3.3
- Spring MVC, Thymeleaf, Bean Validation, Actuator
- JUnit 5 + MockMvc tests

## Run locally
    mvn spring-boot:run
Open http://localhost:8080 - health check at http://localhost:8080/actuator/health

## Build and test
    mvn -B verify
Produces `target/taskflow.jar` (run with `java -jar target/taskflow.jar`).

## Workflows
- `.github/workflows/ci.yml` - Day 1: build, test, upload reports and jar
- `docs/workflow-samples/deploy-azure.yml` - Day 2: deploy to Azure App Service
  (needs secret `AZURE_WEBAPP_PUBLISH_PROFILE` and variable `AZURE_WEBAPP_NAME`)

## Ideas for lab exercises
- Add a Java version matrix (17 / 21)
- Add a failing test and watch the PR check block the merge
- Add Dependabot and CodeQL workflows
- Turn the build steps into a reusable workflow
