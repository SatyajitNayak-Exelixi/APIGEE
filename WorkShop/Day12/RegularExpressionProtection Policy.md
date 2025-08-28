# 🎯 Apigee `RegularExpressionProtection` Policy

> 🚀 Secure your API by blocking malicious patterns and ensuring data integrity with Apigee's **`RegularExpressionProtection`** policy.

---

## 🎨 **What is `RegularExpressionProtection`?**
The **`RegularExpressionProtection`** policy in Apigee helps safeguard your APIs by blocking malicious or risky regular expressions that could result in security vulnerabilities, such as **Denial of Service (DoS)** attacks or injection attacks (e.g., **XSS** or **SQL injection**). This policy scans requests and blocks those that contain malicious patterns in their bodies, query parameters, or headers.

---

## 🔹 **Basic Usage of `RegularExpressionProtection`**

The RegularExpressionProtection policy helps secure your API by blocking requests that contain malicious patterns, such as SQL injection or XSS attempts.

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<RegularExpressionProtection async="false" continueOnError="false" enabled="true" name="RegexProtection">
    <DisplayName>RegexProtection</DisplayName>
    <Source>request</Source>
    <!-- Block attacks in URI path -->
    <URIPath>
        <Pattern><![CDATA[(?i)(delete|exec|drop\s*table|insert|update|shutdown|\bor\b)]]></Pattern>
    </URIPath>
    <!-- Block attacks in query string -->
    <Variable name="request.querystring">
        <Pattern><![CDATA[(?i)(delete|exec|drop\s*table|insert|update|shutdown|\bor\b)]]></Pattern>
    </Variable>
    <!-- Block attacks in form body -->
    <Variable name="request.formstring">
        <Pattern><![CDATA[(?i)(delete|exec|drop\s*table|insert|update|shutdown|\bor\b)]]></Pattern>
    </Variable>
</RegularExpressionProtection>
```

✅ **What this does:**
🚫 Blocks requests with SQL injection keywords (e.g., DROP TABLE, DELETE, UPDATE, etc.) in the URI, query string, or form parameters.
🚫 Prevents basic XSS attempts such as <script> tags if you extend the regex to include them.
✅ Ensures only clean traffic is passed through to the backend.

---

**How to Test this:**

```
https://<Hostname>/deleteUser
https://<Hostname>/demoapi?name=insert
```

---

