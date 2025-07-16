pipeline {
    agent {
        label 'jenkins-slave'
    }
    tools {
        jdk 'jdk'
        maven 'maven'
    }
    stages {
        stage('build') {
            steps {
                cleanWs()
                echo "building the code"
                sh "git clone https://github.com/demosoftility/Java_Project_SourceCode.git"
                dir ('Java_Project_SourceCode') {
                    sh 'mvn package'
                }
            }
        }
    }
}