package Bista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class AmaieraPanela extends JPanel {
	private JLabel lblNewLabel;
	private JPanel pnlIpar;
	private JPanel pnlHego;
	private JLabel lblNewLabel_1;
	private JPanel pnlHegoAukerak;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;

	/**
	 * Create the panel.
	 */
	public AmaieraPanela() {
		setLayout(new BorderLayout(0, 0));
		add(getPnlIpar(), BorderLayout.NORTH);
		add(getPnlHego(), BorderLayout.SOUTH);
		
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Partida amaitu da");
		}
		return lblNewLabel;
	}
	private JPanel getPnlIpar() {
		if (pnlIpar == null) {
			pnlIpar = new JPanel();
			pnlIpar.add(getLblNewLabel());
		}
		return pnlIpar;
	}
	private JPanel getPnlHego() {
		if (pnlHego == null) {
			pnlHego = new JPanel();
			pnlHego.setLayout(new GridLayout(2, 1, 0, 0));
			pnlHego.add(getLblNewLabel_1());
			pnlHego.add(getPnlHegoAukerak());
		}
		return pnlHego;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Beste partida bat jokatu nahi duzu?");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel_1;
	}
	private JPanel getPnlHegoAukerak() {
		if (pnlHegoAukerak == null) {
			pnlHegoAukerak = new JPanel();
			pnlHegoAukerak.add(getRdbtnNewRadioButton());
			pnlHegoAukerak.add(getRdbtnNewRadioButton_1());
			pnlHegoAukerak.add(getRdbtnNewRadioButton_2());
		}
		return pnlHegoAukerak;
	}
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("Erreza (1)");
		}
		return rdbtnNewRadioButton;
	}
	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Ertaina (2)");
		}
		return rdbtnNewRadioButton_1;
	}
	private JRadioButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JRadioButton("Zaila (3)");
		}
		return rdbtnNewRadioButton_2;
	}
}
