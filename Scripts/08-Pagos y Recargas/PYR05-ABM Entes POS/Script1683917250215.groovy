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
def vQuery1 = "SELECT * FROM Toast WHERE idToast = 'Toa-EditarServicio'"
def vQuery2 = "SELECT * FROM Toast WHERE idToast = 'Toa-EditarServicio'"

String vDNI = null
String vClave = null
String vUsuario = null
String vMonto = 1
String vCodigo = "54444444888"
String vEnte = "Metrogas Antiguo"
String vMsjeAdherido = "¡SERVICIO ADHERIDO!"
String vTexto = 'ÑANDÚ'
String vMsjeEditado = null
String vMsjeEliminado = null

CustomKeywords.'pkgDatabase.kwySQL.connectDB'()

//Consulta a la base de datos
ResultSet vResult = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery)
ResultSet vResult1 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery1)

vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)
vMsjeEditado = vResult1.getString(2)

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

//Adherir Servicio
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/btnAdherirNuevoServicio'))
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/mnuCategoriaAdherirServicio'))
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/txtLuzAguaGas'))

//Ingresa Ente
WebUI.setText(findTestObject('Object Repository/08-Pagos y Recargas/txtIngresoEnte'), vEnte)
WebUI.delay(5)
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/lnkMetrogas'))

//Ingresa el código
WebUI.setText(findTestObject('Object Repository/08-Pagos y Recargas/txtCodigoPago'), vCodigo)
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/btnAdherirServicioSolapa'))
WebUI.verifyElementText(findTestObject('Object Repository/08-Pagos y Recargas/txtPYRMsjeServicioAdherido'), vMsjeAdherido)
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/icoPYRBotonCerrarSolapa'))

//Ingresa al menú desplegable y selecciona la opción "Ver servicios Adheridos"
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/mnuPagosRecargas'))
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/lnkVerServiciosAdheridos'))

//Edita el servicio adherido
WebUI.focus(findTestObject('Object Repository/08-Pagos y Recargas/txtPYRServicioMetrogasCompleto'))
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/icoPYREditarServicio'))
WebUI.delay(5)

WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/icoPYRLapiz'))
WebUI.clearText(findTestObject('Object Repository/08-Pagos y Recargas/txtPYRNombreEditado'))
WebUI.sendKeys(findTestObject(('Object Repository/08-Pagos y Recargas/txtPYRNombreEditado')), vTexto)
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/icoPYRAceptarNombreEditado'))

WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/btnPYREditarGuardar'))
WebUI.verifyElementText(findTestObject('Object Repository/08-Pagos y Recargas/txtPYRMsjeNombreEditadoExito'), vMsjeEditado)
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/icoPYRCerrarSolapaAdheridos'))

//Elimina el servicio adherido para reutilizar el código
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/mnuPagosRecargas'))
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/lnkVerServiciosAdheridos'))
WebUI.focus(findTestObject('Object Repository/08-Pagos y Recargas/txtPYRServicioMetrogasCompleto'))
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/icoPYRBorrarServicio'))
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/btnConfirmarEliminarServicio'))
WebUI.verifyElementText(findTestObject('Object Repository/08-Pagos y Recargas/txtPYRMsjeEnteEditadoExito'), vMsjeEliminado)
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/icoPYRCerrarSolapaAdheridos'))

//NOTA: Revisar mensajes de confirmacion

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/PYR05-ABMEntesPOS.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}