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
import javax.swing.JOptionPane

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
String vEstado

CustomKeywords.'pkgDatabase.kwySQL.connectDB'()

//Consulta a la base de datos
ResultSet vResult = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery)

vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)
println(vDNI)
println(vUsuario)
println(vClave)

//Cierre de la conexion
CustomKeywords.'pkgDatabase.kwySQL.closeDatabaseConnection'()
//---------------------------------------------------------------------------------------------------------------------

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI, vClave, vUsuario)

//Ingresa en la sección Otras Operaciones y Pagos Debin del Dashboard
WebUI.verifyElementVisible(findTestObject('Object Repository/02-Dashboard/lnkDsbOtrasOperaciones'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbOtrasOperaciones'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbPagosDebin'))

//Clickea en Administrar Cuentas
WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnAdministrarCuentas'))
WebUI.verifyElementVisible(findTestObject('Object Repository/05-Pagos Debin/txtDbnCBUCuentaAdherirDebin'))

vEstado = WebUI.getAttribute(findTestObject('Object Repository/05-Pagos Debin/btnDbnAdherirDebinCuenta'),'value')

if (vEstado =='Adherir'){
	
	WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnAdherirDebinCuenta'))
	
	}else {

		WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnAdherirDebinCuenta'))
		//WebUI.verifyElementVisible(findTestObject('Object Repository/05-Pagos Debin/lblDbnDesadherirDebinExitoso'))
	}
	
	//WebUI.verifyElementVisible(findTestObject('Object Repository/05-Pagos Debin/lblDbnAdherirDebinExitoso'))
	
	//vAlertText = WebUI.getAlertText('Object Repository/05-Pagos Debin/lblDbnAdherirDebinExitoso')
	//WebUI.verifyMatch(vAlertText, 'La cuenta se adhirió correctamente.', false)
	
	
	//WebUI.verifyTextPresent("La cuenta se adhirió correctamente.", true)
	
	
	//WebUI.getText('Object Repository/05-Pagos Debin/lblDbnAdherirDebinExitoso')
	//WebUI.verifyMatch('La cuenta se adhirió correctamente.','La cuenta se adhirió correctamente.', true)


	//---------------------------------------------------------------------------------------------------------------------
	//Control de fin de script
	
	@com.kms.katalon.core.annotation.TearDownIfFailed
	void fTakeFailScreenshot() {
		CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/DBN03-AdherirDesadherirDebinPOS.png')
	}
	
	@com.kms.katalon.core.annotation.TearDownIfPassed
	void fPassScript() {
		CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
	}



