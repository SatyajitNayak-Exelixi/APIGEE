# 🔐 Apigee Basic Authentication — Full Flow Example

## 📘 Overview

Basic Authentication allows secure API access using a Base64-encoded `username:password` combination sent in the `Authorization` header. In this guide, we’ll walk through a complete working example in Apigee:

- 🔓 Decode the credentials
- 🧠 Validate using Verify API Key.
- ❌ Return 401 if invalid
- ✅ Allow request if valid

---

## 🔁 PreFlow Setup

The following `PreFlow` runs the policies in order:

```xml
<PreFlow name="PreFlow">
    <Request>
        <Step>
        <Name>EV-ExtractSoapCredentials</Name>
    </Step>
    <Step>
        <Name>DecodeBasicAuthentication</Name>
        <Condition>(request.querystring != "wsdl" and request.querystring != "WSDL") and ((request.header.Content-Type !~ "*xml*") and (request.header.Authorization !~ "Bearer*"))</Condition>
    </Step>
    <Step>
        <Name>VerifyAPIKey</Name>
        <Condition>(request.querystring != "wsdl" and request.querystring != "WSDL") and ((request.header.Content-Type !~ "*xml*") and (request.header.Authorization !~ "Bearer*") and (request.header.Authorization ~ "Basic*"))</Condition>
    </Step>
    <Step>
        <Name>VerifyAPIKey-SOAP</Name>
        <Condition>((request.querystring != "wsdl" and request.querystring != "WSDL") and (request.header.Content-Type ~ "*xml*") and (request.header.Authorization !~ "Bearer*"))</Condition>
    </Step>
    <Step>
        <Name>RF_LoginError</Name>
        <Condition>(request.querystring != "wsdl" and request.querystring != "WSDL")  and ((verifyapikey.VerifyAPIKey-SOAP.client_secret != password) or (verifyapikey.VerifyAPIKey.client_secret != request.header.password))</Condition>
    </Step>
    </Request>
    <Response/>
</PreFlow>
```

---

## :gear: **Step-by-Step Guide to Creating an API Proxy**

### **Step 1: Extract SOAP credential from the body**
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<ExtractVariables async="false" continueOnError="false" enabled="true" name="EV-ExtractSoapCredentials">
    <DisplayName>EV-ExtractSoapCredentials</DisplayName>
    <Properties/>
    <IgnoreUnresolvedVariables>true</IgnoreUnresolvedVariables>
    <Source clearPayload="false">request</Source>
    <XMLPayload stopPayloadProcessing="false">
        <Namespaces>
            <Namespace prefix="soap-env">http://schemas.xmlsoap.org/soap/envelope/</Namespace>
            <Namespace prefix="typ">http://www.ingrammicro.com/common/ServiceRequestHeader_v2_2/types</Namespace>
            <Namespace prefix="ser">http://www.ingrammicro.com/ServiceRequestHeader</Namespace>
        </Namespaces>
        <Variable name="username" type="string">
            <XPath>//applicationCredential/ID/text()</XPath>
        </Variable>
        <Variable name="username" type="string">
            <XPath>//ApplicationCredential/ID/text()</XPath>
        </Variable>
        <Variable name="username" type="string">
            <XPath>/soap-env:Envelope/soap-env:Header/typ:ServiceRequestHeader/typ:ApplicationCredential/typ:ID/text()</XPath>
        </Variable>
        <Variable name="password" type="string">
            <XPath>//applicationCredential/credential/text()</XPath>
        </Variable>
        <Variable name="password" type="string">
            <XPath>//ApplicationCredential/Credential/text()</XPath>
        </Variable>
        <Variable name="password" type="string">
            <XPath>/soap-env:Envelope/soap-env:Header/typ:ServiceRequestHeader/typ:ApplicationCredential/typ:Credential/text()</XPath>
        </Variable>
    </XMLPayload>
</ExtractVariables>
```

### **Step 2: BasicAuthentication Policy (Decode) : This policy decodes the Basic Auth header.**

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

### **Step 3: To Verify API Key **

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<VerifyAPIKey async="false" continueOnError="false" enabled="true" name="VerifyAPIKey">
    <DisplayName>VerifyAPIKey</DisplayName>
    <Properties/>
    <APIKey ref="request.header.username"/>
</VerifyAPIKey>
```

### **Step 4: To Verify API Key - SOAP**

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<VerifyAPIKey async="false" continueOnError="false" enabled="true" name="VerifyAPIKey-SOAP">
    <DisplayName>VerifyAPIKey-SOAP</DisplayName>
    <Properties/>
    <APIKey ref="username"/>
</VerifyAPIKey>
```

### **Step 5: Raise Fault for Unauthorized Access : This policy is triggered if validation fails.**

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<RaiseFault async="false" continueOnError="false" enabled="true" name="RF-AuthError">
    <DisplayName>RF-AuthError</DisplayName>
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