# Flight Data Management API

This app is composed by two service the Flight API and 
the database used for it that is a Postgres so to run them
just use one of the commands below:
```
docker compose up flight_app (This block the terminal)
doker compose up -d flight_app (This one runs in detached mode and let the terminal free)
```
The API also has a Swagger documentation that can be accessed at

```
http://localhost:8080/swagger-ui/index.html#/
```

## Data example

You can load some data to test the App with this script

````
INSERT INTO flights(airline,supplier,fare,departure_airport,destination_airport,departure_time,arrival_time)
values('American airlines', 'American Flights',100,'LAS','HOU','2024-05-10T12:00:00','2024-05-11T00:00:00');
INSERT INTO flights(airline,supplier,fare,departure_airport,destination_airport,departure_time,arrival_time)
values('American Airlines', 'American Flights',1400,'DAL','HOU','2024-05-8T10:00:00','2024-05-9T00:00:00');
INSERT INTO flights(airline,supplier,fare,departure_airport,destination_airport,departure_time,arrival_time)
values('Delta Airlines', 'Delta Flights',1400,'WSH','NYC','2024-05-5T10:00:00','2024-05-5T11:40:00');
INSERT INTO flights(airline,supplier,fare,departure_airport,destination_airport,departure_time,arrival_time)
values('United Airlines', 'United Flights',2000,'FLD','LAS','2024-05-05T10:00:00','2024-06-12T00:00:00');
INSERT INTO flights(airline,supplier,fare,departure_airport,destination_airport,departure_time,arrival_time)
values('Porter Airlines', 'Porter Flights',3000,'TOR','VAN','2024-05-11T00:00:00','2024-06-12T00:00:00');

INSERT INTO airlines(name,code) VALUES('American Airlines','AAL');
INSERT INTO airlines(name,code) VALUES('United Airlines','UAL');
INSERT INTO airlines(name,code) VALUES('Delta Airlines','DAL');
INSERT INTO airlines(name,code) VALUES('Porter Airlines','POE');

```