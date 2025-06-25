# 🎯 Apigee Target Load Balancer Configuration Guide

## 📌 What is Target Load Balancer?

In Apigee, a **LoadBalancer** helps distribute incoming traffic to multiple **target servers**, improving reliability, availability, and performance. It can be configured inside the `<HTTPTargetConnection>` of a `<TargetEndpoint>`.

---

## ⚙️ Load Balancer Algorithms

Apigee supports 3 primary load balancing algorithms:

### 1️⃣ Round Robin (Default)

* **Description:** Sends each request to the next server in the list, in order.
* **Use case:** All target servers have equal capacity and performance.

```xml
<HTTPTargetConnection>
    <LoadBalancer>
        <Algorithm>RoundRobin</Algorithm>
        <Server name="Exelixi-Host1"/>
        <Server name="Exelixi-Host1"/>
        <MaxFailures>2</MaxFailures>
        <RetryEnabled>true</RetryEnabled>
    </LoadBalancer>
    <Properties/>
    <Path>/exelixi/cms</Path>
    <HealthMonitor>
        <IsEnabled>true</IsEnabled>
        <IntervalInSec>5</IntervalInSec>
        <TCPMonitor>
            <ConnectTimeoutInSec>10</ConnectTimeoutInSec>
            <Port>5071</Port>
        </TCPMonitor>
    </HealthMonitor>
</HTTPTargetConnection>
```

🟢 **Note:** Simple and effective when all servers are equally capable.

---

### 2️⃣ Weighted Load Balancing

* **Description:** Routes more traffic to servers with higher weights.
* **Use case:** You have servers with different capacities (e.g., target2 is more powerful).

```xml
<TargetEndpoint name="default">
  <HTTPTargetConnection>
    <LoadBalancer>
      <Algorithm>Weighted</Algorithm>
      <Server name="target1">
        <Weight>1</Weight>
      </Server>
      <Server name="target2">
        <Weight>2</Weight>
      </Server>
    </LoadBalancer>
    <Path>/test</Path>
  </HTTPTargetConnection>
</TargetEndpoint>
```

📊 **Example:** For every 3 requests:

* 1 request goes to `target1`
* 2 requests go to `target2`

---

### 3️⃣ Least Connections

* **Description:** Sends traffic to the server with the **fewest open connections**.
* **Use case:** When you want to balance based on real-time load.

```xml
<TargetEndpoint name="default">
  <HTTPTargetConnection>
    <LoadBalancer>
      <Algorithm>LeastConnections</Algorithm>
      <Server name="target1" />
      <Server name="target2" />
    </LoadBalancer>
    <Path>/test</Path>
  </HTTPTargetConnection>
</TargetEndpoint>
```

## 📚 Summary Table

| Algorithm        | Based On                     | Best Use Case                              |
| ---------------- | ---------------------------- | ------------------------------------------ |
| RoundRobin       | Order of server list         | Equal-capacity servers                     |
| Weighted         | Configured weight per server | Unequal server capacity                    |
| LeastConnections | Live open connections        | Varying server load or real-time balancing |

---

