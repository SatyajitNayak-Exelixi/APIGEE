# 🚀 Apigee Flow Callout Policy with Real-Time Scenario

## 📌 What is Flow Callout Policy?
The **Flow Callout** policy in Apigee allows an API proxy to invoke a reusable shared flow from within the main API flow. This helps in scenarios such as centralized authentication, logging, and request validation.

---

## 🔥 Real-Time Scenario: Centralized Authentication using Flow Callout
### 🎯 Use Case:
Before forwarding the request to the backend, the API must validate the authentication token using a shared flow. If the token is valid, the request proceeds; otherwise, it gets rejected.

### 🛠 Steps:
1. **Client makes a request** to Apigee.
2. **Apigee calls a shared flow** using Flow Callout to validate the token.
3. **If valid, request is forwarded** to the backend.
4. **If invalid, Apigee returns an error response** to the client.

---

## 🏗 Implementation in Apigee

### 1️⃣ Define the Flow Callout Policy (`InvokeSharedFlow`)
```xml
<FlowCallout name="InvokeSharedFlow">
    <SharedFlowRef name="TokenValidationFlow"/>
</FlowCallout>
```

### 2️⃣ Create the Shared Flow (`TokenValidationFlow`)
```xml
<Flow name="TokenValidationFlow">
    <Step>
        <VerifyJWT name="VerifyToken">
            <Source>request.header.Authorization</Source>
            <PublicKey>
                <JWKS uri="https://auth-server.com/.well-known/jwks.json"/>
            </PublicKey>
            <Algorithm>RS256</Algorithm>
        </VerifyJWT>
    </Step>
    <Step>
        <RaiseFault name="InvalidToken">
            <Condition>jwt.isValid != true</Condition>
            <FaultResponse>
                <Set>
                    <StatusCode>401</StatusCode>
                    <ReasonPhrase>Unauthorized</ReasonPhrase>
                    <Message>Invalid Token</Message>
                </Set>
            </FaultResponse>
        </RaiseFault>
    </Step>
</Flow>
```

### 3️⃣ Reference the Flow Callout in the API Proxy
```xml
<Step>
    <FlowCallout name="InvokeSharedFlow"/>
</Step>
```

---

## 🎯 Final Flow
```plaintext
[Client] ---> [Apigee Proxy] ---> [Flow Callout (Token Validation)] ---> [Backend Server]
```

- **Reusability Enhanced** ✅ (Centralized authentication logic)
- **Security Improved** 🔒 (Token validation before hitting backend)
- **Modular API Design** 🏗 (Easy to maintain and scale)

---

## 📢 Conclusion
The **Flow Callout** policy is a powerful feature in Apigee that enables modular API design by leveraging reusable shared flows. In this scenario, it ensures that all requests are authenticated before reaching the backend, improving security and maintainability. 🚀

