# 🛑 Apigee Failover Handling with ServerUnhealthyResponse

This document explains how to configure **failover** and how Apigee identifies **unhealthy backend responses** and reroutes traffic to alternate target servers — even without using advanced load balancing algorithms.

---

## 🚦 Failover with Server Unhealthy Responses

The following configuration demonstrates how Apigee detects server failures based on specific HTTP response codes and switches to another target server when one becomes unresponsive or unhealthy:

```xml
<TargetEndpoint name="default">
  <HTTPTargetConnection>
    <LoadBalancer>
      <Algorithm>RoundRobin</Algorithm>
      <Server name="target1" />
      <Server name="target2" />
      <MaxFailures>1</MaxFailures>
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

## ❓ What Does This Do?

* **MaxFailures:** If Apigee receives 1 consecutive failure from a server, it will consider that target server as unhealthy.
* **ServerUnhealthyResponse:** This block tells Apigee what kind of HTTP status codes (like 404, 500, 502, 503) should be considered as a failure.
* **Failover Logic:** Once a server reaches the `MaxFailures` threshold, Apigee automatically fails over to the next server in the list.

---

## 💡 Why Use This?

This setup allows:

* **High Availability** — Requests are rerouted if one backend server is failing.
* **No Manual Intervention** — Apigee self-manages failover based on response codes.
* **Control Over Error Handling** — You can define exactly which HTTP codes should be considered as unhealthy.

---

## 🔁 How Apigee Knows to Switch Targets

1. Apigee sends a request to `target1`.
2. If it receives one of the configured error response codes (like 500), it counts it as a failure.
3. Once 1 such failure is observed (as per `MaxFailures`), Apigee marks `target1` as **unhealthy**.
4. It automatically starts sending requests to the next available target server, here `target2`.

---


### 🚀 !
