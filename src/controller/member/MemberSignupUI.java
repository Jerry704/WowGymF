package controller.member;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import model.Member;
import service.impl.MemberServiceImpl;

public class MemberSignupUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JTextField password;
	private JTextField name;
	private JTextField email;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberSignupUI frame = new MemberSignupUI();
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
	public MemberSignupUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("會員註冊");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(180, 49, 160, 44);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("姓名");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(50, 228, 68, 38);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("帳號");
		lblNewLabel_1_1.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(50, 132, 68, 38);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("密碼");
		lblNewLabel_1_2.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(50, 180, 68, 38);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("電子信箱");
		lblNewLabel_1_3.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(50, 276, 68, 38);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblUsername = new JLabel("");
		lblUsername.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		lblUsername.setBounds(305, 141, 189, 23);
		contentPane.add(lblUsername);
		
		username = new JTextField();
		username.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String uiUsername = username.getText();
				Member m1 = new MemberServiceImpl().queryByUsername(uiUsername);
				if (m1 == null) {
					lblUsername.setText("帳號可用");
				} else {
					lblUsername.setText("帳號不可用");
				}
			}
		});
		username.setBounds(128, 144, 133, 21);
		contentPane.add(username);
		username.setColumns(10);

		password = new JTextField();
		password.setColumns(10);
		password.setBounds(128, 192, 133, 21);
		contentPane.add(password);

		name = new JTextField();
		name.setColumns(10);
		name.setBounds(128, 240, 133, 21);
		contentPane.add(name);

		email = new JTextField();
		email.setColumns(10);
		email.setBounds(128, 288, 133, 21);
		contentPane.add(email);
		
		JLabel lblRegister = new JLabel("");
		lblRegister.setBounds(214, 343, 139, 36);
		contentPane.add(lblRegister);

		JButton btnNewButton = new JButton("註冊");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String uiUsername = username.getText();
				Member m1 = new MemberServiceImpl().queryByUsername(uiUsername);
				if (m1 == null) {
					String uiName = name.getText();
					String uiPassword = password.getText();
					String uiEmail = email.getText();
					Member m2 = new Member(uiName,uiUsername,uiPassword,uiEmail);
					new MemberServiceImpl().addMember(m2);
					JOptionPane.showMessageDialog(
							 null,
			                    "註冊成功，請從新登入",
			                    " ",
			                    JOptionPane.WARNING_MESSAGE);
					MemberLoginUI frame = new MemberLoginUI();
					frame.setVisible(true);
					dispose();
				}else {
					lblRegister.setText("資料有誤，註冊失敗");			
				}
				
			}
		});
		btnNewButton.setBounds(238, 389, 85, 23);
		contentPane.add(btnNewButton);
	}

}
