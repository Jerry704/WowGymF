package controller.admin;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.HomeUI;
import controller.member.MemberManagerUI;
import model.Admin;
import model.Lesson;
import model.Member;
import model.Order;
import model.Trainer;
import service.impl.AdminServiceImpl;
import tool.Clock;
import tool.Tool;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class AdminHomeUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTime;
	private JButton btnQueryAllLesson;
	private List<Lesson> list;
	private JTable adminTable;
	private JTable memberTable;
	private JTable trainerTable;
	private JTable orderTable;
	private JTable lessonTable;
	private JButton btnQueryAllOrder;
	private JButton btnUpdate;
	private JButton btnQueryAllMember;
	private JButton btnQueryAllTrainer;
	private JButton btnQueryAllAdmin;
	private JButton btnHomepage;
	private JTextField tfId;
	private JTextField tfName;
	private JTextField tfUsername;
	private JTextField tfPassword;
	private JTextField tfEmail;
	private String index ="0";
	DefaultTableModel memberModel;
	DefaultTableModel trainerModel ;
	DefaultTableModel adminModel;
	
	
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminHomeUI frame = new AdminHomeUI();
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
	public AdminHomeUI() {
		
	
		lblTime = new JLabel("");
		lblTime.setBounds(650, 555, 121, 21);
		
		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				super.windowActivated(e);
				new Thread(new Clock() {
					@Override
					public void run() {
						while (true) {
							lblTime.setText(Tool.getTime());
							try {	Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}).start();
			}
		});
		
		AdminServiceImpl asi = new AdminServiceImpl();
		Admin ad = (Admin) Tool.readFile("admin.txt");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 623);
		contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);   
                ImageIcon icon = new ImageIcon("src//img//logof4.png");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.add(lblTime);
		
	
		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

		JPanel panel = new JPanel();
		panel.setBounds(150, 134, 621, 196);
		contentPane.add(panel);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(10, 10, 74, 24);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("姓名");
		lblNewLabel_1_1.setBounds(10, 44, 74, 24);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("帳號");
		lblNewLabel_1_2.setBounds(10, 78, 74, 24);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("密碼");
		lblNewLabel_1_2_1.setBounds(10, 112, 74, 24);
		panel.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Email");
		lblNewLabel_1_2_2.setBounds(10, 146, 74, 24);
		panel.add(lblNewLabel_1_2_2);
		
		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBounds(81, 12, 96, 21);
		panel.add(tfId);
		tfId.setColumns(10);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(81, 44, 96, 21);
		panel.add(tfName);
		
		tfUsername = new JTextField();
		tfUsername.setEditable(false);
		tfUsername.setColumns(10);
		tfUsername.setBounds(81, 80, 96, 21);
		panel.add(tfUsername);
		
		tfPassword = new JTextField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(81, 114, 96, 21);
		panel.add(tfPassword);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(81, 146, 233, 21);
		panel.add(tfEmail);
		
		
		
		JLabel lblNewLabel = new JLabel("管理員主頁");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(200, 36, 264, 49);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		lblNewLabel.setText("Hi "+ad.getName()+" 管理員 今天好嗎");
		
		//課程查詢--------------------------
        // Column headers
        String[] lessonColumns = {"Lesson id", "Trainer id","lessonName","starttime","price"};
        //抓出課程list集合
        list = new ArrayList(asi.queryAllLesson());
        //建立Object[]裝Lesson物件(Jtable只接受Object[][])
        List<Object[]> oArr = new ArrayList<>();
        //遍歷物件並轉型
        for (Lesson a : list) {
            Object[] arr = new Object[]{a.getLesson_id(), a.getTrainer_id(), a.getName(),a.getTime(),a.getPrice()};
            oArr.add(arr);
        }

        
        Object[][] dataArray = oArr.toArray(new Object[0][0]);

        // Create a DefaultTableModel and set data and column names
        DefaultTableModel lessonModel = new DefaultTableModel(dataArray, lessonColumns);
        
        lessonTable = new JTable(lessonModel);
        memberTable = new JTable();
        trainerTable = new JTable();
        adminTable = new JTable();
        orderTable = new JTable();
		
		JScrollPane scrollPane = new JScrollPane(lessonTable);
		scrollPane.setSize(621, 221);
		scrollPane.setLocation(150, 340);
		contentPane.add(scrollPane);
		
		
		
		
		//課程查詢-------------------------
		
		btnQueryAllLesson = new JButton("查詢所有課程");
		btnQueryAllLesson.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 index ="queryAllLesson";
				 list = new ArrayList(asi.queryAllLesson());
				 lessonTable.setModel(lessonModel);
				 scrollPane.setViewportView(lessonTable);
				 contentPane.add(scrollPane);
			}
		});
		btnQueryAllLesson.setBounds(10, 179, 120, 31);
		contentPane.add(btnQueryAllLesson);
		
		btnQueryAllOrder = new JButton("查看所有訂單");
		btnQueryAllOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				index ="queryAllOrder";
				//訂單開頭
		        String[] orderColumns = {"order id","trainer id", "Memrber id","ordertime","price","lessonName"};
		        //抓出類型list集合
		        List<Order> ol = new ArrayList(asi.queryAllOrder());
		        //建立Object[]裝Lesson物件(Jtable只接受Object[][])
		        List<Object[]> oArr = new ArrayList<>();
		        //遍歷物件並轉型
		        for (Order a : ol) {
		            Object[] arr = new Object[]{a.getOrder_id(), a.getTrainer_id(),a.getMember_id(), a.getTime(),a.getPrice(),a.getLessonName()};
		            oArr.add(arr);
		        }

		        Object[][] dataArray = oArr.toArray(new Object[0][0]);
		        DefaultTableModel orderModel = new DefaultTableModel(dataArray, orderColumns);
		        
		        orderTable.setModel(orderModel);
		        scrollPane.setViewportView(orderTable);
		        contentPane.add(scrollPane);
			}
		});
		btnQueryAllOrder.setBounds(10, 138, 120, 31);
		contentPane.add(btnQueryAllOrder);
		
		//MyJPanel mp = new MyJPanel("memberJPanel");
		
		btnQueryAllMember = new JButton("查詢所有會員");
		btnQueryAllMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//MemberJFrame mf =new MemberJFrame();
				//contentPane.add(mf.panel);
				contentPane.repaint();
				contentPane.revalidate();
				
				
				//訂單開頭
		        String[] memberColumns = {"member id","name", "username","password","email"};
		        //抓出類型list集合 List<Member> queryAllMember()
		        List<Member> ol = new ArrayList(asi.queryAllMember());
		        //建立Object[]裝Lesson物件(Jtable只接受Object[][])
		        List<Object[]> oArr = new ArrayList<>();
		        //遍歷物件並轉型
		        for (Member a : ol) {
		            Object[] arr = new Object[]{a.getId(), a.getName(),a.getUsername(),a.getPassword(), a.getEmail()};
		            oArr.add(arr);
		        }

		        Object[][] dataArray = oArr.toArray(new Object[0][0]);
		        memberModel = new DefaultTableModel(dataArray, memberColumns);
		        memberTable.setModel(memberModel);
		        scrollPane.setViewportView(memberTable);
		        contentPane.add(scrollPane);
		       
			}
		});
		btnQueryAllMember.setBounds(10, 220, 120, 31);
		contentPane.add(btnQueryAllMember);
		
		btnQueryAllTrainer = new JButton("查詢所有教練");
		btnQueryAllTrainer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				  	String[] trainerColumns = {"trainer id","name", "username","password","email"};
			        //抓出類型list集合 
			        List<Trainer> ol = new ArrayList(asi.queryAllTrainer());
			        List<Object[]> oArr = new ArrayList<>();
			        //遍歷物件並轉型
			        for (Trainer a : ol) {
			            Object[] arr = new Object[]{a.getId(), a.getName(),a.getUsername(),a.getPassword(), a.getEmail()};
			            oArr.add(arr);
			        }

			        Object[][] dataArray = oArr.toArray(new Object[0][0]);
			        trainerModel = new DefaultTableModel(dataArray, trainerColumns);
			        trainerTable.setModel(trainerModel);
			        scrollPane.setViewportView(trainerTable);
			        contentPane.add(scrollPane);
				
			}
		});
		btnQueryAllTrainer.setBounds(10, 261, 120, 31);
		contentPane.add(btnQueryAllTrainer);
		
		btnQueryAllAdmin = new JButton("查詢所有管理員");
		btnQueryAllAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] adminColumns = {"admin id","name", "username","password","email"};
		        //抓出類型list集合 
		        List<Admin> ol = new ArrayList(asi.queryAllAdmin());
		        List<Object[]> oArr = new ArrayList<>();
		        //遍歷物件並轉型
		        for (Admin a : ol) {
		            Object[] arr = new Object[]{a.getId(), a.getName(),a.getUsername(),a.getPassword(), a.getEmail()};
		            oArr.add(arr);
		        }

		        Object[][] dataArray = oArr.toArray(new Object[0][0]);
		        adminModel = new DefaultTableModel(dataArray, adminColumns);
		        adminTable.setModel(adminModel);
		        scrollPane.setViewportView(adminTable);
		        contentPane.add(scrollPane);
			}
		});
		btnQueryAllAdmin.setBounds(10, 302, 120, 31);
		contentPane.add(btnQueryAllAdmin);
		
