package Bista;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;

public class GelaxkaPanela extends JPanel {
	private JLabel[][] lblHautagaiak;
	private JLabel lblZenbakia;
	private JPanel pnlHautagaiak;

	/**
	 * Create the panel.
	 */
	public GelaxkaPanela() {
		lblHautagaiak = new JLabel[3][3];
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0};
		gridBagLayout.rowHeights = new int[] {0};
		gridBagLayout.columnWeights = new double[]{0.0};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc_lblZenbakia = new GridBagConstraints();
		gbc_lblZenbakia.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblZenbakia.gridx = 0;
		gbc_lblZenbakia.gridy = 0;
		add(getLblZenbakia(), gbc_lblZenbakia);
		GridBagConstraints gbc_pnlHautagaiak = new GridBagConstraints();
		gbc_pnlHautagaiak.anchor = GridBagConstraints.NORTHWEST;
		gbc_pnlHautagaiak.gridx = 0;
		gbc_pnlHautagaiak.gridy = 0;
		add(getPnlHautagaiak(), gbc_pnlHautagaiak);
		
		setZenbakia(0,false);
	}
	
	public void setZenbakia(int pBalioa, boolean hasierakoa) {
		pnlHautagaiak.setVisible(false);
		lblZenbakia.setVisible(true);
		if (1 <= pBalioa && pBalioa <= 9) {
			lblZenbakia.setText(Integer.toString(pBalioa));
		} else lblZenbakia.setText("");		
		if (hasierakoa) lblZenbakia.setFont(lblZenbakia.getFont().deriveFont(lblZenbakia.getFont().getStyle() | Font.BOLD));
		else lblZenbakia.setFont(lblZenbakia.getFont().deriveFont(lblZenbakia.getFont().getStyle() & ~Font.BOLD));
		this.setForeground(Color.WHITE);
		switch(pBalioa){
			case 1:
				this.setBackground(Color.decode("#303F9F"));
				lblZenbakia.setForeground(Color.WHITE);
				break;
			case 2:
				this.setBackground(Color.decode("#D32F2F"));
				lblZenbakia.setForeground(Color.WHITE);
				break;
			case 3:
				this.setBackground(Color.decode("#4CAF50"));
				lblZenbakia.setForeground(Color.BLACK);
				break;
			case 4:
				this.setBackground(Color.decode("#7B1FA2"));
				lblZenbakia.setForeground(Color.WHITE);
				break;
			case 5:
				this.setBackground(Color.decode("#7C4DFF"));
				lblZenbakia.setForeground(Color.WHITE);
				break;
			case 6:
				this.setBackground(Color.decode("#1976D2"));
				lblZenbakia.setForeground(Color.WHITE);
				break;
			case 7:
				this.setBackground(Color.decode("#CDDC39"));
				lblZenbakia.setForeground(Color.BLACK);
				break;
			case 8:
				this.setBackground(Color.decode("#E64A19"));
				lblZenbakia.setForeground(Color.WHITE);
				break;
			case 9:
				this.setBackground(Color.decode("#795548"));
				lblZenbakia.setForeground(Color.WHITE);
				break;
			case 0:
			default:
				this.setBackground(Color.decode("#FFFFFF"));
				lblZenbakia.setForeground(Color.BLACK);
				break;
		}
	}
	
	public void setHautagaiak(boolean[] pMatrizea) {
		lblZenbakia.setVisible(false);
		this.setBackground(Color.decode("#FFFFFF"));
		pnlHautagaiak.setVisible(true);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				lblHautagaiak[i][j].setVisible(pMatrizea[i*3+j]);
			}
		}
		System.out.println(this.getBounds());
	}

	private JLabel getLblZenbakia() {
		if (lblZenbakia == null) {
			lblZenbakia = new JLabel("");
		}
		return lblZenbakia;
	}
	private JPanel getPnlHautagaiak() {
		if (pnlHautagaiak == null) {
			pnlHautagaiak = new JPanel();
			pnlHautagaiak.setBackground(Color.decode("#FFFFFF"));
			pnlHautagaiak.setLayout(new GridLayout(3, 3, 4, 0));
			pnlHautagaiak.setBounds(30,30,30,30);
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
		JLabel lblHautagaia = new JLabel(pBalioa);
		lblHautagaia.setBounds(10,10,10,10);
		lblHautagaia.setFont(new Font("Dialog", Font.BOLD, 8));
		return lblHautagaia;
	}
}
