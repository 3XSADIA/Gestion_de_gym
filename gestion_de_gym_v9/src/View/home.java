package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Year;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYBarDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

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
import Model.StatistiquesModel.Equipment;
import Model.StatistiquesModel.YearClients;
import Model.StatistiquesModel.YearGains;

public class home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JLabel seance;
	public JLabel coach;
	public JLabel client;
	public JLabel gains;
	public JLabel equipement;
	private PieChart pieChart;
	private ChartPanel chartPanel;
	private JFreeChart chart;
	private ChartPanel chartPanel_2;
	private JFreeChart chart_2;

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

//pie chart pour nombre des equipement
	public void setData(List<Equipment> equipmentList) {
		for (StatistiquesModel.Equipment equipment : equipmentList) {
			Color color = new Color((int) (Math.random() * 0x1000000));
			pieChart.addData(new ModelPieChart(equipment.getName(), equipment.getQuantity(), color));
		}
	}

// line chart pour le total de gains en focntion d'annee 
	public void setGainsData(List<YearGains> yearGainsList) {
		XYSeries series = new XYSeries("Gains");
		for (StatistiquesModel.YearGains yearGains : yearGainsList) {
			series.add(yearGains.getYear(), yearGains.getGains());
		}
		XYDataset dataset = new XYSeriesCollection(series);
		chart = ChartFactory.createXYLineChart("Total des gains par année", "Année", "Gains (Dhs)", dataset);
		chart.setBackgroundPaint(Color.WHITE);
		XYPlot plot = chart.getXYPlot();
		plot.getRenderer().setSeriesPaint(0, Color.BLUE);
		chartPanel.setChart(chart);

	}

