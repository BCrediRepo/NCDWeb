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


def vNombreModif = findTestData('05-Labels/Labels').getValue(2,13)


//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(GlobalVariable.Cliente3DNI, GlobalVariable.Cliente3Clave, GlobalVariable.Cliente3Usuario)

//Ingresa al módulo de Cuentas desde menú lateral y mapea los campos
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbCuentas'))

//Cliquea sobre el nombre de la cuenta
WebUI.click(findTestObject('Object Repository/03-Cuentas/lnkCtaNombreEtiqueta'))
WebUI.click(findTestObject('Object Repository/03-Cuentas/txtCtaNombreEtiqueta'))
WebUI.clearText(findTestObject(('Object Repository/03-Cuentas/txtCtaNombreEditado')))
WebUI.setText(findTestObject('Object Repository/03-Cuentas/txtCtaNombreEtiqueta'), vNombreModif)
	
	
	
	
