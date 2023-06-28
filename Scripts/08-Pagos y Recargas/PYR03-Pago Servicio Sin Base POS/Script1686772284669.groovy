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

String vDNI = null
String vClave = null
String vUsuario = null
String vMonto = 0.5
String vEnte = "Iplan Networks"
def vCodigo = "0474921"

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

//Ingresa al módulo de Pagos y Recargas
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbPagos y Recargas'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbServicios y Tarjetas'))

//Adherir Servicio
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/btnPYRAdherirNuevoServicio'))
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/mnuPYRCategoriaAdherirServicio'))
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/txtPYRTelefonia'))

//Ingresa Ente
WebUI.setText(findTestObject('Object Repository/08-Pagos y Recargas/txtPYRIngresoEnte'), vEnte, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.delay(5)
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/lnkPYRIplanNetworks'))

//Ingresa el código
WebUI.setText(findTestObject('Object Repository/08-Pagos y Recargas/txtPYRCodigoPago'), vCodigo, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/btnPYRAdherirServicioSolapa'))

//Selecciona pagar
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/btnPYRPagarServicioSinBase'))

//Ingresa Monto y confirma
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/txtPYRFormularioPagoMonto'))
WebUI.sendKeys(findTestObject('Object Repository/08-Pagos y Recargas/txtPYRFormularioPagoMonto'), vMonto, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/mnuPYRFormularioCuentaDebitar'))
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/txtPYRFormularioCuentaDebitar'))
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/btnPYRFormularioConfirmarPago'))

//Ingresa firma ByPass y confirma
WebUI.setText(findTestObject('Object Repository/08-Pagos y Recargas/txtPYRPagoServicioClaveBypass'), vClave, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/btnPYRConfirmarClaveBypass'))

//Validar pantalla pago exitoso

//NOTA: REEMPLAZAR CON SERVICIO SIN BASE MERCADO LIBRE CUANDO CIERREN BUG: BX-3319

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/PYR03-PagoServicioSinBasePOS.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}

