# Accessing Vertex AI Resources Across GCP Projects from Apigee

This document describes the steps to configure an **Apigee API Proxy** deployed in one GCP project to securely access **Vertex AI resources** that are hosted in a **different GCP project**.

---

## Architecture Overview

* **Apigee Project**: Hosts the API proxy
* **Vertex AI Project**: Hosts the Vertex AI resources (Reasoning Engine)
* **Authentication**: Service Account–based, using Google Access Token

---

## Prerequisites

* Apigee X or Hybrid environment
* Vertex AI Reasoning Engine already created
* Required IAM permissions to create and manage service accounts

---

## Step 1: Configure Service Account During Proxy Deployment

While deploying the API proxy in **Apigee**, select the following service account:

```
vertexai@imgcp-20220525024015.iam.gserviceaccount.com
```

This service account will be used by Apigee to generate access tokens when calling Vertex AI APIs.

> ⚠️ Ensure this service account is allowed to impersonate and access resources in the Vertex AI project.

---

## Step 2: Configure Target Endpoint to Call Vertex AI

In the **TargetEndpoint** configuration of your Apigee proxy, add the following under the target URL definition:

```xml
<HTTPTargetConnection>
    <!-- Service Account used for Vertex AI access -->
    <!-- vertexai@imgcp-20220525024015.iam.gserviceaccount.com -->
    <URL>https://us-central1-aiplatform.googleapis.com/v1/projects/imgcp-234225262626/locations/us-central1/reasoningEngines/7726514498986573824:query</URL>
    <Authentication>
      <GoogleAccessToken>
        <Scopes>
          <Scope>https://www.googleapis.com/auth/cloud-platform</Scope>
        </Scopes>
      </GoogleAccessToken>
    </Authentication>
</HTTPTargetConnection>
```

This configuration enables Apigee to:

* Generate a Google access token at runtime
* Authenticate requests to the Vertex AI REST API

---

## Step 3: Grant IAM Permissions in the Vertex AI Project

In the **Vertex AI project**, ensure the service account has the required permissions.

### 3.1 Create or Identify Service Account

Use the service account:

```
vertexai@imgcp-20220525024015.iam.gserviceaccount.com
```

(If not already created, create it in the Apigee project.)

### 3.2 Assign Required Role

Grant the following IAM role **in the Vertex AI project**:

```
roles/aiplatform.user
```

This role allows the service account to invoke Vertex AI endpoints such as Reasoning Engines.

---

## Validation

* Deploy the API proxy successfully with the configured service account
* Invoke the Apigee proxy endpoint
* Verify that the request reaches Vertex AI without authentication or permission errors

---

## Common Issues & Troubleshooting

* **403 Permission Denied**: Verify IAM role assignment in the Vertex AI project
* **401 Unauthorized**: Ensure `GoogleAccessToken` configuration is present in the target endpoint
* **Invalid Audience / Scope**: Confirm `cloud-platform` scope is used

---

## Summary

By using a dedicated service account and Google Access Token authentication, Apigee can securely access Vertex AI resources hosted in a different GCP project. This approach follows Google-recommended best practices for cross-project access.

---

