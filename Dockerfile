FROM openjdk:latest
COPY ./target/GroupProject-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar","GroupProject-1.0-SNAPSHOT-jar-with-dependencies.jar"]