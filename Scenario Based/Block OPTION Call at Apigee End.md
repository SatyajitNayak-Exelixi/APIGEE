# 🚫 Block OPTION Calls in Apigee 🚀

### 📌 Overview
Blocking `OPTIONS` requests at the Apigee level is often required for security and compliance reasons. This can be achieved using a **RaiseFault policy** triggered by a condition that detects `OPTIONS` HTTP method requests.

---

## ❓ What is an `OPTIONS` Call?
The `OPTIONS` HTTP method is used by clients (browsers, applications) to determine the allowed HTTP methods and headers for a specific resource. It is commonly used in **CORS (Cross-Origin Resource Sharing)** preflight requests before sending actual API requests.

Example:
```http
OPTIONS /api/resource HTTP/1.1
Host: example.com
Origin: https://client-app.com
Access-Control-Request-Method: POST
Access-Control-Request-Headers: Content-Type, Authorization
```

If unrestricted, `OPTIONS` calls could expose API details, making it crucial to control them.

---

## 🛠 Condition to Block `OPTIONS` Requests
```xml
<Condition>request.verb == "OPTIONS"</Condition>
```
This condition checks if the incoming request method is `OPTIONS`. If true, it triggers the **RaiseFault** policy to block the request.

---

## ⚠️ RaiseFault Policy Implementation
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<RaiseFault continueOnError="false" enabled="true" name="Common-option">
    <DisplayName>Common-option</DisplayName>
    <Properties/>
    <FaultResponse>
        <Set>
            <Headers>
                <Header name="Access-Control-Allow-Origin">{request.header.Origin}</Header>
                <Header name="Access-Control-Allow-Headers">access-control-allow-origin,authorization,content-type,correlationid,countrycode,im-acceptlanguage,im-correlationid,im-countrycode,im-environment,im-microfrontendid,im-senderid,im-sitecode,podiosubvendorid,im-userid,isocountrycode, *</Header>
                <Header name="Access-Control-Allow-Methods">GET, HEAD, POST, PUT, DELETE, OPTIONS, PATCH, COPY, LINK, UNLINK, PURGE, VIEW</Header>
                <Header name="Access-Control-Allow-Credentials">true</Header>
                <Header name="Access-Control-Max-Age">1800</Header>
                <Header name="Vary">Origin</Header>
                <Header name="Accept-Encoding">gzip, deflate, br</Header>
                <Header name="Content-Type">text/html,application/json</Header>
            </Headers>
            <StatusCode>204</StatusCode>
            <ReasonPhrase>OK</ReasonPhrase>
        </Set>
    </FaultResponse>
    <IgnoreUnresolvedVariables>false</IgnoreUnresolvedVariables>
</RaiseFault>
```
---

## 🎯 Explanation
- ✅ The condition **detects `OPTIONS` requests**.
- ✅ The **RaiseFault policy** is executed, blocking the request with **Status Code `204 (No Content)`**.
- ✅ **CORS Headers** are still included to ensure smooth interaction with frontend applications.
- ✅ `Access-Control-Allow-Headers` and `Access-Control-Allow-Methods` are defined to avoid potential preflight request failures.

---

## 🔥 Benefits
- **Enhanced Security**: Prevents unauthorized preflight requests.
- **Better API Control**: Ensures only intended methods are allowed.
- **Smooth CORS Handling**: Returns necessary headers while blocking unwanted requests.

📌 **Use this setup in Apigee to control and secure API calls effectively!** 🚀

