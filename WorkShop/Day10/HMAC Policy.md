# 🛡️ **HMAC Policy in Apigee: Overview and Implementation** 🛡️

## 🌟 **Introduction**

HMAC (Hash-based Message Authentication Code) is used in **Apigee** to ensure the **integrity** and **authenticity** of API requests. By using HMAC, you can verify that the request data has not been tampered with and that the request comes from a trusted source.

---

## 🎯 **Real-Time Scenario**

### **Scenario:**

Imagine you're building an **API** that processes sensitive transactions, such as **payments**. You need to make sure that the data hasn't been tampered with during transit and that it originated from a **trusted source**.

### **Problem:**
- **Unauthorized Access:** You want to make sure that only trusted systems can interact with your API.
- **Data Integrity:** Sensitive data must remain unaltered during transmission.

### **Solution:**
- **HMAC** ensures the authenticity of requests by generating a unique signature for each request, which can be verified on the server side.

---

## 🔑 **How HMAC Policy Works in Apigee**

1. **Client Side:**
   - The client **signs** the request using a **shared secret key**.
   - The **HMAC signature** is included in the request headers (usually in `X-Signature`).

2. **Server Side (Apigee):**
   - Apigee's **HMAC policy** verifies the signature by recalculating it and checking if it matches the one sent by the client.
   - If the signatures match, the request is accepted. Otherwise, it is rejected.

---

## 💡 **Example of HMAC Policy in Apigee**

### Step 1: **Create the HMAC Policy in Apigee**

In your Apigee API Proxy, you can create an HMAC policy like this:

```xml
<HMAC name="HMAC-Verification">
  <Algorithm>sha256</Algorithm>
  <Key>secret-key</Key> <!-- Shared secret key -->
  <Source>request.headers.X-Signature</Source> <!-- Incoming signature -->
  <Target>request.headers.X-Signature</Target> <!-- Expected signature -->
</HMAC>
```

---

## **HMAC Policy in Apigee**

### **Overview**
HMAC (Hash-based Message Authentication Code) policy in Apigee is used to ensure **data integrity and authentication** by verifying a message's authenticity using a **shared secret key**. This policy helps protect against tampering and unauthorized modifications.

---

## **Client-Side Implementation (Generating HMAC Signature in Python)**

```python
import hashlib
import hmac
import base64

secret_key = b'secret123'
message = b'{"amount": 1000, "currency": "USD"}'

signature = hmac.new(secret_key, message, hashlib.sha256).digest()
encoded_signature = base64.b64encode(signature).decode()

print("HMAC Signature:", encoded_signature)
```

The **generated signature** is included in the request header:
```http
Authorization: HMAC {encoded_signature}
```

---

## **Apigee HMAC Policy to Validate Signature**

```xml
<HMAC name="Verify-HMAC">
    <Algorithm>SHA-256</Algorithm>
    <Message>{request.content}</Message>
    <SecretKey ref="private.secretKey"/>
    <OutputEncoding>BASE64</OutputEncoding>
</HMAC>
```

### **Explanation:**
- Uses **SHA-256** hashing algorithm.
- Extracts the request **payload** (`{request.content}`).
- Uses **SecretKey** stored in Apigee.
- Encodes output as **Base64** to match client-generated HMAC.

If the computed HMAC **matches** the client's HMAC, the request is **trusted**; otherwise, it is **rejected**.

---

## **Benefits of HMAC in Apigee**
✅ **Ensures Message Integrity** – Prevents tampering during transit.
✅ **Enhances Security** – Only those with the secret key can generate valid signatures.
✅ **Lightweight & Fast** – Minimal overhead compared to other authentication methods.

---

## **Conclusion**
HMAC policy in Apigee is a powerful way to secure API requests by ensuring message authenticity. It is widely used in **payment gateways, banking APIs, and secure data transmissions** where integrity is crucial.

🚀 **Implement HMAC today to make your APIs more secure!**