//		btnUpdate = new JButton("修改個人資料");
//		btnUpdate.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				AdminManagerUI frame = new AdminManagerUI();
//				frame.setVisible(true);
//				dispose();
//			}
//		});
//		btnUpdate.setBounds(650, 79, 110, 32);
//		contentPane.add(btnUpdate);

		btnHomepage = new JButton("回首頁");
		btnHomepage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HomeUI frame = new HomeUI();
				frame.setVisible(true);
				dispose();
			}
		});
		btnHomepage.setBounds(650, 26, 110, 32);
		contentPane.add(btnHomepage);
		
		//tableMouseListener-----------------------
		lessonTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				index = "lessonTable";
			}
		});
		
		orderTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				index = "orderTable";
			}
		});
		
		adminTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	                index = "adminTable";
	                	  int selectedRow = adminTable.getSelectedRow();
							if (selectedRow != -1) {
								Integer id =(Integer) adminModel.getValueAt(selectedRow, 0);
								tfId.setText(Integer.toString(id))  ;
								tfName.setText((String)adminModel.getValueAt(selectedRow, 1))  ;
								tfUsername.setText((String)adminModel.getValueAt(selectedRow, 2))  ;
								tfPassword.setText((String)adminModel.getValueAt(selectedRow, 3))  ;
								tfEmail.setText((String)adminModel.getValueAt(selectedRow, 4))  ;
							}
			}
		});
		
		trainerTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	                index = "trainerTable";
	                	  int selectedRow = trainerTable.getSelectedRow();
							if (selectedRow != -1) {
								Integer id =(Integer) trainerModel.getValueAt(selectedRow, 0);
								tfId.setText(Integer.toString(id))  ;
								tfName.setText((String)trainerModel.getValueAt(selectedRow, 1))  ;
								tfUsername.setText((String)trainerModel.getValueAt(selectedRow, 2))  ;
								tfPassword.setText((String)trainerModel.getValueAt(selectedRow, 3))  ;
								tfEmail.setText((String)trainerModel.getValueAt(selectedRow, 4))  ;
							}
			}
		});
		
		memberTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	                index = "memberTable";
	                	  int selectedRow = memberTable.getSelectedRow();
							if (selectedRow != -1) {
								Integer id =(Integer) memberModel.getValueAt(selectedRow, 0);
								tfId.setText(Integer.toString(id))  ;
								tfName.setText((String)memberModel.getValueAt(selectedRow, 1))  ;
								tfUsername.setText((String)memberModel.getValueAt(selectedRow, 2))  ;
								tfPassword.setText((String)memberModel.getValueAt(selectedRow, 3))  ;
								tfEmail.setText((String)memberModel.getValueAt(selectedRow, 4))  ;
							}
			}
		});
				
		//tableMouseListener-----------------------
		
		JButton btnChange = new JButton("修改");
		btnChange.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(index.equals("trainerTable")) {
					asi.updateTrainer(Integer.parseInt(tfId.getText()),tfName.getText(),tfPassword.getText(),tfEmail.getText());
					tfId.setText("");
					tfName.setText("");
					tfUsername.setText("");
					tfPassword.setText("");
					tfEmail.setText("");
				}else if(index.equals("memberTable")) {
					asi.updateMember(Integer.parseInt(tfId.getText()),tfName.getText(),tfPassword.getText(),tfEmail.getText());
					tfId.setText("");
					tfName.setText("");
					tfUsername.setText("");
					tfPassword.setText("");
					tfEmail.setText("");
				}else if(index.equals("adminTable")) {
					asi.updateAdmin(Integer.parseInt(tfId.getText()),tfName.getText(),tfPassword.getText(),tfEmail.getText());
					tfId.setText("");
					tfName.setText("");
					tfUsername.setText("");
					tfPassword.setText("");
					tfEmail.setText("");		 
				}else {
				JOptionPane.showMessageDialog( null,   "目前僅能修改個人資料", " ",
				                    JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnChange.setBounds(346, 11, 85, 23);
		panel.add(btnChange);
		
	}
}
