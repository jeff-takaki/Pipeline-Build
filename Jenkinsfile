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
        stage ('Deploy-Green-Dev-Infrastructure') {
            steps {
                sh """
                    aws cloudformation deploy --stack-name book-management-vpc --template-file ./infrastructure/vpc.yaml --region us-east-1 --no-fail-on-empty-changeset

                    aws cloudformation deploy --stack-name book-management-ip --template-file ./infrastructure/ip.yaml --region us-east-1 --no-fail-on-empty-changeset

                    aws cloudformation deploy --stack-name book-management-security --template-file ./infrastructure/security.yaml --capabilities CAPABILITY_NAMED_IAM --region us-east-1 --no-fail-on-empty-changeset

                    aws cloudformation deploy --stack-name book-management-web-dev --template-file ./infrastructure/webserver-bg.yaml --region us-east-1 --no-fail-on-empty-changeset --parameter-overrides file://infrastructure/webserver-param-dev-green.json

                    #aws cloudformation deploy --stack-name book-management-db-dev --template-file ./infrastructure/db.yaml --region us-east-1 --no-fail-on-empty-changeset --parameter-overrides file://infrastructure/db-param-dev.json
                    
                    """

                    
            }
        }
        stage('Ping-Web-Green-Dev'){
            steps{
                ansiblePlaybook become: true, credentialsId: 'ssh', disableHostKeyChecking: true, installation: 'ansible', inventory: 'ansible/inventory.yaml', playbook: 'ansible/ping-web-playbook.yaml'
            }
        }
        stage ('Deploy-Green-Dev-App'){
            steps {
                deploy adapters: [tomcat9(credentialsId: 'admin', path: '', url: 'http://54.162.187.214:8080/')], contextPath: '', war: '**/*.war ' 
            }
        }
        stage('Test-Green-Book-Management'){
            steps{
                ansiblePlaybook become: true, credentialsId: 'ssh', disableHostKeyChecking: true, installation: 'ansible', inventory: 'ansible/inventory.yaml', playbook: 'ansible/test-web-playbook-green.yaml'
            }
        }

        stage ('Deploy-Blue-Dev-Infrastructure') {
            steps {
                sh """
                    
                    aws cloudformation deploy --stack-name book-management-web-dev --template-file ./infrastructure/webserver-bg.yaml --region us-east-1 --no-fail-on-empty-changeset --parameter-overrides file://infrastructure/webserver-param-dev-blue.json

                    #aws cloudformation deploy --stack-name book-management-db-dev --template-file ./infrastructure/db.yaml --region us-east-1 --no-fail-on-empty-changeset --parameter-overrides file://infrastructure/db-param-dev.json
                    
                    """

                    
            }
        }
        stage('Ping-Web-Blue-Dev'){
            steps{
                ansiblePlaybook become: true, credentialsId: 'ssh', disableHostKeyChecking: true, installation: 'ansible', inventory: 'ansible/inventory.yaml', playbook: 'ansible/ping-web-playbook.yaml'
            }
        }
        stage ('Deploy-Blue-Dev-App'){
            steps {
                deploy adapters: [tomcat9(credentialsId: 'admin', path: '', url: 'http://54.163.128.202:8080/')], contextPath: '', war: '**/*.war ' 
            }
        }
        stage('Test-Blue-Book-Management'){
            steps{
                ansiblePlaybook become: true, credentialsId: 'ssh', disableHostKeyChecking: true, installation: 'ansible', inventory: 'ansible/inventory.yaml', playbook: 'ansible/test-web-playbook-blue.yaml'
            }
        }

        stage ('Deploy-All-Dev-Infrastructure') {
            steps {
                sh """
                    
                    aws cloudformation deploy --stack-name book-management-web-dev --template-file ./infrastructure/webserver-bg.yaml --region us-east-1 --no-fail-on-empty-changeset --parameter-overrides file://infrastructure/webserver-param-dev.json

                    #aws cloudformation deploy --stack-name book-management-db-dev --template-file ./infrastructure/db.yaml --region us-east-1 --no-fail-on-empty-changeset --parameter-overrides file://infrastructure/db-param-dev.json
                    
                    """       
            }
        }
    }
}
    