package Bista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;

import Modeloa.NotifikazioMotak;
import Modeloa.Sudoku;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

@SuppressWarnings("serial")
public class SudokuPanela extends JFrame implements Observer{

	private JPanel contentPane;
	private JPanel pnlTaula;
	private JLabel[][] gelaxkaMatrizea;
	private Aukeratuta auk;
	private JPanel pnlAukerak;
	private JPanel pnlBalioa;
	private JLabel lblBalioa;
	private JButton btnAldatu;
	private JPanel pnlAldatu;
	private JFormattedTextField ftfBalioa;
	private JPanel pnlKonprobatu;
	private JButton btnKonprobatu;
	private JButton btnGarbitu;
	private JPanel pnlGarbitu;
	private JPanel pnlGelaxka;

	/*
	 * Gelaxkak identifikatzeko erabilitako erak:
	 * (i,j) eta (x,y): erlatiboa
	 * 		i eta j blokea adierazten dute, x eta y blokearen barruko kokapena
	 * 
	 * (zu, er): absolutua
	 * 		zuzenki gelaxkaren kokapena adierazten du
	 */
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SudokuPanela frame = new SudokuPanela(new Random().nextInt(2)+1);
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
	public SudokuPanela(int pZailtasuna) {
		gelaxkaMatrizea = new JLabel[9][9];
		auk = new Aukeratuta();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Sudoku");
		setIconImage(new ImageIcon("res/icon.png").getImage()); //Icono sudoku by Jeremiah / CC BY
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.add(getPnlTaula(), BorderLayout.CENTER);
		contentPane.add(getPnlAukerak(), BorderLayout.EAST);
		
		sortuTaula();
		
		Sudoku.getNireSudoku(pZailtasuna).addObserver(this);
	}
	
