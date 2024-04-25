package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;

import Controller.ClientController;
import Controller.CoachController;
import Controller.EquipementController;
import Controller.PaiementController;
import Controller.SeanceController;
import Controller.StatistiquesController;
import Model.ClientModel;
import Model.CoachModel;
import Model.EquipementModel;
import Model.PaiementModel;
import Model.SeanceModel;
import Model.StatistiquesModel;

public class clientView extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField Id_tf;
	public JTextField Nom_tf;
	public JTextField Prenom_tf;
	public JTextField DateInscription_tf;
	public JTextField NumTelephone_tf;
	public JTextField search_tf;
	public static JTable table;
	public JButton btnAdd;
	public JButton btnDelete;
	public JButton btnUpdate;
	public JButton btnExit;
	public JLabel Nom;
	public JLabel Id;
	public JLabel Prenom;
	public JLabel DateInscription;
	public JLabel NumTelephone;

	public clientView() throws SQLException {
		setTitle("Gestion salle de sport");
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

		btnAdd = new JButton("Ajouter");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdd.setForeground(new Color(128, 128, 128));
		btnAdd.setFont(new Font("Californian FB", Font.BOLD | Font.ITALIC, 19));
		btnAdd.setBackground(new Color(255, 255, 255));
		btnAdd.setSelectedIcon(new ImageIcon(clientView.class.getResource("/img/img2.png")));
		btnAdd.setBounds(175, 380, 115, 45);
		panel_1.add(btnAdd);

		search_tf = new JTextField();
		search_tf.setFont(new Font("Californian FB", Font.PLAIN, 21));
		search_tf.setBounds(10, 11, 385, 43);
		panel_1.add(search_tf);
		search_tf.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBorder(new MatteBorder(1, 1, 1, 1, new Color(150, 223, 241)));
		panel_3.setBounds(10, 69, 425, 300);
		panel_1.add(panel_3);
		panel_3.setLayout(null);

		Nom = new JLabel("Nom\r\n");
		Nom.setBounds(21, 75, 41, 24);
		panel_3.add(Nom);
		Nom.setFont(new Font("Californian FB", Font.PLAIN, 20));

		Id = new JLabel("Id");
		Id.setBounds(22, 22, 17, 24);
		panel_3.add(Id);
		Id.setFont(new Font("Californian FB", Font.PLAIN, 20));

		Prenom = new JLabel("Prenom\r\n");
		Prenom.setBounds(21, 123, 88, 31);
		panel_3.add(Prenom);
		Prenom.setFont(new Font("Californian FB", Font.PLAIN, 20));

		DateInscription = new JLabel("DateInscription");
		DateInscription.setBounds(20, 180, 138, 31);
		panel_3.add(DateInscription);
		DateInscription.setFont(new Font("Californian FB", Font.PLAIN, 20));

		NumTelephone = new JLabel("NumTelephone");
		NumTelephone.setBounds(20, 239, 138, 31);
		panel_3.add(NumTelephone);
		NumTelephone.setFont(new Font("Californian FB", Font.PLAIN, 20));

		Id_tf = new JTextField();
		Id_tf.setFont(new Font("Californian FB", Font.PLAIN, 17));
		Id_tf.setBounds(188, 22, 138, 24);
		panel_3.add(Id_tf);
		Id_tf.setColumns(10);

		Nom_tf = new JTextField();
		Nom_tf.setFont(new Font("Californian FB", Font.PLAIN, 17));
		Nom_tf.setBounds(188, 75, 138, 24);
		panel_3.add(Nom_tf);
		Nom_tf.setColumns(10);

		Prenom_tf = new JTextField();
		Prenom_tf.setFont(new Font("Californian FB", Font.PLAIN, 17));
		Prenom_tf.setBounds(188, 126, 138, 24);
		panel_3.add(Prenom_tf);
		Prenom_tf.setColumns(10);

		DateInscription_tf = new JTextField();
		DateInscription_tf.setFont(new Font("Californian FB", Font.PLAIN, 17));
		DateInscription_tf.setBounds(188, 180, 138, 27);
		panel_3.add(DateInscription_tf);
		DateInscription_tf.setColumns(10);

		NumTelephone_tf = new JTextField();
		NumTelephone_tf.setFont(new Font("Californian FB", Font.PLAIN, 17));
		NumTelephone_tf.setBounds(188, 239, 138, 27);
		panel_3.add(NumTelephone_tf);
		NumTelephone_tf.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(445, 69, 492, 300);
		panel_1.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		ClientController.table_load();

		btnDelete = new JButton("Supprimer");
		btnDelete.setForeground(new Color(128, 128, 128));
		btnDelete.setFont(new Font("Californian FB", Font.BOLD | Font.ITALIC, 19));
		btnDelete.setBackground(new Color(255, 255, 255));
		btnDelete.setBounds(310, 380, 125, 45);
		panel_1.add(btnDelete);

		btnUpdate = new JButton("Modifier");
		btnUpdate.setForeground(new Color(128, 128, 128));
		btnUpdate.setFont(new Font("Californian FB", Font.BOLD | Font.ITALIC, 19));
		btnUpdate.setBackground(new Color(255, 255, 255));
		btnUpdate.setBounds(445, 380, 113, 45);
		panel_1.add(btnUpdate);

		btnExit = new JButton("Exit");
		btnExit.setForeground(new Color(128, 128, 128));
		btnExit.setFont(new Font("Californian FB", Font.BOLD | Font.ITALIC, 19));
		btnExit.setBackground(new Color(255, 255, 255));
		btnExit.setBounds(583, 380, 113, 45);
		panel_1.add(btnExit);

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(clientView.class.getResource("/img/icons8-chercher-26.png")));
		lblNewLabel_6.setBounds(405, 11, 30, 43);
		panel_1.add(lblNewLabel_6);
		table.getColumnModel().getColumn(3).setPreferredWidth(86);
		table.getColumnModel().getColumn(4).setPreferredWidth(85);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(205, 0, 947, 49);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Gestion des clients\r\n");
		lblNewLabel_1.setFont(new Font("Californian FB", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel_1.setForeground(new Color(113, 106, 111));
		lblNewLabel_1.setBounds(67, 10, 482, 29);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(clientView.class.getResource("/img/mm (3).png")));
		lblNewLabel_2.setBounds(10, 0, 64, 47);
		panel_2.add(lblNewLabel_2);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 201, 500);
		contentPane.add(layeredPane);

		JLabel lblNewLabel_7 = new JLabel("New label");
		lblNewLabel_7.setBounds(81, 104, 49, 14);
		layeredPane.add(lblNewLabel_7);
		JPanel panel_4_1_1 = new JPanel();
		panel_4_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					dispose();

					EquipementModel model = new EquipementModel();
					equipementView view = new equipementView();
					EquipementController controller = new EquipementController(model, view);
					view.setVisible(true);
					controller.table_load();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_4_1_1.setLayout(null);
		panel_4_1_1.setForeground(Color.WHITE);
		panel_4_1_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),
				new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		panel_4_1_1.setBackground(new Color(255, 255, 255));
		panel_4_1_1.setBounds(10, 444, 181, 42);
		layeredPane.add(panel_4_1_1);

		JLabel Statistiques_1_1_1 = new JLabel("Gestion Equipement");
		Statistiques_1_1_1.setForeground(new Color(128, 128, 128));
		Statistiques_1_1_1.setFont(new Font("Californian FB", Font.BOLD | Font.ITALIC, 20));
		Statistiques_1_1_1.setBounds(0, 10, 181, 26);
		panel_4_1_1.add(Statistiques_1_1_1);

		JPanel panel_4_1 = new JPanel();
		panel_4_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();

				SeanceModel model = new SeanceModel();
				seanceView view = new seanceView();
				SeanceController controller = new SeanceController(model, view);
				view.setVisible(true);
				try {
					controller.table_load();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_4_1.setLayout(null);
		panel_4_1.setForeground(Color.WHITE);
		panel_4_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255),
				new Color(255, 255, 255), new Color(255, 255, 255)));
		panel_4_1.setBackground(new Color(255, 255, 255));
		panel_4_1.setBounds(10, 338, 181, 42);
		layeredPane.add(panel_4_1);

		JLabel Statistiques_1_1 = new JLabel("Gestion Seances");
		Statistiques_1_1.setForeground(new Color(128, 128, 128));
		Statistiques_1_1.setFont(new Font("Californian FB", Font.BOLD | Font.ITALIC, 21));
		Statistiques_1_1.setBounds(10, 10, 161, 26);
		panel_4_1.add(Statistiques_1_1);

		JPanel panel_6 = new JPanel();
		panel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();

				PaiementModel model = new PaiementModel();
				paimentView view = new paimentView();
				PaiementController controller = new PaiementController(model, view);
				view.setVisible(true);
				try {
					controller.table_load();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_6.setLayout(null);
		panel_6.setForeground(Color.WHITE);
		panel_6.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255),
				new Color(255, 255, 255), new Color(255, 255, 255)));
		panel_6.setBackground(new Color(255, 255, 255));
		panel_6.setBounds(10, 391, 181, 42);
		layeredPane.add(panel_6);

		JLabel lblGestionPaiement = new JLabel("Gestion Paiement");
		lblGestionPaiement.setForeground(new Color(128, 128, 128));
		lblGestionPaiement.setFont(new Font("Californian FB", Font.BOLD | Font.ITALIC, 21));
		lblGestionPaiement.setBounds(10, 11, 171, 26);
		panel_6.add(lblGestionPaiement);

		JPanel panel_5 = new JPanel();
		panel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();

				try {
					CoachModel model = new CoachModel();
					coachView view = new coachView();
					CoachController controller = new CoachController(model, view);

					view.setVisible(true);
					controller.table_load();
				} catch (Exception x) {
					x.printStackTrace();
				}
			}
		});
		panel_5.setLayout(null);
		panel_5.setForeground(Color.WHITE);
		panel_5.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255),
				new Color(255, 255, 255), new Color(255, 255, 255)));
		panel_5.setBackground(new Color(255, 255, 255));
		panel_5.setBounds(10, 285, 181, 42);
		layeredPane.add(panel_5);

		JLabel lblGestionCoach = new JLabel("Gestion Coach\r\n");
		lblGestionCoach.setForeground(new Color(128, 128, 128));
		lblGestionCoach.setFont(new Font("Californian FB", Font.BOLD | Font.ITALIC, 21));
		lblGestionCoach.setBounds(21, 11, 139, 26);
		panel_5.add(lblGestionCoach);

		JPanel panel_4 = new JPanel();
		panel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					dispose();

					ClientModel model = new ClientModel();
					clientView view = new clientView();
					ClientController controller = new ClientController(model, view);
					view.setVisible(true);
					controller.table_load();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_4.setLayout(null);
		panel_4.setForeground(Color.WHITE);
		panel_4.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255),
				new Color(255, 255, 255), new Color(255, 255, 255)));
		panel_4.setBackground(new Color(255, 255, 255));
		panel_4.setBounds(10, 232, 181, 42);
		layeredPane.add(panel_4);

		JLabel Statistiques_1 = new JLabel("Gestion Clients");
		Statistiques_1.setForeground(new Color(128, 128, 128));
		Statistiques_1.setFont(new Font("Californian FB", Font.BOLD | Font.ITALIC, 21));
		Statistiques_1.setBounds(20, 11, 151, 26);
		panel_4.add(Statistiques_1);

		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();

				StatistiquesModel model = new StatistiquesModel();
				home view = new home();
				StatistiquesController controller = new StatistiquesController(model, view);
				try {
					controller.updateStatistics();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				view.setVisible(true);
			}
		});
		panel.setBackground(new Color(255, 255, 255));
		panel.setForeground(new Color(255, 255, 255));
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255),
				new Color(255, 255, 255), new Color(255, 255, 255)));
		panel.setBounds(10, 179, 181, 42);
		layeredPane.add(panel);
		panel.setLayout(null);

		JLabel Statistiques = new JLabel("Statistiques");
		Statistiques.setBounds(34, 11, 107, 26);
		panel.add(Statistiques);

		Statistiques.setForeground(new Color(128, 128, 128));
		Statistiques.setFont(new Font("Californian FB", Font.BOLD | Font.ITALIC, 21));

		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(coachView.class.getResource("/img/imageedit_1_8651314769 (1).png")));
		lblNewLabel_8.setBounds(0, 24, 178, 144);
		layeredPane.add(lblNewLabel_8);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(coachView.class.getResource("/img/img2.png")));
		lblNewLabel.setBounds(-50, 0, 251, 500);
		layeredPane.add(lblNewLabel);

	}

	public String getSearchId() {
		String search_nom = search_tf.getText();
		if (search_nom.isEmpty()) {
			// Handle the case where the text field is empty
			return null; // Or any other appropriate default value
		}

		try {
			String id = search_nom;
			return search_nom;
		} catch (NumberFormatException e) {
			// Handle the case where the text field contains non-numeric characters
			e.printStackTrace(); // Or log the error
			return null; // Or any other appropriate default value
		}
	}

	public void clearInputs() {
		Id_tf.setText("");
		Nom_tf.setText("");
		Prenom_tf.setText("");
		NumTelephone_tf.setText("");
		DateInscription_tf.setText("");
	}

	public void addAddButtonListener(ActionListener listener) {
		btnAdd.addActionListener(listener);
	}

	public void addUpdateButtonListener(ActionListener listener) {
		btnUpdate.addActionListener(listener);
	}

	public void addDeleteButtonListener(ActionListener listener) {
		btnDelete.addActionListener(listener);
	}

	public void addExitButtonListener(ActionListener listener) {
		btnExit.addActionListener(listener);
	}

	public void addSearcheButtonListener(KeyAdapter kA) {
		search_tf.addKeyListener(kA);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}