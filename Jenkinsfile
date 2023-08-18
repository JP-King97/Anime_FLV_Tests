pipeline {
    
    agent any
    environment {
        PATH = "/opt/apache-maven-3.9.3/bin:$PATH"
    }

   // tools{
   //     maven 'Maven 3.9.3'
   // }

    stages{

         stage('Start VNC session'){
              steps {
                  script{
                  bat 'Xvnc :1 -screen 0 1024x768x16'

                  env.DISPLAY = ":1"

                  bat 'taskkill /F /IM Xvnc.exe'
                  }
              }
         }

        stage('Start VNC Recording') {
            steps {
                // Configure the VncRecorder plugin
                vncRecorder(
                    server: 'your-vnc-server',
                    port: 5900,
                    password: 'your-vnc-password'
                )
            }
        }

        stage('Checkout'){
            steps{
            checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'a4cd17d1-a90c-498f-8384-35f1239f0300', url: 'https://github.com/JP-King97/Anime_FLV_Tests.git']])            }

        }





       // stage('Start VNC Recording and test'){
       //     steps{
       //         script {
       //             // Start Xvnc server and run test in the background
       //             def command = """
       //                 Xvnc :1 -screen 0 1024x768x16 &
       //                 export DISPLAY=:1
       //                 mvn clean test
       //                 taskkill /F /IM Xvnc.exe
       //             """
       //             bat(command)
       //         }
       //     }
       // }

       // stage('Start VNC Recording') {
       //     steps {
       //         // Configure the VncRecorder plugin
       //         vncRecorder(
       //             server: 'your-vnc-server',
       //             port: 5900,
       //             password: 'your-vnc-password'
       //         )
       //     }
       // }
        
        stage('Test'){
            steps{
                script{

                    bat "mvn clean test"
                }
            }
        }

       // stage('Stop VNC Recording') {
       //     steps {
       //         // Stop the VNC recording
       //         vncRecorderStop()
       //     }
       // }

        stage('Generate Allure Reports'){
            steps{
                allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
            }
        }
    }

    post{
        always {
            // Archive the recorded video as an artifact
            archiveArtifacts artifacts: 'output.flv', allowEmptyArchive: true
        }
    }

}
