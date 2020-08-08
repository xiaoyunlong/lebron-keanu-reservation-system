pipeline {
    
    agent any

    environment {
        JENKINS_NODE_COOKIE = "donotkillme"
    }

    stages {
        stage('Test') {
            steps {
                echo 'test'
                bat 'gradle test'
            }
        }

        stage('Build') {
            steps {
                echo 'build'
                bat 'gradle build'
            }
        }

        stage('Deploy') {
            steps {
                echo 'deploy'
                bat "copy build\\libs\\*.jar d:\\backend\\deploy\\"
                bat "run.bat"
            }
        }
    }
}