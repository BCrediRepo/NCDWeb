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
def vQuery = "SELECT * FROM UsuariosRMobile WHERE NroDNI = 5119298"
def vQuery1 = "SELECT * FROM Labels WHERE Id = 35"
def vQuery2 = "SELECT * FROM Labels WHERE Id = 36"
def vQuery3 = "SELECT * FROM Labels WHERE Id = 37"

String vDNI = null
String vClave = null
String vUsuario = null
String vTxtVencimientos = null
String vTxtSinServicios = null
String vTxtSinPagos = null
String vTxtSinAdheridos = null

CustomKeywords.'pkgDatabase.kwySQL.connectDB'()

//Consulta a la base de datos
ResultSet vResult = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery)
ResultSet vResult1 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery1)
ResultSet vResult2 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery2)
ResultSet vResult3 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery3)

vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)
vTxtVencimientos = vResult1.getString(3)
vTxtSinServicios = vResult2.getString(3)
vTxtSinPagos = vResult3.getString(3)
vTxtSinAdheridos = vResult2.getString(3)

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

//Valida título y Mensaje 
//WebUI.verifyElementText(findTestObject('Object Repository/08-Pagos y Recargas/lblPagosRecargas'), vTxtPagosRecargas)
WebUI.verifyElementText(findTestObject('Object Repository/08-Pagos y Recargas/lblSinPrximosVencimientos'), vTxtVencimientos)

//Ingresa a Historial de Pagos
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/mnuPagosRecargas'))
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/lnkVerHistorialPagos'))

//Valida Titulo y Mensaje
//WebUI.verifyElementText(findTestObject('Object Repository/08-Pagos y Recargas/lblHistorialPagos'), vTxtHistorialPagos)
WebUI.verifyElementText(findTestObject('Object Repository/08-Pagos y Recargas/lblSinServiciosPorVencer'), vTxtSinServicios)
WebUI.verifyElementText(findTestObject('Object Repository/08-Pagos y Recargas/lblSinPagosRealizados'), vTxtSinPagos)
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/icoAtrasHistorial'))

//Ingresa a Servicios Adheridos
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/mnuPagosRecargas'))
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/lnkVerServiciosAdheridos'))

//Valida Mensaje
WebUI.verifyElementText(findTestObject('Object Repository/08-Pagos y Recargas/lblSinServiciosAdheridos'), vTxtSinAdheridos)
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/icoPYRCerrarSolapaAdheridos'))

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/PYR01-SinServiciosaPagarNiPagadosNEG.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}