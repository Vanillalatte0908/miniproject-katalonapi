import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil


int attempts = 0
int limit = 3
boolean success = false

while (attempts < limit) {
	def response = WS.sendRequest(findTestObject('usingdata/getpetdata', [
		('id') : createdId
	]))

	int code = response.getStatusCode()

	if (code == 200) {
		println("GET successful on attempt ${attempts + 1}")
		success = true
		break
	}

	attempts++
	WebUI.delay(1)
}

if (!success) {
	KeywordUtil.markFailed("GET failed after ${limit} attempts")
}

int maxRetries = 5
int retry = 0
String petStatus = ""

while (retry < maxRetries) {
	def getResponse = WS.sendRequest(findTestObject('usingdata/getpetdata', [
		('id') : createdId
	]))
	
	petStatus = WS.getElementPropertyValue(getResponse, 'status')
	
	println "Attempt: ${retry + 1}, Status = ${petStatus}"
	
	if (petStatus == "sold") {
		println "Pet is now SOLD!"
		break
	}
	
	retry++
	WebUI.delay(2)
}

if (petStatus != "sold") {
	KeywordUtil.markFailed("Pet is not sold after ${maxRetries} attempts")
}
