FROM 8-jdk
MAINTAINER Aliaksei Bahdanau sanjeet15121991@gmail.com
ADD spring-boot-docker-0.1.0.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://192.168.1.69:27017/digest","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
