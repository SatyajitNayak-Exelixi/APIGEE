# 🔁 Apigee Retry with Healthy Target Server

This guide explains how to configure **automatic retry** in Apigee when a target server responds with a failure. Instead of returning an error to the client, Apigee retries the request to another healthy server in the backend.

---

## 🚀 What Are We Trying to Solve?

In a standard load balancing setup, if a server fails, Apigee may return an error response to the client. But in mission-critical scenarios, we want to **avoid sending failure to the client** and instead **automatically retry the request to the next available target**.

---

## ✅ Solution: Enable `<RetryEnabled>true</RetryEnabled>`

You can do this by enabling the retry mechanism inside the `<LoadBalancer>` configuration.

### 💡 Updated Configuration:

```xml
<TargetEndpoint name="default">
  <HTTPTargetConnection>
    <LoadBalancer>
      <Algorithm>RoundRobin</Algorithm>
      <Server name="target1" />
      <Server name="target2" />
      <MaxFailures>1</MaxFailures>
      <RetryEnabled>true</RetryEnabled>
      <ServerUnhealthyResponse>
        <ResponseCode>500</ResponseCode>
        <ResponseCode>502</ResponseCode>
        <ResponseCode>503</ResponseCode>
      </ServerUnhealthyResponse>
    </LoadBalancer>
    <Path>/test</Path>
  </HTTPTargetConnection>
</TargetEndpoint>
```

---

## 🔄 How This Works

1. Request is sent to `target1`.
2. If `target1` returns a failure (e.g., 500), Apigee checks the `<ServerUnhealthyResponse>` list.
3. If it matches, failure count is incremented.
4. Since `MaxFailures` = 1, Apigee marks `target1` as **unhealthy**.
5. **Apigee automatically retries the request to `target2` without failing the client request.**

---

