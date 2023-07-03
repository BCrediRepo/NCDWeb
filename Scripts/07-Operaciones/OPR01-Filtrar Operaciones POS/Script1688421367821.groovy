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
def vQuery1 = "SELECT * FROM Labels WHERE id = 10"
def vQuery2 = "SELECT * FROM Labels WHERE id = 11"
def vQuery3 = "SELECT * FROM Labels WHERE id = 12"
def vQuery4 = "SELECT * FROM Labels WHERE id = 13"
def vQuery5 = "SELECT * FROM Labels WHERE id = 14"
def vQuery6 = "SELECT * FROM Labels WHERE id = 15"
def vQuery7 = "SELECT * FROM Labels WHERE id = 16"
def vQuery8 = "SELECT * FROM Labels WHERE id = 17"
def vQuery9 = "SELECT * FROM Labels WHERE id = 18"
def vQuery10 = "SELECT * FROM Labels WHERE id = 19"
def vQuery11 = "SELECT * FROM Labels WHERE id = 20"
def vQuery12 = "SELECT * FROM Labels WHERE id = 21"
def vQuery13 = "SELECT * FROM Labels WHERE id = 22"
def vQuery14 = "SELECT * FROM Labels WHERE id = 100"
def vQuery15 = "SELECT * FROM Labels WHERE id = 101"
def vQuery16 = "SELECT * FROM Labels WHERE id = 102"
def vQuery17 = "SELECT * FROM Labels WHERE id = 103"
def vQuery18 = "SELECT * FROM Labels WHERE id = 104"
def vQuery19 = "SELECT * FROM Labels WHERE id = 105"
def vQuery20 = "SELECT * FROM Labels WHERE id = 106"

String vDNI = null
String vClave = null
String vUsuario = null
String vTodas = null
String vTransferencias = null
String vExtracciones = null
String vModo = null
String vCheques = null
String vAcceso = null
String vPagos = null
String vTarjetas = null
String vAhorro = null
String vDebin = null
String vAfip = null
String vRecargas = null
String vSolicitudes = null
String vOperacion = null
String vFecha = null
String vTipo = null
String vEstado = null
String vCtaOrigen = null
String vReferencia = null
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
ResultSet vResult7 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery7)
ResultSet vResult8 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery8)
ResultSet vResult9 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery9)
ResultSet vResult10 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery10)
ResultSet vResult11 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery11)
ResultSet vResult12 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery12)
ResultSet vResult13 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery13)
ResultSet vResult14 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery14)
ResultSet vResult15 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery15)
ResultSet vResult16 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery16)
ResultSet vResult17 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery17)
ResultSet vResult18 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery18)
ResultSet vResult19 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery19)
ResultSet vResult20 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery20)

vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)

vTodas = vResult1.getString(3)
vTransferencias = vResult2.getString(3)
vExtracciones = vResult3.getString(3)
vModo = vResult4.getString(3)
vCheques = vResult5.getString(3)
vAcceso = vResult6.getString(3)
vPagos = vResult7.getString(3)
vTarjetas = vResult8.getString(3)
vAhorro = vResult9.getString(3)
vDebin = vResult10.getString(3)
vAfip = vResult11.getString(3)
vRecargas = vResult12.getString(3)
vSolicitudes = vResult13.getString(3)
vOperacion = vResult14.getString(3)
vFecha = vResult15.getString(3)
vTipo = vResult16.getString(3)
vEstado = vResult17.getString(3)
vCtaOrigen = vResult18.getString(3)
vReferencia = vResult19.getString(3)
vImporte = vResult20.getString(3)

//Cierre de la conexion
CustomKeywords.'pkgDatabase.kwySQL.closeDatabaseConnection'()
//---------------------------------------------------------------------------------------------------------------------

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI, vClave, vUsuario)

//Ingresa al módulo de Operaciones
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbMisOperaciones'))

//Cliquea en el menú desplegable de Operaciones y valida opciones
WebUI.click(findTestObject('Object Repository/07-Operaciones/mnuOprOperacion'))

WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtOprTodas'), vTodas)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtOprTransferencias'), vTransferencias)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtOprExtraccionesSinTarjeta'), vExtracciones)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtOprModo'), vModo)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtOprCheques'), vCheques)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtOprAccesoSeguridad'), vAcceso)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtOprPagosRecargas'), vPagos)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtOprTarjetas'), vTarjetas)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtOprAhorroInversion'), vAhorro)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtOprDebin'), vDebin)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtOprPagosAFIP'), vAfip)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtOprRecargas'), vRecargas)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtOprSolicitudes'), vSolicitudes)

//Valida filtro Operacion
WebUI.click(findTestObject('Object Repository/07-Operaciones/txtOprTransferencias'))

//Valida filtro tipo de Operacion
WebUI.click(findTestObject('Object Repository/07-Operaciones/mnuOprTipoOperacion'))
WebUI.click(findTestObject('Object Repository/07-Operaciones/txtOprTipoTransferenciaCuentasPropias'))

//Selecciona fecha
WebUI.click(findTestObject('Object Repository/07-Operaciones/icoOprCalendarioDesde'))
WebUI.click(findTestObject('Object Repository/07-Operaciones/icoOprCalendarioAtras'))
WebUI.click(findTestObject('Object Repository/07-Operaciones/icoOprCalendarioDia'))

//Busca Operaciones
WebUI.click(findTestObject('Object Repository/07-Operaciones/btnOprBuscarOperaciones'))

//Valida tabla de resultados
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/lblOprTablaOperacion'), vOperacion, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/lblOprTablaFecha'), vFecha, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/lblOprTablaTipoOperacion'), vTipo, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/lblOprTablaEstado'), vEstado, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/lblOprTablaCuentaOrigen'), vCtaOrigen, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/lblOprTablaReferencia'), vReferencia, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/lblOprTablaImporte'), vImporte, FailureHandling.CONTINUE_ON_FAILURE)

//Valida resultados
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Operaciones/txtOprTablaNumeroOperacion'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Operaciones/txtOprTablaFecha'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Operaciones/txtOprTablaTipoOperacion'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Operaciones/txtOprTablaEstado'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Operaciones/txtOprTablaCuenta'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Operaciones/txtOprTablaReferenciaNombre'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Operaciones/txtOprTablaReferenciaCBU'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/07-Operaciones/txtOprTablaMonto'), FailureHandling.CONTINUE_ON_FAILURE)

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/OPR01-Filtrar Operaciones POS.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}