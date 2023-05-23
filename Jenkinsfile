pipeline {
    agent any
    parameters {
//         string(name: 'VERSION', defaultValue: '', description: 'version to deploy on prod')
        choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: '')
        booleanParam(name : 'executeTests', defaultValue: true, description:'')
    }
    tools {
        maven "<name>"
        gradle "<name>"
        jdk "<name>"
    }
    environment {
        NEW_VERSION = '1.2.0'
//         SERVER_CREDENTIALS = credentials('server-credentials') one way
    }
    
    stages {
        stage("Hello") {
            steps {
                echo "Hello World"
            }
        }
        stage('Build') {
            when {
                expression {
                    params.executeTests
                }
            }
            steps {
            echo "Build"
            echo "Building version ${NEW_VERSION}"
            }
        }
        stage("test") {
            when {
                expression {
                    BRANCH_NAME == 'dev' || BRANCH_NAME == 'master'
                }
            }
//             this stage will only execute if branch name is dev or master
            steps {
            echo "Testing"
        }
        }
        stage("Deploy") {
            steps {
            echo "Deploying"
                echo "eploying version ${params.VERSION}"
//                 echo "Deploying with ${SERVER_CREDENTIALS}" // one way
//                 sh "${SERVER_CREDENTIALS}"
//            second way
            WithCredentials([
                usernamePassword(credentials: 'server-credentials', usernameVariable: USER, passwordVariable: PWD)
                ]) {
                    sh "some script ${USER} ${PWD}"
                }
        }
        }
    }
//     post {
//         always {
//             //
//         }
//     }
}
// environment variables we can see in localhost:8080/env-vars.html or
// set own environment variables using attribute called environment
//  To extract credentials in jenkins and bind them to your env variable using credentials function but need to install "credentials Binding" plugin.
// usernamePassword(credentials: 'server-credentials', usernameVariable: USER, passwordVariable: PWD) is a function which stores username and passwrod in two differen 
// variables
// tools attribute -> Access build tools for your projects Only three tools available gradle, maven and jdk
// parameters -> to provide external configuration
