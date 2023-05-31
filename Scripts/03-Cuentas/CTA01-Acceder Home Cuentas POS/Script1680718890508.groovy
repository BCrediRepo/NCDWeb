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
def vQuery2 = "SELECT * FROM Labels WHERE Id = 2"
def vQuery3 = "SELECT * FROM Labels WHERE Id = 3"
def vQuery4 = "SELECT * FROM Labels WHERE Id = 4"
def vQuery5 = "SELECT * FROM Labels WHERE Id = 5"

String vDNI = null
String vClave = null
String vUsuario = null
String vCuenta = null
String vFavorita = null
String vMoneda = null
String vSaldo = null

CustomKeywords.'pkgDatabase.kwySQL.connectDB'()

//Consulta a la base de datos
ResultSet vResult = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery)
ResultSet vResult2 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery2)
ResultSet vResult3 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery3)
ResultSet vResult4 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery4)
ResultSet vResult5 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery5)

vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)
vCuenta = vResult2.getString(3)
vFavorita = vResult3.getString(3)
vMoneda = vResult4.getString(3)
vSaldo = vResult5.getString(3)

//Cierre de la conexion
CustomKeywords.'pkgDatabase.kwySQL.closeDatabaseConnection'()
//---------------------------------------------------------------------------------------------------------------------

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI, vClave, vUsuario)

//Ingresa al módulo de Cuentas desde menú lateral y mapea los campos
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbCuentas'))

WebUI.verifyElementText(findTestObject('Object Repository/03-Cuentas/lblCtaTituloCuentaTabla'), vCuenta)
WebUI.verifyElementText(findTestObject('Object Repository/03-Cuentas/lblCtaTituloFavoritaTabla'), vFavorita)
WebUI.verifyElementText(findTestObject('Object Repository/03-Cuentas/lblCtaTituloMonedaTabla'), vMoneda)

CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

WebUI.verifyElementText(findTestObject('Object Repository/03-Cuentas/lblCtaTituloSaldoTabla'), vSaldo)

//Ingresa al módulo de Cuentas desde Inicio y mapea los campos
WebUI.click(findTestObject('Object Repository/03-Cuentas/icoCtaAtras'))

WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbCuentasInicio'))

WebUI.verifyElementText(findTestObject('Object Repository/03-Cuentas/lblCtaTituloCuentaTabla'), vCuenta)
WebUI.verifyElementText(findTestObject('Object Repository/03-Cuentas/lblCtaTituloFavoritaTabla'), vFavorita)
WebUI.verifyElementText(findTestObject('Object Repository/03-Cuentas/lblCtaTituloMonedaTabla'), vMoneda)
WebUI.verifyElementText(findTestObject('Object Repository/03-Cuentas/lblCtaTituloSaldoTabla'), vSaldo) 

//---------------------------------------------------------------------------------------------------------------------

//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/CTAS01-AccederHomeCuentas.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}

