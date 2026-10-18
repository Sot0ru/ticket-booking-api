# Concurrent Ticket Booking API

![CI](https://github.com/Sot0ru/ticket-booking-api/actions/workflows/ci.yml/badge.svg)

A REST API for reserving and booking event seats that **never double-books a seat**, even when many users try to book the same seat at the same moment.

> 🚧 **Status: in development.** See the [roadmap](#roadmap) for progress.

## The problem

Two users click "book" on seat A12 within the same millisecond. A naive implementation reads the seat as `AVAILABLE` in both requests and books it twice. This project explores how to prevent that and proves the fix with concurrency tests.

## Tech stack

Java 25 · Spring Boot 4 · Spring Data JPA · PostgreSQL · Docker Compose · JUnit 5 · Testcontainers · GitHub Actions

## Architecture

<!-- TODO: add a diagram (request → controller → service → repository → PostgreSQL) -->

```
Client ──► REST Controller ──► BookingService ──► SeatRepository ──► PostgreSQL
                                     │
                         @Scheduled job releases expired holds
```

### Seat lifecycle

```
AVAILABLE ──hold──► HELD ──book──► BOOKED
    ▲                 │
    └──expire/release─┘
```

## Design decisions

<!-- This section is what interviewers read. Explain *why*, not just *what*. -->

- **Optimistic vs. pessimistic locking:** TODO: which one did you choose, and why?
- **Idempotency keys:** TODO: what happens if a client retries a booking request after a timeout?
- **Hold expiry:** TODO: how are expired holds released, and what race can occur between expiry and booking?

## API

| Method | Path | Description |
|---|---|---|
| `GET` | `/events/{id}/seats` | List seats and their status |
| `POST` | `/seats/{id}/hold` | Hold a seat for 10 minutes |
| `POST` | `/seats/{id}/book` | Book a held seat (requires `Idempotency-Key` header) |
| `DELETE` | `/seats/{id}/hold` | Release a hold |

<!-- TODO: update once implemented; add example requests/responses -->

## Running locally

Requirements: Java 25, Docker Desktop (running).

```bash
./mvnw spring-boot:run   # starts PostgreSQL via compose.yaml automatically
./mvnw verify            # runs all tests (Testcontainers needs Docker)
```

## Roadmap

**Week 1: Domain and basic API**
- [ ] `Seat` state transitions (hold / book / release) with unit tests
- [ ] Optimistic locking with `@Version`
- [ ] `GET /events/{id}/seats`, `POST /seats/{id}/hold`
- [ ] Seed data for one demo event

**Week 2: Concurrency**
- [ ] `SeatHoldConcurrencyTest`: N parallel holds → exactly one succeeds
- [ ] Map lost races to `409 Conflict` (`GlobalExceptionHandler`)
- [ ] `POST /seats/{id}/book` with `Idempotency-Key`
- [ ] `@Scheduled` job that releases expired holds

**Week 3: Polish**
- [ ] Flyway migrations instead of `ddl-auto=update`
- [ ] OpenAPI / Swagger UI
- [ ] Architecture diagram and design decisions in this README
- [ ] Optional: load test (e.g. k6) and measured results

first commit

