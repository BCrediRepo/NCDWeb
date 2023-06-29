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
def vQuery = "SELECT * FROM UsuariosRMobile WHERE NroDNI = 10833645"
def vQuery1 = "SELECT * FROM UsuariosRMobile WHERE NroDNI = 24823100"
def vQuery2 = "SELECT * FROM Parametros WHERE Nombre = 'CBU 3'"

String vDNI = null
String vClave = null
String vUsuario = null
String vValorMonto = 1
String vDNI1 = null
String vClave1 = null
String vUsuario1 = null
String vBenfTrf = null
String vSaldoInicial
String vSaldoI
String vSaldoFinal
String vSaldoF

CustomKeywords.'pkgDatabase.kwySQL.connectDB'()

//Consulta a la base de datos
ResultSet vResult = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery)
ResultSet vResult1 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery1)
ResultSet vResult2 = CustomKeywords.'pkgDatabase.kwySQL.executeQuery'(vQuery2)

vDNI = vResult.getString(2)
vUsuario = vResult.getString(3)
vClave = vResult.getString(4)
vDNI1 = vResult1.getString(2)
vUsuario1 = vResult1.getString(3)
vClave1 = vResult1.getString(4)
vBenfTrf = vResult2.getString(2)

//Cierre de la conexion
CustomKeywords.'pkgDatabase.kwySQL.closeDatabaseConnection'()
//---------------------------------------------------------------------------------------------------------------------

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Loguea con usuario Receptor
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI, vClave, vUsuario)

//Obtiene el Saldo Inicial del Beneficiario Receptor
vSaldoInicial = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrfSaldoInicial'), vSaldoI)
//JOptionPane.showMessageDialog(null, vSaldoInicial)
println vSaldoInicial

//Lo transforma de String a Numero
String vSaldoNuevo = vSaldoInicial.replace(",", ".")
String vNumero = vSaldoNuevo.substring(2)
System.out.println(vNumero)

//Para Decimal
float vNumeroFinal = Double.parseDouble(vNumero)
System.out.println(vNumeroFinal)

//Loguea con usuario Emisor
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI1, vClave1, vUsuario1)

//Realiza la transferencia
//Ingresa en la sección Transferencias del Dashboard
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbTransferencias'))

//Cliquea en Agenda de Beneficiarios
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/lnkTrxAgendaBeneficiario'))

//Busca un Beneficiario con cuenta en Pesos
WebUI.setText(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/txtTrxBuscarBeneficiarioTipo'), vBenfTrf)

//Selecciona el beneficiario e inicia la transferencia
WebUI.delay(5)
WebUI.click(findTestObject('Object Repository/04-Transferencias/txtTrxBenefCuentaPesos'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/lnkTrxIniciarTrxBenefPesos'))

//Ingresa Monto
WebUI.click(findTestObject('Object Repository/04-Transferencias/04-Calendario/txtTrxIngresarMontoPosdatada'))
WebUI.sendKeys(findTestObject('Object Repository/04-Transferencias/04-Calendario/txtTrxIngresarMontoPosdatada'), vValorMonto)

//Selecciona Titularidad
WebUI.click(findTestObject('Object Repository/04-Transferencias/lblTrxSeleccionTitularidadFormulario'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/lblTrxTitularidadTextoFormulario'))

//Selecciona Fecha Programada
WebUI.scrollToElement(findTestObject('Object Repository/04-Transferencias/04-Calendario/icoTrxCalendarioFechaEnvio'), 10)
WebUI.click(findTestObject('Object Repository/04-Transferencias/04-Calendario/icoTrxCalendarioFechaEnvio'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/04-Calendario/icoTrxCalendarioMesSiguiente'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/04-Calendario/lblTrxCalendarioFechaEnvio'))

//Cliquea en Continuar
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/btnTrxContinuarFormulario'))

//Cliquea en boton Confirmar
WebUI.click(findTestObject('Object Repository/04-Transferencias/btnTrxConfirmarTransferenciaInmediata'))

//Ingresa Clave Bypass
WebUI.sendKeys(findTestObject('Object Repository/04-Transferencias/txtTrxClaveBypass'), vClave1)

//Confirma Operación
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/btnTrxConfirmarBypass'))
WebUI.delay(5)

//Valida destinatario y monto Exitoso
//CustomKeywords.'pkgUtilities.kwyUtility.comparacionDatosExitoPosdatadaPesos'(60)
//NOTA: Debe desloguear y volver a loguear para validar el registro de la programada o eliminarlo

//Loguea con usuario Receptor
CustomKeywords.'pkgUtilities.kwyUtility.Login'(vDNI, vClave, vUsuario)

//Obtiene el Saldo Final del Beneficiario Receptor
vSaldoFinal = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrfSaldoInicial'), vSaldoI)
//JOptionPane.showMessageDialog(null, vSaldoFinal)
println vSaldoFinal

//Lo transforma de String a Numero
String vSaldoNuevo2 = vSaldoFinal.replace(",", ".")
String vNumero2 = vSaldoNuevo2.substring(2)
System.out.println(vNumero2)

// Comparar el valor inicial con el valor final
if (vSaldoFinal == vSaldoInicial) {
  System.out.println("El valor del Saldo inicial no ha cambiado.")
} else if (vSaldoFinal < vSaldoInicial) {
  System.out.println("El valor del Saldo inicial se ha restado.")
} else {
  System.out.println("El valor del Saldo inicial se ha incrementado.")
}

//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/TRF20-TransferenciaPostdatadaCompletaPOS.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}
