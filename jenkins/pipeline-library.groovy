def buildDockerImage(String serviceName, String version) {
    sh """
        docker build -t ${DOCKER_REGISTRY}/${serviceName}:${version} .
        docker tag ${DOCKER_REGISTRY}/${serviceName}:${version} ${DOCKER_REGISTRY}/${serviceName}:latest
    """
}

def pushDockerImage(String serviceName, String version) {
    docker.withRegistry("https://${DOCKER_REGISTRY}", DOCKER_CREDENTIALS) {
        sh """
            docker push ${DOCKER_REGISTRY}/${serviceName}:${version}
            docker push ${DOCKER_REGISTRY}/${serviceName}:latest
        """
    }
}

def runTests() {
    sh '''
        npm install
        npm run test:unit
        npm run test:integration
    '''
}

def sonarScan(String projectKey) {
    withSonarQubeEnv('SonarQube') {
        sh """
            sonar-scanner \
                -Dsonar.projectKey=${projectKey} \
                -Dsonar.sources=src \
                -Dsonar.tests=test \
                -Dsonar.javascript.lcov.reportPaths=coverage/lcov.info
        """
    }
}

def deployToK8s(String serviceName, String namespace, String version) {
    sh """
        chmod +x scripts/deploy-k8s.sh
        ./scripts/deploy-k8s.sh ${serviceName} ${namespace} ${version}
    """
}

def sendSlackNotification(String status, String message) {
    def color = status == 'SUCCESS' ? 'good' : 'danger'
    slackSend(
        channel: SLACK_CHANNEL,
        color: color,
        message: "${status}: ${message}"
    )
}

return this
