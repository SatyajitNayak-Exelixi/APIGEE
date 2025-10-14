# 🔐 AccessEntity Policy in Apigee

## 📌 **Introduction**
The **AccessEntity Policy** in Apigee is used to **retrieve and validate entities** like API keys, OAuth tokens, and JWT tokens stored in Apigee’s internal data store. This policy helps in **authentication and authorization** of API requests.

---

## 🎯 **Real-Time Scenario**
### **Scenario:**
A client application sends an API request with an **API Key** in the request header. Your API proxy needs to **validate the API Key** before allowing access to backend resources.

### **Problem:**
- The backend service requires authentication before processing requests.
- The API should only allow valid API Key users.

### **Solution:**
- Use **AccessEntity Policy** to fetch and validate the API Key against Apigee’s internal data store.

---

## 🔄 **How AccessEntity Policy Works in Apigee**
1. **Extracts API Key or token from the request**.
2. **Validates the key/token** against Apigee’s data store.
3. **Allows or denies access** based on validation.

---

## 💡 **Examples of AccessEntity Policy in Apigee**

### **1️⃣ Validate API Key**
Retrieves and validates the API Key from Apigee’s data store.

```xml
<AccessEntity name="ValidateAPIKey">
    <APIKey ref="request.header.x-api-key"/>
</AccessEntity>
```
📌 **Use Case:** Checks if the API Key provided in the `x-api-key` header is valid.

---

### **2️⃣ Validate OAuth Token**
Retrieves and validates an OAuth access token.

```xml
<AccessEntity async="false" continueOnError="false" enabled="true" name="AE-GetCustomerVars">
  <DisplayName>AE-GetCustomerVars</DisplayName>
  <Properties/>
  <EntityIdentifier ref="apigee.client_id" type="consumerkey"/>
  <EntityType value="app"/>
</AccessEntity>
```
📌 **Use Case:** Extracts the `Authorization` header and validates the OAuth token.

---

### **3️⃣ Validate JWT Token**
Validates a JSON Web Token (JWT) stored in Apigee’s data store.

```xml
<AccessEntity name="ValidateJWT">
    <JWT ref="request.header.Authorization"/>
</AccessEntity>
```
📌 **Use Case:** Verifies the JWT token passed in the `Authorization` header.

---

## 🚀 **Benefits of AccessEntity Policy in Apigee**
✅ **Secure authentication** – Prevents unauthorized access to APIs.
✅ **Centralized validation** – Uses Apigee’s data store for verification.
✅ **Flexible implementation** – Works with API keys, OAuth tokens, and JWTs.

---

## **Conclusion**
The **AccessEntity Policy** in Apigee is a powerful tool for securing APIs by validating API Keys, OAuth tokens, and JWTs. It ensures only authorized requests can access backend services, enhancing security and performance.

🚀 **Use AccessEntity Policy to implement robust authentication in your Apigee API proxies today!**

