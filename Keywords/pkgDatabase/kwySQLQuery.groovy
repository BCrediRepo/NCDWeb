package pkgDatabase

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

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

public class kwySQLQuery {

	private static Connection connection = null;

	/**
	 * Abre la conexión con la dB
	 * @param dataFile path absoluto
	 * @return instancia de java.sql.Connection
	 **/
	@Keyword
	def QueryFija(){
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

		Statement stm = connection.createStatement()
		ResultSet rs = stm.executeQuery('SELECT * FROM Labels WHERE Int = 1')
		rs.next()

		println(rs)

		if(connection != null && !connection.isClosed()){
			connection.close()
		}
		connection = null
	}
}
