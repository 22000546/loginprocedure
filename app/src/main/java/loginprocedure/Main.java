package loginprocedure;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

import loginprocedure.utils.LoginProcess;

import java.awt.Font;
import java.awt.HeadlessException;

public class Main extends JFrame {
	
	/*
	 * Field
	 */
	
	private JFrame frame;
	
	// For databases
	private static Connection connection;
	private static Statement statement;
	private static ResultSet resultSet;
	
	private boolean loginSuccess = true;
	private static String adminID = "admin";
	private static String userID;
	private String userPW;
	
	/*
	 * Settings
	 */
	static final int WIDTH = 1200;
	static final int HEIGHT = 700;
	
	/*
	 * main method
	 */
	public static void main(String[] args) {
				
		String url = "jdbc:mysql://localhost:3306/yeeunDB";
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("1");
			connection = DriverManager.getConnection(url, "yeeun", "1234");
			statement = connection.createStatement();
			System.out.println("DB successfully connected.");
			
		} catch (Exception e) {
			
			System.out.println(e.toString());
			
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	/*
	 * Constructor of Main class
	 */
	public Main() {
		initialize();
	}
	
	/*
	 * Initialize
	 */
	private void initialize() {
		
		/*
		 * Set frame structure
		 */
		
		frame = new JFrame();
		frame.setBounds(100, 100, WIDTH, HEIGHT);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		/*
		 * Login frame (Default frame)
		 */
		JPanel panel = new JPanel();
		panel.setBounds(50, 50, WIDTH-100, HEIGHT-100);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		// id
		JLabel idLabel = new JLabel("ID : ");
		idLabel.setBounds(458, 272, 50, 30);
		panel.add(idLabel);
		
		JTextField id = new JTextField(10);
		id.setForeground(Color.DARK_GRAY);
		id.setBounds(493, 272, 140, 30);
		id.setText("ID");
		panel.add(id);
		
		// password
		JLabel pwLabel = new JLabel("PASSWORD : ");
		pwLabel.setBounds(403, 322, 100, 30);
		panel.add(pwLabel);
		
		JPasswordField pw = new JPasswordField(10);
		pw.setForeground(Color.DARK_GRAY);
		pw.setBounds(493, 322, 140, 30);
		pw.setText("PASSWORD");
		panel.add(pw);
		
		// login button
		JButton login = new JButton("로그인");
		login.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		login.setBounds(673, 277, 100, 70);
		panel.add(login);
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				userID = id.getText();
				char[] tmp = pw.getPassword();
				userPW = new String(tmp);
				
				if(LoginProcess.isAdminID(userID, adminID)) {
					try {
						if(LoginProcess.isAdmin(userID, userPW)) {
							new Admin();
							frame.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "ID/비밀번호가 올바르지 않거나 회원 정보가 없습니다.\n" + "다시 한 번 확인하십시오.");
						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					try {
						if(LoginProcess.isUser(userID, userPW)) {
							new User();
							frame.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "ID/비밀번호가 올바르지 않거나 회원 정보가 없습니다.\n" + "다시 한 번 확인하십시오.");
						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		// sign up button
		JButton signup = new JButton("회원가입");
		signup.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		signup.setBounds(458, 376, 100, 50);
		panel.add(signup);
		signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SignUp();
				frame.setVisible(false);
			}
		});
		
		// search id/pw
		JButton search = new JButton("ID/비밀번호 찾기");
		search.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		search.setBounds(558, 376, 100, 50);
		panel.add(search);
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new Searcher();
			}
		});
		
	}
	
	public static String getAdminID() {
		return adminID;
	}
	
	public static String getUserID() {
		return userID;
	}
	
	public static void setStatement(Statement state) {
		statement = state;
	}
	
	public static Statement getStatement() {
		return statement;
	}
	
	public static void setResultSet(ResultSet set) {
		resultSet = set;
	}
	
	public static ResultSet getResultSet() {
		return resultSet;
	}
	
}
