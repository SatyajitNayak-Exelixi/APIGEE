# 🎫 **Apigee Certificate Renewal Guide**

> 🛡️ *Ensuring Secure API Transactions with SSL/TLS Certificates*

---

## 📌 **What is a Certificate?**
A **digital certificate** is an essential security component used to:

✅ **Encrypt** communication between clients and servers.  
✅ **Authenticate** server identities.  
✅ **Prevent** security vulnerabilities like man-in-the-middle attacks.  

---

## 🚀 **Why is a Certificate Needed in Apigee?**

Apigee acts as a secure API gateway, where certificates are crucial for:

🔐 Enabling **HTTPS** for encrypted API communication.  
🔑 Ensuring **trust and verification** between API consumers and providers.  
📜 Maintaining **compliance** with security standards.  

---

## 🔄 **Step-by-Step Certificate Renewal Process**

### 📝 **Step 1: Download the Existing Certificate**
🔹 Log in to **Apigee Management Console**.  
🔹 Navigate to **Admin → SSL Certificates**.  
🔹 Locate the certificate: `renewvue-na-qa.corporate.ingrammicro.com`.  
🔹 Download the current certificate and private key (if required).  

---

### 📩 **Step 2: Raise a Request for Renewal**
🔹 Contact the **Certificate Authority (CA)** team.  
🔹 Submit the downloaded certificate details.  
🔹 Request a **new certificate** renewal.  
🔹 Await the **CA's issuance of the new certificate**.  

---

### 📤 **Step 3: Upload & Replace the New Certificate**
🔹 Log in to **Apigee**.  
🔹 Navigate to **Admin → SSL Certificates**.  
🔹 Upload the **new certificate and private key**.  
🔹 Apply the changes.  

---

### 🔄 **Step 4: Restart Apigee Components**
To apply the updated certificate, restart the services:

🖥️ **Restart the Router:**  
```sh
apigee-service edge-router restart
```

🔄 **Restart the Message Processor:**  
```sh
apigee-service edge-message-processor restart
```

---

### 🔍 **Step 5: Validate the Certificate**
🔹 Verify the applied certificate in Apigee.  
🔹 Use **OpenSSL** to check:
```sh
openssl s_client -connect renewvue-na-qa.corporate.ingrammicro.com:443 -showcerts
```
🔹 Confirm the **updated expiry date**.  
🔹 Conduct API tests to ensure secure connectivity.  

---

## 🎯 **Final Checklist**
✅ **New certificate uploaded successfully**.  
✅ **Restarted necessary Apigee components**.  
✅ **Verified via OpenSSL & browser inspection**.  
✅ **Tested APIs for seamless secure communication**.  

---

🎉 **Congratulations! Your certificate has been renewed successfully!** 🚀

