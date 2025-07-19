pipeline {
    agent any
    parameters {
        string(name: 'COMMENTS', defaultValue: '', description: 'Enter the Change Request details')
        choice(name: 'SERVER', choices: ['uschizlapg3001', 'uschizlapg3002', 'uschizlapg3003', 'uschizlapg3201', 'uschizlapg3202', 'uschezlapg4101', 'uschizlapg4001', 'uschizlapg4002', 'uschizlapg4003', 'uschizlapg4004', 'uschizlapg4201', 'uschizlapg4202'], description: 'Select the server')
        choice(name: 'OPERATION', choices: ['Start', 'Stop', 'Restart', 'Status'], description: 'Select the operation to perform')
    }
    environment {
        DEFAULT_CREDENTIALS = 'jenkins-to-apigee-credentials'
        CREDENTIALS_ID = "${params.SERVER == 'uschezlapg4101' ? 'jenkins-to-apigee-ext-credentials' : DEFAULT_CREDENTIALS}"
    }
    stages {
        stage('Display Parameters') {
            steps {
                script {
                    echo "Comments: ${params.COMMENTS}"
                    echo "Selected Server: ${params.SERVER}"
                    echo "Selected Operation: ${params.OPERATION}"
                }
            }
        }
        stage('Connect to Apigee Server') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: CREDENTIALS_ID, usernameVariable: 'SSH_USER', passwordVariable: 'SSH_PASS')]) {
                        sh """
                        sshpass -p "$SSH_PASS" ssh -o StrictHostKeyChecking=no $SSH_USER@${params.SERVER} \
                        'echo "Connected to Apigee Server"'
                        """
                    }
                }
            }
        }
        stage('Switch to Apigee User') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: CREDENTIALS_ID, usernameVariable: 'SSH_USER', passwordVariable: 'SSH_PASS')]) {
                        sh """
                        sshpass -p "$SSH_PASS" ssh -o StrictHostKeyChecking=no $SSH_USER@${params.SERVER} \
                        'echo "$SSH_PASS" | sudo -S su - apigee -c "echo Switched to Apigee User"'
                        """
                    }
                }
            }
        }
        stage('Perform Operation') {
            when {
                expression { params.OPERATION != 'Status' }
            }
            steps {
                script {
                    if (params.OPERATION == 'Stop') {
                        withCredentials([usernamePassword(credentialsId: CREDENTIALS_ID, usernameVariable: 'SSH_USER', passwordVariable: 'SSH_PASS')]) {
                            sh """
                            sshpass -p "$SSH_PASS" ssh -o StrictHostKeyChecking=no $SSH_USER@${params.SERVER} \
                            'echo "$SSH_PASS" | sudo -S su - apigee -c "apigee-all stop"'
                            """
                        }
                    } else if (params.OPERATION == 'Start') {
                        withCredentials([usernamePassword(credentialsId: CREDENTIALS_ID, usernameVariable: 'SSH_USER', passwordVariable: 'SSH_PASS')]) {
                            sh """
                            sshpass -p "$SSH_PASS" ssh -o StrictHostKeyChecking=no $SSH_USER@${params.SERVER} \
                            'echo "$SSH_PASS" | sudo -S su - apigee -c "apigee-all start"'
                            """
                        }
                    } else if (params.OPERATION == 'Restart') {
                        withCredentials([usernamePassword(credentialsId: CREDENTIALS_ID, usernameVariable: 'SSH_USER', passwordVariable: 'SSH_PASS')]) {
                            sh """
                            sshpass -p "$SSH_PASS" ssh -o StrictHostKeyChecking=no $SSH_USER@${params.SERVER} \
                            'echo "$SSH_PASS" | sudo -S su - apigee -c "apigee-all stop"'
                            """
                        }
                        withCredentials([usernamePassword(credentialsId: CREDENTIALS_ID, usernameVariable: 'SSH_USER', passwordVariable: 'SSH_PASS')]) {
                            sh """
                            sshpass -p "$SSH_PASS" ssh -o StrictHostKeyChecking=no $SSH_USER@${params.SERVER} \
                            'echo "$SSH_PASS" | sudo -S su - apigee -c "apigee-all start"'
                            """
                        }
                    }
                }
            }
        }
        stage('Check Apigee Status') {
            when {
                expression { params.OPERATION == 'Status' || params.OPERATION == 'Start' || params.OPERATION == 'Restart' }
            }
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: CREDENTIALS_ID, usernameVariable: 'SSH_USER', passwordVariable: 'SSH_PASS')]) {
                        sh """
                        sshpass -p "$SSH_PASS" ssh -o StrictHostKeyChecking=no $SSH_USER@${params.SERVER} \
                        'echo "$SSH_PASS" | sudo -S su - apigee -c "apigee-all status"'
                        """
                    }
                }
            }
        }
    }
    post {
        always {
            echo "Pipeline execution completed."
        }
    }
}
