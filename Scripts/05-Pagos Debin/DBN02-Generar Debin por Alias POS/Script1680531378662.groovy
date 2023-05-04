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
def vQuery2 = "SELECT * FROM Parametros WHERE "

String vDNI = null
String vClave = null
String vUsuario = null
String vAliasBenf = null
String vMontoPesos = '10'

CustomKeywords.'pkgDatabase.kwySQL.connectDB'()

//Consulta a la base de datos
ResultSet vResult = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery)
ResultSet vResult2 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery2)

vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)
vAliasBenf = vResult2.getString(4)

//Cierre de la conexion
CustomKeywords.'pkgDatabase.kwySQL.closeDatabaseConnection'()
//---------------------------------------------------------------------------------------------------------------------

//def vAliasBenf = findTestData('04-Parametros/Parametros').getValue(2,2)
//def vMontoPesos = '10'

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI, vUsuario, vClave)

//Ingresa en la sección Otras Operaciones y Pagos Debin del Dashboard
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbOtrasOperaciones'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbPagosDebin'))

//Cliquea en Generar Debin
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnGenerarDebin'))

// Ingresa y busca Alias solicitante
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnCBUSolicitante'))
WebUI.setText(findTestObject('Object Repository/05-Pagos Debin/txtDbnCBUSolicitante'), vAliasBenf)
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnBuscarSolicitante'))

//Completa formulario
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/intDbnMontoPesos'))
WebUI.clearText('Object Repository/05-Pagos Debin/txtDbnMontoCero')
WebUI.setText(findTestObject('Object Repository/05-Pagos Debin/intDbnMontoPesos'), vMontoPesos)

WebUI.click(findTestObject('Object Repository/05-Pagos Debin/mnuDbnDuracionSolicitud'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnDuracionHoras'))

WebUI.click(findTestObject('Object Repository/05-Pagos Debin/mnuDbnConcepto'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnConceptoTipo'))

WebUI.click(findTestObject('Object Repository/05-Pagos Debin/mnuDbnTitularidadCuenta'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnTitularidadTipo'))

WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnContinuar'))

//Valida Datos y confirma la operación
WebUI.verifyElementVisible(findTestObject('Object Repository/05-Pagos Debin/lblDbnConfirmarFormulario'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnConfirmarFormulario'))

//Valida Pantalla de Éxito
WebUI.verifyElementVisible(findTestObject('Object Repository/05-Pagos Debin/lblDbnConfirmarFormulario'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnVolverDebin'))


//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/DBN02-GenerarDebinPorAliasPOS.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}


