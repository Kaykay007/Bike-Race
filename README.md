## Bike Race  API

Overview
The Bike Race API provides endpoints for managing race-related data, including information about riders, race results, and weather conditions. This API allows users to retrieve details about the fastest riders, riders who did not finish, and riders not participating in a race. 
Additionally, it offers weather information for specific racing location.

## Features

Retrieve Fastest Riders: Get a list of the fastest riders for a given race.
Riders Who Did Not Finish: Obtain a list of riders who did not finish the race.
Riders Not in Race: Fetch a list of riders not participating in a specified race.
Current Weather: Access current weather data for a race specified location (city).
Weather Forecast: Get the weather forecast for a race  specific location  (city).

##Technologies Used
Spring Boot: Framework for building the API.
Spring Web: For creating RESTful web services.
Spring Data JPA: For database operations.
Postgres Databse: database for development and testing.
OpenAPI (Springdoc): For API documentation and Swagger UI.
JUnit & Mockito: For unit testing.


## Getting Started

Prerequisites
Java 17 
Maven 
An IDE (IntelliJ IDEA)


## Installation

Clone the repository:

git clone https://github.com/yourusername/race-management-api.git

cd Bike-Race

Build the project:

bash mvn clean install

## API Endpoints

Get Fastest Riders
GET /v1/api/races/{raceId}/fastest-riders

Get Riders Who Did Not Finish
GET /v1/api/races/{raceId}/did-not-finish

Get Riders Not in Race
GET /v1/api/races/{raceId}/not-in-race

Get Current Weather
GET /v1/api/races/current/{cityName}

Get Weather Forecast
GET /v1/api/races/forecast/{cityName}

## Configuration
The API key and base URL for the weather service are configured in the application.properties file

weather.api.url=http://api.openweathermap.org/data/2.5

weather.api.key=your_api_key_here

## Testing
Unit tests can be run using Maven:

bash mvn test

## API Documentation

API documentation is available at:

http://localhost:8080/swagger-ui
