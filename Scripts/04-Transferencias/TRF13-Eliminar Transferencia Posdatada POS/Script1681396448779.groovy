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
String vValorMonto = 1

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

//Ingresa en la sección Transferencias del Dashboard
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbTransferencias'))

//Inicia Transferencia desde Menú de Beneficiario destacado
WebUI.click(findTestObject('Object Repository/04-Transferencias/icoTrfBeneficiarioDestacadoDesplegable'))

//Clickea en Nueva Transferencia
WebUI.click(findTestObject('Object Repository/04-Transferencias/txtTrfBenefDestacadoNuevaTransferencia'))

//Ingresa Monto
WebUI.click(findTestObject('Object Repository/04-Transferencias/04-Calendario/txtIngresarMontoPosdatada'))
WebUI.sendKeys(findTestObject('Object Repository/04-Transferencias/04-Calendario/txtIngresarMontoPosdatada'), vValorMonto)

//Selecciona Titularidad
WebUI.click(findTestObject('Object Repository/04-Transferencias/lblTrfSeleccionTitularidadFormulario'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/lblTitularidadTextoFormulario'))

//Selecciona Fecha Programada
WebUI.scrollToElement(findTestObject('Object Repository/04-Transferencias/04-Calendario/icoTrfCalendarioFechaEnvio'), 10)
WebUI.click(findTestObject('Object Repository/04-Transferencias/04-Calendario/icoTrfCalendarioFechaEnvio'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/04-Calendario/icoTrfCalendarioMesSiguiente'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/04-Calendario/lblTrfCalendarioFechaEnvio'))

//Cliquea en Continuar
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/btnContinuarFormulario'))

//Cliquea en boton Confirmar
WebUI.click(findTestObject('Object Repository/04-Transferencias/btnConfirmarTransferencias'))

//Ingresa Clave Bypass
WebUI.setText(findTestObject('Object Repository/04-Transferencias/txtTrfClaveBypass'), vClave)

//Confirma Operación
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/btnTrfConfirmarBypass'))

//Valida Destinatario
WebUI.verifyElementVisible(findTestObject('Object Repository/04-Transferencias/txtTrfBeneficiarioDestino'))

//Vuelve a Transferencias
WebUI.click(findTestObject('Object Repository/04-Transferencias/btnIrTransferencias'))

//Elimina la Transferencia Posdatada
WebUI.click(findTestObject('Object Repository/04-Transferencias/lnkSolapaProgramadas'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/icoEliminarProgramada'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/btnAceptarEliminarProgramada'))

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/TRF08-TransferenciasPosdatadasCredicoopDolaresPOS.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}
