
pipeline {
    agent any



stage('Checkout') {
    steps {
        git branch: 'master', url: 'https://github.com/moath62how/DPEI-Group-Project-Automation-Testing'
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
