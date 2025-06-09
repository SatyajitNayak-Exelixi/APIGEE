# 🚀 Apigee Condition-Based Routing.

In this tutorial, we’ll explore how to route traffic to different targets based on:

1. `proxy.pathsuffix`
2. `request.header`
3. `request.queryparam`

Above are the major routing logic we use on a regular basic! 👨‍🏫📘

---

## 🎯 What is Condition-Based Routing?

In Apigee, you can define routing logic using `<RouteRule>` blocks inside your `ProxyEndpoint.xml`.  
These conditions decide **which target endpoint** the request should hit.

---

## 🔧 Unified Example: Route Rules with All 3 Conditions

```xml
<!-- 🔹 Routing Based on Path Suffix -->
<RouteRule name="Route-Flipkart-Path">
    <TargetEndpoint>Flipkart</TargetEndpoint>
    <Condition>proxy.pathsuffix MatchesPath "/Flipkart"</Condition>
</RouteRule>

<RouteRule name="Route-Myntra-Path">
    <TargetEndpoint>Myntra</TargetEndpoint>
    <Condition>proxy.pathsuffix MatchesPath "/Myntra"</Condition>
</RouteRule>

<RouteRule name="Route-Amazon-Path">
    <TargetEndpoint>Amazon</TargetEndpoint>
    <Condition>proxy.pathsuffix MatchesPath "/Amazon"</Condition>
</RouteRule>

<!-- 🔹 Routing Based on Header -->
<RouteRule name="Route-Flipkart-Header">
    <TargetEndpoint>Flipkart</TargetEndpoint>
    <Condition>request.header.Site-ID = "Flipkart"</Condition>
</RouteRule>

<RouteRule name="Route-Myntra-Header">
    <TargetEndpoint>Myntra</TargetEndpoint>
    <Condition>request.header.Site-ID = "Myntra"</Condition>
</RouteRule>

<!-- 🔹 Routing Based on Query Parameter -->
<RouteRule name="Route-Flipkart-QueryParam">
    <TargetEndpoint>Flipkart</TargetEndpoint>
    <Condition>request.queryparam.SiteID = "Flipkart"</Condition>
</RouteRule>

<RouteRule name="Route-Amazon-QueryParam">
    <TargetEndpoint>Amazon</TargetEndpoint>
    <Condition>request.queryparam.SiteID = "Amazon"</Condition>
</RouteRule>
