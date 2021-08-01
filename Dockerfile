FROM openjdk:8-jdk

ARG ENV
ARG VERSION
ARG PORT

ENV PROFILE_ACTIVE=${ENV}
ENV VERSION_TAG=${VERSION}
ENV EXPOSE_PORT=${PORT}

WORKDIR /app

EXPOSE ${EXPOSE_PORT}

ADD target/lms-service-$VERSION_TAG.jar /app/lms-service-$VERSION_TAG.jar

ENTRYPOINT [ "sh", "-c", "java -jar -Dspring.profiles.active=$PROFILE_ACTIVE -Dserver.port=$EXPOSE_PORT /app/lms-service-$VERSION_TAG.jar"]
