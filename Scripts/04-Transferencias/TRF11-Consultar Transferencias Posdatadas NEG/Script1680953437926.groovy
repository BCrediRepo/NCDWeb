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


def vConcepto = 'Transferencia'
def vLblPosdt = findTestData('05-Labels/Labels').getValue(2,12)


//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(GlobalVariable.AdminDNI, GlobalVariable.AdminClave, GlobalVariable.AdminUsuario)

//Ingresa en la sección Transferencias del Dashboard
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbTransferencias'))

//Ingresa a la solapa Programadas
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lnkTrfProgramadas'))

//Valida que no haya resultados
WebUI.verifyElementText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrfSinTransferenciasProgramadas'), vLblPosdt)

//Busca por concepto
WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrfBuscarConceptoProgramadas'))
WebUI.sendKeys(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrfBuscarConceptoProgramadas'), vConcepto)
WebUI.verifyElementText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrfBusquedaSinResultados'),'La búsqueda no obtuvo resultados.')

//Busca en calendario


//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/TRF11-ConsultarTransferenciasPosdatadasNEG.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}



