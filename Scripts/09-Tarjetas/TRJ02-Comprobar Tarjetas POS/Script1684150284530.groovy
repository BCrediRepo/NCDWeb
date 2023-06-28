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
def vQuery6 = "SELECT * FROM Labels WHERE Id = 88"
def vQuery7 = "SELECT * FROM Labels WHERE Id = 89"
def vQuery8 = "SELECT * FROM Labels WHERE Id = 90"
def vQuery9 = "SELECT * FROM Labels WHERE Id = 91"
def vQuery10 = "SELECT * FROM Labels WHERE Id = 92"
def vQuery11 = "SELECT * FROM Labels WHERE Id = 93"


String vDNI = null
String vClave = null
String vUsuario = null
String vTarjDash1 = null
String vTarjDash2 = null
String vNroTarjCredito = null
String vTipoTarjCredito = null
String vFchaTarjCredito = null
String vTarjetaDbto = null 
String vNumeroDbto = null
String vLimiteDbto = null
String vCompraDbto = null
String vHabilitadaDbto = null
String vDisponbleDbto = null

CustomKeywords.'pkgDatabase.kwySQL.connectDB'()

//Consulta a la base de datos
ResultSet vResult = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery)
//ResultSet vResult1 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery1)
//ResultSet vResult2 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery2)
ResultSet vResult3 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery3)
ResultSet vResult4 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery4)
ResultSet vResult5 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery5)
ResultSet vResult6 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery6)
ResultSet vResult7 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery7)
ResultSet vResult8 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery8)
ResultSet vResult9 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery9)
ResultSet vResult10 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery10)
ResultSet vResult11 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery11)

vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)
//vTarjDash1 = vResult1.getString(2)
//vTarjDash2 = vResult2.getString(2)
vNroTarjCredito = vResult3.getString(2)
vTipoTarjCredito = vResult4.getString(2)
vFchaTarjCredito = vResult5.getString(2)
vTarjetaDbto = vResult6.getString(3)
vNumeroDbto = vResult7.getString(3)
vLimiteDbto = vResult8.getString(3)
vDisponbleDbto = vResult9.getString(3)
vCompraDbto = vResult10.getString(3)
vHabilitadaDbto = vResult11.getString(3)

//Cierre de la conexion
CustomKeywords.'pkgDatabase.kwySQL.closeDatabaseConnection'()
//---------------------------------------------------------------------------------------------------------------------

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI, vClave, vUsuario)

//Valida datos de tarjetas desde el Dashboard
WebUI.verifyElementVisible(findTestObject('Object Repository/09-Tarjetas/txtTrjCabalDatosDashboard'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/09-Tarjetas/txtTrjVisaDatosDashboard'), FailureHandling.CONTINUE_ON_FAILURE)

//Ingresa al módulo de Tarjetas
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbTarjetas'))

//Valida formato tabla de Tarjeta de Débito
WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/lblTrjDebitoTarjeta'), vTarjetaDbto, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/lblTrjDebitoNumero'), vNumeroDbto, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/lblTrjDebitoLimiteExtraccin'), vLimiteDbto, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/lblTrjDebitoDisponibleExtraccin'), vDisponbleDbto, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/lblTrjDebitoLimiteCompra'), vCompraDbto, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/lblTrjDebitoHabilitadaCompra'), vHabilitadaDbto, FailureHandling.CONTINUE_ON_FAILURE)

//Valida datos de Tarjeta de Débito
WebUI.verifyElementVisible(findTestObject('Object Repository/09-Tarjetas/txtTrjDebitoNumero'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/09-Tarjetas/txtTrjDebitoLimiteExtraccn'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/09-Tarjetas/txtTrjDebitoDisponibleExtraccn'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/09-Tarjetas/txtTrjDebitoLimiteCompra'), FailureHandling.CONTINUE_ON_FAILURE)

//Valida datos de Tarjetas de Crédito
WebUI.verifyElementVisible(findTestObject('Object Repository/09-Tarjetas/txtTrjNumeroTarjetaVisa'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/09-Tarjetas/txtTrjTipoTarjetaVisa'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/09-Tarjetas/txtTrjFechaVencimiento'), FailureHandling.CONTINUE_ON_FAILURE)

/*WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/txtTrjNumeroTarjetaVisa'), vNroTarjCredito)
WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/txtTrjTipoTarjetaVisa'), vTipoTarjCredito)
WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/txtTrjFechaVencimiento'), vFchaTarjCredito)*/

//Cliquea en la Tarjeta de Crédito
WebUI.delay(10)
WebUI.click(findTestObject('Object Repository/09-Tarjetas/icoTrjVisaPlatinum'))

//Valida que se muestren los datos de la Tarjeta de crédito
WebUI.verifyElementVisible(findTestObject('Object Repository/09-Tarjetas/txtTrjVisaNmeroTarjeta'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/09-Tarjetas/txtTrjVisaNmeroCuenta'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/09-Tarjetas/txtTrjVisaMontoDisponible'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/09-Tarjetas/txtTrjVisaLimite'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/09-Tarjetas/txtTrjVisaDisponibleCuotas'), FailureHandling.CONTINUE_ON_FAILURE)

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

