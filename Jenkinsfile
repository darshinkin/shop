CODE_CHANGES = getGitChanges()
pipeline {
    agent any
    environment {
        SERVER_CREDENTIALS = credentials('ssh')
    }
    tools {
        gradle 'Gradle'

    }
    stages {
        stage('Build') {
            steps {
                echo 'Build stage!'
                sh "./gradlew build"
            }
        }
        stage('Test') {
            when {
                expression {
                    BRANCH_NAME = 'main' || BRANCH_NAME = 'dev'
                }
            }
            steps {
                echo 'Test stage!'
                sh "./gradlew test"
            }
        }
        stage('Package') {
            steps {
                echo 'Package stage!'
            }
        }
        stage('Publish to dockerhub.com') {
            steps {
                echo 'Publish stage!'
                sh "docker push arshinkinda/shop-origin:latest"
            }
        }
        stage('Deploy to k8s') {
            steps {
                echo 'Deploy to k8s!'
                echo "deploying with ${SERVER_CREDENTIALS}"
                sh "${SERVER_CREDENTIALS}"
            }
        }
    }
    post {
        always {
            echo 'Pipeline finished'
        }
        success {
            echo ' with success.'
        }
        failure{
            echo ' with failure.'
        }
    }
}