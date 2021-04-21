package Bista;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Modeloa.Amaiera;

public class AmaieraFrame extends JFrame implements Observer{

	private JPanel contentPane;
	private JLabel lblIzenburua;
	private JPanel pnlIpar;
	private JPanel pnlHego;
	private JLabel lblPartidaBerria;
	private JLabel lblPartidaBerria2;
	private JPanel pnlBotoiak;
	private JButton btnBerriaOk;
	private JButton btnBerriaCancel;
	private Amaiera amaieraModeloa;

	/**
	 * Create the frame.
	 */
	public AmaieraFrame(Amaiera pAmaiera) {
		amaieraModeloa = pAmaiera;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.add(getPnlIpar(), BorderLayout.NORTH);
		contentPane.add(getPnlHego(), BorderLayout.SOUTH);
		setVisible(true);
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO 3.sprinterako		
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
			pnlIpar.add(getLblIzenburua());
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
			pnlBotoiak.add(getBtnBerriaOk());
			pnlBotoiak.add(getBtnBerriaCancel());
		}
		return pnlBotoiak;
	}
	private JButton getBtnBerriaOk() {
		if (btnBerriaOk == null) {
			btnBerriaOk = new JButton("OK");
			btnBerriaOk.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("[KONTROLATZAILEA]: btnBerriaOk klikatuta");
					setVisible(false);
					amaieraModeloa.sudokuaHasi();
				}
			});
		}
		return btnBerriaOk;
	}
	private JButton getBtnBerriaCancel() {
		if (btnBerriaCancel == null) {
			btnBerriaCancel = new JButton("Cancel");
			btnBerriaCancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("[KONTROLATZAILEA]: btnBerriaCancel klikatuta");
					amaieraModeloa.programaAmaitu();
				}
			});
		}
		return btnBerriaCancel;
	}
}
