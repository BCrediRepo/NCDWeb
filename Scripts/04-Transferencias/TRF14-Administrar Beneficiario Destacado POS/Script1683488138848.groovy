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
def vQuery1 = "SELECT * FROM Toast WHERE idToast = 'Toa-024'"
def vQuery2 = "SELECT * FROM Toast WHERE idToast = 'BX-MSJ-00119'"

String vDNI = null
String vClave = null
String vUsuario = null
String vMjeError = null
String vMjeExito = null

CustomKeywords.'pkgDatabase.kwySQL.connectDB'()

//Consulta a la base de datos
ResultSet vResult = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery)
ResultSet vResult1 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery1)
ResultSet vResult2 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery2)

vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)
vMjeError = vResult1.getString(2)
vMjeExito = vResult2.getString(2)

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

//Selecciona nuevo Beneficiario Destacado con 3	Seleccionados
WebUI.click(findTestObject('Object Repository/04-Transferencias/icoTrxBenfNoDestacado'))

//Valida mensaje error
WebUI.verifyElementText(findTestObject('Object Repository/04-Transferencias/txtTrxMsjeExcedeBenfDestacado'), vMjeError)

//DesSelecciona Beneficiario Destacado y vuelve a Seleccionarlo
WebUI.click(findTestObject('Object Repository/04-Transferencias/icoTrxBenfDestacado'))
WebUI.verifyElementText(findTestObject('Object Repository/04-Transferencias/txtTrxMsjeOperacionRealizadaExito'), vMjeExito)
WebUI.delay(5)
WebUI.click(findTestObject('Object Repository/04-Transferencias/icoTrxBenfDestacado'))

//Valida mensaje ok
WebUI.verifyElementText(findTestObject('Object Repository/04-Transferencias/txtTrxMsjeOperacionRealizadaExito'), vMjeExito)

//---------------------------------------------------------------------------------------------------------------------

//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/TRF14-AdministrarBeneficiarioDestacadoPOS.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}
	

/*
if(cantidadFav == 3){
	System.out.println("---YA HAY 3 BENEFICIARIOS DESTACADOS")
}else if(cantidadFav < 3){
            System.out.println("---NO HAY 3 BENEFICIARIOS DESTACADOS")
}
*/