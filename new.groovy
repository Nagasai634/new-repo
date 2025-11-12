pipeline {
    agent {
        label 'slave-vm'
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
                sh "git clone https://github.com/Nagasai634/spring-petclinic.git"
                dir ('spring-petclinic') {
                    sh 'mvn package'
                }
            }
        }
    }
}