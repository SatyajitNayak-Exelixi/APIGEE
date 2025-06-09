# 🔐 OAuth 2.0 Policy in Apigee

## 📌 Overview
OAuth 2.0 is an industry-standard protocol for **authorization**, enabling secure API access without sharing credentials. In Apigee, OAuth 2.0 policies help manage token generation, validation, and enforcement.

## ⚡ OAuth 2.0 Policies in Apigee
Apigee provides two key OAuth policies:

1. **OAuthV2 GenerateAccessToken** – Generates access tokens.
2. **OAuthV2 VerifyAccessToken** – Validates access tokens before processing API requests.

### 🔧 **Example: Generating an Access Token**
```xml
<OAuthV2 name="Generate-Access-Token">
    <Operation>GenerateAccessToken</Operation>
    <ExpiresIn>3600</ExpiresIn> <!-- Token expires in 1 hour -->
    <SupportedGrantTypes>
        <GrantType>client_credentials</GrantType>
    </SupportedGrantTypes>
    <GrantType>request.queryparam.grant_type</GrantType>
    <GenerateResponse enabled="false"/>
</OAuthV2>
```

### 🔧 **Example: Verifying an Access Token**
```xml
<OAuthV2 name="Verify-Access-Token">
    <Operation>VerifyAccessToken</Operation>
</OAuthV2>
```

---

## 🔄 OAuth 2.0 Grant Types in Apigee
Apigee supports multiple OAuth 2.0 **grant types** for different use cases.

### **1️⃣ Authorization Code Grant**
- Used for **web applications** where users authenticate via a browser.
- The app gets an **authorization code**, then exchanges it for an **access token**.

🔧 **Example:**
```xml
<OAuthV2 name="AuthCode-Grant">
    <Operation>GenerateAccessToken</Operation>
    <GrantType>authorization_code</GrantType>
</OAuthV2>
```

✅ **Use Case:** Securely authenticating users via login pages.

---

### **2️⃣ Implicit Grant**
- Designed for **single-page applications (SPA)**.
- The token is returned **directly** without an authorization code.

✅ **Use Case:** Lightweight apps needing **fast authentication**.

---

### **3️⃣ Client Credentials Grant**
- Used when a **machine-to-machine** authentication is needed.
- No user interaction is required; the client app **authenticates itself**.

🔧 **Example:**
```xml
<OAuthV2 name="Client-Credentials-Grant">
    <Operation>GenerateAccessToken</Operation>
    <GrantType>client_credentials</GrantType>
</OAuthV2>
```

✅ **Use Case:** Internal microservices communicating securely.

---

### **4️⃣ Password Grant (Resource Owner Credentials)**
- Allows users to **provide their username & password** directly to the client application.
- The app exchanges the credentials for an **access token**.

🔧 **Example:**
```xml
<OAuthV2 name="Password-Grant">
    <Operation>GenerateAccessToken</Operation>
    <GrantType>password</GrantType>
</OAuthV2>
```

✅ **Use Case:** Trusted applications that directly handle user credentials.

---

### **5️⃣ Refresh Token Grant**
- Used to obtain a **new access token** without requiring user reauthentication.
- Improves **security & user experience**.

🔧 **Example:**
```xml
<OAuthV2 name="Refresh-Token-Grant">
    <Operation>GenerateAccessToken</Operation>
    <GrantType>refresh_token</GrantType>
</OAuthV2>
```

✅ **Use Case:** Mobile apps that need **persistent user sessions**.

---

## 🚀 How to Generate an OAuth 2.0 Token in Apigee
### **Step 1: Create an API Proxy with OAuthV2 Policy**
- Add the **OAuthV2 policy** to the proxy to handle token generation.

### **Step 2: Send a Request to Generate a Token**
You can use **cURL** or Postman to generate a token.

🔧 **Example cURL Request:**
```sh
curl -X POST \ 
  https://your-apigee-endpoint/oauth/token \ 
  -H "Content-Type: application/x-www-form-urlencoded" \ 
  -d "grant_type=password&client_id=your-client-id&client_secret=your-client-secret&username=user&password=pass"
```
✅ **Response:**
```json
{
    "access_token": "eyJhbGciOiJIUzI1...",
    "token_type": "Bearer",
    "expires_in": 3600
}
```

### **Step 3: Use the Token for API Requests**
Pass the generated token in the Authorization header:
```sh
curl -X GET \ 
  https://your-apigee-endpoint/api/resource \ 
  -H "Authorization: Bearer your-access-token"
```

---

## 🎯 **Conclusion**
✅ **OAuthV2 Policy** in Apigee allows secure API access via token management.
✅ Different **grant types** cater to various authentication needs.
✅ Ensures **secure, scalable, and flexible** API authentication.
✅ Allows dynamic token generation and validation for API security.

🚀 **Implement OAuth 2.0 in Apigee to build secure API ecosystems!** 🔥

