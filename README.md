# Food_delivery-Microservice
active in development

### 🏗️ Current Progress & Completed Phases

- [x] **Phase 0: Core Monolith & REST API**
  - Setup Spring Boot, PostgreSQL, & JPA Entities.
  - Added Feign Client for user service data validation.

- [x] **Phase 1: Event-Driven Architecture (Kafka)**
  - Implemented Kafka Producer in Order Service.
  - Created Consumer in Driver Service for asynchronous status updates.

----

### ⏳ Ongoing & Next Roadmap

- [ ] **Phase 2: Caching Layer (Redis)** ⏳ *(in progress)
- [ ] **Phase 3: Resilience & Circuit Breaker (Resilience4j)**
- [ ] **Phase 4: API Gateway & Service Discovery (Eureka)**
- [ ] **Phase 5: Observability & Tracing (Zipkin / Prometheus)**

----

## 📅 Dev Log (Progress Harian)

- **22 July 2026**: Set up Spring Boot, PostgreSQL, and JPA Entities; added Feign Client for user-service data validation.
- **25 July 2026**: Set up Kafka Broker and tested Kafka Producer & Consumer via Postman (verified `OrderCreated` event published to topic).
- **26 July 2026**: *(Planned)* Optimize event payload & prepare Redis integration.
