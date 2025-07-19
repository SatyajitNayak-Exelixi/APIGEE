// Set the Datadog API URL here.
// Note: If you are in the Datadog EU site (app.datadoghq.eu), the HTTP log endpoint is http-intake.logs.datadoghq.eu.
var dd_api_url = "https://http-intake.logs.us5.datadoghq.com/api/v2/logs?dd-api-key=<Provide the Key>&ddsource=apigee";
 
// Debug
// print(dd_api_url);
// print('Name of the flow: ' + context.flow);
// calculate response times for client, target and total
var request_start_time = context.getVariable('client.received.start.timestamp');
var request_end_time = context.getVariable('client.received.end.timestamp');
var system_timestamp = context.getVariable('system.timestamp');
var target_start_time = context.getVariable('target.sent.start.timestamp');
var target_end_time = context.getVariable('target.received.end.timestamp');
var total_request_time = system_timestamp - request_start_time;
var total_target_time = target_end_time - target_start_time;
var total_client_time = total_request_time - total_target_time;
var apigeeType = 'edge';
var timestamp = crypto.dateFormat('YYYY-MM-dd HH:mm:ss.SSS');
var organization = context.getVariable("organization.name");
var networkClientIP = context.getVariable("client.ip");
var httpPort = context.getVariable("client.port");
var environment = context.getVariable("environment.name");
var apiProduct = context.getVariable("apiproduct.name");
var apigeeProxyName = context.getVariable("apiproxy.name");
var apigeeProxyRevision = context.getVariable("apiproxy.revision");
var appName = context.getVariable("developer.app.name");
var httpMethod = context.getVariable("request.verb");
var httpUrl = '' + context.getVariable("client.scheme") + '://' + context.getVariable("request.header.host") + context.getVariable("request.uri");
var httpStatusCode = context.getVariable("message.status.code");
var statusResponse = context.getVariable("message.reason.phrase");
var clientLatency = total_client_time;
var targetLatency = total_target_time;
var totalLatency = total_request_time;
var userAgent = context.getVariable('request.header.User-Agent');
var requestContent = context.getVariable('request.content');
var messageContent = context.getVariable('message.content');
var responseContent = context.getVariable('response.content');
var responsecode = context.getVariable('response.status.code');
var value = context.getVariable('request.header.x-datadog-trace-id');
context.setVariable('request.header.dd.trace_id', value);
//Copy
var requestHeaders = context.getVariable("request.headers.names"),
    result = {};
 
// requestHeaders is a java.util.TreeMap$KeySet; convert it to string
requestHeaders = requestHeaders + '';
 
// convert from "[A, B, C]" to an array of strings: ["A", "B", "C"]
requestHeaders = requestHeaders.slice(1, -1).split(', ');
for(var i=0; i < requestHeaders.length; i++) {
    // Replace 'x-datadog-trace-id' with 'dd.trace_id'
    if (requestHeaders[i].trim() === 'x-datadog-trace-id') {
        requestHeaders[i] = 'dd.trace_id';
    }
}
print("Updated Request Headers: " + JSON.stringify(requestHeaders));
// insert each header into the response
requestHeaders.forEach(function(x){
    var a = context.getVariable("request.header." + x );
    // check if the header name is not allheaders
    if (x != 'allheaders' && x != 'password' && x != 'Authorization' && x != 'im-apikey' && x != 'IM-ApiKey') {
        result[x.toLowerCase()] = a;
    }
});
// set the response content
context.setVariable('request.content', JSON.stringify(result, null, 2));
 
// Apply masking to <credential> tags
requestContent = requestContent.replace(/<credential[^>]*>.*?<\/credential>/g, '<credential>***</credential>');

// Datadog log attributes
var logObject = {
    "timestamp": timestamp,
    "organization": organization,
    "network.client.ip": networkClientIP,
    "env": environment,
    "apiProduct": apiProduct,
    "apigee_proxy.name": apigeeProxyName,
    "apigee_proxy.revision": apigeeProxyRevision,
    "apigee_proxy.type": apigeeType,
    "service": appName,
    "http.method": httpMethod,
    "http.url": httpUrl,
    "http.status_code": httpStatusCode,
    "http.port": httpPort,
    "status": statusResponse,
    "clientLatency": clientLatency,
    "targetLatency": targetLatency,
    "totalLatency": totalLatency,
    "http.client.start_time_ms": request_start_time,
    "http.client.end_time_ms": system_timestamp,
    "http.useragent": userAgent,
    "request": requestContent,
    "message": messageContent,
    "http.requestheader": context.getVariable('request.content'),
    "targetresponse":responseContent,
    "errorcode":responsecode,
};
var headers = {
    'Content-Type': 'application/json'
};
 
// Debug
// print('LOGGING OBJECT' + JSON.stringify(logObject));
var myLoggingRequest = new Request(dd_api_url, "POST", headers, JSON.stringify(logObject));
 
// Send logs to Datadog
httpClient.send(myLoggingRequest);