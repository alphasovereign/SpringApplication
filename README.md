# SpringApplication

How to run the application:

1. Clone the repository from develop branch
2. Change the directory to "city-routes". then,
3. run the below command if the cities.txt file is located externally 
    "mvn spring-boot:run -Dspring-boot.run.arguments=--external.city.source=/Users/someuser/Downloads/code/cities.txt"

4. if the cities.txt file is not provided, do not pass any runtime arguments. the default cities.txt can be used that is already present in the repo sources file.
    "mvn spring-boot:run"
5. File name can be canyting as long as the txt  data format is maintained as <key,value> pairs:
6. After the server startup run the command ->  "http://localhost:8080/connected?origin=Boston&destination=New%20York"
