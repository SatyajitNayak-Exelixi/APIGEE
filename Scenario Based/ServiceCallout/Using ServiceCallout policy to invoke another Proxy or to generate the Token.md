# :label: Using ServiceCallout policy to invoke another Proxy orgenerate the OKTA Token  :high_brightness:

- Follow the steps to Implement ServiceCallout policy.

1. Create a KVM which store the Credential.
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<KeyValueMapOperations continueOnError="false" enabled="true" name="KVM-Operations" mapIdentifier="XRE-OrderDomain">
    <DisplayName>KVM-XRE-OrderDomain</DisplayName>
    <Properties/>
    <ExclusiveCache>false</ExclusiveCache>
    <ExpiryTimeInSecs>30000</ExpiryTimeInSecs>
    <Get assignTo="private.username">
        <Key>
            <Parameter>username</Parameter>
        </Key>
    </Get>
    <Get assignTo="private.password">
        <Key>
            <Parameter>password</Parameter>
        </Key>
    </Get>
    <Scope>environment</Scope>
</KeyValueMapOperations>
```
---

2. Use ServiceCallout policy o generate the token .
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<ServiceCallout async="false" continueOnError="false" enabled="true" name="SC-JWTToken">
    <DisplayName>SC-JWTToken</DisplayName>
    <Properties/>
    <Request clearPayload="true" variable="myRequest">
        <Set>
            <Verb>GET</Verb>
            <Path>/Decisions/Primary/REST/AccountService/LoginAndGetJWTToken</Path>
            <QueryParams>
                <QueryParam name="outputType">JSON</QueryParam>
                <QueryParam name="userName">{private.username}</QueryParam>
                <QueryParam name="password">{private.password}</QueryParam>
            </QueryParams>
        </Set>
        <IgnoreUnresolvedVariables>false</IgnoreUnresolvedVariables>
    </Request>
    <Response>calloutResponse</Response>
    <HTTPTargetConnection>
        <URL>https://xre-stg.corporate.ingrammicro.com</URL>
    </HTTPTargetConnection>
</ServiceCallout>
```
---

3. Extract the token using ExtractVariables Policy.
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<ExtractVariables async="false" continueOnError="false" enabled="true" name="EV-JWTToken">
    <DisplayName>EV-JWTToken</DisplayName>
    <IgnoreUnresolvedVariables>true</IgnoreUnresolvedVariables>
    <Source clearPayload="false">calloutResponse.content</Source>
    <VariablePrefix>apigee</VariablePrefix>
    <JSONPayload>
        <Variable name="JWTToken" type="string">
            <JSONPath>$.LoginAndGetJWTTokenResult</JSONPath>
        </Variable>
    </JSONPayload>
</ExtractVariables>
```
---

4. Assign the token in the request header while sending the request to the Targer Server.
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<AssignMessage async="false" continueOnError="false" enabled="true" name="AM-ModifyHeader">
    <DisplayName>AM-ModifyHeader</DisplayName>
    <Properties/>
    <Set>
        <Verb>POST</Verb>
        <Headers>
            <Header name="Authorization">Bearer {apigee.JWTToken}</Header>
            <Header name="Content-Type">text/plain</Header>
            <Header name="Accept">*/*</Header>
        </Headers>
        <Payload contentType="application/json">{request.content}</Payload>
    </Set>
    <IgnoreUnresolvedVariables>true</IgnoreUnresolvedVariables>
    <AssignTo createNew="true" transport="http" type="request"/>
</AssignMessage>
```

