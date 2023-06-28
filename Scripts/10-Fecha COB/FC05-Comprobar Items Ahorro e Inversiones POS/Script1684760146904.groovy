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

def vQuery1 = "SELECT * FROM Labels WHERE Id = 81"

def vQuery2 = "SELECT * FROM Labels WHERE Id = 53"
def vQuery3 = "SELECT * FROM Labels WHERE Id = 54"
def vQuery4 = "SELECT * FROM Labels WHERE Id = 55"
def vQuery5 = "SELECT * FROM Labels WHERE Id = 56"
def vQuery6 = "SELECT * FROM Labels WHERE Id = 57"

def vQuery7 = "SELECT * FROM Labels WHERE Id = 58"
def vQuery8 = "SELECT * FROM Labels WHERE Id = 59"
def vQuery9 = "SELECT * FROM Labels WHERE Id = 60"
def vQuery10 = "SELECT * FROM Labels WHERE Id = 61"

def vQuery11 = "SELECT * FROM Labels WHERE Id = 62"
def vQuery12 = "SELECT * FROM Labels WHERE Id = 63"
def vQuery13 = "SELECT * FROM Labels WHERE Id = 64"
def vQuery14 = "SELECT * FROM Labels WHERE Id = 65"
def vQuery15 = "SELECT * FROM Labels WHERE Id = 66"
def vQuery16 = "SELECT * FROM Labels WHERE Id = 67"

def vQuery17 = "SELECT * FROM Labels WHERE Id = 68"
def vQuery18 = "SELECT * FROM Labels WHERE Id = 69"
def vQuery19 = "SELECT * FROM Labels WHERE Id = 70"
def vQuery20 = "SELECT * FROM Labels WHERE Id = 71"
def vQuery21 = "SELECT * FROM Labels WHERE Id = 72"
def vQuery22 = "SELECT * FROM Labels WHERE Id = 73"
def vQuery23 = "SELECT * FROM Labels WHERE Id = 74"

def vQuery24 = "SELECT * FROM Labels WHERE Id = 75"
def vQuery25 = "SELECT * FROM Labels WHERE Id = 76"
def vQuery26 = "SELECT * FROM Labels WHERE Id = 77"
def vQuery27 = "SELECT * FROM Labels WHERE Id = 78"
def vQuery28 = "SELECT * FROM Labels WHERE Id = 79"
def vQuery29 = "SELECT * FROM Labels WHERE Id = 80"

String vDNI = null
String vClave = null
String vUsuario = null

String vCVReferencia = null

String vPlazoFijo = null
String vCompraVenta = null
String vFondosComunes = null
String vTitulos = null
String vAcciones = null

String vPFNombre = null
String vPFTipo = null
String vPFVencim = null
String vPFMonto = null

String vFCITipo = null
String vFCIDescripcn = null
String vFCICtaParte = null
String vFCICotizacn = null
String vFCIFchaCotzn = null
String vFCISldoEstmdo = null

String vTITTipo = null
String vTITDescripcn = null
String vTITNominal = null
String vTITResidual = null
String vTITCotizacn = null
String vTITFchaCotzn = null
String vTITSldoEstmdo = null

String vACCTipo = null
String vACCDescripcn = null
String vACCNominal = null
String vACCCotizacn = null
String vACCFchaCotzn = null
String vACCSldoEstmdo = null

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
ResultSet vResult21 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery21)
ResultSet vResult22 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery22)
ResultSet vResult23 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery23)

ResultSet vResult24 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery24)
ResultSet vResult25 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery25)
ResultSet vResult26 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery26)
ResultSet vResult27 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery27)
ResultSet vResult28 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery28)
ResultSet vResult29 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery29)

vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)

vCVReferencia = vResult1.getString(3)

vPlazoFijo = vResult2.getString(3)
vCompraVenta = vResult3.getString(3)
vFondosComunes = vResult4.getString(3)
vTitulos = vResult5.getString(3)
vAcciones = vResult6.getString(3)

vPFNombre = vResult7.getString(3)
vPFTipo = vResult8.getString(3)
vPFVencim = vResult9.getString(3)
vPFMonto = vResult10.getString(3)

