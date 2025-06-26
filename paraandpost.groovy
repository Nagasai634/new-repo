pipeline {
    agent any
    parameters {
        string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who are you')
        text(name: 'BIOGRAPHY', defaultValue: '', description: 'enter as much as possible')
        booleanParam(name: 'TOGGLE', defaultValue: true, description: 'enter the value')
        choice(name: 'CHOICE', choices: ['One', 'Two', 'Three'], description: 'enter as much as possible')
        password(name: 'PASSWD',defaultValue: 'SECRET', description: 'enter the password')

    }
    stages {
        stage('building with parameters') {
            steps {
                echo "hello ${params.PERSON}"
                echo "Biography ${params.BIOGRAPHY}"
                echo "choice ${params.CHOICE}"
                echo "Password ${params.PASSWD}"
            }
        }
    }
}


pipeline {
    agent {
        label 'master'
    }
    parameters {
        string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')

        text(name: 'BIOGRAPHY', defaultValue: '', description: 'Enter some information about the person')

        booleanParam(name: 'TOGGLE', defaultValue: true, description: 'Toggle this value')

        choice(name: 'CHOICE', choices: ['One', 'Two', 'Three'], description: 'Pick something')

        password(name: 'PASSWORD', defaultValue: 'SECRET', description: 'Enter a password')
    }
    stages {
        stage('Example') {
            steps {
                echo "Hello ${params.PERSON}"

                echo "Biography: ${params.BIOGRAPHY}"

                echo "Toggle: ${params.TOGGLE}"

                echo "Choice: ${params.CHOICE}"

                echo "Password: ${params.PASSWORD}"
            }
        }
    }
}


pipeline {
    agent any
    stages {
        stage('building the code') {
            steps {
                echo "built the code"
            }
        }
    }
    post {
            success {
                echo "if pipeline is success"
            }
            failure {
                echo "if pipeline is failure"
            }
            always {
                echo "if pipeline is failure or sucsess doesn't matter print it"
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
        stage("deploytoproduction") {
            options {
                timeout(time: 300, unit: 'SECONDS')
            }
            input {
                message  "doing deploying in production??????"
                ok 'yes'
                submitter 'Nagasaivardhan,devsai'
            }
            steps {
                echo "deploy the production"
            }
           
        }
        
    }
}