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
//def vQuery1 = "SELECT * FROM Parametros WHERE Nombre = TarjetaDash1"
//def vQuery2 = "SELECT * FROM Parametros WHERE Nombre = TarjetaDash2"
def vQuery3 = "SELECT * FROM Parametros WHERE Nombre = 'NroTarjetaCredito'"
def vQuery4 = "SELECT * FROM Parametros WHERE Nombre = 'TipoTarjetaCredito'"
def vQuery5 = "SELECT * FROM Parametros WHERE Nombre = 'FechaTarjetaCredito'"

String vDNI = null
String vClave = null
String vUsuario = null
String vTarjDash1 = null
String vTarjDash2 = null
String vNroTarjCredito = null
String vTipoTarjCredito = null
String vFchaTarjCredito = null

CustomKeywords.'pkgDatabase.kwySQL.connectDB'()

//Consulta a la base de datos
ResultSet vResult = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery)
//ResultSet vResult1 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery1)
//ResultSet vResult2 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery2)
ResultSet vResult3 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery3)
ResultSet vResult4 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery4)
ResultSet vResult5 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery5)

vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)
//vTarjDash1 = vResult1.getString(2)
//vTarjDash2 = vResult2.getString(2)
vNroTarjCredito = vResult3.getString(2)
vTipoTarjCredito = vResult4.getString(2)
vFchaTarjCredito = vResult5.getString(2)

//Cierre de la conexion
CustomKeywords.'pkgDatabase.kwySQL.closeDatabaseConnection'()
//---------------------------------------------------------------------------------------------------------------------

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI, vClave, vUsuario)

//Valida datos de tarjetas desde el Dashboard
//WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/txtTarjetaDash1'), vTarjDash1)
//WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/txtTarjetasDash2'), vTarjDash2)

//Ingresa al módulo de Tarjetas
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbTarjetas'))

//Valida datos de tarjetas de crédito
WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/txtNumeroTarjetaVisa'), vNroTarjCredito)
WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/txtTipoTarjetaVisa'), vTipoTarjCredito)
WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/txtFechaVencimiento'), vFchaTarjCredito)

//Cliquea en la tarjeta


//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/TRJ02-ComprobarTarjetasPOS.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}

