Assumptions/Decisions taken

1. Using HSQL embedded database
2. Used Spock groovy for test cases which aid more readability
3. Used Lombok for auto-generation of code and Sl4j implementation
4. The assumption is that already products exist and an offer is one-one mapping to a product


//TODO as of 30-Nov
1. add validation logic
2. Add Swagger for API spec
3. Integration test cases
4. Auditing functionality (adding modified by, modified on, created by and created on)
6. refactor by adding conditions object




---------------------------------------------------------------------------------------------------------------------------------
Assignment

You are required to create a simple RESTful software service that will allow a merchant to create a new simple offer.
Offers, once created, may be queried.
After the period of time defined on the offer it should expire and further requests to query the offer should reflect that somehow.
Before an offer has expired users may cancel it.

Guidelines

The solution should be written in Java or Scala
The merchant should be able to interact with the service over HTTP
No restrictions on external libraries
Submit as a git repository (link to GitHub, BitBucket, etc)
We are looking for a simple solution representative of an enterprise deliverable

Use TDD
Please pay attention to OO design; clean code, adherence to SOLID principles
As a simplification offers may be persisted to a file, embedded database or held in memory
You can ignore authentication and authorization concerns
Feel free to make any assumptions and document in a README markdown file, or otherwise, with the submission

