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

//Set de datos de prueba
def vQuery = "SELECT * FROM UsuariosRMobile WHERE NroDNI = 20144835"
String vDNI = null
String vClave = null
String vUsuario = null

//Conecta a base de datos
CustomKeywords.'pkgDatabase.kwySQL.connectDB'()

//Consulta a la base de datos
ResultSet vResult = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery)
vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)

JOptionPane.showMessageDialog(null, vDNI)
JOptionPane.showMessageDialog(null, vUsuario)
JOptionPane.showMessageDialog(null, vClave)

//Cierre de la conexion
CustomKeywords.'pkgDatabase.kwySQL.closeDatabaseConnection'()

//Se define la URL
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Inicia el browser
WebUI.openBrowser(GlobalVariable.ServerUsado)
WebUI.maximizeWindow()

//Cierra el Banner del inicio
WebUI.click(findTestObject('Object Repository/01-Login/btnLgnCerrarBanner'))

//Ingresa los datos del usuario
WebUI.setText(findTestObject('Object Repository/01-Login/txtLgnDNI'), vDNI)
WebUI.setText(findTestObject('Object Repository/01-Login/txtLgnClave'), vClave)
WebUI.setText(findTestObject('Object Repository/01-Login/txtLgnUsuario'), vUsuario)

WebUI.click(findTestObject('Object Repository/01-Login/btnLgnIngresar'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/icoDsbSalir'))
	
//WebUI.closeBrowser()


//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script
@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {	
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/LGN01-HappyCase.png')
}
//Control de pass solo para Login
@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {	
	CustomKeywords.'pkgUtilities.kwyUtility.fLoginPassStatus'()
}
