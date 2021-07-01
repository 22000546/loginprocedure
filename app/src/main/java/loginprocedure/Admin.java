package loginprocedure;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.SwingConstants;

public class Admin extends JFrame {
	
	private JFrame frame;
	private static String id;
	private static String password;
	private static String name;
	private static String birthdate;
	private static int gender;
	private static String phone;

	public Admin() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, Main.WIDTH, Main.HEIGHT);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		try {
			loadData(Main.getAdminID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		initialize();
		
	}
	
	private void initialize() {
		
		/*
		 * Admin frame
		 */
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, Main.WIDTH, Main.HEIGHT);
		panel.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		/*
		 * User interface
		 */
		JLabel comment1 = new JLabel("안녕하세요, " + name + " 님!");
		comment1.setForeground(Color.WHITE);
		comment1.setHorizontalAlignment(SwingConstants.CENTER);
		comment1.setBounds(Main.WIDTH/2-150, 100, 300, 50);
		comment1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		panel.add(comment1);
		
		JLabel comment2 = new JLabel("관리자 페이지입니다.");
		comment2.setForeground(Color.WHITE);
		comment2.setHorizontalAlignment(SwingConstants.CENTER);
		comment2.setBounds(Main.WIDTH/2-150, 150, 300, 50);
		comment2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		panel.add(comment2);
		
		JLabel comment3 = new JLabel("원하시는 메뉴를 선택해주세요.");
		comment3.setForeground(Color.WHITE);
		comment3.setHorizontalAlignment(SwingConstants.CENTER);
		comment3.setBounds(Main.WIDTH/2-150, 200, 300, 50);
		comment3.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		panel.add(comment3);
		
		/*
		 * Buttons
		 */
		JButton revise = new JButton("사용자 정보 수정");
		revise.setHorizontalAlignment(SwingConstants.CENTER);
		revise.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		revise.setBounds(Main.WIDTH/4-100, Main.HEIGHT/2-50, 200, 80);
		revise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminDataEditor();
				frame.setVisible(false);
			}
		});
		panel.add(revise);
		
		JButton logout = new JButton("로그아웃");
		logout.setHorizontalAlignment(SwingConstants.CENTER);
		logout.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		logout.setBounds(2*Main.WIDTH/4-100, Main.HEIGHT/2-50, 200, 80);
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
		exit.setBounds(3*Main.WIDTH/4-100, Main.HEIGHT/2-50, 200, 80);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int check = JOptionPane.showConfirmDialog(null, "정말로 종료하시겠습니까?");
				if(check == 0) {
					System.exit(0);
				}
			}
		});
		panel.add(exit);
		
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
	
}
