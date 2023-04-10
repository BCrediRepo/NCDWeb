package pkgUtilities

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

import javax.swing.JOptionPane

public class kwyUtility {

	/*----------------------------------------------------------------------------------------------*
	 *Server																						*
	 *					    																		*
	 *Acceso al server			   																	*
	 *												    											*
	 *Parametros:																					*
	 *ServerName: nombre del servidor a conectar													*
	 *FF: Friends and Family																		*
	 *RMobile: RMobile		  																		*
	 *Internet: Servidor expuesto a Internet														*
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def Server(def vServerName) {
		if(vServerName == 'FF'){

			GlobalVariable.ServerUsado = 'https://'+ GlobalVariable.URLFF+'/'+ GlobalVariable.TestServer
			GlobalVariable.LogServerUsado = 'FF'

			//Carga de Usuarios preconfigurados
			GlobalVariable.AdminDNI = findTestData('02-Usuarios/SetUsers-FF').getValue(3,1)
			GlobalVariable.AdminClave = findTestData('02-Usuarios/SetUsers-FF').getValue(5,1)
			GlobalVariable.AdminUsuario = findTestData('02-Usuarios/SetUsers-FF').getValue(4,1)
			GlobalVariable.AdminByPass = findTestData('02-Usuarios/SetUsers-FF').getValue(5,1)

			GlobalVariable.Cliente1DNI = findTestData('02-Usuarios/SetUsers-FF').getValue(3,2)
			GlobalVariable.Cliente1Clave = findTestData('02-Usuarios/SetUsers-FF').getValue(5,2)
			GlobalVariable.Cliente1Usuario = findTestData('02-Usuarios/SetUsers-FF').getValue(4,2)
			GlobalVariable.Cliente1ByPass = findTestData('02-Usuarios/SetUsers-FF').getValue(5,2)

			GlobalVariable.Cliente2DNI = findTestData('02-Usuarios/SetUsers-FF').getValue(3,3)
			GlobalVariable.Cliente2Clave = findTestData('02-Usuarios/SetUsers-FF').getValue(5,3)
			GlobalVariable.Cliente2Usuario = findTestData('02-Usuarios/SetUsers-FF').getValue(4,3)
			GlobalVariable.Cliente2ByPass = findTestData('02-Usuarios/SetUsers-FF').getValue(5,3)

			GlobalVariable.Cliente3DNI = findTestData('02-Usuarios/SetUsers-FF').getValue(3,4)
			GlobalVariable.Cliente3Clave = findTestData('02-Usuarios/SetUsers-FF').getValue(5,4)
			GlobalVariable.Cliente3Usuario = findTestData('02-Usuarios/SetUsers-FF').getValue(4,4)
			GlobalVariable.Cliente3ByPass = findTestData('02-Usuarios/SetUsers-FF').getValue(5,4)

			GlobalVariable.Cliente4DNI = findTestData('02-Usuarios/SetUsers-FF').getValue(3,5)
			GlobalVariable.Cliente4Clave = findTestData('02-Usuarios/SetUsers-FF').getValue(5,5)
			GlobalVariable.Cliente4Usuario = findTestData('02-Usuarios/SetUsers-FF').getValue(4,5)
			GlobalVariable.Cliente4ByPass = findTestData('02-Usuarios/SetUsers-FF').getValue(5,5)

		}


		if(vServerName == 'RMobile'){

			GlobalVariable.ServerUsado = 'https://'+ GlobalVariable.URLRMob+'/'+ GlobalVariable.TestServer
			GlobalVariable.LogServerUsado = 'RMobile'

			//Carga de Usuarios preconfigurados
			GlobalVariable.AdminDNI = findTestData('02-Usuarios/SetUsers-RM').getValue(3,1)
			GlobalVariable.AdminClave = findTestData('02-Usuarios/SetUsers-RM').getValue(5,1)
			GlobalVariable.AdminUsuario = findTestData('02-Usuarios/SetUsers-RM').getValue(4,1)
			GlobalVariable.AdminByPass = findTestData('02-Usuarios/SetUsers-RM').getValue(5,1)

			GlobalVariable.Cliente1DNI = findTestData('02-Usuarios/SetUsers-RM').getValue(3,2)
			GlobalVariable.Cliente1Clave = findTestData('02-Usuarios/SetUsers-RM').getValue(5,2)
			GlobalVariable.Cliente1Usuario = findTestData('02-Usuarios/SetUsers-RM').getValue(4,2)
			GlobalVariable.Cliente1ByPass = findTestData('02-Usuarios/SetUsers-RM').getValue(5,2)

			GlobalVariable.Cliente2DNI = findTestData('02-Usuarios/SetUsers-RM').getValue(3,3)
			GlobalVariable.Cliente2Clave = findTestData('02-Usuarios/SetUsers-RM').getValue(5,3)
			GlobalVariable.Cliente2Usuario = findTestData('02-Usuarios/SetUsers-RM').getValue(4,3)
			GlobalVariable.Cliente2ByPass = findTestData('02-Usuarios/SetUsers-RM').getValue(5,3)

			GlobalVariable.Cliente3DNI = findTestData('02-Usuarios/SetUsers-RM').getValue(3,4)
			GlobalVariable.Cliente3Clave = findTestData('02-Usuarios/SetUsers-RM').getValue(5,4)
			GlobalVariable.Cliente3Usuario = findTestData('02-Usuarios/SetUsers-RM').getValue(4,4)
			GlobalVariable.Cliente3ByPass = findTestData('02-Usuarios/SetUsers-RM').getValue(5,4)

			GlobalVariable.Cliente4DNI = findTestData('02-Usuarios/SetUsers-RM').getValue(3,5)
			GlobalVariable.Cliente4Clave = findTestData('02-Usuarios/SetUsers-RM').getValue(5,5)
			GlobalVariable.Cliente4Usuario = findTestData('02-Usuarios/SetUsers-RM').getValue(4,5)
			GlobalVariable.Cliente4ByPass = findTestData('02-Usuarios/SetUsers-RM').getValue(5,5)
		}


		if(vServerName == 'Internet'){

			GlobalVariable.ServerUsado = 'https://'+ GlobalVariable.URLInternet+'/'+ GlobalVariable.TestServer
			GlobalVariable.LogServerUsado = 'Internet'

			//Carga de Usuarios preconfigurados
			GlobalVariable.AdminDNI = findTestData('02-Usuarios/SetUsers-FF').getValue(3,1)
			GlobalVariable.AdminClave = findTestData('02-Usuarios/SetUsers-FF').getValue(5,1)
			GlobalVariable.AdminUsuario = findTestData('02-Usuarios/SetUsers-FF').getValue(4,1)
			GlobalVariable.AdminByPass = findTestData('02-Usuarios/SetUsers-FF').getValue(5,1)

			GlobalVariable.Cliente1DNI = findTestData('02-Usuarios/SetUsers-FF').getValue(3,2)
			GlobalVariable.Cliente1Clave = findTestData('02-Usuarios/SetUsers-FF').getValue(5,2)
			GlobalVariable.Cliente1Usuario = findTestData('02-Usuarios/SetUsers-FF').getValue(4,2)
			GlobalVariable.Cliente1ByPass = findTestData('02-Usuarios/SetUsers-FF').getValue(5,2)

			GlobalVariable.Cliente2DNI = findTestData('02-Usuarios/SetUsers-FF').getValue(3,3)
			GlobalVariable.Cliente2Clave = findTestData('02-Usuarios/SetUsers-FF').getValue(5,3)
			GlobalVariable.Cliente2Usuario = findTestData('02-Usuarios/SetUsers-FF').getValue(4,3)
			GlobalVariable.Cliente2ByPass = findTestData('02-Usuarios/SetUsers-FF').getValue(5,3)

			GlobalVariable.Cliente3DNI = findTestData('02-Usuarios/SetUsers-FF').getValue(3,4)
			GlobalVariable.Cliente3Clave = findTestData('02-Usuarios/SetUsers-FF').getValue(5,4)
			GlobalVariable.Cliente3Usuario = findTestData('02-Usuarios/SetUsers-FF').getValue(4,4)
			GlobalVariable.Cliente3ByPass = findTestData('02-Usuarios/SetUsers-FF').getValue(5,4)

			GlobalVariable.Cliente4DNI = findTestData('02-Usuarios/SetUsers-FF').getValue(3,5)
			GlobalVariable.Cliente4Clave = findTestData('02-Usuarios/SetUsers-FF').getValue(5,5)
			GlobalVariable.Cliente4Usuario = findTestData('02-Usuarios/SetUsers-FF').getValue(4,5)
			GlobalVariable.Cliente4ByPass = findTestData('02-Usuarios/SetUsers-FF').getValue(5,5)
		}
	}
	//-----------------------------------------------------------------------------------------------

	/*----------------------------------------------------------------------------------------------*
	 *Usuario por tipo de Cuenta                                                    				*
	 *																								*
	 *Requisito:                                 													*
	 *																								*
	 *vCuenta: Pesos o Dolares										 								*	
	 *----------------------------------------------------------------------------------------------*/
	/*@Keyword
	def Cuenta(def vCuenta) {
		if(vCuenta == 'Pesos'){
			GlobalVariable.Cliente1DNI = findTestData('02-Usuarios/SetUsers-FF').getValue(3,2)
			GlobalVariable.Cliente1Clave = findTestData('02-Usuarios/SetUsers-FF').getValue(5,2)
			GlobalVariable.Cliente1Usuario = findTestData('02-Usuarios/SetUsers-FF').getValue(4,2)
			GlobalVariable.Cliente1ByPass = findTestData('02-Usuarios/SetUsers-FF').getValue(5,2)

			GlobalVariable.Cliente2DNI = findTestData('02-Usuarios/SetUsers-FF').getValue(3,3)
			GlobalVariable.Cliente2Clave = findTestData('02-Usuarios/SetUsers-FF').getValue(5,3)
			GlobalVariable.Cliente2Usuario = findTestData('02-Usuarios/SetUsers-FF').getValue(4,3)
			GlobalVariable.Cliente2ByPass = findTestData('02-Usuarios/SetUsers-FF').getValue(5,3)
		}
		if(vCuenta == 'Dolares') {
			GlobalVariable.Cliente3DNI = findTestData('02-Usuarios/SetUsers-FF').getValue(3,4)
			GlobalVariable.Cliente3Clave = findTestData('02-Usuarios/SetUsers-FF').getValue(5,4)
			GlobalVariable.Cliente3Usuario = findTestData('02-Usuarios/SetUsers-FF').getValue(4,4)
			GlobalVariable.Cliente3ByPass = findTestData('02-Usuarios/SetUsers-FF').getValue(5,4)

			GlobalVariable.Cliente4DNI = findTestData('02-Usuarios/SetUsers-FF').getValue(3,5)
			GlobalVariable.Cliente4Clave = findTestData('02-Usuarios/SetUsers-FF').getValue(5,5)
			GlobalVariable.Cliente4Usuario = findTestData('02-Usuarios/SetUsers-FF').getValue(4,5)
			GlobalVariable.Cliente4ByPass = findTestData('02-Usuarios/SetUsers-FF').getValue(5,5)
		}
	}*/
	
