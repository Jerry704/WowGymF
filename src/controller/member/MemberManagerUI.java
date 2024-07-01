package controller.member;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Member;
import service.impl.MemberServiceImpl;
import tool.Tool;


public class MemberManagerUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField uiname;
	private JTextField uipassword;
	private JTextField uiEmail;
	private JTextField uiId;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberManagerUI frame = new MemberManagerUI();
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
	public MemberManagerUI() {
		MemberServiceImpl msi = new MemberServiceImpl();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 669, 595);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MemberManagerUI");
		lblNewLabel.setBounds(245, 0, 113, 52);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 72, 546, 132);
		contentPane.add(scrollPane);
		
		JTextArea show = new JTextArea();
		show.setEditable(false);
		scrollPane.setViewportView(show);
		
		Member m  = (Member) Tool.readFile("member.txt");
		StringBuilder sb = new StringBuilder();
		sb.append("MemberId=\t"+m.getId()+"\n");
		sb.append("Name=\t"+m.getName()+"\n");
		sb.append("Username=\t"+m.getUsername()+"\n");
		sb.append("Passowrd=\t"+m.getPassword()+"\n");
		sb.append("Email=\t"+m.getEmail()+"\n");
		
		show.setText(sb.toString());
		
		JLabel lblNewLabel_1 = new JLabel("姓名:");
		lblNewLabel_1.setBounds(49, 237, 46, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("密碼:");
		lblNewLabel_1_1.setBounds(305, 237, 46, 15);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Email:");
		lblNewLabel_1_2.setBounds(49, 295, 46, 15);
		contentPane.add(lblNewLabel_1_2);
		
		uiname = new JTextField();
		uiname.setBounds(105, 234, 166, 21);
		contentPane.add(uiname);
		uiname.setColumns(10);
		
		uipassword = new JTextField();
		uipassword.setBounds(351, 234, 166, 21);
		uipassword.setColumns(10);
		contentPane.add(uipassword);
		
		uiEmail = new JTextField();
		uiEmail.setBounds(105, 292, 166, 21);
		uiEmail.setColumns(10);
		contentPane.add(uiEmail);
		
		JButton btnUpdate = new JButton("修改");
		btnUpdate.setBounds(366, 291, 85, 23);
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				show.setText(""); //清空顯示窗
				sb.delete(0, sb.length()); //清空sb內容
				m.setName(uiname.getText()); //設置新的會員資料
				m.setPassword(uipassword.getText());
				m.setEmail(uiEmail.getText());
				
				//更改sql內容
				msi.updateMember(m.getId(), m.getName(),m.getPassword() ,m.getEmail() );
				
				//sb新內容
				sb.append("MemberId=\t"+m.getId()+"\n");
				sb.append("Name=\t"+m.getName()+"\n");
				sb.append("Username=\t"+m.getUsername()+"\n");
				sb.append("Passowrd=\t"+m.getPassword()+"\n");
				sb.append("Email=\t"+m.getEmail()+"\n");
				show.setText(sb.toString());
				Tool.saveFile("member.txt", m);
			}
		});
		contentPane.add(btnUpdate);
		
		JButton btnBackPage = new JButton("回上一頁");
		btnBackPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MemberHomeUI frame = new MemberHomeUI();
				frame.setVisible(true);
				dispose();
			}
		});
		btnBackPage.setBounds(510, 15, 85, 23);
		contentPane.add(btnBackPage);
	
				
	}
}
