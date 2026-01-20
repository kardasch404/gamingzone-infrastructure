# GamingZone Infrastructure

Docker-based infrastructure for GamingZone microservices platform.

## Prerequisites

- Docker 20.10+
- Docker Compose 2.0+

## Quick Start

1. Copy environment template:
```bash
cp .env.example .env
```

2. Update `.env` with secure passwords

3. Start infrastructure:
```bash
docker-compose up -d
```

4. Verify services:
```bash
docker-compose ps
```

## Services

| Service | Port | Description |
|---------|------|-------------|
| postgres-auth | 5432 | Auth service database |
| postgres-catalog | 5433 | Catalog service database |
| postgres-inventory | 5434 | Inventory service database |
| postgres-order | 5435 | Order service database |
| redis | 6379 | Cache and sessions |
| zookeeper | 2181 | Kafka coordination |
| kafka-1 | 9092 | Kafka broker 1 |
| kafka-2 | 9093 | Kafka broker 2 |
| kafka-3 | 9094 | Kafka broker 3 |

## Management

Stop services:
```bash
docker-compose down
```

Stop and remove volumes:
```bash
docker-compose down -v
```

View logs:
```bash
docker-compose logs -f [service-name]
```
