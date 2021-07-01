package loginprocedure.utils;

import loginprocedure.Main;
import java.sql.*;

public class SearchProcess {

	static ResultSet rs;
	
	public static String searchID(String name, String phone) throws SQLException {
		
		String id = "";
		String SQL = "SELECT id FROM user_info WHERE name = '" + name +"' AND phone_number = '" + phone + "'";
		
		rs = Main.getStatement().executeQuery(SQL);
		Main.setResultSet(rs);
		
		if(rs.next()) {
			id = rs.getString("id");	
		}
		
		return id;
	}
	
	public static String searchPW(String name, String phone) throws SQLException {
		
		String pw = "";
		String SQL = "SELECT password FROM user_info WHERE name = '" + name +"' AND phone_number = '" + phone + "'";
		
		rs = Main.getStatement().executeQuery(SQL);
		Main.setResultSet(rs);
		
		if(rs.next()) {
			pw = rs.getString("password");	
		}
		
		return pw;
	}
	
}
