package loginprocedure;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class User extends JFrame {
	
	private JFrame frame;
	// Temporary variable
	private String name = "DATABASE";
	
	public User() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, Main.WIDTH, Main.HEIGHT);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
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
		JButton revise = new JButton("정보 수정");
		revise.setHorizontalAlignment(SwingConstants.CENTER);
		revise.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		revise.setBounds(Main.WIDTH/4-100, Main.HEIGHT/2-50, 200, 80);
		revise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
			}
		});
		panel.add(revise);
		
		JButton exit = new JButton("종료");
		exit.setHorizontalAlignment(SwingConstants.CENTER);
		exit.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		exit.setBounds(2*Main.WIDTH/4-100, Main.HEIGHT/2-50, 200, 80);
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
		signout.setBounds(3*Main.WIDTH/4-100, Main.HEIGHT/2-50, 200, 80);
		signout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int check = JOptionPane.showConfirmDialog(null, "정말로 탈퇴하시겠습니까?");
				if(check == 0) {
					// Delete from database
					
					
					if(true) { // Check signout
						JOptionPane.showMessageDialog(null, "탈퇴 처리 되었습니다.\n프로그램을 종료합니다.");
						System.exit(0);
					}
				}
			}
		});
		panel.add(signout);
		
		
	}

}
