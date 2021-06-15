## Goal:

The Goal is to implement a game with two independent units – the players – communicating with each other using an API.

## Description

When a player starts, it insects a random (whole) number and sends it to the second player as an approach of starting the game. 
The receiving player can now always choose between adding one of {-1, 0, 1} to get to a number that is divisible by 3. Divide it by three. 
The resulting whole number is then sent back to the original sender.

## Architecture
I used the hexagonal and onion architectures, so you can find Application(inbound, outbound), Domain, Infrastructure(Broker, Rest API, and Repository).
When the state is changed within a domain, it raises an event that is listened to by outbound in the Application layer, then it is published by the broker in Infrastructure.

## Solution
Using a rich domain model instead of an anemic domain model, hence data and behavior sit together. There are three aggregates which are Game, Player, and Game Round. I used circle array to manage Player's turn and **Repository Pattern** to save and restore output of each move
,**Visitor Pattern** to handle Human and Machine play and **Pub-sub Pattern** to publish event to Game, Player, GameRound components

**As soon as** a player defines its name and which player he/she wants to play(with Human or Machine). It is persisted at the Player component,
then raise an event, which is listened by Player **Outbound** and publish by Broker at the **Infrastructure**,
to the Game, component to create and initialize a game, and generate a random number to start.

**Game** component gets an event at **CommandService(Application Layer)** then it calls the Game Aggregate's method to create/initialize a game.

The same thing happens between **Game** and **Game Round** components. Game Round just persists the game round to be used as a reporting service.

## Config
The main and test project has its own application. Properties, so you can config each base on your criteria.
## Configuration
- to configure the application, set the `resources/application.properties`
- to change logging level set in the `resources/log4j.properties`
## Requirements
- java 11
- maven 3

### Run
- To run it use this command: `sudo docker build -t game-three .`
- Then we run the docker image: `sudo docker run -p 8010:8010 game-three`
- The address of rest api is: `http://loclahost:8010`
- The address of swagger is: `http://localhost:8010/swagger-ui.html`

### Or
- run to build app: `mvn clean` then `mvn package`
- run 'java -jar target/game-three.jar'

**Note** At the GameTest class there are tests you can run to see how Game is working

Good luck


