# 📌 JSON Web Token (JWT) in Apigee

## 🔥 Overview
JSON Web Token (JWT) is a secure way to transmit information between parties as a JSON object. In Apigee, JWT is used for authentication and authorization in APIs.

## 🚀 Key JWT Operations in Apigee
### 1️⃣ Generate JWT
JWT is generated using a private key and contains claims (payload data). This is commonly used to issue tokens for API authentication.

#### ✅ **Apigee Policy for Generating JWT:**
```xml
<GenerateJWT async="false" continueOnError="false" enabled="true" name="Generate-JWT-1">
    <DisplayName>Generate JWT-1</DisplayName>
    <Algorithm>HS256</Algorithm>
    <SecretKey>
        <Value ref="private.key"/>
    </SecretKey>
    <Subject>subject-subject</Subject>
    <Issuer>urn://apigee-edge-JWT-policy-test</Issuer>
    <Audience>audience1,audience2</Audience>
    <ExpiresIn>8h</ExpiresIn>
    <AdditionalClaims>
        <Claim name="additional-claim-name" type="string">additional-claim-value-goes-here</Claim>
    </AdditionalClaims>
    <OutputVariable>jwt-variable</OutputVariable>
</GenerateJWT>
```

#### 📌 **Real-time Scenario:**
A user logs into a system, and Apigee generates a JWT token that includes their **userID and role**. This token is passed in subsequent API requests for authentication.

---

### 2️⃣ Decode JWT
Decoding a JWT extracts the payload without verifying its authenticity. This is useful when you need to read the token claims.

#### ✅ **Apigee Policy for Decoding JWT:**
```xml
<DecodeJWT name="Decode-JWT">
    <JWT>{request.headers.Authorization}</JWT>
    <OutputVariable>decodedJWT</OutputVariable>
</DecodeJWT>
```

#### 📌 **Real-time Scenario:**
A frontend application sends a JWT in the **Authorization header**. Apigee decodes it to extract the user information (e.g., role-based access control).

---

### 3️⃣ Verify JWT
Verifying a JWT ensures its authenticity by checking its signature and validity period.

#### ✅ **Apigee Policy for Verifying JWT:**
```xml
<VerifyJWT name="Verify-JWT">
    <Algorithm>RS256</Algorithm>
    <PublicKey>
        <!-- Reference to public key stored in Apigee -->
    </PublicKey>
    <Subject>user123</Subject>
    <Issuer>trusted-issuer</Issuer>
    <Audience>api.example.com</Audience>
</VerifyJWT>
```

#### 📌 **Real-time Scenario:**
An API request contains a JWT. Apigee verifies:
- **Signature** (Is it tampered?)
- **Issuer** (Did a trusted authority issue it?)
- **Expiration** (Is it still valid?)

Only valid tokens are allowed access to protected resources.

---

## 🎯 Summary Table
| Operation | Purpose | Apigee Policy |
|-----------|---------|--------------|
| Generate JWT | Create a token for authentication | `GenerateJWT` |
| Decode JWT | Extract payload data | `DecodeJWT` |
| Verify JWT | Validate token authenticity | `VerifyJWT` |

---

## 📚 Conclusion
JWTs in Apigee provide a robust mechanism for API security. Implementing **Generate, Decode, and Verify JWT** ensures secure and efficient user authentication.

🚀 **Enhance your API security with JWT today!** 🔐

