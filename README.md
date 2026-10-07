# UPI Simulator

A backend REST API that simulates core UPI payment functionality, including user authentication, bank accounts, UPI IDs, balance management, peer-to-peer payments, transaction processing, and transaction history.

The project focuses on backend concepts such as **JWT authentication, database transactions, idempotency, concurrency control, pessimistic locking, and pagination**.

## Features

* User registration and login
* JWT-based authentication and authorization
* BCrypt password hashing
* Bank management
* Bank account registration
* Multiple UPI IDs per user
* Account balance management
* Credit and debit operations
* Peer-to-peer UPI payments
* Transaction status management
* Payment idempotency
* Concurrency-safe balance updates
* Pessimistic row locking
* Consistent account locking order to reduce deadlock risk
* Paginated transaction history
* User-specific transaction access
* Centralized exception handling
* Input validation
* Swagger/OpenAPI API documentation
* Dockerized deployment
* PostgreSQL database
* Deployed on Render

## Tech Stack

* **Language:** Java 21
* **Framework:** Spring Boot
* **Security:** Spring Security, JWT
* **ORM:** Spring Data JPA, Hibernate
* **Database:** PostgreSQL
* **Build Tool:** Maven
* **API Documentation:** Swagger / OpenAPI
* **Containerization:** Docker
* **Deployment:** Render
* **API Testing:** Postman / Swagger UI

## Architecture

The application follows a layered backend architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL
```

### Main components

* **Controllers** – Handle HTTP requests and responses.
* **Services** – Contain business logic and transaction processing.
* **Repositories** – Handle database access using Spring Data JPA.
* **Entities** – Represent users, banks, bank accounts, UPI IDs, and transactions.
* **DTOs** – Control the data exposed through API responses.
* **Security** – Handles JWT authentication and authorization.
* **Exception Handling** – Provides consistent handling of application errors.

## Payment Flow

A typical payment follows this general flow:

```text
Authenticated User
       ↓
Sender UPI ID
       ↓
Receiver UPI ID
       ↓
Validate transaction
       ↓
Lock required bank accounts
       ↓
Debit sender
       ↓
Credit receiver
       ↓
Update transaction status
       ↓
Commit transaction
```

The payment operation is executed inside a database transaction to keep the balance updates consistent.

## Concurrency Control

The payment system is designed to handle concurrent payment attempts against the same bank account.

Pessimistic row locking is used when accessing the accounts involved in a transaction.

Accounts are locked in a consistent order to reduce the possibility of deadlocks when multiple transactions attempt to access the same accounts concurrently.

This helps prevent situations such as:

* Two payments spending the same available balance
* Inconsistent account balances
* Race conditions during simultaneous transactions
* Deadlocks caused by inconsistent lock ordering

## Idempotency

Payment requests use an idempotency key to prevent accidental duplicate processing of the same payment request.

This allows the system to recognize a previously processed request instead of creating another payment transaction.

## Transaction History

Users can retrieve their transaction history through a paginated API.

The response includes pagination metadata such as:

* Current page
* Page size
* Total pages
* Total elements
* Whether the current page is the last page

This avoids loading an entire transaction history into memory at once.

## Authentication

The API uses JWT-based authentication.

The general authentication flow is:

```text
Register
   ↓
Login
   ↓
Receive JWT
   ↓
Send JWT with protected requests
   ↓
Spring Security validates token
   ↓
Authenticated user accesses protected resources
```

Protected operations use the authenticated user rather than relying on arbitrary user identity supplied by the request.

## API Documentation

Swagger/OpenAPI is included to explore and test the API.

After starting the application, Swagger UI is available at:

```text
/swagger-ui/index.html
```

The deployed API also provides Swagger UI through the Render deployment.

## Running Locally

### Prerequisites

* Java 21
* Maven
* PostgreSQL
* Git

### Clone the repository

```bash
git clone https://github.com/harshbhatt1380/upi-simulator.git
cd upi-simulator
```

### Configure the database

Create a PostgreSQL database and configure the required environment variables:

```text
DB_URL=jdbc:postgresql://localhost:5432/UpiSimulator
DB_USERNAME=your_username
DB_PASSWORD=your_password
JWT_SECRET_KEY=your_secret_key
```

### Run the application

Using Maven:

```bash
mvn spring-boot:run
```

Or build the application:

```bash
mvn clean package
```

Then run the generated JAR:

```bash
java -jar target/upisimulator-0.0.1-SNAPSHOT.jar
```

## Docker

The project includes a `Dockerfile` for containerized deployment.

Build the image:

```bash
docker build -t upi-simulator .
```

Run the container:

```bash
docker run -p 8080:8080 upi-simulator
```

Database configuration should be supplied through environment variables.

## Database

The application uses PostgreSQL with Spring Data JPA and Hibernate.

Main domain entities include:

```text
User
  │
  ├── BankAccount
  │       │
  │       └── Bank
  │
  └── UPI

Transaction
  ├── Sender UPI
  └── Receiver UPI
```

## Deployment

The application is containerized using Docker and deployed on **Render** with a PostgreSQL database.

The deployment uses environment variables for database credentials and the JWT secret rather than hardcoding sensitive configuration into the source code.

## What I Learned

This project helped me work with several backend concepts beyond basic CRUD APIs:

* Designing REST APIs with Spring Boot
* Authentication and authorization with Spring Security
* JWT-based security
* Password hashing with BCrypt
* Entity relationships with JPA/Hibernate
* Database transactions
* Transaction state management
* Idempotent API operations
* Pagination with Spring Data
* Concurrency and race conditions
* Pessimistic database locking
* Deadlock prevention through consistent lock ordering
* PostgreSQL
* Docker-based deployment
* Swagger/OpenAPI documentation
* Deploying a Spring Boot application with a managed PostgreSQL database

## Repository

GitHub:

https://github.com/harshbhatt1380/upi-simulator
