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
def vQuery2 = "SELECT * FROM Labels WHERE id = 10"

String vDNI = null
String vClave = null
String vUsuario = null
String vTodas = null
String vTodas = null

CustomKeywords.'pkgDatabase.kwySQL.connectDB'()

//Consulta a la base de datos
ResultSet vResult = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery)
ResultSet vResult2 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery2)

vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)
/*
vTodas = vResult2.getString(3)
vTransferencias = vResult2.getString(3)
vExtracciones = vResult2.getString(3)
vModo = vResult2.getString(3)
vCheques = vResult2.getString(3)
vAcceso = vResult2.getString(3)
vPagos = vResult2.getString(3)
vTarjetas = vResult2.getString(3)
vAhorro = vResult2.getString(3)
vDebin = vResult2.getString(3)
vAfip = vResult2.getString(3)
vRecargas = vResult2.getString(3)
vSolicitudes = vResult2.getString(3)
*/
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
WebUI.click(findTestObject('Object Repository/07-Operaciones/mnuOperacion'))
/*
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtTodas'), vTodas)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtTransferencias'), vTransferencias)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtTodas'), vExtracciones)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtTodas'), vModo)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtTodas'), vCheques)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtTodas'), vAcceso)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtTodas'), vPagos)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtTodas'), vTarjetas)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtTodas'), vAhorro)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtTodas'), vDebin)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtTodas'), vAfip)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtTodas'), vRecargas)
WebUI.verifyElementText(findTestObject('Object Repository/07-Operaciones/txtTodas'), vSolicitudes)
*/

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