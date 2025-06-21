# 🔐 Apigee KVM Usage – Real Scenarios

## 📚 Table of Contents

* [What is KVM?](#what-is-kvm)
* [Scenario 1: Fetch Credentials from KVM](#scenario-1-fetch-credentials-from-kvm)

  * [Policy 1: Get Username/Password from KVM](#policy-1-get-usernamepassword-from-kvm)
  * [Policy 2: Encode Basic Auth Header](#policy-2-encode-basic-auth-header)
* [Scenario 2: Route Based on Country-Region Mapping](#scenario-2-route-based-on-country-region-mapping)

  * [Policy 1: Get Region from KVM](#policy-1-get-region-from-kvm)
  * [Policy 2: Route Based on Region](#policy-2-route-based-on-region)
* [Summary](#summary)

---

## 🔎 What is KVM?

**Key Value Map (KVM)** is a secure, encrypted key-value store in Apigee used to manage and retrieve configuration data like credentials, tokens, region codes, and other metadata across API proxies.

➡️ **Use Case (One-liner):** Store and retrieve reusable data (e.g., credentials or routing info) securely without hardcoding in your API policies.

---

## ✅ Scenario 1: Fetch Credentials from KVM

### 📘 Use Case:

When a request comes in, fetch the **username and password** from a KVM and send them to the backend using **Basic Authentication**.

### 🧩 KVM Name:

`OSTShipmentRegister`

### 🔐 Policy 1: Get Username/Password from KVM

```xml
<KeyValueMapOperations name="KVM-Operations" mapIdentifier="OSTShipmentRegister" continueOnError="false" enabled="true">
    <DisplayName>KVM-Operations</DisplayName>
    <ExclusiveCache>false</ExclusiveCache>
    <ExpiryTimeInSecs>30000</ExpiryTimeInSecs>
    <Get assignTo="private.username">
        <Key><Parameter>username</Parameter></Key>
    </Get>
    <Get assignTo="private.password">
        <Key><Parameter>password</Parameter></Key>
    </Get>
    <Scope>environment</Scope>
</KeyValueMapOperations>
```

### 🔐 Policy 2: Encode Basic Auth Header

```xml
<BasicAuthentication name="Encode-BA" continueOnError="false" enabled="true">
    <DisplayName>Encode-BA</DisplayName>
    <Operation>Encode</Operation>
    <IgnoreUnresolvedVariables>false</IgnoreUnresolvedVariables>
    <User ref="private.username"/>
    <Password ref="private.password"/>
    <AssignTo createNew="true">request.header.Authorization</AssignTo>
</BasicAuthentication>
```

---

## ✅ Scenario 2: Route Based on Country-Region Mapping

### 📘 Use Case:

You store **country codes and their corresponding regions** in a KVM (e.g. `AU → APAC`, `US → AMER`) and route requests based on the region.

### 🧩 KVM Name:

`SimplifiedAPI-GCPCountryList`

### 🔐 Policy 1: Get Region from KVM

![SimplifiedAPI-GCPCountryList Example](./images/SimplifiedAPI-GCPCountryList.png)

📷 *Example KVM mapping country codes to regions used in routing logic.*

```xml
<KeyValueMapOperations name="Get-Region" mapIdentifier="SimplifiedAPI-GCPCountryList" continueOnError="false" enabled="true">
    <DisplayName>Get-Region</DisplayName>
    <ExclusiveCache>false</ExclusiveCache>
    <ExpiryTimeInSecs>600</ExpiryTimeInSecs>
    <Get assignTo="region">
        <Key><Parameter>request.queryparam.countryCode</Parameter></Key>
    </Get>
    <Scope>environment</Scope>
</KeyValueMapOperations>
```

📝 This policy looks up the region for the country code received in the query param `countryCode` and assigns it directly to the variable `region`.

---

### 🚦 Policy 2: Route Based on Region

```xml
<RouteRule name="OrderAsync-v7-AMER">
    <Condition>(request.verb = "POST") and (region = "AMER")</Condition>
    <TargetEndpoint>OrderAsync-v7-AMER</TargetEndpoint>
</RouteRule>

<RouteRule name="OrderAsync-v7-EMEA">
    <Condition>(request.verb = "POST") and (region = "EMEA")</Condition>
    <TargetEndpoint>OrderAsync-v7-EMEA</TargetEndpoint>
</RouteRule>

<RouteRule name="OrderAsync-v7-APAC">
    <Condition>(request.verb = "POST") and (region = "APAC")</Condition>
    <TargetEndpoint>OrderAsync-v7-APAC</TargetEndpoint>
</RouteRule>
```

📝 The value of the `region` variable determines which backend the request is routed to.

---

## 🧾 Screenshot Reference

Below is the detailed screenshot of the country-to-region mapping used in the KVM `SimplifiedAPI-GCPCountryList`.

![SimplifiedAPI-GCPCountryList Full Mapping](./images/SimplifiedAPI-GCPCountryList-Full.png)

📷 *Screenshot showing key-value mappings for region-based routing logic in Apigee.*

* Always use `Scope = environment` for shared access across proxies.
* Use `AssignTo` to control variable names when retrieving KVM values.
* Use meaningful variable names for easier routing and debugging.
