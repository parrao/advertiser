# iHeart Media
iHeart Media Services

Requirements:
Java 1.8

Build instruction:
-- Go to project home folder and run the below command
$ ./gradlew clean build

To see the Jacoco report:
<project_folder>/iheart-media-services/build/reports/jacoco/test/html/index.html

To see the Test report:
<project_folder>/iheart-media-services/build/reports/tests/test/index.html

Run instruction:
 --  Once build successful
$ java -jar build/libs/iheart-media-services-0.0.1.jar


To Access Service Endpoints:
http://localhost:8080/swagger-ui.html

To Access Actuator
http://localhost:8090/actuator
http://localhost:8090/actuator/info

To Access Flyway Actuator:
http://localhost:8090/actuator/flyway

To H2 DB console:
http://localhost:8080/h2


To make docker image:
-- copy the docker and build jar in folder and run the below command
docker build -t iheart-media-services .


Docker Image in docker hub:
https://hub.docker.com/r/parrao/iheart-media-services/


To download image from docker hun and run
$ docker run -p 8080:8080 -p 8090:8090 parrao/iheart-media-services:v2
 

