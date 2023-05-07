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
def vQuery = "SELECT * FROM UsuariosRMobile WHERE NroDNI = 30276301"
def vQuery1 = "SELECT * FROM Labels WHERE Id = 26"
def vQuery2 = "SELECT * FROM Labels WHERE Id = 27"
def vQuery3 = "SELECT * FROM Labels WHERE Id = 28"
def vQuery4 = "SELECT * FROM Labels WHERE Id = 29"
def vQuery5 = "SELECT * FROM Labels WHERE Id = 30"
def vQuery6 = "SELECT * FROM Labels WHERE Id = 31"
def vQuery7 = "SELECT * FROM Labels WHERE Id = 32"
def vQuery8 = "SELECT * FROM Labels WHERE Id = 33"

String vDNI = null
String vClave = null
String vUsuario = null

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

vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)
vTasa1 = vResult1.getString(3)
vTasa2 = vResult2.getString(3)
vTasa3 = vResult3.getString(3)
vTasa4 = vResult4.getString(3)
vTasa5 = vResult5.getString(3)
vTasa6 = vResult6.getString(3)
vTasa7 = vResult7.getString(3)
vTasa8 = vResult8.getString(3)


//Cierre de la conexion
CustomKeywords.'pkgDatabase.kwySQL.closeDatabaseConnection'()
//---------------------------------------------------------------------------------------------------------------------

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI, vClave, vUsuario)

//Ingresa al módulo de Ahorro e Inversion desde menú lateral
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbAhorro e Inversin'))

//Cliquea en menu y selecciona Consultar tasas de Plazo fijo
WebUI.click(findTestObject('Object Repository/06-Inversiones/mnuInvPlazoFijo'))
WebUI.click(findTestObject('Object Repository/06-Inversiones/txtInvConsultarTasasPlazoFijo'))

//Valida las Tasas de Plazo Fijo para la cta seleccionada
WebUI.verifyElementText(findTestObject('Object Repository/06-Inversiones/txtInvTasa1'), vTasa1)
WebUI.verifyElementText(findTestObject('Object Repository/06-Inversiones/txtInvTasa2'), vTasa2)
WebUI.verifyElementText(findTestObject('Object Repository/06-Inversiones/txtInvTasa3'), vTasa3)
WebUI.verifyElementText(findTestObject('Object Repository/06-Inversiones/txtInvTasa4'), vTasa4)
WebUI.verifyElementText(findTestObject('Object Repository/06-Inversiones/txtInvTasa5'), vTasa5)
WebUI.verifyElementText(findTestObject('Object Repository/06-Inversiones/txtInvTasa6'), vTasa6)
WebUI.verifyElementText(findTestObject('Object Repository/06-Inversiones/txtInvTasa7'), vTasa7)
WebUI.verifyElementText(findTestObject('Object Repository/06-Inversiones/txtInvTasa8'), vTasa8)

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/INV03-ConsultarTasasPlazoFijoPOS.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}