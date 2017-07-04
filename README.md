# spring-dockerwith-mongodb


Spring Boot with mongodb using docker.
What you’ll need
A favorite text editor or IDE
JDK 1.8 or later
Gradle 2.3+ or Maven 3.0+
Mongodb 3.2 or later
Docker latest
Note:There is two way to execute the given  project t:-
1. By eclips right click on project execute java application select 
WbApplication java class & run it.
2. By command line Ubuntu16.04 
which is given below steps:-
1.Download the given project  extract and import in eclips Existing Gradle Project.
2.Change application.ypl (Or) application.properties file.
application.ypl  file:-
spring.data.mongodb.uri: mongodb://sanjeet:sanjeet@192.168.1.100:27017/task

Or

application.properties

#server port
#server.port=8080
#session
#spring.session.store-type=none
#mongodb
#spring.data.mongodb.host=127.0.0.1

#spring.data.mongodb.host=192.168.1.100
#spring.data.mongodb.port=27017
#spring.data.mongodb.database=task

#logging
#logging.level.org.springframework.data=debug
#logging.level.=error

3.Observed the given project flow.
4.Open ubuntu terminal CTRL+ALT+T  go upto project base directory execute command-
i.e:sanjeet@sanjeet-lap:~/workspace/SpringWithMongoDB$ ./gradlew build buildDocker
:compileJava UP-TO-DATE
:processResources UP-TO-DATE
:classes UP-TO-DATE
:findMainClass
:jar
:bootRepackage
:assemble
:compileTestJava UP-TO-DATE
:processTestResources UP-TO-DATE
:testClasses UP-TO-DATE
:test UP-TO-DATE
:check UP-TO-DATE
:build
:buildDocker
--
--
 ---> 584e9803c145
Removing intermediate container 08a2cf7f8934
Successfully built 584e9803c145
Successfully tagged spring-boot-docker:latest
After the Push
The "docker push" will fail for you (unless you are part of the "springio" organization at Dockerhub), but if you change the configuration to match your own docker ID then it should succeed, and you will have a new tagged, deployed image.
5.Now u are on last step, Before running project u have to run mongodb 
5.i open new terminal  CTRL+ALT+T.
5.ii Run the given command
sanjeet@sanjeet-lap:~$ sudo gedit /etc/mongod.conf
edit in this file 
# network interfaces
net:
  port: 27017
  bindIp : 127.0.0.1,urIPaddress
example 
# network interfaces
net:
  port: 27017
  bindIp : 127.0.0.1,192.168.1.100
close the file and execute the given command for run mongodb.
sanjeet@sanjeet-lap:~$mongod
6. sanjeet@sanjeet-lap:~/workspace/SpringWithMongoDB$ docker run -p 8080:8080 -t spring-boot-docker
7.Hit on URL=”http://localhost:8080/login”












