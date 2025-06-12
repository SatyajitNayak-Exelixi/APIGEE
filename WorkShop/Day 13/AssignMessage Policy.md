# ✉️ AssignMessage Policy in Apigee ✉️

## 📌 **Introduction**

The **AssignMessage Policy** in Apigee is used to **modify API request and response messages dynamically**. This policy allows you to **set headers, query parameters, request payloads, and response bodies**, making it useful for customizing API behavior without changing backend services.

---

## 🎯 **Real-Time Scenario**

### **Scenario:**
Imagine you have an API that receives a request **without a required header**, but your backend service requires it.

### **Problem:**
- Clients sometimes **forget to send the required headers**.
- You need to **add default headers** to ensure successful API processing.

### **Solution:**
- Use the **AssignMessage Policy** to add the missing headers before forwarding the request to the backend.

---

## 🔑 **How AssignMessage Policy Works in Apigee**

1. **Modify Request Data** – Add, remove, or modify headers, query parameters, and payloads.
2. **Customize Response Messages** – Modify the response before sending it back to the client.
3. **Set Variables** – Assign values to flow variables that can be used later in the API proxy.

---

## 💡 **Examples of AssignMessage Policy in Apigee**

### **1️⃣ Copy Example**
Copy everything from the **incoming request** into a new message.

```xml
<AssignMessage name="CopyRequest">
    <Copy source="request"/>
    <AssignTo createNew="true" type="request"/>
</AssignMessage>
```
📌 **Use Case:** This copies all headers, query parameters, and payload from the request to a new message.

---

### **2️⃣ Remove Example**
Remove specific headers, query parameters, and payload from the request.

```xml
<AssignMessage name="RemoveHeadersAndPayload">
    <Remove>
        <Headers>
            <Header name="Authorization"/>
        </Headers>
        <QueryParams>
            <QueryParam name="debug"/>
        </QueryParams>
        <Payload/>
    </Remove>
    <AssignTo createNew="false" type="request"/>
</AssignMessage>
```
📌 **Use Case:** Removes the `Authorization` header and `debug` query param before forwarding the request.

---

### **3️⃣ Add Example**
Add a new header and query parameter to the request.

```xml
<AssignMessage name="AddNewHeaderAndQueryParam">
    <Add>
        <Headers>
            <Header name="X-Transaction-ID">12345</Header>
        </Headers>
        <QueryParams>
            <QueryParam name="source">mobile</QueryParam>
        </QueryParams>
    </Add>
    <AssignTo createNew="false" type="request"/>
</AssignMessage>
```
📌 **Use Case:** Adds a transaction ID header and a `source=mobile` query parameter.

---

### **4️⃣ Set Example**
Modify existing headers and change the request path.

```xml
<AssignMessage name="SetHeadersAndPath">
    <Set>
        <Headers>
            <Header name="X-Correlation-ID">updated-id-98765</Header>
        </Headers>
        <Path>/new-api-endpoint</Path>
    </Set>
    <AssignTo createNew="false" type="request"/>
</AssignMessage>
```
📌 **Use Case:** Updates the `X-Correlation-ID` header and modifies the request path.

---

## 🚀 **Benefits of AssignMessage Policy in Apigee**

✅ **Flexibility** – Modify API messages without backend changes.
✅ **Improved API Usability** – Add default values for missing parameters.
✅ **Enhanced Security** – Mask sensitive data before sending responses.

---

## **Conclusion**

The **AssignMessage Policy** in Apigee helps customize API requests and responses dynamically. Whether you need to **add headers, change payloads, or modify responses**, this policy offers a powerful way to fine-tune your API behavior.



