# Apigee OAuth Token API Proxy

## 📌 Overview
This document provides a step-by-step guide to setting up an Apigee OAuth Token API Proxy. The API proxy supports two methods to generate an OAuth token:

- **GET Method**
- **POST Method**

---

## 🚀 Steps to Generate OAuth Token

### 🔹 **Proxy Endpoint Configuration**
```xml
<ProxyEndpoint name="default">
    <PreFlow name="PreFlow">
        <Request/>
        <Response/>
    </PreFlow>
    <PostFlow name="PostFlow">
        <Request/>
        <Response/>
    </PostFlow>
    <Flows>
        <!-- POST Method Flow -->
        <Flow name="TokenFlow">
            <Request>
                <Step>
                    <Name>JS-TransformClientid-Post</Name>
                </Step>
                <Step>
                    <Name>BA-AuthenticateCredential</Name>
                </Step>
                <Step>
                    <Name>OAuth-v20-GenerateToken</Name>
                </Step>
                <Step>
                    <Name>AM-BuildOauthResponse</Name>
                </Step>
            </Request>
            <Condition>(proxy.pathsuffix MatchesPath "/token") and (request.verb = "POST")</Condition>
        </Flow>

        <!-- GET Method Flow -->
        <Flow name="TokenFlow">
            <Request>
                <Step>
                    <Name>JS-TransformClientid</Name>
                </Step>
                <Step>
                    <Name>BA-AuthenticateCredential</Name>
                </Step>
                <Step>
                    <Name>OAuth-v20-GenerateToken-Get</Name>
                </Step>
                <Step>
                    <Name>AM-BuildOAuthResponse-Get</Name>
                </Step>
            </Request>
            <Condition>(proxy.pathsuffix MatchesPath "/token") and (request.verb = "GET")</Condition>
        </Flow>
    </Flows>
    <HTTPProxyConnection>
        <BasePath>/oauth/oauth20</BasePath>
        <VirtualHost>default</VirtualHost>
    </HTTPProxyConnection>
</ProxyEndpoint>
```


---

## 📝 **POST Method Implementation**
### 🔹 **JavaScript: Transform Client ID**
```javascript
var incoming_clientid = context.getVariable('request.content');
var indexOfClientId = incoming_clientid.indexOf('client_id=') + 10;
var indexOfClientSecret = incoming_clientid.indexOf('client_secret=') + 14;
var outgoing_clientid, outgoing_clientsecret;

if (indexOfClientId > 0) {
    var intermediate_clientid = incoming_clientid.substring(indexOfClientId).split('&')[0];
    outgoing_clientsecret = incoming_clientid.substring(indexOfClientSecret).split('&')[0];
    var indexOfColon = intermediate_clientid.indexOf('%3A');
    outgoing_clientid = (indexOfColon > 0) ?
        intermediate_clientid.substring(0, indexOfColon) + '_' + intermediate_clientid.substring(indexOfColon + 3) :
        intermediate_clientid;
}

context.setVariable('request.queryparam.client_id', outgoing_clientid);
context.setVariable('request.queryparam.client_secret', outgoing_clientsecret);
```

## 📝 **Basic Authentication Configuration**
```xml
<BasicAuthentication async="false" continueOnError="false" enabled="true" name="BA-AuthenticateCredential">
    <DisplayName>BA-AuthenticateCredential</DisplayName>
    <Operation>Encode</Operation>
    <IgnoreUnresolvedVariables>false</IgnoreUnresolvedVariables>
    <User ref="request.queryparam.client_id"/>
    <Password ref="request.queryparam.client_secret"/>
    <AssignTo createNew="true">request.header.Authorization</AssignTo>
</BasicAuthentication>
```


### 🔹 **OAuth Policy Configuration**
```xml
<OAuthV2 name="OAuth-v20-GenerateToken">
    <ExpiresIn>86400000</ExpiresIn>
    <Operation>GenerateAccessToken</Operation>
    <SupportedGrantTypes>
        <GrantType>client_credentials</GrantType>
    </SupportedGrantTypes>
</OAuthV2>
```

### 🔹 **Build Response**
```xml
<AssignMessage name="AM-BuildOauthResponse">
    <Set>
        <Payload contentType="application/json">{
            "access_token": "{oauthv2accesstoken.OAuth-v20-GenerateToken.access_token}",
            "token_type": "Bearer",
            "expires_in": "{oauthv2accesstoken.OAuth-v20-GenerateToken.expires_in}"
        }</Payload>
    </Set>
</AssignMessage>
```

---

## 📝 **GET Method Implementation**
### 🔹 **JavaScript: Transform Client ID**
```javascript
var incoming_clientid = context.getVariable('request.queryparam.client_id');
var index = incoming_clientid.indexOf(':');
var outgoing_clientid = (index > 0) ?
    incoming_clientid.substring(0, index) + '_' + incoming_clientid.substring(index + 1) :
    incoming_clientid;

context.setVariable('request.queryparam.client_id', outgoing_clientid);
```

## 📝 **Basic Authentication Configuration**
```xml
<BasicAuthentication async="false" continueOnError="false" enabled="true" name="BA-AuthenticateCredential">
    <DisplayName>BA-AuthenticateCredential</DisplayName>
    <Operation>Encode</Operation>
    <IgnoreUnresolvedVariables>false</IgnoreUnresolvedVariables>
    <User ref="request.queryparam.client_id"/>
    <Password ref="request.queryparam.client_secret"/>
    <AssignTo createNew="true">request.header.Authorization</AssignTo>
</BasicAuthentication>
```

### 🔹 **OAuth Policy Configuration**
```xml
<OAuthV2 name="OAuth-v20-GenerateToken-Get">
    <ExpiresIn>86400000</ExpiresIn>
    <Operation>GenerateAccessToken</Operation>
    <SupportedGrantTypes>
        <GrantType>client_credentials</GrantType>
    </SupportedGrantTypes>
</OAuthV2>
```

### 🔹 **Build Response**
```xml
<AssignMessage name="AM-BuildOAuthResponse-Get">
    <Set>
        <Payload contentType="application/json">{
            "access_token": "{oauthv2accesstoken.OAuth-v20-GenerateToken-Get.access_token}",
            "token_type": "Bearer",
            "expires_in": "{oauthv2accesstoken.OAuth-v20-GenerateToken-Get.expires_in}"
        }</Payload>
    </Set>
</AssignMessage>
```

---

## 🎯 **Summary**
- **POST Method:** Accepts credentials in the request body.
- **GET Method:** Accepts credentials via query parameters.
- Uses **OAuthV2 policies** to generate tokens.
- **JavaScript policies** transform the client ID format before processing.
- **BasicAuthentication policy** encodes client credentials before sending them to the token generation process.

---

## ✅ **Conclusion**
This Apigee OAuth Token API Proxy allows secure and flexible authentication using **GET** and **POST** methods. The structured implementation ensures robust token generation and easy integration with client applications. 🚀

