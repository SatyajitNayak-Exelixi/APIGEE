# Apigee Fault Tolerance Setup

## Overview

This document provides a simple explanation of the Apigee fault tolerance setup used to handle failover between two backend servers.

---

## TargetEndpoint Configuration

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<TargetEndpoint name="default">
  <PreFlow name="PreFlow">
    <Request/>
    <Response/>
  </PreFlow>
  <PostFlow name="PostFlow">
    <Request/>
    <Response/>
  </PostFlow>
  <Flows/>
  <HTTPTargetConnection>
    <LoadBalancer>
      <Server name="EXELIXI-Onprem"/>
      <Server name="EXELIXI-Cloud">
        <IsFallback>true</IsFallback>
      </Server>
      <ServerUnhealthyResponse>
        <ResponseCode>404</ResponseCode>
        <ResponseCode>500</ResponseCode>
        <ResponseCode>503</ResponseCode>
        <ResponseCode>504</ResponseCode>
      </ServerUnhealthyResponse>
    </LoadBalancer>
    <Path>/GPE/v3</Path>
  </HTTPTargetConnection>
</TargetEndpoint>
```

---

## Description

* **Primary Server:** GPE-Onprem
* **Fallback Server:** GPE-Cloud
* **Path:** `/GPE/v3`
* **Failover Trigger Codes:** 404, 500, 503, 504

If the primary server (GPE-Onprem) returns any of the above error codes, Apigee will automatically route the request to the fallback server (GPE-Cloud). Once the primary server becomes healthy again, traffic will be redirected back to it.

---

## Purpose

This configuration ensures high availability and reliability by redirecting traffic to a backup server in case of failures on the primary backend.

---
