package loginprocedure;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SignUp extends Frame {
	
	private JFrame frame;
	private boolean success;
	private String[] monthList = {"1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"};
	private Integer[] dayList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
	
	public SignUp() {
		
		/*
		 * Frame setting
		 */
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
		 * Sign-up frame
		 */
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, Main.WIDTH, Main.HEIGHT);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		// id
		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(Main.WIDTH/6, 200, 100, 30);
		idLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(idLabel);
		
		JTextField id = new JTextField(20);
		id.setBounds(Main.WIDTH/6-10, 230, 200, 30);
		panel.add(id);
		
		// password
		JLabel pwLabel = new JLabel("비밀번호");
		pwLabel.setBounds(Main.WIDTH/6, 270, 100, 30);
		pwLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(pwLabel);
		
		JPasswordField pw = new JPasswordField(20);
		pw.setBounds(Main.WIDTH/6-10, 300, 200, 30);
		panel.add(pw);
		
		// check password
		JLabel pwCheckLabel = new JLabel("비밀번호 재확인");
		pwCheckLabel.setBounds(Main.WIDTH/6, 340, 100, 30);
		pwCheckLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(pwCheckLabel);
		
		JPasswordField pwCheck = new JPasswordField(20);
		pwCheck.setBounds(Main.WIDTH/6-10, 370, 200, 30);
		panel.add(pwCheck);
		
		// name
		JLabel nameLabel = new JLabel("이름");
		nameLabel.setBounds(4*Main.WIDTH/6, 200, 100, 30);
		nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(nameLabel);
		
		JTextField name = new JTextField(20);
		name.setBounds(4*Main.WIDTH/6-10, 230, 200, 30);
		panel.add(name);
		
		// birthday
		JLabel birthLabel = new JLabel("생년월일 / 성별");
		birthLabel.setBounds(4*Main.WIDTH/6, 270, 100, 30);
		birthLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(birthLabel);
		
		JTextField year = new JTextField(8);
		year.setBounds(4*Main.WIDTH/6-10, 300, 80, 30);
		panel.add(year);
		
		JComboBox month = new JComboBox(monthList);
		month.setBounds(4*Main.WIDTH/6+70, 300, 70, 30);
		panel.add(month);
		
		JComboBox day = new JComboBox(dayList);
		day.setBounds(4*Main.WIDTH/6+140, 300, 70, 30);
		panel.add(day);
		
		// gender
		JCheckBox man = new JCheckBox("남자");
		man.setBounds(4*Main.WIDTH/6-10, 330, 70, 30);
		panel.add(man);
		
		JCheckBox woman = new JCheckBox("여자");
		woman.setBounds(4*Main.WIDTH/6+50, 330, 70, 30);
		panel.add(woman);
		
		JCheckBox uncheck = new JCheckBox("선택 안함");
		uncheck.setBounds(4*Main.WIDTH/6+110, 330, 110, 30);
		panel.add(uncheck);
		
		// phone number
		JLabel phoneLabel = new JLabel("휴대폰 번호");
		phoneLabel.setBounds(4*Main.WIDTH/6, 370, 100, 30);
		phoneLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(phoneLabel);
		
		JTextField phone = new JTextField(20);
		phone.setBounds(4*Main.WIDTH/6-10, 400, 200, 30);
		panel.add(phone);
		
		success = true;
		
		// submit
		JButton submit = new JButton("제출");
		submit.setBounds(Main.WIDTH/2-80, 500, 160, 60);
		submit.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(success) {
					int check = JOptionPane.showConfirmDialog(null, "가입이 완료되었습니다. 로그인 창으로 돌아가시겠습니까?");
					if(check == 0) {
						new Main();
						frame.setVisible(false);
					} else if(check == 1) {
						System.exit(0);
					}
				} else {
					JOptionPane.showMessageDialog(null, "문제가 발생하였습니다. 다시 시도하십시오.");
				}
			}
		});
		panel.add(submit);
		
	}
	
	
}
