# 🎯 Apigee `RegularExpressionProtection` Policy

> 🚀 Secure your API by blocking malicious patterns and ensuring data integrity with Apigee's **`RegularExpressionProtection`** policy.

---

## 🎨 **What is `RegularExpressionProtection`?**
The **`RegularExpressionProtection`** policy in Apigee helps safeguard your APIs by blocking malicious or risky regular expressions that could result in security vulnerabilities, such as **Denial of Service (DoS)** attacks or injection attacks (e.g., **XSS** or **SQL injection**). This policy scans requests and blocks those that contain malicious patterns in their bodies, query parameters, or headers.

---

## 🔹 **Basic Usage of `RegularExpressionProtection`**

This example demonstrates the usage of the `RegularExpressionProtection` policy to block common malicious patterns like **XSS** and **SQL injections**.

```xml
<RegularExpressionProtection name="RegExProtection">
  <FaultRules>
    <FaultRule name="InvalidPattern">
      <Condition>request.queryparam.regex_match == "true"</Condition>
      <Response>
        <StatusCode>400</StatusCode>
        <Message>🚫 Request contains invalid patterns.</Message>
      </Response>
    </FaultRule>
  </FaultRules>
  <Patterns>
    <Pattern>.*<script>.*</Pattern> <!-- Block XSS Script tags -->
    <Pattern>.*--.*</Pattern> <!-- Block SQL Injection -->
  </Patterns>
</RegularExpressionProtection>
```

✅ **What this does:**
- Blocks requests containing **XSS** attack vectors (`<script>` tags).
- Blocks SQL injection attempts using `--`.

---

## 🛠 **Validating Patterns in XML, JSON, and Form Payloads**

The `RegularExpressionProtection` policy can validate different content types like **XML**, **JSON**, and **form parameters**. Here's how:

### 📌 **1. XML Payload Validation**
```xml
<RegularExpressionProtection name="XMLPayloadRegex">
  <Patterns>
    <Pattern>.*<script>.*</Pattern> <!-- Block XSS -->
  </Patterns>
  <Condition>request.body.content-type == "application/xml"</Condition>
  <Response>
    <StatusCode>400</StatusCode>
    <Message>🚨 XML Payload contains invalid pattern.</Message>
  </Response>
</RegularExpressionProtection>
```

### 📌 **2. JSON Payload Validation**
```xml
<RegularExpressionProtection name="JSONPayloadRegex">
  <Patterns>
    <Pattern>.*<script>.*</Pattern> <!-- Block XSS -->
  </Patterns>
  <Condition>request.body.content-type == "application/json"</Condition>
  <Response>
    <StatusCode>400</StatusCode>
    <Message>🚨 JSON Payload contains invalid pattern.</Message>
  </Response>
</RegularExpressionProtection>
```

### 📌 **3. Form Parameters Validation**
```xml
<RegularExpressionProtection name="FormParamRegex">
  <Patterns>
    <Pattern>.*<script>.*</Pattern> <!-- Block XSS -->
  </Patterns>
  <Condition>request.body.content-type == "application/x-www-form-urlencoded"</Condition>
  <Response>
    <StatusCode>400</StatusCode>
    <Message>⚠️ Form parameters contain invalid pattern.</Message>
  </Response>
</RegularExpressionProtection>
```

---

## 🌍 **Real-time Scenario: Content Filtering in E-commerce Contact Forms**

Imagine you have an **e-commerce platform** with a **contact form** where users submit inquiries. To prevent **malicious scripts** or **SQL injections**, use the `RegularExpressionProtection` policy:

```xml
<RegularExpressionProtection name="ContactFormRegexProtection">
  <Patterns>
    <Pattern>.*<script>.*</Pattern> <!-- Block XSS -->
    <Pattern>.*DROP.*TABLE.*</Pattern> <!-- Block SQL Injection -->
  </Patterns>
  <Condition>request.body.content-type == "application/x-www-form-urlencoded"</Condition>
  <Response>
    <StatusCode>400</StatusCode>
    <Message>⚠️ Your message contains invalid content.</Message>
  </Response>
</RegularExpressionProtection>
```

🔹 **What This Does:**
- **Blocks** submissions containing **XSS** attack patterns.
- **Prevents** SQL injection attacks like `DROP TABLE`.

---

## ✅ **Summary**

🚀 The `RegularExpressionProtection` policy is a crucial tool for securing your APIs. By filtering out malicious patterns in **XML**, **JSON**, and **form data**, it ensures that your backend remains safe from common threats like **XSS** and **SQL injections**.

---

## 🔑 **Key Takeaways:**
- 🛡️ Use **RegularExpressionProtection** to block harmful patterns.
- 📂 Configure it for **XML**, **JSON**, and **form data** payloads.
- 🔥 Protect your system from **XSS** and **SQL injection**.
- ✨ Customize it based on your application’s needs.

💡 Let me know if you need further customization! 🚀

