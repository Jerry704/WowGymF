package controller.trainer;

import java.awt.EventQueue;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import model.Lesson;
import model.Trainer;
import service.impl.TrainerServiceImpl;
import tool.Tool;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

public class TrainerNewLessonUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textName;
	private JTextField textPrice;
	private JDateChooser DateChooser;
	private Date now = new Date();
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainerNewLessonUI frame = new TrainerNewLessonUI();
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
	public TrainerNewLessonUI() {
		TrainerServiceImpl tsi = new TrainerServiceImpl();
		Trainer t = (Trainer) Tool.readFile("trainer.txt");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("開新課程");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(238, 45, 179, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("課程名稱:");
		lblNewLabel_1.setBounds(101, 126, 64, 35);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("上課時間:");
		lblNewLabel_1_1.setBounds(101, 291, 64, 35);
		contentPane.add(lblNewLabel_1_1);
		
		textName = new JTextField();
		textName.setBounds(191, 133, 206, 21);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textPrice = new JTextField();
		textPrice.setBounds(191, 208, 206, 21);
		contentPane.add(textPrice);
		textPrice.setColumns(10);
		
		DateChooser = new JDateChooser();
		DateChooser.setBounds(191, 275, 137, 67);
		DateChooser.setDate(now);
		getContentPane().add(DateChooser);
		
		SpinnerDateModel dateModel = new SpinnerDateModel();
		JSpinner sSpinner = new JSpinner(dateModel);
		sSpinner.setLocation(352, 275);
		sSpinner.setSize(115, 67);
		sSpinner.setEditor(new JSpinner.DateEditor(sSpinner, "HH:mm"));
		getContentPane().add(sSpinner);
		
		JLabel lblShow = new JLabel("");
		lblShow.setBounds(191, 397, 206, 35);
		contentPane.add(lblShow);
		
		JButton btnConfirm = new JButton("確認");
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Date startDate = DateChooser.getDate();
				Date startTime = (Date) sSpinner.getValue();
				startDate.setHours(startTime.getHours());
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String start = formatter.format(startDate.getTime());
				Integer uiPrice = Integer.parseInt(textPrice.getText());
				Lesson l = new Lesson(t.getId(),textName.getText(),start,uiPrice);
				if(l !=null) {
					tsi.addLesson(l);
					JOptionPane.showMessageDialog(
							 null,
			                    "添加成功",
			                    " ",
			                    JOptionPane.WARNING_MESSAGE);
					TrainerHomeUI frame = new TrainerHomeUI();
					frame.setVisible(true);
					dispose();		
				}else {
					lblShow.setText("添加失敗");
				}
				
			}
		});
		btnConfirm.setBounds(253, 442, 85, 23);
		contentPane.add(btnConfirm);
		
		JButton btnBackPage = new JButton("上一頁");
		btnBackPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TrainerHomeUI frame = new TrainerHomeUI();
				frame.setVisible(true);
				dispose();
			}
		});
		btnBackPage.setBounds(537, 51, 85, 23);
		contentPane.add(btnBackPage);
		
		JLabel lblPrice = new JLabel("價錢:");
		lblPrice.setBounds(101, 201, 64, 35);
		contentPane.add(lblPrice);
		
		
		
	}
}
