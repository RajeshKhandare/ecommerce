# E-commerce REST API

This is a Spring Boot-based RESTful API for an e-commerce system. It supports operations like:

- ✅ User registration and login (with JWT token authentication)
- 📦 CRUD operations for Categories and Items
- 🛒 Placing and managing Orders

## 🔧 Technologies Used

- Java 17
- Spring Boot 3
- Spring Security (JWT)
- Spring Data JPA
- MySQL
- Lombok
- Swagger (OpenAPI)

## 🧪 Sample Endpoints

### 🔐 Auth
- `POST /api/auth/register` — Register user
- `POST /api/auth/login` — Get JWT token

### 📦 Items
- `POST /api/items` — Add item (requires JWT)
- `GET /api/items` — Get all items

### 🛒 Orders
- `POST /api/orders` — Place order (requires JWT)

## 📂 Folder Structure

- `src/main/java/com/demo` — Main code
- `src/main/resources` — Configs like `application.properties`

## 🚀 Getting Started

1. Clone the repo
2. Configure DB in `application.properties`
3. Run the project
4. Use Postman to test with JWT

---

