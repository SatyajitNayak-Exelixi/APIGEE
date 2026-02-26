# Apigee X – Developer App Credential Creation

This document explains how to create or rotate credentials (consumer key and consumer secret) for a **Developer App** that is associated with an API Product in Apigee X.

---

## 🔐 Step 1: Generate Access Token

Before making any Apigee management API calls, generate an OAuth access token:

```bash
export TOKEN=$(gcloud auth print-access-token)
```

This token will be used in the `Authorization` header.

---

## ➕ Step 2: Create New Credentials for Developer App

Use the following command to create a new `consumerKey` and `consumerSecret` for a Developer App.

```bash
curl -X POST \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{"consumerKey":"test","consumerSecret":"test"}' \
  https://apigee.googleapis.com/v1/organizations/org-xyz789abc/developers/vijay.pappireddy@gmail.com/apps/Apigee-identity/keys/create
```

### 🔎 Explanation

* `organizations/org-xyz789abc` → Your Apigee organization ID
* `developers/{email}` → Developer email
* `apps/{app-name}` → Developer App name
* `keys/create` → Endpoint to create new credentials

This command generates new credentials that will be linked to the API Products associated with the Developer App.

---

## 🔍 Step 3: Verify Stored Credentials (If Stored in KVM)

If you are storing credentials in a Key Value Map (KVM), you can verify them using:

```bash
curl -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  "https://apigee.googleapis.com/v1/organizations/org-xyz789abc/environments/eval/keyvaluemaps/USITIMAppEAI/entries"
```

This retrieves all entries stored inside the `USITIMAppEAI` KVM.

---

## 🧠 Notes

* Developer App credentials are used for API key validation and OAuth flows.
* Rotating credentials improves security.
* Ensure the Developer App is properly associated with the required API Products.
* Avoid hardcoding credentials inside proxies or source code.

---

✅ This approach helps manage and rotate Developer App credentials securely in Apigee X.
