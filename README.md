# Library Management System

This project provides RESTful APIs for a library management system. The project is developed using Java and Spring Boot.

## Installation

1. Clone the project: `git clone https://github.com/yourusername/library-management-system.git`
2. Compile the project with Maven: `mvn clean install`
3. Start the application: `java -jar target/library-management-system-0.0.1-SNAPSHOT.jar`

## API Endpoints

### Book Endpoints

- `GET /v1/books`: Lists all books.
- `GET /v1/books/{id}`: Retrieves the details of a specific book.
- `POST /v1/books`: Adds a new book.
- `PUT /v1/books/{id}`: Updates a specific book.
- `DELETE /v1/books/{id}`: Deletes a specific book.

### Author Endpoints

- `GET /v1/authors`: Lists all authors.
- `GET /v1/authors/{id}`: Retrieves the details of a specific author.
- `POST /v1/authors`: Adds a new author.
- `PUT /v1/authors/{id}`: Updates a specific author.
- `DELETE /v1/authors/{id}`: Deletes a specific author.

### Category Endpoints

- `GET /v1/categories`: Lists all categories.
- `GET /v1/categories/{id}`: Retrieves the details of a specific category.
- `POST /v1/categories`: Adds a new category.
- `PUT /v1/categories/{id}`: Updates a specific category.
- `DELETE /v1/categories/{id}`: Deletes a specific category.

### Publisher Endpoints

- `GET /v1/publishers`: Lists all publishers.
- `GET /v1/publishers/{id}`: Retrieves the details of a specific publisher.
- `POST /v1/publishers`: Adds a new publisher.
- `PUT /v1/publishers/{id}`: Updates a specific publisher.
- `DELETE /v1/publishers/{id}`: Deletes a specific publisher.

### BookBorrowing Endpoints

- `GET /v1/bookborrowings`: Lists all borrowed books.
- `GET /v1/bookborrowings/{id}`: Retrieves the details of a specific borrowed book.
- `POST /v1/bookborrowings`: Adds a new borrowed book.
- `PUT /v1/bookborrowings/{id}`: Updates a specific borrowed book.
- `DELETE /v1/bookborrowings/{id}`: Deletes a specific borrowed book.

## License

This project is licensed under the [MIT license](LICENSE).
