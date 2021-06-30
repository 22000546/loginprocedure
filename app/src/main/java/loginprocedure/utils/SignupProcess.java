package loginprocedure.utils;

import java.sql.*;

import javax.swing.JOptionPane;

import loginprocedure.Main;

public class SignupProcess {
	
	static ResultSet rs;
	
	public static int isOverlapped(String ID) throws SQLException {
		
		String SQL = "SELECT id FROM user_info";
				
		rs = Main.getStatement().executeQuery(SQL);
		Main.setResultSet(rs);
		
		while(rs.next()) {
			if(Main.getResultSet().getString("id").equals(ID)) {
				return -1;
			}	
		}
		
		return 1;
	}

	public static boolean isPasswordEqual(String pw1, String pw2) {
		if(pw1.equals(pw2))
			return true;
		return false;
	}
	
	public static boolean isIDFormatted(String id) {
		if(id.length() < 5) {
			return false;
		}
		if(id.length() > 20) {
			return false;
		}
		return true;
	}
	
	public static boolean isPWFormatted(String pw) {
		if(pw.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d~!@#$%^&*()+|=]{8,20}$")) {
			return true;
		}
		return false;
	}
	
	public static boolean isNameFormatted(String name) {
		if(name == null)
			return false;
		if(name.isBlank())
			return false;
		if(name.length() > 20)
			return false;
		return true;
	}

	public static boolean isBirthDateFormatted(String date) {
		String[] tmp = date.split("-");
		if(tmp[0] == null)
			return false;
		if(!tmp[0].matches("[0-9]{4}")) {
			return false;
		}
		return true;
	}
	
	public static boolean isPhoneFormatted(String phoneNum) {
		if(phoneNum.equals("--"))
			return true;
		
		String[] tmp = phoneNum.split("-");
		
		if(!tmp[0].matches("[0-9]{3}")) {
			return false;
		} else if(!tmp[1].matches("[0-9]{4}")) {
			return false;
		} else if(!tmp[2].matches("[0-9]{4}")) {
			return false;
		}
		return true;
	}
	
	public static boolean signUp(String id, String password, String name, String birthdate, int gender, String phone_number) {
						
		String SQL;
		
		if(phone_number.equals("--")) {
			SQL = "INSERT INTO user_info (id, password, name, birthdate, gender, phone_number) values ('"
					+ id
					+ "', '"
					+ password
					+ "', '"
					+ name
					+ "', '"
					+ birthdate
					+ "', "
					+ gender
					+ ", null)";
		} else {
			SQL = "INSERT INTO user_info (id, password, name, birthdate, gender, phone_number) values ('"
					+ id
					+ "', '"
					+ password
					+ "', '"
					+ name
					+ "', '"
					+ birthdate
					+ "', "
					+ gender
					+ ", '"
					+ phone_number
					+ "')";
		}
		
		try {
			int insert = Main.getStatement().executeUpdate(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
