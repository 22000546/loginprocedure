package loginprocedure;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import loginprocedure.utils.EditProcess;
import loginprocedure.utils.LoginProcess;

public class User extends JFrame {
	
	private JFrame frame;
	// Temporary variable
	private static String id;
	private static String password;
	private static String name;
	private static String birthdate;
	private static int gender;
	private static String phone;
	
	public User() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, Main.WIDTH, Main.HEIGHT);
		frame.setBackground(Color.LIGHT_GRAY);
		try {
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		try {
			loadData(Main.getUserID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		initialize();
		
	}
	
	private void initialize() {
		
		/*
		 * User frame
		 */
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, Main.WIDTH, Main.HEIGHT);
		panel.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		/*
		 * User interface
		 */
		// name from database
		JLabel comment1 = new JLabel("안녕하세요, " + name + " 님!");
		comment1.setForeground(Color.WHITE);
		comment1.setHorizontalAlignment(SwingConstants.CENTER);
		comment1.setBounds(Main.WIDTH/2-150, 100, 300, 50);
		comment1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		panel.add(comment1);
		
		JLabel comment2 = new JLabel("원하시는 메뉴를 선택해주세요.");
		comment2.setForeground(Color.WHITE);
		comment2.setHorizontalAlignment(SwingConstants.CENTER);
		comment2.setBounds(Main.WIDTH/2-150, 150, 300, 50);
		comment2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		panel.add(comment2);
		
		/*
		 * Buttons
		 */
		JButton revise = new JButton("내 정보 수정");
		revise.setHorizontalAlignment(SwingConstants.CENTER);
		revise.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		revise.setBounds(Main.WIDTH/5-100, Main.HEIGHT/2-50, 200, 80);
		revise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog("비밀번호를 입력하십시오.");
				try {
					if(LoginProcess.isUser(id, input)) {
						new UserDataEditor();
						frame.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다. 다시 시도하십시오.");
					}
				} catch (HeadlessException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(revise);
		
		JButton logout = new JButton("로그아웃");
		logout.setHorizontalAlignment(SwingConstants.CENTER);
		logout.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		logout.setBounds(2*Main.WIDTH/5-100, Main.HEIGHT/2-50, 200, 80);
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int check = JOptionPane.showConfirmDialog(null, "정말로 로그아웃하시겠습니까?");
				if(check == 0) {
					new Main();
					frame.setVisible(false);
				}
			}
		});
		panel.add(logout);
		
		JButton exit = new JButton("프로그램 종료");
		exit.setHorizontalAlignment(SwingConstants.CENTER);
		exit.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		exit.setBounds(3*Main.WIDTH/5-100, Main.HEIGHT/2-50, 200, 80);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int check = JOptionPane.showConfirmDialog(null, "정말로 종료하시겠습니까?");
				if(check == 0) {
					System.exit(0);
				}
			}
		});
		panel.add(exit);
		
		JButton signout = new JButton("탈퇴");
		signout.setHorizontalAlignment(SwingConstants.CENTER);
		signout.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		signout.setBounds(4*Main.WIDTH/5-100, Main.HEIGHT/2-50, 200, 80);
		signout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int check = JOptionPane.showConfirmDialog(null, "정말로 탈퇴하시겠습니까?");
				if(check == 0) {
					try {
						if(EditProcess.signOut(id)) {
							JOptionPane.showMessageDialog(null, "탈퇴 처리 되었습니다.\n프로그램을 종료합니다.");
							System.exit(0);
						} else {
							JOptionPane.showMessageDialog(null, "탈퇴 처리에 문제가 생겼습니다.\n다시 시도해주십시오.");
						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		panel.add(signout);
		
	}
	
	private static boolean loadData(String userID) throws SQLException {
		
		String SQL = "SELECT * FROM user_info WHERE id = '" + userID + "'";
		
		ResultSet rs = Main.getStatement().executeQuery(SQL);
		Main.setResultSet(rs);
		
		if(rs.next()) {
			id = Main.getResultSet().getString("id");
			password = Main.getResultSet().getString("password");
			name = Main.getResultSet().getString("name");
			birthdate = Main.getResultSet().getString("birthdate");
			gender = Main.getResultSet().getInt("gender");
			phone = Main.getResultSet().getString("phone_number");
		}
		
		return true;
	}
	
	public static String getID() {
		return id;
	}
	
	public static String getPassword() {
		return password;
	}
	
	public static String getUserName() {
		return name;
	}
	
	public static String getBirthdate() {
		return birthdate;
	}
	
	public static int getGender() {
		return gender;
	}
	
	public static String getPhone() {
		return phone;
	}

}
