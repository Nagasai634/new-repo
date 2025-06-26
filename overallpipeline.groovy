pipeline {
    agent any
    parameters {
        choice(name: 'build', 
           choices: 'no/nyes',
           description: 'this will build the appilication'
         )
         choice(name: 'codequality', 
           choices: 'no/nyes',
           description: 'this will run the codequality checks'
         )
         choice(name: 'dockerbuildnpush', 
           choices: 'no/nyes',
           description: 'this will build the docker image and push to docker hub'
         )
         choice(name: 'k8s', 
           choices: 'no/nyes',
           description: 'this will deploy the docker image into k8s cluster'
         )
         choice(name: 'deploytoproduction', 
           choices: 'no/nyes',
           description: 'this will deploy the docker image into production'
         )
    }
    stages {
        stage('build') {
            when {
                expression { params.build == 'yes' }
            }
            steps {
                echo "building the code"
            }
        }
        stage('codequality') {
            when {
                expression { params.codequality == 'yes' }
            }
            steps {
                 echo "checking the code quality"
            }
        }
        stage('Dockerbuildnpush') {
            when {
                expression { params.dockerbuildnpush == 'yes' }
            }
            steps {
                echo "building the docker"    
            }
        }
        stage('k8s') {
            when {
                expression { params.k8s == 'yes' }
            }
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
            when {
                expression { params.deploytoproduction == 'yes' }
            }
            steps {
                echo "deploy the production"
            }
           
        }
        
    }
}