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


vMontoCompra = 1

//Se selecciona el servidor y se cargan los datos
CustomKeywords.'pkgUtilities.kwyUtility.Server'('Internet')

//Se loguea con el usuario seleccionado
CustomKeywords.'pkgUtilities.kwyUtility.Login'(GlobalVariable.AdminDNI, GlobalVariable.AdminClave, GlobalVariable.AdminUsuario)

//Ingresa al módulo de Ahorro e Inversion desde menú lateral
WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbAhorro e Inversin'))

//Cliquea en Compra Dolares
WebUI.click(findTestObject('Object Repository/06-Inversiones/btnComprarDolares'))

//Ingresa Monto
WebUI.click(findTestObject('Object Repository/06-Inversiones/txtMontoCompra'))
WebUI.clearText(findTestObject('Object Repository/06-Inversiones/txtMontoCompra'))
WebUI.sendKeys(findTestObject('Object Repository/06-Inversiones/txtMontoCompra'), vMontoCompra)

//Cliquea en Continuar
WebUI.click(findTestObject('Object Repository/06-Inversiones/btnContinuarCompraDolares'))

//Selecciona Checkbox de declaracion Jurada
WebUI.click(findTestObject('Object Repository/06-Inversiones/chqbxDeclaracionJuradaCompra'))

//Cliquea en Continuar
WebUI.click(findTestObject('Object Repository/06-Inversiones/btnContinuarDeclaracionJurada'))

//Ingresa Segundo Factor
WebUI.click(findTestObject('Object Repository/06-Inversiones/btnContinuarDeclaracionJurada'))



//---------------------------------------------------------------------------------------------------------------------
//Control de fin de script

@com.kms.katalon.core.annotation.TearDownIfFailed
void fTakeFailScreenshot() {
	CustomKeywords.'pkgUtilities.kwyUtility.fFailStatus'('Screenshot/Fails/INV01-CompraDolaresPOS.png')
}

@com.kms.katalon.core.annotation.TearDownIfPassed
void fPassScript() {
	CustomKeywords.'pkgUtilities.kwyUtility.fPassStatus'()
}


