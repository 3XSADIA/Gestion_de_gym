package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import Controller.ClientController;
import Controller.CoachController;
import Controller.CourController;
import Controller.EquipementController;
import Controller.PaiementController;
import Controller.SeanceController;
import Controller.StatistiquesController;
import Model.ClientModel;
import Model.CoachModel;
import Model.CourModel;
import Model.EquipementModel;
import Model.PaiementModel;
import Model.SeanceModel;
import Model.StatistiquesModel;
import Model.StatistiquesModel.Equipment;
import raven.chart.ModelChart;

public class home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PieChart pieChart;

	public JLabel seances;
	public JLabel coachs;
	public JLabel clients;
	public JLabel gain;
	public JLabel equipement;
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private raven.chart.CurveLineChart chart;
	private raven.panel.PanelShadow panelShadow1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					StatistiquesModel model = new StatistiquesModel();
					home view = new home();
					StatistiquesController controller = new StatistiquesController(model, view);
					controller.updateStatistics();
					view.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setGainsClientsData() {
		try {
			List<StatistiquesModel> lists = new ArrayList<>();
			String sql1 = "SELECT YEAR(date_paiement) as `Year`, SUM(montantpaye) as gains FROM paiement GROUP BY YEAR(date_paiement) ORDER BY date_paiement DESC LIMIT 7";
			String sql = "SELECT YEAR(dateInscription) as `Year`, COUNT(*) as total_clients FROM client GROUP BY YEAR(dateInscription) ORDER BY dateInscription DESC LIMIT 7";
			StatistiquesModel sm = new StatistiquesModel();
			PreparedStatement p = sm.con.prepareStatement(sql);
			PreparedStatement p1 = sm.con.prepareStatement(sql1);

			ResultSet r = p.executeQuery();
			ResultSet r1 = p1.executeQuery();

			while (r.next() && r1.next()) {
				String year = r.getString("Year");
				double client = r.getDouble("total_clients");
				double profit = r1.getDouble("gains");
				lists.add(new StatistiquesModel(year, client, profit));
			}

			r.close();
			p.close();
			r1.close();
			p1.close();

			// Define custom x-axis labels
			String[] xLabels = new String[] { "2024", "2023", "2022", "2021", "2020", "2019", "2018", "2017" };

			// Add Data to chart
			for (int i = lists.size() - 1; i >= 0; i--) {
				StatistiquesModel d = lists.get(i);
				chart.addData(new ModelChart(xLabels[i], new double[] { d.getclient(), d.getProfit() }));
			}

			// Start to show data with animation
			chart.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// pie chart pour nombre des equipement
	public void setData(List<Equipment> equipmentList) {
		for (StatistiquesModel.Equipment equipment : equipmentList) {
			Color color = new Color((int) (Math.random() * 0x1000000));
			pieChart.addData(new ModelPieChart(equipment.getName(), equipment.getQuantity(), color));
		}
	}

	/**
	 * Create the frame.
	 */
	public home() {
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
		panel_1.setBounds(203, 0, 949, 500);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		pieChart = new PieChart();
		pieChart.setBackground(new Color(255, 255, 255));
		pieChart.setBorder(new LineBorder(new Color(0, 0, 0)));
		pieChart.setBounds(526, 127, 413, 318);
		panel_1.add(pieChart);
		pieChart.setChartType(PieChart.PeiChartType.DONUT_CHART);
		pieChart.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
		pieChart.setPreferredSize(new Dimension(553, 200));

		//
		//
		panelShadow1 = new raven.panel.PanelShadow();
		chart = new raven.chart.CurveLineChart();

		panelShadow1.setBackground(new java.awt.Color(34, 59, 69));
		panelShadow1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelShadow1.setColorGradient(new java.awt.Color(17, 38, 47));
		panelShadow1.setBounds(20, 120, 509, 370);
		panel_1.add(panelShadow1);
		chart.setForeground(new java.awt.Color(237, 237, 237));
		chart.setFillColor(true);

		javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
		panelShadow1.setLayout(panelShadow1Layout);
		panelShadow1Layout
				.setHorizontalGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								panelShadow1Layout.createSequentialGroup().addContainerGap()
										.addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
										.addContainerGap()));
		panelShadow1Layout
				.setVerticalGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(panelShadow1Layout.createSequentialGroup().addContainerGap()
								.addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
								.addContainerGap()));

		//
		//
		chart.addLegend("Total des Clients", Color.decode("#7b4397"), Color.decode("#dc2430"));
		// chart.addLegend("Cost", Color.decode("#e65c00"), Color.decode("#F9D423"));
		chart.addLegend("Total des Gains", Color.decode("#0099F7"), Color.decode("#F11712"));
		chart.clear();

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(null);
		panel_3.setBounds(20, 11, 212, 91);
		panel_1.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Membres inscrits");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Californian FB", Font.BOLD, 20));
		lblNewLabel_3.setBounds(33, 61, 155, 31);
		panel_3.add(lblNewLabel_3);

		clients = new JLabel("");
		clients.setFont(new Font("Californian FB", Font.BOLD, 18));
		clients.setForeground(new Color(255, 255, 255));
		clients.setIcon(new ImageIcon(home.class.getResource("/img/conference-48.png")));
		clients.setBounds(10, 11, 131, 47);
		panel_3.add(clients);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(0, 0, 211, 92);
		lblNewLabel_2.setIcon(new ImageIcon(home.class.getResource("/img/img5 (1).png")));
		panel_3.add(lblNewLabel_2);

		JPanel panel_3_4 = new JPanel();
		panel_3_4.setBounds(491, 11, 212, 91);
		panel_1.add(panel_3_4);
		panel_3_4.setLayout(null);

		seances = new JLabel("");
		seances.setForeground(new Color(255, 255, 255));
		seances.setFont(new Font("Californian FB", Font.BOLD, 18));
		seances.setBounds(10, -11, 136, 91);
		panel_3_4.add(seances);
		seances.setIcon(new ImageIcon(home.class.getResource("/img/icons8-gym-49.png")));

		JLabel lblNewLabel_3_2 = new JLabel("Seances disponible");
		lblNewLabel_3_2.setBounds(31, 68, 158, 23);
		panel_3_4.add(lblNewLabel_3_2);
		lblNewLabel_3_2.setForeground(Color.WHITE);
		lblNewLabel_3_2.setFont(new Font("Californian FB", Font.BOLD, 20));

		JLabel lblNewLabel_2_2 = new JLabel("");
		lblNewLabel_2_2.setIcon(new ImageIcon(home.class.getResource("/img/img7.png")));
		lblNewLabel_2_2.setBounds(-196, 0, 408, 102);
		panel_3_4.add(lblNewLabel_2_2);

		JPanel panel_3_4_1 = new JPanel();
		panel_3_4_1.setLayout(null);
		panel_3_4_1.setBounds(254, 11, 212, 91);
		panel_1.add(panel_3_4_1);

		gain = new JLabel("");
		gain.setForeground(new Color(255, 255, 255));
		gain.setFont(new Font("Californian FB", Font.BOLD, 18));
		gain.setIcon(new ImageIcon(home.class.getResource("/img/money-bag-48.png")));
		gain.setBounds(10, -12, 129, 80);
		panel_3_4_1.add(gain);

		JLabel lblNewLabel_3_1_1 = new JLabel("Total des gains");
		lblNewLabel_3_1_1.setForeground(Color.WHITE);
		lblNewLabel_3_1_1.setFont(new Font("Californian FB", Font.BOLD, 20));
		lblNewLabel_3_1_1.setBounds(44, 68, 137, 23);
		panel_3_4_1.add(lblNewLabel_3_1_1);

		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setIcon(new ImageIcon(home.class.getResource("/img/img6.png")));
		lblNewLabel_2_1.setBounds(-129, 0, 341, 91);
		panel_3_4_1.add(lblNewLabel_2_1);

		JPanel panel_3_2 = new JPanel();
		panel_3_2.setLayout(null);
		panel_3_2.setBorder(null);
		panel_3_2.setBounds(727, 11, 212, 91);
		panel_1.add(panel_3_2);

		coachs = new JLabel("");
		coachs.setFont(new Font("Californian FB", Font.BOLD, 18));
		coachs.setForeground(new Color(255, 255, 255));
		coachs.setBounds(22, 0, 116, 68);
		panel_3_2.add(coachs);
		coachs.setIcon(new ImageIcon(home.class.getResource("/img/icons8-gym-51.png")));

		JLabel lblNewLabel_3_1_1_2 = new JLabel("Coach disponible");
		lblNewLabel_3_1_1_2.setBounds(32, 60, 156, 31);
		panel_3_2.add(lblNewLabel_3_1_1_2);
		lblNewLabel_3_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_3_1_1_2.setFont(new Font("Californian FB", Font.BOLD, 20));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		//
		//
		//
		JLabel lblNewLabel_2_3 = new JLabel("");
		lblNewLabel_2_3.setIcon(new ImageIcon(home.class.getResource("/img/img5 (1).png")));
		lblNewLabel_2_3.setBounds(0, 0, 212, 121);
		panel_3_2.add(lblNewLabel_2_3);
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 201, 500);
		contentPane.add(layeredPane);
		coachs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				dispose();

				new login().setVisible(true);

			}
		});

		JPanel panel_state = new JPanel();
		panel_state.setBounds(20, 120, 919, 370);
		panel_1.add(panel_state);
		panel_state.setLayout(null);

		JLabel lblNewLabel_4_1 = new JLabel("Equipement Statistiques");
		lblNewLabel_4_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_4_1.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_4_1.setBounds(611, 20, 212, 31);
		panel_state.add(lblNewLabel_4_1);

		JLabel lblNewLabel_7_ = new JLabel("");
		lblNewLabel_7_.setBounds(863, 322, 56, 48);
		panel_state.add(lblNewLabel_7_);
		lblNewLabel_7_.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();

				new login().setVisible(true);
			}
		});
		lblNewLabel_7_.setIcon(new ImageIcon(home.class.getResource("/img/icons8-logout-48.png")));

		JPanel panel_4_1_1 = new JPanel();
		panel_4_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					dispose();

					CourModel model = new CourModel();
					courView view = new courView();
					CourController controller = new CourController(model, view);
					view.setVisible(true);
					controller.table_load();
					new courView().setVisible(true);
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
		panel_4_1_1.setBounds(10, 387, 181, 42);
		layeredPane.add(panel_4_1_1);

		JLabel Statistiques_1_1_1 = new JLabel("Gestion Cours");
		Statistiques_1_1_1.setForeground(new Color(128, 128, 128));
		Statistiques_1_1_1.setFont(new Font("Californian FB", Font.BOLD | Font.ITALIC, 20));
		Statistiques_1_1_1.setBounds(20, 11, 133, 26);
		panel_4_1_1.add(Statistiques_1_1_1);

		JPanel panel_4_1_1_1 = new JPanel();
		panel_4_1_1_1.addMouseListener(new MouseAdapter() {
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
		panel_4_1_1_1.setLayout(null);
		panel_4_1_1_1.setForeground(Color.WHITE);
		panel_4_1_1_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255),

				new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		panel_4_1_1_1.setBackground(Color.WHITE);
		panel_4_1_1_1.setBounds(10, 434, 181, 42);
		layeredPane.add(panel_4_1_1_1);

		JLabel Statistiques_1_1_1_1 = new JLabel("Gestion Equipements");
		Statistiques_1_1_1_1.setForeground(Color.GRAY);
		Statistiques_1_1_1_1.setFont(new Font("Californian FB", Font.BOLD | Font.ITALIC, 20));
		Statistiques_1_1_1_1.setBounds(0, 11, 181, 26);
		panel_4_1_1_1.add(Statistiques_1_1_1_1);

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
		panel_4_1.setBounds(10, 295, 181, 42);
		layeredPane.add(panel_4_1);

		JLabel Statistiques_1_1 = new JLabel("Gestion Seances");
		Statistiques_1_1.setForeground(new Color(128, 128, 128));
		Statistiques_1_1.setFont(new Font("Californian FB", Font.BOLD | Font.ITALIC, 21));
		Statistiques_1_1.setBounds(20, 11, 161, 26);
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
		panel_6.setBounds(10, 341, 181, 42);
		layeredPane.add(panel_6);

		JLabel lblGestionPaiement = new JLabel("Gestion Paiements");
		lblGestionPaiement.setForeground(new Color(128, 128, 128));
		lblGestionPaiement.setFont(new Font("Californian FB", Font.BOLD | Font.ITALIC, 21));
		lblGestionPaiement.setBounds(10, 10, 171, 26);
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
		panel_5.setBounds(10, 250, 181, 42);
		layeredPane.add(panel_5);

		JLabel lblGestionCoach = new JLabel("Gestion Coachs\r\n");
		lblGestionCoach.setForeground(new Color(128, 128, 128));
		lblGestionCoach.setFont(new Font("Californian FB", Font.BOLD | Font.ITALIC, 21));
		lblGestionCoach.setBounds(21, 11, 150, 26);
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
		panel_4.setBounds(10, 205, 181, 42);
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
		panel.setBounds(10, 159, 181, 42);
		layeredPane.add(panel);
		panel.setLayout(null);

		JLabel Statistiques = new JLabel("Statistiques");
		Statistiques.setBounds(34, 11, 107, 26);
		panel.add(Statistiques);

		Statistiques.setForeground(new Color(128, 128, 128));
		Statistiques.setFont(new Font("Californian FB", Font.BOLD | Font.ITALIC, 21));

		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(coachView.class.getResource("/img/imageedit_1_8651314769 (1).png")));
		lblNewLabel_8.setBounds(0, 10, 178, 144);
		layeredPane.add(lblNewLabel_8);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(coachView.class.getResource("/img/img2.png")));
		lblNewLabel.setBounds(-50, 0, 251, 500);
		layeredPane.add(lblNewLabel);

	}
}
