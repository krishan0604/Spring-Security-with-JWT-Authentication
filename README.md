🔐 Spring Security with JWT Authentication

This project demonstrates JWT (JSON Web Token) based Authentication & Authorization in a Spring Boot application using Spring Security.

🚀 Features

User login with username & password → generates JWT Token

Authenticate API requests using JWT in Authorization Header

Role-based access control (USER, ADMIN)

Token validation & expiration handling

Secure password storage using BCrypt

REST APIs protected with JWT

🔧 Tech Stack

Backend: Java, Spring Boot, Spring Security

Authentication: JWT (JSON Web Token)

Database: MySQL (or H2 for testing)

Tools: Postman (API Testing), Maven

📂 Project Structure

Controller Layer: Handles user login & secured APIs

Service Layer: Business logic for user authentication

Repository Layer: Database operations (User & Roles)

Model Layer: User, Role, and JWT Request/Response DTOs

Configuration Layer: Spring Security + JWT configuration (filters, authentication manager)
