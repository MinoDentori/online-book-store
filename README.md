# Bookstore Pro
![Alt text](https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.istockphoto.com%2Fphoto%2Fbooks-on-display-in-the-corner-of-a-second-hand-bookstore-gm1129874863-298635408&psig=AOvVaw0Tp8xTO4IOApfkjtIwjJhw&ust=1704989607760000&source=images&cd=vfe&ved=0CBIQjRxqFwoTCLiG8rCb04MDFQAAAAAdAAAAABAE)

## Introduction

Bookstore Pro – your go-to spot for hassle-free book exploration, easy browsing, 
and quick purchases, all from the comfort of your device. 
We've built this platform to tackle some common book-related issues:

Efficient Book Search: Find your favorite books in a snap by searching titles and authors.
Smooth Book Buying: Bookstore Pro lets you grab your must-reads with a straightforward shopping cart.

## Technologies Used

Data Handling: Spring Data JPA, Hibernate, and MySQL.
Web Crafting: Spring MVC, Servlets, JSP, and Tomcat are in action for a solid web setup.
Project Setup: We've made life easy with Spring Boot, Spring, and Maven.
Testing and Docs: JUnit, Mockito, Swagger, and Test Containers ensure everything is on point.
Coding Language: Java is our superhero.
Control: Keeping it all in check with Git.
Dev Tools: Navigating the coding seas with a reliable IDE.
Data Formats: We're fluent in both XML and JSON.
Security Guard: Sleep easy knowing Spring Security has our back.

## Functionalities
✏️ User Registration: Create a new user account to access the platform.
🔑 Log In / Out: Sign in to or sign out of your user account.
📚 Book Operations (CRUD): Manage books with Create, Read, Update, and Delete operations.
🔎 Search Books by Parameters: Explore books by searching based on specific criteria.
💵 Order Books: Create orders  for your selected books.


## Endpoints

### User

+ _`POST: /api/auth/registration` - endpoint for user registration (No authorities)_
+ _`POST: /api/auth/login` - endpoint for user login (No authorities)_

### Books

+ _`GET: /api/books` - endpoint to look at all the books (User authority)_
+ _`GET: /api/books/{id}` - endpoint for searching a specific book (User authority)_
+ _`POST: /api/books` - endpoint for creating a new book (Admin authority)_
+ _`PUT: /api/books/{id}` - endpoint for updating information about book (Admin authority)_
+ _`DELETE: /api/books/{id}` - endpoint for deleting books (Soft delete is used, Admin authority)_
+ _`GET: /api/books/search` - endpoint for searching books by parameters (User authority)_

### Categories

+ _`GET: /api/categories` - endpoint to look at all categories (User authority)_
+ _`GET: /api/categories/{id}` - endpoint for searching a specific category (User authority)_
+ _`POST: /api/categories` - endpoint for creating a new category (Admin authority) (User authority)_
+ _`PUT: /api/categories/{id}` - endpoint for updating information about specific category (Admin authority)_
+ _`DELETE: /api/categories/{id}` - endpoint for deleting categories (Soft delete is used, Admin authority)_
+ _`GET: /api/categories/{id}/books` - endpoint to look at books with specific category (User authority)_

### Orders

+ _`GET: /api/orders` - endpoint for getting orders history (User authority)_
+ _`POST: /api/orders` - endpoint for placing orders (User authority)_
+ _`PATCH: /api/orders/{id}` - endpoint for updating orders status (Admin authority)_
+ _`GET: /api/orders/{orderId}/items` - endpoint for getting order items from specific order (User authority)_
+ _`GET: /api/orders/{orderId}/items/{itemId}` - endpoint for getting specific item from certain order (User authority)_

### Cart

+ _`GET: /api/cart` - endpoint for getting all items in your shopping cart (User authority)_
+ _`POST: /api/cart` - endpoint for adding items in your shopping cart (User authority)_
+ _`PUT: /api/cart/cart-items/{itemId}` - endpoint for updating items quantity (User authority)_
+ _`DELETE: /api/cart/cart-items/{itemId}` - endpoint for deleting items from your shopping cart (User authority)_


## Setup

To set up Bookstore Pro and start exploring the literary world, follow these simple steps:

Clone the repository to your local machine.
Ensure you have Java, Maven, and your preferred IDE installed.
Configure the database settings in the application.properties file.
Run the application using your IDE or the command line.
Access the platform through your web browser.

## Challenges and Solutions

The journey of creating Bookstore Pro wasn't without its challenges:

Docker and Test Issues: 
    Overcame Docker configuration challenges by restructuring the setup. 
    Resolved test issues by revising the JwtAuthenticationFilter logic for successful test executions.
Liquibase and Security Challenges: 
    Addressed Liquibase sequencing problems for a smooth startup. 
    Tackled security challenges by adjusting configurations to specify accessible endpoints for unregistered users.

## Postman Collection (Optional)




