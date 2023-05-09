import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.configuration.RunConfiguration

import javax.swing.JOptionPane

// Se lee el PDF y se guarda el contenido en una variable
//String path = RunConfiguration.getProjectDir() + '/Data Files/'
String path = 'C://Users/natalia.heit/Desktop/'
String vMsg =CustomKeywords.'pkgPDFManage.PDF.getTextFromPage'(path + "Comprobante de operación (2).pdf",1)
println(vMsg)

//Se toman los textos de los objetos
String vFecha
String vNombreServ
String vNroOp
String vImporte

vFecha = WebUI.getText(findTestObject('Object Repository/08-Pagos y Recargas/txtPagosFecha'))
vNombreServ = WebUI.getText(findTestObject('Object Repository/08-Pagos y Recargas/txtNombreServicio'))
vNroOp = WebUI.getText(findTestObject('Object Repository/08-Pagos y Recargas/txtPagosNumeroOperacion'))
vImporte = WebUI.getText(findTestObject('Object Repository/08-Pagos y Recargas/txtPagosImporte'))

//Se crea el archivo .txt
f = new File ("C://Users/natalia.heit/Desktop/Prueba.txt")
f.append(vMsg)
def text = vMsg

//Se buscan en el archivo .txt los textos contenidos en el PDF
//Fecha
if (text.contains(vFecha)) {
		println('Fecha encontrada')
	}
	else {
		
		KeywordUtil.markFailedAndStop("PDF incompleto")
	}
		
//Nombre del Servicio
if (text.contains(vNombreServ)) {
		println('Nombre del servicio encontrado')
	}
	else {
		KeywordUtil.markFailedAndStop("PDF incompleto")
	}

//Numero de operacion
if (text.contains(vNroOp)) {
		println('Nro de Operacion encontrado')
}
else {
		KeywordUtil.markFailedAndStop("PDF incompleto")
}
			
//Importe
if (text.contains(vImporte)) {
		println('Importe encontrado')
}
else {
		KeywordUtil.markFailedAndStop("PDF incompleto")
}




 