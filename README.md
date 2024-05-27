# Clients
The project is a Java-based Spring Boot application for managing client data. There are two versions of this project, one under the client-back-hexa directory and another under the client-backMVC directory. Both versions seem to share the same routes and functionality, suggesting they may be variants of the same project implemented using different architectural styles (possibly Hexagonal Architecture for the hexa version and Model-View-Controller for the MVC version).

Key Components

REST API Endpoints:

- Create Client:
  POST /v1/client: Adds a new client.
- Update Client:
  PUT /v1/client: Updates an existing client's details.
- Get Client by Shared Key:
  GET /v1/client/sharedKey/{sharedKey}: Retrieves a client's details using their shared key.
- Get All Clients:
  GET /v1/client: Fetches details of all clients.

## Infrastructure Layer

The infrastructure layer contains the implementation of various adapters for communication with external systems (e.g., databases).

ClientRestAdapter: This class manages the RESTful API interactions.

```java
Copy
// Example of creating a client
@PostMapping("/v1/client")
public ResponseEntity<CreateClientResponse> createClient(@Valid @RequestBody CreateClientRequest createClientRequest) {
// Implementation logic
}

```

## ClientPersistenceAdapter

This class handles the communication with the database.

```java
Copy
// Example of saving a client to the database
@Override
public Client saveClient(Client client) {
return clientPersistenceMapper.toModel(clientRepository.save(clientPersistenceMapper.toEntity(client)));
}
```

## Domain Layer

This layer includes the business logic.
GetClientUseCaseImpl: Implementation of the use case for retrieving clients.

```java
java
Copy
@Override
public Client getClientById(String id) {
return clientPersistencePort.getClientById(id)
.orElseThrow(() -> new ClientNotFoundException(String.format("Client not found [id]: %s", id)));
}
```

## Database Interaction

The application interacts with the client table in the database.

ClientRepository: Defines methods like findById, findByEmail, and findAll.

# Build and Run:
```
To build the application: mvn package
To run the application: mvn spring-boot:run
The application runs on port 8090.
bash
Copy

# Build and run commands

mvn package
mvn spring-boot:run
```

# Data and Http routes

## Routes

- GET /v1/client
- GET /v1/client/sharedKey/{sharedKey}
- POST /v1/client
- PUT /v1/client


# Tables:

client
Configuration Files:
The project uses .idea/uiDesigner.xml for defining UI components, though these seem unrelated to its core functionality, indicating a possible legacy or incomplete feature.
Summary
This project provides a REST API for managing client data, supported by a robust architecture involving distinct layers for handling domain logic and infrastructure concerns. The available client operations include creating, updating, and retrieving client details through well-defined REST endpoints.

If you have any specific questions or need further details about any part of the project, feel free to ask!
