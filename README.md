# Employee Management System

A simple employee management system built using **Java**, **Spring Boot**, **Thymeleaf**, **Spring Security**, and **MySQL**. This project is still in development.

## ✨ Features Implemented
- ✅ Employee CRUD (Create, Read, Update, Delete)
- ✅ Search functionality
- ✅ Authentication system
- ✅ Role-based access control (Admin & User)

## 🚧 Features to be Implemented
- [ ] Department management
- [ ] Salary management
- [ ] Attendance tracking
- [ ] Leave management
- [ ] Interns module

## 🛠️ Technologies Used
- Java
- Spring Boot
- Spring Security
- Spring MVC
- Thymeleaf
- MySQL

## 🧑‍💻 Roles
- **Admin**: Full access to manage employees
- **User**: View-only access

## 📸 Screenshots
### 1. Login page
<img width="1916" height="897" alt="image" src="https://github.com/user-attachments/assets/f4fe1c51-e2aa-4514-b43c-ce3a5b3b2589" />

### 2. Add Form
<img width="1447" height="911" alt="image" src="https://github.com/user-attachments/assets/6f137bde-7911-4c3d-b2ce-b0822febc956" />

### 3. List page
<img width="1477" height="846" alt="image" src="https://github.com/user-attachments/assets/70711ce5-8e02-451d-b576-f1c06ea583b2" />

### 4. Search bar
<img width="1465" height="683" alt="image" src="https://github.com/user-attachments/assets/6501fea8-8e54-4d34-9c01-38482e1b41ad" />

### 5. Edit page
<img width="1413" height="902" alt="image" src="https://github.com/user-attachments/assets/de4c1819-2131-4784-9603-d9c5b6aac738" />

### 6. Delete operation
<img width="1485" height="837" alt="image" src="https://github.com/user-attachments/assets/94b493d2-06c9-41fc-8ec8-f2566cafde4f" />


## 🗂️ Setup Instructions

### 1. Clone the repository

```bash
git clone https://github.com/yourusername/employee-management-system.git
cd employee-management-system
```
### 1. Configure the database

```bash
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
