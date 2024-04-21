package gestionsallesport;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DropMode;

public class login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 938, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 201, 249));
		panel_1.setForeground(new Color(167, 208, 250));
		panel_1.setBounds(493, 0, 431, 500);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel login = new JLabel("Login");
		login.setBackground(new Color(240, 240, 240));
		login.setForeground(new Color(255, 255, 255));
		login.setFont(new Font("Bookman Old Style", Font.BOLD, 37));
		login.setBounds(152, 54, 116, 43);
		panel_1.add(login);
		
		JLabel lblNewLabel_1_1 = new JLabel("User");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 28));
		lblNewLabel_1_1.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_1_1.setBounds(34, 157, 116, 43);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("_________________________________________");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBackground(new Color(240, 240, 240));
		lblNewLabel_2.setBounds(44, 233, 313, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(login.class.getResource("/img/user-16.png")));
		lblNewLabel_3.setBounds(361, 218, 16, 21);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Password");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Calibri", Font.PLAIN, 28));
		lblNewLabel_1_1_1.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_1_1_1.setBounds(34, 276, 116, 43);
		panel_1.add(lblNewLabel_1_1_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(214, 214, 214));
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(34, 402, 343, 43);
		panel_1.add(btnNewButton);
		
		JLabel show = new JLabel("");
		show.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passwordField.setEchoChar((char)0);
				show.setVisible(true);
				show.setEnabled(false);
				
				
			}
		});
		show.setIcon(new ImageIcon(login.class.getResource("/img/invisible-16.png")));
		show.setBounds(361, 337, 16, 21);
		panel_1.add(show);
		
		JLabel lblNewLabel_5 = new JLabel("Hello!!");
		lblNewLabel_5.setFont(new Font("Calibri", Font.PLAIN, 19));
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setBackground(new Color(240, 240, 240));
		lblNewLabel_5.setBounds(178, 124, 82, 26);
		panel_1.add(lblNewLabel_5);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(153, 201, 249));
		passwordField.setBounds(44, 330, 302, 28);
		panel_1.add(passwordField);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBackground(new Color(153, 201, 249));
		textField.setBounds(44, 211, 302, 28);
		panel_1.add(textField);
		
		JLabel lblNewLabel_2_1 = new JLabel("_________________________________________");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_2_1.setBounds(44, 351, 313, 14);
		panel_1.add(lblNewLabel_2_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setForeground(Color.WHITE);
		panel_1_1.setBackground(Color.WHITE);
		panel_1_1.setBounds(0, 0, 492, 500);
		contentPane.add(panel_1_1);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setIcon(new ImageIcon(login.class.getResource("/img/img1.png")));
		lblNewLabel.setBounds(0, 0, 492, 500);
		panel_1_1.add(lblNewLabel);
	}
}
