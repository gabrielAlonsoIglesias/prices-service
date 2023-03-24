## transaction-service

Service in charged of

## Tech Stack

* Java 11
* OpenApi
* Spring Boot 2
* Spring Boot Data JPA
* Spring Boot Validation
* Spring Boot Web
* H2
* Cucumber
* MapStruct

## Documentation

* Built: API-First approach with hexagonal architecture
* Testing: Unit testing, Integrating testing and End to End with Cucumber / Gherkin

## API Reference

* End points published with OpenApi
* For further details, check `openapi.yml`

#### Get prices

```http
  GET /prices
```

| Parameter    | Type       | Description                                |
|:-------------|:-----------|:-------------------------------------------|
| `price_date` | `string`   | **Required**. The application date         |
| `product_id` | `integer`  | **Required**. The product id               |
| `brand_id`   | `integer`  | **Required**. The brand id of the product  |

Returns the price to be applied in the given date for the incoming product and brand.

## Installation

Invoke the build at the root of the project:

```bash
  .\mvnw -f ./code/pom.xml clean compile
```

You can use the parameter -f (or --file) and specify the path to your pom file, e.g. mvn -f /path/to/pom.xml
This runs maven "as if" it were in /path/to for the working directory.

## Run Locally

Execute the `main` method in the `com.inditex.prices.Application` class from your IDE.

## Running Tests

To run unit, integration and acceptance tests, run the following command:

```bash
  .\mvnw -f ./code/pom.xml clean verify
```
