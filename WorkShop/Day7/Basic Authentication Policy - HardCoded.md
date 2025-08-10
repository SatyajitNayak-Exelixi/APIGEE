# 🔐 Apigee Basic Authentication — Full Flow Example

## 📘 Overview

Basic Authentication allows secure API access using a Base64-encoded `username:password` combination sent in the `Authorization` header. In this guide, we’ll walk through a complete working example in Apigee:

- 🔓 Decode the credentials
- 🧠 Validate using JavaScript
- ❌ Return 401 if invalid
- ✅ Allow request if valid

---

## 🔁 PreFlow Setup

The following `PreFlow` runs the policies in order:

```xml
<PreFlow name="PreFlow">
    <Request>
        <Step>
            <Name>DecodeBasicAuthentication</Name>
        </Step>
        <Step>
            <Name>JS-ValidateCredential</Name>
        </Step>
        <Step>
            <Name>RF-AuthError</Name>
            <Condition>Authentication-Failed = "true"</Condition>
        </Step>
    </Request>
    <Response/>
</PreFlow>
```

---

## :gear: **Step-by-Step Guide to Creating an API Proxy**

### **Step 1: BasicAuthentication Policy (Decode) : This policy decodes the Basic Auth header.**

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<BasicAuthentication async="false" continueOnError="false" enabled="true" name="DecodeBasicAuthentication">
    <DisplayName>DecodeBasicAuthentication</DisplayName>
    <Operation>Decode</Operation>
    <IgnoreUnresolvedVariables>false</IgnoreUnresolvedVariables>
    <User ref="request.header.username"/>
    <Password ref="request.header.password"/>
    <Source>request.header.Authorization</Source>
</BasicAuthentication>
```

### **Step 2: JavaScript Policy to Validate Credentials**

## Policy XML
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<Javascript async="false" continueOnError="false" enabled="true" timeLimit="200" name="JS-ValidateCredential">
    <DisplayName>JS-ValidateCredential</DisplayName>
    <Properties/>
    <ResourceURL>jsc://JS-ValidateCredential.js</ResourceURL>
</Javascript>
```
### JavaScript File: `JS-ValidateCredential.js`
```javascript
// Expected Credentials
var expectedUsername = "Famlanding";
var expectedPassword = "Famlanding12345";

// Extract from decoded headers
var username = context.getVariable("request.header.username");
var password = context.getVariable("request.header.password");

// Validate
if (username !== expectedUsername || password !== expectedPassword) {
    context.setVariable("Authentication-Failed", true);
}
 ```


### **Step 3: Raise Fault for Unauthorized Access : This policy is triggered if validation fails.**

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<RaiseFault async="false" continueOnError="false" enabled="true" name="RF-AuthError">
    <DisplayName>RF-AuthenticationError</DisplayName>
    <Properties/>
    <FaultResponse>
        <Set>
            <Headers/>
            <Payload>{"error": "Invalid username or password."}</Payload>
            <StatusCode>401</StatusCode>
            <ReasonPhrase>UnAuthorized</ReasonPhrase>
        </Set>
    </FaultResponse>
    <IgnoreUnresolvedVariables>true</IgnoreUnresolvedVariables>
</RaiseFault>
```