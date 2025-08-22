# 🚀 Apigee Condition-Based Routing

In this tutorial, we’ll explore how to route traffic to different targets based on:

* proxy.pathsuffix
* request.queryparam
* request.header

These are the most common routing strategies used in Apigee! 👨‍🏫📘

---

## 🎯 What is Condition-Based Routing?

In Apigee, you can define routing logic using `<RouteRule>` blocks inside your ProxyEndpoint.xml.
These conditions decide which target endpoint the request should hit.

---

## 🔹 1. Path Suffix Routing

This method routes requests based on the **path suffix** in the URL.

**Example:**

```xml
<RouteRule name="Route-Myntra-Path">
    <TargetEndpoint>Myntra</TargetEndpoint>
    <Condition>proxy.pathsuffix MatchesPath "/Myntra"</Condition>
</RouteRule>

<RouteRule name="Route-Amazon-Path">
    <TargetEndpoint>Amazon</TargetEndpoint>
    <Condition>proxy.pathsuffix MatchesPath "/Amazon"</Condition>
</RouteRule>

<RouteRule name="Route-IndiaMart-Path">
    <TargetEndpoint>IndiaMart</TargetEndpoint>
    <Condition>proxy.pathsuffix MatchesPath "/IndiaMart"</Condition>
</RouteRule>
```

**How to Access:**

```
https://<Hostname>/demoapi/Myntra
https://<Hostname>/demoapi/Amazon
https://<Hostname>/demoapi/IndiaMart
```

---

## 🔹 2. Query Parameter Routing

This method routes requests based on **query parameters**.

**Example:**

```xml
<RouteRule name="Route-Myntra-QueryParam">
    <TargetEndpoint>Myntra</TargetEndpoint>
    <Condition>request.queryparam.SiteID = "Myntra"</Condition>
</RouteRule>

<RouteRule name="Route-Amazon-QueryParam">
    <TargetEndpoint>Amazon</TargetEndpoint>
    <Condition>request.queryparam.SiteID = "Amazon"</Condition>
</RouteRule>

<RouteRule name="Route-IndiaMart-QueryParam">
    <TargetEndpoint>IndiaMart</TargetEndpoint>
    <Condition>request.queryparam.SiteID = "IndiaMart"</Condition>
</RouteRule>
```

**How to Access:**

```
https://<Hostname>/demoapi?SiteID=Myntra
https://<Hostname>/demoapi?SiteID=Amazon
https://<Hostname>/demoapi?SiteID=IndiaMart
```

---

## 🔹 3. Header-Based Routing

This method routes requests based on **request headers**.

**Example:**

```xml
<RouteRule name="Route-Myntra-Header">
    <TargetEndpoint>Myntra</TargetEndpoint>
    <Condition>request.header.Site-ID = "Myntra"</Condition>
</RouteRule>

<RouteRule name="Route-Amazon-Header">
    <TargetEndpoint>Amazon</TargetEndpoint>
    <Condition>request.header.Site-ID = "Amazon"</Condition>
</RouteRule>

<RouteRule name="Route-IndiaMart-Header">
    <TargetEndpoint>IndiaMart</TargetEndpoint>
    <Condition>request.header.Site-ID = "IndiaMart"</Condition>
</RouteRule>
```

**How to Access:**

```bash
curl --location 'https://<Hostname>/demoapi' \
--header 'Site-ID: Myntra'

curl --location 'https://<Hostname>/demoapi' \
--header 'Site-ID: Amazon'

curl --location 'https://<Hostname>/demoapi' \
--header 'Site-ID: IndiaMart'
```

---

## 🔧  Remove Extra Query Params

Sometimes, you don’t want unnecessary query params to reach the target.
Use an **AssignMessage** policy in the PreFlow of the Target Endpoint:

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<AssignMessage continueOnError="false" enabled="true" name="RemoveQueryParams">
    <DisplayName>RemoveQueryParams</DisplayName>
    <Properties/>
    <Remove>
        <QueryParams/>
    </Remove>
    <AssignVariable>
        <Name>target.copy.pathsuffix</Name>
        <Value>false</Value>
        <Ref/>
    </AssignVariable>
    <IgnoreUnresolvedVariables>true</IgnoreUnresolvedVariables>
    <AssignTo createNew="false" transport="http" type="request"/>
</AssignMessage>
```

---

