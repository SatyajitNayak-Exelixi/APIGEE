# 📘 API Method Design Guide

This markdown file outlines the methods available in the API, when to use them, and their intended purpose. It serves as a reference guide for developers and integrators.

---

## ✅ Available HTTP Methods & When to Use Them

### 1. **GET**

* **Purpose:** Retrieve data from the server.
* **Use Case Examples:**

  * Fetch user details
  * Get list of products or services
* **Design Notes:**

  * Should not modify any data
  * Can include query parameters for filtering or pagination

### 2. **POST**

* **Purpose:** Create a new resource on the server.
* **Use Case Examples:**

  * Create a new user account
  * Submit a form or order
* **Design Notes:**

  * Request body should contain all necessary data to create the resource

### 3. **PUT**

* **Purpose:** Update or replace an existing resource.
* **Use Case Examples:**

  * Update a user profile completely
* **Design Notes:**

  * Entire object is expected in the request body
  * Use only when you want to replace all fields

### 4. **PATCH**

* **Purpose:** Partially update a resource.
* **Use Case Examples:**

  * Update only a user's email or password
* **Design Notes:**

  * Only include the fields you want to update

### 5. **DELETE**

* **Purpose:** Remove a resource from the server.
* **Use Case Examples:**

  * Delete a user
  * Remove a product from a catalog
* **Design Notes:**

  * Should return 204 No Content if deletion is successful

---

## 📌 Best Practices

* Always use nouns for resource names (e.g., `/users`, `/orders`)
* Use plural form for collections
* Use appropriate status codes: `200`, `201`, `204`, `400`, `404`, `500`
* Secure endpoints using authentication (e.g., OAuth2, API Key)
* Use versioning in the path (e.g., `/api/v1/users`)

---

## 🛠️ Design Reference Table

| Method | Action  | Idempotent | Use When                      |
| ------ | ------- | ---------- | ----------------------------- |
| GET    | Read    | Yes        | Retrieving data               |
| POST   | Create  | No         | Submitting or adding new data |
| PUT    | Replace | Yes        | Full updates of a resource    |
| PATCH  | Modify  | No         | Partial updates               |
| DELETE | Remove  | Yes        | Deleting a resource           |

---

