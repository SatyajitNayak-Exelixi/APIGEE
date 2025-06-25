# 🔄 Apigee Streaming and Timeout Configuration

This document explains how to configure **streaming** and **timeout settings** in Apigee using `<Properties>` inside the `<HTTPTargetConnection>` block.

---

## ⚙️ Configuration : Proxy EndPoint

```xml
<HTTPProxyConnection>
        <BasePath>/user/v1</BasePath>
        <Properties>
            <Property name="response.streaming.enabled">true</Property>
            <Property name="request.streaming.enabled">true</Property>
        </Properties>
</HTTPProxyConnection>
```

---

## ⚙️ Configuration : Target EndPoint

```xml
<HTTPTargetConnection>
        <URL>https://exelixi.com/user/v1</URL>
        <Properties>
            <Property name="io.timeout.millis">120000</Property>
            <Property name="response.streaming.enabled">true</Property>
            <Property name="request.streaming.enabled">true</Property>
        </Properties>
</HTTPTargetConnection>
```

---

## ⏱️ Property Breakdown

### `io.timeout.millis`

* **Purpose**: Sets the timeout (in milliseconds) for I/O operations like connecting to the backend.
* **120000 ms = 2 minutes**.
* If a backend takes longer than this, the request will fail with a timeout.

### `request.streaming.enabled`

* **Purpose**: Enables **streaming** of request payloads to the target server.
* Useful when dealing with **large or chunked payloads**.
* Reduces memory usage and improves performance for large incoming requests.

### `response.streaming.enabled`

* **Purpose**: Enables **streaming** of response data from the backend to the client.
* Allows data to be forwarded to the client as it is received from the backend, rather than waiting for the full response.
* Great for APIs returning large responses (e.g., file downloads, logs).

---

## 📈 Why Use These?

* Improves performance for large payloads.
* Prevents timeouts on long-running APIs.
* Optimizes memory handling via streaming.

---


### ✅ That’s how you configure streaming and timeout in Apigee!
