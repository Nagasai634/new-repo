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
                label 'java-maven'
            }
            steps {
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
                label 'jenkins-slave'
            }
            steps {
                timeout (time:10,unit:'SECONDS') 
                {
                    echo "sleeping for 60 seconds"
                    sleep 60
                }
                
            }
        }
    }
}

pipeline {
    agent any
    tools {
        maven 'MAVEN'
    }
    stages { 
        stage('build') {
            steps {
                echo "welcome to tools section"
                sh "mvn --version"
            }
        }
        stage('otherprojects') {
            tools {
                jdk 'jdk-21'
            }
            steps {
                echo "another version of java"
                sh "java --version"
            }
        }
    }
}

pipeline {
    agent any

    stages {
        stage('parallel-tasks') {
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
                echo "deploying to k8s"
            }
        }
    }
}