vFCITipo = vResult11.getString(3)
vFCIDescripcn = vResult12.getString(3)
vFCICtaParte = vResult13.getString(3)
vFCICotizacn = vResult14.getString(3)
vFCIFchaCotzn = vResult15.getString(3)
vFCISldoEstmdo = vResult16.getString(3)

vTITTipo = vResult17.getString(3)
vTITDescripcn = vResult18.getString(3)
vTITNominal = vResult19.getString(3)
vTITResidual = vResult20.getString(3)
vTITCotizacn = vResult21.getString(3)
vTITFchaCotzn = vResult22.getString(3)
vTITSldoEstmdo = vResult23.getString(3)

vACCTipo = vResult24.getString(3)
vACCDescripcn = vResult25.getString(3)
vACCNominal = vResult26.getString(3)
vACCCotizacn = vResult27.getString(3)
vACCFchaCotzn = vResult28.getString(3)
vACCSldoEstmdo = vResult29.getString(3)

//Cierre de la conexion
CustomKeywords.'pkgDatabase.kwySQL.closeDatabaseConnection'()
//---------------------------------------------------------------------------------------------------------------------

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI, vClave, vUsuario)

//Ingresa al módulo de Ahorro e Inversion desde menú lateral
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbAhorro e Inversin'))

//Valida los titulos de la sección Ahorro e Inversion
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/lblInvPlazosFijos'), vPlazoFijo, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/lblInvCompraVentaDlares'), vCompraVenta, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/lblInvFondosComunesInversin'), vFondosComunes, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/lblInvTtulos'), vTitulos, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/lblInvAcciones'), vAcciones, FailureHandling.CONTINUE_ON_FAILURE)

//Valida los titulos de Compra Venta
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvCVCotizacinReferencia'), vCVReferencia, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/10-Fecha COB/txtInvCVCotizacinCompra'), FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/10-Fecha COB/txtInvCVCotizacinVenta'), FailureHandling.CONTINUE_ON_FAILURE)

//Valida los titulos de la Tabla de PF
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvPFTablaNombre'), vPFNombre, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvPFTablaTipo'), vPFTipo, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvPFTablaVencimiento'), vPFVencim, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvPFTablaMonto'), vPFMonto, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/10-Fecha COB/txtInvPFlListaPlazosFijos'), FailureHandling.CONTINUE_ON_FAILURE)

//Valida los titulos de la tabla FCI
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtIvnFCITablaTipo'), vFCITipo, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvFCITablaDescripcin'), vFCIDescripcn, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvFCITablaCuotapartes'), vFCICtaParte, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvFCITablaCotizacin'), vFCICotizacn, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvFCITablaFechaCotizacin'), vFCIFchaCotzn, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvFCITablaSaldoEstimado'), vFCISldoEstmdo, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/10-Fecha COB/txtInvFCIListaFondos'), FailureHandling.CONTINUE_ON_FAILURE)

//Valida los titulos de la tabla Titulos
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvTituTablaTipo'), vTITTipo, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvTituTablaDescripcin'), vTITDescripcn, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvTituTablaNominal'), vTITNominal, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvTituTablaResidual'), vTITResidual, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvTituTablaCotizacin'), vTITCotizacn, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvTituTablaFechaCotizacin'), vTITFchaCotzn, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvTituTablaSaldoEstimado'), vTITSldoEstmdo, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/10-Fecha COB/txtInvTITListaTitulos'), FailureHandling.CONTINUE_ON_FAILURE)

//Valida los titulos de la tabla Acciones
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvACCTablaTipo'), vACCTipo, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvACCTablaDescripcin'), vACCDescripcn, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvACCTablaNominal'), vACCNominal, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvACCTablaCotizacin'), vACCCotizacn, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvACCTablaFechaCotizacin'), vACCFchaCotzn, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvACCTablaSaldoEstimado'), vACCSldoEstmdo, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyElementVisible(findTestObject('Object Repository/10-Fecha COB/txtInvACCListaAcciones'), FailureHandling.CONTINUE_ON_FAILURE)

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/TRF05-ComprobarItemsAhorroInversionPOS.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}
