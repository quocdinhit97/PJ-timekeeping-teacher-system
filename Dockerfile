#EVR
FROM openjdk:8-jre
VOLUME /tmp

#Set default port for container
EXPOSE 8010

#Define path jar file
ARG JAR_FILE=target/demo-0.0.1-SNAPSHOT.jar

#Copy jar file to app.jar
ADD  ${JAR_FILE} app.jar

#Execute file jar
ENTRYPOINT ["java", "-jar", "app.jar"]
