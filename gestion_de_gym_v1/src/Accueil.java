import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class Accueil extends JFrame {
	static Timer t;
	static JFrame loginFrame;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Accueil frame = new Accueil();
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
	public Accueil() {
		setResizable(false);
		setTitle("3XS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(new Color(100, 149, 237));
		progressBar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		progressBar.setStringPainted(true);
		progressBar.setBounds(12, 374, 500, 65);
		contentPane.add(progressBar);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("WhatsApp Image 2024-04-03 at 14.54.57_89bee4e9.jpg"));
		lblNewLabel.setBounds(12, 10, 500, 361);
		contentPane.add(lblNewLabel);
		t = new Timer(100, new ActionListener() {
			int x = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				progressBar.setValue(x++);
				if (x > 100) {
					t.stop();
					openLoginWindow();
				}
			}
		});
		t.start();
	}

	public static void openLoginWindow() {
		loginFrame = new Login();
		// JOptionPane.showMessageDialog(loginFrame, "Le temps est écoulé. Veuillez vous
		// connecter.", "Alerte",
		// JOptionPane.INFORMATION_MESSAGE);
		loginFrame.setVisible(true);
	}
}
