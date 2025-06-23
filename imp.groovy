pipeline {
    agent {
        label 'docker-slave'
    }
    environment {
        DOCKER_CREDS = credentials('dockerhub_creds')
        
    }
    stages {
        stage('build') {
            steps {
                sh "docker pull nginx"
                echo "pulling the image from docker hub"
                sh "docker images"
                sh "docker tag nginx nagasaivardhan/ngnix:sai"
                echo "docker login creditinals"
                sh "docker login -u ${DOCKER_CREDS_USR} -p ${DOCKER_CREDS_PSW}"
                sh "docker push nagasaivardhan/ngnix:sai"

            }
        }
    }
}