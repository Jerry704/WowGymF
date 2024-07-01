package controller.member;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.HomeUI;
import model.Member;
import service.impl.MemberServiceImpl;
import tool.Tool;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class MemberLoginUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textAccount;
	private JTextField textPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberLoginUI frame = new MemberLoginUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MemberLoginUI() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 612);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("會員登入");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(180, 49, 160, 44);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("上一頁");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HomeUI frame = new HomeUI();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(418, 49, 85, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("帳號:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(91, 198, 63, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("密碼:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(91, 291, 63, 30);
		contentPane.add(lblNewLabel_1_1);
		
		textAccount = new JTextField();
		textAccount.setBounds(180, 201, 179, 25);
		contentPane.add(textAccount);
		textAccount.setColumns(10);
		
		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(180, 296, 179, 25);
		contentPane.add(textPassword);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(157, 358, 224, 44);
		contentPane.add(lblNewLabel_2);
		
		JButton btnComfirm = new JButton("登入");
		btnComfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String uiUsername = textAccount.getText();
				String uiPassword = textPassword.getText();
				Member m = new MemberServiceImpl().queryMember(uiUsername,uiPassword);
				if(m != null) {
					Tool.saveFile("member.txt",m);
					MemberHomeUI frame = new MemberHomeUI();
					frame.setVisible(true);
					dispose();
				}else {
					lblNewLabel_2.setText("帳號不存在或密碼有誤");
				}
				
			}
		});
		btnComfirm.setBounds(121, 429, 101, 44);
		contentPane.add(btnComfirm);
		
		JButton btnSignUp = new JButton("前往註冊");
		btnSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MemberSignupUI frame = new MemberSignupUI();
				frame.setVisible(true);
				dispose();
			}
		});
		btnSignUp.setBounds(311, 429, 101, 44);
		contentPane.add(btnSignUp);
		
		
	}
}
