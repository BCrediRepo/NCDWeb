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

def vQuery1 = "SELECT * FROM Labels WHERE Id = 53"
def vQuery2 = "SELECT * FROM Labels WHERE Id = 54"
def vQuery3 = "SELECT * FROM Labels WHERE Id = 55"
def vQuery4 = "SELECT * FROM Labels WHERE Id = 56"
def vQuery5 = "SELECT * FROM Labels WHERE Id = 57"

def vQuery6 = "SELECT * FROM Labels WHERE Id = 58"
def vQuery7 = "SELECT * FROM Labels WHERE Id = 59"
def vQuery8 = "SELECT * FROM Labels WHERE Id = 60"
def vQuery9 = "SELECT * FROM Labels WHERE Id = 61"

def vQuery10 = "SELECT * FROM Labels WHERE Id = 62"
def vQuery11 = "SELECT * FROM Labels WHERE Id = 63"
def vQuery12 = "SELECT * FROM Labels WHERE Id = 64"
def vQuery13 = "SELECT * FROM Labels WHERE Id = 65"
def vQuery14 = "SELECT * FROM Labels WHERE Id = 66"
def vQuery15 = "SELECT * FROM Labels WHERE Id = 67"

String vDNI = null
String vClave = null
String vUsuario = null

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


vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)

vPlazoFijo = vResult1.getString(3)
vCompraVenta = vResult2.getString(3)
vFondosComunes = vResult3.getString(3)
vTitulos = vResult4.getString(3)
vAcciones = vResult5.getString(3)

vPFNombre = vResult6.getString(3)
vPFTipo = vResult7.getString(3)
vPFVencim = vResult8.getString(3)
vPFMonto = vResult9.getString(3)

vFCITipo = vResult10.getString(3)
vFCIDescripcn = vResult11.getString(3)
vFCICtaParte = vResult12.getString(3)
vFCICotizacn = vResult13.getString(3)
vFCIFchaCotzn = vResult14.getString(3)
vFCISldoEstmdo = vResult15.getString(3)

//Cierre de la conexion
CustomKeywords.'pkgDatabase.kwySQL.closeDatabaseConnection'()
//---------------------------------------------------------------------------------------------------------------------

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI, vClave, vUsuario)

//Ingresa al módulo de Ahorro e Inversion desde menú lateral
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbAhorro e Inversin'))

//Valida los Titulos de la sección Ahorro e Inversion
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/lblInvPlazosFijos'), vPlazoFijo)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/lblInvCompraVentaDlares'), vCompraVenta)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/lblInvFondosComunesInversin'), vFondosComunes)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/lblInvTtulos'), vTitulos)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/lblInvAcciones'), vAcciones)

//Valida los Titulos de la Tabla de PF
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvPFTablaNombre'), vPFNombre)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvPFTablaTipo'), vPFTipo)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvPFTablaVencimiento'), vPFVencim)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvPFTablaMonto'), vPFMonto)

//Valida los Titulos de la tabla FCI
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtIvnFCITablaTipo'), vFCITipo)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvFCITablaDescripcin'), vFCIDescripcn)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvFCITablaCuotapartes'), vFCICtaParte)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvFCITablaCotizacin'), vFCICotizacn)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvFCITablaFechaCotizacin'), vFCIFchaCotzn)
WebUI.verifyElementText(findTestObject('Object Repository/10-Fecha COB/txtInvFCITablaSaldoEstimado'), vFCISldoEstmdo)

//Valida los Titulos de la tabla Titulos


//Valida los Titulos de la tabla Acciones