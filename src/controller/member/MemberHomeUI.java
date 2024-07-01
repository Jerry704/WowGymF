package controller.member;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.HomeUI;
import controller.trainer.TrainerHomeUI;
import model.Lesson;
import model.Member;
import model.Order;
import service.impl.MemberServiceImpl;
import tool.Tool;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import tool.Clock;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.ImageIcon;

public class MemberHomeUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTime;
	private JButton btnQueryAll;
	private List<Lesson> list;
	private JTable table;
	private JButton btnMyOrder;
	private JButton btnUpdate;
	private JButton btnToOrder;
	private DefaultTableModel tableModel;
	private JButton btnHomepage;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberHomeUI frame = new MemberHomeUI();
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
	public MemberHomeUI() {

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
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}).start();
			}
		});

		MemberServiceImpl msi = new MemberServiceImpl();
		Member m = (Member) Tool.readFile("member.txt");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 623);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.add(lblTime);

		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("會員主頁");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel.setBounds(178, 26, 242, 52);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);

		lblNewLabel.setText("Hello " + m.getName() + " 會員 今天運動了嗎");

		// 課程查詢--------------------------
		// Column headers
		String[] lessonColumns = { "Lesson id", "Trainer id", "name", "starttime","price" };
		// 抓出課程list集合
		list = new ArrayList(msi.queryAllLesson());
		// 建立Object[]裝Lesson物件(Jtable只接受Object[][])
		List<Object[]> oArr = new ArrayList<>();
		// 遍歷物件並轉型
		for (Lesson a : list) {
			Object[] arr = new Object[] { a.getLesson_id(), a.getTrainer_id(), a.getName(), a.getTime(),a.getPrice() };
			oArr.add(arr);
		}

		Object[][] dataArray = oArr.toArray(new Object[0][0]);

		// Create a DefaultTableModel and set data and column names
		DefaultTableModel lessonModel = new DefaultTableModel(dataArray, lessonColumns);

		table = new JTable(lessonModel);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setSize(621, 386);
		scrollPane.setLocation(150, 139);
		contentPane.add(scrollPane);
		
		
		
		// 課程查詢-------------------------

		btnQueryAll = new JButton("查詢所有課程");
		btnQueryAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				list = new ArrayList(msi.queryAllLesson());
				table.setModel(lessonModel);
				scrollPane.setViewportView(table);
				contentPane.add(scrollPane);
			}
		});
		btnQueryAll.setBounds(10, 139, 120, 31);
		contentPane.add(btnQueryAll);
		

		btnMyOrder = new JButton("購買紀錄");
		btnMyOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 訂單開頭
				String[] orderColumns = { "order id", "Trainer id", "time", "price", "lessonName" };
				// 抓出課程list集合
				List<Order> ol;
				ol = new ArrayList(msi.queryOrder(m.getId()));
				// 建立Object[]裝Lesson物件(Jtable只接受Object[][])
				List<Object[]> oArr = new ArrayList<>();
				// 遍歷物件並轉型
				for (Order a : ol) {
					Object[] arr = new Object[] { a.getOrder_id(), a.getTrainer_id(), a.getTime(), a.getPrice(),
							a.getLessonName() };
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

		btnUpdate = new JButton("修改個人資料");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MemberManagerUI frame = new MemberManagerUI();
				frame.setVisible(true);
				dispose();
			}
		});
		btnUpdate.setBounds(650, 79, 110, 32);
		contentPane.add(btnUpdate);

		btnToOrder = new JButton("購買課程");
		btnToOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Order o =null;
				tableModel = new DefaultTableModel(dataArray, lessonColumns);
				int selectedRow = table.getSelectedRow();
				
				if (selectedRow != -1) {

					// Get the data from the selected row
				
					Integer trainerId = (Integer) tableModel.getValueAt(selectedRow, 1);
					String lessonName = (String) tableModel.getValueAt(selectedRow, 2);
					Integer price = (Integer) tableModel.getValueAt(selectedRow, 4);
					o = new Order(trainerId,m.getId(),Tool.getTime(),price,lessonName);
					if(o != null) {
						msi.addOrder(o);
						JOptionPane.showMessageDialog(
								 null,
				                    "購買成功",
				                    " ",
				                    JOptionPane.WARNING_MESSAGE);
					}
				}else if(o ==null){
					JOptionPane.showMessageDialog(
							 null,
			                    "購買失敗",
			                    " ",
			                    JOptionPane.WARNING_MESSAGE);
				}
			}

		});
		btnToOrder.setBounds(10, 494, 120, 31);
		contentPane.add(btnToOrder);
		
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
		lblNewLabel_1.setIcon(new ImageIcon(MemberHomeUI.class.getResource("/img/member2.jpg")));
		lblNewLabel_1.setBounds(0, 121, 781, 465);
		contentPane.add(lblNewLabel_1);

	}
}
