# Identify Service – Learning Project (Spring Boot + React)

> A practice repository to learn and implement a production‑style backend with Spring Boot and a lightweight React frontend playground.

<p align="center">
  <em>Backend</em>: Spring Boot • JWT • MySQL • Testcontainers • Spotless • JaCoCo • SonarQube • Docker • AWS EC2  \
  <em>Frontend</em>: React + Vite • Material UI • Axios Interceptors • Error Boundary
</p>

---

## 📚 Table of Contents

- [📌 Overview](#-overview)
- [📌 Review](#-review)
- [✨ Features](#-features)
  - [Backend](#backend)
  - [Frontend](#frontend)
- [🧰 Tech Stack](#-tech-stack)
- [🗂️ Repository Structure](#️-repository-structure)
- [🚀 Quickstart](#-quickstart)
  - [⚙️ Option 1: Run Everything Locally](#️-option-1-run-everything-locally)
    - [🧩 Prerequisites](#-prerequisites)
    - [🧠 1. Frontend Setup](#-1-frontend-setup)
    - [🧱 2. Backend Setup](#-2-backend-setup)
  - [🐳 Option 2: Run Backend with Docker Compose (Frontend Locally)](#-option-2-run-backend-with-docker-compose-frontend-locally)
    - [🧩 Prerequisites](#-prerequisites-1)
    - [🔧 Steps](#-steps)
    - [🧩 Frontend (Run Locally)](#-frontend-run-locally)
  - [🧰 Quick Reference](#-quick-reference)
  - [🧾 Tips](#-tips)
- [🔗 API (sample)](#-api-sample)
- [🖥️ Frontend Notes](#️-frontend-notes)
## 📌 Overview


This project is primarily **for learning** and **hands‑on practice**. The goal is to build a realistic backend for an authentication/authorization flow and to rehearse essential frontend skills.

* Focus: Spring Boot 3 (Java 17+) with JWT‐based **AuthN/AuthZ** (login, register, logout, access/refresh token).
* Frontend: Small React app to exercise **Axios interceptor** auto refresh token, cleanup patterns with `useEffect`, and **Error Boundaries**.
* Infra/Quality: Docker, AWS EC2 deployment, **Spotless** code format, **JaCoCo** coverage, **Testcontainers** for integration tests, **SonarQube** for static analysis, and **JMeter** for basic load testing.

> ⚠️ Since this is a learning project, some trade‑offs are intentional to demonstrate patterns and alternatives.

---
---
## 📌 Review
> 📄 You can view the complete list of issues here: [**View on GitHub**](https://github.com/dev000000/IdentifyProject/issues)
---
| Issues                                                                    | Notes                                                                                                                                                                           |
|---------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Dev001 – Manager Exception, Normalize Response #1                         | Learned to `normalize API responses` with consistent structure and implement centralized `exception handling` for better readability and frontend parsing.                      |
| Dev001 – use MapStruct in project #3                                      | Explored `MapStruct` library for fast and type-safe object mapping between DTOs and entities, reducing boilerplate getter/setter conversions.                                   |
| Dev001 – use BCrypt #5                                                    | Implemented `password hashing` using `BCrypt` before saving to the database, improving security for authentication system.                                                      |
| Dev001 – Issue and verify JWT token #7                                    | Learned how to `generate, sign, and verify JWT tokens` to manage authentication and secure API endpoints effectively.                                                           |
| Dev001 – Spring Security Part 1 (config endpoint) #9                      | `Configured endpoints and authentication` entry points for Spring Security, setting up public/private routes and secure filters.                                                |
| Dev001 – Spring Security Part 2 (hasRole / hasAuthority) #11              | Practiced role-based authorization using `hasRole()` and `hasAuthority()` annotations in Spring Security.                                                                        |
| Dev001 – Spring Security Part 3 (Pre/Post Authorize) #13                  | Learned method-level security with `@PreAuthorize` and `@PostAuthorize` for fine-grained access control in service layers.                                                      |
| Dev001 – Handle Exception with flex http code #17                         | Enhanced exception handling to return `flexible HTTP status codes` and meaningful error messages for frontend clients.                                                          |
| Dev001 – Role-based authorization , annotation @ManyToMany #19            | Implemented `@ManyToMany` mapping between users and roles; applied `role-based authorization` for fine-grained access.                                                          |
| Dev001 – Validation with custom annotation #21                            | Created `custom validation annotations` and handled constraint messages for cleaner data validation flow.                                                                       |
| Dev001 – Feature Log out with JWT token #23                               | Implemented `logout feature` by invalidating refresh tokens and handling token blacklisting logic.                                                                              |
| Dev001 – Refresh JWT token #25                                            | Added `refresh-token endpoint` to issue new access tokens and improve user experience without frequent re-login.                                                                |
| Dev001 – Unit Test #28                                                    | Practiced writing basic `Unit Tests` for controllers, services, and repositories using `JUnit 5` following Spring Boot testing architecture.                                    |
| Dev001 – Integration Test with JaCoCo & TestContainers #31                | Integrated `TestContainers` for DB testing and `JaCoCo` for `code coverage reports` to ensure test quality and maintainability.                                                 |
| Dev001 – Format code with Spotless #33                                    | Applied `Spotless plugin` to format code automatically and maintain consistent code style across the backend project.                                                            |
| Dev001 – Learn about SonarLint and SonarQube #35                          | Explored `SonarLint` for local static analysis and `SonarQube` for continuous code quality inspection and maintainability metrics.                                              |
| Dev001 – Build FrontEnd for Application (ReactJS) #39                     | Developed a lightweight `ReactJS frontend` for Identify-Service to test authentication and token flows visually.                                                                |
| Dev001 – Restructure project: backend + frontend folders #37              | Refactored repository structure into clear `BACKEND` and `FRONTEND` modules for cleaner project organization.                                                                   |
| Dev001 – Error Boundary , FallbackUI #42                                  | Implemented React `ErrorBoundary` and `Fallback UI` components to handle rendering errors gracefully on the client side.                                                        |
| Dev001 – Complete Project (feature Login , Logout , Register) #44         | Completed core `authentication flow`: register, login, logout with JWT and refresh-token handling end-to-end.                                                                  |
| Dev001 – Fix issue Refresh token many time #46                            | Fixed `Axios interceptor logic` to prevent `multiple refresh-token calls` when several parallel API requests fail due to expired access token.                                 |
| Dev001 – use HttpOnly cookie for Refresh, Access Token #48                | Replaced localStorage token storage with `HttpOnly cookies` for better security against XSS and token theft.                                                                    |
| Dev001 – Unique Field in JPA, concurrent request #50                      | Fixed `concurrency` bug during registration by enforcing unique DB constraints and handling duplicate username exceptions gracefully.                                            |
| Dev001 – Learn about profiles and environment variables #52               | Studied `Spring Profiles`, `environment variables`, and how to configure dynamic values for multiple deployment environments.                                                   |
| Dev001 – Build Project #54                                                | Learned different Spring Boot build formats (Fat JAR, WAR, Native, Docker Image) and chose Fat `JAR` for deployment simplicity.                                                 |
| Dev001 – Using Docker #56                                                 | Created `Dockerfile`, built image, ran and published image to `Docker Hub`. Fixed build/run issues during containerization process.                                             |
| Dev001 – Deploy to EC2 (AWS) #58                                          | Learned `AWS EC2` setup (Ubuntu, Docker, Termius) and deployed Identify-Service container successfully on cloud instance.                                                       |
| Dev001 – Refactor file structure & dynamic env setup (Spring Profiles) #62| Refactored project structure, rebuilt image (v0.0.2) using `Docker Compose`, applied `.env` configuration for faster setup and deployment across profiles.                      |

---

## ✨ Features

### Backend

* ✅ User **Register / Login / Logout**
* 🔐 **JWT Authentication** (Access + Refresh) with rotation & expiry
* 🛂 **Authorization** with roles/permissions (sample endpoints)
* 🍪 Optional cookie support for refresh token (HttpOnly, SameSite, Secure)
* 🧪 Unit + Integration tests (**JUnit 5 + Testcontainers**)
* 🧹 **Spotless** formatting, **JaCoCo** coverage report, **SonarQube** quality gate

### Frontend

* ⚛️ React + Vite + **Material UI**
* 🔁 **Axios interceptors** (auto attach access token, refresh on 401, single‑flight refresh)
* 🧯 **Error Boundary** and fallback UI
* 🧼 `useEffect` cleanup, request cancellation via `AbortController`

---

## 🧰 Tech Stack

**Languages & Frameworks:** Java 17, Spring Boot 3, React, TypeScript/JavaScript, HTML, CSS, Material UI
**Build & Runtime:** Maven, Docker, Docker Compose
**Database:** MySQL (dev via Docker), H2 (tests optional)
**Testing/QA:** JUnit 5, Testcontainers, JaCoCo, SonarQube, Spotless
**Ops:** AWS EC2, JMeter (load testing)

---

## 🗂️ Repository Structure

```
IDENTIFY/
├─ BACKEND/                          # Spring Boot 3.x backend
│  ├─ src/
│  │  ├─ main/java/                  # Source code
│  │  ├─ main/resources/             # Config files (application.yml, etc.)
│  │  ├─ test/java/                  # Unit & integration tests (Testcontainers, JUnit5)
│  │  └─ test/resources/
│  ├─ target/                        # Build output (ignored by Git)
│  ├─ .env                           # Environment variables for backend
│  ├─ .env.example                   # Template for environment configuration
│  ├─ Dockerfile                     # Backend Docker build file
│  ├─ pom.xml                        # Maven dependencies & plugins
│
├─ FRONTEND/                         # React + Vite + Material UI frontend
│  ├─ public/                        # Static assets (favicon, etc.)
│  ├─ src/                           # React components, routes, hooks, etc.
│  ├─ .env                           # Environment variables for frontend
│  ├─ .env.example                   # Example config for frontend
│  ├─ index.html                     # Main HTML entry
│  ├─ package.json                   # Frontend dependencies
│  ├─ yarn.lock                      # Yarn lock file
│  
├─ deploy/                           # Deployment configs (local, EC2, etc.)
│  ├─ docker-compose.yml             # Multi-container orchestration (MySQL, backend)
│  
├─ .gitattributes                    # Git attributes
├─ .gitignore                        # Ignored files and folders

```
---

# 🚀 Quickstart

You can run this project in two ways:
1. Run everything locally (frontend + backend).
2. Run backend via Docker Compose and frontend locally (recommended – since this project focuses on backend development).

## ⚙️ Option 1: Run Everything Locally

### 🧩 Prerequisites

| Component   | Recommended Version | Notes                                      |
|-------------|---------------------|--------------------------------------------|
| JDK         | 17+                 | Required for Spring Boot backend           |
| Maven       | 3.9+                | Build backend                              |
| Node.js     | 20+                 | Run frontend                               |
| Yarn / npm  | latest              | Install frontend dependencies              |
| MySQL       | 8.0+                | You can use a Docker container like mysql:8.0.43-debian |

### 🧠 1. Frontend Setup

**Step 1.** Navigate to the frontend directory:

```bash
cd FRONTEND
```

**Step 2.** Install dependencies:

```bash
yarn install     # or npm install
```

**Step 3.** Create a `.env` file (based on `.env.example`):

```
VITE_API_ENDPOINT=http://localhost:8080
VITE_AUTH_MODE=JWT
```

⚠️ Update `VITE_API_ENDPOINT` if your backend runs on a different host or port.

**Step 4.** Start the development server:

```bash
yarn dev --host
```

✅ Frontend will be available at http://localhost:5173

### 🧱 2. Backend Setup

#### 🗄 MySQL Database

**Step 1.** Start MySQL:  
You can run it via Docker:

```bash
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:8.0.43-debian
```

or use a local MySQL installation.

**Step 2.** Connect with MySQL Workbench or CLI (localhost:3306, user root), then create the database:

```sql
CREATE DATABASE identifyDB;
```

#### 🧩 Spring Boot Application

**Step 3.** Navigate to the backend directory:

```bash
cd BACKEND
```

**Step 4.** Create a `.env` file (based on `.env.example`):

```
SPRING_PROFILES_ACTIVE=dev
SERVER_PORT=8080
DBMS_CONNECTION=jdbc:mysql://localhost:3306/identifyDB
DBMS_USERNAME=root
DBMS_PASSWORD=root
SECRET_KEY="7RNUqJlIkY3IuGxPKbC7GoGDW40BdtEviJxdjqXSCqrptnnqC98+hyRUB87BxzQG"
JWT_ACCESS_EXP_MS=900000
JWT_REFRESH_EXP_MS=1209600000
```

**Step 5.** In your `SecurityConfig.java`, make sure CORS allows your frontend origin:

```java
corsConfiguration.addAllowedOrigin("http://localhost:5173");
```

**Step 6.** Build and run the backend:

```bash
mvn clean package -DskipTests
java -jar target/*-SNAPSHOT.jar
```

✅ Backend will be available at http://localhost:8080

## 🐳 Option 2: Run Backend with Docker Compose (Frontend Locally)

### 🧩 Prerequisites

- Docker Desktop or Docker Engine ≥ v27
- Docker Compose ≥ v2.20

### 🔧 Steps

**Step 1.** Create a `.env` file inside the `BACKEND` folder (based on `.env.example`):

```
SPRING_PROFILES_ACTIVE=dev
SERVER_PORT=8080
DBMS_CONNECTION=jdbc:mysql://mysql:3306/identifyDB
DBMS_USERNAME=root
DBMS_PASSWORD=root
SECRET_KEY="7RNUqJlIkY3IuGxPKbC7GoGDW40BdtEviJxdjqXSCqrptnnqC98+hyRUB87BxzQG"
JWT_ACCESS_EXP_MS=900000
JWT_REFRESH_EXP_MS=1209600000
```

**Step 2.** Navigate to the `deploy` folder:

```bash
cd deploy
```

**Step 3.** Run Docker Compose:

```bash
docker compose up -d
```

Docker will automatically:

- Create a MySQL container (mysql:8.0.43-debian)
- Start identify-service (Spring Boot backend)
- Create a private network and volume (dev001-network, dbdata)

✅ **Once started:**

- Backend → http://localhost:8080
- MySQL → localhost:3306

### 🧩 Frontend (Run Locally)

Repeat the frontend setup steps from Option 1 (Steps 1–4).  
Ensure your `.env` points to the backend API:

```
VITE_API_ENDPOINT=http://localhost:8080
```

## 🧰 Quick Reference

| Service              | URL                  | Notes                          |
|----------------------|----------------------|--------------------------------|
| Frontend (Vite)      | http://localhost:5173 | Local dev mode                 |
| Backend (Spring Boot)| http://localhost:8080 | API server                     |
| MySQL                | localhost:3306       | Accessible via Workbench       |
| Docker Network       | dev001-network       | Shared between backend & DB    |

## 🧾 Tips

- Use `docker compose logs -f identify-service` to view backend logs.
- After updating `.env`, rebuild with `docker compose up -d --build`.
- The backend waits until MySQL passes its health check before starting.



---

## 🔗 API (sample)

```
POST   /api/v1/auth/register     # create account
POST   /api/v1/auth/login        # return access + refresh token
POST   /api/v1/auth/refresh-token      # rotate access token
POST   /api/v1/auth/logout       # revoke refresh
```

---

## 🖥️ Frontend Notes

* **Axios interceptor**: attach `Authorization: Bearer <access>`, on 410 (GONE) → queue & refresh once.
* **Error Boundary**: catch render/runtime errors → show fallback with retry.
* **Cleanup**: cancel pending requests on unmount (`AbortController`).

---
