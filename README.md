```markdown
# Retailer Discount Service

This service calculates the net payable amount for a retail website based on specific discount rules. The service is implemented using Java with the Spring Boot framework.

## Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Project Structure](#project-structure)
- [Testing](#testing)
- [Test Cases Coverage](#test-cases-coverage)
- [Contributing](#contributing)
- [License](#license)

## Features

- Calculates net payable amount based on user type, years of customer loyalty, and bill amount.
- Applies different percentage-based discounts for employees, affiliates, and long-term customers.
- Considers a fixed discount for every $100 on the bill.
- Handles the case where percentage-based discounts do not apply to groceries.
- Ensures a user can receive only one percentage-based discount on a bill.

## Requirements

- Java 8 or higher
- Maven
- Spring Boot

## Installation

Clone the repository:

```bash
git clone https://github.com/zubairmujeeb/retailer-store-service.git
```

Build the project using Maven:

```bash
cd retailer-discount-service
mvn clean install
```

## Usage

Run the application:

```bash
mvn spring-boot:run
```

The service will be accessible at [http://localhost:8080](http://localhost:8080).

## Endpoints

### Calculate Net Payable Amount

**Endpoint:**

```
POST /api/discount/calculate
```

**Request Body:**

```json
{
  "user": {
    "employee": true,
    "affiliate": false,
    "yearsOfCustomer": 2,
    "groceries": false
  },
  "bill": {
    "amount": 150,
    "containsGroceries": false
  }
}
```

**Response:**

```json
{
  "netPayableAmount": 150.0
}
```

## Project Structure

The project structure follows a typical Spring Boot application layout:

```plaintext
retailer-discount-service/
|-- src/
|   |-- main/
|       |-- java/
|           |-- com.kadipay.retailerdiscountservice/
|               |-- controller/
|               |   |-- DiscountController.java
|               |-- dto/
|               |   |-- BillDTO.java
|               |   |-- UserDTO.java
|               |-- service/
|               |   |-- DiscountService.java
|               |   |-- impl/
|               |       |-- DiscountServiceImpl.java
|               |-- transformer/
|                   |-- Transformer.java
|                   |-- UserTransformer.java
|                   |-- BillTransformer.java
|-- test/
    |-- java/
        |-- com.kadipay.retailerdiscountservice/
            |-- controller/
            |   |-- DiscountControllerIntegrationTest.java
            |-- service/
                |-- impl/
                    |-- DiscountServiceImplTest.java
```

## Testing

The testing of the service is extensively covered using JUnit and Mockito. Test cases are available for various scenarios, including different user types, years of customer loyalty, bill amounts, and the inclusion of groceries.

To run the tests, use the following Maven command:

```bash
mvn test
```

## Test Cases Coverage

- [x] Employee without groceries
- [x] Affiliate with groceries
- [x] Customer over two years with groceries
- [x] Regular customer with amount discount
- [x] Employee with amount discount
- [x] Affiliate over two years with amount discount
- [x] Regular customer with groceries
- [x] Customer over two years without groceries
- [x] Regular customer with large amount

## Contributing

Feel free to contribute by opening issues, providing feedback, or submitting pull requests.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
```