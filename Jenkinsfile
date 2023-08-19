
pipeline{
    agent any
    environment{
        PATH = "/opt/apache-maven-3.9.3/bin:$PATH"
        FFMPEG_DIR = "${WORKSPACE}\\ffmpeg-6.0"  // Assuming your tar.xz file is in the workspace
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
                    bat "cd ${FFMPEG_DIR}"
                    bat "ffmpeg -f gdigrab -framerate 30 -t 60 -i desktop ${videoFile}"

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