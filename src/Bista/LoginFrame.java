package Bista;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modeloa.Login;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JPanel pnlDatuak;
	private JLabel lblIzena;
	private JTextField txfIzena;
	private JLabel lblZailtasuna;
	private JPanel pnlDatZailtasuna;
	private ButtonGroup btgZailtasuna;
	private JRadioButton rdbErreza;
	private JRadioButton rdbErtaina;
	private JRadioButton rdbZaila;
	private JPanel pnlHasi;
	private JButton btnHasi;

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 417, 160);
		setTitle("Sudoku");
		setIconImage(new ImageIcon("res/icon.png").getImage()); //Icono sudoku by Jeremiah / CC BY
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnlDatuak(), BorderLayout.CENTER);
		contentPane.add(getPnlHasi(), BorderLayout.SOUTH);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private JPanel getPnlDatuak() {
		if (pnlDatuak == null) {
			pnlDatuak = new JPanel();
			GridBagLayout gbl_pnlDatuak = new GridBagLayout();
			gbl_pnlDatuak.columnWidths = new int[] {0, 0};
			gbl_pnlDatuak.rowHeights = new int[] {0, 0};
			gbl_pnlDatuak.columnWeights = new double[]{0.0, 0.0};
			gbl_pnlDatuak.rowWeights = new double[]{0.0, 0.0};
			pnlDatuak.setLayout(gbl_pnlDatuak);
			GridBagConstraints gbc_lblIzena = new GridBagConstraints();
			gbc_lblIzena.insets = new Insets(0, 0, 5, 5);
			gbc_lblIzena.anchor = GridBagConstraints.EAST;
			gbc_lblIzena.gridx = 0;
			gbc_lblIzena.gridy = 0;
			pnlDatuak.add(getLblIzena(), gbc_lblIzena);
			GridBagConstraints gbc_txfIzena = new GridBagConstraints();
			gbc_txfIzena.insets = new Insets(0, 0, 5, 0);
			gbc_txfIzena.fill = GridBagConstraints.HORIZONTAL;
			gbc_txfIzena.gridx = 1;
			gbc_txfIzena.gridy = 0;
			pnlDatuak.add(getTxfIzena(), gbc_txfIzena);
			GridBagConstraints gbc_lblZailtasuna = new GridBagConstraints();
			gbc_lblZailtasuna.insets = new Insets(0, 0, 0, 5);
			gbc_lblZailtasuna.gridx = 0;
			gbc_lblZailtasuna.gridy = 1;
			pnlDatuak.add(getLblZailtasuna(), gbc_lblZailtasuna);
			GridBagConstraints gbc_pnlDatZailtasuna = new GridBagConstraints();
			gbc_pnlDatZailtasuna.fill = GridBagConstraints.BOTH;
			gbc_pnlDatZailtasuna.gridx = 1;
			gbc_pnlDatZailtasuna.gridy = 1;
			pnlDatuak.add(getPnlDatZailtasuna(), gbc_pnlDatZailtasuna);
		}
		return pnlDatuak;
	}
	private JLabel getLblIzena() {
		if (lblIzena == null) {
			lblIzena = new JLabel("Izena:");
		}
		return lblIzena;
	}
	private JTextField getTxfIzena() {
		if (txfIzena == null) {
			txfIzena = new JTextField();
			txfIzena.setColumns(10);
		}
		return txfIzena;
	}
	private JLabel getLblZailtasuna() {
		if (lblZailtasuna == null) {
			lblZailtasuna = new JLabel("Zailtasuna:");
		}
		return lblZailtasuna;
	}
	private JPanel getPnlDatZailtasuna() {
		if (pnlDatZailtasuna == null) {
			pnlDatZailtasuna = new JPanel();
			btgZailtasuna = new ButtonGroup();
			pnlDatZailtasuna.add(getRdbErreza());
			pnlDatZailtasuna.add(getRdbErtaina());
			pnlDatZailtasuna.add(getRdbZaila());
		}
		return pnlDatZailtasuna;
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
	private JPanel getPnlHasi() {
		if (pnlHasi == null) {
			pnlHasi = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlHasi.getLayout();
			pnlHasi.add(getBtnHasi());
		}
		return pnlHasi;
	}
	private JButton getBtnHasi() {
		if (btnHasi == null) {
			btnHasi = new JButton("Hasi");
			btnHasi.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (!txfIzena.getText().equals("") && btgZailtasuna.getSelection() != null) {
						int zailtasuna = -1;
						if (getRdbErreza().isSelected()) zailtasuna = 1;
						else if (getRdbErtaina().isSelected()) zailtasuna = 2;
						else if (getRdbZaila().isSelected()) zailtasuna = 3;
						
						System.out.println(String.format("[BISTA.Login]: Saio berria hasi, izena:%s, zailtasuna:%d", txfIzena.getText(), zailtasuna));
						setVisible(false);
						Login.getInstantzia().logeatu(txfIzena.getText(), zailtasuna);
					} else JOptionPane.showMessageDialog(contentPane, "Sudokua hasteko izen bat sartu eta zailtasun bat aukeratu!", "Errorea", JOptionPane.ERROR_MESSAGE);
				}
			});
		}
		return btnHasi;
	}
}
