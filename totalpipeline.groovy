pipeline {
    agent {
        label 'jenkins-slave'  
    }
    tools {
        jdk 'jdk'             
        maven 'maven'         
    }
    stages {
        stage('Build') {      
            steps {
                
                cleanWs()
                sh "rm -rf spring-petclinic"
                
                // Clone repository using Jenkins git step instead of shell
                git 'https://github.com/spring-projects/spring-petclinic.git'
                
                // Build with proper error handling
                dir('spring-petclinic') {
                    sh "mvn clean package"
                }
            }
            
        }
    }
}





pipeline {
    agent {
        label 'jenkins-slave'
    }
    tools  {
        jdk 'jdk'
        maven 'maven'
    }
    stages {
        stage('build') {
           steps {
            echo "********************Building the code**********************"
                sh 'rm -rf spring-petclinic'   
                sh 'git clone https://github.com/pavandath/spring-petclinic.git'
                dir ('spring-petclinic'){
                sh 'mvn clean package -DskipTests'
                }
            
           }
        }
    }
}









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
        stage
    }
}