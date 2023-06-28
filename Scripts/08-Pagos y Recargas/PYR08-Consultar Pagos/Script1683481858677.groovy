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
def vQuery1 = "SELECT * FROM Labels WHERE Id = 82"
def vQuery2 = "SELECT * FROM Labels WHERE Id = 83"
def vQuery3 = "SELECT * FROM Labels WHERE Id = 84"
def vQuery4 = "SELECT * FROM Labels WHERE Id = 85"
def vQuery5 = "SELECT * FROM Labels WHERE Id = 86"
def vQuery6 = "SELECT * FROM Labels WHERE Id = 87"

String vDNI = null
String vClave = null
String vUsuario = null
String vFecha = null
String vServicio = null
String vAnotacion = null
String vOperacion = null
String vCuenta = null
String vImporte = null

CustomKeywords.'pkgDatabase.kwySQL.connectDB'()

//Consulta a la base de datos
ResultSet vResult = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery)
ResultSet vResult1 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery1)
ResultSet vResult2 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery2)
ResultSet vResult3 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery3)
ResultSet vResult4 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery4)
ResultSet vResult5 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery5)
ResultSet vResult6 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery6)

vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)
vFecha = vResult1.getString(3)
vServicio = vResult2.getString(3)
vAnotacion = vResult3.getString(3)
vOperacion = vResult4.getString(3)
vCuenta = vResult5.getString(3)
vImporte = vResult6.getString(3)

//Cierre de la conexion
CustomKeywords.'pkgDatabase.kwySQL.closeDatabaseConnection'()
//---------------------------------------------------------------------------------------------------------------------

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI, vClave, vUsuario)

//Ingresa al módulo de Pagos y Recargas
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbPagos y Recargas'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbServicios y Tarjetas'))

//Ingresa a Historial de Pagos
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/mnuPYRPagosRecargas'))
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/lnkPYRVerHistorialPagos'))

//Selecciona fecha desde en el calendario
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/icoPYRCalendarioFechaDesdeHitorialPagos'))
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/txtPYRCalendarioMesHistorialPagos'))
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/txtPYRCalendarioMesDesdeHistorialPagos'))
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/txtPYRCalendarioAnioDesdeHistorialPagos'))
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/icoPYRCalendarioAceptarFechaDesde'))
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/icoPYRCalendarioDiaDesdeHistorialPagos'))
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/btnPYRBuscarHistorialPagos'))

//Cliquea en el menu desplegable
//WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/mnuPYRServicioAdheridoHistorial'))
//WebUI.scrollToElement(findTestObject('Object Repository/08-Pagos y Recargas/txtPYRVisaArgentina'), 10)
//WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/txtPYRServicioAdheridoMenu'))
//WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/btnPYRBuscarHistorialPago'))

//Valida titulos de la tabla
WebUI.delay(10)
WebUI.verifyElementText(findTestObject('Object Repository/08-Pagos y Recargas/lblPYRListaHistorialFecha'), vFecha, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/08-Pagos y Recargas/lblPYRListaHistorialServicio'), vServicio, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/08-Pagos y Recargas/lblPYRListaHistorialAnotacinPersonal'), vAnotacion, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/08-Pagos y Recargas/lblPYRListaHistorialNroOperacin'), vOperacion, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/08-Pagos y Recargas/lblPYRListaHistorialCuentaOrigen'), vCuenta, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/08-Pagos y Recargas/lblPYRListaHistorialImporte'), vImporte, FailureHandling.CONTINUE_ON_FAILURE)

//Valida Resultado de la Busqueda
WebUI.verifyElementVisible(findTestObject('Object Repository/08-Pagos y Recargas/txtPYRListaHistorialFecha'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/08-Pagos y Recargas/txtPYRListaHistorialServicioAdherido'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/08-Pagos y Recargas/txtPYRListaHistorialAnotacionPersonal'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/08-Pagos y Recargas/txtPYRListaHistorialNroOperacion'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/08-Pagos y Recargas/txtPYRListaHistorialCuentaOrigen'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/08-Pagos y Recargas/txtPYRListaHistorialImporte'), FailureHandling.CONTINUE_ON_FAILURE)

//Valida que coincidan ambos nombres del servicio seleccionado
//WebUI.callTestCase(findTestCase("UTL01-VerifyPDFKeywords"), null)
//println("Vuelve al caso de prueba principal")

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/PYR08-ConsultarPagos.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}

