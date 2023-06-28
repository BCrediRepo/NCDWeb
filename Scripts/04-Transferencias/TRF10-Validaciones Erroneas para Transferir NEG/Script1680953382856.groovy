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

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

import javax.swing.JOptionPane

//-------------------Conecta a base de datos--------------------------------------------
def vQuery = "SELECT * FROM UsuariosRMobile WHERE NroDNI = 20144835"
def vQuery1 = "SELECT * FROM Toast WHERE idToast = 'Toa-015'"
def vQuery2 = "SELECT * FROM Labels WHERE Id = 24"

String vDNI = null
String vClave = null
String vUsuario = null
String vMonedaTexto = null
String vMontoLimite = null
String vValorMonto = 1200009

CustomKeywords.'pkgDatabase.kwySQL.connectDB'()

//Consulta a la base de datos
ResultSet vResult = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery)
ResultSet vResult1 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery1)
ResultSet vResult2 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery2)

vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)
vClaveBypass = vResult.getString(4)
vMonedaTexto = vResult1.getString(2)
vMontoLimite = vResult2.getString(3)

//Cierre de la conexion
CustomKeywords.'pkgDatabase.kwySQL.closeDatabaseConnection'()
//---------------------------------------------------------------------------------------------------------------------

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI, vClave, vUsuario)

//Cliquea en el menú desplegable desde Inicio y selecciona Nueva Transferencia desde cta Dólares
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/mnuTrxDesplegableCtasInicioCtaDolares'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxNuevaTransferenciaInicioCtaDolares'))

//Selecciono solapa Mis cuentas Credicoop
WebUI.click(findTestObject('Object Repository/04-Transferencias/lblTrxMisCuentasCredicoop'))

//Selecciona Cuenta en pesos
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxMisCtasPesos'))

//Valida Toast Moneda Errónea
WebUI.verifyElementText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrxMensajeMonedaErronea'),vMonedaTexto, FailureHandling.CONTINUE_ON_FAILURE)

//Ingresa en la sección Inicio del Dashboard
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbInicioEstado2'))

//Cliquea en el menú desplegable desde Inicio y selecciona Nueva Transferencia desde cta Pesos
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/mnuTrxDespelgableInicioCtaPesos'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxNuevaTransferenciaInicioCtaPesos'))

//Selecciono solapa Mis cuentas Credicoop
WebUI.click(findTestObject('Object Repository/04-Transferencias/lblTrxMisCuentasCredicoop'))

//Selecciona Cuenta en pesos
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxMisCtasPesos'))

//Ingresa monto
WebUI.click(findTestObject('Object Repository/04-Transferencias/txtTrxMontoPesos'))
WebUI.sendKeys(findTestObject('Object Repository/04-Transferencias/txtTrxMontoPesos'), vValorMonto, FailureHandling.CONTINUE_ON_FAILURE)

//Selecciona Titularidad
WebUI.click(findTestObject('Object Repository/04-Transferencias/lblTrxSeleccionTitularidadFormulario'))

//Valida Mensaje Monto Supera Limite
//vMontoAtributo = WebUI.getAttribute(findTestObject('Object Repository/04-Transferencias/txtTrxMontoSuperaLimite'), text)
//WebUI.verifyMatch(vMontoAtributo,vMontoLimite, true)
WebUI.verifyElementText(findTestObject('Object Repository/04-Transferencias/txtTrxMontoSuperaSaldo'), vMontoLimite, FailureHandling.CONTINUE_ON_FAILURE)

//NOTA: Cambiar validacion monto Limite

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
