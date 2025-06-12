# 🔑 Key Value Map (KVM) Operations Policy in Apigee

## 📌 **Introduction**
Key Value Map (KVM) in **Apigee** is a secure way to **store and retrieve key-value pairs** that can be used across API proxies. It helps in managing sensitive information, such as **API keys, secrets, or configurations**, without hardcoding them in the policies.

---

## 🎯 **Real-Time Scenario**
### **Scenario:**
You have an API that needs to fetch **client-specific configuration** values (such as rate limits or feature flags) based on the request.

### **Problem:**
- Hardcoding values in policies makes maintenance difficult.
- Sensitive data like API keys should not be exposed in code.

### **Solution:**
- Store configurations securely in **KVM** and retrieve them dynamically during API execution using the **KeyValueMapOperations policy**.

---

## 🔄 **How Key Value Map Operations Work in Apigee**

1. **Storing Data** – Add key-value pairs to a named KVM.
2. **Retrieving Data** – Fetch values dynamically during API execution.
3. **Deleting Data** – Remove keys when they are no longer needed.

---

## 💡 **Examples of Key Value Map Operations in Apigee**

### **1️⃣ Create and Store Key-Value Pairs**
Use this policy to **store values** in KVM.

```xml
<KeyValueMapOperations name="KVM-Store">
    <Scope>environment</Scope>
    <Put>
        <Key>client-rate-limit</Key>
        <Value>100</Value>
    </Put>
</KeyValueMapOperations>
```
📌 **Use Case:** Stores the value `100` for the key `client-rate-limit`.

---

### **2️⃣ Retrieve Value from KVM**
Use this policy to **retrieve values** at runtime.

```xml
<KeyValueMapOperations name="KVM-Retrieve">
    <Scope>environment</Scope>
    <Get assignTo="clientRateLimit">
        <Key>client-rate-limit</Key>
    </Get>
</KeyValueMapOperations>
```
📌 **Use Case:** Fetches `client-rate-limit` and assigns it to a variable `clientRateLimit`.

---

### **3️⃣ Remove a Key from KVM**
Use this policy to **delete stored values**.

```xml
<KeyValueMapOperations name="KVM-Delete">
    <Scope>environment</Scope>
    <Remove>
        <Key>client-rate-limit</Key>
    </Remove>
</KeyValueMapOperations>
```
📌 **Use Case:** Deletes the key `client-rate-limit` from KVM.

---

## 🚀 **Benefits of KVM in Apigee**
✅ **Security** – Store sensitive data like API keys securely.
✅ **Flexibility** – Manage configurations without redeploying proxies.
✅ **Scalability** – Store and retrieve values dynamically across multiple APIs.

---

## **Conclusion**
The **Key Value Map (KVM) Operations Policy** in Apigee is a powerful feature for securely storing and retrieving configuration data. Implementing KVM ensures that your API proxies remain **flexible, secure, and easy to manage**.

🚀 **Use KVM to enhance API security and configuration management in Apigee today!**

