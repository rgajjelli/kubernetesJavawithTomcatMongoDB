pipeline {

    agent any

    tools {
            jdk 'java8'
            maven 'maven3'
    }

    environment {
       IMAGE = "gajjelli/kuberneteswithtomcatmongodb"
     }

    stages {

          stage ('scm-checkout:0') {
                  steps {
                        git url: 'https://github.com/rgajjelli/kubernetesJavawithTomcatMongoDB.git'
                        checkout scm
                        }
          }

            stage ('maven-build:1') {
                    steps {
                          sh "mvn clean install"
                          }
            }
            stage('docker-build:2') {
                  agent any
                    steps {
                        script {
                          if ( env.BRANCH_NAME == 'master' ) {
                            pom = readMavenPom file: 'pom.xml'
                            TAG = pom.version
                            IMAGE = pom.name
                          } else {
                            TAG = env.BRANCH_NAME
                          }
                          sh "docker build -t ${IMAGE}:3.0 ."
                        }
                    }
              }

              stage('push-dockerhub:3'){
                  steps {
                     withCredentials([usernamePassword(credentialsId: 'dockerHubAccount', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {

                           script {
                                sh "docker login -u ${env.USERNAME} -p ${env.PASSWORD}"
                                sh "docker tag ${IMAGE}:3.0 ${IMAGE}:4.0"
                                sh "docker push ${IMAGE}:4.0"
                                echo "Image push complete."
                             }
                           }
                     }
            }

            stage('docker-compose:test-local:4') {
               agent any
                 steps {
                   sh 'docker-compose -f docker-compose.yml up &'

                 }
            }

            stage('sleep:5') {
                 agent any
                   steps {
                     sh 'sleep 1000000'
                   }
            }

            stage('docker-compose:down-local:6') {
                 agent any
                   steps {
                     sh 'docker-compose -f docker-compose.yml down &'
                   }
            }
      }
}
