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

String vDNI = null
String vClave = null
String vUsuario = null
String vMonto = 100

CustomKeywords.'pkgDatabase.kwySQL.connectDB'()

//Consulta a la base de datos
ResultSet vResult = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery)

vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)

//Cierre de la conexion
CustomKeywords.'pkgDatabase.kwySQL.closeDatabaseConnection'()
//---------------------------------------------------------------------------------------------------------------------

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI, vClave, vUsuario)

//Ingresa al módulo de Ahorro e Inversion desde menú lateral
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbAhorro e Inversin'))

//Cliquea en la opción Simular y Crear PF
WebUI.click(findTestObject('Object Repository/10-Fecha COB/lnkInvSimularPlazoFijo'))

//Ingresa Monto
WebUI.delay(5)
WebUI.click(findTestObject('Object Repository/06-Inversiones/txtInvPFMonto'))
WebUI.sendKeys(findTestObject('Object Repository/06-Inversiones/txtInvPFMonto'), vMonto)

//Selecciona Tipo de PF
WebUI.click(findTestObject('Object Repository/06-Inversiones/mnuInvPFTipo'))
WebUI.click(findTestObject('Object Repository/06-Inversiones/txtInvPFTipo'))
WebUI.click(findTestObject('Object Repository/06-Inversiones/btnInvPFSimular'))

//Valida los datos ingresados en la simulacion
CustomKeywords.'pkgUtilities.kwyUtility.comparacionSimularCrearPlazoFijo'(60)

//Confirma Crear PF
WebUI.click(findTestObject('Object Repository/10-Fecha COB/chqbxInvPFDeclaracionJurada'))
WebUI.click(findTestObject('Object Repository/10-Fecha COB/btnInvPFConfirmar'))

//Firma Bypass
WebUI.sendKeys(findTestObject('Object Repository/10-Fecha COB/txtInvPFClaveBypass'), vClave)
WebUI.click(findTestObject('Object Repository/10-Fecha COB/btnInvPFConfirmarBypass'))

//Valida los datos en el comprobante
CustomKeywords.'pkgUtilities.kwyUtility.ValidacionComprobanteCrearPlazoFijo'(60)

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/FC06-SimularCrearPlazoFijoPOS.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}


