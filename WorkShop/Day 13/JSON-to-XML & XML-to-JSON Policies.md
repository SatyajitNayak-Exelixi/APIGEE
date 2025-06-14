# 🔄 **JSON-to-XML & XML-to-JSON Policies in Apigee** 🔄

## 🌟 **Introduction**

APIs often exchange data in different formats like **JSON** and **XML**. Apigee provides **JSON-to-XML** and **XML-to-JSON** policies to transform request and response payloads dynamically, ensuring seamless integration between systems that use different data formats.

---

## 🎯 **Real-Time Scenario**

### **Scenario:**
Imagine you are developing an API that connects a **modern mobile application** (which uses **JSON**) with a **legacy system** that only understands **XML**.

### **Problem:**
- The **mobile app** sends JSON requests, but the **legacy system** requires XML.
- The **legacy system** responds in XML, but the **mobile app** understands only JSON.

### **Solution:**
- **Apigee JSON-to-XML Policy**: Converts JSON requests into XML before sending them to the legacy system.
- **Apigee XML-to-JSON Policy**: Converts XML responses back to JSON before delivering them to the mobile app.

---

## 🔑 **How These Policies Work in Apigee**

### **1️⃣  JSON-to-XML Policy** TO CONVERT A JSON PAYLOAD TO XML.
- Converts incoming JSON response payloads into XML format.

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<JSONToXML name="JSON-To-XML-Convert">
    <DisplayName>Convert JSON to XML</DisplayName>
    <Source>response</Source> <!-- or "request" depending on where your JSON is -->
    <OutputVariable>convertedXml</OutputVariable>
    <Format>yahoo</Format> <!-- "yahoo" gives a simpler XML, "standard" is more verbose -->
</JSONToXML>
```

```xml
<AssignMessage name="ReturnConvertedXML">
    <DisplayName>Return XML</DisplayName>
    <AssignTo createNew="false" transport="http">response</AssignTo>
    <Set>
        <Payload contentType="application/xml">{convertedXml}</Payload>
    </Set>
</AssignMessage>
```
---

### **2️⃣ XML-to-JSON Policy** TO CONVERT A XML RESPONSE PAYLOAD TO JSON.

- Converts incoming XML response payloads into JSON format.

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<XMLToJSON name="Convert-XML-To-JSON">
    <DisplayName>Convert XML To JSON</DisplayName>
    <Source>response</Source>
    <!-- Or 'request' if you're converting request body -->
    <OutputVariable>convertedJson</OutputVariable>
    <Options>
        <ObjectRootElementName>root</ObjectRootElementName>
        <SuppressJsonNull>false</SuppressJsonNull>
    </Options>
</XMLToJSON>
```

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<AssignMessage name="Return-JSON">
    <AssignVariable>
        <Name>response.content</Name>
        <Ref>convertedJson</Ref>
    </AssignVariable>
    <AssignVariable>
        <Name>response.header.Content-Type</Name>
        <Value>application/json</Value>
    </AssignVariable>
    <IgnoreUnresolvedVariables>false</IgnoreUnresolvedVariables>
    <AssignTo createNew="false" transport="http" type="response"/>
</AssignMessage>
```
---

## 🚀 **Benefits of JSON-to-XML & XML-to-JSON Policies in Apigee**
✅ **Seamless Integration** – Connect modern and legacy systems effortlessly.
✅ **Automated Transformation** – No need for manual conversion in backend systems.
✅ **Performance Optimization** – Reduces API complexity and enhances performance.

---

## **Conclusion**
Apigee’s **JSON-to-XML** and **XML-to-JSON** policies make it easy to integrate APIs with different data formats. They are widely used in **banking, e-commerce, and enterprise systems** where legacy applications need to interact with modern APIs.

🚀 **Implement these policies today for smoother API communication!**