// bar chart pour le total des clients en fonction des années

	public void setClientsData(List<YearClients> clientData) {
		XYSeries series = new XYSeries("Total des clients inscrits par année");
		for (YearClients yearClients : clientData) {
			series.add(new Year(yearClients.getYear()).getYear(), yearClients.getTotalClients());
		}
		XYDataset dataset = new XYSeriesCollection(series);
		IntervalXYDataset intervalDataset = new XYBarDataset(dataset, 1.0); // Assuming width of bars as 1.0
		JFreeChart chart = ChartFactory.createXYBarChart("Total des clients inscrits par année", "Année", true,
				"Nombre de clients", intervalDataset);
		chart.setBackgroundPaint(Color.WHITE);
		XYPlot plot = chart.getXYPlot();
		plot.getRenderer().setSeriesPaint(0, Color.BLUE);

		DateAxis domainAxis = (DateAxis) plot.getDomainAxis();
		domainAxis.setDateFormatOverride(new SimpleDateFormat("yyyy")); // Format the date to display only the year

		chartPanel_2.setChart(chart);
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
		contentPane.setLayout(new BorderLayout());
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 201, 500);
		contentPane.add(layeredPane);

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
		panel_4_1_1.setBackground(Color.WHITE);
		panel_4_1_1.setBounds(10, 444, 181, 42);
		layeredPane.add(panel_4_1_1);

		JLabel Statistiques_1_1_1 = new JLabel("Gestion Equipement");
		Statistiques_1_1_1.setForeground(new Color(128, 128, 128));
		Statistiques_1_1_1.setFont(new Font("Californian FB", Font.BOLD | Font.ITALIC, 20));
		Statistiques_1_1_1.setBounds(0, 11, 181, 26);
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
		panel_4_1.setBackground(Color.WHITE);
		panel_4_1.setBounds(10, 338, 181, 42);
		layeredPane.add(panel_4_1);

		JLabel Statistiques_1_1 = new JLabel("Gestion Seances");
		Statistiques_1_1.addMouseListener(new MouseAdapter() {
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
		Statistiques_1_1.setForeground(new Color(113, 106, 111));
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
		panel_6.setBackground(Color.WHITE);
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
		panel_5.setForeground(new Color(128, 128, 128));
		panel_5.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255),
				new Color(255, 255, 255), new Color(255, 255, 255)));
		panel_5.setBackground(Color.WHITE);
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
		panel_4.setBackground(Color.WHITE);
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
		panel.setBackground(Color.WHITE);
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

		JPanel panel_4_ = new JPanel();
		panel_4_.setBounds(31, 510, 1142, 0);
		layeredPane.add(panel_4_);
		panel_4_.setLayout(null);
		panel_4_.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255),
				new Color(255, 255, 255), new Color(255, 255, 255)));
		panel_4_.setBackground(new Color(255, 255, 255));
		JLabel lblNewLabel_4 = new JLabel("Equipement Statistiques");
		lblNewLabel_4.setBounds(64, 207, 212, 31);
		panel_4_.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Californian FB", Font.BOLD, 20));

		JPanel panel_1 = new JPanel();
		layeredPane.add(panel_1);
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(201, 10, 947, 476);
		panel_1.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(null);
		panel_3.setBounds(10, 10, 166, 78);
		panel_1.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Membres ");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Californian FB", Font.BOLD, 20));
		lblNewLabel_3.setBounds(30, 47, 143, 31);
		panel_3.add(lblNewLabel_3);

		client = new JLabel("");
		client.setForeground(new Color(255, 255, 255));
		client.setHorizontalAlignment(SwingConstants.LEFT);
		client.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		client.setIcon(new ImageIcon(home.class.getResource("/img/conference-48.png")));
		client.setBounds(20, 0, 133, 57);
		panel_3.add(client);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(0, 0, 212, 111);
		lblNewLabel_2.setIcon(new ImageIcon(home.class.getResource("/img/img5 (1).png")));
		panel_3.add(lblNewLabel_2);

		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBounds(549, 10, 183, 78);
		panel_1.add(panel_3_1);
		panel_3_1.setLayout(null);

		JLabel lblNewLabel_3_2_2 = new JLabel("Equipement ");
		lblNewLabel_3_2_2.setForeground(Color.WHITE);
		lblNewLabel_3_2_2.setFont(new Font("Californian FB", Font.BOLD, 20));
		lblNewLabel_3_2_2.setBounds(10, 47, 143, 31);
		panel_3_1.add(lblNewLabel_3_2_2);

		equipement = new JLabel("\r\n");
		equipement.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		equipement.setForeground(new Color(255, 255, 255));
		equipement.setIcon(new ImageIcon(home.class.getResource("/img/icons8-gym-48.png")));
		equipement.setBounds(10, 0, 166, 58);
		panel_3_1.add(equipement);

		JLabel lblNewLabel_2_1_1 = new JLabel("");
		lblNewLabel_2_1_1.setIcon(new ImageIcon(home.class.getResource("/img/img6.png")));
		lblNewLabel_2_1_1.setBounds(-182, 0, 463, 121);
		panel_3_1.add(lblNewLabel_2_1_1);

		JPanel panel_3_4 = new JPanel();
		panel_3_4.setBounds(186, 10, 166, 78);
		panel_1.add(panel_3_4);
		panel_3_4.setLayout(null);

		seance = new JLabel("");
		seance.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		seance.setForeground(new Color(255, 255, 255));
		seance.setBounds(10, 0, 168, 58);
		panel_3_4.add(seance);
		seance.setIcon(new ImageIcon(home.class.getResource("/img/icons8-gym-49.png")));

		JLabel lblNewLabel_3_2 = new JLabel("Seances ");
		lblNewLabel_3_2.setBounds(10, 47, 143, 31);
		panel_3_4.add(lblNewLabel_3_2);
		lblNewLabel_3_2.setForeground(Color.WHITE);
		lblNewLabel_3_2.setFont(new Font("Californian FB", Font.BOLD, 20));

		JLabel lblNewLabel_2_2 = new JLabel("");
		lblNewLabel_2_2.setIcon(new ImageIcon(home.class.getResource("/img/img7.png")));
		lblNewLabel_2_2.setBounds(-196, 0, 408, 121);
		panel_3_4.add(lblNewLabel_2_2);

		JPanel panel_3_4_1 = new JPanel();
		panel_3_4_1.setLayout(null);
		panel_3_4_1.setBounds(362, 10, 179, 78);
		panel_1.add(panel_3_4_1);

		gains = new JLabel("");
		gains.setForeground(new Color(255, 255, 255));
		gains.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		gains.setBounds(10, 0, 162, 48);
		panel_3_4_1.add(gains);
		gains.setIcon(new ImageIcon(home.class.getResource("/img/money-bag-48.png")));

		JLabel lblNewLabel_3_1_1 = new JLabel("Total des gains");
		lblNewLabel_3_1_1.setForeground(Color.WHITE);
		lblNewLabel_3_1_1.setFont(new Font("Californian FB", Font.BOLD, 20));
		lblNewLabel_3_1_1.setBounds(10, 47, 143, 31);
		panel_3_4_1.add(lblNewLabel_3_1_1);

		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setIcon(new ImageIcon(home.class.getResource("/img/img6.png")));
		lblNewLabel_2_1.setBounds(-129, 0, 341, 121);
		panel_3_4_1.add(lblNewLabel_2_1);

		JPanel panel_3_2 = new JPanel();
		panel_3_2.setLayout(null);
		panel_3_2.setBorder(null);
		panel_3_2.setBounds(742, 10, 183, 78);
		panel_1.add(panel_3_2);

		coach = new JLabel("");
		coach.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		coach.setForeground(new Color(255, 255, 255));
		coach.setBounds(10, 0, 164, 57);
		panel_3_2.add(coach);
		coach.setIcon(new ImageIcon(home.class.getResource("/img/icons8-gym-51.png")));

		JLabel lblNewLabel_3_1_1_2 = new JLabel("Coach");
		lblNewLabel_3_1_1_2.setBounds(10, 47, 143, 31);
		panel_3_2.add(lblNewLabel_3_1_1_2);
		lblNewLabel_3_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_3_1_1_2.setFont(new Font("Californian FB", Font.BOLD, 20));

		JLabel lblNewLabel_2_3 = new JLabel("");
		lblNewLabel_2_3.setIcon(new ImageIcon(home.class.getResource("/img/img5 (1).png")));
		lblNewLabel_2_3.setBounds(0, 0, 212, 121);
		panel_3_2.add(lblNewLabel_2_3);

		pieChart = new PieChart();
		pieChart.setBorder(new LineBorder(new Color(0, 0, 0)));
		pieChart.setBounds(456, 325, 469, 141);
		panel_1.add(pieChart);
		pieChart.setChartType(PieChart.PeiChartType.DONUT_CHART);
		pieChart.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
		pieChart.setPreferredSize(new Dimension(553, 200));

		chartPanel = new ChartPanel(chart);
		chartPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		chartPanel.setBounds(10, 98, 417, 368);
		chartPanel.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
		chartPanel.setPreferredSize(new Dimension(553, 200));
		panel_1.add(chartPanel);

		chartPanel_2 = new ChartPanel(chart_2);
		chartPanel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		chartPanel_2.setBackground(Color.green);
		chartPanel_2.setBounds(456, 98, 469, 219);
		chartPanel_2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
		chartPanel_2.setPreferredSize(new Dimension(553, 200));
		panel_1.add(chartPanel_2);

		JLabel lblNewLabel_4_1 = new JLabel("Equipement Statistiques");
		lblNewLabel_4_1.setBackground(new Color(240, 240, 240));
		lblNewLabel_4_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_4_1.setBounds(466, 325, 212, 31);
		panel_1.add(lblNewLabel_4_1);

		try {
			StatistiquesModel model = new StatistiquesModel();
			List<Equipment> equipmentList = model.retrieveData();
			setData(equipmentList);
			List<YearGains> yearGainsList = model.getGainsByYear();
			setGainsData(yearGainsList);
			List<YearClients> clientData = model.getClientsByYear();
			setClientsData(clientData);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}