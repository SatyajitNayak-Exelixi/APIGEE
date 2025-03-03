# 🎯 Handling Large Payloads in Apigee: Processing Only 50 Student Records

## 📌 Scenario Overview
A client is making a `POST` request to:
```
https://api-beta.exelixi.com:443/cms
```
with a request body containing an **array of 100 student objects**. However, the backend can only process **50 student records** at a time. The goal is to limit the number of student records sent to the backend URL:
```
https://api-beta.exelixi.com:443/de/api/data
```

## 🛠 Solution Approach
To achieve this in Apigee, we will:
1. **Extract** the request body and parse it as a JSON array.
2. **Truncate** the array to **50 student records**.
3. **Forward** only the first **50 student objects** to the backend.
4. **Respond** to the client with the processed student records.

---

## 🚀 Implementation Steps

### **Step 1: Extract the Request Body**
Use an **AssignMessage Policy** to store the incoming request body in a variable:
```xml
<AssignMessage name="ExtractRequestBody">
    <AssignVariable>
        <Name>requestBody</Name>
        <Value>{request.content}</Value>
    </AssignVariable>
    <IgnoreUnresolvedVariables>false</IgnoreUnresolvedVariables>
</AssignMessage>
```

### **Step 2: Process the JSON Array Using JavaScript**
Use a **JavaScript Policy** to limit the student records to 50:
```xml
<Javascript name="LimitStudentRecords">
    <ResourceURL>jsc://LimitStudents.js</ResourceURL>
</Javascript>
```

Create a **JavaScript file** (`LimitStudents.js`) with the following logic:
```javascript
var requestBody = context.getVariable("requestBody");
var studentData = JSON.parse(requestBody);

if (studentData.length > 50) {
    studentData = studentData.slice(0, 50);
}

context.setVariable("updatedRequestBody", JSON.stringify(studentData));
```

### **Step 3: Modify the Request Body Before Sending to Backend**
Use another **AssignMessage Policy** to update the request body:
```xml
<AssignMessage name="ModifyRequestBody">
    <AssignVariable>
        <Name>request.content</Name>
        <Ref>updatedRequestBody</Ref>
    </AssignVariable>
    <IgnoreUnresolvedVariables>false</IgnoreUnresolvedVariables>
</AssignMessage>
```

### **Step 4: Forward to Backend**
The processed data (first 50 student records) is sent to:
```
https://api-beta.exelixi.com:443/de/api/data
```
using the default API proxy routing.

---

## 🎯 Benefits of This Approach
✅ Ensures **backend stability** by preventing excessive data processing.
✅ **Improves performance** by handling only necessary records.
✅ **Maintains API efficiency** without rejecting the full request.
✅ **Flexible & Scalable**, allowing future modifications for different limits.

---

## 🔥 Conclusion
By combining **AssignMessage, JavaScript, and Routing Policies**, we efficiently limit and forward only the first **50 student records** while maintaining API performance and backend constraints. 🚀

