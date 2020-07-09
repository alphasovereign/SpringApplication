# SpringApplication

How to run the application:

1. Clone the repository from develop branch
2. run the below command if the cities.txt file is located externally 
    mvn spring-boot:run -Dspring-boot.run.arguments=--external.city.source=/Users/someuser/Downloads/code/cities.txt

3. if the cities.txt file is not provided, do not pass any runtime arguments. the default cities.txt can be used that is already present in the repo sources file.
    mvn spring-boot:run
