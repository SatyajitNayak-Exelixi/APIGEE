# 🎯 Condition-Based Routing in Apigee

## 🚀 Overview
Condition-based routing in Apigee enables API traffic to be directed based on request parameters, headers, and paths. This helps in implementing flexible routing logic based on business needs.

---

## 🛠️ Scenario 1: Path Suffix-Based Routing
**🔹 Requirement:** Extract the path suffix and route the request accordingly.

**📝 Condition:**
```xml
<Condition>proxy.pathsuffix MatchesPath "/*/news-scorecard"</Condition>
```

**✅ Explanation:**
- The `proxy.pathsuffix` variable extracts the last part of the request URL.
- The `MatchesPath "/*/news-scorecard"` condition ensures that requests matching this pattern are routed accordingly.
- Example:
  - **Request:** `https://api.example.com/v1/sports/news-scorecard`
  - **Extracted Suffix:** `/v1/news-scorecard`
  - **Routing Decision:** Forward request to the backend responsible for handling news scorecards.

📌 **Use Case:** Sports API routing for news scorecards.

---

## 🛠️ Scenario 2: Header-Based Routing
**🔹 Requirement:** Reject requests from a specific origin.

**📝 Condition:**
```xml
<Condition>request.header.origin != "https://demo-beta.ingrammicro.com"</Condition>
```

**✅ Explanation:**
- The `request.header.origin` fetches the value of the `Origin` header.
- The `!=` operator ensures that requests from `https://demo-beta.ingrammicro.com` are blocked or handled differently.
- Example:
  - **Request 1:**
    - **Origin:** `https://demo-beta.ingrammicro.com` ❌ **Blocked**
  - **Request 2:**
    - **Origin:** `https://allowed-origin.com` ✅ **Allowed**

📌 **Use Case:** Security enforcement to prevent cross-origin requests from unauthorized sources.

---

## 🛠️ Scenario 3: Query Parameter-Based Routing
**🔹 Requirement:** Route requests based on query parameters.

**📝 Condition:**
```xml
<Condition>request.queryparam.type = "premium"</Condition>
```

**✅ Explanation:**
- The `request.queryparam.type` extracts the `type` query parameter from the request.
- If `type` equals `premium`, route the request to a premium service backend.
- Example:
  - **Request 1:** `https://api.example.com/news?type=premium` ✅ **Route to Premium News Service**
  - **Request 2:** `https://api.example.com/news?type=free` ✅ **Route to Free News Service**

📌 **Use Case:** Implementing tier-based API access (Free vs. Premium users).

---

## 🎨 Visual Representation
![Condition-Based Routing](https://via.placeholder.com/800x400.png?text=Condition-Based+Routing+in+Apigee)

---

## 📌 Summary
| Scenario | Condition | Use Case |
|----------|-----------|----------|
| Path Suffix-Based | `proxy.pathsuffix MatchesPath "/*/news-scorecard"` | Routing news scorecard requests |
| Header-Based | `request.header.origin != "https://demo-beta.ingrammicro.com"` | Blocking unauthorized origins |
| Query Param-Based | `request.queryparam.type = "premium"` | Routing based on user type |

---

## 🔗 Further Reading
- [Apigee Condition-Based Routing Docs](https://cloud.google.com/apigee/docs/api-platform/reference/policies/flow-variables)
- [Apigee API Proxy Routing](https://cloud.google.com/apigee/docs/api-platform/fundamentals/understanding-apis)

🚀 **Happy Learning!**

