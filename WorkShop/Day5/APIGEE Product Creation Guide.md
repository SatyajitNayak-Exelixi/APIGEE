# 🌐 **APIGEE Product Creation Guide**

---

## 📖 **1. What are Products?**
Products (also referred to as **"Contracts"** in Akana terminology) represent a set of APIs offered to external users. These APIs include authentication options such as:
- 🔒 **Basic Authentication**
- 🔑 **OAuth**

---

## 🛠️ **2. Steps to Create a Product**

### 📝 **2.1 Pre-requisite**
Before creating a product, ensure the following:
- ✅ **Product-Specific Developer Apps (Identity)** are created.
- ✅ A **Developer** is created.

#### 🔍 **2.1.1 Create Developer**
1. Navigate to **Publish > Developer > +Developer**.
2. On the Developer page, fill in the following details:
   - **First Name** and **Username:** APP+Application Name.
   - **Last Name:** User.
   - **Email:** The email address of the user or team requesting credentials.

> **Example:**
> ```
> First Name: APPExample
> Last Name: User
> Username: APPExample
> Email: example@companyname.com
> ```

---

### 🔒 **2.2 Create and Deploy Product**
1. Navigate to **Publish > Product > +Product**.
2. Fill in the details:
   - 🏛️ **Name and Display Name:** Application Username (e.g., APPExample).
   - 🖱️ **Description:** Purpose of the product.
   - 🌍 **Environment:** Deployment environment (e.g., QA, DEV, TEST).
   - 🔒 **Access:** Internal only.
   - ⚙️ **Key Approval:** Automatic.

> **Example Configuration:**
> ```
> Environment: usch01 (for internal IM applications)
> Access: Internal Only
> Key Approval: Automatic
> ```

---

#### **2.2.1 Create Developer App**
1. Navigate to **Publish > Developer Apps > +Developer Apps**.
2. Provide the following details:
   - 🏛️ **Name and Display Name:** Application Username + "Identity" (e.g., APPExample-Identity).
   - 👤 **Developer:** Select the developer created earlier.
   - 🔐 **Credentials Tab:**
     - Set **Expiration** to "Never".
     - Select the product created earlier.

> **Example:**
> ```
> Name: APPExample-Identity
> Developer: APPExample
> Product: APPExample
> Expiration: Never
> ```

---

### 🔧 **2.3 Updating Product with Credentials**
This step involves backend updates using a management server call. 

#### **Steps:**
1. **Build the URL** using the format below:
   ```
   https://<Host>:<Port>/v1/organizations/<Organization>/developers/<DeveloperEmail>/apps/<AppIdentity>/keys/create
   ```
   
   > **Example:**
   > ```
   > https://<Host>:<Port>/v1/organizations/<Organization>/developers/<DeveloperEmail>/apps/<AppIdentity>/keys/create/v1/organizations/ingrammicro/developers/APPExample@ingrammicro.com/apps/APPExample-Identity/keys/create
   > ```

2. **Authorization Header**:
   - Use Basic Authentication with Apigee Edge login credentials.
   - Ensure **Org Admin** rights are assigned.

3. **Request Body Format**:
   ```json
   {
     "consumerKey": "APPExample",
     "consumerSecret": "APPExample12345"
   }
   ```

---

## 🔄 **3. Adding/Removing APIs from a Product**
1. Navigate to **Publish > Product > [Select Product]**.
2. Click on **Edit**.
3. To manage APIs:
   - ➕ Use the **+ API Proxy** option to add.
   - 🗑️ Use the **Delete** option to remove.

---

## 📌 **Examples and Sample Commands**

### 🖥️ **cURL Command for QA:**
```bash
curl --location 'https://<Host>:<Port>/v1/organizations/<Organization>/developers/<DeveloperEmail>/apps/<AppIdentity>/keys/create/v1/organizations/organizationName/developers/APPExample@companyname.com/apps/APPExample-Identity/keys/create' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic <encoded_credentials>' \
--data '{
  "consumerKey": "APPExample",
  "consumerSecret": "APPExample12345"
}'
```

---

## 🖼️ **Visual Aids**
> **Add images at appropriate places to enhance understanding.**  
For example:
- Add screenshots of **Publish > Developer > +Developer** form.  
- Add screenshots of **Developer Apps** and **Product Settings**.

---

## 🎯 **Conclusion**
This guide outlines the steps to create, deploy, and manage products in Apigee. Follow the detailed steps to ensure seamless API product deployment and integration.
