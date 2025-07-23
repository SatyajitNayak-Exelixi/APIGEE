# 📌 API Key Verification in Apigee

## 🔥 Overview
API keys are widely used to control and monitor API access. In Apigee, the **Verify API Key** policy ensures that only authorized clients can call protected APIs.

## 🚀 How Verify API Key Works
The **Verify API Key** policy checks the API key sent in the request against the keys stored in Apigee. If the key is valid, the request proceeds; otherwise, access is denied.

## 🔑 Two Ways to Pass API Key in Apigee

### 1️⃣ **Passing API Key in the Request Header**

#### ✅ **Apigee Policy for Verifying API Key (Header Method)**
```xml
<VerifyAPIKey name="Verify-API-Key">
    <APIKey ref="request.header.x-api-key"/>
</VerifyAPIKey>
```

#### 📌 **Real-time Scenario**
A client application wants to access an API endpoint `https://api.example.com/user/data`. The request must include an API key in the **`x-api-key`** header.

#### **Request:**
```http
GET /user/data HTTP/1.1
Host: api.example.com
x-api-key: abc123xyz
```

#### **Verification Steps:**
1. Apigee extracts the API key from `x-api-key`.
2. It checks the key against a stored database.
3. If the key is **valid**, the request is forwarded to the backend.
4. If the key is **invalid**, Apigee returns a `401 Unauthorized` response.

#### **Response (Success):**
```http
HTTP/1.1 200 OK
{
    "message": "Access granted!"
}
```

#### **Response (Failure):**
```http
HTTP/1.1 401 Unauthorized
{
    "error": "Invalid API Key"
}
```

---

### 2️⃣ **Passing API Key in the Query Parameters**

#### ✅ **Apigee Policy for Verifying API Key (Query Parameter Method)**
```xml
<VerifyAPIKey name="Verify-API-Key-QueryParam">
    <APIKey ref="request.queryparam.apiKey"/>
</VerifyAPIKey>
```

#### 📌 **Real-time Scenario**
A client application wants to access an API endpoint `https://api.example.com/user/data`. Instead of passing the API key in the header, it is included as a query parameter.

#### **Request:**
```http
GET /user/data?apiKey=abc123xyz HTTP/1.1
Host: api.example.com
```

#### **Verification Steps:**
1. Apigee extracts the API key from the `apiKey` query parameter.
2. It checks the key against a stored database.
3. If the key is **valid**, the request is forwarded to the backend.
4. If the key is **invalid**, Apigee returns a `401 Unauthorized` response.

#### **Response (Success):**
```http
HTTP/1.1 200 OK
{
    "message": "Access granted!"
}
```

#### **Response (Failure):**
```http
HTTP/1.1 401 Unauthorized
{
    "error": "Invalid API Key"
}
```

---

## 🎯 Summary Table
| API Key Location  | Purpose                           | Apigee Policy           |
|------------------|---------------------------------|------------------------|
| Request Header  | E   | `VerifyAPIKey`         |
| Query Parameter | nsures secure transmission   Alternative way to send API Key | `VerifyAPIKey-QueryParam` |

---

## 📚 Conclusion
Using **Verify API Key** in Apigee strengthens API security by restricting access to authenticated clients. While passing the API key in the header is more secure, the query parameter method is sometimes used for web-based APIs.

🚀 **Secure your APIs with API Key verification today! 🔐**

