package DAO.MARIADB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDBConexion {
	private static Connection con = null;

	/**
	 * debe ir cargado en un xml externo
	 * 
	 * @return
	 */
	private static String uri = "jdbc:mysql://localhost:3307/aadd_ud2";
	private static String user = "root";
	private static String password = "";

	public static Connection getConexion() {
		if (con == null) {
			try {
				con = DriverManager.getConnection(uri, user, password);
			} catch (SQLException e) {
				e.printStackTrace();
				con = null;
				// TODO: handle exception
			}
		}
		return con;
	}

	public static void cerrar() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}
}
