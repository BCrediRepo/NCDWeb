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


def vTipoTrf = findTestData('03-Transferencias/TipoTrf').getValue(2,3)
def vClaveBypass = findTestData('02-Usuarios/SetUsers-FF').getValue(5,2)
def vValorMonto = '1'


//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(GlobalVariable.Cliente1DNI, GlobalVariable.Cliente1Clave, GlobalVariable.Cliente1Usuario)

//Ingresa en la sección Transferencias del Dashboard
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbTransferencias'))

//Valida y cliquea en Agenda de Beneficiarios
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/lnkTrfAgendaBeneficiario'))

//Busca por tipo de Beneficiario
WebUI.setText(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/txtTrfBuscarBeneficiarioTipo'), vTipoTrf)

//Inicia la transferencia
WebUI.click(findTestObject('Object Repository/04-Transferencias/lnkTrfCuentaBenefTercerosPesos'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/lnkTrfIniciarTransferenciaBeneficiario'))

//Ingresa Monto
WebUI.click(findTestObject('Object Repository/04-Transferencias/txtTrfMontoFormulario'))
WebUI.sendKeys(findTestObject('Object Repository/04-Transferencias/txtTrfMontoFormulario'), vValorMonto)

//Selecciona Titularidad
WebUI.click(findTestObject('Object Repository/04-Transferencias/lblTrfSeleccionTitularidadFormulario'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/lblTitularidadTextoFormulario'))

//Cliquea en Continuar
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/btnContinuarFormulario'))

//Cliquea en boton Confirmar
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/btnTrfConfirmar'))

//Ingresa Clave Bypass
WebUI.setText(findTestObject('Object Repository/04-Transferencias/txtTrfClaveBypass'), vClaveBypass)

//Confirma Operación
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/btnTrfConfirmarBypass'))

//Valida Destinatario
WebUI.verifyElementVisible(findTestObject('Object Repository/04-Transferencias/txtTrfBeneficiarioDestino'))

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/TRF03-TransferenciaTercerosCredicoopPesosPOS.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}