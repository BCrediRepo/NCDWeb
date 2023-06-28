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
def vQuery1 = "SELECT * FROM Labels WHERE Id = 94"

String vDNI = null
String vClave = null
String vUsuario = null
String vLimiteExitoso = null

CustomKeywords.'pkgDatabase.kwySQL.connectDB'()

//Consulta a la base de datos
ResultSet vResult = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery)
ResultSet vResult1 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery1)

vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)
vLimiteExitoso = vResult1.getString(3)

//Cierre de la conexion
CustomKeywords.'pkgDatabase.kwySQL.closeDatabaseConnection'()
//---------------------------------------------------------------------------------------------------------------------

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI, vClave, vUsuario)

//Ingresa al módulo de Tarjetas
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbTarjetas'))

//Cliquea menu desplegable de tarjeta debito y selecciona Modificar Limites
WebUI.click(findTestObject('Object Repository/09-Tarjetas/mnuTrjDebitoDesplegable'))
WebUI.click(findTestObject('Object Repository/09-Tarjetas/txtTrjDebitoModificarLimitesExtraccin'))

//Seleccina un límite del listado
WebUI.click(findTestObject('Object Repository/09-Tarjetas/rdbTrjDebitoSeleccionLimite'))
WebUI.click(findTestObject('Object Repository/09-Tarjetas/btnTrjDebitoGuardarLimite'))

//Nota: Agregar un if para que seleccione otro Límite si esta seleccionado

//Ingresa firma Bypass
WebUI.setText(findTestObject('Object Repository/09-Tarjetas/txtTrjDebitoLimiteConfirmarBypass'), vClave, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/09-Tarjetas/btnTrjDebitoLimiteConfirmarBypass'))

//Valida Datos y cierra la solapa
WebUI.verifyElementText(findTestObject('Object Repository/09-Tarjetas/lblTrjDebitoModificarLimiteExitoso'), vLimiteExitoso, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/09-Tarjetas/icoTrjDebitoModificarLimiteCerrarSolapa'))

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/TRJ07-ModificarLimiteTarjetaPOS.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}


