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

Create a Java program (SpringBoot) that reads a set of XML event files, extracts a set of elements (fields), stores them into DB, filters the events based on a set of criteria, and reports the events in JSON Format.
The eventN.xml files are included in the email and instructions. When reading the event XML files, keep the Java code simple, consider using the following XML parser and Xpath reader included in the JDK: javax.xml.parsers.DocumentBuilder, javax.xml.xpath.Xpath. Once the information is read from XML. We need to store them in DB via JPA. And then we query the information based on the following criteria and return them as the HTTP response. During the design, we need to consider how to extend or add more criteria later without impacting the existing filters.


The following XML elements should be used for the filter criteria and then only these fields should be included in the response.

EMU_BANK,LEFT_BANK,100.0,AUD

Xml elements (fields) to use Format: xpath expression from event file => column header name

//buyerPartyReference/@href => buyer_party
//sellerPartyReference/@href => seller_party
//paymentAmount/amount => premium_amount
//paymentAmount/currency => premium_currency

Filter Criteria Only report events to JSON response if the following 3 criteria are true:

- (The seller_party is EMU_BANK and the premium_currency is AUD) or (the seller_party is BISON_BANK and the premium_currency is USD)
- The seller_party and buyer_party must not be anagrams Only events that match all criteria should be reported.

1. new API is needed as the triggering point.
2. When implementing this topic, we would better to consider the scalability and maintainability when the business wants to change the condition/logic of extracting events
