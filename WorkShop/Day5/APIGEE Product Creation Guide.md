# APIGEE Product Creation Guide

## Table of Contents
1. [What are Products](#what-are-products)
2. [Steps to Create a Product](#steps-to-create-a-product)
   - [Pre-requisite](#pre-requisite)
     - [Create Developer](#create-developer)
   - [Create and Deploy Product](#create-and-deploy-product)
     - [Create Developer App](#create-developer-app)
   - [Updating Product with Credentials](#updating-product-with-credentials)
3. [Adding/Removing APIs from a Product](#addingremoving-apis-from-a-product)

---

## 1. What are Products
Products (also referred to as "Contracts" in Akana terminology) represent a set of APIs offered to external users. These APIs include authentication options such as:
- **Basic Authentication**
- **OAuth**

---

## 2. Steps to Create a Product

### 2.1 Pre-requisite
Before creating a product, ensure that:
- **Product-Specific Developer Apps (Identity)** are created.
- A **Developer** is created.

#### 2.1.1 Create Developer
1. Navigate to **Publish > Developer > +Developer**.
2. On the Developer page:
   - **First Name** and **Username:** APP+Application Name.
   - **Last Name:** User.
   - **Email:** The email address of the user or team requesting credentials.

**Example:**
```
First Name: APPExample
Last Name: User
Username: APPExample
Email: example@companyname.com
```

---

### 2.2 Create and Deploy Product
1. Navigate to **Publish > Product > +Product**.
2. Fill in the following details:
   - **Name and Display Name:** Application Username (e.g., APPExample).
   - **Description:** Purpose of the product.
   - **Environment:** Deployment environment (e.g., QA, DEV, TEST).
   - **Access:** Internal only.
   - **Key Approval:** Automatic.

**Example Configuration:**
```
Environment: usch01 (for internal IM applications)
Access: Internal Only
Key Approval: Automatic
```

---

#### 2.2.1 Create Developer App
1. Navigate to **Publish > Developer Apps > +Developer Apps**.
2. Provide the following:
   - **Name and Display Name:** Application Username + "Identity" (e.g., APPExample-Identity).
   - **Developer:** Select the developer created earlier.
   - **Credentials Tab:**
     - Set **Expiration** to "Never".
     - Select the product created earlier.

**Example:**
```
Name: APPExample-Identity
Developer: APPExample
Product: APPExample
Expiration: Never
```

---

### 2.3 Updating Product with Credentials
This step involves backend updates using a management server call. The following steps outline the process:
1. Build the URL using the following format:
   ```
   https://<Host>:<Port>/v1/organizations/<Organization>/developers/<DeveloperEmail>/apps/<AppIdentity>/keys/create
   ```
   
2. Example:
   ```
   https://<Host>:<Port>/v1/organizations/<Organization>/developers/<DeveloperEmail>/apps/<AppIdentity>/keys/create/v1/organizations/ingrammicro/developers/APPExample@ingrammicro.com/apps/APPExample-Identity/keys/create
   ```
3. Use the **Authorization Header**:
   - Basic Authentication with Apigee Edge login credentials.
   - Ensure "Org Admin" rights are assigned.

4. **Request Body Format:**
   ```json
   {
     "consumerKey": "APPExample",
     "consumerSecret": "APPExample12345"
   }
   ```

---

## 3. Adding/Removing APIs from a Product
1. Navigate to **Publish > Product > [Select Product]**.
2. Click on **Edit**.
3. To manage APIs:
   - Use the **+ API Proxy** option to add.
   - Use the **Delete** option to remove.

---

## Examples and Sample Commands
### cURL Command for QA:
```bash
curl --location 'https://<Host>:<Port>/v1/organizations/<Organization>/developers/<DeveloperEmail>/apps/<AppIdentity>/keys/create/v1/organizations/ingrammicro/developers/APPExample@ingrammicro.com/apps/APPExample-Identity/keys/create' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic <encoded_credentials>' \
--data '{
  "consumerKey": "APPExample",
  "consumerSecret": "APPExample12345"
}'
```

---

## Screenshots
### Navigation: Publish > Developer
![Developer Navigation](path-to-image)

### Navigation: Publish > Developer Apps
![Developer Apps Navigation](path-to-image)

### Sample UI for Creating Developer App
![Create Developer App](path-to-image)

---
