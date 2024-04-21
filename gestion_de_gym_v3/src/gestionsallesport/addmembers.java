package gestionsallesport;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLayeredPane;

public class addmembers extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addmembers frame = new addmembers();
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
	public addmembers() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1166, 537);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(185, 230, 242));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(205, 50, 947, 450);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFont(new Font("Californian FB", Font.PLAIN, 19));
		btnNewButton.setBackground(new Color(128, 216, 238));
		btnNewButton.setSelectedIcon(new ImageIcon(addmembers.class.getResource("/img/img2.png")));
		btnNewButton.setBounds(175, 380, 103, 45);
		panel_1.add(btnNewButton);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Californian FB", Font.PLAIN, 21));
		textField_5.setBounds(10, 11, 317, 43);
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearch.setFont(new Font("Californian FB", Font.PLAIN, 19));
		btnSearch.setBackground(new Color(128, 216, 238));
		btnSearch.setBounds(370, 12, 89, 43);
		panel_1.add(btnSearch);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(128, 216, 238)));
		panel_3.setBounds(10, 69, 425, 300);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("Nom\r\n");
		lblNewLabel_2_1.setBounds(21, 75, 41, 24);
		panel_3.add(lblNewLabel_2_1);
		lblNewLabel_2_1.setFont(new Font("Californian FB", Font.PLAIN, 20));
		
		JLabel lblNewLabel_2 = new JLabel("Id");
		lblNewLabel_2.setBounds(22, 22, 17, 24);
		panel_3.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Californian FB", Font.PLAIN, 20));
		
		JLabel lblNewLabel_2_2 = new JLabel("Prenom\r\n");
		lblNewLabel_2_2.setBounds(21, 123, 88, 31);
		panel_3.add(lblNewLabel_2_2);
		lblNewLabel_2_2.setFont(new Font("Californian FB", Font.PLAIN, 20));
		
		JLabel lblNewLabel_2_3 = new JLabel("DateInscription");
		lblNewLabel_2_3.setBounds(20, 180, 138, 31);
		panel_3.add(lblNewLabel_2_3);
		lblNewLabel_2_3.setFont(new Font("Californian FB", Font.PLAIN, 20));
		
		JLabel lblNewLabel_2_4 = new JLabel("NumTelephone");
		lblNewLabel_2_4.setBounds(20, 239, 138, 31);
		panel_3.add(lblNewLabel_2_4);
		lblNewLabel_2_4.setFont(new Font("Californian FB", Font.PLAIN, 20));
		
		textField = new JTextField();
		textField.setFont(new Font("Californian FB", Font.PLAIN, 17));
		textField.setBounds(188, 22, 138, 24);
		panel_3.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Californian FB", Font.PLAIN, 17));
		textField_1.setBounds(188, 75, 138, 24);
		panel_3.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Californian FB", Font.PLAIN, 17));
		textField_2.setBounds(188, 126, 138, 24);
		panel_3.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Californian FB", Font.PLAIN, 17));
		textField_3.setBounds(188, 180, 138, 27);
		panel_3.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Californian FB", Font.PLAIN, 17));
		textField_4.setBounds(188, 239, 138, 27);
		panel_3.add(textField_4);
		textField_4.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(445, 69, 492, 300);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nom", "Prenom", "DateInscription", "NumTelephone"
			}
		));
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.setFont(new Font("Californian FB", Font.PLAIN, 19));
		btnNewButton_1.setBackground(new Color(128, 216, 238));
		btnNewButton_1.setBounds(310, 380, 103, 45);
		panel_1.add(btnNewButton_1);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Californian FB", Font.PLAIN, 19));
		btnUpdate.setBackground(new Color(128, 216, 238));
		btnUpdate.setBounds(445, 380, 103, 45);
		panel_1.add(btnUpdate);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Californian FB", Font.PLAIN, 19));
		btnExit.setBackground(new Color(128, 216, 238));
		btnExit.setBounds(583, 380, 103, 45);
		panel_1.add(btnExit);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(addmembers.class.getResource("/img/search-16.gif")));
		lblNewLabel_4.setBounds(228, 11, 30, 43);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(addmembers.class.getResource("/img/search-16.gif")));
		lblNewLabel_3.setBounds(252, 11, 49, 43);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setBounds(252, 28, 49, 14);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(addmembers.class.getResource("/img/search-24.png")));
		lblNewLabel_6.setBounds(330, 11, 30, 43);
		panel_1.add(lblNewLabel_6);
		table.getColumnModel().getColumn(3).setPreferredWidth(86);
		table.getColumnModel().getColumn(4).setPreferredWidth(85);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(205, 0, 947, 47);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon(addmembers.class.getResource("/img/icons8-gym-64.png")));
		lblNewLabel_1_1.setBounds(10, 0, 64, 42);
		panel_2.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Gym managment system");
		lblNewLabel_1.setFont(new Font("Californian FB", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel_1.setForeground(new Color(99, 207, 235));
		lblNewLabel_1.setBounds(84, 11, 275, 25);
		panel_2.add(lblNewLabel_1);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 201, 500);
		contentPane.add(layeredPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(addmembers.class.getResource("/img/img2.png")));
		lblNewLabel.setBounds(-50, 0, 251, 500);
		layeredPane.add(lblNewLabel);
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		lblNewLabel_7.setBounds(81, 104, 49, 14);
		layeredPane.add(lblNewLabel_7);
	}
}
