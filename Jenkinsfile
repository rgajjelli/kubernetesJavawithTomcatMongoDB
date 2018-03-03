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

            stage ('maven-build:1') {
                    steps {
                          sh "mvn clean compile package install"
                          }
            }
            stage('docker-build:2') {
                    steps {
                        script {
                          if ( env.BRANCH_NAME == 'master' ) {
                            pom = readMavenPom file: 'pom.xml'
                            TAG = pom.version
                            IMAGE = pom.name
                          } else {
                            TAG = env.BRANCH_NAME
                          }
                          sh "docker build -t ${IMAGE}:1.0 ."
                        }
                    }
              }

              stage('push-dockerhub:3'){
                  steps {
                     withCredentials([usernamePassword(credentialsId: 'dockerHubAccount', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {

                           script {
                                sh "docker login -u ${env.USERNAME} -p ${env.PASSWORD}"
                                sh "docker tag ${IMAGE}:1.0 ${IMAGE}:latest"
                                sh "docker push ${IMAGE}:latest"
                                echo "Image push complete."
                             }
                           }
                     }
            }

            stage('docker-compose:test-local:4') {

                 steps {
                   sh 'ls -lrt docker-compose.yml'
                   sh 'docker-compose -f docker-compose.yml up &'

                 }
            }

            stage('sleep:5') {

                   steps {
                     sh 'sleep 60'
                   }
            }

            stage('docker-compose:down-local:6') {

                   steps {
                     sh 'ls -lrt docker-compose.yml'
                     sh 'docker-compose -f docker-compose.yml down &'
                   }
            }
      }
}
