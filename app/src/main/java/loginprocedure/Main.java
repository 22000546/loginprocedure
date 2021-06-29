package loginprocedure;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.*;
import java.awt.Font;

public class Main extends JFrame {
	
	/*
	 * Field
	 */
	
	private JFrame frame;
	
	/*
	 * Settings
	 */
	static final int WIDTH = 1200;
	static final int HEIGHT = 700;
	
	
	/*
	 * Subframes
	 * Sign-up / 
	 */

	/*
	 * Sign up frame 
	 */
	class SignUpFrame extends JFrame {
		
	}
	
	/*
	 * main method
	 */
	public static void main(String[] args) {
		
		Connection connection = null;
		
		String url = "jdbc:mysql://localhost:3306/yeeunDB";
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("1");
			connection = DriverManager.getConnection(url, "yeeun", "1234");
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
				//new Home();
				//frame.setVisible(false);
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
				//
			}
		});
		
	}
}
