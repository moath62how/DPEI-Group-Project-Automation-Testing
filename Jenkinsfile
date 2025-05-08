
pipeline {
    agent any

    tools {
        maven 'Maven 3.8.6'    // Make sure Jenkins has Maven configured with this name
        jdk 'JDK 11'           // Same for JDK
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/moath62how/DPEI-Group-Project-Automation-Testing'   // Replace with your repo URL
            }
        }

        stage('Build and Run Tests') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Publish Test Report') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }
    }
}
