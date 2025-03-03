 # 🔒 Configuring SSL in Apigee for Secure Connections

## 📌 Scenario
A client faced an **SSL issue** while connecting to the following endpoint:

```
https://exelixi.com/XVR/api/v2/renewalsLookup
```

To resolve the issue, an **SSL configuration** was added in Apigee using the `HTTPTargetConnection` policy.

---

## 🔹 SSL Configuration in `HTTPTargetConnection`
The following configuration enables **SSL**, **client authentication**, and uses a **Keystore reference**:

```xml
<HTTPTargetConnection>
    <Properties/>
    <SSLInfo>
        <Enabled>true</Enabled>
        <ClientAuthEnabled>true</ClientAuthEnabled>
        <KeyStore>ref://api-beta</KeyStore>
        <KeyAlias>myKeystoreVhost</KeyAlias>
        <!--<TrustStore>ref://myKeyStoreRef</TrustStore>-->
    </SSLInfo>
    <URL>https://exelixi.com/XVR/api/v2/renewalsLookup</URL>
</HTTPTargetConnection>
```

This configuration ensures that Apigee can securely connect to the backend server using SSL and mutual TLS authentication.

---

## 🔹 When to Use This Configuration?
You should use this SSL setup in Apigee when:
✅ The target server requires **mutual TLS authentication**.  
✅ You need to secure communication between Apigee and the backend.  
✅ The backend expects **client certificates** for authentication.  
✅ SSL errors occur due to missing or incorrect certificate configurations.  
✅ The backend enforces **strict SSL/TLS policies** and requires Apigee to present a trusted certificate.  

---

## 🔹 How to Configure SSL Certificates in Apigee?

### 1️⃣ Upload the Keystore (Client Certificate)
1. Navigate to **Apigee UI** → **Admin** → **Keystores & Truststores**.
2. Click **+ Keystore**, provide a name (e.g., `api-beta`), and upload the **Keystore file (.jks or .p12)**.
3. Add a **Key Alias** (`myKeystoreVhost`) to reference the private key.

### 2️⃣ Upload the Truststore (Optional)
If the backend server requires a specific **trusted CA certificate**, add a **Truststore**:
1. Go to **Keystores & Truststores** → **+ Truststore**.
2. Upload the **CA certificate** and save.

### 3️⃣ Configure the Target Endpoint in Apigee
Ensure that your `HTTPTargetConnection` includes:
- `KeyStore` reference (`ref://api-beta`).
- `KeyAlias` matching the uploaded certificate alias.
- (Optional) `TrustStore` reference for validating backend certificates.

### 4️⃣ Verify the Configuration
After setting up SSL, test the connection using:
- **Apigee Trace Tool** to check SSL handshake logs.
- **cURL command** with `-v` flag to verify the certificate.
- Ensure that the backend accepts the Apigee client certificate.

---

## 🚀 Best Practices
✅ Use **strong encryption algorithms** for certificates.  
✅ Keep **Keystore and Truststore credentials secure**.  
✅ Periodically **rotate SSL certificates** to maintain security.  
✅ Always test the connection after updating SSL configurations.  
✅ Monitor Apigee logs for **SSL handshake failures** and resolve any certificate mismatches.  

🔐 **With the correct SSL setup, you ensure a secure and seamless connection between Apigee and your backend!** 🚀

