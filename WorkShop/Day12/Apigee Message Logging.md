# 🚀 Apigee Message Logging Policy with Real-Time Scenario

## 📌 What is Message Logging Policy?
The **Message Logging** policy in Apigee allows API proxies to log request and response details to external logging systems like Cloud Logging, Splunk, or a syslog server. This helps in tracking API transactions, debugging issues, and monitoring performance.

---

## 🔥 Real-Time Scenario: Logging API Requests and Responses
### 🎯 Use Case:
An API proxy needs to log incoming requests and outgoing responses to a logging server for debugging and auditing purposes.

### 🛠 Steps:
1. **Client makes a request** to Apigee.
2. **Apigee logs request details** (headers, method, path, etc.).
3. **Request is forwarded** to the backend.
4. **Apigee logs response details** before sending it back to the client.
5. **Logs are sent** to an external logging server.

---

## 🏗 Implementation in Apigee

### 1️⃣ Define the Message Logging Policy (`LogAPIRequests`)
```xml
<MessageLogging name="LogAPIRequests">
    <Syslog>
        <Message>{"request":"{request.verb} {request.path}", "headers":"{request.headers}"}</Message>
        <Host>logging-server.com</Host>
        <Port>514</Port>
        <Protocol>UDP</Protocol>
    </Syslog>
</MessageLogging>
```

### 2️⃣ Define the Response Logging Policy (`LogAPIResponses`)
```xml
<MessageLogging name="LogAPIResponses">
    <Syslog>
        <Message>{"response_code":"{response.status.code}", "response_headers":"{response.headers}"}</Message>
        <Host>logging-server.com</Host>
        <Port>514</Port>
        <Protocol>UDP</Protocol>
    </Syslog>
</MessageLogging>
```

### 3️⃣ Attach Policies to Request and Response Flows
```xml
<PreFlow>
    <Request>
        <Step>
            <Name>LogAPIRequests</Name>
        </Step>
    </Request>
    <Response>
        <Step>
            <Name>LogAPIResponses</Name>
        </Step>
    </Response>
</PreFlow>
```

---

## 🎯 Final Flow
```plaintext
[Client] ---> [Apigee Proxy] ---> [Message Logging (Request)] ---> [Backend Server] ---> [Message Logging (Response)] ---> [Client]
```

- **Enhanced Debugging** 🛠 (Track API request/response flow)
- **Improved Monitoring** 📊 (Real-time logs for analysis)
- **Security & Auditing** 🔍 (Track API usage patterns)

---

## 📢 Conclusion
The **Message Logging** policy in Apigee provides real-time tracking and debugging capabilities by logging API requests and responses. This ensures better observability, security, and performance monitoring for API transactions. 🚀

