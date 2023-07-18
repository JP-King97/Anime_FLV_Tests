pipeline {
    
    agent any
    environment {
        PATH = "/opt/apache-maven-3.9.3/bin:$PATH"
    }

   // tools{
   //     maven 'Maven 3.9.3'
   // }

    stages{
        stage('Checkout'){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'a4cd17d1-a90c-498f-8384-35f1239f0300', url: 'https://github.com/JP-King97/Anime_FLV_Tests']])    
            }
        
        }
        
       // stage('Build'){
       //     steps{
       //         bat 'cd Anime_FLV_Tests'
       //     }
       // }
        
        stage('Test'){
            steps{
                bat "mvn clean test"
            }
        }
    }
}
//