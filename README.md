# Hospital Management System API 🏥

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)](https://spring.io/projects/spring-boot)

A RESTful API for managing hospital operations, built with **Spring Boot**. Streamline doctor schedules, patient records, room reservations, and department management with this modular backend system.

---

## 📌 Features

### **Core Functionality**
- **Doctor Management**: Add, update, and track doctor availability.
- **Patient Records**: Securely manage patient medical files.
- **Room Reservations**: Book, update, and query room statuses.
- **Department Operations**: Manage hospital departments (e.g., Cardiology, Pediatrics).
- **Patient-Room Assignments**: Track patient admissions and room assignments.

### **Key Endpoints**
| Category          | Endpoints                                                                                   |
|--------------------|--------------------------------------------------------------------------------------------|
| **Doctors**        | `GET /doctors/all`, `POST /doctors/doctor/create`, `GET /doctors/doctor/available`          |
| **Patients**       | `GET /patient-files/all`, `POST /patient-files/patient-file/create`                        |
| **Rooms**          | `GET /rooms/room/available`, `PUT /rooms/room/update/{id}`, `DELETE /rooms/room/delete/{id}`|
| **Reservations**   | `POST /reservations/reservation/create`, `GET /reservations/reservation/by-date`            |
| **Departments**    | `POST /departments/department/add`, `DELETE /departments/department/delete/{id}`            |

---

## 🛠️ Technologies Used
- **Backend**: Spring Boot 3.x
- **Database**: H2 (in-memory) / MySQL (production-ready)
- **ORM**: Spring Data JPA, Hibernate
- **API Docs**: Swagger/OpenAPI 3.0
- **Build Tool**: Maven
- **Testing**: JUnit 5, Mockito

---

## 🚀 Getting Started

### **Prerequisites**
- Java 17+
- Maven 3.8+
- MySQL 8.x (optional)

### **Installation**
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/hospital-management-api.git
   cd hospital-management-api
