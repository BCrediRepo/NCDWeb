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

String vCuenta = findTestData('05-Labels/Labels').getValue(2, 2)

String vFavorita = findTestData('05-Labels/Labels').getValue(2, 3)

String vMoneda = findTestData('05-Labels/Labels').getValue(2, 4)

String vSaldo = findTestData('05-Labels/Labels').getValue(2, 5)

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(GlobalVariable.AdminDNI, GlobalVariable.AdminClave, GlobalVariable.AdminUsuario)

//Ingresa al módulo de Cuentas desde menú lateral y mapea los campos
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbCuentas'))

WebUI.verifyElementText(findTestObject('Object Repository/03-Cuentas/lblCtasTituloCuentaTabla'), vCuenta)

WebUI.verifyElementText(findTestObject('Object Repository/03-Cuentas/lblCtasTituloFavoritaTabla'), vFavorita)

WebUI.verifyElementText(findTestObject('Object Repository/03-Cuentas/lblCtasTituloMonedaTabla'), vMoneda)

CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

WebUI.verifyElementText(findTestObject('Object Repository/03-Cuentas/lblCtasTituloSaldoTabla'), vSaldo)

//Ingresa al módulo de Cuentas desde Inicio y mapea los campos
WebUI.click(findTestObject('Object Repository/03-Cuentas/icoCtasAtras'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbCuentasInicio'))

WebUI.verifyElementText(findTestObject('Object Repository/03-Cuentas/lblCtasTituloCuentaTabla'), vCuenta)

WebUI.verifyElementText(findTestObject('Object Repository/03-Cuentas/lblCtasTituloFavoritaTabla'), vFavorita)

WebUI.verifyElementText(findTestObject('Object Repository/03-Cuentas/lblCtasTituloMonedaTabla'), vMoneda)

WebUI.verifyElementText(findTestObject('Object Repository/03-Cuentas/lblCtasTituloSaldoTabla'), vSaldo) 

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

