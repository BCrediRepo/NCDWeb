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

//Ingresa en la sección Otras Operaciones y Pagos Debin del Dashboard
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbOtrasOperaciones'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbPagosDebin'))

//Selecciona menu desplegable e ingresa en Consultar Debin por estado
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/mnuDbnConsultarDebinEstado'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnConsultarDebinPorEstadoCuenta'))

//Filtra por Cuenta y visualiza detalle de Debines Recibidos
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/mnuDbnConsularEstadoCuenta'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnConsultarEstadoCuenta'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnConsultarEstadoBuscar'))

//Valida los datos en el listado de Debines Recibidos por Cuenta
CustomKeywords.'pkgUtilities.kwyUtility.comparacionDebinRecibidosDetalleCuenta'(60)

//Limpia filtro por Cuenta
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/mnuDbnConsularEstadoCuenta'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnConsultarEstadoCuentasTodas'))
WebUI.delay(5)

//Filtra por Estado y visualiza detalle de Debines Recibidos
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/mnuDbnConsultarEstadoEstados'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnConsultaEstadosVencidos'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnConsultarEstadoBuscar'))

//Valida que filtre por estado seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.comparacionDebinRecibidosDetalleEstado'(60)

//Limpia filtro por Estado
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/mnuDbnConsultarEstadoEstados'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnConsultarEstadoEstadosTodos'))
WebUI.delay(5)

//Ingresa a Histórico Debin Generados
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/lblDbnConsultarDebinGenerados'))

//Filtra por Cuenta y visualiza detalle de Debines Generados
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/mnuDbnConsularEstadoCuenta'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnConsultarEstadoCuentaGenerados'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnConsultarEstadoBuscar'))

//Valida los datos en el listado de Debines Generados por Cuenta
CustomKeywords.'pkgUtilities.kwyUtility.comparacionDebinGeneradosDetalleCuenta'(60)

//Limpia filtro por Cuenta
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/mnuDbnConsularEstadoCuenta'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnConsultarEstadoCuentasTodasGenerados'))
WebUI.delay(5)

//Filtra por Estado y visualiza detalle de Debines Generados
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/mnuDbnConsultarEstadoEstados'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnConsultaEstadoGeneradosAcreditados'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnConsultarEstadoBuscar'))

//Valida que filtre por estado seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.comparacionDebinGeneradosDetalleEstado'(60)

//Limpia filtro por Estado
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/mnuDbnConsultarEstadoEstados'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtConsultaDebinEstadoGeneradosTodos'))
WebUI.delay(5)

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/DBN05-ConsultarHistoricoDebin.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}




//------------------------------------------------------------------------------------------------------------------------

/*
//Filtra por Fecha y visualiza detalle de Debines Recibidos
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/icoDbnCalendarioConsultarDebinDesde'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/icoDbnCalendarioMesSiguienteDesde'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/icoDbnCalendarioDiaDesde'))

WebUI.click(findTestObject('Object Repository/05-Pagos Debin/icoDbnCalendarioConsultarDebinHasta'))
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/icoDbnCalendarioDiaHasta'))

WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnConsultarEstadoBuscar'))
*/
