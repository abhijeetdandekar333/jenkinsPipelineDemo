pipeline {
    agent any
    
    stages {
        stage("Hello") {
            steps {
                echo "Hello World"
            }
        }
        stage('Build') {
            steps {
            echo "Build"
            }
        }
        stage("test") {
            steps {
            echo "Testing"
        }
        }
        stage("Deploy") {
            steps {
            echo "Deploying"
        }
        }
    }
}
