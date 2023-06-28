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
def vQuery1 = "SELECT * FROM Parametros WHERE Nombre = 'Alias 1'"
def vQuery2 = "SELECT * FROM Labels WHERE Id = 8"
def vQuery3 = "SELECT * FROM Labels WHERE Id = 9"
def vQuery4 = "SELECT * FROM Parametros WHERE Nombre = 'CBU 1'"

/*
def vQuery3 = "SELECT * FROM Labels WHERE Id = 9"
def vQuery4 = "SELECT * FROM Toast WHERE idToast = 'BX-MSJ-00119'"
def vQuery5 = "SELECT * FROM Toast WHERE idToast = 'BX-MSJ-00124'"
*/
String vDNI = null
String vClave = null
String vUsuario = null
String vAliasBenf = null
String vBenfLbl = null
String vBenfGuardado = null
String vCBUBenf = null
/*
String vBenfLbl = null
String vBenfGuardado = null
String vBenfEditado = null
String vBenfEliminado = null
String vNvoNombre = 'EDITADO'
*/

CustomKeywords.'pkgDatabase.kwySQL.connectDB'()

//Consulta a la base de datos
ResultSet vResult = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery)
ResultSet vResult1 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery1)
ResultSet vResult2 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery2)
ResultSet vResult3 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery3)
ResultSet vResult4 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery4)
/*
ResultSet vResult4 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery4)
ResultSet vResult5 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery5)
*/
vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)
vAliasBenf = vResult1.getString(2)
vBenfLbl = vResult2.getString(3)
vBenfGuardado = vResult3.getString(3)
vCBUBenf = vResult4.getString(2)
/*
vBenfEditado = vResult4.getString(2)
vBenfEliminado = vResult5.getString(2)
*/
//Cierre de la conexion
CustomKeywords.'pkgDatabase.kwySQL.closeDatabaseConnection'()
//---------------------------------------------------------------------------------------------------------------------

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI, vClave, vUsuario)

//Ingresa en la sección Transferencias del Dashboard
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbTransferencias'))

//Valida y cliquea en Agenda de Beneficiarios
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/lnkTrxAgendaBeneficiario'))

//Busca Beneficiario por CBU

WebUI.setText(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/txtTrxBuscarBeneficiarioTipo'), vAliasBenf, FailureHandling.CONTINUE_ON_FAILURE)

//Valida que el Beneficiario no este registrado
WebUI.delay(5)
WebUI.verifyElementText(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/lblTrxBusquedaSinResultadosCBUBenf'),vBenfLbl, FailureHandling.CONTINUE_ON_FAILURE)

//Cliquea en Nuevo Beneficiario
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/btnTrxNuevoBeneficiario'))

//Cliquea en la opcion Alias
WebUI.click(findTestObject('Object Repository/04-Transferencias/txtTrxNuevoBenefAlias'))

//Ingresa Alias
WebUI.setText(findTestObject('Object Repository/04-Transferencias/txtTrxIngresoAlias'), vAliasBenf, FailureHandling.CONTINUE_ON_FAILURE)

//Cliqueo en Continuar
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/btnTrxNuevoBenfContinuar'))

//Selecciono Opción del menu tipo de transferencia
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/mnuTrxTipoNuevoBeneficiario'))
WebUI.delay(5)
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/txtTrxMenuTipoNuevoBenficiario'))

//Selecciono Moneda
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/radTrxMonedaPesosNuevoBeneficiario'))

//Cliqueo en continuar
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/btnTrxContinuarNuevoBeneficiario'))

//Valida Mensaje Exitoso
WebUI.verifyElementText(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/lblTrxBeneficiarioGuardado'),vBenfGuardado, FailureHandling.CONTINUE_ON_FAILURE)

//Cierra Pantalla
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/icoTrxCerrarBeneficiarioGuardado'))

//Busca Beneficiario Guardado
WebUI.setText(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/txtTrxBuscarBeneficiarioTipo'), vCBUBenf, FailureHandling.CONTINUE_ON_FAILURE)

//Cliquea en menú para eliminar
WebUI.delay(10)
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/mnuTrxBeneficiarioDesplegable'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/txtTrxNuevoBeneficiarioEliminar'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/btnTrxConfirmarEliminarBenf'))

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/TRF00-ABBeneficiarioConAliasPOS.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}
