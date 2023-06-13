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
import internal.GlobalVariable

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import com.kms.katalon.core.util.KeywordUtil

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
vClaveBypass = vResult.getString(4)

//Cierre de la conexion
CustomKeywords.'pkgDatabase.kwySQL.closeDatabaseConnection'()
//---------------------------------------------------------------------------------------------------------------------

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI, vClave, vUsuario)

//Ingresa en la sección Transferencias del Dashboard
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbTransferencias'))

//Cliquea en Nueva Transferencia
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/btnTrxNuevaTransferenciaInicio'))

//Selecciono solapa Mis cuentas Credicoop
WebUI.click(findTestObject('Object Repository/04-Transferencias/lblTrxMisCuentasCredicoop'))







TestObject toList =  findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/LiTrxCuentasPropiasCredicoop2')

//println  toList.

//println toList.size()
println 'ACAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA'






// Obtengo todos los elementos de la lista de mis cuentas credicop
List<WebElement> listMisCuentas = WebUI.findWebElements(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/LiTrxCuentasPropiasCredicoop2'),2)


//Obtengo el primer elemento de la lista de mis cuentas credicop
WebElement primeraCuenta = listMisCuentas.stream().findFirst().get()


//Obtengo la moneda de la primera cuenta
WebElement weMoneda = primeraCuenta.findElement(By.xpath("//div[@id='flxMisCuentasCredicoopRow_lblAccountCurrency']"))
String monedaPrimeraCuenta = weMoneda.getAttribute("innerHTML")
println monedaPrimeraCuenta

//Obtengo el nombre de la primera cuenta
WebElement weNombrePrimeraCuenta = primeraCuenta.findElement(By.xpath("//div[@id='flxMisCuentasCredicoopRow_lblAccountTitle']"))
String nombrePrimeraCuenta = weNombrePrimeraCuenta.getAttribute("innerHTML")
println nombrePrimeraCuenta

//Obtengo el numero de la primera cuenta
WebElement weNumeroPrimeraCuenta = primeraCuenta.findElement(By.xpath("//div[@id='flxMisCuentasCredicoopRow_lblAccountDetail']"))
String numeroPrimeraCuenta = weNumeroPrimeraCuenta.getAttribute("innerHTML")
println numeroPrimeraCuenta

//Obtengo el denominacion de la primera cuenta
WebElement weDenominacionPrimeraCuenta = primeraCuenta.findElement(By.xpath("//div[@id='flxMisCuentasCredicoopRow_lblDenominacionCuenta']"))
String denominacionPrimeraCuenta = weDenominacionPrimeraCuenta.getAttribute("innerHTML")
println denominacionPrimeraCuenta










//Valida Cuenta Pesos 
CustomKeywords.'pkgUtilities.kwyUtility.comparacionListadoTrxPropiasPesos'(60)

if (GlobalVariable.vMonedaCta == '$' && GlobalVariable.vMonedaTrx == '$'){
	println("La cuenta seleccionada corresponde a una moneda en pesos.")
	
}else{
	KeywordUtil.markFailedAndStop("La cuenta seleccionada NO corresponde a una moneda en pesos.")
}

//Ingresa Monto valido y menor al saldo de la cuenta
WebUI.click(findTestObject('Object Repository/04-Transferencias/txtTrxMontoFormulario'))
WebUI.sendKeys(findTestObject('Object Repository/04-Transferencias/txtTrxMontoFormulario'),vValorMonto)

//Selecciona Titularidad
WebUI.click(findTestObject('Object Repository/04-Transferencias/lblTrxSeleccionTitularidadFormulario'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/lblTrxTitularidadTextoFormulario'))

//Valida Monto Transferencia
CustomKeywords.'pkgUtilities.kwyUtility.comparacionMontoTrxPropias'(60)

//Cliquea en boton Confirmar
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/btnTrxConfirmar'))

//Ingresa Clave Bypass
WebUI.setText(findTestObject('Object Repository/04-Transferencias/txtTrxClaveBypass'), vClave)

////Confirma Operación
//WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/btnTrxConfirmarBypass'))
//
////Valida Numero de operacion
//CustomKeywords.'pkgUtilities.kwyUtility.comparacionNumeroOperacionTrxPropias'(60)

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
    CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/TRF01-TransferenciaPropiasCredicoopPesosPOS.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
    CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}