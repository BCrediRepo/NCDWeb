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
CustomKeywords.'pkgUtilities.kwyUtility.Login'(GlobalVariable.AdminDNI, GlobalVariable.AdminClave, GlobalVariable.AdminUsuario)

//Ingresa en la sección Cuentas del Dashboard
WebUI.verifyElementVisible(findTestObject('Object Repository/02-Dashboard/lnkDsbCuentas'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbCuentas'))

//Valida Home Cuentas
WebUI.verifyElementVisible(findTestObject('Object Repository/03-Cuentas/lblCtasTituloTusCuentasHome'))

//Valida formato Tabla
WebUI.verifyElementVisible(findTestObject('Object Repository/03-Cuentas/lblCtasTituloCuentaTabla'))
WebUI.verifyElementVisible(findTestObject('Object Repository/03-Cuentas/lblCtasTituloFavoritaTabla'))
WebUI.verifyElementVisible(findTestObject('Object Repository/03-Cuentas/lblCtasTituloMonedaTabla'))
WebUI.verifyElementVisible(findTestObject('Object Repository/03-Cuentas/lblCtasTituloSaldoTabla'))

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/CTAS01-AccederHomeCuentas.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}

