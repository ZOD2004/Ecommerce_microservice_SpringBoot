# Ecommerce_microservice_SpringBoot
A microservices-based E-Commerce backend built with Spring Boot, Spring Cloud, and Eureka Service Discovery. The system consists of multiple services — User, Product, Category, Inventory, Cart, Order, and Email — communicating via Feign Clients and routed through a Spring Cloud Gateway.
E-Commerce Microservices Project
This project is a Spring Boot + Spring Cloud based backend for an E-Commerce platform, following the microservices architecture.
It is designed for scalability, modularity, and independent service deployment, with services communicating through Feign Clients and managed via Eureka Service Discovery.

Architecture Overview
The system is composed of the following core services:

User Service – Manages customer accounts and user details.

Product Service – Handles product catalog with pagination, sorting, and filtering by category, name, and price.

Category Service – Organizes products into categories.

Inventory Service – Maintains stock levels, supports single and bulk stock checks, and reduces inventory on confirmed orders.

Cart Service – Manages shopping carts for users, including adding, updating, removing items, and clearing the cart.

Order Service – Creates orders from carts, verifies inventory availability, and triggers email confirmations.

Email Service – Sends order confirmation emails to customers using SMTP.

API Gateway – Central entry point for routing requests to microservices.

Key Features
Eureka Service Discovery for dynamic service registration and lookup.

Feign Clients for inter-service communication without hard-coded URLs.

Bulk Inventory Check & Reduction to optimize order processing.

Order Creation Flow:

Retrieve cart items for a user.

Perform bulk inventory check via Inventory Service.

If available, reduce stock and create the order.

Send email confirmation via Email Service.

RESTful APIs for each service with consistent /api routing.

Technology Stack
Java 24

Spring Boot (Web, Data JPA, Mail)

Spring Cloud (Eureka, OpenFeign, Gateway)

MySQL (per-service database)

Lombok for boilerplate reduction

This system demonstrates how multiple independent microservices can work together to create a robust, scalable E-Commerce backend.<img width="1536" height="1024" alt="bcafff12-f568-46be-989f-6e8feb0d1c08" src="https://github.com/user-attachments/assets/13a87757-42ee-4df0-a51a-c8d524d062c7" />
