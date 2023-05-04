package pkgDatabase

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

import groovy.sql.ResultSetMetaDataWrapper

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

import javax.swing.JOptionPane

import internal.GlobalVariable

public class kwySQL {

	private static Connection connection = null;

	/**
	 * Abre la conexión con la dB
	 * @param dataFile path absoluto
	 * @return instancia de java.sql.Connection
	 **/
	@Keyword
	def connectDB(){
		//Parametros de conexion
		String dBServer = findTestData('00-Database/dBase-Params').getValue(2,1)
		String dBName = findTestData('00-Database/dBase-Params').getValue(2,2)
		String dBUser = findTestData('00-Database/dBase-Params').getValue(2,3)
		String dBPswd = findTestData('00-Database/dBase-Params').getValue(2,4)

		//String de conexion
		String dBConnString = "jdbc:mysql://" + dBServer + ":" + "/" + dBName

		Class.forName("com.mysql.jdbc.Driver")
		if(connection != null && !connection.isClosed()){
			connection.close()
		}
		connection = DriverManager.getConnection(dBConnString, dBUser, dBPswd)
		//connection = DriverManager.getConnection('jdbc:mysql://aws.connect.psdb.cloud/ncdtestdata','g0xzdwatq04il432urws','pscale_pw_xUQ9FSdLakmfBAonylOYDQTgZE8hNUzjS1hrkrbaw0d')
		return connection
	}

	/**
	 * Ejecuta un consulta SQL sobre la dB
	 * @param query SQL como string
	 **/
	@Keyword
	def executeQuery(String queryString) {
		Statement stm = connection.createStatement()
		ResultSet rs = stm.executeQuery(queryString)
		rs.next()

		return rs
	}

	@Keyword
	def closeDatabaseConnection() {
		if(connection != null && !connection.isClosed()){
			connection.close()
		}
		connection = null
	}

	/**
	 * Ejecuta INSERT/UPDATE/DELETE/COUNT/SUM.. en la dB
	 * @param query SQL como string
	 */
	@Keyword
	def execute(String queryString) {
		Statement stm = connection.createStatement()
		boolean result = stm.execute(queryString)
		return result
	}

	/*----------------------------------------------------------------------------------------------*
	 *SQL Operaciones																			    *
	 *					    																		*
	 *Menu desplegable de Operaciones			   													*												    											*
	 *Parametros:																		            *
	 *Id: nombre del servidor a conectar													        *														*
	 *----------------------------------------------------------------------------------------------*/
	/*
	 @Keyword
	 def fTipoOP(int vId) {
	 String vQuery = 'SELECT * FROM Labels WHERE id =  ' + vId
	 //Conecta a la BD
	 //Parametros de conexion
	 String dBServer = findTestData('00-Database/dBase-Params').getValue(2,1)
	 String dBName = findTestData('00-Database/dBase-Params').getValue(2,2)
	 String dBUser = findTestData('00-Database/dBase-Params').getValue(2,3)
	 String dBPswd = findTestData('00-Database/dBase-Params').getValue(2,4)
	 //String de conexion
	 String dBConnString = "jdbc:mysql://" + dBServer + ":" + "/" + dBName
	 Class.forName("com.mysql.jdbc.Driver")
	 if(connection != null && !connection.isClosed()){
	 connection.close()
	 }
	 connection = DriverManager.getConnection(dBConnString, dBUser, dBPswd)
	 //connection = DriverManager.getConnection('jdbc:mysql://aws.connect.psdb.cloud/ncdtestdata','g0xzdwatq04il432urws','pscale_pw_xUQ9FSdLakmfBAonylOYDQTgZE8hNUzjS1hrkrbaw0d')
	 return connection
	 //Ejecuta Consulta
	 Statement stm = connection.createStatement()
	 ResultSet rs = stm.executeQuery(vQuery)
	 rs.next()
	 return rs
	 ResultSet naty = rs
	 naty.get
	 GlobalVariable.vOpTodas = rs.getString(3)
	 GlobalVariable.vOpTrf = rs.getString(3)
	 GlobalVariable.vOpExtracciones = rs.getString(3)
	 GlobalVariable.vOpModo = rs.getString(3)
	 GlobalVariable.vOpChques = rs.getString(3)
	 GlobalVariable.vOpAcceso = rs.getString(3)
	 GlobalVariable.vOpPagos = rs.getString(3)
	 GlobalVariable.vOpTarjetas = rs.getString(3)
	 GlobalVariable.vOpAhorro = rs.getString(3)
	 GlobalVariable.vOpDebin = rs.getString(3)
	 GlobalVariable.vOpAfip = rs.getString(3)
	 GlobalVariable.vOpRecargas = rs.getString(3)
	 GlobalVariable.vOpSolicitudes = rs.getString(3)
	 if(connection != null && !connection.isClosed()){
	 connection.close()
	 }
	 connection = null
	 }
	 */
}
