package Bista;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Egitura.PuntuazioaEgitura;
import Modeloa.Amaiera;
import Modeloa.NotifikazioMotak;

import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class AmaieraFrame extends JFrame implements Observer{

	private JPanel contentPane;
	private JLabel lblIzenburua;
	private JPanel pnlIpar;
	private JPanel pnlHego;
	private JLabel lblPartidaBerria;
	private JLabel lblPartidaBerria2;
	private JPanel pnlBotoiak;
	private JButton btnBerriaBai;
	private JButton btnBerriaEz;
	private Amaiera amaieraModeloa;
	private JPanel pnlErdia;
	private JTable tblRanking;
	private DefaultTableModel dtmRanking;
	private JPanel pnlIparBotoi;
	private JLabel lblZailtasuna;
	private ButtonGroup btgZailtasuna;
	private JRadioButton rdbRankGuztiak;
	private JRadioButton rdbRank1;
	private JRadioButton rdbRank2;
	private JRadioButton rdbRank3;
	private JScrollPane scpRanking;

	/**
	 * Create the frame.
	 */
	public AmaieraFrame(Amaiera pAmaiera) {
		amaieraModeloa = pAmaiera;
		amaieraModeloa.addObserver(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.add(getPnlIpar(), BorderLayout.NORTH);
		contentPane.add(getPnlErdia(), BorderLayout.CENTER);
		contentPane.add(getPnlHego(), BorderLayout.SOUTH);
		setVisible(true);

		amaieraModeloa.rankingLortu();
	}
	// arg -> Object[]:
	//			arg[0] -> NotifikazioMotak,
	//			arg[1,2,3,...] -> Datuak
	// Datu egiturak:
	// RANKING_EGUNERATU: 	PuntuazioaEgitura[]
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Amaiera update o:"+o+" arg:"+arg);
		if (o instanceof Amaiera && arg instanceof Object[] && ((Object[])arg).length > 0 && ((Object[])arg)[0] instanceof NotifikazioMotak) {
			switch ((NotifikazioMotak)((Object[])arg)[0]) {
			case RANKING_EGUNERATU:
				if (((Object[])arg).length == 2 && ((Object[])arg)[1] instanceof ArrayList){
					taulaEguneratu((ArrayList<PuntuazioaEgitura>) ((Object[])arg)[1]);
				} else System.out.println("[BISTA.Amaiera]: RANKING_EGUNERATU ez du eskatutakoa jaso");
				break;
			default:
				break;
			}
		}
	}
	
	private void taulaEguneratu(ArrayList<PuntuazioaEgitura> pLista) {
		dtmRanking.setRowCount(0);
		int i = 1;
		for (PuntuazioaEgitura pe : pLista) {
			dtmRanking.addRow(new Object[] {i++, pe.izena, pe.zailtasuna, pe.puntuazioa});
		}
	}

	private JLabel getLblIzenburua() {
		if (lblIzenburua == null) {
			lblIzenburua = new JLabel("Sudokua ondo ebatzi duzu!");
		}
		return lblIzenburua;
	}
	private JPanel getPnlIpar() {
		if (pnlIpar == null) {
			pnlIpar = new JPanel();
			pnlIpar.setLayout(new BorderLayout(0, 0));
			pnlIpar.add(getLblIzenburua());
			pnlIpar.add(getPnlIparBotoi(), BorderLayout.SOUTH);
		}
		return pnlIpar;
	}
	private JPanel getPnlHego() {
		if (pnlHego == null) {
			pnlHego = new JPanel();
			GridBagLayout gbl_pnlHego = new GridBagLayout();
			gbl_pnlHego.columnWidths = new int[] {0};
			gbl_pnlHego.rowHeights = new int[] {0, 0, 0};
			gbl_pnlHego.columnWeights = new double[]{0.0};
			gbl_pnlHego.rowWeights = new double[]{0.0, 0.0, 0.0};
			pnlHego.setLayout(gbl_pnlHego);
			GridBagConstraints gbc_lblPartidaBerria = new GridBagConstraints();
			gbc_lblPartidaBerria.fill = GridBagConstraints.BOTH;
			gbc_lblPartidaBerria.insets = new Insets(0, 0, 5, 0);
			gbc_lblPartidaBerria.gridx = 0;
			gbc_lblPartidaBerria.gridy = 0;
			pnlHego.add(getLblPartidaBerria(), gbc_lblPartidaBerria);
			GridBagConstraints gbc_lblPartidaBerria2 = new GridBagConstraints();
			gbc_lblPartidaBerria2.fill = GridBagConstraints.BOTH;
			gbc_lblPartidaBerria2.insets = new Insets(0, 0, 5, 0);
			gbc_lblPartidaBerria2.gridx = 0;
			gbc_lblPartidaBerria2.gridy = 1;
			pnlHego.add(getLblPartidaBerria2(), gbc_lblPartidaBerria2);
			GridBagConstraints gbc_pnlBotoiak = new GridBagConstraints();
			gbc_pnlBotoiak.fill = GridBagConstraints.BOTH;
			gbc_pnlBotoiak.gridx = 0;
			gbc_pnlBotoiak.gridy = 2;
			pnlHego.add(getPnlBotoiak(), gbc_pnlBotoiak);
		}
		return pnlHego;
	}
	private JLabel getLblPartidaBerria() {
		if (lblPartidaBerria == null) {
			lblPartidaBerria = new JLabel("Beste partida bat jokatu nahi duzu?");
			lblPartidaBerria.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPartidaBerria;
	}
	private JLabel getLblPartidaBerria2() {
		if (lblPartidaBerria2 == null) {
			lblPartidaBerria2 = new JLabel("Hurrengo zailtasuneko sudokua kargatuko da.");
			lblPartidaBerria2.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPartidaBerria2;
	}
	private JPanel getPnlBotoiak() {
		if (pnlBotoiak == null) {
			pnlBotoiak = new JPanel();
			pnlBotoiak.add(getBtnBerriaBai());
			pnlBotoiak.add(getBtnBerriaEz());
		}
		return pnlBotoiak;
	}
	private JButton getBtnBerriaBai() {
		if (btnBerriaBai == null) {
			btnBerriaBai = new JButton("BAI");
			btnBerriaBai.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("[KONTROLATZAILEA]: btnBerriaOk klikatuta");
					setVisible(false);
					amaieraModeloa.sudokuaHasi();
				}
			});
		}
		return btnBerriaBai;
	}
	private JButton getBtnBerriaEz() {
		if (btnBerriaEz == null) {
			btnBerriaEz = new JButton("EZ, itxi");
			btnBerriaEz.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("[KONTROLATZAILEA]: btnBerriaCancel klikatuta");
					System.out.println(getBounds());
					amaieraModeloa.programaAmaitu();
				}
			});
		}
		return btnBerriaEz;
	}
	private JPanel getPnlErdia() {
		if (pnlErdia == null) {
			pnlErdia = new JPanel();
			pnlErdia.setLayout(new BorderLayout(0, 0));
			pnlErdia.add(getScpRanking(), BorderLayout.CENTER);
		}
		return pnlErdia;
	}
	private JScrollPane getScpRanking() {
		if (scpRanking == null) {
			scpRanking = new JScrollPane();
			scpRanking.setViewportView(getTblRanking());
		}
		return scpRanking;
	}
	private JTable getTblRanking() {
		if (tblRanking == null) {
			dtmRanking = new DefaultTableModel(new Object[][] {},
					new String[] {"Pos.", "Izena", "Zailtasuna", "Puntuazioa"}) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			tblRanking = new JTable(dtmRanking);
			tblRanking.getTableHeader().setReorderingAllowed(false);
		}
		return tblRanking;
	}
	private JPanel getPnlIparBotoi() {
		if (pnlIparBotoi == null) {
			pnlIparBotoi = new JPanel();
			pnlIparBotoi.add(getLblZailtasuna());
			btgZailtasuna = new ButtonGroup();
			pnlIparBotoi.add(getRdbRankGuztiak());
			pnlIparBotoi.add(getRdbRank1());
			pnlIparBotoi.add(getRdbRank2());
			pnlIparBotoi.add(getRdbRank3());
		}
		return pnlIparBotoi;
	}
	private JLabel getLblZailtasuna() {
		if (lblZailtasuna == null) {
			lblZailtasuna = new JLabel("Zailtasuna:");
		}
		return lblZailtasuna;
	}
	private JRadioButton getRdbRankGuztiak() {
		if (rdbRankGuztiak == null) {
			rdbRankGuztiak = new JRadioButton("Guztiak");
			btgZailtasuna.add(rdbRankGuztiak);
			rdbRankGuztiak.setSelected(true);
			rdbRankGuztiak.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					amaieraModeloa.rankingLortu();					
				}
			});
		}
		return rdbRankGuztiak;
	}
	
	private JRadioButton getRdbRank1() {
		if (rdbRank1 == null) {
			rdbRank1 = new JRadioButton("1");
			btgZailtasuna.add(rdbRank1);
			rdbRank1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					amaieraModeloa.rankingLortu(1);
				}
			});
		}
		return rdbRank1;
	}
	private JRadioButton getRdbRank2() {
		if (rdbRank2 == null) {
			rdbRank2 = new JRadioButton("2");
			btgZailtasuna.add(rdbRank2);
			rdbRank2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					amaieraModeloa.rankingLortu(2);
				}
			});
		}
		return rdbRank2;
	}
	private JRadioButton getRdbRank3() {
		if (rdbRank3 == null) {
			rdbRank3 = new JRadioButton("3");
			btgZailtasuna.add(rdbRank3);
			rdbRank3.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					amaieraModeloa.rankingLortu(3);
				}
			});
		}
		return rdbRank3;
	}
}
