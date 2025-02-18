# 🚀 Apigee Service Callout Policy with Real-Time Scenario

## 📌 What is Service Callout Policy?
The **Service Callout** policy in Apigee allows an API proxy to call an external or internal service before proceeding with the main request flow. This helps in scenarios such as fetching authentication tokens, enriching requests, or validating data.

---

## 🔥 Real-Time Scenario: Generating a Token Before Hitting Backend
### 🎯 Use Case:
Before forwarding the request to the backend, the API must first obtain an authentication token from an authorization server and pass it in the request header.

### 🛠 Steps:
1. **Client makes a request** to Apigee.
2. **Apigee calls an authorization server** to get a token using the Service Callout policy.
3. **Token is extracted and added** to the request headers.
4. **Request is forwarded** to the backend with the token.

---

## 🏗 Implementation in Apigee

### 1️⃣ Define the Service Callout Policy (`GenerateToken`)
```xml
<ServiceCallout name="GenerateToken">
    <Request variable="tokenResponse">
        <Set>
            <Verb>POST</Verb>
            <Path>/oauth/token</Path>
            <Headers>
                <Header name="Content-Type">application/x-www-form-urlencoded</Header>
            </Headers>
            <FormParams>
                <FormParam name="grant_type">client_credentials</FormParam>
                <FormParam name="client_id">your-client-id</FormParam>
                <FormParam name="client_secret">your-client-secret</FormParam>
            </FormParams>
        </Set>
        <HTTPTargetConnection>
            <URL>https://auth-server.com</URL>
        </HTTPTargetConnection>
    </Request>
</ServiceCallout>
```

### 2️⃣ Extract the Token from Response
```xml
<ExtractVariables name="ExtractToken">
    <Source>tokenResponse</Source>
    <Variable name="access_token">
        <JSONPath>$.access_token</JSONPath>
    </Variable>
</ExtractVariables>
```

### 3️⃣ Add the Token to Backend Request
```xml
<AssignMessage name="SetAuthHeader">
    <AssignTo createNew="false" type="request">request</AssignTo>
    <Set>
        <Headers>
            <Header name="Authorization">Bearer {access_token}</Header>
        </Headers>
    </Set>
</AssignMessage>
```

---

## 🎯 Final Flow
```plaintext
[Client] ---> [Apigee Proxy] ---> [Service Callout (Auth Server)] ---> [Backend Server]
```

- **Security Improved** ✅ (Token-based authentication)
- **Dynamic Token Fetching** 🔄 (No hardcoded credentials)
- **Seamless API Integration** 🔗 (Works with any authentication server)

---

## 📢 Conclusion
The **Service Callout** policy is a powerful feature in Apigee that helps fetch external data before processing a request. In this scenario, it ensures that requests are authorized before reaching the backend, enhancing security and compliance. 🚀

