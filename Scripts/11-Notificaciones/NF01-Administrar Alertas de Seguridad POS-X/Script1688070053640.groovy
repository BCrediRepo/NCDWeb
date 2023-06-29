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
import com.kms.katalon.core.util.KeywordUtil

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

import javax.swing.JOptionPane

import java.util.ArrayList

//-------------------Conecta a base de datos--------------------------------------------
def vQuery = "SELECT * FROM UsuariosRMobile WHERE NroDNI = 20144835"
def vQuery1 = "SELECT * FROM Toast WHERE idToast = 20144835"

String vDNI = null
String vClave = null
String vUsuario = null
String vMjeExito = null

CustomKeywords.'pkgDatabase.kwySQL.connectDB'()

//Consulta a la base de datos
ResultSet vResult = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery)
ResultSet vResult1 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery1)


vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)
vMjeExito = vResult1.getString(4)

//Cierre de la conexion
CustomKeywords.'pkgDatabase.kwySQL.closeDatabaseConnection'()
//---------------------------------------------------------------------------------------------------------------------

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI, vClave, vUsuario)

//Ingresa en Notificaciones
WebUI.click(findTestObject('Object Repository/11-Notificaciones/icoNtfNotificaciones'))
WebUI.delay(2)
WebUI.click(findTestObject('Object Repository/11-Notificaciones/icoNtfConfirgurarNotificaciones'))

//Edita Notificacion
WebUI.click(findTestObject('Object Repository/11-Notificaciones/lnkNtfEditarNotificacion'))

//Toma el atributo
vInicioSesion = WebUI.getAttribute(findTestObject('Object Repository/11-Notificaciones/chqNtfMovimientoSeguridadInicioSesion'), 'kdisabled')

//Valida que las opciones esten habilitadas para ese atributo
if (vInicioSesion == 'true'){
	println("Las Opciones de Tipo de movimiento de seguridad se encuentran Deshabiitadas.")
	WebUI.click(findTestObject('Object Repository/11-Notificaciones/chqNtfCanalesNotificacionesBanca'))
} else{
	KeywordUtil.markFailedAndStop("Las Opciones de Tipo de movimiento de seguridad se encuentran habiitadas.")
}

WebUI.click(findTestObject('Object Repository/11-Notificaciones/btnNtfAceptarConfiguracin'),)

WebUI.click(findTestObject('Object Repository/11-Notificaciones/btnNtfAceptarConfiguracin'))

//Guarda el cambio y valida el toast
WebUI.click(findTestObject('Object Repository/11-Notificaciones/btnNtfAceptarConfiguracin'))
WebUI.verifyElementText(findTestObject('Object Repository/11-Notificaciones/txtNtfConfiguracinExitosa'), vMjeExito, FailureHandling.CONTINUE_ON_FAILURE)


/*
//Valida que las opciones esten habilitadas en Tipo de movimiento de seguridad
if (vInicioSesion == 'false'){
	println("Las Opciones de Tipo de movimiento de seguridad se encuentran habiitadas.")
	
}else{
	KeywordUtil.markFailedAndStop("Las Opciones de Tipo de movimiento de seguridad se encuentran Deshabiitadas.")
}

//Vuelve a destildar la opción en Canales Habilitados

*/


/*
// Crea una instancia de ArrayList
ArrayList<String> ListaOpciones = new ArrayList<String>("Notificaciones en la Banca", "Notificaciones Push", "Inicio de sesión", "Actualización de credenciales")

listaOpciones = WebUI.verifyElementVisible(findTestObject('Object Repository/11-Notificaciones/liNtfListaEditarNotificaciones'))
//listaOpciones = WebUI.verifyElementVisible(findTestObject('Object Repository/11-Notificaciones/txtNtfNotificacionesBanca'))

// Agrega elementos a la lista
ListaOpciones.add("Elemento 1")
ListaOpciones.add("Elemento 2")

// Selecciona el primer elemento de la lista
String primerElemento = ListaOpciones.get(0)
println("Primer elemento: " + primerElemento)

*/