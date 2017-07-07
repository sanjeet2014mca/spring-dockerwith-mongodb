FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER Aliaksei Bahdanau lex.nox@gmail.com
VOLUME /tmp
ADD spring-boot-docker-0.1.0.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://192.168.1.69:27017/task","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]