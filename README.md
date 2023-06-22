#Drone REST API Service
This is a REST API service that allows clients to communicate with drones for registering drones, loading medication items, checking loaded medication items, checking available drones for loading, and checking drone battery levels.

##Prerequisites
- Java Development Kit (JDK) 8 or higher
- Gradle 6.8.3
- Docker (optional, for running a database container)
##Technologies Used
- Java
- Spring Boot
- Spring Data JPA
- Spring Web
- MySql (can be replaced with any other database)
##Getting Started
Clone the Repository
```shell
git clone https://github.com/foreverluis/MusalaDrones.git
cd drones
```
##Build the Application

```shell
gradle build
```
##Run the Application
###Option 1: Run with Gradle
```shell
gradle bootRun
```
###Option 2: Run as JAR file
```shell
java -jar target/drones.jar
```
##Access the API
The API can be accessed at http://localhost:8080

##API Endpoints
###Register a Drone
Endpoint: POST /drones

Content-Type: application/json

Request Body:

```json
{
"serialNumber": "SERIAL000",
"model": "LightWeight",
"weightLimit": 100,
"batteryCapacity": 80,
"state": "IDLE"
}

```
Response:
200 OK, CREATED
```json

{
"serialNumber": "SERIAL000",
"model": "LightWeight",
"weightLimit": 100,
"batteryCapacity": 80,
"state": "IDLE"
}

```
###Load Medication on a Drone
Endpoint: POST /drone/{droneSerial}/load-medication

Content-Type: application/json

Request Body:

```json
{
"medicationCode": "MD001",
"quantity": 3
}
```
Response:
200 OK, CREATED

###Get Loaded Medication on a Drone
Endpoint: GET /drone/{droneSerial}/loaded-medications

###Request Parameters:

- droneSerial - The serial of the drone to retrieve loaded medication from
###Response:

```json
[

  {
    "medicationCode": "MD03150",
    "medicationName": "Medicine 3",
    "medicationWeight": 150,
    "medicationImage": "MD03150.JPG",
    "quantity": 3
  },
  {
    "medicationCode": "MD05250",
    "medicationName": "Medicine 5",
    "medicationWeight": 250,
    "medicationImage": "MD05250.JPG",
    "quantity": 5
  }
]

```
###Get Available Drones for Loading
Endpoint: GET /drone/check-available-drones

Response:
```json
[
{
"serialNumber": "SERIAL21",
"droneModel": "HeavyWeight",
"weightLimit": 499,
"batteryCapacity": 33,
"droneState": "IDLE"
},
{
"serialNumber": "SERIAL22",
"droneModel": "LightWeight",
"weightLimit": 150,
"batteryCapacity": 55,
"droneState": "IDLE"
}
]
```

###Get Drone Battery Level
Endpoint: GET /drone/{droneSerial}/batteryLevel

###Request Parameters:

- droneSerial - The serial of the drone to retrieve battery level from
Response:
```json
90
```
##Database Configuration
By default, the application uses an MySql Database. If you prefer to use a different database, you can modify the database configuration in the application.yaml file.
The database Script is provided(DDL & INSERT) for reference tables and dummy data

##Running Tests
To run the unit tests, execute the following command:
```shell
gradle test
```
