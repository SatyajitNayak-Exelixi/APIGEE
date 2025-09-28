# 🚀 Jenkins use in APIGEE.

## 📌 Introduction to Jenkins

Jenkins is an open-source automation server widely used in DevOps to automate the building, testing, and deployment of applications. It supports continuous integration (CI) and continuous delivery (CD), making it an essential tool in modern development pipelines.

---

## 🛠️ Daily Use Cases of Jenkins

### ✅ 1. Automated Build and Deployment

* Trigger builds on code check-in (e.g., Git).
* Build applications automatically.
* Deploy apps to test, staging, or production environments.

### ✅ 2. Scheduled Jobs

* Schedule regular backups.
* Schedule nightly builds.
* Cron-based testing jobs.

### ✅ 3. Monitoring & Reporting

* Generate test and code coverage reports.
* Notify teams via email/Slack on build status.

### ✅ 4. Integration with Tools

* Docker, Kubernetes
* GitHub, Bitbucket
* Slack, Email
* SonarQube, JIRA

---

## 🖥️ Jenkins Components Running on Linux Server

### 📁 Basic Setup Steps:

1. **Install Jenkins** via yum/apt or Docker.

2. **Configure Jenkins:**

   * Setup credentials
   * Install plugins (Git, Pipeline, Docker, etc.)

3. **Create Jobs:**

   * Freestyle or Pipeline jobs

4. **Connect Jenkins to Source Control**

   * Git/GitHub

5. **Trigger Builds**

   * Automatically (via webhook)
   * Manually
   * Scheduled (cron syntax)

---

## 🧪 Sample Jenkins Pipeline Using Groovy (Declarative)

```groovy
pipeline {
    agent any

    environment {
        IMAGE_NAME = 'my-app'
        CONTAINER_NAME = 'my-app-container'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/your-repo/my-app.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Docker Run') {
            steps {
                sh 'docker run -d --name $CONTAINER_NAME $IMAGE_NAME'
            }
        }
    }

    post {
        always {
            echo 'Cleaning up...'
            sh 'docker rm -f $CONTAINER_NAME || true'
        }
    }
}
```

---

## 📘 Tips

* Use **credentials plugin** to securely store secrets.
* Use **shared libraries** to reuse Groovy code.
* Keep pipelines in version control using `Jenkinsfile`.
* Set up **agent labels** to manage Linux node execution.

---

## 📎 References

* [Jenkins Documentation](https://www.jenkins.io/doc/)
* [Pipeline Syntax](https://www.jenkins.io/doc/book/pipeline/syntax/)
* [Groovy Language Basics](https://groovy-lang.org/)

---
