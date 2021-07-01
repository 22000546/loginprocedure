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

import loginprocedure.utils.EditProcess;
import loginprocedure.utils.SignupProcess;

public class UserDataEditor extends JFrame {
	
	JFrame frame;
	String input;
	
	String pw = "";
	
	public UserDataEditor() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, Main.WIDTH, Main.HEIGHT);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		initialize();
	}
	
	private void initialize() {
		
		// set values
		
		for(int i = 0; i < User.getPassword().length(); i ++) {
			pw += "*";
		}
		
		String gender = "";
		if(User.getGender() == 1)
			gender = "남자";
		else if(User.getGender() == 2)
			gender = "여자";
		else if(User.getGender() == 3)
			gender = "선택 안 함";
		
		String phone = "";
		if(User.getPhone() == null)
			phone = "입력 안 함";
		else
			phone = User.getPhone();
		
		JPanel editorPanel = new JPanel();
		editorPanel.setBounds(0, 0, Main.WIDTH, Main.HEIGHT);
		editorPanel.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(editorPanel);
		editorPanel.setLayout(null);
		
		JLabel title = new JLabel("현재 나의 정보");
		title.setForeground(Color.WHITE);
		title.setBounds(Main.WIDTH/2-150, 50, 300, 50);
		title.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		editorPanel.add(title);
		
		JLabel currentID = new JLabel("현재 ID : " + User.getID());
		currentID.setForeground(Color.WHITE);
		currentID.setBounds(Main.WIDTH/2-150, 150, 300, 50);
		currentID.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		editorPanel.add(currentID);
		
		JLabel currentPW = new JLabel("현재 비밀번호 : " + pw);
		currentPW.setForeground(Color.WHITE);
		currentPW.setBounds(Main.WIDTH/2-150, 230, 300, 50);
		currentPW.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		editorPanel.add(currentPW);
		
		JLabel currentName = new JLabel("현재 이름 : " + User.getUserName());
		currentName.setForeground(Color.WHITE);
		currentName.setBounds(Main.WIDTH/2-150, 310, 300, 50);
		currentName.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		editorPanel.add(currentName);
		
		JLabel currentBirthdate = new JLabel("현재 생년월일 : " + User.getBirthdate());
		currentBirthdate.setForeground(Color.WHITE);
		currentBirthdate.setBounds(Main.WIDTH/2-150, 390, 300, 50);
		currentBirthdate.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		editorPanel.add(currentBirthdate);
		
		JLabel currentGender = new JLabel("현재 성별 : " + gender);
		currentGender.setForeground(Color.WHITE);
		currentGender.setBounds(Main.WIDTH/2-150, 470, 300, 50);
		currentGender.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		editorPanel.add(currentGender);
		
		JLabel currentPhoneNum = new JLabel("현재 휴대폰 번호 : " + phone);
		currentPhoneNum.setForeground(Color.WHITE);
		currentPhoneNum.setBounds(Main.WIDTH/2-150, 550, 300, 50);
		currentPhoneNum.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		editorPanel.add(currentPhoneNum);
		
		// Edit button
		JButton editPW = new JButton("수정");
		editPW.setBounds(Main.WIDTH/2+170, 230, 80, 50);
		editPW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input = JOptionPane.showInputDialog("변경된 비밀번호를 입력하십시오.");
				if(SignupProcess.isPWFormatted(input)) {
					try {
						if(EditProcess.editPassword(User.getID(), input)) {
							JOptionPane.showMessageDialog(null, "수정되었습니다.");
							frame.dispose();
							new User();
							new UserDataEditor();
						} else {
							JOptionPane.showMessageDialog(null, "문제가 발생하였습니다. 다시 시도하십시오.");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "비밀번호 형식이 틀렸습니다.\n비밀번호는 8자 이상, 20자 이하의 문자열로 문자 및 숫자, 특수문자 조합이 가능합니다.");
				}
			}
		});
		editorPanel.add(editPW);
		
		JButton editName = new JButton("수정");
		editName.setBounds(Main.WIDTH/2+170, 310, 80, 50);
		editName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input = JOptionPane.showInputDialog("이름을 입력하십시오.");
				try {
					if(EditProcess.editName(User.getID(), input)) {
						JOptionPane.showMessageDialog(null, "수정되었습니다.");
						frame.dispose();
						new User();
						new UserDataEditor();
					} else {
						JOptionPane.showMessageDialog(null, "문제가 발생하였습니다. 다시 시도하십시오.");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		editorPanel.add(editName);
		
		JButton editGender = new JButton("수정");
		editGender.setBounds(Main.WIDTH/2+170, 470, 80, 50);
		editGender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] options = {"선택 안 함", "여자", "남자"};
				int genderInput = JOptionPane.showOptionDialog(null, "성별을 선택해주세요.", null, 
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, e);
				if(genderInput == 2)
					genderInput = 1;
				else if(genderInput == 1)
					genderInput = 2;
				else if(genderInput == 0)
					genderInput = 3;
				try {
					if(EditProcess.editGender(User.getID(), genderInput)) {
						JOptionPane.showMessageDialog(null, "수정되었습니다.");
						frame.dispose();
						new User();
						new UserDataEditor();
					} else {
						JOptionPane.showMessageDialog(null, "문제가 발생하였습니다. 다시 시도하십시오.");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		editorPanel.add(editGender);
		
		JButton editPhone = new JButton("수정");
		editPhone.setBounds(Main.WIDTH/2+170, 550, 80, 50);
		editPhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input = JOptionPane.showInputDialog("휴대폰 번호를 입력하십시오.");
				if(SignupProcess.isBirthDateFormatted(input)) {
					try {
						if(EditProcess.editPhone(User.getID(), input)) {
							JOptionPane.showMessageDialog(null, "수정되었습니다.");
							frame.dispose();
							new User();
							new UserDataEditor();
						} else {
							JOptionPane.showMessageDialog(null, "문제가 발생하였습니다. 다시 시도하십시오.");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "휴대폰 번호가 형식에 맞지 않습니다. 다시 시도해주십시오.");
				}
			}
		});
		editorPanel.add(editPhone);
		
		// Back button
		JButton back = new JButton();
		back.setBounds(10, 10, 50, 50);
		back.setIcon(new ImageIcon("./images/left-arrow.png"));
		back.setBorderPainted(false);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new User();
				frame.setVisible(false);
			}
		});
		editorPanel.add(back);
		
	}

}
