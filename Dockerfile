FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY /target/UserMIS-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 6200
ENV JAVA_OPTS=""
RUN sh -c "touch UserMIS-0.0.1-SNAPSHOT.jar"
ENTRYPOINT [ "java", "-jar", "UserMIS-0.0.1-SNAPSHOT.jar" ]