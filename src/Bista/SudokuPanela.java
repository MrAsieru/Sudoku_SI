package Bista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;

import Egitura.GelaxkaEgitura;
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
import javax.swing.JToggleButton;

public class SudokuPanela extends JFrame implements Observer{

	private final JPanel contentPane;
	private JPanel pnlTaula;
	private final GelaxkaPanela[][] gelaxkaMatrizea;
	private JPanel pnlAukerak;
	private JPanel pnlBalioa;
	private JLabel lblBalioa;
	private JButton btnAldatu;
	private JPanel pnlAldatu;
	private JFormattedTextField ftfBalioa;
	private JPanel pnlAukKonprobatu;
	private JButton btnKonprobatu;
	private JButton btnGarbitu;
	private JPanel pnlGarbitu;
	private JPanel pnlAukGelaxka;

	private int aukZ = -1;
	private int aukE = -1;
	private JLabel lblNewLabel;
	private JPanel pnlHautagaiak;
	private JToggleButton[][] tgbHautagaiak;
	private JPanel pnlTaulaRatio;
	private JPanel pnlAukHautagaiak;
	private JButton btnAukHautagaiak;



	/*
	 * Gelaxkak identifikatzeko erabilitako erak:
	 * (x,y) eta (i,j): erlatiboa
	 * 		x eta y blokea adierazten dute, i eta j blokearen barruko kokapena
	 * 		x eta i errenkada adierazten dute, y eta j zutabea
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
					SudokuPanela frame = new SudokuPanela("main", new Random().nextInt(3)+1); //Ausaz zailtasun bat aueratu
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
	public SudokuPanela(String pIzena, int pZailtasuna) {
		gelaxkaMatrizea = new GelaxkaPanela[9][9];
		tgbHautagaiak = new JToggleButton[3][3];
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 339);
		setTitle("Sudoku");
		setIconImage(new ImageIcon("res/icon.png").getImage()); //Icono sudoku by Jeremiah / CC BY
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.add(getPnlTaulaRatio(), BorderLayout.NORTH);
		contentPane.add(getPnlTaulaRatio(), BorderLayout.CENTER);
		contentPane.add(getPnlAukerak(), BorderLayout.EAST);
		this.setVisible(true);

		//GUI taula sortu
		sortuTaula();

		//Sudoku sortu eta eraiki
		Sudoku.getNireSudoku().addObserver(this);
		Sudoku.getNireSudoku().eraiki(pZailtasuna);
		System.out.println("[BISTA]: Aukeratutako zailtasuna: "+pZailtasuna);

		//Teklatuko geziak erabiltzeko
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
						blk.add(getLblGelaxka_xy_ij(x, y, i, j));
					}
				}				
				getPnlTaula().add(blk);
			}
		}
		System.out.println("[BISTA]: Taula sortuta");
	}
	
	private void ertzakGarbitu() {
		if (aukZ != -1 && aukE != -1){
			gelaxkaMatrizea[aukE][aukZ].setBorder(new LineBorder(Color.GRAY, 1)); //Defektuzko bordea jarri
			System.out.println("[BISTA]: Ertzak garbituta");
		}		
	}

	private void aukeratutakoGelaxkaMugituTeklatuarekin(KeyEvent keyEvent){
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
	
	private void aukeratutakoGelaxkaAldatu(int pEr, int pZu){
		if (0 <= pEr && pEr <= 8 && 0 <= pZu && pZu <= 8){
			ertzakGarbitu();
			aukZ = pZu;
			aukE = pEr;
			gelaxkaMatrizea[aukE][aukZ].setBorder(new LineBorder(Color.RED, 1));
			eskatuHautagaiakEguneratu(pEr, pZu);
			
			System.out.println("[BISTA]: Gelaxka aukeratuta - er:"+aukE+", zu:"+aukZ);
		} else if (pEr < 0 || 8 < pEr) {
			aukeratutakoGelaxkaAldatu((pEr < 0)?0:8,pZu);
		} else if (pZu < 0 || 8 < pZu) {
			aukeratutakoGelaxkaAldatu(pEr, (pZu < 0)?0:8);
		} else {
			aukeratutakoGelaxkaAldatu(0,0);
		}
	}
	
	//Sudoku-n garbitu
	private void eskatuGelaxkaGarbitu() {
		eskatuBalioaAldatu(0);
	}
	//Sudoku-n aldatu
	private void eskatuBalioaAldatu(int pBalioa) {
		if (aukE != -1 && aukZ != -1){
			eskatuBalioaAldatu(aukE, aukZ, pBalioa);
		} else {
			JOptionPane.showMessageDialog(contentPane, "Balio bat aldatzeko gelaxka bat aukeratu", "Errorea", JOptionPane.ERROR_MESSAGE);
		}
	}
	//Sudoku-n aldatu
	private void eskatuBalioaAldatu(int pEr, int pZu, int pBalioa) {
		Sudoku.getNireSudoku().aldatuGelaxkaBalioa(pEr, pZu, pBalioa);
		ftfBalioa.setText("");
		ftfBalioa.requestFocus();
	}
	
	private void eskatuHautagaiakEguneratu(int pEr, int pZu) {
		
	}
	
	private void eskatuHautagaiakAldatu(boolean[] pHautagaiak) {
		if (aukE != -1 && aukZ != -1){
			Sudoku.getNireSudoku().aldatuGelaxkaHautagaiak(aukE, aukZ, pHautagaiak);
		} else {
			JOptionPane.showMessageDialog(contentPane, "Hautagaiak aldatzeko gelaxka bat aukeratu", "Errorea", JOptionPane.ERROR_MESSAGE);
			for (int i = 0; i < 3; i++){
				for (int j = 0; j < 3; j++){
					tgbHautagaiak[i][j].setSelected(false);
				}
			}
		}
	}
	
	private void eskatuHautagaiakLortu() {
		if (aukE != -1 && aukZ != -1){
			Sudoku.getNireSudoku().hautagaiakKalkulatu(aukE, aukZ);
		} else {
			JOptionPane.showMessageDialog(contentPane, "Hautagaiak lortzeko gelaxka bat aukeratu", "Errorea", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// arg -> Object[]:
		//			arg[0] -> NotifikazioMotak,
		//			arg[1,2,3,...] -> Datuak
		// Datu egiturak:
		// TAULA_EGUNERATU: 				GelaxkaEgitura[][], boolean[][]
		// EMAITZA_ONDO_DAGO:				ezer
		// EMAITZA_TXARTO_DAGO:				ezer
		// EZIN_DA_BALIOA_ALDATU:			ezer
		// EZIN_IZAN_DA_SUDOKUA_SORTU:		ezer
		// HAUTAGAIAK_EGUNERATU:			boolean[]
		if (o instanceof Sudoku && arg instanceof Object[] && ((Object[])arg).length > 0 && ((Object[])arg)[0] instanceof NotifikazioMotak) {
			switch((NotifikazioMotak)((Object[])arg)[0]) {
				case TAULA_EGUNERATU:
					if (((Object[])arg)[1] instanceof GelaxkaEgitura[][] && ((Object[])arg)[2] instanceof boolean[]){
						System.out.println("[Bista]: Taula eguneratu da");
						taulaEguneratu((GelaxkaEgitura[][]) ((Object[])arg)[1], (boolean[][]) ((Object[])arg)[2]);
					} else System.out.println("[Bista]: TAULA_EGUNERATU ez du eskatutakoa jaso");
					break;
				case EMAITZA_ONDO_DAGO:
					JOptionPane.showMessageDialog(contentPane, "Sudokua ondo ebatzi da", "Zorionak!", JOptionPane.PLAIN_MESSAGE);
					break;
				case EMAITZA_TXARTO_DAGO:
					JOptionPane.showMessageDialog(contentPane, "Sudokua ez dago ondo ebatzita", "Errorea", JOptionPane.ERROR_MESSAGE);
					break;
				case EZIN_DA_BALIOA_ALDATU:
					JOptionPane.showMessageDialog(contentPane, "Aukeratu duzun gelaxka ezin da aldatu", "Errorea", JOptionPane.ERROR_MESSAGE);
					break;
				case EZIN_IZAN_DA_SUDOKUA_SORTU:
					JOptionPane.showMessageDialog(contentPane, "Ezin da sudokua sortu", "Errorea", JOptionPane.ERROR_MESSAGE);
					System.exit(0);
					break;
				case HAUTAGAIAK_EGUNERATU:
					if (((Object[])arg)[1] instanceof boolean[]){
						System.out.println("[Bista]: Hautagaiak eguneratu dira");
						hautagaiakEguneratu((boolean[]) ((Object[])arg)[1]);
					} else System.out.println("[Bista]: HAUTAGAIAK_EGUNERATU ez du eskatutakoa jaso");
					break;
				default:
					break;
			}
		}
	}
	//GUI taula aldatu
	private void taulaEguneratu(GelaxkaEgitura[][] pBal2, boolean[][] pHasMask) {
		for (int er = 0; er < 9; er++) {
			for (int zu = 0; zu < 9; zu++) {
				GelaxkaPanela lblGelaxka = gelaxkaMatrizea[er][zu];
				if (pBal2[er][zu].balioa != null) { //Balioa da
					if (pHasMask[er][zu]) { //Hasierako balioa da
						lblGelaxka.setFont(lblGelaxka.getFont().deriveFont(lblGelaxka.getFont().getStyle() | Font.BOLD)); //Beltzan jarri
					} else {
						lblGelaxka.setFont(lblGelaxka.getFont().deriveFont(lblGelaxka.getFont().getStyle() & ~Font.BOLD)); //Normal jarri
					}
					lblGelaxka.setZenbakia(pBal2[er][zu].balioa);
					System.out.println("[BISTA]: Gelaxka aldatuta - er:"+er+", zu:"+zu+", balioa:"+pBal2[er][zu].balioa);
				} else if (pBal2[er][zu].hautagaiak != null) { //Hautagaiak dira
					lblGelaxka.setHautagaiak(pBal2[er][zu].hautagaiak);
					System.out.println("[BISTA]: Gelaxka aldatuta - er:"+er+", zu:"+zu+", balioa:"+pBal2[er][zu].hautagaiak);
				}
			}
		}
		System.out.println("[BISTA]: Taula eguneratuta");
	}
	
	//GUI hautagaiak eguneratu
	private void hautagaiakEguneratu(boolean[] pHautagaiak) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				tgbHautagaiak[i][j].setSelected(pHautagaiak[i*3+j]);
			}
		}
	}
	
	//GUI elementuak
	private JPanel getPnlTaulaRatio() {
		if (pnlTaulaRatio == null) {
			pnlTaulaRatio = new JPanel();
			pnlTaulaRatio.add(getPnlTaula());
			GridBagLayout gbl_pnlTaulaRatio = new GridBagLayout();
			gbl_pnlTaulaRatio.columnWidths = new int[]{0};
			gbl_pnlTaulaRatio.rowHeights = new int[]{0};
			gbl_pnlTaulaRatio.columnWeights = new double[]{Double.MIN_VALUE};
			gbl_pnlTaulaRatio.rowWeights = new double[]{Double.MIN_VALUE};
			pnlTaulaRatio.setLayout(gbl_pnlTaulaRatio);
			pnlTaulaRatio.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					int tamaina = Math.min(pnlTaulaRatio.getWidth(), pnlTaulaRatio.getHeight());
					pnlTaula.setPreferredSize(new Dimension(tamaina, tamaina));
					pnlTaulaRatio.revalidate();
				}
			});
		}
		return pnlTaulaRatio;
	}
	
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
	private GelaxkaPanela getLblGelaxka_xy_ij(int x, int y, int i, int j) {
		GelaxkaPanela lblGelaxka = new GelaxkaPanela();
		lblGelaxka.setBorder(new LineBorder(Color.GRAY, 1));
		lblGelaxka.setOpaque(true);
		lblGelaxka.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				aukeratutakoGelaxkaAldatu(3*x+i, 3*y+j);
				getFtfBalioa().requestFocus();
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
			GridBagConstraints gbc_pnlAukGelaxka = new GridBagConstraints();
			gbc_pnlAukGelaxka.insets = new Insets(0, 0, 5, 0);
			gbc_pnlAukGelaxka.fill = GridBagConstraints.BOTH;
			gbc_pnlAukGelaxka.gridx = 0;
			gbc_pnlAukGelaxka.gridy = 0;
			pnlAukerak.add(getPnlAukGelaxka(), gbc_pnlAukGelaxka);
			GridBagConstraints gbc_pnlAukKonprobatu = new GridBagConstraints();
			gbc_pnlAukKonprobatu.insets = new Insets(0, 0, 5, 0);
			gbc_pnlAukKonprobatu.gridx = 0;
			gbc_pnlAukKonprobatu.gridy = 1;
			pnlAukerak.add(getPnlAukKonprobatu(), gbc_pnlAukKonprobatu);
			GridBagConstraints gbc_pnlAukHautagaiak = new GridBagConstraints();
			gbc_pnlAukHautagaiak.insets = new Insets(0, 0, 5, 0);
			gbc_pnlAukHautagaiak.fill = GridBagConstraints.BOTH;
			gbc_pnlAukHautagaiak.gridx = 0;
			gbc_pnlAukHautagaiak.gridy = 2;
			pnlAukerak.add(getPnlAukHautagaiak(), gbc_pnlAukHautagaiak);

		}
		return pnlAukerak;
	}
	private JPanel getPnlAukGelaxka() {
		if (pnlAukGelaxka == null) {
			pnlAukGelaxka = new JPanel();
			GridBagLayout gbl_pnlAukGelaxka = new GridBagLayout();
			gbl_pnlAukGelaxka.columnWidths = new int[] {130};
			gbl_pnlAukGelaxka.rowHeights = new int[] {0, 0, 0, 0, 0, 0};
			gbl_pnlAukGelaxka.columnWeights = new double[]{0.0};
			gbl_pnlAukGelaxka.rowWeights = new double[]{0.0, Double.MIN_VALUE, 0.0, 0.0, 0.0, 1.0};
			pnlAukGelaxka.setLayout(gbl_pnlAukGelaxka);
			
			GridBagConstraints gbc_pnlBalioa = new GridBagConstraints();
			gbc_pnlBalioa.insets = new Insets(0, 0, 5, 0);
			gbc_pnlBalioa.anchor = GridBagConstraints.NORTHWEST;
			gbc_pnlBalioa.gridx = 0;
			gbc_pnlBalioa.gridy = 0;
			pnlAukGelaxka.add(getPnlBalioa(), gbc_pnlBalioa);
			GridBagConstraints gbc_pnlAldatu = new GridBagConstraints();
			gbc_pnlAldatu.insets = new Insets(0, 0, 5, 0);
			gbc_pnlAldatu.anchor = GridBagConstraints.NORTHWEST;
			gbc_pnlAldatu.gridx = 0;
			gbc_pnlAldatu.gridy = 1;
			pnlAukGelaxka.add(getPnlAldatu(), gbc_pnlAldatu);
			GridBagConstraints gbc_pnlGarbitu = new GridBagConstraints();
			gbc_pnlGarbitu.insets = new Insets(0, 0, 5, 0);
			gbc_pnlGarbitu.anchor = GridBagConstraints.WEST;
			gbc_pnlGarbitu.fill = GridBagConstraints.VERTICAL;
			gbc_pnlGarbitu.gridx = 0;
			gbc_pnlGarbitu.gridy = 2;
			pnlAukGelaxka.add(getPnlGarbitu(), gbc_pnlGarbitu);
			pnlAukGelaxka.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED), "Gelaxka aukerak", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.RIGHT));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 5, 5, 0);
			gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 3;
			pnlAukGelaxka.add(getLblNewLabel(), gbc_lblNewLabel);
			GridBagConstraints gbc_pnlHautagaiak = new GridBagConstraints();
			gbc_pnlHautagaiak.anchor = GridBagConstraints.NORTHWEST;
			gbc_pnlHautagaiak.insets = new Insets(0, 10, 5, 0);
			gbc_pnlHautagaiak.gridx = 0;
			gbc_pnlHautagaiak.gridy = 4;
			pnlAukGelaxka.add(getPnlHautagaiak(), gbc_pnlHautagaiak);
		}
		return pnlAukGelaxka;
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
						eskatuBalioaAldatu(balioa);
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
						eskatuBalioaAldatu(balioa);
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

	private JPanel getPnlAukKonprobatu() {
		if (pnlAukKonprobatu == null) {
			pnlAukKonprobatu = new JPanel();
			pnlAukKonprobatu.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
			pnlAukKonprobatu.add(getBtnKonprobatu());
		}
		return pnlAukKonprobatu;
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
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Hautagaiak:");
		}
		return lblNewLabel;
	}
	private JPanel getPnlHautagaiak() {
		if (pnlHautagaiak == null) {
			pnlHautagaiak = new JPanel();
			pnlHautagaiak.setLayout(new GridLayout(3, 3, 0, 0));
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					tgbHautagaiak[i][j] = getTgbHaut((i*3+j+1)+"");
					pnlHautagaiak.add(tgbHautagaiak[i][j]);
				}
			}
		}
		return pnlHautagaiak;
	}
	
	private JToggleButton getTgbHaut(String pZenbakia) {
		JToggleButton tgbHaut = new JToggleButton(pZenbakia);
		tgbHaut.setMargin(new Insets(2, 7, 2, 7));
		tgbHaut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				System.out.println("[KONTROLATZAILEA]: Hautagaien JToggleButton bat klikatuta");
				boolean[] hautagaiak = new boolean[9];
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						hautagaiak[i*3+j] = tgbHautagaiak[i][j].isSelected();
					}
				}
				eskatuHautagaiakAldatu(hautagaiak);
			}
		});
		/*tgbHaut.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {

			}
		});*/
		return tgbHaut;
	}
	private JPanel getPnlAukHautagaiak() {
		if (pnlAukHautagaiak == null) {
			pnlAukHautagaiak = new JPanel();
			pnlAukHautagaiak.add(getBtnAukHautagaiak());
		}
		return pnlAukHautagaiak;
	}
	private JButton getBtnAukHautagaiak() {
		if (btnAukHautagaiak == null) {
			btnAukHautagaiak = new JButton("Hautagaiak");
			btnAukHautagaiak.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("[KONTROLATZAILEA]: btnAukHautagaiak klikatuta");
					int aukera = JOptionPane.showConfirmDialog(contentPane, "Gelaxkako hautagaiak lortu nahi dituzu?", "Hautagaiak lortu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					if (aukera == 0) {
						System.out.println(getBounds());
						System.out.println("[KONTROLATZAILEA]: hautagaiak kalkulatzen...");
						eskatuHautagaiakLortu();
					} else {}
				}
			});
		}
		return btnAukHautagaiak;
	}
}
