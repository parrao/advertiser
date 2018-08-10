FROM parrao/docker-jdk8 
MAINTAINER paratieshwara.rao@gmail.com

# Define working directory.
WORKDIR /app

#copy the iheart-media-services jar into the image
ADD  iheart-media-services-0.0.1.jar /app/iheart-media-services-0.0.1.jar

#run the jar
CMD ["java", "-jar", "/app/iheart-media-services-0.0.1.jar"]

# Default http port
EXPOSE 8080
EXPOSE 8090