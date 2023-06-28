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
def vQuery = "SELECT * FROM UsuariosRMobile WHERE NroDNI = 13976407"
def vQuery2 = "SELECT * FROM Parametros WHERE Nombre = 'CBU'"
def vQuery3 = "SELECT * FROM Toast WHERE idToast = 'Toa-eliminarDebin'"
def vQuery4 = "SELECT * FROM UsuariosRMobile WHERE NroDNI = 30276301"

String vDNI = null
String vClave = null
String vUsuario = null
String vCBUBenf = null
String vMontoPesos = '10'
String vMjsEliminar = null
String vMontoPesos1 = '20'
String vMontoPesos2 = '30'
String vDNI1 = null
String vClave1 = null
String vUsuario1 = null
String vMjsAceptado = 'DEBIN ACEPTADO CON ÉXITO'
String vMjsRechazado = 'DEBIN RECHAZADO CON ÉXITO'

CustomKeywords.'pkgDatabase.kwySQL.connectDB'()

//Consulta a la base de datos
ResultSet vResult = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery)
ResultSet vResult2 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery2)
ResultSet vResult3 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery3)
ResultSet vResult4 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery4)

vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)
vCBUBenf = vResult2.getString(2)
vMjsEliminar = vResult3.getString(2)
vDNI1 = vResult4.getString(2)
vUsuario1 = vResult4.getString(3)
vClave1 = vResult4.getString(4)


//Cierre de la conexion
CustomKeywords.'pkgDatabase.kwySQL.closeDatabaseConnection'()
//---------------------------------------------------------------------------------------------------------------------

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI, vClave, vUsuario)

//Ingresa en la sección Otras Operaciones y Pagos Debin del Dashboard
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbOtrasOperaciones'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbPagosDebin'))

//Cliquea en Generar Debin
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnGenerarDebin'))

// Ingresa y busca CBU solicitante
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnCBUSolicitante'))
WebUI.setText(findTestObject('Object Repository/05-Pagos Debin/txtDbnCBUSolicitante'), vCBUBenf, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnBuscarSolicitante'))

//Completa formulario
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/intDbnMontoPesos'))
WebUI.sendKeys(findTestObject('Object Repository/05-Pagos Debin/intDbnMontoPesos'), vMontoPesos, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/05-Pagos Debin/mnuDbnDuracionSolicitud'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnDuracionHoras'))

WebUI.click(findTestObject('Object Repository/05-Pagos Debin/mnuDbnConcepto'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnConceptoTipo'))

WebUI.click(findTestObject('Object Repository/05-Pagos Debin/mnuDbnTitularidadCuenta'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnTitularidadTipo'))

WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnContinuar'))

//Valida Datos y confirma la operación
WebUI.verifyElementVisible(findTestObject('Object Repository/05-Pagos Debin/lblDbnConfirmarFormulario'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnConfirmarFormulario'))

//Valida Pantalla de Éxito
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnVolverDebin'))

//------------------Genera 2do Debin-------------------------------------------------------------------------

//Cliquea en Generar Debin
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnGenerarDebin'))

// Ingresa y busca CBU solicitante
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnCBUSolicitante'))
WebUI.setText(findTestObject('Object Repository/05-Pagos Debin/txtDbnCBUSolicitante'), vCBUBenf, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnBuscarSolicitante'))

//Completa formulario
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/intDbnMontoPesos'))
WebUI.sendKeys(findTestObject('Object Repository/05-Pagos Debin/intDbnMontoPesos'), vMontoPesos1, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/05-Pagos Debin/mnuDbnDuracionSolicitud'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnDuracionHoras'))

WebUI.click(findTestObject('Object Repository/05-Pagos Debin/mnuDbnConcepto'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnConceptoTipo'))

WebUI.click(findTestObject('Object Repository/05-Pagos Debin/mnuDbnTitularidadCuenta'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnNoTitular'))

WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnContinuar'))

//Valida Datos y confirma la operación
WebUI.verifyElementVisible(findTestObject('Object Repository/05-Pagos Debin/lblDbnConfirmarFormulario'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnConfirmarFormulario'))

//Valida Pantalla de Éxito
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnVolverDebin'))

//--------------------Genera 3er Debin--------------------------------------------------------------------

//Cliquea en Generar Debin
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnGenerarDebin'))

// Ingresa y busca CBU solicitante
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnCBUSolicitante'))
WebUI.setText(findTestObject('Object Repository/05-Pagos Debin/txtDbnCBUSolicitante'), vCBUBenf, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnBuscarSolicitante'))

//Completa formulario
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/intDbnMontoPesos'))
WebUI.sendKeys(findTestObject('Object Repository/05-Pagos Debin/intDbnMontoPesos'), vMontoPesos2, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/05-Pagos Debin/mnuDbnDuracionSolicitud'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnDuracionHoras'))

WebUI.click(findTestObject('Object Repository/05-Pagos Debin/mnuDbnConcepto'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnConceptoTipo'))

WebUI.click(findTestObject('Object Repository/05-Pagos Debin/mnuDbnTitularidadCuenta'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnNoTitular'))

WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnContinuar'))

//Valida Datos y confirma la operación
WebUI.verifyElementVisible(findTestObject('Object Repository/05-Pagos Debin/lblDbnConfirmarFormulario'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnConfirmarFormulario'))

//Valida Pantalla de Éxito
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnVolverDebin'))

//Ingresa en Solapa Pendientes
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/lnkDbnGeneradosPendientes'))

//Elimina Debin generado
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/icoDbnEliminarGenerado'))

//Confirma Eliminar
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnConfirmarEliminar'))

//Valida mensaje de confirmacion
WebUI.verifyElementText(findTestObject('Object Repository/05-Pagos Debin/lblDbnAdherirCtaExitoso'), vMjsEliminar, FailureHandling.CONTINUE_ON_FAILURE)

//----------------Loguea con el otro adherente------------------------------------------------------------------

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI1, vClave1, vUsuario1)

//Ingresa en la sección Otras Operaciones y Pagos Debin del Dashboard
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbOtrasOperaciones'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbPagosDebin'))

//Cliquea en Aceptar Debin
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnAceptarDebin'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnAceptar'))

//Ingresa ByPass
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnAceptarBypass'))
WebUI.sendKeys(findTestObject('Object Repository/05-Pagos Debin/txtDbnAceptarBypass'), vClave1, FailureHandling.CONTINUE_ON_FAILURE)

//Cliquea en Confirmar y valida pantalla de éxito
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnConfirmar'))
WebUI.verifyElementText(findTestObject('Object Repository/05-Pagos Debin/txtDbnMensajeAceptadoExito'), vMjsAceptado, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/icoDbnCerrarSolapaAceptar'))

//Cliquea en Rechazar Debin
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnRechazarDebin'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/mnuDbnMotivoRechazo'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnRechazo'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnRechazar'))

//Valida pantalla de éxito
WebUI.verifyElementText(findTestObject('Object Repository/05-Pagos Debin/txtDbnMensajeRechazoExito'), vMjsRechazado, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/icoDbnCerrarSolapaRechazo'))

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/DBN04-AceptarEliminarRechazarDebinPOS.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}
