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
                sh "docker tag ubuntu nagasaivardhan/sai:v4"
                sh "docker images"
                sh "docker login -u ${DOCKER_CREDS_USR} -p ${DOCKER_CREDS_PSW}"
                sh "docker push nagasaivardhan/sai:v4"
            }
        }
    }
}







pipeline {
    agent {
        label 'jenkins-slave1' // Specify the agent to run the pipeline
    }
    tools {
        jdk 'jdk' // Define the JDK tool
        maven 'maven' // Define the Maven tool
    }
    environment {
        APPLICATION_NAME = 'eureka' // Set the application name
        SONAR_URL = 'http://34.122.117.31:9000' // Set the SonarQube URL
        SONAR_TOKEN = credentials('sonar_creds') // Retrieve SonarQube token from Jenkins credentials
    }
    stages {
        stage('Build') {
            steps {
                echo "Building ${APPLICATION_NAME}..." // Log the build process
                sh "mvn clean package -DskipTests=true" // Build the application, skipping tests
            }
        }
        stage('Code Quality') {
            steps {
                echo "Scanning ${APPLICATION_NAME} code for quality..." // Log the code quality scan
                withSonarQube('sonarqube') { // Use the SonarQube server defined in Jenkins
                    sh """
                    mvn clean verify sonar:sonar \
                      -Dsonar.projectKey=eureka-ms \
                      -Dsonar.host.url=${SONAR_URL} \
                      -Dsonar.login=${SONAR_TOKEN} // Run SonarQube analysis
                    """
                }
                timeout(time: 2, unit: "MINUTES") { // Set a timeout for the quality gate check
                    script {
                        waitforQualityGate abortPipeline: true // Wait for the quality gate to pass or abort
                    }
                }
            }
        }
       
    }
}