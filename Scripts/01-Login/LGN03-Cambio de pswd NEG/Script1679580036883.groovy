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

//Se define la URL
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Datos de login
def vDNI = findTestData('02-Usuarios/Users-FF').getValue(3,1)
def vClave = findTestData('02-Usuarios/Users-FF').getValue(5,1)
def vUsuario = findTestData('02-Usuarios/Users-FF').getValue(4,1)

//Inicia el browser

WebUI.openBrowser(GlobalVariable.ServerUsado)
WebUI.maximizeWindow()

WebUI.setText(findTestObject('Object Repository/01-Login/txtLgnDNI'), vDNI)
WebUI.setText(findTestObject('Object Repository/01-Login/txtLgnClave'), vClave)
WebUI.setText(findTestObject('Object Repository/01-Login/txtLgnUsuario'), vUsuario)

WebUI.click(findTestObject('Object Repository/01-Login/btnLgnIngresar'))

