package chapter5.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Deprecated
public class DBConnecter {

	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	public static final String DB_URL = "jdbc:mysql://localhost:3306/easyORM";
	
	public static final String USR = "root";
	
	public static final String PASSWD = "root";
	
	public void connect(){
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USR, PASSWD);
			stmt = conn.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
					// TODO Auto-generated catch block
			e.printStackTrace();
		}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public static void main(String[] args) {
		new DBConnecter().connect();
	}

}
