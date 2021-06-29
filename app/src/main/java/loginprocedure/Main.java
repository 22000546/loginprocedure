package loginprocedure;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.*;

public class Main extends JFrame {
	
	/*
	 * Field
	 */
	
	private JFrame frame;
	
	/*
	 * Settings
	 */
	private static final int WIDTH = 1200;
	private static final int HEIGHT = 700;
	
	
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
		//panel.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		// id
		JLabel idLabel = new JLabel("ID : ");
		idLabel.setBounds(WIDTH/2-105, HEIGHT/6, 50, 30);
		panel.add(idLabel);
		
		JTextField id = new JTextField(10);
		id.setBounds(WIDTH/2-70, HEIGHT/6, 140, 30);
		id.setText("ID");
		panel.add(id);
		
		// password
		JLabel pwLabel = new JLabel("PASSWORD : ");
		pwLabel.setBounds(WIDTH/2-160, HEIGHT/6+50, 100, 30);
		panel.add(pwLabel);
		
		JPasswordField pw = new JPasswordField(10);
		pw.setBounds(WIDTH/2-70, HEIGHT/6+50, 140, 30);
		pw.setText("PASSWORD");
		panel.add(pw);
		
		// login button
		JButton login = new JButton("로그인");
		login.setBounds(HEIGHT/2+100, HEIGHT/2-100, 200, 70);
		panel.add(login);
	}
}
