# Apigee Fault Handling Examples

## Part 1: FaultRules with AssignMessage

Below are 5 examples of using **FaultRules** with **AssignMessage** in Apigee.

### Example 1: Simple Fault Rule for 404 Error

```xml
<FaultRules>
    <FaultRule name="Handle-NotFound">
        <Step>
            <Name>Assign-404-Response</Name>
        </Step>
    </FaultRule>
</FaultRules>

<AssignMessage name="Assign-404-Response">
    <Set>
        <StatusCode>404</StatusCode>
        <ReasonPhrase>Not Found</ReasonPhrase>
        <Payload contentType="application/json">
            { "error": "The requested resource was not found" }
        </Payload>
    </Set>
    <AssignTo createNew="true" transport="http" type="response"/>
</AssignMessage>
```

---

### Example 2: Fault Rule for 500 Internal Server Error

```xml
<FaultRules>
    <FaultRule name="Handle-InternalError">
        <Step>
            <Name>Assign-500-Response</Name>
        </Step>
    </FaultRule>
</FaultRules>

<AssignMessage name="Assign-500-Response">
    <Set>
        <StatusCode>500</StatusCode>
        <ReasonPhrase>Internal Server Error</ReasonPhrase>
        <Payload contentType="application/json">
            { "error": "Unexpected server error occurred" }
        </Payload>
    </Set>
    <AssignTo createNew="true" transport="http" type="response"/>
</AssignMessage>
```

---

### Example 3: Fault Rule with Custom Header

```xml
<FaultRules>
    <FaultRule name="Handle-Unauthorized">
        <Step>
            <Name>Assign-401-Response</Name>
        </Step>
    </FaultRule>
</FaultRules>

<AssignMessage name="Assign-401-Response">
    <Set>
        <StatusCode>401</StatusCode>
        <ReasonPhrase>Unauthorized</ReasonPhrase>
        <Headers>
            <Header name="WWW-Authenticate">Bearer realm="example"</Header>
        </Headers>
        <Payload contentType="application/json">
            { "error": "Unauthorized access" }
        </Payload>
    </Set>
    <AssignTo createNew="true" transport="http" type="response"/>
</AssignMessage>
```

---

### Example 4: Fault Rule with Variable in Response

```xml
<FaultRules>
    <FaultRule name="Handle-BadRequest">
        <Step>
            <Name>Assign-400-Response</Name>
        </Step>
    </FaultRule>
</FaultRules>

<AssignMessage name="Assign-400-Response">
    <Set>
        <StatusCode>400</StatusCode>
        <ReasonPhrase>Bad Request</ReasonPhrase>
        <Payload contentType="application/json">
            { "error": "Invalid input: {request.queryparam.invalid}" }
        </Payload>
    </Set>
    <AssignTo createNew="true" transport="http" type="response"/>
</AssignMessage>
```

---

### Example 5: Catch-All Fault Rule

```xml
<FaultRules>
    <FaultRule name="CatchAll">
        <Step>
            <Name>Assign-Default-Error</Name>
        </Step>
    </FaultRule>
</FaultRules>

<AssignMessage name="Assign-Default-Error">
    <Set>
        <StatusCode>400</StatusCode>
        <ReasonPhrase>Bad Request</ReasonPhrase>
        <Payload contentType="application/json">
            { "error": "Something went wrong. Please try again later." }
        </Payload>
    </Set>
    <AssignTo createNew="true" transport="http" type="response"/>
</AssignMessage>
```

### Example 5: Handling Quota Exceeded Error

```xml
<FaultRules>
    <FaultRule name="Quota-Exceeded">
        <Step>
            <Name>Assign-QuotaExceeded-Response</Name>
        </Step>
    </FaultRule>
</FaultRules>

<AssignMessage name="Assign-QuotaExceeded-Response">
    <Set>
        <StatusCode>429</StatusCode>
        <ReasonPhrase>Too Many Requests</ReasonPhrase>
        <Payload contentType="application/json">
            { "error": "API quota exceeded. Please try again later." }
        </Payload>
    </Set>
    <AssignTo createNew="true" transport="http" type="response"/>
</AssignMessage>
```

---

## Part 2: RaiseFault Policies

Below are 5 examples of using **RaiseFault** policies in Apigee.

### Example 1: Simple RaiseFault with 400 Error

```xml
<RaiseFault name="Raise-400">
    <FaultResponse>
        <Set>
            <StatusCode>400</StatusCode>
            <ReasonPhrase>Bad Request</ReasonPhrase>
            <Payload contentType="application/json">
                { "error": "Invalid request format" }
            </Payload>
        </Set>
    </FaultResponse>
</RaiseFault>
```

---

### Example 2: RaiseFault for 401 Unauthorized

```xml
<RaiseFault name="Raise-401">
    <FaultResponse>
        <Set>
            <StatusCode>401</StatusCode>
            <ReasonPhrase>Unauthorized</ReasonPhrase>
            <Payload contentType="application/json">
                { "error": "Authentication required" }
            </Payload>
        </Set>
    </FaultResponse>
</RaiseFault>
```

---

### Example 3: RaiseFault for 403 Forbidden

```xml
<RaiseFault name="Raise-403">
    <FaultResponse>
        <Set>
            <StatusCode>403</StatusCode>
            <ReasonPhrase>Forbidden</ReasonPhrase>
            <Payload contentType="application/json">
                { "error": "You do not have permission to access this resource" }
            </Payload>
        </Set>
    </FaultResponse>
</RaiseFault>
```

---

### Example 4: RaiseFault for 404 Not Found

```xml
<RaiseFault name="Raise-404">
    <FaultResponse>
        <Set>
            <StatusCode>404</StatusCode>
            <ReasonPhrase>Not Found</ReasonPhrase>
            <Payload contentType="application/json">
                { "error": "The requested resource was not found" }
            </Payload>
        </Set>
    </FaultResponse>
</RaiseFault>
```

---

### Example 5: RaiseFault with Custom Headers

```xml
<RaiseFault name="Raise-429">
    <FaultResponse>
        <Set>
            <StatusCode>429</StatusCode>
            <ReasonPhrase>Too Many Requests</ReasonPhrase>
            <Headers>
                <Header name="Retry-After">60</Header>
            </Headers>
            <Payload contentType="application/json">
                { "error": "Rate limit exceeded. Please retry after 60 seconds." }
            </Payload>
        </Set>
    </FaultResponse>
</RaiseFault>
```

---

