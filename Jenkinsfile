pipeline {
    agent any

    tools {
        maven 'Maven 3.8.6'    // Ensure Jenkins has Maven configured with this name
        jdk 'JDK 11'           // Ensure Jenkins has JDK 11 configured
    }

    stages {
        // Checkout stage is unnecessary, as Jenkins will automatically handle it
        // stage('Checkout') {
        //     steps {
        //         git 'https://github.com/moath62how/DPEI-Group-Project-Automation-Testing'
        //     }
        // }

        stage('Build and Run Tests') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Publish Test Report') {
            steps {
                junit 'target/surefire-reports/*.xml'  // Assuming Maven Surefire reports are generated here
            }
        }
    }
}
