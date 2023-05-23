def gv

def getEnv() {
    return ["dev", "preProd", "Prod", "staging"]
}

properties ([
    parameters([ 
        [  $class: 'CascadeChoiceParameter',
            choiceType: 'PT_SINGLE_SELECT',
            description: 'Select a choice',
            filterLength: 1,
            filterable: false,
            name: 'SERVER',
            referencedParameters: 'ENV',
             script: [
                $class: 'GroovyScript',
                fallbackScript: [
                  classpath: [],
                  sandbox: true,
                  script: 'return ["ERROR"]'
                ],
                script: [
                  classpath: [],
                  sandbox: true,
                  script: """
                    if (ENV == 'dev') {
                      return['link for dev environment']
                    } 
                    else if (ENV == 'staging') {
                      return['link for staging environment']
                    } 
                    else if (ENV == 'preProd') {
                      return['link for preProd environment']
                    }
                    else if (ENV == 'Prod') {
                      return['link for Prod environment']
                    }
                  """.stripIndent()         
        ]
       ]
      ]
    ])
])




pipeline {
    agent any
    parameters {
//         string(name: 'VERSION', defaultValue: '', description: 'version to deploy on prod')
        choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: '')
        booleanParam(name : 'executeTests', defaultValue: true, description:'')
        choice(name: 'ENV', choices: getEnv(), description: 'Select one')
    }
//     tools {
//         maven "<name>"
//         gradle "<name>"
//         jdk "<name>"
//     }
    environment {
        NEW_VERSION = '1.2.0'
//         SERVER_CREDENTIALS = credentials('server-credentials') // one way
    }
    
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
                echo "fetching groovy code"
            }
        }
        stage('Build') {
            when {
                expression {
                    params.executeTests
                }
            }
            steps {
                script{
            gv.buildApp()
//             echo "Building version ${NEW_VERSION}"
                }}
        }
        stage("test") {
//             when {
//                 expression {
//                     BRANCH_NAME == 'dev' || BRANCH_NAME == 'master'
//                 }
//             }
//             this stage will only execute if branch name is dev or master
            steps {
//             echo "Testing"
                script{
                    gv.testApp()
                }
        }
        }
        stage("Deploy") {
            steps {
//             echo "Deploying"
//                 echo "eploying version ${params.VERSION}"
//                 echo "Deploying with ${SERVER_CREDENTIALS}" // one way
//                 sh "${SERVER_CREDENTIALS}"
//            second way
                script {
                gv.deployApp()
                }
//             WithCredentials([
//                 usernamePassword(credentials: 'server-credentials', usernameVariable: USER, passwordVariable: PWD)
//                 ]) {
//                     sh "some script ${USER} ${PWD}"
//                 }
        }
        }
        stage("checkout") {
            steps {
                git branch: 'master', credentialsId: 'MyGitHub', url: 'https://github.com/abhijeetdandekar333/jenkins_test_code/blob/master/script.sh'
            }
        }
        stage("Properties") {
            steps {
                catchError(buildResult: 'FAILURE', stageResult:'FAILURE') {
                   echo "Server = ${SERVER}"
                   echo "Environment = ${ENV}"
                   sh '''#!/bin/bash
                    pwd
                    ls -ltr
                    sudo chmod +x script.sh
                    ./script.sh
                    if [ "$?" -eq "0" ]
                    then
                        echo "Script executed"
                    else
                        echo "Script has bug"
                    fi
                   '''
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
// using external scripts in jenkins -> 
// script {
// }
// above script inside each stage in which we can wriye groovy scrips
