
pipeline{
    agent any
    environment{
        PATH = "${env.PATH}\\allure-2.23.1\\lib\\;${env.PATH}"

    }

    stages{
        stage('Checkout'){
            steps{
                script{
                    checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'a4cd17d1-a90c-498f-8384-35f1239f0300', url: 'https://github.com/JP-King97/Anime_FLV_Tests.git']])
                }
            }
        }

        stage('Test'){
            steps{
                script{
                    bat "mvn clean test"
                }
            }
        }

        stage('Generate AllureReports'){
            steps{
                script{
                    allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
                }
            }
        }
    }

    post{
       always{
           archiveArtifacts artifacts: 'output.flv' , allowEmptyArchive: true
       }
   }
}//