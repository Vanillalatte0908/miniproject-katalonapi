import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonOutput

// Debug print to verify DDT values
println "DDT Input -> ID: ${id}, Name: ${name}, Status: ${status}"

// =========================
// POST REQUEST
// =========================
def postResponse = WS.sendRequest(findTestObject(
    'usingdata/postpetdata',
    [
        ('id'): id,
        ('name'): name,
        ('status'): status
    ]
))

WS.verifyResponseStatusCode(postResponse, 200)

// Pretty print POST JSON
String postPretty = JsonOutput.prettyPrint(postResponse.getResponseBodyContent())

// Extract ID returned by API
def createdId = WS.getElementPropertyValue(postResponse, 'id')
println "Created Pet ID from POST: ${createdId}"

// Validate POST
WS.verifyElementPropertyValue(postResponse, 'name', name)
WS.verifyElementPropertyValue(postResponse, 'status', status)


// =========================
// GET REQUEST
// =========================
def getResponse = WS.sendRequest(findTestObject(
    'usingdata/getpetdata',
    [
        ('id'): createdId
    ]
))

WS.verifyResponseStatusCode(getResponse, 200)

// Pretty GET JSON
String getPretty = JsonOutput.prettyPrint(getResponse.getResponseBodyContent())

// Validate GET
WS.verifyElementPropertyValue(getResponse, 'id', createdId)
WS.verifyElementPropertyValue(getResponse, 'name', name)
WS.verifyElementPropertyValue(getResponse, 'status', status)


// =========================
// VALIDATION LOGIC
// =========================
boolean idMatch     = (createdId.toString() == WS.getElementPropertyValue(getResponse, 'id').toString())
boolean nameMatch   = (name == WS.getElementPropertyValue(getResponse, 'name'))
boolean statusMatch = (status == WS.getElementPropertyValue(getResponse, 'status'))

String finalResult = (idMatch && nameMatch && statusMatch) ? "PASS" : "FAIL"


// =========================
// SAVE EVIDENCE FILE
// =========================
new File("Evidence").mkdirs()

String filePath = "Evidence/Evidence_${createdId}.txt"

String evidence = """
================================================
API Validation Evidence
================================================
Timestamp            : ${new Date()}

[ Input Data from DDT ]
ID                   : ${id}
Name                 : ${name}
Status               : ${status}

[ POST Request ]
Endpoint             : POST /pet
Status Code          : ${postResponse.getStatusCode()}
Response Body:
${postPretty}

[ GET Request ]
Endpoint             : GET /pet/${createdId}
Status Code          : ${getResponse.getStatusCode()}
Response Body:
${getPretty}

[ Validation ]
ID Match             : ${idMatch}
Name Match           : ${nameMatch}
Status Match         : ${statusMatch}

[ RESULT ]
${finalResult} – API Data Validation
================================================
"""

new File(filePath).text = evidence

println ">>> Evidence saved at: ${filePath}"
