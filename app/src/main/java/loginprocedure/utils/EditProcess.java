package loginprocedure.utils;

import loginprocedure.Main;
import loginprocedure.User;

import java.sql.*;

public class EditProcess {
	
	static ResultSet rs;
	static int update;
	
	public static boolean signOut(String id) throws SQLException {
		
		String SQL = "DELETE FROM user_info WHERE id = '" + id + "'";
		
		update = Main.getStatement().executeUpdate(SQL);
		
		return true;
	}
	
	public static boolean editPassword(String id, String newPW) throws SQLException {
		
		String SQL = "UPDATE user_info SET password = '" + newPW + "' WHERE id = '" + id + "'";
		
		update = Main.getStatement().executeUpdate(SQL);
		
		return true;
		
	}

	public static boolean editName(String id, String newName) throws SQLException {
		
		String SQL = "UPDATE user_info SET name = '" + newName + "' WHERE id = '" + id + "'";
		
		update = Main.getStatement().executeUpdate(SQL);
		
		return true;
	}
	
	public static boolean editGender(String id, int newGender) throws SQLException {
		
		String SQL = "UPDATE user_info SET gender = " + newGender + " WHERE id = '" + id + "'";
		
		update = Main.getStatement().executeUpdate(SQL);
		
		return true;
	}
	
	public static boolean editPhone(String id, String newPhone) throws SQLException {
		
		String SQL = "UPDATE user_info SET phone_number = '" + newPhone + "' WHERE id = '" + id + "'";
		
		update = Main.getStatement().executeUpdate(SQL);
		
		return true;
	}

}
