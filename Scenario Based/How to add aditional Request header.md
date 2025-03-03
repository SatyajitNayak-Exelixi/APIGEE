# 🏷️ How to Add an Additional Header in a Request using Assign Message 🌟

## 🔹 Condition to Invoke the Assign Message
Use the following condition to trigger the `AssignMessage` policy when the `IM-SiteCode` header is missing or empty:

```xml
<Condition>(request.header.IM-SiteCode is null) or (request.header.IM-SiteCode = "")</Condition>
```

---

## 🔹 Assign Message Policy
The following `AssignMessage` policy will add the `IM-SiteCode` header with the value **US** if it's not already present:

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<AssignMessage async="false" continueOnError="false" enabled="true" name="AM-AssignSiteCode">
    <DisplayName>AM-AssignSiteCode</DisplayName>
    <Properties/>
    <Set>
        <Headers>
            <Header name="IM-SiteCode">US</Header>
        </Headers>
    </Set>
    <IgnoreUnresolvedVariables>true</IgnoreUnresolvedVariables>
    <AssignTo createNew="false" transport="http" type="request"/>
</AssignMessage>
```

---

## 📌 Key Points
✅ Ensures `IM-SiteCode` is always present in requests.  
✅ Prevents issues caused by missing site codes.  
✅ Lightweight and efficient policy implementation.  

🚀 **Happy Coding!**

