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
import java.awt.event.*;
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

	private int aukZ = -1;
	private int aukE = -1;



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
					SudokuPanela frame = new SudokuPanela(new Random().nextInt(3)+1);
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
		
		Sudoku.getNireSudoku().addObserver(this);
		Sudoku.getNireSudoku().eraiki(pZailtasuna);
		System.out.println("[BISTA]: Aukeratutako zailtasuna: "+pZailtasuna);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent keyEvent) {
				aukeratutakoGelaxkaMugituTeklatuarekin(keyEvent);
			}
		});
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
		if (aukZ != -1 && aukE != -1){
			gelaxkaMatrizea[aukE][aukZ].setBorder(new LineBorder(Color.GRAY, 1));
			System.out.println("[BISTA]: Ertzak garbituta");
		}		
	}

	private void aukeratutakoGelaxkaMugituTeklatuarekin(KeyEvent keyEvent){
		System.out.println(keyEvent.getKeyCode());
		if (aukZ == -1 && aukE == -1) {
			aukeratutakoGelaxkaAldatu(0,0);
		} else {
			switch (keyEvent.getKeyCode()){
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_KP_LEFT:
					aukeratutakoGelaxkaAldatu(aukE,aukZ-1);
					System.out.println("[BISTA]: Teklatuan eskuinako gezia pultsatuta");
					break;
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_KP_RIGHT:
					aukeratutakoGelaxkaAldatu(aukE,aukZ+1);
					System.out.println("[BISTA]: Teklatuan ezkerreko gezia pultsatuta");
					break;
				case KeyEvent.VK_UP:
				case KeyEvent.VK_KP_UP:
					aukeratutakoGelaxkaAldatu(aukE-1,aukZ);
					System.out.println("[BISTA]: Teklatuan goiko gezia pultsatuta");
					break;
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_KP_DOWN:
					aukeratutakoGelaxkaAldatu(aukE+1,aukZ);
					System.out.println("[BISTA]: Teklatuan beheko gezia pultsatuta");
					break;
			}
		}
	}

	private void aukeratutakoGelaxkaAldatu(int pEr, int pZu){
		if (0 <= pEr && pEr <= 8 && 0 <= pZu && pZu <= 8){
			ertzakGarbitu();
			aukZ = pZu;
			aukE = pEr;
			gelaxkaMatrizea[aukE][aukZ].setBorder(new LineBorder(Color.RED, 1));
			System.out.println("[BISTA]: Gelaxka aukeratuta - er:"+aukE+", zu:"+aukZ);
		}
	}
	
	private void aukeratutakoGelaxkaAhaztu() {
		ertzakGarbitu();
		aukZ = -1; aukE = -1;
		System.out.println("[BISTA]: Aukeratutako gelaxka ahaztuta");
	}
	
	//GUI gelaxka aldatu
	private void taulaBalioaAldatu(int pEr, int pZu, int pBalioa) {
		String balioBerria = "";
		if (1 <= pBalioa && pBalioa <= 9) {
			balioBerria = Integer.toString(pBalioa);
		}
		gelaxkaMatrizea[pEr][pZu].setText(balioBerria);
		System.out.println("[BISTA]: Gelaxka aldatuta - er:"+pEr+", zu:"+pZu+", balioa:"+balioBerria);
	}
	
	//Sudoku-n garbitu
	private void eskatuGelaxkaGarbitu() {
		eskatuGelaxkaAldatu(0);
	}
	//Sudoku-n aldatu
	private void eskatuGelaxkaAldatu(int pBalioa) {
		if (aukZ != -1 && aukE != -1){
			eskatuGelaxkaAldatu(aukE, aukZ, pBalioa);
		} else {
			JOptionPane.showMessageDialog(contentPane, "Balio bat aldatzeko gelaxka bat aukeratu", "Zorionak!", JOptionPane.PLAIN_MESSAGE);
		}
	}
	//Sudoku-n aldatu
	private void eskatuGelaxkaAldatu(int pEr, int pZu, int pBalioa) {
		Sudoku.getNireSudoku().aldatuGelaxka(pEr, pZu, pBalioa);
		ftfBalioa.setText("");
		ftfBalioa.requestFocus();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Sudoku && arg instanceof NotifikazioMotak) {
			System.out.println("[BISTA]: Observer update deituta, "+((NotifikazioMotak) arg).name()+" komandoa");
			switch ((NotifikazioMotak) arg) {
			case TAULA_EGUNERATU:
				taulaEguneratu(((Sudoku) o).getGelaxkaBalioak(), ((Sudoku) o).getHasierakoBalioMaskara());
				break;
			case EMAITZA_ONDO_DAGO:
				JOptionPane.showMessageDialog(contentPane, "Sudokua ondo ebatzi da", "Zorionak!", JOptionPane.PLAIN_MESSAGE);
				break;
			case EMAITZA_TXARTO_DAGO:
				JOptionPane.showMessageDialog(contentPane, "Sudokua ez dago ondo ebatzita", "Errorea", JOptionPane.ERROR_MESSAGE);
				break;
			case EZIN_DA_BALIOA_ALDATU:
				JOptionPane.showMessageDialog(contentPane, "Aukeratu duzun gelaxka ezin da aldatu", "Errorea", JOptionPane.ERROR_MESSAGE);
			default:
				break;
			}			
		}
	}
	
	private void taulaEguneratu(int[][] pBal, boolean[][] pHasMask) {
		for (int er = 0; er < 9; er++) {
			for (int zu = 0; zu < 9; zu++) {
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
		JLabel lblGelaxka = new JLabel(" ");
		lblGelaxka.setBorder(new LineBorder(Color.GRAY, 1));
		lblGelaxka.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				aukeratutakoGelaxkaAldatu(3*x+i, 3*y+j);
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
						System.out.println("[KONTROLATZAILEA]: ftfBalioa-n enter sakatu, gelaxka aldatzeko eskatu, balioa:"+balioa);
						eskatuGelaxkaAldatu(balioa);
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(contentPane, "Mesedez, 1-9 tartean dagoen zenbaki bat sartu");
					}
				}
			});
			ftfBalioa.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent keyEvent) {
					aukeratutakoGelaxkaMugituTeklatuarekin(keyEvent);
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
						System.out.println("[KONTROLATZAILEA]: btnAldatu klikatuta, gelaxka aldatzeko eskatu, balioa:"+balioa);
						eskatuGelaxkaAldatu(balioa);
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
					System.out.println("[KONTROLATZAILEA]: btnGarbitu sakatu, gelaxka garbitzeko eskatu");
					eskatuGelaxkaGarbitu();
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
					System.out.println("[KONTROLATZAILEA]: btnKonprobatu klikatuta, sudokua konprobatzeko eskatu");
					Sudoku.getNireSudoku().ondoDago();
				}
			});
		}
		return btnKonprobatu;
	}
}
