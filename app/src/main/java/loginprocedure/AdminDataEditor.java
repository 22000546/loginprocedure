package loginprocedure;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import loginprocedure.utils.EditProcess;
import loginprocedure.utils.SignupProcess;

public class AdminDataEditor implements TableModelListener {
	
	JFrame frame;
	String[] columnType = {"index", "id", "password", "name", "birthdate", "gender", "phone_number"};
	static Object[][] rawdata;
	static Object[][] data;

	public AdminDataEditor() {
		frame = new JFrame();
		frame.setBounds(100, 100, Main.WIDTH, Main.HEIGHT);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);		
		initialize();
	}
	
	private void initialize() {
		
		JPanel editorPanel = new JPanel();
		editorPanel.setBounds(0, 0, Main.WIDTH, Main.HEIGHT);
		editorPanel.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(editorPanel);
		editorPanel.setLayout(null);
		
		rawdata = new Object[100][7];
		data = new Object[100][7];
		try {
			loadAllData(rawdata);
			loadAllData(data);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JTable table = new JTable(data, columnType);
		JScrollPane scrollPane = new JScrollPane(table);
		
        table.setFillsViewportHeight(true);
		table.getModel().addTableModelListener(this);
		scrollPane.setBounds(100, 100, Main.WIDTH-200, Main.HEIGHT-300);
        frame.add(scrollPane);
        
        // Forced out
        JButton out = new JButton("강제 탈퇴");
        out.setBounds(Main.WIDTH/2-80, 550, 160, 70);
        out.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int row = table.getSelectedRow();
        		if(row == -1) {
        			JOptionPane.showMessageDialog(null, "선택된 데이터가 없습니다.");
        		} else {
            		String id = (String) table.getValueAt(row, 1);
        			int check = JOptionPane.showConfirmDialog(null, "정말로 " + id + "님을 강제로 탈퇴시키겠습니까?\n데이터는 복구되지 않습니다.");
                	if(check == JOptionPane.YES_OPTION) {
                		try {
    						if(EditProcess.signOut(id)) {
    							JOptionPane.showMessageDialog(null, "탈퇴 처리 되었습니다.");
    						} else {
    							JOptionPane.showMessageDialog(null, "탈퇴 처리에 문제가 생겼습니다.\n다시 시도해주십시오.");
    						}
    					} catch (HeadlessException | SQLException e1) {
    						e1.printStackTrace();
    					}
                	}
        		}
            	frame.dispose();
            	new AdminDataEditor();
        	}
        });
        editorPanel.add(out);
        
		// Back button
		JButton back = new JButton();
		back.setBounds(10, 10, 50, 50);
		back.setIcon(new ImageIcon("./images/left-arrow.png"));
		back.setBorderPainted(false);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Admin();
				frame.setVisible(false);
			}
		});
		editorPanel.add(back);
				
	}
	
	private static void loadAllData(Object[][] data) throws SQLException {
		
		String SQL = "SELECT * FROM user_info";
		
		ResultSet rs = Main.getStatement().executeQuery(SQL);
		Main.setResultSet(rs);
		
		int i = 0;
		
		while(rs.next()) {
			
			data[i][0] = i+1;
			data[i][1] = Main.getResultSet().getString("id");
			data[i][2] = Main.getResultSet().getString("password");
			data[i][3] = Main.getResultSet().getString("name");
			data[i][4] = Main.getResultSet().getString("birthdate");
			data[i][5] = Main.getResultSet().getInt("gender");
			data[i][6] = Main.getResultSet().getString("phone_number");
			
			i++;
			
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
        int column = e.getColumn();
        
        if(column == 1) {
        	JOptionPane.showMessageDialog(null, "ID는 변경할 수 없습니다.");
        	frame.dispose();
        	new AdminDataEditor();
        }
        if(column == 2) {
        	
        	TableModel model = (TableModel) e.getSource();
        	String id = (String) model.getValueAt(row, 1);
        	String pw = (String) model.getValueAt(row, column);
        	int check = JOptionPane.showConfirmDialog(null, "정말로 " + id + "님의 비밀번호를 바꾸시겠습니까? ");
        	if(check == JOptionPane.YES_OPTION) {
        		if(SignupProcess.isPWFormatted(pw)) {
    				try {
    					if(EditProcess.editPassword(id, pw)) {
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
        	frame.dispose();
			new AdminDataEditor();
			
        }
        if(column == 3) {
        	
        	TableModel model = (TableModel) e.getSource();
        	String id = (String) model.getValueAt(row, 1);
        	String name = (String) model.getValueAt(row, column);
        	int check = JOptionPane.showConfirmDialog(null, "정말로 " + id + "님의 이름을 바꾸시겠습니까? ");
        	if(check == JOptionPane.YES_OPTION) {
        		try {
					if(EditProcess.editName(id, name)) {
						JOptionPane.showMessageDialog(null, "수정되었습니다.");
					} else {
						JOptionPane.showMessageDialog(null, "문제가 발생하였습니다. 다시 시도하십시오.");
					}
				} catch (HeadlessException | SQLException e1) {
					e1.printStackTrace();
				}
        	}
        	frame.dispose();
			new AdminDataEditor();
			
        }
        
        if(column == 4) {
        	JOptionPane.showMessageDialog(null, "생년월일은 변경할 수 없습니다.");
        	frame.dispose();
        	new AdminDataEditor();
        }
        
        if(column == 5) {
        	
        	TableModel model = (TableModel) e.getSource();
        	String id = (String) model.getValueAt(row, 1);
        	String tmp = (String) model.getValueAt(row, column);
        	int gender = Integer.parseInt(tmp);
        	int check = JOptionPane.showConfirmDialog(null, "정말로 " + id + "님의 성별을 바꾸시겠습니까? ");
        	if(check == JOptionPane.YES_OPTION) {
        		if(gender == 1 || gender == 2 || gender == 3) {
        			try {
    					if(EditProcess.editGender(id, gender)) {
    						JOptionPane.showMessageDialog(null, "수정되었습니다.");
    					} else {
    						JOptionPane.showMessageDialog(null, "문제가 발생하였습니다. 다시 시도하십시오.");
    					}
    				} catch (SQLException e1) {
    					e1.printStackTrace();
    				}
        		} else {
        			JOptionPane.showMessageDialog(null, "성별은 남자(1), 여자(2), 선택 안 함(3)의 숫자로 입력해주십시오.");
        		}
        	}
        	
        	frame.dispose();
			new AdminDataEditor();
        }
        
        if(column == 6) {
        	
        	TableModel model = (TableModel) e.getSource();
        	String id = (String) model.getValueAt(row, 1);
        	String num = (String) model.getValueAt(row, column);
        	int check = JOptionPane.showConfirmDialog(null, "정말로 " + id + "님의 휴대폰 번호를 바꾸시겠습니까? ");
        	if(check == JOptionPane.YES_OPTION) {
        		if(SignupProcess.isBirthDateFormatted(num)) {
					try {
						if(EditProcess.editPhone(id, num)) {
							JOptionPane.showMessageDialog(null, "수정되었습니다.");
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
        	
        	frame.dispose();
			new AdminDataEditor();
        }
		
	}

}
