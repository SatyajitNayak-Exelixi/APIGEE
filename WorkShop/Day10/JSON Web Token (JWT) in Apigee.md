# 📌 JSON Web Token (JWT) in Apigee

## 🔥 Overview

JSON Web Token (JWT) is a secure way to transmit information between parties as a JSON object. In Apigee, JWT is used for authentication and authorization in APIs.

---

## 📦 Attach Policies in Request Flow

```xml
<Request>
    <Step>
        <Name>JS-BearerToken</Name>
    </Step>
    <Step>
        <Name>DecodeJWT</Name>
    </Step>
    <Step>
        <Name>Verify-JWT</Name>
    </Step>
</Request>
```

---

## 🚀 Key JWT Operations in Apigee

### 1️⃣ Extract Bearer Token (JavaScript)

```xml
<Javascript async="false" continueOnError="false" enabled="true" timeLimit="200" name="JS-BearerToken">
    <DisplayName>JS-BearerToken</DisplayName>
    <Properties/>
    <ResourceURL>jsc://JS-BearerToken.js</ResourceURL>
</Javascript>
```

#### JavaScript File (JS-BearerToken.js)

```javascript
var auth = context.getVariable('request.header.Authorization');
if (auth && auth.startsWith('Bearer ')) {
    var token = auth.substring(auth.indexOf(' ') + 1);
    context.setVariable("bearerToken", token);
} else {
    context.setVariable("bearerToken", '');
}
```

---

### 2️⃣ Decode JWT

```xml
<DecodeJWT async="false" continueOnError="true" enabled="true" name="DecodeJWT">
    <DisplayName>DecodeJWT</DisplayName>
    <Source>bearerToken</Source>
</DecodeJWT>
```

### 3️⃣ Verify JWT

```xml
<VerifyJWT async="false" continueOnError="false" enabled="true" name="Verify-JWT">
    <DisplayName>Verify JWT</DisplayName>
    <Algorithm>RS256</Algorithm>
    <Source>bearerToken</Source>
    <PublicKey>
        <JWKS uri="<Take the Issuer from the token>/discovery/v2.0/keys"/>
    </PublicKey>
    <IgnoreUnresolvedVariables>false</IgnoreUnresolvedVariables>
    <Issuer><Take the Issuer from the token></Issuer>
    <Audience><Take the Audience from the token></Audience>
</VerifyJWT>
```

---

## 🎯 Summary Table

| Operation     | Purpose                          | Apigee Policy |
| ------------- | -------------------------------- | ------------- |
| Extract Token | Extract token from Authorization | `Javascript`  |
| Decode JWT    | Read token claims                | `DecodeJWT`   |
| Verify JWT    | Authenticate & validate token    | `VerifyJWT`   |

---

## 📚 Conclusion

JWTs in Apigee provide a robust way to secure APIs.


🚀 **Secure your APIs with JWT today!** 🔐
