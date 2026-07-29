# Food_delivery-Microservice
active in development

### 🏗️ Current Progress & Completed Phases

- [x] **Phase 0: Core Monolith & REST API**
  - Setup Spring Boot, PostgreSQL, & JPA Entities.
  - Added Feign Client for user service data validation.

- [x] **Phase 1: Event-Driven Architecture (Kafka)**
  - Implemented Kafka Producer in Order Service.
  - Created Consumer in Driver Service for asynchronous status updates.
- [x] **Phase 2: Caching Layer (Redis)**
  - Integrated Redis In-Memory Data Store to cache restaurant menu data.
  - **Goal:** Handled heavy traffic by serving menu queries under 5ms without hitting PostgreSQL repeatedly.
- [x] **Phase 3: Resilience & Fault Tolerance (Resilience4j / Circuit Breaker)** 
  - Implement Circuit Breaker in Order Service to isolate Driver Service failures.
  - Provide fallback responses (e.g., *"Driver search is busy, order queued"*) when downstream services go down.
- [x] **Phase 4: API Gateway & Service Discovery (Spring Cloud Gateway + Eureka)** 
  - Centralize entry point on port 8080 (routing, JWT security, and Rate Limiting).
  - Hide direct backend service ports (8081, 8082, 8083) and enable dynamic discovery.
- [x] **Phase 5: Centralized Observability & Tracing (Zipkin / Prometheus / Grafana)**
  - Implement Distributed Tracing (`Trace ID`) across Gateway → Order → Kafka → Driver Service.
  - Monitor microservices health and visualize performance metrics.
- [x] **Phase 6: Orchestration & Deployment (Docker & Kubernetes)** (In Progress)
  * Containerize all microservices using optimized multi-stage `Dockerfile`.
  * Orchestrate local infrastructure (Services, PostgreSQL, Redis, Kafka, Zipkin) via `docker-compose.yml`.
  * Define Kubernetes manifests (`deployment.yaml`, `service.yaml`) for auto-scaling and cloud orchestration.
----

### ⏳ Ongoing & Next Roadmap


----

## 📅 Dev Log (Progress Harian)
- **22 July 2026:** Set up Spring Boot, PostgreSQL, and JPA Entities; added Feign Client for user-service data validation.
- **25 July 2026:** Set up Kafka Broker and tested Kafka Producer & Consumer via Postman (verified `OrderCreated` event published to topic).
- **26 July 2026:** Integrated Redis caching layer and implemented Resilience4j Circuit Breaker with fallback mechanism.
- **27 July 2026:** Configured Spring Cloud Gateway on port 8080 and integrated Eureka Server for dynamic service discovery across microservices.
- **28 July 2026:** Implemented Centralized Observability & Tracing with Zipkin, Prometheus, and Grafana across microservices
- **29 July 2026:** Started Phase 6 setup by containerizing services with Dockerfiles and configuring multi-container docker-compose deployment.
 
