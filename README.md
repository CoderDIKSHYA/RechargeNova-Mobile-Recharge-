# RechargeNova(-Mobile-Recharge-Microservices System)
A scalable microservices-based telecom recharge system built using Spring Boot, featuring JWT-based authentication, API Gateway routing, service discovery with Eureka, and asynchronous communication using RabbitMQ.

Here is your **properly formatted GitHub README (clean, professional, copy-paste ready)** — exactly like your friend’s but even better.
```

---

## 📌 Project Overview

| Field | Details |
|------|--------|
| Domain | Telecom / FinTech (Recharge System) |
| Team | Team 9 — 4 members |
| Stack | Java 17 · Spring Boot 3.x · Spring Cloud · MySQL · JWT · RabbitMQ |
| Architecture | Microservices (5 services + Gateway + Eureka + Config Server) |
| Focus | Backend REST APIs + Async Messaging |

---

## 🏗️ Architecture

```
Client (Postman / Frontend)
        │
        ▼
┌─────────────────────────────┐
│       API Gateway           │
│   JWT Filter · Routing      │
└─────────────┬───────────────┘
              │
              ▼
┌─────────────────────────────┐
│       Eureka Server         │
│    Service Registry         │
└─────────────┬───────────────┘
              │
   ┌──────────┬──────────┬──────────┬──────────┬──────────┐
   ▼          ▼          ▼          ▼          ▼

 User      Operator    Recharge    Payment    Notification
 :8081      :8082       :8083       :8084       :8085

  │           │           │           │           │
user_db   operator_db recharge_db payment_db notification_db
```
---

## 🧩 Microservices Breakdown

### 1. User Service — :8081
Handles authentication and user management.

| Method | Endpoint | Description |
|--------|----------|------------|
| POST | /users/register | Register user |
| POST | /users/login | Login & JWT |
| GET | /users/{id} | Get user profile |

**Key components:** User, JwtUtil, SecurityConfig  

---

### 2. Operator Service — :8082
Manages telecom operators and recharge plans.

| Method | Endpoint | Description |
|--------|----------|------------|
| GET | /operators | List operators |
| GET | /operators/{id} | Operator details |
| GET | /plans/{id} | Plan details |

**Key components:** Operator, Plan  

---

### 3. Recharge Service (Core Service) — :8083 ⭐
Handles recharge workflow and orchestration.

| Method | Endpoint | Description |
|--------|----------|------------|
| POST | /recharges | Create recharge |
| GET | /recharges/{id} | Recharge details |
| GET | /recharges/user/{userId} | User recharge history |

**Key components:** Recharge, RechargeStatus  

👉 This is the main orchestration service  

---

### 4. Payment Service — :8084
Processes transactions.

| Method | Endpoint | Description |
|--------|----------|------------|
| POST | /api/payments | Process payment |
| GET | /api/payments/{id} | Transaction details |
| GET | /api/payments/user/{userId} | User payments |
| GET | /api/payments/recharge/{id} | Recharge payment |

**Key components:** Transaction  

---

### 5. Notification Service — :8085
Handles asynchronous notifications.

| Method | Endpoint | Description |
|--------|----------|------------|
| POST | /api/notifications | Send notification |
| GET | /api/notifications/{id} | Get notification |
| GET | /api/notifications/user/{userId} | User notifications |

**Key components:** Notification  

---

## 🗄️ Database Design

Each service has its own isolated database:

| Service | Database |
|--------|---------|
| User | user_db |
| Operator | operator_db |
| Recharge | recharge_db |
| Payment | payment_db |
| Notification | notification_db |

### Core Entities
- users  
- operators  
- plans  
- recharges  
- transactions  
- notifications  

---

## 🔗 Entity Relationships (IMPORTANT RULE)

✔ Only **Operator ↔ Plan** uses JPA relationship  
❌ All others use **ID-based references only**


---

## 🔄 System Workflow

### 🚀 Complete Flow

**Step 1: Authentication**  
User logs in → receives JWT  

**Step 2: Fetch Plans**  
User selects operator + plan  

**Step 3: Create Recharge**  
Recharge created with status = **PENDING**  

**Step 4: Validation (Feign Calls)**  
- Validate user  
- Validate operator & plan  

**Step 5: Payment Processing**  
Recharge → Payment Service  

**Step 6: Transaction Created**  
Payment status = SUCCESS / FAILED  

**Step 7: Event Publishing (RabbitMQ)**  
Payment → Notification  

**Step 8: Notification Sent**  
SMS/Email triggered  

**Step 9: Update Recharge**  
Status updated → SUCCESS / FAILED  

---

## 🔄 Recharge Status Lifecycle

```

PENDING
→ SUCCESS
→ FAILED

```

---

## 🔐 Security

- JWT Authentication (Spring Security)  
- API Gateway level validation  
- Role-based access (optional: USER, ADMIN)  

---

## ⚙️ Infrastructure Services

| Component | Technology |
|----------|-----------|
| API Gateway | Spring Cloud Gateway |
| Service Discovery | Eureka |
| Config Server | Spring Cloud Config |
| Messaging | RabbitMQ |

---

## 📁 Project Structure


```
recharge-backend/
├── eureka-server/
├── api-gateway/
├── config-server/
├── user-service/
├── operator-service/
├── recharge-service/
├── payment-service/
└── notification-service/
```

Each service structure:

```

com.project.service/
├── controller/
├── service/
├── repository/
├── entity/
├── dto/
├── client/       (Feign)
├── config/
└── exception/

```

---

## 🧪 Testing

```

mvn clean verify

```

### Test Focus
- Auth (login/signup, JWT)  
- Recharge flow  
- Payment success/failure  
- Notification trigger  
- Feign communication  

---

## 👥 Team & Ownership

| Member | Service |
|-------|--------|
| Person A | User + Security + Gateway |
| Person B | Operator Service |
| Person C | Recharge + Payment |
| Person D | Notification + Messaging |

---

## 🚀 Future Enhancements

- Kafka instead of RabbitMQ  
- Retry mechanism for failed payments  
- Circuit Breaker (Resilience4j)  
- Redis caching for plans  
- Admin dashboard  
- Docker Compose deployment  
- Rate limiting on Gateway  
```
