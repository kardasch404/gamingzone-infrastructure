# Kubernetes Deployment Guide

## Prerequisites

- Kubernetes cluster 1.27+
- kubectl CLI installed
- Istio 1.19+ (for service mesh)
- Helm 3+ (optional)

## Quick Start

### 1. Create Namespace

```bash
kubectl apply -f k8s/base/namespace.yaml
```

### 2. Setup RBAC

```bash
kubectl apply -f k8s/base/rbac.yaml
```

### 3. Create Secrets

**⚠️ Update secret values before applying!**

```bash
# Edit secrets with your values
kubectl apply -f k8s/base/secret-template.yaml
```

### 4. Apply ConfigMaps

```bash
kubectl apply -f k8s/base/configmap.yaml
kubectl apply -f k8s/base/configmap-services.yaml
```

### 5. Create Persistent Volumes

```bash
kubectl apply -f k8s/base/pvc-postgres.yaml
kubectl apply -f k8s/base/pvc-redis.yaml
kubectl apply -f k8s/base/pvc-storage.yaml
```

### 6. Deploy Services

```bash
# Apply all base configurations
kubectl apply -f k8s/base/
```

### 7. Setup Istio Service Mesh

```bash
# Install Istio
istioctl install --set profile=default -y

# Enable sidecar injection
kubectl label namespace gamingzone istio-injection=enabled

# Apply gateway
kubectl apply -f k8s/base/istio-gateway.yaml
```

## Verify Deployment

```bash
# Check all resources
kubectl get all -n gamingzone

# Check PVCs
kubectl get pvc -n gamingzone

# Check secrets and configmaps
kubectl get secrets,configmaps -n gamingzone

# View logs
kubectl logs -f deployment/service-name -n gamingzone
```

## Service Mesh Monitoring

```bash
# Istio dashboard
istioctl dashboard kiali

# Grafana
istioctl dashboard grafana

# Jaeger tracing
istioctl dashboard jaeger
```

## Scaling

```bash
# Manual scaling
kubectl scale deployment service-name --replicas=5 -n gamingzone

# HPA is configured automatically (2-10 replicas)
kubectl get hpa -n gamingzone
```

## Troubleshooting

### Pod not starting
```bash
kubectl describe pod <pod-name> -n gamingzone
kubectl logs <pod-name> -n gamingzone
```

### PVC issues
```bash
kubectl get pvc -n gamingzone
kubectl describe pvc <pvc-name> -n gamingzone
```

### Service mesh issues
```bash
istioctl analyze -n gamingzone
kubectl logs -l app=istio-ingressgateway -n istio-system
```

## Clean Up

```bash
# Delete all resources
kubectl delete namespace gamingzone

# Or delete specific resources
kubectl delete -f k8s/base/
```

## Production Checklist

- [ ] Update all secrets with production values
- [ ] Configure TLS certificates
- [ ] Setup monitoring and alerting
- [ ] Configure backup strategy for PVCs
- [ ] Review resource limits
- [ ] Setup network policies
- [ ] Configure ingress/load balancer
- [ ] Enable pod security policies
- [ ] Setup log aggregation
- [ ] Configure auto-scaling policies
