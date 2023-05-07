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
def vQuery = "SELECT * FROM UsuariosRMobile WHERE NroDNI = 17319143"
def vQuery1 = "SELECT * FROM Parametros WHERE Nombre = 'CBU 2'"
def vQuery2 = "SELECT * FROM Labels WHERE Id = 8"
def vQuery3 = "SELECT * FROM Labels WHERE Id = 9"
def vQuery4 = "SELECT * FROM Toast WHERE idToast = 'BX-MSJ-00119'"
def vQuery5 = "SELECT * FROM Toast WHERE idToast = 'BX-MSJ-00124'"

String vDNI = null
String vClave = null
String vUsuario = null
String vCBUBenf = null
String vBenfLbl = null
String vSucursal = "001"
String vNroCuenta = "054864"
String vDigito = "8"
String vBenfGuardado = null
String vBenfEditado = null
String vBenfEliminado = null
String vNvoNombre = 'EDITADO'

CustomKeywords.'pkgDatabase.kwySQL.connectDB'()

//Consulta a la base de datos
ResultSet vResult = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery)
ResultSet vResult1 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery1)
ResultSet vResult2 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery2)
ResultSet vResult3 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery3)
ResultSet vResult4 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery4)
ResultSet vResult5 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery5)

vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)
vCBUBenf = vResult1.getString(2)
vBenfLbl = vResult2.getString(3)
vBenfGuardado = vResult3.getString(3)
vBenfEditado = vResult4.getString(2)
vBenfEliminado = vResult5.getString(2)

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
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/lnkTrfAgendaBeneficiario'))

//Busca Beneficiario por CBU
WebUI.setText(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/txtTrfBuscarBeneficiarioTipo'), vCBUBenf)

//Valida que el Beneficiario no este registrado
WebUI.delay(5)
WebUI.verifyElementText(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/lblBusquedaSinResultadosCBUBenf'),vBenfLbl)

//Cliquea en Nuevo Beneficiario
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/btnNuevoBeneficiario'))

//Cliquea en opcion Cuenta
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxNvoBenefNmeroCuenta'))

//Ingresa Tipo de Cuenta
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/mnuTrxTipoCuenta'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxCA'))

//Ingresa Numero de Cuenta
WebUI.setText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxSucursal'), vSucursal)
WebUI.setText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxNmeroCuenta'), vNroCuenta)
WebUI.setText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxDigitoVerificacion'), vDigito)

//Cliqueo en Continuar
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/btnNuevoBenfContinuar'))

//Selecciono Opción del menu tipo de transferencia
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/mnuTrfTipoNuevoBeneficiario'))
WebUI.delay(5)
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/txtTrfMenuTipoNuevoBenficiario'))

//Selecciono Moneda
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/radTrfMonedaPesosNuevoBeneficiario'))

//Cliqueo en continuar
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/btnTrfContinuarNuevoBeneficiario'))

//Valida Mensaje Exitoso
WebUI.verifyElementText(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/lblTrfBeneficiarioGuardado'),vBenfGuardado)

//Cierra Pantalla
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/icoCerrarBeneficiarioGuardado'))

//Busca Beneficiario Guardado
WebUI.setText(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/txtTrfBuscarBeneficiarioTipo'), vCBUBenf)

//Edita Beneficiario desde el menu
WebUI.delay(5)
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/mnuTrfBeneficiarioDesplegable'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/txtTrfNuevoBeneficiarioEditar'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/icoTrfEditarBeneficiario'))

WebUI.delay(10)
//WebUI.clearText('Object Repository/04-Transferencias/03-Nuevo Beneficiario/txtNombreViejoBenef')
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/txtTrfNuevoNombreBeneficiario'))
WebUI.setText(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/txtTrfNuevoNombreBeneficiario'), vNvoNombre)
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/icoTrfConfirmaNombreEditadoBenf'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/btnTrfGuardarNombreEditadoBenf'))
//WebUI.verifyElementText(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/lblTrfMensajeNombreEditadoBenf'),vBenfEditado)

//Cliquea en menú para eliminar
WebUI.delay(10)
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/mnuTrfBeneficiarioDesplegable'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/txtTrfNuevoBeneficiarioEliminar'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/btnTrfConfirmarEliminarBenf'))
//WebUI.verifyElementText(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/lblTrfMensajeExitosoEliminarBenf'),vBenfEliminado)

//NOTA: REVISAR VALIDACIONES CONFIRMACION

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/TRF00-ABMBeneficiarioConCuentaPOS.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}




