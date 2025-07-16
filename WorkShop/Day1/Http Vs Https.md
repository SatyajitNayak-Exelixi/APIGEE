# :globe_with_meridians: HTTP vs HTTPS & Status Codes :lock:

## :earth_asia: What is HTTP?
**HyperText Transfer Protocol (HTTP)** is a communication protocol used for transferring data over the web.

### :point_right: Characteristics:
- Sends and receives data without encryption.
- Prone to security risks like *man-in-the-middle* attacks.
- Faster but less secure.

### :zap: Example:
```
http://www.example.com
```

---

## :lock: What is HTTPS?
**HyperText Transfer Protocol Secure (HTTPS)** is the secure version of HTTP, using encryption for safe communication.It is the secure version of HTTP, where data between your browser and the server is encrypted.

### :point_right: Characteristics:
- Uses **SSL/TLS** encryption to protect data.
- Ensures authentication, integrity, and confidentiality.
- Required for secure transactions (banking, login pages, etc.).

### :closed_lock_with_key: Example:
```
https://www.example.com
```

---

## :bar_chart: HTTP Status Codes
Status codes indicate the response from a server. They are grouped into **5 categories**:

| **Status Code** | **Meaning**                       | **Example**                      |
|---------------|---------------------------------|---------------------------------|
| **1xx**      | *Informational* - Request received | `100 Continue`                  |
| **2xx**      | *Success* - Request processed successfully | `200 OK`, `201 Created`         |
| **3xx**      | *Redirection* - Further action needed | `301 Moved Permanently`         |
| **4xx**      | *Client Errors* - Request issue   | `400 Bad Request`, `404 Not Found` |
| **5xx**      | *Server Errors* - Server failure | `500 Internal Server Error`     |

---

## :fire: Quick Summary
| Feature | HTTP | HTTPS |
|---------|------|------|
| Security | Not secure | Encrypted via SSL/TLS |
| Performance | Faster | Slightly slower (encryption overhead) |
| Use Case | General browsing | Secure transactions, logins |

:bulb: **Tip:** Always prefer HTTPS for security and trustworthiness!
