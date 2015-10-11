package restaurant.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {

	public final static String DB_url = "jdbc:mysql://localhost:3306/achija";
	public final static String DB_user = "root";
	public final static String DB_password = "billgates";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("MySQL JDBC Driver Successfully Loaded");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error in loading driver" + e.getMessage());
		}
	}

	public static Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(DB_url, DB_user, DB_password);
			System.out.println("Connection Successfull");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error in connection" + e.getMessage());
		}
		return con;
	}

	public static void closeResource(PreparedStatement ps, ResultSet rs, Connection con){
		try {
			if(ps != null){
					ps.close();
				}
			if (rs != null){
				rs.close();
			}
			if (con != null){
				con.close();
			}
			}catch (SQLException e) {
					e.printStackTrace();
				}
			}
	}