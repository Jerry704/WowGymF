package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.admin.AdminLoginUI;
import controller.member.MemberLoginUI;
import controller.trainer.TrainerLoginUI;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;

public class HomeUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeUI frame = new HomeUI();
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
	public HomeUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 476, 594);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("歡迎光臨WOWGYM");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(145, 45, 161, 37);
		contentPane.add(lblNewLabel);
		
		JButton btnMember = new JButton("會員登入/註冊");
		btnMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MemberLoginUI frame = new MemberLoginUI();
				frame.setVisible(true);
				dispose();
			}
		});
		btnMember.setBounds(161, 337, 145, 44);
		contentPane.add(btnMember);
		
		JButton btnTrainer = new JButton("教練登入/註冊");
		btnTrainer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TrainerLoginUI frame = new TrainerLoginUI();
				frame.setVisible(true);
				dispose();
			}
		});
		btnTrainer.setBounds(161, 406, 145, 44);
		contentPane.add(btnTrainer);
		
		JButton btnAdmin = new JButton("管理員登入/註冊");
		btnAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminLoginUI frame = new AdminLoginUI();
				frame.setVisible(true);
				dispose();
			}
		});
		btnAdmin.setBounds(161, 475, 145, 44);
		contentPane.add(btnAdmin);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(HomeUI.class.getResource("/img/logoIcon.png")));
		lblNewLabel_1.setBounds(145, 116, 161, 161);
		contentPane.add(lblNewLabel_1);
		
		
	}
}
