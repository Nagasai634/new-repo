pipeline {
    agent {
        label 'jenkins-slave'
    }
    stages {
        stage('paralleltasks') {
            parallel {
                stage('build') {
                    steps {
                        echo "building the code"
                        sleep 30
                    }
                }
                stage('scan') {
                    steps {
                        echo "scanning the code"
                        sleep 30
                    }
                }
                stage('test') {
                    steps {
                        echo "testing the code"
                        sleep 30
                    }
                }    
            }
        }
        stage('docker') {
            steps {
                echo "building the docker images"
            }
        }
    }
}