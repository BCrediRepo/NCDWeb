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

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(GlobalVariable.Cliente1DNI, GlobalVariable.Cliente1Clave, GlobalVariable.Cliente1Usuario)

//Ingresa en la sección Cuentas del Dashboard
WebUI.verifyElementVisible(findTestObject('Object Repository/02-Dashboard/lnkDsbCuentas'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbCuentas'))

//Valido título Cuentas
WebUI.verifyElementVisible(findTestObject('Object Repository/03-Cuentas/lblCtasCuentasTitulo'))

//Selecciono una CC como favorita y valido Toast de confirmación
WebUI.click(findTestObject('Object Repository/03-Cuentas/icoCtaFavoritoCC'))
//vAlertText = WebUI.getAlertText('Object Repository/03-Cuentas/lblCtaCuentaFavoritaActualizada')
//WebUI.verifyMatch(vAlertText, 'Tu cuenta favorita fue actualizada.', false)


//Selecciono una CA como favorita y valido Toast de confirmación
WebUI.click(findTestObject('Object Repository/03-Cuentas/icoCtaFavoritoCA'))
//vAlertText = WebUI.getAlertText('Object Repository/03-Cuentas/lblCtaCuentaFavoritaActualizada')
//WebUI.verifyMatch(vAlertText, 'Tu cuenta favorita fue actualizada.', false)

//Selecciono una CAUSD como favorita y valido Toast de confirmación
WebUI.scrollToElement(findTestObject('Object Repository/03-Cuentas/icoCtaFavoritaCAUSD'), 10)
WebUI.click(findTestObject('Object Repository/03-Cuentas/icoCtaFavoritaCAUSD'))
//vAlertText = WebUI.getAlertText('Object Repository/03-Cuentas/lblCtaCuentaFavoritaActualizada')
//WebUI.verifyMatch(vAlertText, 'Tu cuenta favorita fue actualizada.', false)







