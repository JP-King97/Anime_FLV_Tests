
pipeline{
    agent any
    environment{
        PATH = "/opt/apache-maven-3.9.3/bin:$PATH"
    }

    stages{
        stage('Checkout'){
            steps{
                script{
                    checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'a4cd17d1-a90c-498f-8384-35f1239f0300', url: 'https://github.com/JP-King97/Anime_FLV_Tests.git']])
                }
            }
        }

        stage('Download and Install Xvnc') {
            steps {
                script {
                    // Define the URL to download the Xvnc installation package
                    def xvncUrl = 'https://example.com/path/to/xvnc_installer.exe'  // Replace with the actual download URL

                    // Define the installation directory
                    def installDir = 'C:\\Xvnc'  // Replace with your desired installation directory

                    // Create the installation directory if it doesn't exist
                    bat "mkdir -p ${installDir}"

                    // Download Xvnc installer
                    bat "curl -o ${installDir}\\xvnc_installer.exe ${xvncUrl}"

                    // Install Xvnc silently (modify the installer command as needed)
                    bat "${installDir}\\xvnc_installer.exe /S"  // /S for silent installation

                    // Optionally, add Xvnc to the system PATH
                    bat 'setx PATH "%PATH%;C:\\Xvnc"'

                    // Verify Xvnc installation (you can modify this as needed)
                    bat "C:\\Xvnc\\xvnc.exe --help"  // Example: Check if Xvnc responds to --help

                    // Clean up the installer if needed
                    bat "del /q ${installDir}\\xvnc_installer.exe"
                }
            }
        }

        stage('Start VNC session'){
            steps{
                script{
                    bat 'Xvnc :1 -screen 0 1024x768x16 &'
                    bat 'export DISPLAY=:1'
                    bat 'taskkill /F /IM Xvnc.exe'
                }
            }
        }

        stage('Start VNC Recording'){
            steps{
                vncRecorder(
                    server: 'Kingtero97',
                    port: 5900,
                    password: 'J3141592'
                )
            }
        }

        stage('Test'){
            steps{
                script{
                    bat "mvn clean test"
                }
            }
        }

        stage('Stop VNC Recording'){
            steps{
                vncRecorderStop()
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