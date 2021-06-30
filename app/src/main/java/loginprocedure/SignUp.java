package loginprocedure;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.*;

import loginprocedure.utils.SignupProcess;

public class SignUp extends JFrame {
	
	private JFrame frame;
	private String[] monthList = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
	private String[] dayList = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", 
								"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", 
								"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	
	/*
	 * For input information
	 */
	private String userID;
	private String userPW;
	private String userPWCheck;
	private String userName;
	private String userBirthDate;
	private int userGender = 0; // man:1 / woman:2 / none:3
	private String userPhone;
	
	private int userIDChecker = 0; // unchecked:0 / overlapped:-1 / normal:1
	private int userPWChecker = 0;
	
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
		JLabel idLabel = new JLabel("ID *");
		idLabel.setBounds(Main.WIDTH/6, 200, 100, 30);
		idLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(idLabel);
		
		JTextField id = new JTextField(20);
		id.setBounds(Main.WIDTH/6-10, 230, 200, 30);
		panel.add(id);
		
		// id check button
		JButton idCheck = new JButton("ID 확인");
		idCheck.setBounds(Main.WIDTH/6+90, 200, 100, 30);
		idCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userID = id.getText();
				try {
					if(userID.isBlank()) {
						JOptionPane.showMessageDialog(null, "필수 정보입니다. 다시 입력해주십시오.");
						userID = null;
						id.setText(null);
					} else if(SignupProcess.isOverlapped(userID) == -1) {
						JOptionPane.showMessageDialog(null, "이미 존재하는 ID입니다. 다른 ID로 시도해주십시오.");
						userID = null;
						id.setText(null);
					} else if(SignupProcess.isOverlapped(userID) == 1) {
						if(!SignupProcess.isIDFormatted(userID)) {
							JOptionPane.showMessageDialog(null, "ID는 5자 이상, 20자 이하의 문자열이어야 합니다.");
						} else {
							JOptionPane.showMessageDialog(null, "사용가능한 ID입니다.");
							userIDChecker = 1;
							id.setEnabled(false);
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(idCheck);
		
		// password
		JLabel pwLabel = new JLabel("비밀번호 *");
		pwLabel.setBounds(Main.WIDTH/6, 270, 100, 30);
		pwLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(pwLabel);
		
		JPasswordField pw = new JPasswordField(20);
		pw.setBounds(Main.WIDTH/6-10, 300, 200, 30);
		panel.add(pw);
		
		// check password
		JLabel pwCheckLabel = new JLabel("비밀번호 재확인 *");
		pwCheckLabel.setBounds(Main.WIDTH/6, 340, 200, 30);
		pwCheckLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(pwCheckLabel);
		
		JPasswordField pwCheck = new JPasswordField(20);
		pwCheck.setBounds(Main.WIDTH/6-10, 370, 200, 30);
		panel.add(pwCheck);
		
		// 
		JButton pwEqual = new JButton("비밀번호 확인");
		pwEqual.setBounds(Main.WIDTH/6+90, 410, 100, 30);
		pwEqual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] tmp = pw.getPassword();
				userPW = new String(tmp);
				tmp = pwCheck.getPassword();
				userPWCheck = new String(tmp);
				if(userPW.isBlank() || userPWCheck.isBlank()) {
					JOptionPane.showMessageDialog(null, "필수 정보입니다. 다시 입력해주십시오.");
					pw.setText(null);
					pwCheck.setText(null);
				} else if(SignupProcess.isPasswordEqual(userPW, userPWCheck)) {
					if(!SignupProcess.isPWFormatted(userPW)) {
						JOptionPane.showMessageDialog(null, "비밀번호 형식이 틀렸습니다.\n비밀번호는 8자 이상, 20자 이하의 문자열로 문자 및 숫자, 특수문자 조합이 가능합니다.");
						pw.setText(null);
						pwCheck.setText(null);
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호가 정상적으로 설정되었습니다.");
						userPWChecker = 1;
						pw.setEnabled(false);
						pwCheck.setEnabled(false);
					}
				} else {
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다. 다시 시도해주십시오.");
					pw.setText(null);
					pwCheck.setText(null);
				}
			}
		});
		panel.add(pwEqual);
		
		// name
		JLabel nameLabel = new JLabel("이름 *");
		nameLabel.setBounds(4*Main.WIDTH/6, 200, 100, 30);
		nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(nameLabel);
		
		JTextField name = new JTextField(20);
		name.setBounds(4*Main.WIDTH/6-10, 230, 200, 30);
		panel.add(name);
		
		// birthday
		JLabel birthLabel = new JLabel("생년월일 * / 성별 *");
		birthLabel.setBounds(4*Main.WIDTH/6, 270, 120, 30);
		birthLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(birthLabel);
		
		JTextField year = new JTextField(8);
		year.setBounds(4*Main.WIDTH/6-10, 300, 60, 30);
		panel.add(year);
		
		JComboBox<Object> month = new JComboBox<Object>(monthList);
		month.setBounds(4*Main.WIDTH/6+50, 300, 70, 30);
		panel.add(month);
		
		JLabel monthLabel = new JLabel("월");
		monthLabel.setBounds(4*Main.WIDTH/6+120, 300, 30, 30);
		panel.add(monthLabel);
		
		JComboBox<Object> day = new JComboBox<Object>(dayList);
		day.setBounds(4*Main.WIDTH/6+150, 300, 70, 30);
		panel.add(day);
		
		JLabel dayLabel = new JLabel("일");
		dayLabel.setBounds(4*Main.WIDTH/6+220, 300, 30, 30);
		panel.add(dayLabel);
		
		// gender
		ButtonGroup group = new ButtonGroup();
		
		JRadioButton man = new JRadioButton("남자");
		man.setBounds(4*Main.WIDTH/6-10, 330, 70, 30);
		man.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					userGender = 1;
				} else if(e.getStateChange() == ItemEvent.DESELECTED) {
					userGender = 0;
				}
			}
		});
		group.add(man);
		panel.add(man);
		
		JRadioButton woman = new JRadioButton("여자");
		woman.setBounds(4*Main.WIDTH/6+50, 330, 70, 30);
		woman.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					userGender = 2;
				} else if(e.getStateChange() == ItemEvent.DESELECTED) {
					userGender = 0;
				}
			}
		});
		group.add(woman);
		panel.add(woman);
		
		JRadioButton uncheck = new JRadioButton("선택 안함");
		uncheck.setBounds(4*Main.WIDTH/6+110, 330, 110, 30);
		uncheck.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					userGender = 3;
				} else if(e.getStateChange() == ItemEvent.DESELECTED) {
					userGender = 0;
				}
			}
		});
		group.add(uncheck);
		panel.add(uncheck);
						
		// phone number
		JLabel phoneLabel = new JLabel("휴대폰 번호");
		phoneLabel.setBounds(4*Main.WIDTH/6, 370, 100, 30);
		phoneLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(phoneLabel);
		
		JTextField phone1 = new JTextField(20);
		phone1.setBounds(4*Main.WIDTH/6-10, 400, 50, 30);
		panel.add(phone1);
		
		JLabel phoneConnector1 = new JLabel("-");
		phoneConnector1.setBounds(4*Main.WIDTH/6+40, 400, 20, 30);
		phoneConnector1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(phoneConnector1);
		
		JTextField phone2 = new JTextField(20);
		phone2.setBounds(4*Main.WIDTH/6+60, 400, 50, 30);
		panel.add(phone2);
		
		JLabel phoneConnector2 = new JLabel("-");
		phoneConnector2.setBounds(4*Main.WIDTH/6+110, 400, 20, 30);
		phoneConnector2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(phoneConnector2);
		
		JTextField phone3 = new JTextField(20);
		phone3.setBounds(4*Main.WIDTH/6+130, 400, 50, 30);
		panel.add(phone3);
		
		// option warning
		JLabel required = new JLabel("* 표시된 항목은 필수 입력 항목입니다.");
		required.setForeground(Color.DARK_GRAY);
		required.setBounds(Main.WIDTH/6-10, 450, 200, 30);
		panel.add(required);
			
		// submit
		JButton submit = new JButton("제출");
		submit.setBounds(Main.WIDTH/2-80, 500, 160, 60);
		submit.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				userName = name.getText();
				userBirthDate = year.getText() + "-" + month.getSelectedItem().toString() + "-" + day.getSelectedItem().toString();
				userPhone = phone1.getText() + "-" + phone2.getText() + "-" + phone3.getText();
				
				if(isSuccess()) {
					SignupProcess.signUp(userID, userPW, userName, userBirthDate, userGender, userPhone);
					int check = JOptionPane.showConfirmDialog(null, "가입이 완료되었습니다. 로그인 창으로 돌아가시겠습니까?");
					if(check == 0) {
						new Main();
						frame.setVisible(false);
					} else if(check == 1) {
						System.exit(0);
					}
				}
			}
		});
		panel.add(submit);
		
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
	
	private boolean isSuccess() {
		
		if(userIDChecker != 1|| userPWChecker != 1 || userGender == 0) {
			JOptionPane.showMessageDialog(null, "필수 항목이 채워지지 않았습니다. 확인 후 제출해주세요.");
			return false;
		}
		
		if(userIDChecker == 0) {
			JOptionPane.showMessageDialog(null, "ID 확인이 필요합니다. 확인 후 제출해주세요.");
			return false;
		}
		
		if(userPWChecker == 0) {
			JOptionPane.showMessageDialog(null, "비밀번호 확인이 필요합니다. 확인 후 제출해주세요.");
			return false;
		}
		
		if(!SignupProcess.isNameFormatted(userName)) {
			JOptionPane.showMessageDialog(null, "이름이 형식에 맞지 않습니다. 다시 시도해주십시오.");
			return false;
		}
		
		if(!SignupProcess.isBirthDateFormatted(userBirthDate)) {
			JOptionPane.showMessageDialog(null, "생년월일이 형식에 맞지 않습니다. 다시 시도해주십시오.");
			return false;
		}
		
		if(!SignupProcess.isPhoneFormatted(userPhone)) {
			JOptionPane.showMessageDialog(null, "휴대폰 번호가 형식에 맞지 않습니다. 다시 시도해주십시오.");
			return false;
		}	
		
		return true;
	}
	
	
	
	
}
