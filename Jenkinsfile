pipeline { 
    agent any  

    tools { 
        maven 'maven 3.8.6' 
        jdk 'jdk11' 
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                ''' 
            }
        }

        stage ('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
        stage ('Test'){
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
        }
        stage ('Deploy-Dev-Infrastructure') {
            steps {
                sh """
                    aws cloudformation deploy --stack-name book-management-vpc --template-file ./infrastructure/vpc.yaml --region us-east-1 --no-fail-on-empty-changeset

                    aws cloudformation deploy --stack-name book-management-ip --template-file ./infrastructure/ip.yaml --region us-east-1 --no-fail-on-empty-changeset

                    aws cloudformation deploy --stack-name book-management-security --template-file ./infrastructure/security.yaml --capabilities CAPABILITY_NAMED_IAM --region us-east-1 --no-fail-on-empty-changeset

                    aws cloudformation deploy --stack-name book-management-web --template-file ./infrastructure/webserver.yaml --region us-east-1 --no-fail-on-empty-changeset

                    aws cloudformation deploy --stack-name book-management-db --template-file ./db.yaml --region us-east-1 --capabilities CAPABILITY_NAMED_IAM --no-fail-on-empty-changeset
                """
            }
        }
        stage ('Deploy-App'){
            steps {
                deploy adapters: [tomcat9(credentialsId: 'admin', path: '', url: 'http://54.163.128.202:8080/')], contextPath: '', war: '**/*.war ' 
            }
        }
    }
}
