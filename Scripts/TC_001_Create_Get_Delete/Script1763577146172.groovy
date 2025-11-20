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
import com.kms.katalon.core.util.KeywordUtil


// CREATE PET
def createResponse = WS.sendRequest(findTestObject('curlpet/postpetid'))
WS.verifyResponseStatusCode(createResponse, 200)

def createdId = WS.getElementPropertyValue(createResponse, 'id')
println "Created Pet ID: " + createdId

// GET PET
def getResponse = WS.sendRequest(findTestObject('curlpet/getpetid', [('petId'): createdId]))
WS.verifyResponseStatusCode(getResponse, 200)
WS.verifyElementPropertyValue(getResponse, 'id', createdId)
key

// DELETE PET
def deleteResponse = WS.sendRequest(findTestObject('curlpet/deletepetid', [('petId'): createdId]))
WS.verifyResponseStatusCode(deleteResponse, 200)
