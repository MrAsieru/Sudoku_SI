package Bista;

import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class AmaieraPanela extends JPanel {
	private JLabel lblIzenburua;
	private JPanel pnlIpar;
	private JPanel pnlHego;
	private JLabel lblPartidaBerria;
	private JPanel pnlHegoAukerak;
	private ButtonGroup btgZailtasuna;
	private JRadioButton rdbErreza;
	private JRadioButton rdbErtaina;
	private JRadioButton rdbZaila;

	/**
	 * Create the panel.
	 */
	public AmaieraPanela(boolean pOndo) {
		setLayout(new BorderLayout(0, 0));
		add(getPnlIpar(), BorderLayout.NORTH);
		add(getPnlHego(), BorderLayout.SOUTH);
		
		getLblIzenburua().setText((pOndo)?"Sudokua ondo ebatzi duzu!":"Ez duzu sudokua ebatzi.");
	}
	
	public int getZailtasuna() {
		int zailtasuna = 0;
		
		if (getRdbErreza().isSelected()) zailtasuna = 1;
		else if (getRdbErtaina().isSelected()) zailtasuna = 2;
		else if (getRdbZaila().isSelected()) zailtasuna = 3;
		
		return zailtasuna;
	}

	private JLabel getLblIzenburua() {
		if (lblIzenburua == null) {
			lblIzenburua = new JLabel("");
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
			pnlHego.setLayout(new GridLayout(2, 1, 0, 0));
			pnlHego.add(getLblPartidaBerria());
			pnlHego.add(getPnlHegoAukerak());
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
	private JPanel getPnlHegoAukerak() {
		if (pnlHegoAukerak == null) {
			pnlHegoAukerak = new JPanel();
			btgZailtasuna = new ButtonGroup();
			pnlHegoAukerak.add(getRdbErreza());
			pnlHegoAukerak.add(getRdbErtaina());
			pnlHegoAukerak.add(getRdbZaila());
		}
		return pnlHegoAukerak;
	}
	private JRadioButton getRdbErreza() {
		if (rdbErreza == null) {
			rdbErreza = new JRadioButton("Erreza (1)");
			rdbErreza.setName("1");
			btgZailtasuna.add(rdbErreza);
		}
		return rdbErreza;
	}
	private JRadioButton getRdbErtaina() {
		if (rdbErtaina == null) {
			rdbErtaina = new JRadioButton("Ertaina (2)");
			rdbErtaina.setName("2");
			btgZailtasuna.add(rdbErtaina);
		}
		return rdbErtaina;
	}
	private JRadioButton getRdbZaila() {
		if (rdbZaila == null) {
			rdbZaila = new JRadioButton("Zaila (3)");
			rdbZaila.setName("3");
			btgZailtasuna.add(rdbZaila);
		}
		return rdbZaila;
	}
}
