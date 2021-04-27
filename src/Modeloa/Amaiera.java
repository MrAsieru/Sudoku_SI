package Modeloa;

import java.util.Observable;

import Bista.AmaieraFrame;

public class Amaiera extends Observable {
	public Amaiera() {
		addObserver(new AmaieraFrame(this));
	}
	
	public void sudokuaHasi() {
		System.out.println("[MODELOA.Amaiera]: Sudoku berria hasiko da");
		Partida.getPartida().sudokuBerria();
	}
	
	public void programaAmaitu() {
		System.out.println("[MODELOA.Amaiera]: Programa itxiko da");
		System.exit(0);
	}
}
