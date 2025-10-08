# Identify Service – Learning Project (Spring Boot + React)

> A practice repository to learn and implement a production‑style backend with Spring Boot and a lightweight React frontend playground.

<p align="center">
  <em>Backend</em>: Spring Boot • JWT • MySQL • Testcontainers • Spotless • JaCoCo • SonarQube • Docker • AWS EC2  \
  <em>Frontend</em>: React + Vite • Material UI • Axios Interceptors • Error Boundary
</p>

---

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

## 🚀 Quick Start

You can run backend with **Docker Compose** and run frontend **locally** or run everything (backend + frontend) **locally** 

### Option A) Run with Docker Compose (recommended for first try)

1. Create an **.env** file (project root) for compose:

```env
# --- Backend ---
APP_PROFILE=dev
JWT_SECRET=change_me_super_secret
ACCESS_TOKEN_TTL_MS=900000      # 15 minutes
REFRESH_TOKEN_TTL_MS=1209600000 # 14 days
CORS_ALLOWED_ORIGINS=http://localhost:5173
DB_HOST=mysql
DB_PORT=3306
DB_NAME=identify
DB_USER=root
DB_PASSWORD=root

# --- Frontend ---
VITE_API_BASE_URL=http://localhost:8080
```

2. Use the provided Compose file:

```yaml
# deploy/docker-compose.yml
version: "3.9"
services:
  mysql:
    image: mysql:8.0.43-debian
    environment:
      MYSQL_ROOT_PASSWORD: ${DB_PASSWORD}
      MYSQL_DATABASE: ${DB_NAME}
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql
    healthcheck:
      test: ["CMD", "mysqladmin", "ping", "-h", "localhost"]
      interval: 5s
      timeout: 5s
      retries: 10

  backend:
    build:
      context: ../BACKEND
    environment:
      SPRING_PROFILES_ACTIVE: ${APP_PROFILE}
      APPLICATION_SECURITY_JWT_SECRET: ${JWT_SECRET}
      APPLICATION_SECURITY_JWT_ACCESS_EXPIRATION: ${ACCESS_TOKEN_TTL_MS}
      APPLICATION_SECURITY_JWT_REFRESH_EXPIRATION: ${REFRESH_TOKEN_TTL_MS}
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:${DB_PORT}/${DB_NAME}?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
      SPRING_DATASOURCE_USERNAME: ${DB_USER}
      SPRING_DATASOURCE_PASSWORD: ${DB_PASSWORD}
      CORS_ALLOWED_ORIGINS: ${CORS_ALLOWED_ORIGINS}
    depends_on:
      mysql:
        condition: service_healthy
    ports:
      - "8080:8080"

  frontend:
    build:
      context: ../FRONTEND
    environment:
      VITE_API_BASE_URL: ${VITE_API_BASE_URL}
    ports:
      - "5173:5173"
    command: ["npm", "run", "dev", "--", "--host"]
    depends_on:
      - backend

volumes:
  mysql_data:
```

3. Run:

```bash
cd deploy
docker compose up --build
```

* Backend available at: `http://localhost:8080`
* Frontend available at: `http://localhost:5173`

### Option B) Run locally without Docker

**Prerequisites**

* JDK 17+, Maven 3.9+
* Node 20+, pnpm/npm/yarn
* MySQL 8.x (create DB `identify`)

**1) Backend**

```bash
cd BACKEND
# optional
mvn spotless:apply
mvn -Pdev clean verify
java -jar target/*-SNAPSHOT.jar \
  --spring.profiles.active=dev \
  --spring.datasource.url=jdbc:mysql://localhost:3306/identify?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC \
  --spring.datasource.username=root \
  --spring.datasource.password=root \
  --application.security.jwt.secret=change_me_super_secret \
  --application.security.jwt.access-expiration=900000 \
  --application.security.jwt.refresh-expiration=1209600000 \
  --cors.allowed-origins=http://localhost:5173
```

**2) Frontend**

```bash
cd FRONTEND
cp .env.example .env # edit VITE_API_BASE_URL if needed
npm install
npm run dev -- --host
```

---

## ⚙️ Configuration

### Backend – `application.yml`

```yaml
spring:
  datasource:
    url: ${SPRING_DATASOURCE_URL}
    username: ${SPRING_DATASOURCE_USERNAME}
    password: ${SPRING_DATASOURCE_PASSWORD}
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate.format_sql: true

application:
  security:
    jwt:
      secret: ${APPLICATION_SECURITY_JWT_SECRET}
      access-expiration: ${APPLICATION_SECURITY_JWT_ACCESS_EXPIRATION:900000}
      refresh-expiration: ${APPLICATION_SECURITY_JWT_REFRESH_EXPIRATION:1209600000}
    cookie:
      secure: false
      same-site: Lax
cors:
  allowed-origins: ${CORS_ALLOWED_ORIGINS:http://localhost:5173}
```

> **Note**: If you store refresh token in HttpOnly cookie, ensure `cookie.secure=true` when using HTTPS and set `SameSite=None` for cross‑site.

### Frontend – `.env`

```env
VITE_API_BASE_URL=http://localhost:8080
```

Usage in code:

```ts
// FRONTEND/src/lib/http.ts
import axios from "axios";
export const http = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
  withCredentials: true, // if using cookies
});
```

---

## 🌐 Deployment (AWS EC2 quick notes)
//EDIT lai
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
