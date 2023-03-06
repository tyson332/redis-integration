"# redis-integration"

###Spring Boot Build commands
>mvn clean package spring-boot:repackage
>java -jar target\redis-integrator.jar


 
###Run in docker
>docker build -t redis-integration:5.0 .
>docker run --name redis-integration -d -p 9191:9090 -p 6379:6379 redis-integration:5.0