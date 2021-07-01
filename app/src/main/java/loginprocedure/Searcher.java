package loginprocedure;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import loginprocedure.utils.SearchProcess;

public class Searcher extends JFrame {
	
	JFrame frame;
	private String userName;
	private String phone;
	

	public Searcher() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, Main.WIDTH, Main.HEIGHT);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		initialize();
	}
	
	private void initialize() {
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, Main.WIDTH, Main.HEIGHT);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel idSearch = new JLabel("ID / 비밀번호 찾기");
		idSearch.setBounds(Main.WIDTH/3, 80, Main.WIDTH/3, 80);
		idSearch.setBackground(Color.DARK_GRAY);
		idSearch.setHorizontalAlignment(SwingConstants.CENTER);
		idSearch.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		panel.add(idSearch);
		
		JLabel name = new JLabel("이름");
		name.setBounds(300, 300, 100, 30);
		name.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(name);
		
		JTextField nameField = new JTextField(20);
		nameField.setBounds(350, 300, 100, 30);
		panel.add(nameField);
		
		JLabel phoneLabel = new JLabel("휴대폰 번호");
		phoneLabel.setBounds(4*Main.WIDTH/6-150, 300, 100, 30);
		phoneLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(phoneLabel);
		
		JTextField phone1 = new JTextField(20);
		phone1.setBounds(4*Main.WIDTH/6-60, 300, 50, 30);
		panel.add(phone1);
		
		JLabel phoneConnector1 = new JLabel("-");
		phoneConnector1.setBounds(4*Main.WIDTH/6-10, 300, 20, 30);
		phoneConnector1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(phoneConnector1);
		
		JTextField phone2 = new JTextField(20);
		phone2.setBounds(4*Main.WIDTH/6+10, 300, 50, 30);
		panel.add(phone2);
		
		JLabel phoneConnector2 = new JLabel("-");
		phoneConnector2.setBounds(4*Main.WIDTH/6+60, 300, 20, 30);
		phoneConnector2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(phoneConnector2);
		
		JTextField phone3 = new JTextField(20);
		phone3.setBounds(4*Main.WIDTH/6+80, 300, 50, 30);
		panel.add(phone3);
		
		JButton idSearcher = new JButton("ID 찾기");
		idSearcher.setBounds(Main.WIDTH/3-70, 500, 140, 50);
		idSearcher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userName = nameField.getText();
				phone = phone1.getText() + "-" + phone2.getText() + "-" + phone3.getText();
				String id;
				try {
					id = SearchProcess.searchID(userName, phone);
					if(id == null) {
						JOptionPane.showMessageDialog(null, "해당되는 ID가 존재하지 않습니다.");
					} else {
						JOptionPane.showMessageDialog(null, "ID는 " + id + "입니다.");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(idSearcher);
		
		JButton pwSearcher = new JButton("비밀번호 찾기");
		pwSearcher.setBounds(2*Main.WIDTH/3-70, 500, 140, 50);
		pwSearcher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userName = nameField.getText();
				phone = phone1.getText() + "-" + phone2.getText() + "-" + phone3.getText();
				String pw;
				try {
					pw = SearchProcess.searchPW(userName, phone);
					if(pw == null) {
						JOptionPane.showMessageDialog(null, "해당되는 비밀번호가 존재하지 않습니다.");
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호는 " + pw + "입니다.");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(pwSearcher);
		
		// Back button
		JButton back = new JButton();
		back.setBounds(10, 10, 50, 50);
		back.setIcon(new ImageIcon("./images/left-arrow.png"));
		back.setBorderPainted(false);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main();
				frame.setVisible(false);
			}
		});
		panel.add(back);
		
	}
	
}
