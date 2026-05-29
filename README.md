# Ayushman HMS Backend

Backend API for Ayushman HMS – a full-stack Hospital Management System built using Spring Boot, Spring Security, JWT Authentication, PostgreSQL, and Email Services.

## Live API

Backend URL:

https://hospital-management-backend-u64d.onrender.com

---

## Features

### Authentication & Authorization

* JWT Authentication
* Role-Based Access Control (RBAC)
* Admin, Doctor and Receptionist Roles
* BCrypt Password Encryption

### Patient Management

* Add Patient
* Update Patient
* Delete Patient
* View Patient Details

### Doctor Management

* Add Doctor
* Update Doctor
* Delete Doctor
* View Doctor Details

### Appointment Management

* Create Appointments
* Appointment Confirmation Emails

### Billing Management

* Generate Bills
* PDF Bill Download using iText PDF

### Inventory Management

* Add Medicines
* Delete Medicines
* Low Inventory Email Alerts

### Room Management

* Add Rooms
* Delete Rooms
* Room Availability Tracking

### Dashboard

* Total Patients
* Total Doctors
* Total Appointments
* Revenue Statistics
* Inventory Statistics

### Reports

* Excel Export using Apache POI

---

## Tech Stack

### Backend

* Java 17
* Spring Boot 3
* Spring Security
* Spring Data JPA
* JWT
* Maven

### Database

* PostgreSQL (Neon)

### Email Service

* Gmail SMTP

### Reporting

* Apache POI (Excel Export)
* iText PDF

### Deployment

* Render

---

## API Modules

### Authentication

```http
POST /api/auth/login
POST /api/auth/forgot-password
POST /api/auth/reset-password
```

### Patients

```http
GET    /api/patients
POST   /api/patients
PUT    /api/patients/{id}
DELETE /api/patients/{id}
```

### Doctors

```http
GET    /api/doctors
POST   /api/doctors
PUT    /api/doctors/{id}
DELETE /api/doctors/{id}
```

### Appointments

```http
GET    /api/appointments
POST   /api/appointments
DELETE /api/appointments/{id}
```

### Billing

```http
GET    /api/billing
POST   /api/billing
DELETE /api/billing/{id}
GET    /api/billing/{id}/pdf
```

### Inventory

```http
GET    /api/inventory
POST   /api/inventory
DELETE /api/inventory/{id}
```

### Dashboard

```http
GET /api/dashboard/stats
```

### Export

```http
GET /api/export/patients
```

---

## Demo Credentials

### Admin

Email:
[admin@hospital.com](mailto:admin@hospital.com)

Password:
admin123

### Doctor

Email:
[doctor@hospital.com](mailto:doctor@hospital.com)

Password:
doctor123

### Receptionist

Email:
[reception@hospital.com](mailto:reception@hospital.com)

Password:
reception123

---

## Database Schema

Main Entities:

* User
* Patient
* Doctor
* Appointment
* Billing
* Inventory
* Room

---

## Author

Ronit Bawankule

B.Tech Computer Science & Engineering

Full Stack Developer
