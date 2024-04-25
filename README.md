# QikServe Java Project

Welcome to the QikServe Java project! This repository contains an implementation of a Java application developed to respond to a QikServe test. This document provides information about the project, including the technologies used, the proposed objective, information about the API, and how to perform the installation. All the answers to this test will be in the end of the README.md.

## Technologies Used

- **Java 22**: The project is developed using Java 22.
- **Maven**: Maven is used as a dependency manager and to build the project.
- **GitHub Actions**: GitHub Actions is used for the CI/CD pipeline automation.
- **JUnit and Mockito**: These are used for unit testing in the project.
- **Wiremock**: Wiremock is used to simulate APIs for testing the project.

## Proposed Objective

The QikServe Java project was developed to demonstrate proficiency in Java, REST APIs, and unit testing. The goal is to develop an application that consumes a Wiremock API, rendering usable content and handling promotions as requested.

## Information about the API

- **Wiremock API**: The application consumes a Wiremock API that provides information about products, promotions, and the shopping cart.
- **API Endpoints**:
    - `/products`: Returns a list of available products.
    - `/products/{productId}`: Returns details of a specific product.
    - `/addItem`: Adds an item to the shopping cart.
    - `/details`: Returns details of the shopping cart, including items and total savings.

## Wiremock Service for POST Requests

The project includes a Wiremock service configured to handle POST requests for adding items to the shopping cart. This service simulates the API's behavior for the POST request and allows for testing the application without needing a real API endpoint.

- **URL**: [https://251k8.wiremockapi.cloud](https://251k8.wiremockapi.cloud)
- **Endpoint**: `/addItem`
    - **Request Body**: JSON object with the following fields:
       ```json
            {
                "productId": "PWWe3w1SDU",
                "quantity": 5
            }
            
    - **Response**:
        - A JSON object indicating the success of the operation, including the `productId` and `quantity` of the added item.
        - Example response:
            ```json
            {
                "message": "Item added to cart successfully",
                "productId": "PWWe3w1SDU",
                "quantity": 5
            }
            ```

Response will show the `productId` and `quantity` only with the project running(`message` is default).
The Wiremock service allows the project to simulate adding items to the shopping cart and handles the expected request and response for the `/addItem` endpoint.

## How to Install

1. **Clone the repository**:
    - Clone this repository to your local machine:
        ```shell
        git clone <repository-url>
        ```

2. **Set up the development environment**:
    - Ensure that Java 22 and Maven are installed on your machine.
    - Make sure Java 22 is set as the default Java version.

3. **Build the project**:
    - Navigate to the project directory and build it using Maven:
        ```shell
        cd <project-directory-name>
        mvn clean install
        ```

4. **Run the tests**:
    - To run the unit tests, use the following command:
        ```shell
        mvn test
        ```

5. **Start the application**:
    - To start the application, run the following command:
        ```shell
        mvn spring-boot:run
        ```

6. **Monitor the CI/CD pipeline execution**:
    - The GitHub Actions YAML file (`.github/workflows`) is set to automatically trigger a CI/CD pipeline on push or pull request events.
    - Check the "Actions" tab in your GitHub repository to monitor the pipeline execution.

## Follow Up Questions

1. **How long did you spend on the test? What would you add if you had more time?**

    I spent Saturday and Sunday mostly, with some additional adjustments implemented on Monday and Tuesday. I didn't have much time to further improve the project beyond that. If I had more time, I would implement a series of improvements, but the main one would be to properly use the methods for calculating savings and total price, eliminating the need for a new Wiremock service.

2. **What was the most useful feature that was added to the latest version of your chosen language? Please include a snippet of code that shows how you've used it.**

    In the project, one of the most useful features from the recent versions of Java is the `var` keyword for local variable type inference. This allows the code to be more concise and expressive. This feature simplifies code by allowing the compiler to infer the data type of a variable based on its initialization. As a result, it reduces boilerplate code and improves readability by eliminating the need to specify data types explicitly. Here's a snippet of code that shows its use:

    ```java
    var product = new Product("productId1", "Product 1", 1000, new ArrayList<>());
    ```

3. **What did you find most difficult?**

    The most difficult part was the beginning, due to having many ideas and finding it a bit challenging to organize everything in my head. However, in the end, everything worked out well.

4. **What mechanism did you put in place to track down issues in production on this code?**

    I used JUnit and Mockito for unit tests and also set up a CI pipeline. Every time there is a commit to the `master` branch, the automated test suite runs. This helps ensure the code is functioning as expected and can catch any issues early.

5. **The Wiremock represents one source of information. We should be prepared to integrate with more sources. List the steps that we would need to take to add more sources of items with different formats and promotions.**

    To integrate with additional sources of items with different formats and promotions, I would first ensure that the data model is flexible enough to accommodate multiple data types. This may involve creating interfaces or adapters for different data formats. Additionally, updating the business logic to incorporate new sources and promotions will be important. Thorough testing and validation would be essential to ensure data consistency and performance. Overall, the focus would be on creating a scalable and adaptable system that can easily integrate new sources as needed.

## Contact

If you have any questions or feedback about the project, contact me.

Thank you for reading this document. Enjoy exploring the QikServe Java project!
