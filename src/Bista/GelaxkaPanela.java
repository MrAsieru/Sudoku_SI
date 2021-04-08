package Bista;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

public class GelaxkaPanela extends JPanel {
	private JLabel[][] lblHautagaiak;
	private JLabel lblZenbakia;
	private JPanel pnlHautagaiak;
	private JLabel lblHautagaia;

	/**
	 * Create the panel.
	 */
	public GelaxkaPanela() {
		lblHautagaiak = new JLabel[3][3];
		
		add(getLblZenbakia());
		add(getPnlHautagaiak());
		
		setZenbakia(0);
		
	}
	
	public void setZenbakia(int pBalioa) {
		pnlHautagaiak.setVisible(false);
		lblZenbakia.setVisible(true);
		if (1 <= pBalioa && pBalioa <= 9) {
			lblZenbakia.setText(Integer.toString(pBalioa));
		} else lblZenbakia.setText("");		
		
		lblZenbakia.setForeground(Color.WHITE);
		switch(pBalioa){
			default:
			case 0:
				lblZenbakia.setBackground(Color.decode("#FFFFFF"));
				lblZenbakia.setForeground(Color.BLACK);
				break;
			case 1:
				lblZenbakia.setBackground(Color.decode("#303F9F"));
				lblZenbakia.setForeground(Color.WHITE);
				break;
			case 2:
				lblZenbakia.setBackground(Color.decode("#D32F2F"));
				lblZenbakia.setForeground(Color.WHITE);
				break;
			case 3:
				lblZenbakia.setBackground(Color.decode("#4CAF50"));
				lblZenbakia.setForeground(Color.BLACK);
				break;
			case 4:
				lblZenbakia.setBackground(Color.decode("#7B1FA2"));
				lblZenbakia.setForeground(Color.WHITE);
				break;
			case 5:
				lblZenbakia.setBackground(Color.decode("#7C4DFF"));
				lblZenbakia.setForeground(Color.WHITE);
				break;
			case 6:
				lblZenbakia.setBackground(Color.decode("#1976D2"));
				lblZenbakia.setForeground(Color.WHITE);
				break;
			case 7:
				lblZenbakia.setBackground(Color.decode("#CDDC39"));
				lblZenbakia.setForeground(Color.BLACK);
				break;
			case 8:
				lblZenbakia.setBackground(Color.decode("#E64A19"));
				lblZenbakia.setForeground(Color.WHITE);
				break;
			case 9:
				lblZenbakia.setBackground(Color.decode("#795548"));
				lblZenbakia.setForeground(Color.WHITE);
				break;
		}
	}
	
	public void setHautagaiak(boolean[] pMatrizea) {
		lblZenbakia.setVisible(false);
		pnlHautagaiak.setVisible(true);
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				lblHautagaiak[i][j].setVisible(pMatrizea[i*3+j]);
			}
		}
	}

	private JLabel getLblZenbakia() {
		if (lblZenbakia == null) {
			lblZenbakia = new JLabel("0");
		}
		return lblZenbakia;
	}
	private JPanel getPnlHautagaiak() {
		if (pnlHautagaiak == null) {
			pnlHautagaiak = new JPanel();
			pnlHautagaiak.setLayout(new GridLayout(3, 3, 0, 0));
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					lblHautagaiak[i][j] = getLblHautagaia((i*3+j+1)+"");
					pnlHautagaiak.add(lblHautagaiak[i][j]);
				}
			}
			
		}
		return pnlHautagaiak;
	}
	private JLabel getLblHautagaia(String pBalioa) {
		lblHautagaia = new JLabel(pBalioa);
		lblHautagaia.setFont(new Font("Dialog", Font.BOLD, 8));
		return lblHautagaia;
	}
}
