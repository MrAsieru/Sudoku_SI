package modeloa;

import java.awt.EventQueue;
import java.util.Observable;

import bista.LoginFrame;
import modeloa.support.NotifikazioMotak;
import modeloa.support.Regex;

public class Login extends Observable {
	private static Login instantzia;
	
	private Login(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					addObserver(frame);
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
		if (Regex.getRegex().izenFormatua(pIzena)) {
			setChanged();
			notifyObservers(NotifikazioMotak.LOGIN_ZUZENA);
			System.out.println(String.format("[MODELOA.Login]: Jokalaria logeatuta: %s %d", pIzena, pZailtasuna));
			Partida.getPartida().setIzena(pIzena); //Izena gorde
			Partida.getPartida().setZailtasuna(pZailtasuna); //Zailtasuna gorde
			Partida.getPartida().sudokuBerria(); //Sudoku berria eratu
		} else {
			setChanged();
			notifyObservers(NotifikazioMotak.IZENA_BATERAEZINA);
		}
	}
}
