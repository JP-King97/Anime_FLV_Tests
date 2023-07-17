pipeline {
    
    agent any
    
    tools{
        maven 'Maven'
    }
    stages{
        stage('Checkout'){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'a4cd17d1-a90c-498f-8384-35f1239f0300', url: 'https://github.com/JP-King97/Anime_FLV_Tests']])    
            }
        
        }
        
        stage('Build'){
            steps{
                sh 'mvn clean install -e'
            }
        }
        
        stage('Test'){
            steps{
                sh 'mvn clean test'
            }
        }
    }
}
