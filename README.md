# Membership Service

This microservice handles membership and pass data for users in the GymApp ecosystem. It is designed to **store and query** existing membership and pass records, while the **creation** of these records is restricted to the `finance-service` to ensure transactional integrity.

## Responsibilities

- Store and retrieve `Membership` data.
- Store and retrieve `Pass` data.
- Expose read-only endpoints for clients.
- Accept internal write operations only from `finance-service`.

---

## Security

This service **does not implement its own authentication** directly. Instead:

- Relies on **API Gateway** for JWT validation.
- User identity and roles are passed via secure headers like:
    - `X-User-ID`
    - `X-User-Roles`

**Example** endpoint for logged-in user to query their passes:

```http
GET /api/pass/me
Headers:
  X-User-ID: <uuid>
```

---

## Status

-  Clean Checkstyle/PMD/SpotBugs report
-  96%+ test coverage via JaCoCo
-  Full Javadoc and method documentation
-  Integration tests included

---

## Technologies

- Java 17
- Spring Boot 3
- Spring Web
- Spring Validation
- SpringDoc OpenAPI (Swagger)
- JUnit 5 + Mockito
- JaCoCo + Checkstyle + PMD + SpotBugs

---

##  Endpoints

| Method | Path                | Description                     |
|--------|---------------------|---------------------------------|
| GET    | `/api/membership`   | Get current user's membership   | 
| GET    | `/api/pass`         | Get current user's passes       | 
| POST   | `/api/pass`         | Create passes                   | 
| POST   | `/api/membership`   | Create membership               | 

---


##  Notes

- It is **NOT** responsible for payment or business rules.
- All validations (membership rules, quantity, cost, etc.) are enforced upstream by `finance-service`.

---

##  Testing

To run tests and check coverage:

```bash
./mvnw clean verify
```

Check coverage report in: `target/site/jacoco/index.html`

---

## 👤 Author

Backend system designed and implemented by daniel-dev-2474.