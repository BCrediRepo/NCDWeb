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
def vQuery = "SELECT * FROM UsuariosRMobile WHERE NroDNI = 22809322"
def vQuery1 = "SELECT * FROM Toast WHERE idToast = 'Toa-SinProgramadas'"
def vQuery2 = "SELECT * FROM Toast WHERE idToast = 'Toa-SinResultado'"

String vDNI = null
String vClave = null
String vUsuario = null
String vSinProgramadas = null
String vValorMonto = 1
String vTxtBusqueda = 'Transferencia'
String vSinResultado = null

CustomKeywords.'pkgDatabase.kwySQL.connectDB'()

//Consulta a la base de datos
ResultSet vResult = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery)
ResultSet vResult1 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery1)
ResultSet vResult2 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery2)

vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)
vSinProgramadas = vResult1.getString(2)
vSinResultado = vResult2.getString(2)

//Cierre de la conexion
CustomKeywords.'pkgDatabase.kwySQL.closeDatabaseConnection'()
//---------------------------------------------------------------------------------------------------------------------

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI, vClave, vUsuario)

//Ingresa en la sección Transferencias del Dashboard
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbTransferencias'))

//Ingresa a la solapa Programadas
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lnkTrxProgramadas'))

//Valida mensaje sin Transferencias Programadas
WebUI.verifyElementText(findTestObject('Object Repository/04-Transferencias/txtTrxSinTransferenciasProgramadas'), vSinProgramadas, FailureHandling.CONTINUE_ON_FAILURE)

//Cliquea en el campo y busca por Transferencias
WebUI.click(findTestObject('Object Repository/04-Transferencias/txtTrxBusquedaPosdatadas'))
WebUI.sendKeys(findTestObject('Object Repository/04-Transferencias/txtTrxBusquedaPosdatadas'), vTxtBusqueda, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.delay(5)

//Valida mensaje de busqueda sin resultados
WebUI.verifyElementText(findTestObject('Object Repository/04-Transferencias/txtTrxBusquedaSinResultados'), vSinResultado, FailureHandling.CONTINUE_ON_FAILURE)

//NOTA: Agregar validacion calendario

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/TRF11-ConsultarTransferenciasPosdatadasNEG.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}



