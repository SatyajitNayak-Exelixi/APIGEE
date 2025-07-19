pipeline {
    agent any
    parameters {
        string(name: 'COMMENTS', defaultValue: '', description: 'Enter the Change Request details')
        choice(name: 'BATCH', choices: ['Batch1', 'Batch2', 'Batch3'], description: 'Select the batch')
        choice(name: 'OPERATION', choices: ['Start', 'Stop'], description: 'Select the operation to perform')
    }
    environment {
        DEFAULT_CREDENTIALS = 'jenkins-to-apigee-credentials'
        EXT_CREDENTIALS = 'jenkins-to-apigee-ext-credentials'
    }
    stages {
        stage('Display Parameters') {
            steps {
                script {
                    echo "Comments: ${params.COMMENTS}"
                    echo "Selected Batch: ${params.BATCH}"
                    echo "Selected Operation: ${params.OPERATION}"
                }
            }
        }

        stage('Connect to Apigee Server') {
            steps {
                script {
                    def servers = []
                    def credentialsId = DEFAULT_CREDENTIALS

                    if (params.BATCH == 'Batch1') {
                        servers = ['uschezlapg4101']
                        credentialsId = EXT_CREDENTIALS
                    } else if (params.BATCH == 'Batch2') {
                        servers = ['uschizlapg3001', 'uschizlapg3002', 'uschizlapg3201', 'uschizlapg4001', 'uschizlapg4002', 'uschizlapg4003', 'uschizlapg4201']
                    } else if (params.BATCH == 'Batch3') {
                        servers = ['uschizlapg3202', 'uschizlapg4202', 'uschizlapg3003', 'uschizlapg4004']
                    }

                    servers.each { server ->
                        withCredentials([usernamePassword(credentialsId: credentialsId, usernameVariable: 'SSH_USER', passwordVariable: 'SSH_PASS')]) {
                            sh """
                            sshpass -p "$SSH_PASS" ssh -o StrictHostKeyChecking=no $SSH_USER@$server \
                            'echo "Connected to Apigee Server"'
                            """
                        }
                    }
                }
            }
        }

        stage('Switch to Apigee User') {
            steps {
                script {
                    def servers = []
                    def credentialsId = DEFAULT_CREDENTIALS

                    if (params.BATCH == 'Batch1') {
                        servers = ['uschezlapg4101']
                        credentialsId = EXT_CREDENTIALS
                    } else if (params.BATCH == 'Batch2') {
                        servers = ['uschizlapg3001', 'uschizlapg3002', 'uschizlapg3201', 'uschizlapg4001', 'uschizlapg4002', 'uschizlapg4003', 'uschizlapg4201']
                    } else if (params.BATCH == 'Batch3') {
                        servers = ['uschizlapg3202', 'uschizlapg4202', 'uschizlapg3003', 'uschizlapg4004']
                    }

                    servers.each { server ->
                        withCredentials([usernamePassword(credentialsId: credentialsId, usernameVariable: 'SSH_USER', passwordVariable: 'SSH_PASS')]) {
                            sh """
                            sshpass -p "$SSH_PASS" ssh -o StrictHostKeyChecking=no $SSH_USER@$server \
                            'echo "$SSH_PASS" | sudo -S su - apigee -c "echo Switched to Apigee User"'
                            """
                        }
                    }
                }
            }
        }

        stage('Perform Operation') {
            steps {
                script {
                    def servers = []
                    def credentialsId = DEFAULT_CREDENTIALS
                    def skipValidationServers = ['uschizlapg3202', 'uschizlapg4202']

                    if (params.BATCH == 'Batch1') {
                        servers = ['uschezlapg4101']
                        credentialsId = EXT_CREDENTIALS
                    } else if (params.BATCH == 'Batch2') {
                        servers = ['uschizlapg3001', 'uschizlapg3002', 'uschizlapg3201', 'uschizlapg4001', 'uschizlapg4002', 'uschizlapg4003', 'uschizlapg4201']
                    } else if (params.BATCH == 'Batch3') {
                        servers = ['uschizlapg3202', 'uschizlapg4202', 'uschizlapg3003', 'uschizlapg4004']
                    }
                     
                    if (params.BATCH == 'Batch3' && params.OPERATION == 'Start') {
                        def validationServers = ['uschizlapg3202', 'uschizlapg4202']
                        validationServers.each { server ->
                            withCredentials([usernamePassword(credentialsId: DEFAULT_CREDENTIALS, usernameVariable: 'SSH_USER', passwordVariable: 'SSH_PASS')]) {
                                echo "Validating server: ${server}"

                                sh """
                                sshpass -p "$SSH_PASS" ssh -o StrictHostKeyChecking=no $SSH_USER@$server \
                                'echo "$SSH_PASS" | sudo -S su - apigee -c "apigee-all status"'
                                """

                                sh """
                                sshpass -p "$SSH_PASS" ssh -o StrictHostKeyChecking=no $SSH_USER@$server \
                                'echo "$SSH_PASS" | sudo -S su - apigee -c "ps -ef | grep sql | grep -v grep || echo No SQL process found"'
                                """
                            }
                        }
                    }

                    servers.each { server ->
                        withCredentials([usernamePassword(credentialsId: credentialsId, usernameVariable: 'SSH_USER', passwordVariable: 'SSH_PASS')]) {
                            if (skipValidationServers.contains(server) && (params.OPERATION == 'Stop' || params.OPERATION == 'Start')) {
                                echo "Skipping server ${server} for '${params.OPERATION}' operation"
                            } else if (params.OPERATION == 'Stop') {
                                sh """
                                sshpass -p "$SSH_PASS" ssh -o StrictHostKeyChecking=no $SSH_USER@$server \
                                'echo "$SSH_PASS" | sudo -S su - apigee -c "apigee-all stop"'
                                """
                            } else if (params.OPERATION == 'Start') {
                                sh """
                                sshpass -p "$SSH_PASS" ssh -o StrictHostKeyChecking=no $SSH_USER@$server \
                                'echo "$SSH_PASS" | sudo -S su - apigee -c "apigee-all start"'
                                """
                            }
                        }
                    }
                }
            }
        }

        stage('Check Apigee Status') {
            when {
                expression { params.OPERATION == 'Start' }
            }
            steps {
                script {
                    def servers = []
                    def credentialsId = DEFAULT_CREDENTIALS

                    if (params.BATCH == 'Batch1') {
                        servers = ['uschezlapg4101']
                        credentialsId = EXT_CREDENTIALS
                    } else if (params.BATCH == 'Batch2') {
                        servers = ['uschizlapg3001', 'uschizlapg3002', 'uschizlapg3201', 'uschizlapg4001', 'uschizlapg4002', 'uschizlapg4003', 'uschizlapg4201']
                    } else if (params.BATCH == 'Batch3') {
                        servers = ['uschizlapg3003', 'uschizlapg4004', 'uschizlapg3202', 'uschizlapg4202']
                    }

                    servers.each { server ->
                        withCredentials([usernamePassword(credentialsId: credentialsId, usernameVariable: 'SSH_USER', passwordVariable: 'SSH_PASS')]) {
                            sh """
                            sshpass -p "$SSH_PASS" ssh -o StrictHostKeyChecking=no $SSH_USER@$server \
                            'echo "$SSH_PASS" | sudo -S su - apigee -c "apigee-all status"'
                            """
                        }
                    }
                }
            }
        }
    }
    post {
        always {
            echo "Pipeline execution completed."
            script {
                if (params.BATCH == 'Batch3' && params.OPERATION == 'Stop') {
                    echo "Please ask DB Team to stop PG and SQL on uschizlapg3202 and uschizlapg4202 server."
                }
            }
        }
    }
}