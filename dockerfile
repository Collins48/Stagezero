ROM openjdk:17-jdk-slim


ADD target/*.jar /STAGEONEAPI-0.0.1-SNAPSHOT.jar

RUN ls -ltrh /

# default environment
ENV JVM_OPTS=""

ENV SPRING_OPTS=""

ENTRYPOINT ["java", "-jar", "/STAGEONEAPI-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080