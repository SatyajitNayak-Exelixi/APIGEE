# 🔐 Basic Authentication Policy in Apigee

## 🚀 Overview
Basic Authentication in **Apigee** is used to validate API requests by encoding and decoding user credentials (username & password) into a **Base64-encoded** `Authorization` header.

---

## 🛠 Policy Configuration

### ✅ **Encoding Credentials**
This policy encodes username and password into a **Basic Auth Header**:

```xml
<BasicAuthentication continueOnError="false" enabled="true" name="Basic-Authentication-1">
    <DisplayName>Basic Authentication-1</DisplayName>
    <Operation>Encode</Operation>
    <User ref="request.queryparam.username"/>
    <Password ref="request.queryparam.password"/>
    <AssignTo createNew="false">request.header.Authorization</AssignTo>
</BasicAuthentication>
```

---

### ✅ **Decoding and Validating Credentials**
When an API request includes the following header:

```http
Authorization: Basic YWRtaW46c2VjdXJlMTIz
```

Apigee needs to:
1. Extract the token.
2. Decode it to retrieve the username and password.
3. Validate credentials.
4. Return a **401 Unauthorized** error if authentication fails.

```xml
<BasicAuthentication async="false" continueOnError="false" enabled="true" name="DecodeBasicAuthentication">
    <DisplayName>Decode Basic Authentication</DisplayName>
    <Operation>Decode</Operation>
    <User ref="request.header.username"/>
    <Password ref="request.header.password"/>
    <Source>request.header.Authorization</Source>
</BasicAuthentication>
```

This policy extracts and decodes credentials from the `Authorization` header.

---

### ⚡ **Handling Invalid Credentials (JavaScript Policy)**
If the decoded credentials are incorrect, return a **401 Unauthorized error**:

```javascript
var username = context.getVariable("request.header.username");
var password = context.getVariable("request.header.password");

if (!username || !password || username !== "admin" || password !== "secure123") {
    context.setVariable("response.status.code", 401);
    context.setVariable("response.reason.phrase", "Unauthorized");
    context.setVariable("response.content", JSON.stringify({ "error": "Invalid Credentials" }));
    throw "Unauthorized";
}
```

---

## 🔄 **How It Works**

1️⃣ **Client Request:**
```http
GET /api/resource HTTP/1.1
Host: api.example.com
Authorization: Basic YWRtaW46c2VjdXJlMTIz
```

2️⃣ **Apigee Decodes and Validates Credentials**

3️⃣ **Response Based on Validation:**
✅ **Success:** API processes the request.  
❌ **Failure:** API returns **401 Unauthorized**:
```json
{
  "error": "Invalid Credentials"
}
```

---

## 🏆 Advantages of Basic Authentication
✔ **Simple & Easy to Implement**
✔ **Works with Any HTTP Client**
✔ **Avoids Sending Plaintext Passwords**

---

## ⚠ Important Considerations
⚠ **Always Use HTTPS** – Prevents credentials from being intercepted.  
⚠ **Not Ideal for Sensitive APIs** – Consider OAuth2 for stronger security.  

---

## 🎯 Summary
This policy enables **secure authentication** using `BasicAuth`, ensuring only valid users access the API. For high-security environments, use **OAuth2** instead.

🚀 **Now you can confidently implement Basic Authentication in Apigee!** 🔥

