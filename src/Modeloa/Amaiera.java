package Modeloa;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import Bista.AmaieraFrame;
import Egitura.PuntuazioaEgitura;

public class Amaiera extends Observable {
	public Amaiera() {
		addObserver(new AmaieraFrame(this));
	}
	
	public void rankingLortu() {
		ArrayList<PuntuazioaEgitura> lista;
		
		
		setChanged();
		notifyObservers(new Object[] {NotifikazioMotak.RANKING_EGUNERATU, lista});
	}
	
	public void rankingLortu(int pZailtasuna) {
		ArrayList<PuntuazioaEgitura> lista;
		
		
		setChanged();
		notifyObservers(new Object[] {NotifikazioMotak.RANKING_EGUNERATU, lista});
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
