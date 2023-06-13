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

//Busca en calendario una fecha desde en el calendario
WebUI.click(findTestObject('Object Repository/10-Fecha COB/icoTrxRecibidasCalendarioDesde'))
WebUI.click(findTestObject('Object Repository/10-Fecha COB/txtTrxMesDesdeCalendario'))
WebUI.click(findTestObject('Object Repository/10-Fecha COB/txtTrxCalendarioMesDesde'))
WebUI.click(findTestObject('Object Repository/10-Fecha COB/txtTrxCalendarioAnioDesde'))
WebUI.click(findTestObject('Object Repository/10-Fecha COB/icoTrxConfirmarFecha'))
WebUI.click(findTestObject('Object Repository/10-Fecha COB/txtTrxCalendarioDiaDesde'))

//Cliquea en Buscar
WebUI.click(findTestObject('Object Repository/10-Fecha COB/btnTrxBuscarCalendario'))

//Pedir usuario para validación de resultados filtro por fecha