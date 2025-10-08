# Identify Service – Learning Project (Spring Boot + React)

> A practice repository to learn and implement a production‑style backend with Spring Boot and a lightweight React frontend playground.

<p align="center">
  <em>Backend</em>: Spring Boot • JWT • MySQL • Testcontainers • Spotless • JaCoCo • SonarQube • Docker • AWS EC2  \
  <em>Frontend</em>: React + Vite • Material UI • Axios Interceptors • Error Boundary
</p>

---

## 📚 Table of Contents

- [📌 Overview](#-overview)
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
- [🌐 Deployment (AWS EC2 quick notes)](#-deployment-aws-ec2-quick-notes)
- [🔗 API (sample)](#-api-sample)
- [🖥️ Frontend Notes](#️-frontend-notes)
## 📌 Overview

This project is primarily **for learning** and **hands‑on practice**. The goal is to build a realistic backend for an authentication/authorization flow and to rehearse essential frontend skills.

* Focus: Spring Boot 3 (Java 17+) with JWT‐based **AuthN/AuthZ** (login, register, logout, access/refresh token).
* Frontend: Small React app to exercise **Axios interceptor** auto refresh token, cleanup patterns with `useEffect`, and **Error Boundaries**.
* Infra/Quality: Docker, AWS EC2 deployment, **Spotless** code format, **JaCoCo** coverage, **Testcontainers** for integration tests, **SonarQube** for static analysis, and **JMeter** for basic load testing.

> ⚠️ Since this is a learning project, some trade‑offs are intentional to demonstrate patterns and alternatives.

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
---

## 🌐 Deployment (AWS EC2 quick notes)

1. Provision an Ubuntu EC2 (t3.micro is fine for demo).
2. Install Docker & Docker Compose.
3. Copy project, set `.env` (strong secrets), open security group ports **22**, **80/443** (or **8080/5173** for demo).
4. Run `docker compose -f deploy/docker-compose.yml up -d --build`.
5. For HTTPS, put Nginx/Traefik in front and set cookie `Secure=true` + `SameSite=None`.

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
