# 🏢 Employee Management System

A robust backend API for managing employees, departments, and leave requests with enterprise-grade security.

## ✨ Features

- **Employee Management** - CRUD operations for employees and departments
- **Leave Request System** - Submit, approve, and track leave requests
- **JWT Authentication** - Secure token-based authentication
- **Email Verification** - Automated email confirmation for new accounts
- **Password Recovery** - Forgot & reset password functionality
- **Role-Based Access** - Authorization with Spring Security
- **Pagination** - Efficient data retrieval for large datasets

## 🛠️ Tech Stack

- **Backend:** Spring Boot
- **Database:** MySQL
- **Security:** Spring Security + JWT
- **Email:** Spring Mail
- **Architecture:** RESTful API

## 📊 Database Schema

The system includes 4 main entities:
- `employee` - Employee profiles and details
- `user_account` - Authentication credentials
- `leave_request` - Leave management
- `department` - Department organization

## 🚀 Getting Started

1. Clone the repository
```bash
git clone https://github.com/fatimaelhajoui/employee-management-system.git
```

2. Configure MySQL database in `application.properties`
```properties
spring.application.name=employee-managment
spring.datasource.url=jdbc:mysql://localhost:3306/employee_api?createDatabaseIfNotExist=true
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. Configure email settings for verification
```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=(your email adresse)
spring.mail.password=(your email password)
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

4. Run the application
```bash
mvn spring-boot:run
```

## 🔐 API Endpoints

### Authentication
- `POST /auth/sign-up` - Register new user
- `POST /auth/login` - User login
- `POST /auth/forgot-password/{username}` - Request password reset
- `POST /auth/reset-password` - Reset password

### Employees
- `GET /employees` - Get all employees (paginated)
- `GET /employees/{employeeid}` - Get employee by ID
- `POST /employees` - Create employee
- `PUT /employees/{employeeid}` - Update employee
- `DELETE /employees/{employeeid}` - Delete employee

### Leave Requests
- `POST /employees/{employeedId}/leave-request` - Create request
- `GET /employees/{employeeid}/leave-request-by-employee` -  Get request by ID

### Department
- `GET /departments/` - Get all departments 
- `GET /departments/{departmentId}` - Get department by ID
- `POST /departments/` - Create department
- `DELETE /departments/{departmentId}` - Delete department

## 📧 Email Features

- Welcome email with credentials
- Email verification links
- Password reset tokens
- Leave request notifications

## 🔒 Security

- Password encryption with BCrypt
- JWT token-based authentication
- Role-based authorization (Admin, Manager, Employee)
- Secure password reset flow

## 📝 License

MIT License

## 👤 Author

Fatima Zahrae EL HAJOUI - [GitHub](https://github.com/fatimaelhajoui) | [LinkedIn](https://linkedin.com/in/fatima-zahrae-el-hajoui)

---

⭐ Star this repo if you find it helpful!
