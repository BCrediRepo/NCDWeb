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
def vQuery1 = "SELECT * FROM Labels WHERE Id = 41"
def vQuery2 = "SELECT * FROM Labels WHERE Id = 42"
def vQuery3 = "SELECT * FROM Labels WHERE Id = 43"
def vQuery4 = "SELECT * FROM Labels WHERE Id = 44"


String vDNI = null
String vClave = null
String vUsuario = null
String vFechaCierre = null
String vFechaVenc = null
String vSldoPesos = null
String vSldoDolares = null


CustomKeywords.'pkgDatabase.kwySQL.connectDB'()

//Consulta a la base de datos
ResultSet vResult = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery)

ResultSet vResult1 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery1)
ResultSet vResult2 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery2)
ResultSet vResult3 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery3)
ResultSet vResult4 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery4)


vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)
vFechaCierre = vResult1.getString(3)
vFechaVenc = vResult2.getString(3)
vSldoPesos = vResult3.getString(3)
vSldoDolares = vResult4.getString(3)


//Cierre de la conexion
CustomKeywords.'pkgDatabase.kwySQL.closeDatabaseConnection'()
//---------------------------------------------------------------------------------------------------------------------

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI, vClave, vUsuario)

//Ingresa al módulo de Tarjetas
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbTarjetas'))

//Cliquea en la card e ingresa al módulo para ver los movimientos
WebUI.click(findTestObject('Object Repository/09-Tarjetas/LblTarjetaVisa'))

//Selecciona la solapa Resumen
WebUI.click(findTestObject('Object Repository/09-Tarjetas/lblResumenes'))

//Filtra fecha para ver los movimientos
WebUI.click(findTestObject('Object Repository/09-Tarjetas/icoCalendario'))
WebUI.click(findTestObject('Object Repository/09-Tarjetas/txtMes'))
WebUI.click(findTestObject('Object Repository/09-Tarjetas/txt2020'))
WebUI.click(findTestObject('Object Repository/09-Tarjetas/txtMesMar'))
WebUI.click(findTestObject('Object Repository/09-Tarjetas/icoAceptarFecha'))
WebUI.click(findTestObject('Object Repository/09-Tarjetas/txtDiaCalendario'))
WebUI.click(findTestObject('Object Repository/09-Tarjetas/btnBuscarResumen'))

//Valido tabla de resultados
WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/lblFechaCierre'), vFechaCierre)
WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/lblFechaVencimiento'), vFechaVenc)
WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/lblSaldoPesos'), vSldoPesos)
WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/lblSaldoDolares'), vSldoDolares)

//Valido los datos de Resumenes
WebUI.verifyElementPresent(findTestObject('Object Repository/09-Tarjetas/txtFechaCierreValorRes'), 10)
WebUI.verifyElementPresent(findTestObject('Object Repository/09-Tarjetas/txtFechaVencValorRes'), 10)
WebUI.verifyElementPresent(findTestObject('Object Repository/09-Tarjetas/txtSldoPesosValorRes'), 10)
WebUI.verifyElementPresent(findTestObject('Object Repository/09-Tarjetas/txtSldoDolarValorRes'), 10)

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/TRJ06-CosultaResumenesVisaPOS.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}







