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

String vDNI = null
String vClave = null
String vUsuario = null
String vMonto = 1
vEnte = 'ECOWIFI'
vCodigo = '99990556'
vServicioAdherido = '¡SERVICIO ADHERIDO! ECOWIFI'

CustomKeywords.'pkgDatabase.kwySQL.connectDB'()

//Consulta a la base de datos
ResultSet vResult = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery)

vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)
vEnte = 'ECOWIFI'
vCodigo = '99990567'
vServicioAdherido = '¡SERVICIO ADHERIDO! ECOWIFI'

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
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/txtInternetTelecomunicaciones'))

//Ingresa Ente
WebUI.setText(findTestObject('Object Repository/08-Pagos y Recargas/txtIngresoEnte'), vEnte)
WebUI.delay(5)
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/lnkECOWIFI'))

//Ingresa el código
WebUI.setText(findTestObject('Object Repository/08-Pagos y Recargas/txtCodigoPago'), vCodigo)
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/btnAdherirServicioSolapa'))

//Valida pantalla de adhesión y paga el servicio
//WebUI.verifyElementText(findTestObject('Object Repository/08-Pagos y Recargas/lblServicioAdheridoExito'), vServicioAdherido)
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/btnPagarAhoraSolapa'))
//Valida Pantalla Pago
//Confirma Pago
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/btnConfirmarPagoServicio'))
WebUI.setText(findTestObject('Object Repository/08-Pagos y Recargas/txtFirmaByPass'), vClave)
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/btnConfirmarByPassPago'))




