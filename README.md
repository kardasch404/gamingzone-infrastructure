# GamingZone Infrastructure

Docker-based infrastructure for GamingZone microservices platform.

## 🏗️ Architecture

This infrastructure provides:
- **PostgreSQL 16**: Primary database
- **Redis 7**: Caching layer
- **Apache Kafka**: Event streaming
- **ElasticSearch 8**: Search engine
- **Prometheus**: Metrics collection
- **Grafana**: Monitoring dashboards
- **Kibana**: Log visualization

## 📋 Prerequisites

- Docker Engine 24.0+
- Docker Compose 2.20+
- 8GB RAM minimum
- 20GB free disk space

## 🚀 Quick Start

### 1. Clone and Setup

```bash
git clone <repository-url>
cd gamingzone-infrastructure
cp .env.example .env
```

### 2. Start Infrastructure

**Production mode:**
```bash
docker-compose up -d
```

**Development mode:**
```bash
docker-compose -f docker-compose.yml -f docker-compose.dev.yml up -d
```

### 3. Verify Services

```bash
docker-compose ps
```

## 🔌 Service Endpoints

| Service | Port | URL |
|---------|------|-----|
| PostgreSQL | 5432 | localhost:5432 |
| Redis | 6379 | localhost:6379 |
| Kafka | 9092 | localhost:9092 |
| ElasticSearch | 9200 | http://localhost:9200 |
| Kibana | 5601 | http://localhost:5601 |
| Prometheus | 9090 | http://localhost:9090 |
| Grafana | 3000 | http://localhost:3000 |

## 🔐 Default Credentials

**PostgreSQL:**
- User: `gamingzone`
- Password: `gamingzone_secret`
- Database: `gamingzone`

**Redis:**
- Password: `redis_secret`

**Grafana:**
- User: `admin`
- Password: `admin`

**⚠️ Change these in production!**

## 📊 Monitoring

Access Grafana at http://localhost:3000
- Prometheus datasource is pre-configured
- Create custom dashboards for your services

## 🛠️ Management Commands

**Stop all services:**
```bash
docker-compose down
```

**Stop and remove volumes:**
```bash
docker-compose down -v
```

**View logs:**
```bash
docker-compose logs -f [service-name]
```

**Restart a service:**
```bash
docker-compose restart [service-name]
```

## 🔍 Health Checks

All services include health checks. Check status:
```bash
docker-compose ps
```

## 📦 Volumes

Persistent data is stored in Docker volumes:
- `postgres_data`: Database files
- `redis_data`: Cache data
- `kafka_data`: Message logs
- `elasticsearch_data`: Search indices
- `prometheus_data`: Metrics
- `grafana_data`: Dashboards

## 🐛 Troubleshooting

**Kafka connection issues:**
```bash
docker-compose restart zookeeper kafka
```

**ElasticSearch memory errors:**
Increase Docker memory limit to 4GB+

**Port conflicts:**
Check `.env` file and modify ports if needed

## 📝 Port Allocation

### Infrastructure (5000-6999)
- PostgreSQL: 5432
- Redis: 6379
- Kafka: 9092
- Zookeeper: 2181

### Monitoring (9000-9999)
- ElasticSearch: 9200
- Prometheus: 9090
- Kibana: 5601
- Grafana: 3000

### Microservices (4000-4999)
See main project documentation

## 🤝 Contributing

1. Create feature branch
2. Make changes
3. Test locally
4. Submit pull request

## 📄 License

MIT License - see LICENSE file
