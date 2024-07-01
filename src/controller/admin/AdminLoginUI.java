package controller.admin;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.HomeUI;
import controller.trainer.TrainerHomeUI;
import model.Admin;
import model.Trainer;
import service.impl.AdminServiceImpl;
import service.impl.TrainerServiceImpl;
import tool.Tool;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminLoginUI extends JFrame {

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
					AdminLoginUI frame = new AdminLoginUI();
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
	public AdminLoginUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 595);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("管理員登入");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(145, 47, 144, 45);
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
		btnNewButton.setBounds(405, 58, 85, 23);
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
				Admin a = new AdminServiceImpl().queryAdmin(uiUsername,uiPassword);
				if(a != null) {
					Tool.saveFile("admin.txt",a);
					AdminHomeUI frame = new AdminHomeUI();
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
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminSignupUI frame = new AdminSignupUI();
				frame.setVisible(true);
				dispose();
			}
		});
		btnSignUp.setBounds(311, 429, 101, 44);
		contentPane.add(btnSignUp);
	}

}
