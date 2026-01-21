#!/bin/bash
set -e

SERVICE_NAME=$1
NAMESPACE=$2
VERSION=$3

if [ -z "$SERVICE_NAME" ] || [ -z "$NAMESPACE" ] || [ -z "$VERSION" ]; then
    echo "Usage: $0 <service-name> <namespace> <version>"
    exit 1
fi

echo "Deploying ${SERVICE_NAME}:${VERSION} to ${NAMESPACE}..."

# Update image in deployment
kubectl set image deployment/${SERVICE_NAME} \
    ${SERVICE_NAME}=${DOCKER_REGISTRY}/${SERVICE_NAME}:${VERSION} \
    -n ${NAMESPACE}

# Wait for rollout
kubectl rollout status deployment/${SERVICE_NAME} -n ${NAMESPACE} --timeout=5m

# Verify deployment
kubectl get pods -n ${NAMESPACE} -l app=${SERVICE_NAME}

echo "Deployment completed successfully!"
