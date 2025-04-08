[![header.png](https://i.postimg.cc/KcDZCj2Q/header.png)](https://postimg.cc/Z00tdT0y)

# Sales Manager
Product sales management. Implements user registration and login, allowing them to make purchases. These are saved in a PDF file that the user can download.

### ☑️ Main features:
- CRUD (Create, Read, Update, Remove) functions for Users and Products.
- Sales record creation, including associated details, products, and the related user entity. Supports retrieval of global sales history as well as user-specific sales history.
- Generation of downloadable invoices as PDF files for each purchase made by the user.
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

- 📋 OpenPDF:
    - Implementation of [OpenPDF](https://github.com/LibrePDF/OpenPDF) library to export a user response in PDF format. You can generate invoices with purchase details. <br> Here's an example: <br>
    [![invoice-openpdf-ex.png](https://i.postimg.cc/ht1DC76Z/invoice-openpdf-ex.png)](https://postimg.cc/YvjB0jpQ)


### ❓ What's next:
- Polish / Update README
- Email verification
- Basic front-end implementation