pipeline{
    agent any 
    stages {
        stage('build') {
            steps {
                sh "ls"
                echo "building the code"
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
                    expression {BRANCH_NAME==~ /('develops|production')/}
                }
            steps { 
                echo "welcome to production stage2"
            }
        }
    }
}



pipeline{
    agent {
        label 'jenkins-slave1'
    }
    stages {
        stage('build'){
            steps{
                sh "docker images"
            }
        }
    }   
}