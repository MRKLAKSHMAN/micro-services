# micro-services
This repository contains code for sample project which I wrote during my learning and the code related to micro-services architecture with spring boot framework

Docker Compose file is used to run the containers of all required services at a time "docker compose up"

Spring Cloud Config Server is used to manage the configrurations of multiple profiles like Dev,QA,Staging etc

Spring Cloud Service Registry Eureka is used as a service registry service

Spring cloud Gateway is used to manage the requests from clients by using API Gateway configuration

Feign Client is used to make interservice communication as it provides inbuilt load balancing

RabbitMQ Messaging Queue Service is used to make the project work with requests Asynchronously

Resilience4J is used to setup Fault Tolerance like Circuit Breaker, Retries, Fall Back
