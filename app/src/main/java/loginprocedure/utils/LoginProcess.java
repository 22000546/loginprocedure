package loginprocedure.utils;

import loginprocedure.Main;
import java.sql.*;

public class LoginProcess {
	
	static ResultSet rs;
	
	public static boolean isAdminID(String string, String admin) {
		if(string.equals(admin)) {
			return true;
		} else
			return false;
	}
	
	public static boolean isAdmin(String adminID, String adminPW) throws SQLException {
		
		String SQL = "SELECT password FROM user_info WHERE id = '" + Main.getAdminID() +"'";
		
		rs = Main.getStatement().executeQuery(SQL);
		Main.setResultSet(rs);
		
		if(rs.next()) {
			if(Main.getResultSet().getString("password").equals(adminPW)) {
				return true;
			}	
		}
		return false;
	}
	
	public static boolean isUser(String ID, String PW) throws SQLException {
		
		String SQL = "SELECT password FROM user_info WHERE id = '" + Main.getUserID() + "'";
		
		rs = Main.getStatement().executeQuery(SQL);
		Main.setResultSet(rs);
		
		if(rs.next()) {
			if(Main.getResultSet().getString("password").equals(PW)) {
				return true;
			}
		}
		return false;
		
	}
	
}
