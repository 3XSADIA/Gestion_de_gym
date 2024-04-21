import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setResizable(false);
		setTitle("LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1099, 663);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(346, 160, 353, 306);
		contentPane.add(panel);
		panel.setLayout(null);
		// panel.setOpaque(false);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(218, 112, 214));
		panel_1.setBounds(0, 0, 353, 46);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(122, 0, 103, 42);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nom d'utilisateur");
		lblNewLabel_1.setForeground(new Color(128, 128, 128));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblNewLabel_1.setBounds(21, 56, 162, 36);
		panel.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(21, 102, 315, 36);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(21, 194, 315, 36);
		panel.add(textField_1);

		JLabel lblNewLabel_1_1 = new JLabel("Mot de passe");
		lblNewLabel_1_1.setForeground(new Color(128, 128, 128));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblNewLabel_1_1.setBounds(21, 148, 162, 36);
		panel.add(lblNewLabel_1_1);

		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(218, 112, 214));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(94, 247, 162, 35);
		panel.add(btnNewButton);

		JLabel lblNewLabel_2 = new JLabel("");
		ImageIcon icon = new ImageIcon("D:\\Desktop\\gym2.jpg");
		Image img = icon.getImage().getScaledInstance(1085, 626, Image.SCALE_SMOOTH);
		// Définition de l'image redimensionnée dans le JLabel
		lblNewLabel_2.setIcon(new ImageIcon(img));
		lblNewLabel_2.setBounds(0, 0, 1085, 626);
		contentPane.add(lblNewLabel_2);

	}
}
