pipeline {
    agent any
    
    environment {
        JAVA_HOME = tool 'JDK_8'
        MAVEN_HOME = tool 'Maven_3.6'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                script {
                    def mvnHome = tool 'Maven_3.6'
                    sh "${mvnHome}/bin/mvn clean install"
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    def mvnHome = tool 'Maven_3.6'
                    sh "${mvnHome}/bin/mvn test"
                }
            }
        }

        stage('Deploy') {
            steps {
                // Add deployment steps as needed
            }
        }
    }

    post {
        always {
            // Clean up or post-build actions
        }
    }
}
