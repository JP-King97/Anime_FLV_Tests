
pipeline{
    agent any
    environment{
        PATH = "C:\\path\\to\\apache-maven-3.9.3\\bin;${env.PATH}"
        FFMPEG_PATH = "${WORKSPACE}\\ffmpeg-6.0"
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
                script {
                    // Start recording video
                    def videoFile = 'output.flv'
                    //bat "cd \\ffmpeg-6.0"
                    def cmd = "-f gdigrab -framerate 30 -t 60 -i desktop ${videoFile}"

                    // Run your tests
                    def process = bat(script: cmd, returnStatus: true)
                    if (process != 0) {
                        error("Failed to start video recording.")
                    }

                    bat "mvn clean test"

                    // Stop recording video
                    sh "taskkill /IM ffmpeg.exe /F"

                    // Archive the video
                    archiveArtifacts artifacts: videoFile, allowEmptyArchive: true
                }
            }
        }

        stage('Generate AllureReports'){
            steps{
                allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
            }
        }
    }

    post{
        always{
            archiveArtifacts artifacts: 'output.flv' , allowEmptyArchive: true
        }
    }


}