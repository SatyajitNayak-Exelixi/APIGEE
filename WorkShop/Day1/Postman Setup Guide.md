# 🪰 Postman Setup Guide

## 📥 Step 1: Download Postman

**Official Download URL:**
👉 [https://www.postman.com/downloads/](https://www.postman.com/downloads/)

Postman is available for:

* Windows (64-bit)
* macOS (Intel & Apple Silicon)
* Linux (Debian, RedHat, Snap, AppImage)

## 💻 Step 2: Install Postman

### 🔹 For Windows:

* Download the `.exe` file from the link above.
* Run the installer and follow on-screen instructions.
* Once installed, Postman will launch automatically.

### 🔹 For macOS:

* Download the `.zip` file.
* Extract and move Postman to the Applications folder.
* Open Postman via Launchpad or Spotlight.

### 🔹 For Linux (Debian-based):

```bash
sudo apt install ./postman-linux-x64-x.x.x.deb
```

Or using Snap:

```bash
sudo snap install postman
```

## 👤 Step 3: Create or Sign in to Postman Account

* Launch Postman.
* You can sign in with Google, or use email/password.
* Optionally, skip sign-in and use in offline mode.

## 🛠️ Step 4: Basic Setup

### 🔹 Create Your First Request:

* Click **"New" → "HTTP Request"**.
* Enter the request URL (e.g., `https://api.example.com/users`).
* Choose method (GET, POST, etc.).
* Add Headers (e.g., `Content-Type: application/json`).
* Add Body (for POST/PUT) in raw JSON format.

### 🔹 Save the Request:

* Organize your requests in **Collections**.
* Save environments (e.g., dev, prod) using **Environment Variables**.

## 💪 Step 5: Testing APIs

Use the **Tests** tab to write JavaScript-based assertions.

**Example:**

```javascript
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});
```

## 🔐 Step 6: Authentication

Postman supports:

* API Key
* Bearer Token
* Basic Auth
* OAuth 1.0/2.0
* AWS Signature
* Custom Headers

Configure in the **Authorization** tab.

## 📤 Step 7: Import/Export

* Import collections from `.json` files or from Swagger/OpenAPI URLs.
* Export collections and environments to share with your team.
