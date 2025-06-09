# 🔒 Access Control Policy in Apigee

## 🚀 Overview
The **AccessControl** policy in Apigee allows or blocks API requests based on IP addresses or geolocation. This helps in restricting access to sensitive APIs and ensures only authorized users can make requests.

## ✅ Apigee Access Control Policy Example

```xml
<AccessControl name="Restrict-Access">
    <Allow>
        <IP>192.168.1.1</IP>
        <IP>192.168.1.2</IP>
    </Allow>
    <Deny>
        <IP>10.0.0.1</IP>
    </Deny>
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

