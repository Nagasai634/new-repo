pipeline {
    agent any

    stages {
        stage('build') {
            steps{
                echo "welcome to my first pipeline"
            }
        }
        stage('sonar') {
            steps {
                echo "scanning the code"
            }
        }
        stage('docker') {
            steps {
                echo "building the docker image"
            }
        }
        stage('kube') {
            steps {
                echo "deploying the k8s"
            }
        }
    }
}