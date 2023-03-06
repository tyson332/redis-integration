#base image name
FROM openjdk:8

#RUN will execute the commnad on the image
RUN mkdir /app

#Copy will copy from host to an image
COPY target/redis-integrator.jar /app

#Defalt directory on the image
WORKDIR /app

#Enabling port outside of container
#EXPOSE 9090 6379

# Execute below command in WORKDIR while running image
CMD java -jar redis-integrator.jar
