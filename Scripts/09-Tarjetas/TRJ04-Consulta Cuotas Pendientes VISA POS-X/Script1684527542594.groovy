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

/*
def vQuery1 = "SELECT * FROM Labels WHERE Id = 45"
def vQuery2 = "SELECT * FROM Labels WHERE Id = 46"
def vQuery3 = "SELECT * FROM Labels WHERE Id = 47"
def vQuery4 = "SELECT * FROM Labels WHERE Id = 48"
def vQuery5 = "SELECT * FROM Labels WHERE Id = 49"
def vQuery6 = "SELECT * FROM Labels WHERE Id = 50"
def vQuery7 = "SELECT * FROM Labels WHERE Id = 51"
*/

String vDNI = null
String vClave = null
String vUsuario = null

/*
String vFechaCP = null
String vDescripCP = null
String vNombreCP = null
String vCtasPendtesCP = null
String vValorCuotaCP = null
String vCtasTotalCP = null
String vSldoPendCP = null
*/

CustomKeywords.'pkgDatabase.kwySQL.connectDB'()

//Consulta a la base de datos
ResultSet vResult = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery)

/*
ResultSet vResult1 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery1)
ResultSet vResult2 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery2)
ResultSet vResult3 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery3)
ResultSet vResult4 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery4)
ResultSet vResult5 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery5)
ResultSet vResult6 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery6)
ResultSet vResult7 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery7)
*/

vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)

/*
vFechaCP = vResult1.getString(3)
vDescripCP = vResult2.getString(3)
vNombreCP = vResult3.getString(3)
vCtasPendtesCP = vResult4.getString(3)
vValorCuotaCP = vResult5.getString(3)
vCtasTotalCP = vResult6.getString(3)
vSldoPendCP = vResult7.getString(3)
*/

//Cierre de la conexion
CustomKeywords.'pkgDatabase.kwySQL.closeDatabaseConnection'()
//---------------------------------------------------------------------------------------------------------------------

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI, vClave, vUsuario)

//Ingresa al módulo de Tarjetas
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbTarjetas'))

//Cliquea en la Card Visa y Selecciona Solapa Cuotas Pendientes
WebUI.click(findTestObject('Object Repository/09-Tarjetas/LblTarjetaVisa'))
WebUI.click(findTestObject('Object Repository/09-Tarjetas/lblCuotasPendientes'))

/*
//Valida titulos de la solapa Cuotas Pendientes
WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/txtFechaMovimVisa'), vFechaCP)
WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/txtFechaMovimVisa'), vDescripCP)
WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/txtFechaMovimVisa'), vNombreCP)
WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/txtFechaMovimVisa'), vCtasPendtesCP)
WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/txtFechaMovimVisa'), vValorCuotaCP)
WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/txtFechaMovimVisa'), vCtasTotalCP)
WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/txtFechaMovimVisa'), vSldoPendCP)
*/

//Valida datos
WebUI.verifyElementPresent(findTestObject('Object Repository/09-Tarjetas/txtCPFecha'), 10)
WebUI.verifyElementPresent(findTestObject('Object Repository/09-Tarjetas/txtCPDescripcion'), 10)
WebUI.verifyElementPresent(findTestObject('Object Repository/09-Tarjetas/txtCPNombre'), 10)
WebUI.verifyElementPresent(findTestObject('Object Repository/09-Tarjetas/txtCPCuotas'), 10)
WebUI.verifyElementPresent(findTestObject('Object Repository/09-Tarjetas/txtCPValorCuota'), 10)
WebUI.verifyElementPresent(findTestObject('Object Repository/09-Tarjetas/txtCPSaldo'), 10)

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/TRJ04-ConsultaCuotasPendientesVisaPOS.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}

