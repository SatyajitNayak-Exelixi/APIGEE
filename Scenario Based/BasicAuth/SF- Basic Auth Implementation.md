# :label: To Validate Basic Auth In Apigee :high_brightness:

- Below is the Flow .
```xml
<Step>
        <Name>EV-ExtractSoapCredentials</Name>
    </Step>
    <Step>
        <Name>DecodeBasicAuthentication</Name>
        <Condition>(request.querystring != "wsdl" and request.querystring != "WSDL") and ((request.header.Content-Type !~ "*xml*") and (request.header.Authorization !~ "Bearer*"))</Condition>
    </Step>
    <Step>
        <Name>VerifyAPIKey</Name>
        <Condition>(request.querystring != "wsdl" and request.querystring != "WSDL") and ((request.header.Content-Type !~ "*xml*") and (request.header.Authorization !~ "Bearer*") and (request.header.Authorization ~ "Basic*"))</Condition>
    </Step>
    <Step>
        <Name>RF_LoginError</Name>
        <Condition>(request.querystring != "wsdl" and request.querystring != "WSDL")  and ((verifyapikey.VerifyAPIKey-SOAP.client_secret != password) or (verifyapikey.VerifyAPIKey.client_secret != request.header.password))</Condition>
    </Step>
```

---


- Use this Extract variable to extract the Credential from the XML Body .

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<ExtractVariables async="false" continueOnError="false" enabled="true" name="EV-ExtractSoapCredentials">
    <DisplayName>EV-ExtractSoapCredentials</DisplayName>
    <Properties/>
    <IgnoreUnresolvedVariables>true</IgnoreUnresolvedVariables>
    <Source clearPayload="false">request</Source>
    <XMLPayload stopPayloadProcessing="false">
        <Namespaces>
            <Namespace prefix="soap-env">http://schemas.xmlsoap.org/soap/envelope/</Namespace>
            <Namespace prefix="soap">http://www.w3.org/2003/05/soap-envelope</Namespace>
            <Namespace prefix="typ">http://www.ingrammicro.com/common/ServiceRequestHeader_v2_2/types</Namespace>
            <Namespace prefix="ser">http://www.ingrammicro.com/ServiceRequestHeader</Namespace>
        </Namespaces>
        <Variable name="username" type="string">
            <XPath>//applicationCredential/ID/text()</XPath>
        </Variable>
        <Variable name="username" type="string">
            <XPath>//ApplicationCredential/ID/text()</XPath>
        </Variable>
        <Variable name="username" type="string">
            <XPath>/soap-env:Envelope/soap-env:Header/typ:ServiceRequestHeader/typ:ApplicationCredential/typ:ID/text()</XPath>
        </Variable>
        <Variable name="username" type="string">
            <XPath>/soap-env:Envelope/soap-env:Header/ser:ServiceRequestHeader/ser:applicationCredential/ser:ID/text()</XPath>
        </Variable>
        <Variable name="username" type="string">
            <XPath>/soap-env:Envelope/soap-env:Header/ser:ServiceRequestHeader/ser:ApplicationCredential/ser:ID/text()</XPath>
        </Variable>
        <Variable name="username" type="string">
            <XPath>/soap:Envelope/soap:Header/ser:ServiceRequestHeader/ser:ApplicationCredential/ser:ID/text()</XPath>
        </Variable>
        <Variable name="password" type="string">
            <XPath>//applicationCredential/credential/text()</XPath>
        </Variable>
        <Variable name="password" type="string">
            <XPath>//ApplicationCredential/Credential/text()</XPath>
        </Variable>
        <Variable name="password" type="string">
            <XPath>/soap-env:Envelope/soap-env:Header/typ:ServiceRequestHeader/typ:ApplicationCredential/typ:Credential/text()</XPath>
        </Variable>
        <Variable name="password" type="string">
            <XPath>/soap-env:Envelope/soap-env:Header/ser:ServiceRequestHeader/ser:applicationCredential/ser:Password/text()</XPath>
        </Variable>
        <Variable name="password" type="string">
            <XPath>/soap-env:Envelope/soap-env:Header/ser:ServiceRequestHeader/ser:ApplicationCredential/ser:Password/text()</XPath>
        </Variable>
        <Variable name="password" type="string">
            <XPath>/soap:Envelope/soap:Header/ser:ServiceRequestHeader/ser:ApplicationCredential/ser:Password/text()</XPath>
        </Variable>
    </XMLPayload>
</ExtractVariables>
```
---

- Use this BasicAuthentication policy to extract the token.
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<BasicAuthentication async="false" continueOnError="false" enabled="true" name="DecodeBasicAuthentication">
    <DisplayName>DecodeBasicAuthentication</DisplayName>
    <Operation>Decode</Operation>
    <IgnoreUnresolvedVariables>false</IgnoreUnresolvedVariables>
    <User ref="request.header.username"/>
    <Password ref="request.header.password"/>
    <Source>request.header.Authorization</Source>
</BasicAuthentication>
```

---

- Then verify the Username with the Product .
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<VerifyAPIKey async="false" continueOnError="false" enabled="true" name="VerifyAPIKey">
    <DisplayName>VerifyAPIKey</DisplayName>
    <Properties/>
    <APIKey ref="request.header.username"/>
</VerifyAPIKey>
```

---

- Throw Error id the Password doesnt match.
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<RaiseFault async="false" continueOnError="false" enabled="true" name="RF_LoginError">
    <DisplayName>RF_LoginError</DisplayName>
    <Properties/>
    <FaultResponse>
        <Set>
            <Headers/>
            <Payload contentType="text/xml">
                <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
                    <soap:Header/>
                    <soap:Body>
                        <soap:Fault>
                            <faultcode>soap:Server.75612</faultcode>
                            <faultstring>Authentication error</faultstring>
                        </soap:Fault>
                    </soap:Body>
                </soap:Envelope>
            </Payload>
            <StatusCode>401</StatusCode>
            <ReasonPhrase>Unauthorized</ReasonPhrase>
        </Set>
    </FaultResponse>
    <IgnoreUnresolvedVariables>true</IgnoreUnresolvedVariables>
</RaiseFault>
```
