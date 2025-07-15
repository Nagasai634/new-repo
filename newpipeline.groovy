pipeline {
    agent {
        label 'jenkins-slave'
    }
    stages {
        stage('build') {
             options {
                timeout (time: 300, unit: 'SECONDS')
            }
            input {
                message 'building'
                ok 'yes'
                submitter 'sai,venkat,nagasai'
            }
            steps{
                echo "building new code"
            }
        }
        stage('scanning') {
             options {
                timeout (time: 300, unit: 'SECONDS')
            }
            input {
                message 'scanning'
                ok 'yes'
                submitter 'sai,venkat,nagasai'
            }
            steps{
                echo "scanning the code"
            }
        }
        stage("deployToprod") {
            options {
                timeout (time: 300, unit: 'SECONDS')
            }
            input {
                message 'are you directly deploying into production'
                ok 'yes'
                submitter 'sai,venkat,nagasai'
            }
            steps {
                echo "deploying the code into production"
            }
        }
    }
}