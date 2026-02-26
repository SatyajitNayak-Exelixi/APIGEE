# Apigee EDGE (On-Prem) – Developer App Credential Update

This document explains how to create or update Developer App credentials (consumer key and consumer secret) in **Apigee EDGE On-Prem** using a CURL command with Basic Authentication.

---

## 🔐 Credential Creation / Update Command

Use the following CURL command to create new credentials for a Developer App:

```bash
curl --location 'https://10.22.103.199/v1/organizations/exelixi/developers/appvims@exelixi.com/apps/APPVIMS-Identity/keys/create' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic c2F0eWFqaXQubmF5YWtAaW5ncmFtbWljcm8uY29tOk1hbXVuaUBSYWh1bEA1NDMyMQ==' \
--data '{
    "consumerKey": "APPVIMS",
    "consumerSecret": "APPVIMS12345"
}'
```

---

## 🔎 Explanation

* `https://10.22.103.199` → Apigee EDGE On-Prem Management Server IP
* `organizations/exelixi` → Organization name
* `developers/{email}` → Developer email ID
* `apps/{app-name}` → Developer App name
* `keys/create` → Endpoint to create new credentials
* `Authorization: Basic` → Base64 encoded username and password

---

## 🧠 Important Notes

* This command creates new credentials for the Developer App.
* The credentials will be associated with API Products already linked to the app.
* Always protect Basic Auth credentials and avoid exposing them in shared documents.
* Consider revoking old keys if rotating credentials for security reasons.

---

✅ This method is used in Apigee EDGE On-Prem environments where Basic Authentication is required instead of OAuth tokens.
