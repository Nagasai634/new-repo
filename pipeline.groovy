pipeline {
    agent {
        label 'jenkins-slave'
    }
    tools {
        jdk 'jdk'
        maven 'maven'
    }
    environment {
        DOCKER_CREDS = credentials('docker_creds')
    }
    stages {
        stage('build') {
            steps {
               echo "building the code"
               sh "java --version"  
            }
        }
        stage('docker') {
            steps {
                sh "docker pull ubuntu"
                sh "docker tag nginx nagasaivardhan/sai:v4"
                sh "docker images"
                sh "docker login -u ${DOCKER_CREDS_USR} -p ${DOCKER_CREDS_PSW}"
                sh "docker push nagasaivardhan/sai:v4"
            }
        }
    }
}