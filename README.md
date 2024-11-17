# Trade Reporting Engine

## Overview

The Trade Reporting Engine is a Spring Boot application designed to process trade event data stored in XML files, filter them based on specific business rules, and expose the filtered results via REST APIs.


---

## **Features**
- Parse XML files to extract trade event details.
- Persist data in a database using JPA.
- Apply filtering rules:
  - Select specific `sellerParty` and `premiumCurrency` combinations.
  - Exclude events where `buyerParty` and `sellerParty` are anagrams.
- Expose filtered results via REST endpoints.

---

## **Technologies**
- **Backend Framework:** Spring Boot
- **Database:** H2 (in-memory for development)
- **Build Tool:** Maven
- **Java Version:** 17
- **Testing:** JUnit, Mockito
- **XML Parsing:** DOM with XPath

---

## Setup and Running the Application

### 1. Clone the Repository

```bash
git clone https://github.com/ZeeshanM20/trade-reporting-engine-springboot
cd trade-reporting-engine-springboot
```

### 2. Build the Application
Using Maven, you can build the project by running:

```bash
mvn clean install
```

### 3. Run the Application
To start the application, run:

```bash
mvn spring-boot:run
```

### 4. Access the Application
Once the application is running, you can access the API at:

```bash
http://localhost:8080/api/events/filtered
```
This endpoint will return a list of filtered events in JSON format.

### 5. Testing the Application
Unit tests are located in the src/test/java/com/tradereport directory.
To run the tests, use:

```bash
mvn test
```

# Design and Implementation
The Trade Reporting Engine is designed to process XML trade event files, apply filtering criteria, and expose the results through REST APIs. The core components of the system are divided into XML Parsing, Business Logic, and Persistence.

## XML Parsing
Trade event data is read from XML files using the javax.xml.parsers.DocumentBuilder and javax.xml.xpath.XPath classes. 
The system extracts key fields like buyerParty, sellerParty, premiumAmount, and premiumCurrency based on the following XPath expressions:

//buyerPartyReference/@href → buyer_party
//sellerPartyReference/@href → seller_party
//paymentAmount/amount → premium_amount
//paymentAmount/currency → premium_currency

These values are then stored in an in-memory database (H2) for further processing.

## Business Logic
The filtering rules are encapsulated within the EventService class. The logic applies the following criteria:

- Seller and Currency Filter: Only include events where:
  - The sellerParty is either EMU_BANK with premiumCurrency of AUD, or BISON_BANK with premiumCurrency of USD.
- Anagram Check: Exclude events where the buyerParty and sellerParty are anagrams. This is accomplished by comparing sorted character arrays of both parties.

This structure allows for easy expansion in the future: new filters can be added without affecting existing functionality.

## Persistence Layer
Using Spring Data JPA, the application persists trade event data in the events table. The table schema consists of:

- id: Primary key for the event.
- buyer_party: The buyer's party reference.
- seller_party: The seller's party reference.
- premium_amount: The premium amount for the trade event.
- premium_currency: The currency of the premium amount.

Spring JPA handles automatic schema creation and updates when the application starts.

## Scalability and Maintainability
The design prioritizes scalability and maintainability:

- The business logic (filtering) is modular and can be extended to support new rules without significant changes to the existing code.
- The EventService class can easily accommodate additional filtering criteria, and the XML parsing and database persistence are separated for clarity and ease of modification.


# Project Structure
## Key Files
### EventController.java

- Handles REST API requests for trade events.

### EventService.java

- Implements business logic for filtering events.

### EventRepository.java

- Provides database access using Spring Data JPA.

### XmlFileProcessor.java

- Processes XML files and saves data to the database.

### XmlParser.java

- Extracts trade events from XML using DOM and XPath.

## Database

### Table: events
- Fields: id, buyerParty, sellerParty, premiumAmount, premiumCurrency

# How to Use

1. Place your XML files in com/tradereport/resources/data.

2. The application will process the files automatically and store events in the database.

3. Call the API endpoint /api/events/filtered to fetch filtered results.

## Assumptions
- The XML files are properly formatted and stored in the src/main/resources/data directory.

- The database is H2 and is configured to update the schema automatically.

- A word is an anagram of itself 


## Task Brief:

### Trade Reporting Engine

#### Description:

Create a Java program (Spring Boot) that reads a set of XML event files, extracts a set of elements (fields), stores them into the database, filters the events based on specified criteria, and reports the events in JSON format. The XML elements to be used for filtering include buyerParty, sellerParty, premiumAmount, and premiumCurrency. The system filters events where:

- The seller_party is either EMU_BANK (with AUD currency) or BISON_BANK (with USD currency).
- The buyer_party and seller_party must not be anagrams.

A new API endpoint is created to trigger the filtering process, and the system is designed with maintainability in mind, allowing for easy modification of filtering criteria in the future.