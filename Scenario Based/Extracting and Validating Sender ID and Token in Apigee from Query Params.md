# 🌐 **Extracting and Validating Sender ID and Token in Apigee**

## 📌 **Overview**
In this guide, we will learn how to extract the `senderid` and `authtoken` from query parameters and validate them using Apigee policies. We will implement:
- **ExtractVariables** policy to fetch the `senderid` and `authtoken`.
- **VerifyJWT** policy to validate the token against trusted issuers.
- **Conditional Flow Execution** to handle different sender IDs with separate JWT validation rules.

---
## 🔥 **Real-Time Scenario**
Imagine a **push notification system** where different applications send notifications using **unique sender IDs**. Each request contains a JWT token that needs to be verified. We need to ensure that:
1. **The correct sender ID is extracted** from the query parameters.
2. **The JWT token is validated** against a specific issuer and key set based on the sender ID.
3. **Unauthorized requests are rejected** to prevent security risks.

For example, a request from **IMX4V** should be validated with a different JWT key set than a request from **IMX4A**.

---
## 🚀 **Implementation in Apigee**

### 📌 **PreFlow Configuration**
```xml
<PreFlow name="PreFlow">
    <Request>
        <Step>
            <Name>ExtractSenderIdAndToken</Name>
        </Step>
        <Step>
            <Name>Verify-JWT</Name>
            <Condition>auth.senderid = "IMX4V"</Condition>
        </Step>
        <Step>
            <Name>Verify-JWT-X4A</Name>
            <Condition>auth.senderid = "IMX4A"</Condition>
        </Step>
    </Request>
    <Response/>
</PreFlow>
```
🔹 **Purpose**: This flow ensures that only valid requests pass through based on the sender ID.

---

### 📌 **Extract Variables Policy**
```xml
<ExtractVariables async="false" continueOnError="false" enabled="true" name="ExtractSenderIdAndToken">
    <DisplayName>ExtractSenderIdAndToken</DisplayName>
    <Properties/>
    <QueryParam name="senderid">
        <Pattern ignoreCase="true">{senderid}</Pattern>
    </QueryParam>
    <QueryParam name="authtoken">
        <Pattern ignoreCase="true">{authtoken}</Pattern>
    </QueryParam>
    <VariablePrefix>auth</VariablePrefix>
</ExtractVariables>
```
🔹 **Purpose**: This extracts `senderid` and `authtoken` from query parameters and stores them under `auth.senderid` and `auth.authtoken`.

---

### 📌 **Verify JWT for IMX4V**
```xml
<VerifyJWT async="false" continueOnError="false" enabled="true" name="Verify-JWT">
    <DisplayName>Verify JWT-X4V</DisplayName>
    <Algorithm>RS256</Algorithm>
    <Source>auth.authtoken</Source>
    <PublicKey>
        <JWKS uri="https://myaccount-stage.ingrammicro.com/oauth2/aus1c1iir68zVlNjN0h8/v1/keys"/>
    </PublicKey>
    <IgnoreUnresolvedVariables>false</IgnoreUnresolvedVariables>
    <Issuer>https://myaccount-stage.ingrammicro.com/oauth2/aus1c1iir68zVlNjN0h8</Issuer>
</VerifyJWT>
```
🔹 **Purpose**: This policy verifies JWT tokens for **IMX4V** sender ID using its corresponding issuer and key set.

---

### 📌 **Verify JWT for IMX4A**
```xml
<VerifyJWT async="false" continueOnError="false" enabled="true" name="Verify-JWT-X4A">
    <DisplayName>Verify JWT-X4A</DisplayName>
    <Algorithm>RS256</Algorithm>
    <Source>auth.authtoken</Source>
    <PublicKey>
        <JWKS uri="https://login.microsoftonline.com/d78aee32-8f91-4f9e-90ea-fb72965d9d7c/discovery/v2.0/keys"/>
    </PublicKey>
    <IgnoreUnresolvedVariables>false</IgnoreUnresolvedVariables>
    <Issuer>https://sts.windows.net/d78aee32-8f91-4f9e-90ea-fb72965d9d7c/</Issuer>
</VerifyJWT>
```
🔹 **Purpose**: This policy verifies JWT tokens for **IMX4A** sender ID using its corresponding issuer and key set.

---
## 🛠️ **Key Takeaways**
✅ **Security First**: Extract and validate tokens before processing any request.
✅ **Conditional Validation**: Different sender IDs may require different validation rules.
✅ **Error Handling**: Ensure proper logging and error messages in case of validation failures.
✅ **Performance Considerations**: Extracting and verifying tokens should be optimized to avoid latency.

---
## 📢 **Final Thoughts**
This implementation ensures that only authorized requests with valid JWT tokens are processed, reducing security risks. By separating the logic for different sender IDs, we create a **scalable** and **secure** API gateway.

🚀 **Next Steps**: Try extending this by implementing error handling and logging mechanisms for better debugging!


