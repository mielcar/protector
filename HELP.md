# Protector

## Architecture

Project is a monorepo, divided into to modules, which can be treated as independent microservices.
Services communicate with one another via Kafka (provided in docker compose file).
All services leverage reactive, nonblocking approach for optimised performance.

### Warehouse

Warehouse service is a Spring Boot application written in onion/hexagonal/ports&adapters architecture.
Whichever name we wouldn't choose it just means that input and output are hidden behind interfaces :).
This allows to easily create new inbound/outbound adapters. Currently, default implementations are UDP inbound and Kafka outbound.
Service processes UDP messages sent to ports 3344 and 3355 and publishes them to kafka server.
All communication details can be overridden by spring configuration properties (application.yml or env properties). 

### Monitoring

Monitoring service is a Spring Boot application that consumes Kafka messages, evaluates them, 
and raises alarm if any sensor data exceeds configured values (logs an error).  

## Building and running

There is a docker-compose file for your convenience, which builds and starts all services + Kafka server.
Please use `docker compose up` for quick startup.

## Testing

Example windows netcat command simulating temperature sensor which exceeds configured threshold:
`echo sensor_id=t1; value=50 | ncat 127.0.0.1 3344 -u`

Check monitoring logs application for message: 
`ERROR 1 --- [monitoring] [-kafka-sensor-1] p.m.s.monitoring.alarm.AlarmService      : Error, measurement exceeds configured threshold! warehouse ID: warehouse1, measurement type: temperature, value: 50, threshold: 35.0`

## To consider

Usage of protobuf for messages structure coordination/validation