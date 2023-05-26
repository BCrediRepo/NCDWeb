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
def vQuery1 = "SELECT * FROM Labels where Id = 49"
def vQuery2 = "SELECT * FROM Labels where Id = 50"
def vQuery3 = "SELECT * FROM Labels where Id = 51"
def vQuery4 = "SELECT * FROM Labels where Id = 52"

String vDNI = null
String vClave = null
String vUsuario = null
String vFechaCta = null
String vConceptoCta = null
String vImporteCta = null
String vSaldoCta = null

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
vFechaCta = vResult1.getString(3)
vConceptoCta = vResult2.getString(3)
vImporteCta = vResult3.getString(3)
vSaldoCta = vResult4.getString(3)

//Cierre de la conexion
CustomKeywords.'pkgDatabase.kwySQL.closeDatabaseConnection'()
//---------------------------------------------------------------------------------------------------------------------

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI, vClave, vUsuario)

//Cliquea el menú desplegable de Cuentas en el Inicio
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbCuentas'))

//Ingresa en cta en Pesos 
WebUI.click(findTestObject('Object Repository/10-Fecha COB/lblCtasCuentaPesos'))

//Valida tabla movimientos
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/lblCtasMovimFecha'), vFechaCta)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/lblCtasMovimConcepto'), vConceptoCta)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/lblCtasMovimImporte'), vImporteCta)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/lblCtasMovimSaldo'), vSaldoCta)

//Valida datos Movimientos cta Pesos
WebUI.verifyElementPresent(findTestObject('Object Repository/10-Fecha COB/txtCtasMovimPesosFecha'), 10)
WebUI.verifyElementPresent(findTestObject('Object Repository/10-Fecha COB/txtCtasMovimPesosConcepto'), 10)
WebUI.verifyElementPresent(findTestObject('Object Repository/10-Fecha COB/txtCtasMovimPesosImporte'), 10)
WebUI.verifyElementPresent(findTestObject('Object Repository/10-Fecha COB/txtCtasMovimPesosSaldo'), 10)

//Vuelve atras e ingresa en cta en Dolares
WebUI.click(findTestObject('Object Repository/10-Fecha COB/icoCtasMovimAtras'))
WebUI.delay(5)
WebUI.click(findTestObject('Object Repository/10-Fecha COB/lblCtasCuentaDolares'))

//Valida datos Movimiento cta Dolares
WebUI.verifyElementPresent(findTestObject('Object Repository/10-Fecha COB/txtCtasMovimDolarFecha'), 10)
WebUI.verifyElementPresent(findTestObject('Object Repository/10-Fecha COB/txtCtasMovimDolarConcepto'), 10)
WebUI.verifyElementPresent(findTestObject('Object Repository/10-Fecha COB/txtCtasMovimDolarImporte'), 10)
WebUI.verifyElementPresent(findTestObject('Object Repository/10-Fecha COB/txtCtasMovimDolarSaldo'), 10)

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/FC01-ConsultarMovimientosPOS.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}


