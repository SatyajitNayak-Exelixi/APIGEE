# 🚀 Restricting API Access Based on IP Address in Apigee

## 📌 Scenario
In this use case, we have an **open API**, but we need to restrict access to only specific IP addresses. Any request from an unauthorized IP should be denied with a **403 Forbidden** response.

---

## 🔹 IP Restriction Condition
We use an **Access Control Policy** to check the `x-forwarded-for` header, which contains the client's IP address. If the IP is not in the allowed list, the request will be blocked.

```xml
<Step>
    <Name>Error-Flow</Name>
    <Condition>(request.verb != "OPTIONS") and !((request.header.x-forwarded-for matches "3.92.38.80") or
    (request.header.x-forwarded-for matches "54.164.194.84") or
    (request.header.x-forwarded-for matches "52.40.34.27") or
    (request.header.x-forwarded-for matches "54.149.22.206"))
    </Condition>
</Step>
```

### ✅ Explanation:
- **request.verb != "OPTIONS"** → This ensures that preflight `OPTIONS` requests are allowed (useful for CORS requests).
- **request.header.x-forwarded-for matches "<IP>"** → Checks if the request is coming from an **allowed IP**.
- **!()** → Denies access if the request IP is **not in the allowed list**.

---

## 🔹 Custom Error Message for Unauthorized Access
If an unauthorized IP tries to access the API, we return a **403 Forbidden** response with a custom error message.

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<RaiseFault continueOnError="false" enabled="true" name="Error-Flow">
    <DisplayName>Error-Flow</DisplayName>
    <Properties/>
    <FaultResponse>
        <Set>
            <Payload contentType="application/json">{
  "error" : {
    "code" : 403,
    "message" : "Access control Denied ipaddress"
  }
}</Payload>
            <StatusCode>403</StatusCode>
            <ReasonPhrase>Access control Denied ipaddress</ReasonPhrase>
        </Set>
    </FaultResponse>
    <IgnoreUnresolvedVariables>true</IgnoreUnresolvedVariables>
</RaiseFault>
```

### ✅ Explanation:
- **RaiseFault Policy** → Used to return a custom error response.
- **Payload ContentType `application/json`** → The response will be in JSON format.
- **StatusCode `403`** → Denotes forbidden access.
- **Custom Message** → Clearly states that the request is blocked due to an unauthorized IP.

---

## 🔹 How to Implement in Apigee?
### 1️⃣ Add the Condition in Your Proxy Flow
- Navigate to **Apigee UI** → **APIs** → **Select your API Proxy**.
- Click on **Develop** and go to the **PreFlow** of your API Proxy.
- Add the `Step` condition inside the request flow before reaching the backend.

### 2️⃣ Add the `RaiseFault` Policy
- Inside **Policies**, create a new policy **RaiseFault**.
- Copy and paste the above RaiseFault XML code.
- Attach it to the **PreFlow** with the `Step` condition.

### 3️⃣ Deploy & Test
- Deploy your API changes.
- Test with **allowed IPs** → Should pass.
- Test with **restricted IPs** → Should return **403 Forbidden**.

---

## 🚀 Best Practices
✅ Always log unauthorized access attempts for security monitoring.  
✅ Keep your **allowed IP list dynamic** (store in KVM or use an external configuration).  
✅ Ensure that **x-forwarded-for** is properly passed by the API Gateway or Load Balancer.  
✅ Test extensively to confirm that legitimate users are not blocked.  

🔐 **With this setup, you effectively control access to your API based on IP addresses, improving security and compliance!** 🚀

