package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import Controller.StatistiquesController;
import Model.StatistiquesModel;

public class login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtuser;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
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

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setForeground(Color.WHITE);
		panel_1_1.setBackground(Color.WHITE);
		panel_1_1.setBounds(0, 0, 439, 500);
		contentPane.add(panel_1_1);

		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(2, 2, 2, 2, new Color(255, 255, 255)));
		panel.setBounds(20, 41, 396, 419);
		panel.setOpaque(false);
		panel_1_1.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(255, 255, 255)));
		panel_1.setBounds(110, 43, 174, 45);
		panel_1.setOpaque(false);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Sign in");
		lblNewLabel_3.setFont(new Font("Ink Free", Font.BOLD | Font.ITALIC, 27));
		lblNewLabel_3.setForeground(new Color(113, 113, 113));
		lblNewLabel_3.setBounds(47, 0, 98, 39);
		panel_1.add(lblNewLabel_3);

		txtuser = new SearchTextField(" Username");
		txtuser.setText(" Username");
		txtuser.setForeground(new Color(113, 113, 113));
		txtuser.setFont(new Font("Ink Free", Font.BOLD | Font.ITALIC, 14));
		txtuser.setBounds(66, 148, 270, 45);
		panel.add(txtuser);
		txtuser.setColumns(10);
		txtuser.setOpaque(false);
		txtuser.setBorder(new MatteBorder(1, 1, 1, 1, new Color(255, 255, 255)));

		JButton btnNewButton = new JButton("Sign in\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtuser.getText().equals("admin") && new String(passwordField.getPassword()).equals("admin")) {
					try {
						dispose();
						StatistiquesModel model = new StatistiquesModel();
						home view = new home();
						StatistiquesController controller = new StatistiquesController(model, view);
						controller.updateStatistics();
						view.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					txtuser.setText("");
					passwordField.setText("");
					JOptionPane.showMessageDialog(null, "Identifiant ou mot de passe incorrect", "Erreur de connexion",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnNewButton.setBackground(new Color(222, 222, 222));
		btnNewButton.setForeground(new Color(113, 113, 113));
		btnNewButton.setFont(new Font("Ink Free", Font.BOLD | Font.ITALIC, 22));
		btnNewButton.setBounds(110, 337, 174, 45);
		panel.add(btnNewButton);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 1, 1, 0, new Color(255, 255, 255)));
		panel_2.setOpaque(false);
		panel_2.setBounds(28, 148, 36, 45);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(10, 11, 32, 21);
		panel_2.add(lblNewLabel_4);
		lblNewLabel_4.setIcon(new ImageIcon(login.class.getResource("/img/user-16.png")));

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setOpaque(false);
		panel_2_1.setBorder(new MatteBorder(1, 1, 1, 0, new Color(255, 255, 255)));
		panel_2_1.setBounds(28, 238, 36, 45);
		panel.add(panel_2_1);

		JLabel lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_4_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passwordField.setEchoChar((char) 0);
				lblNewLabel_4_1.setVisible(true);
				lblNewLabel_4_1.setEnabled(false);
			}

		});
		lblNewLabel_4_1.setBounds(10, 11, 30, 21);
		panel_2_1.add(lblNewLabel_4_1);
		lblNewLabel_4_1.setIcon(new ImageIcon(login.class.getResource("/img/visible-24.png")));

		JPanel panel_3 = new JPanel();
		panel_3.setForeground(new Color(122, 122, 122));
		panel_3.setOpaque(false);
		panel_3.setBorder(new MatteBorder(1, 1, 1, 1, new Color(255, 255, 255)));
		panel_3.setBounds(66, 238, 270, 45);
		panel.add(panel_3);
		panel_3.setLayout(null);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Ink Free", Font.BOLD | Font.ITALIC, 15));
		passwordField.setForeground(new Color(122, 122, 122));
		passwordField.setBounds(0, 0, 270, 45);
		panel_3.add(passwordField);
		passwordField.setBackground(new Color(255, 255, 255));
		passwordField.setOpaque(false);
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(login.class.getResource("/img/img99.png")));
		lblNewLabel_1.setBounds(-61, 0, 500, 500);
		panel_1_1.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(437, 0, 487, 500);
		contentPane.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(login.class.getResource("/img/img1.png")));

	}
}
