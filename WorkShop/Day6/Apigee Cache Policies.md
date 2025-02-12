# 🚀 Apigee Cache Policies: Response Cache, Lookup Cache, Populate Cache & Invalidate Cache

## 🔹 Overview

Apigee provides **cache management policies** to improve API performance, reduce backend load, and enhance response time. The four key cache policies are:

1. **Response Cache** – Caches entire API responses.
2. **Lookup Cache** – Retrieves values from the cache.
3. **Populate Cache** – Stores values into the cache.
4. **Invalidate Cache** – Removes cache entries.

---

## 📌 **1. Response Cache Policy**

The **Response Cache Policy** stores complete API responses in memory, allowing faster retrieval without hitting the backend.

### ✅ **How It Works**
- When a request is made, Apigee first checks if the response is cached.
- If a cached response exists, it is returned immediately.
- If no cached response exists, the request is forwarded to the backend, and the response is cached.

### 🔧 **Example Response Cache Policy**
```xml
<ResponseCache name="Cache-API-Response">
    <CacheKey>
        <KeyFragment ref="request.uri"/>
    </CacheKey>
    <ExpirySettings>
        <TimeoutInSeconds>300</TimeoutInSeconds> <!-- Cache for 5 minutes -->
    </ExpirySettings>
</ResponseCache>
```

### ⚡ **Use Case**
✔ Reduces redundant backend calls.
✔ Improves API response time.
✔ Ideal for static or infrequently changing responses (e.g., **product catalogs, weather data**).

---

## 📌 **2. Lookup Cache Policy**

The **Lookup Cache Policy** retrieves values from the cache **without calling the backend**.

### ✅ **How It Works**
- Checks if a specific key exists in the cache.
- If found, returns the cached value.
- If not found, calls the backend and stores the result.

### 🔧 **Example Lookup Cache Policy**
```xml
<LookupCache name="Retrieve-Cache-Value">
    <CacheKey>
        <KeyFragment ref="request.queryparam.userId"/>
    </CacheKey>
    <AssignTo variable="cachedResponse"/>
</LookupCache>
```

### ⚡ **Use Case**
✔ Fetches **cached user session data**.
✔ Reduces the number of backend hits for frequently accessed values.
✔ Useful for **authentication tokens, user preferences, and configurations**.

---

## 📌 **3. Populate Cache Policy**

The **Populate Cache Policy** stores values into the cache for future use.

### ✅ **How It Works**
- When a request is processed, the response or specific values are stored in the cache.
- Future requests retrieve the cached value using **Lookup Cache**.

### 🔧 **Example Populate Cache Policy**
```xml
<PopulateCache name="Store-User-Data">
    <CacheKey>
        <KeyFragment ref="request.queryparam.userId"/>
    </CacheKey>
    <Scope>Exclusive</Scope>
    <ExpirySettings>
        <TimeoutInSeconds>600</TimeoutInSeconds> <!-- Cache for 10 minutes -->
    </ExpirySettings>
    <Source>response.content</Source>
</PopulateCache>
```

### ⚡ **Use Case**
✔ Caches user session data after authentication.
✔ Stores **computed results** to avoid redundant processing.
✔ Improves **API efficiency** for frequently accessed data.

---

## 📌 **4. Invalidate Cache Policy**

The **Invalidate Cache Policy** removes cached data, ensuring updated values are fetched from the backend.

### ✅ **How It Works**
- Deletes cache entries based on a key.
- Ensures **stale data is removed** when updates occur.

### 🔧 **Example Invalidate Cache Policy**
```xml
<InvalidateCache name="Clear-User-Cache">
    <CacheKey>
        <KeyFragment ref="request.queryparam.userId"/>
    </CacheKey>
</InvalidateCache>
```

### ⚡ **Use Case**
✔ Clears **user-specific cached data** when the user logs out.
✔ Ensures **real-time data updates** when records are modified.
✔ Useful for **inventory, financial transactions, and user settings**.

---

## 🎯 **When to Use Which Policy?**

| **Policy**            | **Use Case**                                      | **Example**                        |
|-----------------------|--------------------------------------------------|------------------------------------|
| Response Cache       | Cache entire API responses                      | Store weather data, product info  |
| Lookup Cache        | Retrieve specific values from the cache           | Fetch user session data          |
| Populate Cache      | Store specific values in the cache                | Cache user authentication tokens |
| Invalidate Cache    | Remove outdated or unwanted cache entries         | Delete user session on logout    |

---

## 🚀 **Conclusion**

✅ **Response Cache** speeds up API responses by caching full responses.
✅ **Lookup Cache** retrieves specific cached values efficiently.
✅ **Populate Cache** stores necessary values in the cache for later use.
✅ **Invalidate Cache** ensures data freshness by clearing outdated cache entries.

By implementing **Apigee caching policies**, you can optimize API performance, reduce backend load, and ensure a **better user experience**! 🚀🔥

