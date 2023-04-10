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

//def vTipoTrf = findTestData('03-Transferencias/TipoTrf').getValue(2,2)
def vCBUBenf = findTestData('04-Parametros/Parametros').getValue(2,1)
def vNvoNombre = 'NombreEditado'

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(GlobalVariable.Cliente1DNI, GlobalVariable.Cliente1Clave, GlobalVariable.Cliente1Usuario)

//Ingresa en la sección Transferencias del Dashboard
WebUI.verifyElementVisible(findTestObject('Object Repository/02-Dashboard/lnkDsbTransferencias'))
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbTransferencias'))

//Valida y cliquea en Agenda de Beneficiarios
WebUI.verifyElementVisible(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/lnkTrfAgendaBeneficiario'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/lnkTrfAgendaBeneficiario'))

//Busca Beneficiario por CBU
WebUI.setText(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/txtTrfBuscarBeneficiarioTipo'), vCBUBenf)

//Valida que el Beneficiario no este registrado
WebUI.delay(5)
WebUI.verifyElementVisible(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/lblBusquedaSinResultadosCBUBenf'))

//Cliquea en Nuevo Beneficiario
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/btnNuevoBeneficiario'))

//Cliquea en Opcion CBU/CVU
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/btnCBUBeneficiario'))

//Ingresa CBU
WebUI.setText(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/txtCBUBeneficiario'), vCBUBenf)

//Cliqueo en Continuar
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/btnNuevoBenfContinuar'))

//Selecciono Opción del menu tipo de transferencia
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/mnuTrfTipoNuevoBeneficiario'))
WebUI.delay(5)
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/txtTrfMenuTipoNuevoBenficiario'))

//Selecciono Moneda 
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/radTrfMonedaPesosNuevoBeneficiario'))

//Cliqueo en continuar
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/btnTrfContinuarNuevoBeneficiario'))

//Valida Mensaje Exitoso
WebUI.verifyElementVisible(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/lblTrfBeneficiarioGuardado'))

//Cierra Pantalla
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/icoCerrarBeneficiarioGuardado'))

//Busca Beneficiario Guardado
WebUI.setText(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/txtTrfBuscarBeneficiarioTipo'), vCBUBenf)

//Edita Beneficiario desde el menu
WebUI.delay(5)
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/mnuTrfBeneficiarioDesplegable'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/txtTrfNuevoBeneficiarioEditar'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/icoTrfEditarBeneficiario'))

WebUI.clearText('Object Repository/04-Transferencias/03-Nuevo Beneficiario/txtTrfNuevoNombreBeneficiario')
WebUI.setText(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/txtTrfNuevoNombreBeneficiario'), vNvoNombre)
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/icoTrfConfirmaNombreEditadoBenf'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/btnTrfGuardarNombreEditadoBenf'))
WebUI.verifyElementVisible(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/lblTrfMensajeNombreEditadoBenf'))

//Busca Beneficiario Editado y valida
WebUI.setText(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/txtTrfBuscarBeneficiarioTipo'), vCBUBenf)

//Cliquea en menú para eliminar
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/mnuTrfBeneficiarioDesplegable'))
WebUI.click(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/txtTrfNuevoBeneficiarioEliminar'))
WebUI.verifyElementVisible(findTestObject('Object Repository/04-Transferencias/03-Nuevo Beneficiario/lblTrfMensajeExitosoEliminarBenf'))




