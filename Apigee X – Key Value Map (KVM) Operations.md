# Apigee X – Key Value Map (KVM) Operations

This document explains how to create, verify, and retrieve **Key Value Maps (KVMs)** in Apigee X using `curl` commands and a Google Cloud access token.

---

## 🔐 Step 1: Generate Access Token

Before calling Apigee APIs, generate an OAuth token using gcloud:

```bash
export TOKEN=$(gcloud auth print-access-token)
```

This token will be used in the `Authorization` header for all API requests.

---

## 📌 Step 2: List All KVMs in an Environment

To check existing Key Value Maps in an environment:

```bash
curl "https://apigee.googleapis.com/v1/organizations/org-abc123xyz/environments/eval/keyvaluemaps" \
  -H "Authorization: Bearer $TOKEN"
```

This command returns all KVMs created in the specified environment (`eval`).

---

## ➕ Step 3: Create KVM Entries

### Create Username Entry

```bash
curl -X POST \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{"name":"username","value":"test"}' \
  https://apigee.googleapis.com/v1/organizations/org-abc123xyz/environments/eval/keyvaluemaps/Apigee/entries
```

### Create Password Entry

```bash
curl -X POST \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{"name":"password","value":"test"}' \
  https://apigee.googleapis.com/v1/organizations/org-abc123xyz/environments/eval/keyvaluemaps/Apigee/entries
```

These commands create two entries (`username` and `password`) inside the `Apigee` KVM.

---

## 🔎 Step 4: View Specific KVM Entries

To view entries inside a specific KVM:

```bash
curl -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  https://apigee.googleapis.com/v1/organizations/org-789def456ghi/environments/prodbeta4/keyvaluemaps/IngestionMasterLog/entries
```

This retrieves all key-value pairs stored inside the `IngestionMasterLog` KVM.

---

## 📋 Step 5: List All KVMs Again (Verification)

```bash
curl -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  "https://apigee.googleapis.com/v1/organizations/org-abc123xyz/environments/eval/keyvaluemaps"
```

This is useful to verify that the KVM exists after creation.

---

## 🧠 Notes

* KVMs are used to securely store configuration data such as usernames, passwords, tokens, and environment-specific values.
* Always avoid hardcoding sensitive credentials inside API proxies.
* Use environment-scoped KVMs for better separation between `dev`, `test`, and `prod` environments.
* Ensure the correct organization and environment names are used in each API call.

---

✅ This setup helps manage secure configuration data centrally in Apigee X.
