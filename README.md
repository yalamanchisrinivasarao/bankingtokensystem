## ABC​ ​Banking

### Build and run

#### Prerequisites

- Java 8
- Maven > 3.0
- MySQL

#### Configurations

Open the `application.properties` file and set the MySQL configuration.

#### Schema installation

Run the file install_schema.sql present in src/main/resources to set up the database. Following tables would be created:

- customer
- address
- counter
- service
- service_counter_mapping
- token
- token_service_mapping
- user
- role
- user_role

ER Diagram

Please refer the ERDiagram.mwb

Class Diagram

Please refer ClassDiagram.ucls


#### From Editor (IntelliJ or Eclipse)

Import as *Existing Maven Project* and run it as *Spring Boot App*.


### Usage

- Run the application and go on http://localhost:8080/



### Controller

#### HomeController
    GET / Welcome 

#### CustomerController
    POST /customers Create a new Customer
    
    {
  "name": "Srinivasa Rao Yalamanchi",
  "mobile": "8669083993",
  "type": "PREMIUM",
  "address": {
    "name": "Srinivasa Rao Yaamanchi",
    "addressLine1": "Near Jaiswal Hospital",
    "addressLine2": "Moti Nagar",
    "city": "Hyderabad",
    "state": "Telangana",
    "zipCode": "500018"
  }
}

    GET /customers/{mobile} Lookup customer via mobile

#### TokenController
    GET /tokens Gives a counter wise list of active tokens
    POST /tokens Generates a new token, takes customer and service(s) as input
    
    {
  "customerMobile": "8669083993",
  "services" : ["A"]
}


    PUT /tokens/{tokenNumber}/cancel Cancels an active token
    PUT /tokens/{tokenNumber}/complete Marks a service token a complete, in case the token is a multi-counter token, it gets queued at the next counter
    PUT /tokens/{tokenNumber}/comment Records a comment against the current service of the token
            
#### ServiceController
    GET /services List all the available banking services, some services are multi-counter
     
#### CounterController
    GET /counters List all the counters

### Token Generation Strategy

Token generation strategy has been kept as pluggable. The current default implementation
first identifies counters based on the customer type and then chooses the counter with the 
minimum queue size.


While issuing a token, the systems identifies all the services that need to be served by this token 
(either user chooses multiple services or service that by itself is a multi-counter service). Once a token
 is marked as complete for the current service, token is queued at the next counter for the next service.
 
### Token Sequence Generation

A Simple date based sequential generator has been used. The sequence gets reset at 00 hours everyday.
