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

## 📌 **1. JSON Threat Protection**

**JSON Threat Protection** prevents **overly large, deeply nested, or malformed JSON payloads** from causing API disruptions.

### 🛠 **Example Policy for JSON Threat Protection**

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<JSONThreatProtection async="false" continueOnError="false" enabled="true" name="JSON-Threat-Protection-1">
    <DisplayName>JSON Threat Protection-1</DisplayName>
    <Properties/>
    <ArrayElementCount>2</ArrayElementCount>
    <ContainerDepth>2</ContainerDepth>
    <ObjectEntryCount>10</ObjectEntryCount>
    <ObjectEntryNameLength>10</ObjectEntryNameLength>
    <Source>request</Source>
    <StringValueLength>50</StringValueLength>
</JSONThreatProtection>
```

**How to Test:**

```
curl --location 'https://<HostName>/demoapi' \
--header 'Content-Type: application/json' \
--data '{
  "a":{"b":{"c":{"d":{"e":{"f":"too deep"}}}}}
}'
```

```
curl --location 'https://<HostName>/demoapi' \
--header 'Content-Type: application/json' \
--data '{
  "field": "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
}'
```

---

✅ The **JSONThreatProtection** policy ensures that such **excessive payloads** are **blocked** before reaching backend services.

---


## 📌 **2. XML Threat Protection**

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
---
✅ The **XMLThreatProtection** policy **blocks** such malicious payloads.




