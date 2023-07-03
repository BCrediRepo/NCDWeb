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
import com.thoughtworks.selenium.webdriven.commands.GetText

import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable

import javax.swing.JOptionPane

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement


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
		}


		if(vServerName == 'RMobile'){

			GlobalVariable.ServerUsado = 'https://'+ GlobalVariable.URLRMob+'/'+ GlobalVariable.TestServer
			GlobalVariable.LogServerUsado = 'RMobile'
		}


		if(vServerName == 'Internet'){

			GlobalVariable.ServerUsado = 'https://'+ GlobalVariable.URLInternet+'/'+ GlobalVariable.TestServer
			GlobalVariable.LogServerUsado = 'Internet'
		}
	}
	//-----------------------------------------------------------------------------------------------



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
		WebUI.click(findTestObject('Object Repository/01-Login/btnLgnCerrarBanner'))

		WebUI.setText(findTestObject('Object Repository/01-Login/txtLgnDNI'), vDNI, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.setText(findTestObject('Object Repository/01-Login/txtLgnClave'), vClave, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.setText(findTestObject('Object Repository/01-Login/txtLgnUsuario'), vUsuario, FailureHandling.CONTINUE_ON_FAILURE)

		WebUI.click(findTestObject('Object Repository/01-Login/btnLgnIngresar'))
	}

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
		WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbIconoSalir'))
		WebUI.delay(3)
		WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDsbAceptarSalir'))
		WebUI.closeBrowser()
	}

	@Keyword
	def fLoginPassStatus(){
		WebUI.closeBrowser()
	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Registro de Operaciones	                                                        *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionOrigenDetalle(def vTiempo){

		String vNumeroOperacion
		String vOrigenNumeroOperacion
		String vTipoOperacion
		String vOrigenTipoOperacion
		String vImporte
		String vOrigenImporte
		String vDetalleOperacion
		String vDetalleNumeroOperacion
		String vDetalleTipo
		String vDetalleTipoOperacion
		String vDetImporte
		String vDetalleImporte

		//En la pagina del Listado de Operaciones
		WebUI.waitForPageLoad(vTiempo)

		vOrigenNumeroOperacion = WebUI.getText(findTestObject('Object Repository/07-Operaciones/txtNumeroOperacion'), vNumeroOperacion)
		//JOptionPane.showMessageDialog(null, vOrigenNumeroOperacion)

		vOrigenTipoOperacion = WebUI.getText(findTestObject('Object Repository/07-Operaciones/txtOprListaTipoOperacion'), vTipoOperacion)
		//JOptionPane.showMessageDialog(null, vOrigenTipoOperacion)

		vOrigenImporte = WebUI.getText(findTestObject('Object Repository/07-Operaciones/txtOprListaMontoOperacion'), vImporte)
		//JOptionPane.showMessageDialog(null, vOrigenImporte)

		WebUI.click(findTestObject('Object Repository/07-Operaciones/mnuOprVerDetalleOperacion'))
		WebUI.click(findTestObject('Object Repository/07-Operaciones/txtOprVerDetalleOperacion'))


		//En la pagina del Detalle de la Operacion
		WebUI.waitForPageLoad(vTiempo)

		vDetalleNumeroOperacion = WebUI.getText(findTestObject('Object Repository/07-Operaciones/txtOprDetalleNumOperacn'), vDetalleOperacion)
		//JOptionPane.showMessageDialog(null, vDetalleNumeroOperacion)

		vDetalleTipoOperacion = WebUI.getText(findTestObject('Object Repository/07-Operaciones/txtOprDetalleTipoTransferenciaOperacn'), vDetalleTipo)
		//JOptionPane.showMessageDialog(null, vDetalleTipoOperacion)

		vDetalleImporte = WebUI.getText(findTestObject('Object Repository/07-Operaciones/txtOprDetalleMontoOperacn'), vDetImporte)
		//JOptionPane.showMessageDialog(null, vDetalleImporte)

		//Comparacion
		WebUI.verifyEqual(vOrigenNumeroOperacion, vDetalleNumeroOperacion, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vOrigenTipoOperacion, vDetalleTipoOperacion, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vOrigenImporte, vDetalleImporte, FailureHandling.CONTINUE_ON_FAILURE)

		println(vOrigenNumeroOperacion + "-" + vDetalleNumeroOperacion)
		println(vOrigenTipoOperacion + "-" + vDetalleTipoOperacion)
		println(vOrigenImporte + "-" + vDetalleImporte)

	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Detalle Pago de Servicio Sin Base	                                                *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionListaDetallePago(def vTiempo){

		String vServicioLista
		String vServicioL
		String vIdentificadorLista
		String vIdentificadorL
		String vConceptoLista
		String vConceptoL
		String vServicioDetalle
		String vServicioD
		String vIdentificadorDetalle
		String vIdentificadorD
		String vConceptoDetalle
		String vConceptoD

		//En la pagina del Listado de Servicios a pagar
		WebUI.scrollToElement(findTestObject('Object Repository/08-Pagos y Recargas/lblPYRServicioLista'), 10)
		WebUI.waitForPageLoad(vTiempo)

		vServicioLista = WebUI.getText(findTestObject('Object Repository/08-Pagos y Recargas/lblPYRServicioLista'), vServicioL)
		//JOptionPane.showMessageDialog(null, vServicioLista)
		vIdentificadorLista = WebUI.getText(findTestObject('Object Repository/08-Pagos y Recargas/lblPYRIdentificadorLista'), vIdentificadorL)
		//JOptionPane.showMessageDialog(null, vIdentificadorLista)
		vConceptoLista = WebUI.getText(findTestObject('Object Repository/08-Pagos y Recargas/lblPYRConceptoLista'), vConceptoL)
		//JOptionPane.showMessageDialog(null, vConceptoLista)

		WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/btnPYRPagarLista'))

		//En la solapa del detalle del Servicio a pagar
		WebUI.waitForPageLoad(vTiempo)

		vServicioDetalle = WebUI.getText(findTestObject('Object Repository/08-Pagos y Recargas/lblPYRServicioDetalle'), vServicioD)
		//JOptionPane.showMessageDialog(null, vServicioDetalle)
		vIdentificadorDetalle = WebUI.getText(findTestObject('Object Repository/08-Pagos y Recargas/lblPYRIdentificadorDetalle'), vIdentificadorD)
		//JOptionPane.showMessageDialog(null, vIdentificadorDetalle)
		vConceptoDetalle = WebUI.getText(findTestObject('Object Repository/08-Pagos y Recargas/lblPYRConceptoDetalle'), vConceptoD)
		//JOptionPane.showMessageDialog(null, vConceptoDetalle)

		//Comparacion
		WebUI.verifyEqual(vServicioLista, vServicioDetalle, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vIdentificadorLista, vIdentificadorDetalle, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vConceptoLista, vConceptoDetalle, FailureHandling.CONTINUE_ON_FAILURE)
	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Cuenta Transferencias Cuentas Propias Pesos                                        *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionListadoTrxPropiasPesos(def vTiempo){

		String vMonedaCta
		String vMoneda
		String vMonedaTrx
		String vMonedaT
		String vNombreCta
		String vNombre
		String vNumeroCta
		String vNumero
		String vDenominacionCta
		String vDenominacion
		String vNombreTrx
		String vNombreT
		String vNumeroTrx
		String vNumeroT
		String vDenominacionTrx
		String vDenominacionT

		//En la pagina del Listado de Mis Cuentas
		WebUI.waitForPageLoad(vTiempo)

		vNombreCta = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrxNombreCta'), vNombre)
		//JOptionPane.showMessageDialog(null, vNombreCta)
		vNumeroCta = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrxNumeroCta'), vNumero)
		//JOptionPane.showMessageDialog(null, vNumeroCta)
		vDenominacionCta = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrxDenominacionCta'), vDenominacion)
		//JOptionPane.showMessageDialog(null, vDenominacionCta)
		vMonedaCta = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/icoTrxMonedaPesos'), vMoneda)
		//GlobalVariable.vMonedaCta = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/icoTrfListaCuentaDolares'))
		//JOptionPane.showMessageDialog(null, vMonedaCta)

		//Cliquea sobre la cuenta
		WebUI.click(findTestObject('Object Repository/04-Transferencias/lnkTrxCuentaPropiaPesos'))
		WebUI.delay(5)

		//En el formulario de Transferencia
		WebUI.waitForPageLoad(vTiempo)

		vNombreTrx = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrxNombre'), vNombreT)
		//JOptionPane.showMessageDialog(null, vNombreTrx)
		vNumeroTrx = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrxNumero'), vNumeroT)
		//JOptionPane.showMessageDialog(null, vNumeroTrx)
		vDenominacionTrx = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrxDenominacion'), vDenominacionT)
		//JOptionPane.showMessageDialog(null, vDenominacionTrx)
		vMonedaTrx = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxMoneda'), vMonedaT)
		//GlobalVariable.vMonedaTrx = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/icoTrxMonedaFormDolares'))
		//JOptionPane.showMessageDialog(null, vMonedaTrx)

		//Comparacion entre datos del Listado y Datos del Formulario de Transferencia
		//WebUI.verifyEqual(vNombreCta, vNombreTrx, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vNumeroCta, vNumeroTrx, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vDenominacionCta, vDenominacionTrx, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vMonedaCta, vMonedaTrx, FailureHandling.CONTINUE_ON_FAILURE)

		//-------------------------------------------------------------------------------------------

		if (vMonedaCta == '$' && vMonedaTrx == '$'){
			println("La cuenta seleccionada corresponde a una moneda en pesos.")

		}else{
			KeywordUtil.markFailedAndStop("La cuenta seleccionada NO corresponde a una moneda en pesos.")
		}

	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Cuenta Transferencias Cuentas Propias Dolares                                      *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionListadoTrxPropiasDolares(def vTiempo){

		String vMonedaCta
		String vMoneda
		String vMonedaTrx
		String vMonedaT
		String vNombreCta
		String vNombre
		String vNumeroCta
		String vNumero
		String vDenominacionCta
		String vDenominacion
		String vNombreTrx
		String vNombreT
		String vNumeroTrx
		String vNumeroT
		String vDenominacionTrx
		String vDenominacionT

		//En la pagina del Listado de Mis Cuentas
		WebUI.waitForPageLoad(vTiempo)

		vNombreCta = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrfListaNombreCajaAhorroDolres'), vNombre)
		//JOptionPane.showMessageDialog(null, vNombreCta)
		vNumeroCta = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxListaNumeroCuentaDolar'), vNumero)
		//JOptionPane.showMessageDialog(null, vNumeroCta)
		vDenominacionCta = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrfListaDenomDolar'), vDenominacion)
		//JOptionPane.showMessageDialog(null, vDenominacionCta)
		vMonedaCta = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/icoTrxMonedaDolares'), vMoneda)
		//GlobalVariable.vMonedaCta = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/icoTrfListaCuentaDolares'))
		//JOptionPane.showMessageDialog(null, vMonedaCta)

		//Cliquea sobre la cuenta
		WebUI.scrollToElement(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrfListaNombreCajaAhorroDolres'), 10)
		WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrfListaNombreCajaAhorroDolres'))
		WebUI.delay(5)

		//En el formulario de Transferencia
		WebUI.waitForPageLoad(vTiempo)

		vNombreTrx = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrfFormNombreCajaAhorroDolar'), vNombreT)
		//JOptionPane.showMessageDialog(null, vNombreTrx)
		vNumeroTrx = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrfFormNumeroCuentaDolar'), vNumeroT)
		//JOptionPane.showMessageDialog(null, vNumeroTrx)
		vDenominacionTrx = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrfFormDenomCuentaDolar'), vDenominacionT)
		//JOptionPane.showMessageDialog(null, vDenominacionTrx)
		vMonedaTrx = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/icoTrfFormDolar'), vMonedaT)
		//GlobalVariable.vMonedaTrx = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/icoTrxMonedaFormDolares'))
		//JOptionPane.showMessageDialog(null, vMonedaTrx)

		//Comparacion entre datos del Listado y Datos del Formulario de Transferencia
		WebUI.verifyEqual(vNombreCta, vNombreTrx, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vNumeroCta, vNumeroTrx, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vDenominacionCta, vDenominacionTrx, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vMonedaCta, vMonedaTrx, FailureHandling.CONTINUE_ON_FAILURE)

		if (vMonedaCta == 'U$S' && vMonedaTrx == 'U$S'){
			println("La cuenta seleccionada corresponde a una moneda en dolares.")

		}else{
			KeywordUtil.markFailedAndStop("La cuenta seleccionada NO corresponde a una moneda en dolares.")
		}

	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Confirmacion Transferencias Cuentas Propias Pesos                                  *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionMontoTrxPropias(def vTiempo){

		String vCtaOrigenTrx
		String vCtaOrigenT
		String vMontoTrx
		String vMontoT
		String vCtaDestinoTrx
		String vCtaDestinoT
		String vMontoConfirm
		String vMontoC
		String vCtaOrigenConfirm
		String vCtaOrigenC
		String vCtaDestinoConfirm
		String vCtaDestinoC

		//En el formulario de Transferencia
		WebUI.waitForPageLoad(vTiempo)

		vCtaOrigenTrx = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrxCtaOrigen'), vCtaOrigenT)
		//JOptionPane.showMessageDialog(null, vCtaOrigenTrx)
		vMontoTrx = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxMonto'), vMontoT)
		//JOptionPane.showMessageDialog(null, vMontoTrx)
		WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrxNumero'), vCtaDestinoT)
		//JOptionPane.showMessageDialog(null, vCtaDestinoTrx)

		WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/btnTrxContinuarFormulario'))

		//En la Pantalla de Confirmacion
		WebUI.waitForPageLoad(vTiempo)

		vMontoConfirm = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxMontoConfirm'), vMontoC)
		//JOptionPane.showMessageDialog(null, vMontoConfirm)
		vCtaOrigenConfirm = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrxCtaOrigenConfirm'), vCtaOrigenC)
		//JOptionPane.showMessageDialog(null, vCtaOrigenConfirm)
		vCtaDestinoConfirm = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrxCtaDestinoConfirm'), vCtaDestinoC)
		//JOptionPane.showMessageDialog(null, vCtaDestinoConfirm)


		//Comparacion entre el monto del formulario de Transferencia y la pantalla de Confirmacion
		WebUI.verifyEqual(vMontoTrx, vMontoConfirm, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vCtaOrigenTrx, vCtaOrigenConfirm, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vCtaDestinoTrx, vCtaDestinoConfirm, FailureHandling.CONTINUE_ON_FAILURE)

	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Comprobante Transferencias Cuentas Propias Pesos                                   *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionNumeroOperacionTrxPropias(def vTiempo){

		String vMontoExito
		String vMontoE
		String vNumOperacionExito
		String vNumeroE
		String vMontoComprobante
		String vMontoC
		String vNumOperacionComprobante
		String vNumeroC

		//En la pantalla de Confirmacion
		WebUI.waitForPageLoad(vTiempo)

		vMontoExito = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxMontoExito'), vMontoE)
		//JOptionPane.showMessageDialog(null, vMontoExito)
		vNumOperacionExito = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxNumOperacionExito'), vNumeroE)
		//JOptionPane.showMessageDialog(null, vNumOperacionExito)

		WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/btnTrxVerComprobante'))

		//En el Comprobante
		WebUI.waitForPageLoad(vTiempo)

		vMontoComprobante = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxMontoComprobante'), vMontoC)
		//JOptionPane.showMessageDialog(null, vMontoComprobante)
		vNumOperacionComprobante = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxNumOperacionComprobante'), vNumeroC)
		//JOptionPane.showMessageDialog(null, vNumOperacionComprobante)

		//Comparacion entre el monto del formulario de Transferencia y la pantalla de confirmacion
		WebUI.verifyEqual(vMontoExito, vMontoComprobante, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vNumOperacionExito, vNumOperacionComprobante, FailureHandling.CONTINUE_ON_FAILURE)

	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Comprobante Transferencias Cuentas Propias Dolares                                 *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionNroOperacionTrxPropiasDolares(def vTiempo){

		String vMontoExito
		String vMontoE
		String vNumOperacionExito
		String vNumeroE
		String vMontoComprobante
		String vMontoC
		String vNumOperacionComprobante
		String vNumeroC

		//En la pantalla de Confirmacion
		WebUI.waitForPageLoad(vTiempo)

		vMontoExito = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxMontoExitoDolar'), vMontoE)
		//JOptionPane.showMessageDialog(null, vMontoExito)
		vNumOperacionExito = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxNroOperExitoDolar'), vNumeroE)
		//JOptionPane.showMessageDialog(null, vNumOperacionExito)

		WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/btnTrxVerComprobante'))

		//En el Comprobante
		WebUI.waitForPageLoad(vTiempo)

		vMontoComprobante = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxMontoComprobDolar'), vMontoC)
		//JOptionPane.showMessageDialog(null, vMontoComprobante)
		vNumOperacionComprobante = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtNroOperComprobDolar'), vNumeroC)
		//JOptionPane.showMessageDialog(null, vNumOperacionComprobante)

		//Comparacion entre el monto del formulario de Transferencia y la pantalla de confirmacion
		WebUI.verifyEqual(vMontoExito, vMontoComprobante, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vNumOperacionExito, vNumOperacionComprobante, FailureHandling.CONTINUE_ON_FAILURE)

	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Comprobante Transferencias a Terceros Pesos                                        *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionNroOperacionTercerosPesos(def vTiempo){

		String vMontoExito
		String vMontoE
		String vNumOperacionExito
		String vNumeroE
		String vMontoComprobante
		String vMontoC
		String vNumOperacionComprobante
		String vNumeroC

		//En la pantalla de Confirmacion
		WebUI.waitForPageLoad(vTiempo)

		vMontoExito = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxMontoExitoTerceros'), vMontoE)
		//JOptionPane.showMessageDialog(null, vMontoExito)
		vNumOperacionExito = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxNroOperExitoTerceros'), vNumeroE)
		//JOptionPane.showMessageDialog(null, vNumOperacionExito)

		WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/btnTrxVerComprobante'))

		//En el Comprobante
		WebUI.waitForPageLoad(vTiempo)

		vMontoComprobante = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxMontoComprobTerceros'), vMontoC)
		//JOptionPane.showMessageDialog(null, vMontoComprobante)
		vNumOperacionComprobante = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxNroOperComprobTerceros'), vNumeroC)
		//JOptionPane.showMessageDialog(null, vNumOperacionComprobante)

		//Comparacion entre el monto del formulario de Transferencia y la pantalla de confirmacion
		WebUI.verifyEqual(vMontoExito, vMontoComprobante, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vNumOperacionExito, vNumOperacionComprobante, FailureHandling.CONTINUE_ON_FAILURE)

	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Comprobante Transferencias Judiciales Credicoop Pesos                              *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionNroOperacionTrxJudicialesCredicoop(def vTiempo){

		String vMontoExito
		String vMontoE
		String vNumOperacionExito
		String vNumeroE
		String vMontoComprobante
		String vMontoC
		String vNumOperacionComprobante
		String vNumeroC

		//En la pantalla de Confirmacion
		WebUI.waitForPageLoad(vTiempo)

		vMontoExito = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxJudicialMontoExito'), vMontoE)
		//JOptionPane.showMessageDialog(null, vMontoExito)
		vNumOperacionExito = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxJudicialNroOperExito'), vNumeroE)
		//JOptionPane.showMessageDialog(null, vNumOperacionExito)

		WebUI.click(findTestObject('Object Repository/04-Transferencias/btnTrxJudicialVerComprobanteExito'))

		//En el Comprobante
		WebUI.waitForPageLoad(vTiempo)

		vMontoComprobante = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxJudicialMontoComprobante'), vMontoC)
		//JOptionPane.showMessageDialog(null, vMontoComprobante)
		vNumOperacionComprobante = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxJudicialNroOperComprobante'), vNumeroC)
		//JOptionPane.showMessageDialog(null, vNumOperacionComprobante)

		//Comparacion entre el monto del formulario de Transferencia y la pantalla de confirmacion
		WebUI.verifyEqual(vMontoExito, vMontoComprobante, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vNumOperacionExito, vNumOperacionComprobante, FailureHandling.CONTINUE_ON_FAILURE)

	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Comprobante Transferencias Terceros Credicoop Dolar                                *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionOperacionTrxTercerosCredicoopDolar(def vTiempo){

		String vMontoExito
		String vMontoE
		String vNumOperacionExito
		String vNumeroE
		String vMontoComprobante
		String vMontoC
		String vNumOperacionComprobante
		String vNumeroC

		//En la pantalla de Confirmacion
		WebUI.waitForPageLoad(vTiempo)

		vMontoExito = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxMontoExitoTercerosDolar'), vMontoE)
		//JOptionPane.showMessageDialog(null, vMontoExito)
		vNumOperacionExito = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxNroOperacionExitoTercerosDolar'), vNumeroE)
		//JOptionPane.showMessageDialog(null, vNumOperacionExito)

		WebUI.click(findTestObject('Object Repository/04-Transferencias/btnTrxJudicialVerComprobanteExito'))

		//En el Comprobante
		WebUI.waitForPageLoad(vTiempo)

		vMontoComprobante = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxMontoCompTercerosDolar'), vMontoC)
		//JOptionPane.showMessageDialog(null, vMontoComprobante)
		vNumOperacionComprobante = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxNroOperacionCompTercerosDolar'), vNumeroC)
		//JOptionPane.showMessageDialog(null, vNumOperacionComprobante)

		//Comparacion entre el monto del formulario de Transferencia y la pantalla de confirmacion
		WebUI.verifyEqual(vMontoExito, vMontoComprobante, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vNumOperacionExito, vNumOperacionComprobante, FailureHandling.CONTINUE_ON_FAILURE)

	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Comprobante Transferencias Judiciales Otros Bancos Pesos                           *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionNroOperacionTrxJudicialesOtrosBancos(def vTiempo){

		String vMontoExito
		String vMontoE
		String vNumOperacionExito
		String vNumeroE
		String vMontoComprobante
		String vMontoC
		String vNumOperacionComprobante
		String vNumeroC

		//En la pantalla de Confirmacion
		WebUI.waitForPageLoad(vTiempo)

		vMontoExito = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxMontoOtrosBncosJudicialExito'), vMontoE)
		//JOptionPane.showMessageDialog(null, vMontoExito)
		vNumOperacionExito = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxNroOperOtrosBncosJudicialExito'), vNumeroE)
		//JOptionPane.showMessageDialog(null, vNumOperacionExito)

		WebUI.click(findTestObject('Object Repository/04-Transferencias/btnTrxJudicialVerComprobanteExito'))

		//En el Comprobante
		WebUI.waitForPageLoad(vTiempo)

		vMontoComprobante = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxMontoOtrosBncosComprbnte'), vMontoC)
		//JOptionPane.showMessageDialog(null, vMontoComprobante)
		vNumOperacionComprobante = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxNroOperOtrosBncosComprbnte'), vNumeroC)
		//JOptionPane.showMessageDialog(null, vNumOperacionComprobante)

		//Comparacion entre el monto del formulario de Transferencia y la pantalla de confirmacion
		WebUI.verifyEqual(vMontoExito, vMontoComprobante, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vNumOperacionExito, vNumOperacionComprobante, FailureHandling.CONTINUE_ON_FAILURE)

	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Datos Transferencias Posdatadas Pesos                                              *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionDatosExitoPosdatadaPesos(def vTiempo){

		String vMontoExito
		String vMontoE
		String vBeneficiarioExito
		String vBeneficiarioE
		String vMontoSolapa
		String vMontoS
		String vBeneficiarioSolapa
		String vBeneficiarioS

		//En la pantalla de Confirmacion
		WebUI.waitForPageLoad(vTiempo)

		vMontoExito = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxProgramadasExitoMonto'), vMontoE)
		//JOptionPane.showMessageDialog(null, vMontoExito)
		vBeneficiarioExito = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxProgramadasExitoBeneficiario'), vBeneficiarioE)
		//JOptionPane.showMessageDialog(null, vBeneficiarioExito)

		WebUI.click(findTestObject('Object Repository/04-Transferencias/btnTrxProgramadasExitoIrTransferencias'))
		WebUI.click(findTestObject('Object Repository/04-Transferencias/lnkTrxSolapaProgramadas'))

		//En la solapa de Programadas
		WebUI.waitForPageLoad(vTiempo)

		vMontoSolapa = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxProgramadasSolapaMonto'), vMontoS)
		//JOptionPane.showMessageDialog(null, vMontoSolapa)
		vBeneficiarioSolapa = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxProgramadasSolapaBeneficiario'), vBeneficiarioS)
		//JOptionPane.showMessageDialog(null, vBeneficiarioSolapa)

		//Comparacion entre la pantalla de confirmacion y el registro en la solapa Programadas
		WebUI.verifyEqual(vMontoExito, vMontoSolapa, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vBeneficiarioExito, vBeneficiarioSolapa, FailureHandling.CONTINUE_ON_FAILURE)

	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Datos Transferencias Posdatadas Dolares                                            *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionDatosExitoPosdatadaDolares(def vTiempo){

		String vMontoExito
		String vMontoE
		String vBeneficiarioExito
		String vBeneficiarioE
		String vMontoSolapa
		String vMontoS
		String vBeneficiarioSolapa
		String vBeneficiarioS

		//En la pantalla de Confirmacion
		WebUI.waitForPageLoad(vTiempo)

		vMontoExito = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxProgramadasExitoMontoDolar'), vMontoE)
		//JOptionPane.showMessageDialog(null, vMontoExito)
		vBeneficiarioExito = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxProgramadasExitoBeneficiarioDolar'), vBeneficiarioE)
		//JOptionPane.showMessageDialog(null, vBeneficiarioExito)

		WebUI.click(findTestObject('Object Repository/04-Transferencias/btnTrxProgramadasExitoIrTransferencias'))
		WebUI.click(findTestObject('Object Repository/04-Transferencias/lnkTrxSolapaProgramadas'))

		//En la solapa de Programadas
		WebUI.waitForPageLoad(vTiempo)

		vMontoSolapa = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxProgramadasSolapaMontoDolar'), vMontoS)
		//JOptionPane.showMessageDialog(null, vMontoSolapa)
		vBeneficiarioSolapa = WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxProgramadasSolapaBeneficiarioDolar'), vBeneficiarioS)
		//JOptionPane.showMessageDialog(null, vBeneficiarioSolapa)

		//Comparacion entre la pantalla de confirmacion y el registro en la solapa Programadas
		WebUI.verifyEqual(vMontoExito, vMontoSolapa, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vBeneficiarioExito, vBeneficiarioSolapa, FailureHandling.CONTINUE_ON_FAILURE)

	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Detalle Plazo Fijo                                                                 *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionListaDetallePlazoFijo(def vTiempo){

		String vNombrePFLista
		String vNombrePFL
		String vTipoPFLista
		String vTipoPFL
		String vFechaPFLista
		String vFechaPFL
		String vMontoPFLista
		String vMontoPFL
		String vNombrePFDetalle
		String vNombrePFD
		String vTipoPFDetalle
		String vTipoPFD
		String vFechaPFDetalle
		String vFechaPFD
		String vMontoPFDetalle
		String vMontoPFD

		//En el listado de Plazo Fijo
		WebUI.waitForPageLoad(vTiempo)

		vNombrePFLista = WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvNombrePlazoFijoListado'), vNombrePFL)
		//JOptionPane.showMessageDialog(null, vNombrePFLista)
		vTipoPFLista = WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvTipoPlazoFijoListado'), vTipoPFL)
		//JOptionPane.showMessageDialog(null, vTipoPFLista)
		vFechaPFLista = WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvFechaVencimientoPFListado'), vFechaPFL)
		//JOptionPane.showMessageDialog(null, vFechaPFLista)
		vMontoPFLista = WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvMontoPlazoFijoListado'), vMontoPFL)
		//JOptionPane.showMessageDialog(null, vMontoPFLista)

		WebUI.click(findTestObject('Object Repository/06-Inversiones/txtInvNombrePlazoFijoListado'))

		//En el Detalle del Plazo Fijo
		WebUI.waitForPageLoad(vTiempo)

		vNombrePFDetalle = WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvNombrePlazoFijoDetalle'), vNombrePFD)
		//JOptionPane.showMessageDialog(null, vNombrePFDetalle)
		vTipoPFDetalle = WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvTipoPFDetalle'), vTipoPFD)
		//JOptionPane.showMessageDialog(null, vTipoPFDetalle)
		vFechaPFDetalle = WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvFechaVencimientoPFDetalle'), vFechaPFD)
		//JOptionPane.showMessageDialog(null, vFechaPFDetalle)
		vMontoPFDetalle = WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvMontoPFDetalle'), vMontoPFD)
		//JOptionPane.showMessageDialog(null, vMontoPFDetalle)

		//Comparacion entre el Listado y el Detalle de Plazo Fijo
		WebUI.verifyEqual(vNombrePFLista, vNombrePFDetalle, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vTipoPFLista, vTipoPFDetalle, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vFechaPFLista, vFechaPFDetalle, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vMontoPFLista, vMontoPFDetalle, FailureHandling.CONTINUE_ON_FAILURE)

	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Simulación Crear Plazo Fijo                                                        *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionSimularCrearPlazoFijo(def vTiempo){

		String vMontoPFSimular
		String vMontoPFS
		String vPlazoPFSimular
		String vPlazoPFS
		String vFechaPFSimular
		String vFechaPFS
		String vMontoPFCrear
		String vMontoPFC
		String vPlazoPFCrear
		String vPlazoPFC
		String vFechaPFCrear
		String vFechaPFC

		//Datos igresados en la simulacion
		WebUI.waitForPageLoad(vTiempo)

		vMontoPFSimular = WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvPFMontoSumulado'), vMontoPFS)
		//JOptionPane.showMessageDialog(null, vMontoPFSimular)
		vPlazoPFSimular = WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvPFDiasSimulado'), vPlazoPFS)
		//JOptionPane.showMessageDialog(null, vPlazoPFSimular)
		vFechaPFSimular = WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvPFFechaVencimSimulacion'), vFechaPFS)
		//JOptionPane.showMessageDialog(null, vFechaPFSimular)

		WebUI.click(findTestObject('Object Repository/06-Inversiones/btnInvPFCrearSimulacion'))

		//Datos informados en la pantalla de confirmacion
		WebUI.waitForPageLoad(vTiempo)

		vMontoPFCrear = WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvPFMontoCrear'), vMontoPFC)
		//JOptionPane.showMessageDialog(null, vMontoPFCrear)
		vPlazoPFCrear = WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvPFDiasCrear'), vPlazoPFC)
		//JOptionPane.showMessageDialog(null, vPlazoPFCrear)
		vFechaPFCrear = WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvPFFechaVencimCrear'), vFechaPFC)
		//JOptionPane.showMessageDialog(null, vFechaPFCrear)

		//Comparacion entre formulario y simulacion
		WebUI.verifyEqual(vMontoPFSimular, vMontoPFCrear, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vPlazoPFSimular, vPlazoPFCrear, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vFechaPFSimular, vFechaPFCrear, FailureHandling.CONTINUE_ON_FAILURE)

	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Comprobante Crear Plazo Fijo                                                       *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def ValidacionComprobanteCrearPlazoFijo(def vTiempo){

		String vMontoPFExito
		String vMontoPFE
		String vNroOperPFExito
		String vNroOperPFE
		String vMontoPFComprobante
		String vMontoPFC
		String vNroOperPFComprobante
		String vNroOperPFC

		//Datos igresados en la simulacion
		WebUI.waitForPageLoad(vTiempo)

		vMontoPFExito = WebUI.getText(findTestObject('Object Repository/10-Fecha COB/txtInvPFMontoExito'), vMontoPFE)
		//JOptionPane.showMessageDialog(null, vMontoPFExito)
		vNroOperPFExito = WebUI.getText(findTestObject('Object Repository/10-Fecha COB/txtInvPFNroOperExito'), vNroOperPFE)
		//JOptionPane.showMessageDialog(null, vNroOperPFExito)

		WebUI.click(findTestObject('Object Repository/10-Fecha COB/btnInvPFVerComprobanteExito'))

		//Datos informados en la pantalla de confirmacion
		WebUI.waitForPageLoad(vTiempo)

		vMontoPFComprobante = WebUI.getText(findTestObject('Object Repository/10-Fecha COB/txtInvPFMontoComprobante'), vMontoPFC)
		//JOptionPane.showMessageDialog(null, vMontoPFComprobante)
		vNroOperPFComprobante = WebUI.getText(findTestObject('Object Repository/10-Fecha COB/txtInvPFNroOperComprobante'), vNroOperPFC)
		//JOptionPane.showMessageDialog(null, vNroOperPFComprobante)

		//Comparacion entre formulario y simulacion
		WebUI.verifyEqual(vMontoPFExito, vMontoPFComprobante, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vNroOperPFExito, vNroOperPFComprobante, FailureHandling.CONTINUE_ON_FAILURE)
	}


	/*----------------------------------------------------------------------------------------------*
	 *Validación Tarjeta Visa Detalle                                                        	 	*
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionDetalleTarjetaVisa(def vTiempo){

		String vTituloVisa
		String vTituloV
		String vTituloVisaOvervw
		String vTituloVO
		String vNroVisaOvervw
		String vNumeroVO
		String vNroVisa
		String vNumeroV

		//Valida los datos de la tarjeta Visa desde el Overview
		vTituloVisaOvervw = WebUI.getText(findTestObject('Object Repository/09-Tarjetas/txtTrjVisaOverview'), vTituloVO)
		//JOptionPane.showMessageDialog(null, vTituloVisaOvervw)
		vNroVisaOvervw = WebUI.getText(findTestObject('Object Repository/09-Tarjetas/txtTrjNumeroVisaOverview'), vNumeroVO)
		//JOptionPane.showMessageDialog(null, vNroVisaOvervw)

		//Cliquea en la card e ingresa al módulo para ver los movimientos
		WebUI.click(findTestObject('Object Repository/09-Tarjetas/LblTrjTarjetaVisa'))

		//Valida los datos de la tarjeta Visa desde el detalle de la Tarjeta
		vTituloVisa = WebUI.getText(findTestObject('Object Repository/09-Tarjetas/lblTrjTarjetaVisaPlatinum'), vTituloV)
		//JOptionPane.showMessageDialog(null, vTituloVisa)
		vNroVisa = WebUI.getText(findTestObject('Object Repository/09-Tarjetas/txtTrjNumeroVisaOverview'), vNumeroV)
		//JOptionPane.showMessageDialog(null, vNroVisa)

		//Compara ambos datos
		WebUI.verifyEqual(vTituloVisaOvervw, vTituloVisa, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vNroVisaOvervw, vNroVisa, FailureHandling.CONTINUE_ON_FAILURE)
	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Cuentas Detalle                                                            	 	*
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionCuentaDetalle(def vTiempo){

		String vFechaMovim
		String vFechaM
		String vConceptoMovim
		String vConceptoM
		String vCuentaMovim
		String vCuentaM
		String vImporteMovim
		String vImporteM
		String vFechaDetalle
		String vFechaD
		String vConceptoDetalle
		String vConceptoD
		String vCuentaDetalle
		String vCuentaD
		String vImporteDetalle
		String vImporteD

		//Valida los datos de la cuenta desde Movimientos
		vFechaMovim = WebUI.getText(findTestObject('Object Repository/10-Fecha COB/txtCtaMovimPesosFecha'), vFechaM)
		//JOptionPane.showMessageDialog(null, vFechaMovim)
		vConceptoMovim = WebUI.getText(findTestObject('Object Repository/10-Fecha COB/txtCtaMovimPesosConcepto'), vConceptoM)
		//JOptionPane.showMessageDialog(null, vConceptoMovim)
		vCuentaMovim = WebUI.getText(findTestObject('Object Repository/10-Fecha COB/txtCtaMovimCuenta'), vCuentaM)
		//JOptionPane.showMessageDialog(null, vCuentaMovim)
		vImporteMovim = WebUI.getText(findTestObject('Object Repository/10-Fecha COB/txtCtaMovimPesosImporte'), vImporteM)
		//JOptionPane.showMessageDialog(null, vImporteMovim)

		//Cliquea sobre una cta para ver la solapa del detalle
		WebUI.click(findTestObject('Object Repository/10-Fecha COB/txtCtaMovimPesosFecha'))

		//Valida los datos de la cuenta desde el detalle
		vFechaDetalle = WebUI.getText(findTestObject('Object Repository/10-Fecha COB/txtCtaDetalleFecha'), vFechaD)
		//JOptionPane.showMessageDialog(null, vFechaDetalle)
		vConceptoDetalle = WebUI.getText(findTestObject('Object Repository/10-Fecha COB/txtCtaDetalleConcepto'), vConceptoD)
		//JOptionPane.showMessageDialog(null, vConceptoDetalle)
		vCuentaDetalle = WebUI.getText(findTestObject('Object Repository/10-Fecha COB/txtCtaDetalleCuenta'), vCuentaD)
		//JOptionPane.showMessageDialog(null, vCuentaDetalle)
		vImporteDetalle = WebUI.getText(findTestObject('Object Repository/10-Fecha COB/txtCtaDetalleImporte'), vImporteD)
		//JOptionPane.showMessageDialog(null, vImporteDetalle)

		//Compara ambos datos
		WebUI.verifyEqual(vFechaMovim, vFechaDetalle, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vConceptoMovim, vConceptoDetalle, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vCuentaMovim, vCuentaDetalle, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vImporteMovim, vImporteDetalle, FailureHandling.CONTINUE_ON_FAILURE)

	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Debin Recibidos Detalle Cuenta                                                     *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionDebinRecibidosDetalleCuenta(def vTiempo){

		String vMontoLista
		String vMontoL
		String vConceptoLista
		String vConceptoL
		String vEstadoLista
		String vEstadoL
		String vNumeroLista
		String vNumeroL
		String vMontoDetalle
		String vMontoD
		String vConceptoDetalle
		String vConceptoD
		String vEstadoDetalle
		String vEstadoD
		String vNumeroDetalle
		String vNumeroD

		//Valida los datos de la lista de debines Recibidos
		vMontoLista = WebUI.getText(findTestObject('Object Repository/05-Pagos Debin/txtDbnListaRecibidosMonto'), vMontoL)
		//JOptionPane.showMessageDialog(null, vMontoLista)
		vConceptoLista = WebUI.getText(findTestObject('Object Repository/05-Pagos Debin/txtDbnListaRecibidosConcepto'), vConceptoL)
		//JOptionPane.showMessageDialog(null, vConceptoLista)
		vEstadoLista = WebUI.getText(findTestObject('Object Repository/05-Pagos Debin/txtDbnListaRecibidosEstado'), vEstadoL)
		//JOptionPane.showMessageDialog(null, vEstadoLista)
		vNumeroLista = WebUI.getText(findTestObject('Object Repository/05-Pagos Debin/txtDbnListaRecibidosNumero'), vNumeroL)
		//JOptionPane.showMessageDialog(null, vNumeroLista)

		//Cliquea sobre Registro de la lista para ver la solapa del detalle
		WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnListaRecibidosFechaExpiracion'))

		//Valida los datos de debines Recibidos desde el detalle
		vMontoDetalle = WebUI.getText(findTestObject('Object Repository/05-Pagos Debin/txtDbnDetalleRecibidosMonto'), vMontoD)
		//JOptionPane.showMessageDialog(null, vMontoDetalle)
		vConceptoDetalle = WebUI.getText(findTestObject('Object Repository/05-Pagos Debin/txtDbnDetalleRecibidosConcepto'), vConceptoD)
		//JOptionPane.showMessageDialog(null, vConceptoDetalle)
		vEstadoDetalle = WebUI.getText(findTestObject('Object Repository/05-Pagos Debin/txtDbnDetalleRecibidosEstado'), vEstadoD)
		//JOptionPane.showMessageDialog(null, vEstadoDetalle)
		vNumeroDetalle = WebUI.getText(findTestObject('Object Repository/05-Pagos Debin/txtDbnDetalleRecibidosNumero'), vNumeroD)
		//JOptionPane.showMessageDialog(null, vNumeroDetalle)

		//Compara ambos datos
		WebUI.verifyEqual(vMontoLista, vMontoDetalle, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vConceptoLista, vConceptoDetalle, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vEstadoLista, vEstadoDetalle, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vNumeroLista, vNumeroDetalle, FailureHandling.CONTINUE_ON_FAILURE)

		//Cierra la solapa del detalle
		WebUI.click(findTestObject('Object Repository/05-Pagos Debin/icoDbnSolapaDetalleCerrar'))

	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Debin Recibidos Detalle Estado                                                     *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionDebinRecibidosDetalleEstado(def vTiempo){

		String vEstadoMenu
		String vEstadoM
		String vEstadoLista
		String vEstadoL
		String vEstadoDetalle
		String vEstadoD

		//Valida los datos del Estado del Menu
		vEstadoMenu = WebUI.getText(findTestObject('Object Repository/05-Pagos Debin/txtDbnConsultaEstadosVencidos'), vEstadoM)
		//JOptionPane.showMessageDialog(null, vEstadoMenu)

		//Cliquea en Buscar
		WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnConsultarEstadoBuscar'))

		//Valida los datos del Estado en la Lista
		vEstadoLista = WebUI.getText(findTestObject('Object Repository/05-Pagos Debin/txtDbnListaRecibidosEstadoVencido'), vEstadoL)
		//JOptionPane.showMessageDialog(null, vEstadoLista)

		//Cliquea sobre el registro de la lista
		WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnListaRecibidosEstadoVencido'))

		//Valida los datos del Estado en el detalle
		vEstadoDetalle = WebUI.getText(findTestObject('Object Repository/05-Pagos Debin/txtDbnConsultaEstadoDetalleVencido'), vEstadoD)
		//JOptionPane.showMessageDialog(null, vEstadoDetalle)

		//Compara ambos datos
		WebUI.verifyEqual(vEstadoMenu, vEstadoLista, vEstadoDetalle)

		//Cierra la solapa del detalle
		WebUI.click(findTestObject('Object Repository/05-Pagos Debin/icoDbnSolapaDetalleCerrar'))

	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Debin Generados Detalle Cuenta                                                     *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionDebinGeneradosDetalleCuenta(def vTiempo){

		String vMontoLista
		String vMontoL
		String vConceptoLista
		String vConceptoL
		String vEstadoLista
		String vEstadoL
		String vNumeroLista
		String vNumeroL
		String vMontoDetalle
		String vMontoD
		String vConceptoDetalle
		String vConceptoD
		String vEstadoDetalle
		String vEstadoD
		String vNumeroDetalle
		String vNumeroD

		//Valida los datos de la lista de debines Generados
		vMontoLista = WebUI.getText(findTestObject('Object Repository/05-Pagos Debin/txtDbnListaGeneradosMonto'), vMontoL)
		//JOptionPane.showMessageDialog(null, vMontoLista)
		vConceptoLista = WebUI.getText(findTestObject('Object Repository/05-Pagos Debin/txtDbnListaGeneradosConcepto'), vConceptoL)
		//JOptionPane.showMessageDialog(null, vConceptoLista)
		vEstadoLista = WebUI.getText(findTestObject('Object Repository/05-Pagos Debin/txtDbnListaGeneradosEstado'), vEstadoL)
		//JOptionPane.showMessageDialog(null, vEstadoLista)
		vNumeroLista = WebUI.getText(findTestObject('Object Repository/05-Pagos Debin/txtDbnListaGeneradosNumero'), vNumeroL)
		//JOptionPane.showMessageDialog(null, vNumeroLista)

		//Cliquea sobre Registro de la lista para ver la solapa del detalle
		WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnListaGeneradosFechaExpiracion'))

		//Valida los datos de debines Generados desde el detalle
		vMontoDetalle = WebUI.getText(findTestObject('Object Repository/05-Pagos Debin/txtDbnDetalleGeneradosMonto'), vMontoD)
		//JOptionPane.showMessageDialog(null, vMontoDetalle)
		vConceptoDetalle = WebUI.getText(findTestObject('Object Repository/05-Pagos Debin/txtDbnDetalleGeneradosConcepto'), vConceptoD)
		//JOptionPane.showMessageDialog(null, vConceptoDetalle)
		vEstadoDetalle = WebUI.getText(findTestObject('Object Repository/05-Pagos Debin/txtDbnDetalleGeneradosEstado'), vEstadoD)
		//JOptionPane.showMessageDialog(null, vEstadoDetalle)
		vNumeroDetalle = WebUI.getText(findTestObject('Object Repository/05-Pagos Debin/txtDbnDetalleGeneradosNumero'), vNumeroD)
		//JOptionPane.showMessageDialog(null, vNumeroDetalle)

		//Compara ambos datos
		WebUI.verifyEqual(vMontoLista, vMontoDetalle, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vConceptoLista, vConceptoDetalle, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vEstadoLista, vEstadoDetalle, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(vNumeroLista, vNumeroDetalle, FailureHandling.CONTINUE_ON_FAILURE)

		//Cierra la solapa del detalle
		WebUI.click(findTestObject('Object Repository/05-Pagos Debin/icoDbnSolapaDetalleCerrar'))

	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Debin Generados Detalle Estado                                                     *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionDebinGeneradosDetalleEstado(def vTiempo){

		String vEstadoMenu
		String vEstadoM
		String vEstadoLista
		String vEstadoL
		String vEstadoDetalle
		String vEstadoD

		//Valida los datos del Estado del Menu
		vEstadoMenu = WebUI.getText(findTestObject('Object Repository/05-Pagos Debin/txtDbnConsultaEstadoGeneradosAcreditados'), vEstadoM)
		//JOptionPane.showMessageDialog(null, vEstadoMenu)

		//Cliquea en Buscar
		WebUI.click(findTestObject('Object Repository/05-Pagos Debin/btnDbnConsultarEstadoBuscar'))

		//Valida los datos del Estado en la Lista
		vEstadoLista = WebUI.getText(findTestObject('Object Repository/05-Pagos Debin/txtDbnListaGeneradosEstado'), vEstadoL)
		//JOptionPane.showMessageDialog(null, vEstadoLista)

		//Cliquea sobre el registro de la lista
		WebUI.click(findTestObject('Object Repository/05-Pagos Debin/txtDbnListaGeneradosEstado'))

		//Valida los datos del Estado en el detalle
		vEstadoDetalle = WebUI.getText(findTestObject('Object Repository/05-Pagos Debin/txtDbnDetalleGeneradosEstado'), vEstadoD)
		//JOptionPane.showMessageDialog(null, vEstadoDetalle)

		//Compara ambos datos
		WebUI.verifyEqual(vEstadoMenu, vEstadoLista, vEstadoDetalle)

		//Cierra la solapa del detalle
		WebUI.click(findTestObject('Object Repository/05-Pagos Debin/icoDbnSolapaDetalleCerrar'))

	}

}




