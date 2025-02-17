# 🚀 Apigee XML & JSON Threat Protection Policy

> 🛡️ Secure your APIs from malicious payloads with **XML Threat Protection** and **JSON Threat Protection** in Apigee.

---

## 🎯 **What is XML & JSON Threat Protection?**

Apigee provides built-in **XMLThreatProtection** and **JSONThreatProtection** policies to prevent various types of attacks, such as:
- **XML External Entity (XXE) Attacks**
- **Recursive Entity Expansion (Billion Laughs Attack)**
- **Massive JSON Object Payloads**
- **Denial of Service (DoS) through deeply nested structures**

These policies help in **validating payload size, element depth, and node count** to ensure secure API processing. ✅

---

## 📌 **1. XML Threat Protection**

**XML Threat Protection** prevents malicious XML payloads from overwhelming or exploiting an API.

### 🛠 **Example Policy for XML Threat Protection**

```xml
<XMLThreatProtection name="XMLSecurityPolicy">
    <MaxDepth>10</MaxDepth> <!-- Prevents deep XML nesting -->
    <MaxElementCount>500</MaxElementCount> <!-- Limits total XML elements -->
    <MaxAttributeCount>10</MaxAttributeCount> <!-- Restricts attributes per element -->
    <MaxNamespaceCount>5</MaxNamespaceCount> <!-- Restricts namespace usage -->
    <MaxNodeValueLength>1000</MaxNodeValueLength> <!-- Restricts node text length -->
    <FaultResponse>
        <StatusCode>400</StatusCode>
        <Message>🚨 XML Payload violates security rules.</Message>
    </FaultResponse>
</XMLThreatProtection>
```

### 🔥 **Real-time Scenario: Preventing XXE Attacks**
Imagine you have a **banking API** that accepts XML requests for account details. Attackers can exploit XML processing to read sensitive files from the server (e.g., `/etc/passwd`).

#### 🚨 **Attack Example**
```xml
<?xml version="1.0"?>
<!DOCTYPE foo [ <!ENTITY xxe SYSTEM "file:///etc/passwd"> ]>
<request>
    <account>&xxe;</account>
</request>
```
✅ The **XMLThreatProtection** policy **blocks** such malicious payloads.

---

## 📌 **2. JSON Threat Protection**

**JSON Threat Protection** prevents **overly large, deeply nested, or malformed JSON payloads** from causing API disruptions.

### 🛠 **Example Policy for JSON Threat Protection**

```xml
<JSONThreatProtection name="JSONSecurityPolicy">
    <MaxDepth>5</MaxDepth> <!-- Restricts JSON nesting depth -->
    <MaxElementCount>200</MaxElementCount> <!-- Limits the number of elements -->
    <MaxNodeValueLength>500</MaxNodeValueLength> <!-- Restricts string length -->
    <FaultResponse>
        <StatusCode>400</StatusCode>
        <Message>🚨 JSON Payload exceeds security limits.</Message>
    </FaultResponse>
</JSONThreatProtection>
```

### 🔥 **Real-time Scenario: Preventing DoS with Large JSON Objects**
Consider an **e-commerce API** where users submit cart details. Attackers may send **massive JSON payloads** to overload the system.

#### 🚨 **Attack Example**
```json
{
  "cart": [
    {"item": "product1", "quantity": 10000},
    {"item": "product2", "quantity": 20000},
    {"item": "product3", "quantity": 30000}
  ]
}
```
✅ The **JSONThreatProtection** policy ensures that such **excessive payloads** are **blocked** before reaching backend services.

---

## ✅ **Summary**

🔹 **XMLThreatProtection** and **JSONThreatProtection** policies prevent security threats by restricting **payload size, depth, and structure**.

🔹 **Common threats mitigated:**
- 🛡️ **XML Entity Expansion Attacks (XXE)**
- 🛡️ **Large JSON Payload DoS Attacks**
- 🛡️ **Deeply Nested Object Attacks**

🔹 **Apigee’s Threat Protection ensures APIs remain**:
✅ **Secure** | ✅ **Optimized** | ✅ **High-performing**

💡 Let me know if you need further customization! 🚀

