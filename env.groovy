pipeline {
    agent any
    environment {
        course = 'docker and gcp'
        name = 'sai'
    } 
    stages {
        stage ('classtoday') {
            steps {
                echo "welcome to class $name"
                echo "you enrolled into $course"
            }
        }
    }
}



pipeline {
    agent any 
    environment {
        course = 'docker and gcp'
        name = 'sai'
    } 
    stages {
        stage ('classtoday') {
            steps {
                echo "welcome to class $name"
                echo "you enrolled into $course"
            }
        }
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


pipeline {
    agent any 
    environment {
        DEPLOY_TO ='production'
    }
    stages {
        stage('proddeploy') {
            when {
               environment name: 'DEPLOY_TO', value: 'production' 
            }
            steps {
                echo "building the production"
            }
        }
    }
}


pipeline {
    agent any
    stages {
        stage('develop') {
            steps {
                echo "welcome to develop stage1"
            }
        }
        stage('production') {
            when {
                    expression {BRANCH_NAME==~ /('production|develop')/}
                }
            steps { 
                echo "welcome to production stage2"
            }
        }
    }
}

pipeline {
    agent any
    environment {
        DEPLOY_TO = 'production'
    }
    stages {
        stage('build') {
            when {
                allOf {
                                    branch 'production'
                  environment name: 'DEPLOY_TO',value: 'production'
                }

            }
            steps {
                echo "bulding the next env"
            }
        }
    }
}

pipeline {
    agent any
    environment {
        DEPLOY_TO = 'production'
    }
    stages {
        stage('build') {
            when {
                anyOf {
                                    branch 'production'
                  environment name: 'DEPLOY_TO',value: 'productionenv'
                }

            }
            steps {
                echo "bulding the next env"
            }
        }
    }
}