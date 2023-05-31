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

import internal.GlobalVariable

import javax.swing.JOptionPane

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

		WebUI.setText(findTestObject('Object Repository/01-Login/txtLgnDNI'), vDNI)
		WebUI.setText(findTestObject('Object Repository/01-Login/txtLgnClave'), vClave)
		WebUI.setText(findTestObject('Object Repository/01-Login/txtLgnUsuario'), vUsuario)

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
		WebUI.click(findTestObject('Object Repository/02-Dashboard/lnkDsbSalir'))
		WebUI.click(findTestObject('Object Repository/02-Dashboard/btnDsbSalirMsgConfirmacionAceptar'))
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

		String vOrigenNumeroOperacion = null
		String vOrigenTipoOperacion = null
		String vOrigenImporte = null
		String vDetalleNumeroOperacion = null
		String vDetalleTipoOperacion = null
		String vDetalleImporte = null

		//En la pagina del Listado de Operaciones
		WebUI.waitForPageLoad(vTiempo)

		WebUI.getText(findTestObject('Object Repository/07-Operaciones/txtOprNumeroOperacion'), vOrigenNumeroOperacion)
		WebUI.getText(findTestObject('Object Repository/07-Operaciones/txtOprTipoOperacion'), vOrigenTipoOperacion)
		WebUI.getText(findTestObject('Object Repository/07-Operaciones/txtOprImporte'), vOrigenImporte)

		String vPrueba = WebUI.getAttribute(findTestObject('Object Repository/07-Operaciones/txtOprNumeroOperacion'),'text')

		JOptionPane.showMessageDialog(null, vPrueba)
		/*
		 WebUI.click(findTestObject('Object Repository/07-Operaciones/txtOprVerDetalle'))
		 //En la pagina del Detalle de la Operacion
		 WebUI.waitForPageLoad(vTiempo)
		 WebUI.getText(findTestObject('Object Repository/07-Operaciones/txtOprNumeroOperacion'), vDetalleNumeroOperacion)
		 WebUI.getText(findTestObject('Object Repository/07-Operaciones/txtOprTipoOperacion'), vDetalleTipoOperacion)
		 WebUI.getText(findTestObject('Object Repository/07-Operaciones/txtOprImporte'), vDetalleImporte)
		 //Comparacion
		 WebUI.verifyEqual(vOrigenNumeroOperacion, vOrigenNumeroOperacion)
		 WebUI.verifyEqual(vOrigenTipoOperacion, vDetalleTipoOperacion)
		 WebUI.verifyEqual(vOrigenImporte, vDetalleImporte)
		 println(vOrigenNumeroOperacion + "-" + vDetalleNumeroOperacion)
		 println(vOrigenTipoOperacion + "-" + vDetalleTipoOperacion)
		 println(vOrigenImporte + "-" + vDetalleImporte)
		 */
	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Detalle Pago de Servicio Sin Base	                                                *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionListaDetallePago(def vTiempo){

		String vServicioLista = null
		String vIdentificadorLista = null
		String vConceptoLista = null
		String vServicioDetalle = null
		String vIdentificadorDetalle = null
		String vConceptoDetalle = null

		//En la pagina del Listado de Servicios a pagar
		WebUI.scrollToElement(findTestObject('Object Repository/08-Pagos y Recargas/lblPYRServicioLista'), 10)
		WebUI.waitForPageLoad(vTiempo)

		WebUI.getText(findTestObject('Object Repository/08-Pagos y Recargas/lblPYRServicioLista'), vServicioLista)
		WebUI.getText(findTestObject('Object Repository/08-Pagos y Recargas/lblPYRIdentificadorLista'), vIdentificadorLista)
		WebUI.getText(findTestObject('Object Repository/08-Pagos y Recargas/lblPYRConceptoLista'), vConceptoLista)

		WebUI.click(findTestObject('Object Repository/08-Pagos y Recargas/btnPYRPagarLista'))

		//En la solapa del detalle del Servicio a pagar
		WebUI.waitForPageLoad(vTiempo)

		WebUI.getText(findTestObject('Object Repository/08-Pagos y Recargas/lblPYRServicioDetalle'), vServicioDetalle)
		WebUI.getText(findTestObject('Object Repository/08-Pagos y Recargas/lblPYRIdentificadorDetalle'), vIdentificadorDetalle)
		WebUI.getText(findTestObject('Object Repository/08-Pagos y Recargas/lblPYRConceptoDetalle'), vConceptoDetalle)

		//Comparacion
		WebUI.verifyEqual(vServicioLista, vServicioDetalle)
		WebUI.verifyEqual(vIdentificadorLista, vIdentificadorDetalle)
		WebUI.verifyEqual(vConceptoLista, vConceptoDetalle)
	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Cuenta Transferencias Cuentas Propias Pesos                                        *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionListadoTrxPropias(def vTiempo){

		String vMonedaCta
		String vMonedaTrx
		String vNombreCta = null
		String vNumeroCta = null
		String vDenominacionCta = null
		String vNombreTrx = null
		String vNumeroTrx = null
		String vDenominacionTrx = null
		String vVerificacion = null

		//En la pagina del Listado de Mis Cuentas
		WebUI.waitForPageLoad(vTiempo)

		WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrxNombreCta'), vNombreCta)
		WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrxNumeroCta'), vNumeroCta)
		WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrxDenominacionCta'), vDenominacionCta)
		//vMonedaCta = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/icoTrxMonedaPesos'))
		GlobalVariable.vMonedaCta = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/icoTrxMonedaPesos'))
		//JOptionPane.showMessageDialog(null, vMoneda)
		WebUI.click(findTestObject('Object Repository/04-Transferencias/lnkTrxCuentaPropiaPesos'))

		//En el formulario de Transferencia
		WebUI.waitForPageLoad(vTiempo)

		WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrxNombre'), vNombreTrx)
		WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrxNumero'), vNumeroTrx)
		WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrxDenominacion'), vDenominacionTrx)
		//vMonedaTrx = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxMoneda'))
		GlobalVariable.vMonedaTrx = WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxMoneda'))

		//Comparacion entre datos del Listado y Datos del Formulario de Transferencia
		WebUI.verifyEqual(vNombreCta, vNombreTrx)
		WebUI.verifyEqual(vNumeroCta, vNumeroTrx)
		WebUI.verifyEqual(vDenominacionCta, vDenominacionTrx)
		WebUI.verifyEqual(vMonedaCta, vMonedaTrx )

	}


	/*----------------------------------------------------------------------------------------------*
	 *Validación Confirmacion Transferencias Cuentas Propias Pesos                                  *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionMontoTrxPropias(def vTiempo){

		String vCtaOrigenTrx = null
		String vMontoTrx = null
		String vCtaDestinoTrx = null
		String vMontoConfirm = null
		String vCtaOrigenConfirm = null
		String vCtaDestinoConfirm = null

		//En el formulario de Transferencia
		WebUI.waitForPageLoad(vTiempo)

		WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrxCtaOrigen'), vCtaOrigenTrx)
		WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxMonto'), vMontoTrx)
		WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrxNumero'), vCtaDestinoTrx)

		WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/btnTrxContinuarFormulario'))

		//En la Pantalla de Confirmacion
		WebUI.waitForPageLoad(vTiempo)

		WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxMontoConfirm'), vMontoConfirm)
		WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrxCtaOrigenConfirm'), vCtaOrigenConfirm)
		WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/lblTrxCtaDestinoConfirm'), vCtaDestinoConfirm)

		//Comparacion entre el monto del formulario de Transferencia y la pantalla de Confirmacion
		WebUI.verifyEqual(vMontoTrx, vMontoConfirm)
		WebUI.verifyEqual(vCtaOrigenTrx, vCtaOrigenConfirm)
		WebUI.verifyEqual(vCtaDestinoTrx, vCtaDestinoConfirm)

	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Comprobante Transferencias Cuentas Propias Pesos                                   *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionNumeroOperacionTrxPropias(def vTiempo){

		String vMontoExito = null
		String vNumOperacionExito = null
		String vMontoComprobante = null
		String vNumOperacionComprobante = null

		//En la pantalla de Confirmacion
		WebUI.waitForPageLoad(vTiempo)

		WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxMontoExito'), vMontoExito)
		WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxNumOperacionExito'), vNumOperacionExito)

		WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/btnTrxVerComprobante'))

		//En el Comprobante
		WebUI.waitForPageLoad(vTiempo)

		WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxMontoComprobante'), vMontoComprobante)
		WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxNumOperacionComprobante'), vNumOperacionComprobante)

		//Comparacion entre el monto del formulario de Transferencia y la pantalla de confirmacion
		WebUI.verifyEqual(vMontoExito, vMontoComprobante)
		WebUI.verifyEqual(vNumOperacionExito, vNumOperacionComprobante)

	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Comprobante Transferencias Cuentas Propias Dolares                                 *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionNroOperacionTrxPropiasDolares(def vTiempo){

		String vMontoExito = null
		String vNumOperacionExito = null
		String vMontoComprobante = null
		String vNumOperacionComprobante = null

		//En la pantalla de Confirmacion
		WebUI.waitForPageLoad(vTiempo)

		WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxMontoExitoDolar'), vMontoExito)
		WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxNroOperExitoDolar'), vNumOperacionExito)

		WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/btnTrxVerComprobante'))

		//En el Comprobante
		WebUI.waitForPageLoad(vTiempo)

		WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtTrxMontoComprobDolar'), vMontoComprobante)
		WebUI.getText(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/txtNroOperComprobDolar'), vNumOperacionComprobante)

		//Comparacion entre el monto del formulario de Transferencia y la pantalla de confirmacion
		WebUI.verifyEqual(vMontoExito, vMontoComprobante)
		WebUI.verifyEqual(vNumOperacionExito, vNumOperacionComprobante)

	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Comprobante Transferencias a Terceros Pesos                                        *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionNroOperacionTercerosPesos(def vTiempo){

		String vMontoExito = null
		String vNumOperacionExito = null
		String vMontoComprobante = null
		String vNumOperacionComprobante = null

		//En la pantalla de Confirmacion
		WebUI.waitForPageLoad(vTiempo)

		WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxMontoExitoTerceros'), vMontoExito)
		WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxNroOperExitoTerceros'), vNumOperacionExito)

		WebUI.click(findTestObject('Object Repository/04-Transferencias/02-Nueva Transferencia/btnTrxVerComprobante'))

		//En el Comprobante
		WebUI.waitForPageLoad(vTiempo)

		WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxMontoComprobTerceros'), vMontoComprobante)
		WebUI.getText(findTestObject('Object Repository/04-Transferencias/txtTrxNroOperComprobTerceros'), vNumOperacionComprobante)

		//Comparacion entre el monto del formulario de Transferencia y la pantalla de confirmacion
		WebUI.verifyEqual(vMontoExito, vMontoComprobante)
		WebUI.verifyEqual(vNumOperacionExito, vNumOperacionComprobante)

	}


	/*----------------------------------------------------------------------------------------------*
	 *Validación Detalle Plazo Fijo                                                                 *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionListaDetallePlazoFijo(def vTiempo){

		String vNombrePFLista = null
		String vTipoPFLista = null
		String vFechaPFLista = null
		String vMontoPFLista = null
		String vNombrePFDetalle = null
		String vTipoPFDetalle = null
		String vFechaPFDetalle = null
		String vMontoPFDetalle = null

		//En el listado de Plazo Fijo
		WebUI.waitForPageLoad(vTiempo)

		WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvNombrePlazoFijoListado'), vNombrePFLista)
		WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvTipoPlazoFijoListado'), vTipoPFLista)
		WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvFechaVencimientoPFListado'), vFechaPFLista)
		WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvMontoPlazoFijoListado'), vMontoPFLista)

		WebUI.click(findTestObject('Object Repository/06-Inversiones/txtInvNombrePlazoFijoListado'))

		//En el Detalle del Plazo Fijo
		WebUI.waitForPageLoad(vTiempo)

		WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvNombrePlazoFijoDetalle'), vNombrePFDetalle)
		WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvTipoPFDetalle'), vTipoPFDetalle)
		WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvFechaVencimientoPFDetalle'), vFechaPFDetalle)
		WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvMontoPFDetalle'), vMontoPFDetalle)

		//Comparacion entre el Listado y el Detalle de Plazo Fijo
		WebUI.verifyEqual(vNombrePFLista, vNombrePFDetalle)
		WebUI.verifyEqual(vTipoPFLista, vTipoPFDetalle)
		WebUI.verifyEqual(vFechaPFLista, vFechaPFDetalle)
		WebUI.verifyEqual(vMontoPFLista, vMontoPFDetalle)

	}
	
	/*----------------------------------------------------------------------------------------------*
	 *Validación Simulación Crear Plazo Fijo                                                        *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionSimularCrearPlazoFijo(def vTiempo){

		String vMontoPFSimular = null
		String vPlazoPFSimular = null
		String vFechaPFSimular = null
		String vMontoPFCrear = null
		String vPlazoPFCrear = null
		String vFechaPFCrear = null

		//Datos igresados en la simulacion
		WebUI.waitForPageLoad(vTiempo)

		WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvPFMontoSumulado'), vMontoPFSimular)
		WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvPFDiasSimulado'), vPlazoPFSimular)
		WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvPFFechaVencimSimulacion'), vFechaPFSimular)

		WebUI.click(findTestObject('Object Repository/06-Inversiones/btnInvPFCrearSimulacion'))

		//Datos informados en la pantalla de confirmacion
		WebUI.waitForPageLoad(vTiempo)

		WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvPFMontoCrear'), vMontoPFCrear)
		WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvPFDiasCrear'), vPlazoPFCrear)
		WebUI.getText(findTestObject('Object Repository/06-Inversiones/txtInvPFFechaVencimCrear'), vFechaPFCrear)

		//Comparacion entre formulario y simulacion
		WebUI.verifyEqual(vMontoPFSimular, vMontoPFCrear)
		WebUI.verifyEqual(vPlazoPFSimular, vPlazoPFCrear)
		WebUI.verifyEqual(vFechaPFSimular, vFechaPFCrear)

	}
	
	/*----------------------------------------------------------------------------------------------*
	 *Validación Comprobante Crear Plazo Fijo                                                       *
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def ValidacionComprobanteCrearPlazoFijo(def vTiempo){

		String vMontoPFExito = null
		String vNroOperPFExito = null
		String vMontoPFComprobante = null
		String vNroOperPFComprobante = null

		//Datos igresados en la simulacion
		WebUI.waitForPageLoad(vTiempo)

		WebUI.getText(findTestObject('Object Repository/10-Fecha COB/txtInvPFMontoExito'), vMontoPFExito)
		WebUI.getText(findTestObject('Object Repository/10-Fecha COB/txtInvPFNroOperExito'), vNroOperPFExito)
		
		WebUI.click(findTestObject('Object Repository/10-Fecha COB/btnInvPFVerComprobanteExito'))

		//Datos informados en la pantalla de confirmacion
		WebUI.waitForPageLoad(vTiempo)

		WebUI.getText(findTestObject('Object Repository/10-Fecha COB/txtInvPFMontoComprobante'), vMontoPFComprobante)
		WebUI.getText(findTestObject('Object Repository/10-Fecha COB/txtInvPFNroOperComprobante'), vNroOperPFComprobante)

		//Comparacion entre formulario y simulacion
		WebUI.verifyEqual(vMontoPFExito, vMontoPFComprobante)
		WebUI.verifyEqual(vNroOperPFExito, vNroOperPFComprobante)
	}


	/*----------------------------------------------------------------------------------------------*
	 *Validación Tarjeta Visa Detalle                                                        	 	*
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionDetalleTarjetaVisa(def vTiempo){

		String vTituloVisa = null
		String vTituloVisaOvervw = null
		String vNroVisaOvervw = null
		String vNroVisa = null

		//Valida los datos de la tarjeta Visa desde el Overview
		WebUI.getText(findTestObject('Object Repository/09-Tarjetas/txtTrjVisaOverview'), vTituloVisaOvervw)
		WebUI.getText(findTestObject('Object Repository/09-Tarjetas/txtTrjNumeroVisaOverview'), vNroVisaOvervw)

		//Cliquea en la card e ingresa al módulo para ver los movimientos
		WebUI.click(findTestObject('Object Repository/09-Tarjetas/LblTrjTarjetaVisa'))

		//Valida los datos de la tarjeta Visa desde el detalle de la Tarjeta
		WebUI.getText(findTestObject('Object Repository/09-Tarjetas/lblTrjTarjetaVisaPlatinum'), vTituloVisa)
		WebUI.getText(findTestObject('Object Repository/09-Tarjetas/txtTrjNumeroVisaOverview'), vNroVisa)

		//Compara ambos datos
		WebUI.verifyEqual(vTituloVisaOvervw, vTituloVisa)
		WebUI.verifyEqual(vNroVisaOvervw, vNroVisa)
	}

	/*----------------------------------------------------------------------------------------------*
	 *Validación Cuentas Detalle                                                            	 	*
	 *----------------------------------------------------------------------------------------------*/
	@Keyword
	def comparacionCuentaDetalle(def vTiempo){

		String vFechaMovim = null
		String vConceptoMovim = null
		String vCuentaMovim = null
		String vImporteMovim = null
		String vFechaDetalle = null
		String vConceptoDetalle = null
		String vCuentaDetalle = null
		String vImporteDetalle = null

		//Valida los datos de la cuenta desde Movimientos
		WebUI.getText(findTestObject('Object Repository/10-Fecha COB/txtCtaMovimPesosFecha'), vFechaMovim)
		WebUI.getText(findTestObject('Object Repository/10-Fecha COB/txtCtaMovimPesosConcepto'), vConceptoMovim)
		WebUI.getText(findTestObject('Object Repository/10-Fecha COB/txtCtaMovimCuenta'), vCuentaMovim)
		WebUI.getText(findTestObject('Object Repository/10-Fecha COB/txtCtaMovimPesosImporte'), vImporteMovim)

		//Cliquea sobre una cta para ver la solapa del detalle
		WebUI.click(findTestObject('Object Repository/10-Fecha COB/txtCtaMovimPesosFecha'))

		//Valida los datos de la cuenta desde el detalle
		WebUI.getText(findTestObject('Object Repository/10-Fecha COB/txtCtaDetalleFecha'), vFechaDetalle)
		WebUI.getText(findTestObject('Object Repository/10-Fecha COB/txtCtaDetalleConcepto'), vConceptoDetalle)
		WebUI.getText(findTestObject('Object Repository/10-Fecha COB/txtCtaDetalleCuenta'), vCuentaDetalle)
		WebUI.getText(findTestObject('Object Repository/10-Fecha COB/txtCtaDetalleImporte'), vImporteDetalle)

		//Compara ambos datos
		WebUI.verifyEqual(vFechaMovim, vFechaDetalle)
		WebUI.verifyEqual(vConceptoMovim, vConceptoDetalle)
		WebUI.verifyEqual(vCuentaMovim, vCuentaDetalle)
		WebUI.verifyEqual(vImporteMovim, vImporteDetalle)

	}





}




