FROM openjdk:17-alpine
ARG JAR_FILE=target/vehicleloan-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} /app/vehicleloan-1.0-SNAPSHOT.jar
CMD java -jar /app/vehicleloan-1.0-SNAPSHOT.jar $FILE_PATH
