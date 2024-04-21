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

public class home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home frame = new home();
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
	public home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1166, 537);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(185, 230, 242));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 204, 506);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(-120, 0, 324, 506);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(home.class.getResource("/img/img2.png")));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(205, 50, 947, 450);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(64, 53, 212, 121);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(0, 0, 212, 121);
		lblNewLabel_2.setIcon(new ImageIcon(home.class.getResource("/img/img5 (1).png")));
		panel_3.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Registered members");
		lblNewLabel_3.setBackground(new Color(0, 0, 0));
		lblNewLabel_3.setBounds(0, 54, 129, 121);
		panel_3.add(lblNewLabel_3);
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBounds(365, 53, 212, 121);
		panel_1.add(panel_3_1);
		panel_3_1.setLayout(null);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("");
		lblNewLabel_2_1_1.setIcon(new ImageIcon(home.class.getResource("/img/img6.png")));
		lblNewLabel_2_1_1.setBounds(-182, 0, 463, 121);
		panel_3_1.add(lblNewLabel_2_1_1);
		
		JPanel panel_3_2 = new JPanel();
		panel_3_2.setBounds(662, 53, 212, 121);
		panel_1.add(panel_3_2);
		panel_3_2.setLayout(null);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("");
		lblNewLabel_2_2_1.setIcon(new ImageIcon(home.class.getResource("/img/img7.png")));
		lblNewLabel_2_2_1.setBounds(-196, 0, 408, 121);
		panel_3_2.add(lblNewLabel_2_2_1);
		
		JPanel panel_3_4 = new JPanel();
		panel_3_4.setBounds(365, 267, 212, 121);
		panel_1.add(panel_3_4);
		panel_3_4.setLayout(null);
		
		JLabel lblNewLabel_2_2 = new JLabel("");
		lblNewLabel_2_2.setIcon(new ImageIcon(home.class.getResource("/img/img7.png")));
		lblNewLabel_2_2.setBounds(-196, 0, 408, 121);
		panel_3_4.add(lblNewLabel_2_2);
		
		JPanel panel_3_5 = new JPanel();
		panel_3_5.setBounds(662, 267, 212, 121);
		panel_1.add(panel_3_5);
		panel_3_5.setLayout(null);
		
		JLabel lblNewLabel_2_3 = new JLabel("");
		lblNewLabel_2_3.setIcon(new ImageIcon(home.class.getResource("/img/img5 (1).png")));
		lblNewLabel_2_3.setBounds(0, 0, 212, 121);
		panel_3_5.add(lblNewLabel_2_3);
		
		JPanel panel_3_4_1 = new JPanel();
		panel_3_4_1.setLayout(null);
		panel_3_4_1.setBounds(64, 267, 212, 121);
		panel_1.add(panel_3_4_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setIcon(new ImageIcon(home.class.getResource("/img/img6.png")));
		lblNewLabel_2_1.setBounds(-129, 0, 341, 121);
		panel_3_4_1.add(lblNewLabel_2_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(205, 0, 947, 47);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon(home.class.getResource("/img/icons8-gym-64.png")));
		lblNewLabel_1_1.setBounds(10, 0, 64, 42);
		panel_2.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Gym managment system");
		lblNewLabel_1.setFont(new Font("Californian FB", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel_1.setForeground(new Color(64, 186, 221));
		lblNewLabel_1.setBounds(84, 11, 275, 25);
		panel_2.add(lblNewLabel_1);
	}
}
