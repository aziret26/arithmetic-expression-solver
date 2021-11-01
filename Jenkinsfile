pipeline {
  environment {
    IMAGE_BASE = 'com.home.practice/calculator-app'
    IMAGE_TAG = "v$BUILD_NUMBER"
    IMAGE_NAME = "${env.IMAGE_BASE}:${env.IMAGE_TAG}"
    IMAGE_NAME_LATEST = "${env.IMAGE_BASE}:latest"
    DOCKERFILE_NAME = "calculator-app-backend"
  }
  parameters {
    string(name: 'DOCKER_USER', defaultValue: '', description: 'docker.io username')
    string(name: 'DOCKER_PSWD', defaultValue: '', description: 'docker.io password')
  }
  agent none
  stages {
    stage('Prepare container') {
      agent {
        docker {
          image 'openjdk:11-jdk'
        }
      }
      stages {
        stage ('build'){
          steps {
            checkout scm
            sh 'chmod +x ./gradlew'
            sh './gradlew clean build'
          }
        }
        stage ('Archive artifact') {
          steps: {
            archiveArtifacts artifacts: 'build/libs/calculator-app-*.jar', onlyIfSuccessful: true
          }
        }
      }

    }
  }
}