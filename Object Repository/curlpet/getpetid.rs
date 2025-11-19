<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>get pet id from sweger pet shop</description>
   <name>getpetid</name>
   <tag></tag>
   <elementGuidId>b328b193-7acf-4c38-a0d4-c47930ee681d</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <smartLocatorEnabled>false</smartLocatorEnabled>
   <useRalativeImagePath>false</useRalativeImagePath>
   <autoUpdateContent>true</autoUpdateContent>
   <connectionTimeout>0</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent></httpBodyContent>
   <httpBodyType></httpBodyType>
   <katalonVersion>10.4.2</katalonVersion>
   <maxResponseSize>0</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <path></path>
   <restRequestMethod>GET</restRequestMethod>
   <restUrl>https://petstore.swagger.io/v2/pet/${petId}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>0</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>1001</defaultValue>
      <description></description>
      <id>4e500722-cced-4742-b17d-4569442969e2</id>
      <masked>false</masked>
      <name>petId</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable
String id = GlobalVariable.petId
def response = WS.sendRequest(findTestObject('Petstore/GET_PetById', [('petId'): 1]))

WS.verifyResponseStatusCode(response, 200)
WS.verifyElementPropertyValue(response, 'id', 1)
WS.verifyElementPropertyValue(response, 'status', 'available')</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
