# E-commerce Application

This is an e-commerce platform developed using Spring Boot, Hibernate, and PostgreSQL, with integration of PayPal payment processing. The application allows users to browse products, add items to their cart, place orders, and process payments using PayPal. This README provides instructions on how to run the application and includes feedback for the application development process using AI.

## Instructions

### Prerequisites

Before running the application, make sure you have the following installed:

- Java Development Kit 11 (JDK 11)
- PostgreSQL Database

### Setup

1. Clone the repository from GitHub: `git clone <repository_url>`
2. Create a PostgreSQL database and update the application properties in `src/main/resources/application.properties` with the database credentials.
3. Set up PayPal API credentials in `src/main/resources/application.properties` under the appropriate properties.
4. Build and run the application: `./mvnw spring-boot:run`

The application will be available at [http://localhost:8080](http://localhost:8080).

## Feedback

### 1. Was it easy to complete the task using AI?
It was mostly helpful, but I needed to provide specific instructions to get accurate code. The AI lacked some contextual understanding, so precise instructions were necessary for optimal results.

### 2. How long did the task take you to complete?
Approximately 4 hours.

### 3. Was the code ready to run after generation? What did you have to change to make it usable?
The generated code was not ready to run. Some adjustments were required, especially in the integration of components. For example, the AI generated tests for previously written classes that failed, and it attempted to create a table named 'Order,' which is a reserved keyword in PostgreSQL.

### 4. Which challenges did you face during the completion of the task?
The main challenges were ensuring that the AI-generated code met specific requirements. 

### 5. Which specific prompts did you learn as a good practice to complete the task?
Using specific examples and references, and clearly instructing the AI about the desired code structure were essential for getting accurate and usable code.
