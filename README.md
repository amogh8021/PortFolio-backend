# 📧 Portfolio Contact API

A Spring Boot backend powering my chess-inspired developer portfolio.

This backend handles contact form submissions, validates incoming requests, and securely sends emails using Spring Mail. It is designed as a clean REST API that integrates seamlessly with the frontend deployed on Vercel.

---

# 🚀 Live Demo

Frontend:
https://portfolio-frontend-delta-tan.vercel.app/

Backend API:
https://portfolio-backend1-u4ay.onrender.com

---

# ✨ Features

- RESTful Contact API
- Email sending using Spring Mail
- Input validation
- Global exception handling
- Secure environment variable configuration
- CORS configuration for frontend integration
- JSON request & response
- Production deployment on Render

---

# 🛠 Tech Stack

### Backend

- Java 21
- Spring Boot
- Spring Web
- Spring Mail
- Maven

### Deployment

- Render

---

# 📂 Project Structure

```
src/
├── controller/
├── dto/
├── service/
├── exception/
├── config/
├── resources/
└── PortfolioBackendApplication.java
```

---

# 🚀 Getting Started

Follow these steps to run the project locally.

### 1. Clone the repository

```bash
git clone https://github.com/your-username/portfolio-backend.git
cd portfolio-backend
```

### 2. Configure Environment Variables

Update your `application.properties` file.

```properties
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
```

> Use a Gmail App Password instead of your account password.

### 3. Build the project

```bash
mvn clean install
```

### 4. Run the application

```bash
mvn spring-boot:run
```

The backend will be available at

```
http://localhost:8080
```

---

# 📬 API Endpoint

### POST

```
/api/contact
```

### Request Body

```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "message": "Hello! Nice portfolio."
}
```

### Success Response

```json
{
  "message": "Message sent successfully"
}
```

---

# 📈 Future Improvements

- Docker Support
- Swagger/OpenAPI Documentation
- Rate Limiting
- Request Logging
- Email Templates
- Database Integration
- Admin Dashboard
- Unit & Integration Tests

---



# 🤝 Contributing

Contributions, suggestions, and feedback are always welcome.

If you have ideas to improve this project, feel free to open an issue or submit a pull request.

---

# 📄 License

This project is licensed under the MIT License.

---

# 👨‍💻 Author

**Amogh Shrivastav**

LinkedIn:
https://www.linkedin.com/in/amogh-shrivastav-45a6b0346/

GitHub:
https://github.com/amogh8021

---

# ⭐ Support

If you found this project helpful, consider giving it a ⭐ on GitHub.

It motivates me to build more open-source projects.

---

# 💡 Final Note

Building reliable backend systems is just as important as creating beautiful user interfaces.

This project reflects my approach to designing clean, maintainable, and production-ready REST APIs using Spring Boot.

Thank you for visiting!

Made with ❤️ by **Amogh Shrivastav**