	//Logika
	private void sortuTaula() {		
		//Sortu bloke bakoitza
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				JPanel blk = getPnlBlk_xy();
				
				//Sortu gelaxka bakoitza
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						JLabel gelaxka = getLblGelaxka_xy_ij(x, y, i, j);
						blk.add(gelaxka);
					}
				}				
				getPnlTaula().add(blk);
			}
		}
		System.out.println("[BISTA]: Taula sortuta");
	}
	
	private void ertzakGarbitu() {
		if (auk.x != -1 && auk.y != -1 && auk.i != -1 && auk.j != -1) {
			gelaxkaMatrizea[3*auk.x+auk.i][3*auk.y+auk.j].setBorder(new LineBorder(Color.GRAY, 1));
			System.out.println("[BISTA]: Ertzak garbituta");
		}		
	}
	
	private void aukeratutakoGelaxkaAhaztu() {
		ertzakGarbitu();
		auk = new Aukeratuta();
		System.out.println("[BISTA]: Aukeratutako gelaxka ahaztuta");
	}
	
	//GUI gelaxka aldatu
	private void taulaBalioaAldatu(int er, int zu, int balioa) {
		String balioBerria = "";
		if (1 <= balioa && balioa <= 9) {
			balioBerria = Integer.toString(balioa);
		}
		gelaxkaMatrizea[er][zu].setText(balioBerria);
		System.out.println("[BISTA]: Gelaxka aldatuta - er:%s, zu:%s, balioa:%s".formatted(er, zu, balioBerria));
	}
	
	//Sudoku-n garbitu
	private void eskatuGelaxkaGarbitu() {
		eskatuGelaxkaAldatu(0);
	}
	//Sudoku-n aldatu
	private void eskatuGelaxkaAldatu(int pBalioa) {
		if (auk.x != -1 && auk.y != -1 && auk.i != -1 && auk.j != -1) {
			eskatuGelaxkaAldatu(3*auk.x+auk.i, 3*auk.y+auk.j, pBalioa);
		}
	}
	//Sudoku-n aldatu
	private void eskatuGelaxkaAldatu(int pEr, int pZu, int pBalioa) {
		Sudoku.getNireSudoku().aldatuGelaxka(pEr, pZu, pBalioa);
		ftfBalioa.setText("");
		ftfBalioa.requestFocus();
		aukeratutakoGelaxkaAhaztu();
	}
	
	private static class Aukeratuta{
		public int x;
		public int y;
		public int i;
		public int j;
		/*
		 * x eta y: Blokea identifikatzeko
		 * i eta j: Bloke barruko lekua identifikatzeko
		 */
		public Aukeratuta() {
			x = -1;
			y = -1;
			i = -1;
			j = -1;
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		//Beharrezkoa: balioen matrizea, hasierako zenbakien maskara matrizea
		if (o instanceof Sudoku) {
			switch ((NotifikazioMotak) arg) {
			case TAULA_EGUNERATU:
				taulaEguneratu(((Sudoku) o).getGelaxkaBalioak(), ((Sudoku) o).getHasierakoBalioMaskara());
				break;
			default:
				break;
			}			
		}
	}
	
	private void taulaEguneratu(int[][] pBal, boolean[][] pHasMask) {
		for (int er = 0; er < 3; er++) {
			for (int zu = 0; zu < 3; zu++) {
				JLabel lblGelaxka = gelaxkaMatrizea[er][zu];
				if (pHasMask[er][zu]) {
					lblGelaxka.setFont(lblGelaxka.getFont().deriveFont(lblGelaxka.getFont().getStyle() | Font.BOLD)); //Beltzan jarri
				} else {
					lblGelaxka.setFont(lblGelaxka.getFont().deriveFont(lblGelaxka.getFont().getStyle() & ~Font.BOLD)); //Normal jarri
				}
				taulaBalioaAldatu(er, zu, pBal[er][zu]);
			}
		}
		System.out.println("[BISTA]: Taula eguneratuta");
	}
	
	
	//GUI elementuak
	private JPanel getPnlTaula() {
		if (pnlTaula == null) {
			pnlTaula = new JPanel();
			pnlTaula.setLayout(new GridLayout(3, 3, 0, 0));
		}
		return pnlTaula;
	}
	private JPanel getPnlBlk_xy() {
		JPanel pnlBlk = new JPanel();
		pnlBlk.setLayout(new GridLayout(3, 3, 0, 0));
		pnlBlk.setBorder(new LineBorder(Color.BLACK, 1));
		return pnlBlk;
	}
	private JLabel getLblGelaxka_xy_ij(int x, int y, int i, int j) {
		JLabel lblGelaxka = new JLabel(Integer.toString(3*x+i)+Integer.toString(3*y+j));
		lblGelaxka.setBorder(new LineBorder(Color.GRAY, 1));
		lblGelaxka.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				ertzakGarbitu();
				auk.x = x;
				auk.y = y;
				auk.i = i;
				auk.j = j;
				lblGelaxka.setBorder(new LineBorder(Color.RED, 1));
				System.out.println("[BISTA]: Gelaxka aukeratuta - zu:%s, er:%s".formatted(3*x+i,3*y+j));
			}
		});
		gelaxkaMatrizea[3*x+i][3*y+j] = lblGelaxka; //Gelaxka matrizean gorde
		return lblGelaxka;
	}
	private JPanel getPnlAukerak() {
		if (pnlAukerak == null) {
			pnlAukerak = new JPanel();
			GridBagLayout gbl_pnlAukerak = new GridBagLayout();
			gbl_pnlAukerak.columnWidths = new int[] {69};
			gbl_pnlAukerak.rowHeights = new int[] {0, 0, 0, 0, 0, 0};
			gbl_pnlAukerak.columnWeights = new double[]{1.0};
			gbl_pnlAukerak.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
			pnlAukerak.setLayout(gbl_pnlAukerak);
			GridBagConstraints gbc_pnlGelaxka = new GridBagConstraints();
			gbc_pnlGelaxka.insets = new Insets(0, 0, 5, 0);
			gbc_pnlGelaxka.fill = GridBagConstraints.BOTH;
			gbc_pnlGelaxka.gridx = 0;
			gbc_pnlGelaxka.gridy = 0;
			pnlAukerak.add(getPnlGelaxka(), gbc_pnlGelaxka);
			GridBagConstraints gbc_pnlKonprobatu = new GridBagConstraints();
			gbc_pnlKonprobatu.insets = new Insets(0, 0, 5, 0);
			gbc_pnlKonprobatu.gridx = 0;
			gbc_pnlKonprobatu.gridy = 2;
			pnlAukerak.add(getPnlKonprobatu(), gbc_pnlKonprobatu);

		}
		return pnlAukerak;
	}
	private JPanel getPnlGelaxka() {
		if (pnlGelaxka == null) {
			pnlGelaxka = new JPanel();
			GridBagLayout gbl_pnlGelaxka = new GridBagLayout();
			gbl_pnlGelaxka.columnWidths = new int[] {130};
			gbl_pnlGelaxka.rowHeights = new int[] {0, 0};
			gbl_pnlGelaxka.columnWeights = new double[]{0.0};
			gbl_pnlGelaxka.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			pnlGelaxka.setLayout(gbl_pnlGelaxka);
			
			GridBagConstraints gbc_pnlBalioa = new GridBagConstraints();
			gbc_pnlBalioa.insets = new Insets(0, 0, 5, 5);
			gbc_pnlBalioa.anchor = GridBagConstraints.NORTHWEST;
			gbc_pnlBalioa.gridx = 0;
			gbc_pnlBalioa.gridy = 0;
			pnlGelaxka.add(getPnlBalioa(), gbc_pnlBalioa);
			GridBagConstraints gbc_pnlAldatu = new GridBagConstraints();
			gbc_pnlAldatu.insets = new Insets(0, 0, 5, 5);
			gbc_pnlAldatu.anchor = GridBagConstraints.NORTHWEST;
			gbc_pnlAldatu.gridx = 0;
			gbc_pnlAldatu.gridy = 1;
			pnlGelaxka.add(getPnlAldatu(), gbc_pnlAldatu);
			GridBagConstraints gbc_pnlGarbitu = new GridBagConstraints();
			gbc_pnlGarbitu.anchor = GridBagConstraints.WEST;
			gbc_pnlGarbitu.insets = new Insets(0, 0, 0, 5);
			gbc_pnlGarbitu.fill = GridBagConstraints.VERTICAL;
			gbc_pnlGarbitu.gridx = 0;
			gbc_pnlGarbitu.gridy = 3;
			pnlGelaxka.add(getPnlGarbitu(), gbc_pnlGarbitu);
			pnlGelaxka.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED), "Gelaxka aukerak", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.RIGHT));
		}
		return pnlGelaxka;
	}

	private JPanel getPnlBalioa() {
		if (pnlBalioa == null) {
			pnlBalioa = new JPanel();
			pnlBalioa.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
			pnlBalioa.add(getLblBalioa());
			pnlBalioa.add(getFtfBalioa());
		}
		return pnlBalioa;
	}
	private JLabel getLblBalioa() {
		if (lblBalioa == null) {
			lblBalioa = new JLabel("Balioa:");
		}
		return lblBalioa;
	}
	private JFormattedTextField getFtfBalioa() {
		if (ftfBalioa == null) {
			NumberFormatter formatua = new NumberFormatter(NumberFormat.getInstance()) {
				@Override
				public Object stringToValue(String text) throws ParseException {
					if (text.equals("")) return null; //Horrela balioak ezabatu ahal izango dira kutxan
					return super.stringToValue(text);
				}
			};
			formatua.setValueClass(Integer.class);
			formatua.setMinimum(1);
			formatua.setMaximum(9);
			formatua.setAllowsInvalid(false);
			ftfBalioa = new JFormattedTextField(formatua);
			ftfBalioa.setColumns(1);
			ftfBalioa.addActionListener(new ActionListener() { //Enter sartzerakoan balioa aldatzeko
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						int balioa = Integer.parseInt(ftfBalioa.getText());
						eskatuGelaxkaAldatu(balioa);
						System.out.println("[KONTROLATZAILEA]: ftfBalioa-n enter sakatu, gelaxka aldatzeko eskatu");
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(contentPane, "Mesedez, 1-9 tartean dagoen zenbaki bat sartu");
					}
				}
			});
		}
		return ftfBalioa;
	}
	
	private JPanel getPnlAldatu() {
		if (pnlAldatu == null) {
			pnlAldatu = new JPanel();
			pnlAldatu.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
			pnlAldatu.add(getBtnAldatu());
		}
		return pnlAldatu;
	}
	private JButton getBtnAldatu() {
		if (btnAldatu == null) {
			btnAldatu = new JButton("Aldatu");
			btnAldatu.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						int balioa = Integer.parseInt(ftfBalioa.getText());
						eskatuGelaxkaAldatu(balioa);
						System.out.println("[KONTROLATZAILEA]: btnAldatu klikatuta, gelaxka aldatzeko eskatu");
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(contentPane, "Mesedez, 1-9 tartean dagoen zenbaki bat sartu");
					}
					
				}
			});
		}
		return btnAldatu;
	}	
	private JPanel getPnlGarbitu() {
		if (pnlGarbitu == null) {
			pnlGarbitu = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlGarbitu.getLayout();
			flowLayout.setVgap(0);
			flowLayout.setHgap(10);
			pnlGarbitu.add(getBtnGarbitu());
		}
		return pnlGarbitu;
	}

	private JButton getBtnGarbitu() {
		if (btnGarbitu == null) {
			btnGarbitu = new JButton("Garbitu");
			btnGarbitu.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					eskatuGelaxkaGarbitu();
					System.out.println("[KONTROLATZAILEA]: btnGarbitu sakatu, gelaxka garbitzeko eskatu");
				}
			});
		}
		return btnGarbitu;
	}

	private JPanel getPnlKonprobatu() {
		if (pnlKonprobatu == null) {
			pnlKonprobatu = new JPanel();
			pnlKonprobatu.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
			pnlKonprobatu.add(getBtnKonprobatu());
		}
		return pnlKonprobatu;
	}
	private JButton getBtnKonprobatu() {
		if (btnKonprobatu == null) {
			btnKonprobatu = new JButton("Konprobatu");
			btnKonprobatu.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Sudoku.getNireSudoku().ondoDago();
					System.out.println("[KONTROLATZAILEA]: btnKonprobatu klikatuta, sudokua konprobatzeko eskatu");
				}
			});
		}
		return btnKonprobatu;
	}
}
