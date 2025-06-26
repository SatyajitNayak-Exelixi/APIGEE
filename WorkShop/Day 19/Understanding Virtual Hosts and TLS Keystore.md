# Apigee: Understanding Virtual Hosts and TLS Keystore

In Apigee, **Virtual Hosts** and **TLS Keystores** are essential components for handling secure traffic over HTTPS and routing requests to the correct API Proxies.

---

## 🔹 What is a Virtual Host?

A **Virtual Host** in Apigee acts like a "gatekeeper" that listens for incoming requests on a specific domain and port and tells Apigee how to route them.

### ✅ Key Elements of a Virtual Host:

* **HostAlias**: The custom domain (e.g., `api.exelixi.com`) that will be exposed to clients.
* **Port**: The port Apigee will listen on, usually **443** for HTTPS.
* **BasePath**: Optional. Defines a root path like `/` or `/v1`, useful when you want to host multiple APIs under the same domain.
* **SSLInfo**: TLS settings that point to the keystore and certificate alias.

### ✅ Purpose:

* Binds a custom domain (like `api.exelixi.com`) to your Apigee runtime.
* Associates that domain with the correct **TLS cert** and **API proxy base paths**.
* Ensures that incoming requests are handled securely (HTTPS) and routed appropriately.

### 🛠 Example Configuration:

```xml
<VirtualHost name="secure-vhost">
  <HostAliases>
    <HostAlias>api.exelixi.com</HostAlias>
  </HostAliases>
  <Port>443</Port>
  <BasePath>/</BasePath>
  <SSLInfo>
    <KeyStore>exelixi-keystore</KeyStore>
    <KeyAlias>exelixi-cert</KeyAlias>
  </SSLInfo>
</VirtualHost>
```

### 🔍 Real-World Analogy:

Think of a **Virtual Host** like the entrance gate of a building. Based on the name on the gate (your custom domain), Apigee knows which set of APIs (floors/rooms inside) the request should be routed to.

---

## 🔹 What is a TLS Keystore?

A **Keystore** is a secure container in Apigee used to store:

* **TLS certificates** (public key)
* **Private keys**

These are used to enable HTTPS communication between clients and the Apigee Gateway.

### ✅ Purpose:

To ensure that Apigee can handle secure (HTTPS) requests using valid certificates for a custom domain.

### 🛠 How to Use:

1. Upload your cert & private key to Apigee under **Environment > Keystores & Truststores**.
2. Create a keystore (e.g., `exelixi-keystore`)
3. Add an alias (e.g., `exelixi-cert`) with your cert & key
4. Reference this alias in your **Virtual Host**

---

## 🔁 How They Work Together

When a request hits `https://api.exelixi.com/my-api`:

1. DNS resolves the domain to Apigee's external IP
2. Apigee matches the domain and port using the **Virtual Host**
3. TLS is terminated using the cert from the **Keystore**
4. The request is routed to the correct **API Proxy** based on the BasePath

---

## 🧠 Key Points to Remember

* Virtual Hosts define which hostnames Apigee will respond to.
* TLS Keystores ensure secure communication over HTTPS.
* You must bind a keystore to a virtual host to support HTTPS traffic.
* Your API Proxy must be configured to use the correct Virtual Host.

---

Let me know if you want a full deployment example or setup guide for Apigee X.
