pipeline {
    agent any

    tools {
        jdk 'Java17'
        maven 'Maven3'
    }

    environment {
        // This sets the SONAR_HOME variable using the SonarQube tool name
        SONAR_HOME = tool "Sonar"
    }

    stages {
        stage('Clone Code from GitHub') {
            steps {
                git url: "https://github.com/smrutisouravmoharana/Spring-Boot-Pipeline-SetUp.git", branch: "main"
            }
        }

        stage('Build') {
            steps {
                echo "Building the project..."
                sh 'mvn clean package'
            }
        }

        stage('SonarQube Quality Analysis') {
            steps {
                withSonarQubeEnv("Sonar") {
                    sh 'mvn sonar:sonar -Dsonar.projectKey=Spring-Boot-Pipeline-SetUp -Dsonar.projectName=Spring-Boot-Pipeline-SetUp'
                }
            }
        }
        
        // stage('OWASP Dependency Check') {
          //  steps {
            //   dependencyCheck additionalArguments: '--scan ./', odcInstallation: 'dc'
              // dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
            //}
        //}
        
        stage('Sonar Quality Gate Scan') {
            steps {
                timeout(time: 2, unit: "MINUTES"){
                    waitForQualityGate abortPipeline: false
                }
            }
        }
        
         stage('Trivy File System Scan') {
            steps {
              sh "trivy fs --format table -o trivy-fs-report.html ."
            }
        }

        stage('Test') {
            steps {
                echo "Running tests..."
                sh 'mvn test'
            }
        }

        stage('Deploy Using Docker Compose') {
          steps {
              echo "Building Docker image..."
              sh 'docker build -t spring-boot-pipeline-setup .'

              echo "Starting app with Docker Compose..."
              sh 'docker-compose up -d'
               }
          }

    }
}
