# 🔑 How to Send Basic Credentials to Backend Using KVM

## 📌 Scenario
In real-world API integrations, you may need to **securely store and retrieve credentials** instead of hardcoding them.
Using **Key-Value Maps (KVM)** in Apigee, you can securely store credentials and pass them to the backend via the **BasicAuthentication policy**.

---

## 🔹 Step 1: Store Credentials in KVM
Create a **KVM entry** to store the username and password securely in an **environment-scoped Key-Value Map**.

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<KeyValueMapOperations name="KVM-Operation" enabled="true" continueOnError="false" async="false" mapIdentifier="USITIMAppEAI">
    <DisplayName>KVM-Operation</DisplayName>
    <Properties/>
    <ExclusiveCache>false</ExclusiveCache>
    <ExpiryTimeInSecs>300</ExpiryTimeInSecs>
    <Get assignTo="private.Username" index="1">
        <Key>
            <Parameter>Username</Parameter>
        </Key>
    </Get>
    <Get assignTo="private.Password" index="1">
        <Key>
            <Parameter>Password</Parameter>
        </Key>
    </Get>
    <Scope>environment</Scope>
</KeyValueMapOperations>
```

🔹 **What this does:**
- Retrieves **Username** and **Password** from the KVM.
- Assigns them to variables `private.Username` and `private.Password`.
- Uses `environment` scope for security and separation.
- Uses caching (`ExpiryTimeInSecs=300`) to improve performance.

---

## 🔹 Step 2: Encode and Pass Credentials in the Request
Now, use **BasicAuthentication** policy to encode credentials and attach them to the request header.

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<BasicAuthentication async="false" continueOnError="false" enabled="true" name="BA-Authentication">
    <DisplayName>BA-Authentication</DisplayName>
    <Operation>Encode</Operation>
    <IgnoreUnresolvedVariables>false</IgnoreUnresolvedVariables>
    <User ref="private.Username"/>
    <Password ref="private.Password"/>
    <AssignTo createNew="true">request.header.Authorization</AssignTo>
</BasicAuthentication>
```

🔹 **What this does:**
- Takes `private.Username` and `private.Password` from KVM.
- Encodes them in **Base64** format as per Basic Auth standards.
- Sets the encoded credentials in the `Authorization` header.

---

## 🔹 Step 3: Configure KVM in Apigee UI
To add credentials to KVM manually:

1️⃣ **Go to** Apigee UI → Admin → Key-Value Maps.  
2️⃣ **Select Environment Scope** (e.g., `prod`, `test`).  
3️⃣ **Create a new Key-Value Map** named `USITIMAppEAI`.  
4️⃣ **Add Entries**:
   - `Username` → `your_api_username`
   - `Password` → `your_secure_password`

🚀 Now, Apigee will retrieve these values dynamically!

---

## 🔹 Real-Time Use Case
### 🏢 **Example: Secure API Call to Backend**
Imagine you are calling a backend service that requires Basic Authentication:

🔹 **Backend API:** `https://api.example.com/protected-endpoint`
🔹 **Without KVM:** You would have to hardcode credentials in policies (not secure!).
🔹 **With KVM:** You securely store them and dynamically retrieve them before sending the request.

📌 This ensures **security, maintainability, and flexibility** in managing credentials!

---

## ✅ Best Practices
✔️ **Never hardcode sensitive credentials** in policies or code.  
✔️ **Use KVMs with environment scope** to keep credentials organized.  
✔️ **Rotate credentials periodically** to enhance security.  
✔️ **Ensure encryption is enabled** in your Apigee KVM settings.  
✔️ **Limit access to KVMs** to authorized users only.  

🔐 **By implementing this approach, you ensure secure and scalable API authentication!** 🚀

