# 🔒 Access Control Policy in Apigee

## 🚀 Overview
The **AccessControl** policy in Apigee allows or blocks API requests based on IP addresses or geolocation. This helps in restricting access to sensitive APIs and ensures only authorized users can make requests.

## ✅ Apigee Access Control Policy Example

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<AccessControl async="false" continueOnError="false" enabled="true" name="Access-Control-1">
    <DisplayName>Access Control-1</DisplayName>
    <Properties/>
    <IPRules noRuleMatchAction="DENY">
        <MatchRule action="ALLOW">
            <SourceAddress mask="32">106.213.87.148</SourceAddress>
        </MatchRule>
    </IPRules>
</AccessControl>
```

## 📌 Real-time Scenario
A company wants to allow only certain IP addresses to access their internal APIs while blocking unauthorized access.

### **Allowed Request:**
```http
GET /secure-data HTTP/1.1
Host: api.example.com
Origin-IP: 192.168.1.1
```
✅ **Access Granted**

### **Blocked Request:**
```http
GET /secure-data HTTP/1.1
Host: api.example.com
Origin-IP: 10.0.0.1
```
❌ **Access Denied: Unauthorized IP**

## 🎯 Summary Table

| Policy         | Purpose                                    | Apigee Policy   |
|--------------|------------------------------------------|---------------|
| Access Control | Restricts access based on IP or geolocation | `AccessControl` |

## 📚 Conclusion
Using the **AccessControl** policy in Apigee ensures that only trusted IPs can access APIs, enhancing security and preventing unauthorized access.

🚀 **Implement Access Control today to secure your APIs! 🔐**

