# 🚀 Apigee Service Callout Policy with Real-Time Scenario

## 📌 What is Service Callout Policy?
The **Service Callout** policy in Apigee allows an API proxy to call an external or internal service before proceeding with the main request flow. This helps in scenarios such as fetching authentication tokens, enriching requests, or validating data.

---

## 🔥 Real-Time Scenario: Token Generation with KVM-Stored Credentials

### 🎯 Objective:

Before the client request is routed to the backend, the Apigee proxy must:

1. Retrieve `client_id` and `client_secret` securely from a KVM.
2. Make a token request to the authorization server.
3. Extract the `access_token` from the response.
4. Add the token to the request headers.
5. Forward the updated request to the backend.

---

## 🛠️ Step-by-Step Implementation with Policies

### ✅ Step 1: Retrieve Credentials from KVM

**Purpose:** Securely fetch `client_id` and `client_secret` from environment-scoped KVM.

**Policy Type:** `KeyValueMapOperations`

```xml
<KeyValueMapOperations name="KVM-Operations" mapIdentifier="On-prem-OAuth">
    <DisplayName>KVM-On-prem-OAuth</DisplayName>
    <ExpiryTimeInSecs>30000</ExpiryTimeInSecs>
    <Get assignTo="private.username">
        <Key>
            <Parameter>username</Parameter>
        </Key>
    </Get>
    <Get assignTo="private.password">
        <Key>
            <Parameter>password</Parameter>
        </Key>
    </Get>
    <Scope>environment</Scope>
</KeyValueMapOperations>
```

---

### ✅ Step 2: Call Token Endpoint

**Purpose:** Make a `GET` request to the token endpoint using credentials as query parameters.

**Policy Type:** `ServiceCallout`

```xml
<ServiceCallout name="On-prem-GetToken">
    <DisplayName>On-prem-GetToken</DisplayName>
    <Request clearPayload="false">
        <Set>
            <Verb>GET</Verb>
            <Headers>
                <Header name="Content-Type">application/x-www-form-urlencoded</Header>
            </Headers>
            <QueryParams>
                <QueryParam name="client_id">{private.username}</QueryParam>
                <QueryParam name="client_secret">{private.password}</QueryParam>
                <QueryParam name="grant_type">client_credentials</QueryParam>
            </QueryParams>
        </Set>
    </Request>
    <Response>calloutResponse</Response>
    <HTTPTargetConnection>
        <URL>https://api-qa.exelixi.com/oauth/oauth20/token</URL>
    </HTTPTargetConnection>
</ServiceCallout>
```

---

### ✅ Step 3: Extract Token from JSON Response

**Purpose:** Extract the `access_token` from the JSON payload using JSONPath.

**Policy Type:** `ExtractVariables`

```xml
<ExtractVariables name="Extract-token">
    <Source>calloutResponse</Source>
    <JSONPayload>
        <Variable name="access_token">
            <JSONPath>$.access_token</JSONPath>
        </Variable>
    </JSONPayload>
</ExtractVariables>
```

---

### ✅ Step 4: Assign Token to Request Header

**Purpose:** Add the `access_token` to the Authorization header and forward the request.

**Policy Type:** `AssignMessage`

```xml
<AssignMessage name="Assign-Authentication">
    <DisplayName>Assign-Authentication</DisplayName>
    <Set>
        <Headers>
            <Header name="Authorization">Bearer {access_token}</Header>
            <Header name="Content-Type">application/json</Header>
            <Header name="Accept">application/json</Header>
        </Headers>
        <Payload contentType="application/json">{request.content}</Payload>
    </Set>
    <AssignVariable>
        <Name>request.verb</Name>
        <Value>POST</Value>
    </AssignVariable>
    <AssignTo createNew="true" transport="http" type="request"/>
</AssignMessage>
```

---

## 📊 Key Advantages

* ✅ **Secure Credential Handling:** No hardcoded secrets; KVM is used.
* ♻️ **Reusable Components:** Easily maintainable and reusable flow across proxies.
* ⚖️ **Compliance-Friendly:** Centralized token management and auditing.
* ✨ **Better Abstraction:** Business logic is separated from credential handling.

---

**Policy Type:** `Use this Service CallOut Policy when the credential is send in the body`

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<ServiceCallout async="false" continueOnError="false" enabled="true" name="SC-LoginAndGetJWTToken">
    <DisplayName>SC-GenerateToken</DisplayName>
    <Properties/>
    <Request variable="myRequest" clearPayload="true">
        <Set>
            <Verb>POST</Verb>
            <Path>/Decisions/Primary/REST/AccountService/LoginAndGetJWTToken</Path>
            <Headers>
                <Header name="Content-Type">application/json</Header>
            </Headers>
            <Payload contentType="application/json">
                {
                    "userName": "{private.username}",
                    "password": "{private.password}",
                    "outputtype": "Json"
                }
            </Payload>
        </Set>
        <IgnoreUnresolvedVariables>false</IgnoreUnresolvedVariables>
    </Request>
    <Response>calloutResponse</Response>
    <HTTPTargetConnection>
        <URL>https://xre-stg.corporate.exelixi.com</URL>
    </HTTPTargetConnection>
</ServiceCallout>
```

---


