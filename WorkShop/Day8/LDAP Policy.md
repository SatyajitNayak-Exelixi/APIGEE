# 🔐 Apigee LDAP Policy

> 🛡️ Secure authentication and authorization using **LDAP Policy** in Apigee.

---

## 🎯 **What is LDAP Policy in Apigee?**

The **LDAP (Lightweight Directory Access Protocol) Policy** in Apigee allows APIs to authenticate users against an **LDAP directory**, such as **Active Directory (AD)**. It helps in validating **user credentials** and retrieving **user attributes** before processing API requests.

✅ Common Use Cases:
- User authentication against **Active Directory**
- Role-based access control (RBAC)
- Fetching user details from an LDAP server
- Implementing Single Sign-On (SSO) for APIs

---

## 🛠 **Example: Authenticating Users with LDAP Policy**

The following **LDAP Policy** validates user credentials by connecting to an **LDAP server**.

```xml
<LDAP name="LDAPAuthenticationPolicy">
    <Connection>
        <Host>ldap://ldap.example.com</Host>
        <Port>389</Port>
        <BaseDN>dc=example,dc=com</BaseDN>
        <ConnectionTimeoutInMilliseconds>3000</ConnectionTimeoutInMilliseconds>
    </Connection>
    <Authentication>
        <BindDN>cn=admin,dc=example,dc=com</BindDN>
        <Password ref="private.ldapPassword"/>
    </Authentication>
    <Search>
        <Filter>(uid={request.queryparam.username})</Filter>
        <Scope>subtree</Scope>
    </Search>
    <Response>
        <StatusCode>200</StatusCode>
        <Message>✅ Authentication Successful!</Message>
    </Response>
</LDAP>
```

### ✅ **How It Works:**
1. Connects to the LDAP server at `ldap.example.com`.
2. Authenticates using **BindDN credentials**.
3. Searches for the user with **username** from query params.
4. If found, it **authenticates successfully**.

---

## 🔥 **Real-time Scenario: LDAP Authentication for Employee Login**

Imagine an enterprise **employee portal** where employees log in using their **corporate credentials**. Instead of storing credentials in the API backend, the API integrates with the company’s **Active Directory (AD) via LDAP** to verify user identity.

### 🚀 **Implementation:**
1. User enters **username & password** on the login page.
2. API sends a request to Apigee with **LDAP Policy**.
3. Apigee authenticates against **Active Directory**.
4. If authentication is successful, the employee accesses the portal.
5. If authentication fails, access is denied with a `401 Unauthorized` error.

🔹 **LDAP-based authentication ensures:**
- 🔒 **Centralized User Management**
- 🔄 **No need to store passwords in API backend**
- 🔥 **Seamless integration with corporate authentication systems**

---

## ✅ **Summary**

🔹 **Apigee LDAP Policy** provides **secure authentication** by integrating APIs with **LDAP/Active Directory**.

🔹 **Key Benefits:**
- 🔐 **Centralized Authentication**
- 🎯 **Improved Security & Access Control**
- 🚀 **Seamless User Management**

💡 Let me know if you need further enhancements! 🚀

