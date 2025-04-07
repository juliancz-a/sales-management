[![header.png](https://i.postimg.cc/KcDZCj2Q/header.png)](https://postimg.cc/Z00tdT0y)

# Sales Manager
Product sales management. Implements user registration and login, allowing them to make purchases.

### ☑️ Main features:
- CRUD (Create, Read, Update, Remove) functions for Users and Products.
- Invoice generation with the user and their purchased products.
- Authentication and authorization of users with different roles.
- Data persistance.

### ⚙️ Main technologies:

- 🍃 Spring Boot: 
    - Spring Web, DI (Dependency Injection), Exception Handler, validations, DTOs, Mappers, Services, Rest Controllers.

- 🖧 Spring Data JPA - Hibernate - MySQL:
    - Hibernate implementation, MySQL for persistence, repositories with custom queries, Jakarta Validation for entities.

- 🛡️ Spring Security & JWT:
    - [jjwt](https://github.com/jwtk/jjwt) library for creating and veryfing JSON Web Tokens,
    Authentication and JWT Validation filters, endpoints configuration.

### ❓ What's next:
- Polish/README
- PDF Invoice generation
- Basic front-end implementation