package Modeloa;

import java.awt.EventQueue;

import Bista.LoginFrame;

public class Login {
	private static Login instantzia;
	
	private Login(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static Login getInstantzia() {
		if (instantzia == null) {
			instantzia = new Login();
		}
		return instantzia;
	}
	
	public void logeatu(String pIzena, int pZailtasuna) {
		System.out.println(String.format("[MODELOA.Login]: Jokalaria logeatuta: %s %d", pIzena, pZailtasuna));
		Partida.getPartida().setIzena(pIzena); //Izena gorde
		Partida.getPartida().setZailtasuna(pZailtasuna); //Zailtasuna gorde
		Partida.getPartida().sudokuBerria(); //Sudoku berria eratu
	}
}
