pipeline {
    agent {
        label 'docker-slave'
    }
    stages {
        stage('build') {
            steps {
                echo "welcome to my first-pipeline"
                sh "hostname -i"
            }
        }
    }
}

pipeline {
    agent none

    stages {
        stage('build') {
            agent {
                label 'docker-slave'
            }
            steps {
                sh "hostname -i"
            }
        } 

        stage('maven') {
            agent {
                label 'java-mavem'
            }
            steps {
                sh "mvn --version"
            }
        }
    }
}



pipeline {
    agent any 
    stages {
        stage('build') {
            steps {
                echo "building the code"
            }
        }
        stage('codequality') {
            steps {
                 echo "checking the code quality"
            }
        }
        stage('Dockerbuildnpush') {
            steps {
                echo "building the docker"    
            }
        }
        stage('k8s') {
            steps {
                echo "deploying the image into k8s"
            }
        }
        stage("deploytostage") {
            when {
                branch 'release/*'
            }
            steps {
                echo "deploying the product"
            }
        }
        stage("deploytorelease") {
            when {
               tag pattern: 'v1.2.4.5',comparator: "REGEXP"
            }
            steps {
                echo "deploying the release as tag"
            }
        }
    }


pipeline {
    agent any

    stages {
        parallel {
            stage('build') {
                steps {
                    echo "building the code"
                    sleep 10
                }
            }
            stage('codequality') {
                steps {
                    echo "checking the code quality"
                    sleep 10
                }
            }
        }
        stage('build') {
            steps {
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