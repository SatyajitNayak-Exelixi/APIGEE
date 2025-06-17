# 🔍 ExtractVariables Policy in Apigee

## 📌 **Introduction**
The **ExtractVariables Policy** in Apigee is used to **extract specific values** from request or response messages and store them in variables. These values can be extracted from:

- JSON or XML payloads
- Query parameters
- Headers
- Regular expressions

This policy helps dynamically route traffic, validate data, or manipulate flow logic without custom code.

---

## 🎯 Real-Time Scenario – Candidate Routing Based on Profile Type

### 🔹 Use Case:

You receive a JSON request with candidate details. You want to **route the request to different backend targets** based on the `profileType` field.

- If `"profileType": "internal"` → Route to `https://662e3897a7dda1fa378c64d4.mockapi.io/api/v1/vendorcodes/LearningApigee`
- If `"profileType": "external"` → Route to `https://662e3897a7dda1fa378c64d4.mockapi.io/api/v1/vendorcodes/vendorcodes`

---

## 📤 Sample cURL Request with Payload

```bash
curl --location 'https://api-te.ingrammicro.com:443/famlanding/LearningApigee' \
--header 'SenderID: FAMLAnding' \
--header 'Authorization: Basic RmFtbGFuZGluZzpGYW1sYW5kaW5nMTIzNA==' \
--header 'Content-Type: application/json' \
--data-raw '{
  "candidateId": "CAND12345",
  "fullName": "Jack Sparrow",
  "email": "jack.sparrow@example.com",
  "experienceYears": 5,
  "skills": ["Apigee", "DevOps", "JavaScript", "React"],
  "location": "Bangalore",
  "profileType": "external",
  "status": "active"
}'
``` 
---

### **1️⃣ Extract profileType Using ExtractVariables Policy**
Extracts values from a **JSON request body**.

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<ExtractVariables name="EV-Extract-ProfileType">
    <JSONPayload>
        <Variable name="profileType">
            <JSONPath>$.profileType</JSONPath>
        </Variable>
    </JSONPayload>
    <Source>request</Source>
</ExtractVariables>
```
---

### **2️⃣ Routing Logic in ProxyEndpoint**
Route the Traffice to the specif target depends on the matching condition..

```xml
<RouteRule name="LearningApigee">
        <TargetEndpoint>LearningApigee</TargetEndpoint>
        <Condition>profileType = "external"</Condition>
    </RouteRule>
    <RouteRule name="LearningApigee-EXT">
        <TargetEndpoint>LearningApigee-EXT</TargetEndpoint>
        <Condition>profileType = "internal"</Condition>
    </RouteRule>
```
---


## 🚀 **Benefits of ExtractVariables Policy in Apigee**
✅ **Automates data extraction** – No need for additional backend processing.
✅ **Enhances API security** – Extracts and validates tokens, headers, or query params.
✅ **Improves API efficiency** – Reduces redundant processing steps.

---

## **Conclusion**
The **ExtractVariables Policy** in Apigee is an essential tool for extracting and storing key data dynamically. It simplifies API workflows by parsing JSON, XML, headers, query parameters, and regex patterns.

🚀 **Leverage ExtractVariables to enhance your API’s performance and flexibility!**

