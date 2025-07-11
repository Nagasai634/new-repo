pipeline {
    agent {
        label 'jenkins-slave'
    }
    environment {
        course_name = 'Gcp'
        name =  'siva'
    }
    stages {
        stage('versions') {
            steps {
                echo "welcome to course ${course_name}"
                 echo "welcome to course ${name}"
               sh "docker images"
               sh "mvn --version"
               sh "java --version"
            }
        }
    }
}