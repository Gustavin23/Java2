package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Classe modelo - Conex�o com o banco de dados
 * @author gustavo.rdboamorte
 *
 */
public class DAO {
	// Par�metros de conex�o
	private String driver="com.mysql.cj.jdbc.Driver";
	private String url="jdbc:mysql://192.168.0.115:3306/lojask8";
	private String user="root";
	private String password="123@senac";
	
	/**
	 * M�todo respons�vel pela conex�o
	 * @return con / null
	 */
	public Connection conectar() {
		// Objeto usado para conex�o
		Connection con = null;
		// Tratamento de exce��es
		try {
			// Uso do driver
			Class.forName(driver);
			// Estabelecer a conex�o
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
