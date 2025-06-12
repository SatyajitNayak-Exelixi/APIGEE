# 🔍 ExtractVariables Policy in Apigee

## 📌 **Introduction**
The **ExtractVariables Policy** in Apigee is used to **extract specific values from request or response messages** and store them in variables. This policy is useful when you need to **parse JSON, XML, query parameters, headers, or even regular expressions** from an API request/response.

---

## 🎯 **Real-Time Scenario**
### **Scenario:**
A client sends an API request containing a **JWT token** in the Authorization header, and your API needs to extract the **user ID** from the token.

### **Problem:**
- The backend service expects a **user ID** but receives a **JWT token** instead.
- You need to extract and pass the **user ID** dynamically.

### **Solution:**
- Use **ExtractVariables Policy** to parse the JWT token and extract the **user ID**.

---

## 🔄 **How ExtractVariables Policy Works in Apigee**
1. **Extracts data from JSON, XML, headers, query parameters, or regular expressions.**
2. **Stores extracted values in variables** for further processing.
3. **Enhances API performance** by reducing the need for additional backend calls.

---

## 💡 **Examples of ExtractVariables Policy in Apigee**

### **1️⃣ Extract from JSON Response**
Extracts values from a **JSON response body**.

```xml
<ExtractVariables name="ExtractFromJSON">
    <Source>response</Source>
    <JSONPayload>
        <Variable name="userId" type="string">
            <JSONPath>$.data.user.id</JSONPath>
        </Variable>
    </JSONPayload>
</ExtractVariables>
```
📌 **Use Case:** Extracts `userId` from the JSON response `{"data": {"user": {"id": "12345"}}}` and stores it in a variable.

---

### **2️⃣ Extract from XML Response**
Extracts values from an **XML response body**.

```xml
<ExtractVariables name="ExtractFromXML">
    <Source>response</Source>
    <XMLPayload>
        <Variable name="orderId" type="string" xpath="//Order/ID"/>
    </XMLPayload>
</ExtractVariables>
```
📌 **Use Case:** Extracts `orderId` from the XML `<Order><ID>98765</ID></Order>`.

---

### **3️⃣ Extract from Query Parameters**
Extracts query parameters from the request URL.

```xml
<ExtractVariables name="ExtractFromQueryParam">
    <Source>request</Source>
    <QueryParam name="transactionId"/>
</ExtractVariables>
```
📌 **Use Case:** Extracts `transactionId` from `https://api.example.com/orders?transactionId=abc123`.

---

### **4️⃣ Extract from Headers**
Extracts data from request headers.

```xml
<ExtractVariables name="ExtractFromHeader">
    <Source>request</Source>
    <Header name="Authorization" variable="authHeader"/>
</ExtractVariables>
```
📌 **Use Case:** Extracts the `Authorization` header value (e.g., `Bearer token123`).

---

### **5️⃣ Extract Using Regular Expressions**
Extracts specific patterns from a string.

```xml
<ExtractVariables name="ExtractUsingRegex">
    <Source>request</Source>
    <Variable name="clientId">
        <Pattern>client_id=([^&]+)</Pattern>
    </Variable>
</ExtractVariables>
```
📌 **Use Case:** Extracts `clientId` from `client_id=XYZ123&scope=read` using regex.

---

## 🚀 **Benefits of ExtractVariables Policy in Apigee**
✅ **Automates data extraction** – No need for additional backend processing.
✅ **Enhances API security** – Extracts and validates tokens, headers, or query params.
✅ **Improves API efficiency** – Reduces redundant processing steps.

---

## **Conclusion**
The **ExtractVariables Policy** in Apigee is an essential tool for extracting and storing key data dynamically. It simplifies API workflows by parsing JSON, XML, headers, query parameters, and regex patterns.

🚀 **Leverage ExtractVariables to enhance your API’s performance and flexibility!**

