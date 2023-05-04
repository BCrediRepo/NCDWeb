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


def vMontoTexto = WebUI.getAttribute(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrfMensajeMonedaErronea'),'text')
def vMontoLimite = findTestData('05-Labels/Labels').getValue(2,11)
def vValorMonto1 = 120009
def vValorMonto2 = 1

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI, vClave, vUsuario)

//Cliquea en el menú desplegable desde Inicio y selecciona Nueva Transferencia desde cta Dólares
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/mnuTrfDesplegableCtasInicioCtaDolares'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrfNuevaTransferenciaInicioCtaDolares'))

//Selecciono solapa Mis cuentas Credicoop
WebUI.click(findTestObject('Object Repository/04-Transferencias/lblTrfMisCuentasCredicoop'))

//Selecciona Cuenta en pesos
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrfMisCtasPesos'))

//Valida Toast Moneda Errónea
WebUI.verifyElementText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrfMensajeMonedaErronea'),vMontoTexto)

//Ingresa en la sección Inicio del Dashboard
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbInicioEstado2'))

//Cliquea en el menú desplegable desde Inicio y selecciona Nueva Transferencia desde cta Pesos
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/mnuTrfDespelgableInicioCtaPesos'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrfNuevaTransferenciaInicioCtaPesos'))

//Selecciono solapa Mis cuentas Credicoop
WebUI.click(findTestObject('Object Repository/04-Transferencias/lblTrfMisCuentasCredicoop'))

//Selecciona Cuenta en pesos
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrfMisCtasPesos'))

//Ingresa monto
WebUI.click(findTestObject('Object Repository/04-Transferencias/txtTrfMontoFormulario'))
WebUI.clearText(findTestObject('Object Repository/04-Transferencias/txtTrfMontoFormulario'))
WebUI.sendKeys(findTestObject('Object Repository/04-Transferencias/txtTrfMontoFormulario'), vValorMonto1)

//Valida Mensaje Monto Supera Limite
WebUI.verifyElementText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrfMontoSuperaLimite'), vMontoLimite)

//Ingresa un monto menor
WebUI.click(findTestObject('Object Repository/04-Transferencias/txtTrfMontoFormulario'))
WebUI.sendKeys(findTestObject('Object Repository/04-Transferencias/txtTrfMontoFormulario'), vValorMonto2)

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/TRF10-ValidacionesErroneasParaTransferirNEG.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}

