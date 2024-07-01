package controller.trainer;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.HomeUI;
import controller.member.MemberManagerUI;
import model.Lesson;
import model.Order;
import model.Trainer;
import service.impl.TrainerServiceImpl;
import tool.Clock;
import tool.Tool;
import javax.swing.ImageIcon;

public class TrainerHomeUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTime;
	private JButton btnQueryAll;
	private List<Lesson> list;
	private JTable table;
	private JButton btnMyOrder;
	private JButton btnUpdate;
	private JButton btnHomepage;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainerHomeUI frame = new TrainerHomeUI();
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
	public TrainerHomeUI() {
		
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
		
		TrainerServiceImpl tsi = new TrainerServiceImpl();
		Trainer t = (Trainer) Tool.readFile("trainer.txt");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 623);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.add(lblTime);
		
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("教練主頁");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel.setBounds(178, 26, 242, 52);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		lblNewLabel.setText("Hi "+t.getName()+" 教練 今天好嗎");
		
		//課程查詢--------------------------
        // Column headers
        String[] lessonColumns = {"Lesson id", "Trainer id","name","starttime","price"};
        //抓出課程list集合
        list = new ArrayList(tsi.queryAllLesson());
        //建立Object[]裝Lesson物件(Jtable只接受Object[][])
        List<Object[]> oArr = new ArrayList<>();
        //遍歷物件並轉型
        for (Lesson a : list) {
            Object[] arr = new Object[]{a.getLesson_id(), a.getTrainer_id(), a.getName(),a.getTime(),a.getPrice()};
            oArr.add(arr);
        }

        
        Object[][] dataArray = oArr.toArray(new Object[0][0]);

        // 放入資料與列名
        DefaultTableModel lessonModel = new DefaultTableModel(dataArray, lessonColumns);
        
        table = new JTable(lessonModel);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setSize(621, 386);
		scrollPane.setLocation(150, 139);
		contentPane.add(scrollPane);
		//課程查詢-------------------------
		
		btnQueryAll = new JButton("查詢所有課程");
		btnQueryAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				list = new ArrayList(tsi.queryAllLesson());
				 table.setModel(lessonModel);
				 scrollPane.setViewportView(table);
				 contentPane.add(scrollPane);
			}
		});
		btnQueryAll.setBounds(10, 139, 120, 31);
		contentPane.add(btnQueryAll);
		
		
		btnMyOrder = new JButton("查看我的訂單");
		btnMyOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 // 訂單開頭
		        String[] orderColumns = {"order id", "Memrber id","ordertime","price","lessonName"};
		        //抓出課程list集合
		        List<Order> ol;
		        ol = new ArrayList(tsi.queryOrder(t.getId()));
		        //建立Object[]裝Lesson物件(Jtable只接受Object[][])
		        List<Object[]> oArr = new ArrayList<>();
		        //遍歷物件並轉型
		        for (Order a : ol) {
		            Object[] arr = new Object[]{a.getOrder_id(), a.getMember_id(), a.getTime(),a.getPrice(),a.getLessonName()};
		            oArr.add(arr);
		        }

		        Object[][] dataArray = oArr.toArray(new Object[0][0]);
		        DefaultTableModel orderModel = new DefaultTableModel(dataArray, orderColumns);
		        
		        table.setModel(orderModel);
		        scrollPane.setViewportView(table);
		        contentPane.add(scrollPane);
			}
		});
		btnMyOrder.setBounds(10, 193, 120, 31);
		contentPane.add(btnMyOrder);
		
		JButton btnNewLesson = new JButton("開新課程");
		btnNewLesson.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TrainerNewLessonUI frame = new TrainerNewLessonUI();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewLesson.setBounds(10, 242, 120, 31);
		contentPane.add(btnNewLesson);
		
		btnUpdate = new JButton("修改個人資料");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TrainerManagerUI frame = new TrainerManagerUI();
				frame.setVisible(true);
				dispose();
			}
		});
		btnUpdate.setBounds(650, 79, 110, 32);
		contentPane.add(btnUpdate);
		
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
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TrainerHomeUI.class.getResource("/img/trainer01.jpg")));
		lblNewLabel_1.setBounds(0, 118, 781, 468);
		contentPane.add(lblNewLabel_1);
		
	}
}