	/*----------------------------------------------------------------------------------------------*
	 *LOGIN																							*
	 *																								*
	 *Requisito: haber seleccionado servidor														*	
	 *																								*
	 *vDNI: Nro de DNI										 										*		
	 *vClave: Clave de acceso																		*																	
	 *vUsuario: usuario de acceso																	*
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def Login(def vDNI, vClave, vUsuario) {

		WebUI.openBrowser(GlobalVariable.ServerUsado)

		WebUI.maximizeWindow()

		//Cierra el Banner del inicio
		WebUI.verifyElementVisible(findTestObject('Object Repository/01-Login/btnLgnCerrarBanner'))
		WebUI.click(findTestObject('Object Repository/01-Login/btnLgnCerrarBanner'))

		WebUI.setText(findTestObject('Object Repository/01-Login/txtLgnDNI'), vDNI)
		WebUI.setText(findTestObject('Object Repository/01-Login/txtLgnClave'), vClave)
		WebUI.setText(findTestObject('Object Repository/01-Login/txtLgnUsuario'), vUsuario)

		WebUI.click(findTestObject('Object Repository/01-Login/btnLgnIngresar'))
	}
	//-----------------------------------------------------------------------------------------------

	/*----------------------------------------------------------------------------------------------*
	 *LOGOUT CON CONTROL DE ERRORES																	*
	 *																								*
	 *vImgPath: Ubicacion de la imagen capturada con el error.										*
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def fFailStatus(def vImgPath){
		WebUI.takeScreenshot(vImgPath)
		WebUI.delay(3)
		WebUI.closeBrowser()
	}

	@Keyword
	def fPassStatus(){
		WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbSalir'))
		WebUI.click(findTestObject('Object Repository/02-Dashboard/btnSalirMsgConfirmacionAceptar'))
		WebUI.closeBrowser()
	}

	@Keyword
	def fLoginPassStatus(){
		WebUI.closeBrowser()
	}
	//-----------------------------------------------------------------------------------------------


}

