# 🔁 Apigee Flow Hooks

## 📌 What are Flow Hooks?

**Flow Hooks** in Apigee are **global pre-defined extension points** in the API proxy execution pipeline where you can attach shared flows.

They allow you to execute reusable logic (like logging, auth checks, or quota validation) across **all proxies** in an environment — **without modifying individual API proxies**.

---

## 📍 Flow Hook Types

There are 4 types of flow hooks in Apigee:

| Flow Hook Type       | Trigger Point                          | Common Use Case                         |
|----------------------|-----------------------------------------|------------------------------------------|
| `PreProxyFlowHook`   | Before any proxy logic is executed      | Global auth validation, IP whitelisting |
| `PostProxyFlowHook`  | After proxy logic but before target     | Global logging, request transformation  |
| `PreTargetFlowHook`  | Before target is invoked                | Modify request, set headers             |
| `PostTargetFlowHook` | After receiving response from target    | Global response transformation, logging |

---

## 🔧 How to Configure a Flow Hook

You can attach a **shared flow** to a hook using either **Apigee UI** or **Apigee CLI / APIs**.

### ✅ Example (Apigee CLI)
```bash
apigeecli flowhooks attach \
  --org=my-org \
  --env=test \
  --hook=PreProxyFlowHook \
  --sharedflow=global-auth-validator
