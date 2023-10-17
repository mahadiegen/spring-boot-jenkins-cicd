FROM openjdk:11-slim
ADD target/cicd.jar cicd.jar
EXPOSE 8088
ENTRYPOINT ["java","-jar","cicd.jar"]