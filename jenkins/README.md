# Jenkins CI/CD Pipeline

## Overview

Automated CI/CD pipeline for GamingZone microservices with multi-stage deployment.

## Pipeline Stages

1. **Checkout** - Clone repository and set version
2. **Install Dependencies** - Install npm packages
3. **Build** - Build Docker image
4. **Test** - Run unit, integration, and e2e tests
5. **SonarQube Analysis** - Code quality scan
6. **Quality Gate** - Enforce quality standards
7. **Push to Registry** - Push Docker image (main/develop only)
8. **Deploy to Kubernetes** - Deploy to cluster (main/develop only)

## Prerequisites

### Jenkins Plugins
- Docker Pipeline
- Kubernetes CLI
- SonarQube Scanner
- Slack Notification
- JUnit
- HTML Publisher

### Credentials
- `docker-hub-credentials` - Docker registry credentials
- `kubeconfig` - Kubernetes cluster config
- `sonarqube-token` - SonarQube authentication
- `slack-webhook` - Slack webhook URL

## Environment Variables

```groovy
SERVICE_NAME = 'service-name'
DOCKER_REGISTRY = 'docker.io/gamingzone'
K8S_NAMESPACE = 'gamingzone'
SONAR_HOST = 'http://sonarqube:9000'
SLACK_CHANNEL = '#deployments'
```

## Usage

### Automatic Triggers
- Push to any branch triggers build and test
- Push to `main` or `develop` triggers full deployment
- Pull requests run tests only

### Manual Trigger
```bash
# From Jenkins UI
Build with Parameters -> Select branch -> Build
```

## Branch Strategy

- `main` → Production deployment (gamingzone namespace)
- `develop` → Development deployment (gamingzone-dev namespace)
- `feature/*` → Build and test only
- `release/*` → Build, test, and push image

## Quality Gates

- Code coverage > 80%
- No critical/blocker issues
- Security vulnerabilities = 0
- Code duplication < 3%

## Notifications

### Slack Notifications
- ✅ Success: Deployment completed
- ❌ Failure: Build/test/deployment failed
- ⚠️ Quality Gate: Failed quality checks

## Troubleshooting

### Build Fails
```bash
# Check Jenkins console output
# Verify Dockerfile exists
# Check npm dependencies
```

### Test Fails
```bash
# Review test reports in Jenkins
# Check coverage report
# Verify test environment
```

### Deployment Fails
```bash
# Check Kubernetes cluster status
kubectl get pods -n gamingzone
# Verify image exists in registry
# Check deployment logs
kubectl logs -f deployment/service-name -n gamingzone
```

## Scripts

### deploy-k8s.sh
Automated Kubernetes deployment script
```bash
./scripts/deploy-k8s.sh <service-name> <namespace> <version>
```

## Pipeline Library

Shared functions in `jenkins/pipeline-library.groovy`:
- `buildDockerImage()` - Build Docker image
- `pushDockerImage()` - Push to registry
- `runTests()` - Execute test suite
- `sonarScan()` - Run SonarQube analysis
- `deployToK8s()` - Deploy to Kubernetes
- `sendSlackNotification()` - Send Slack message
