package Modeloa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;

import Bista.AmaieraFrame;
import Egitura.PuntuazioaEgitura;
import Modeloa.Support.Irakurlea;

public class Amaiera extends Observable {
	public Amaiera() {
		new AmaieraFrame(this);
	}
	
	public void rankingLortu() {
		List<PuntuazioaEgitura> lista = Irakurlea.getIrakurlea().parseLeaderBoard();

		lista.stream().
				sorted((PuntuazioaEgitura o1, PuntuazioaEgitura o2) -> Double.compare(o1.getPuntuazioa(), o2.getPuntuazioa()));
		
		setChanged();
		notifyObservers(new Object[] {NotifikazioMotak.RANKING_EGUNERATU, lista});
	}
	
	public void rankingLortu(int pZailtasuna) {
		List<PuntuazioaEgitura> lista = Irakurlea.getIrakurlea().parseLeaderBoard();

		lista.stream().
				filter(p -> p.getZailtasuna()==pZailtasuna).
				sorted(
						(PuntuazioaEgitura o1, PuntuazioaEgitura o2) -> Double.compare(o1.getPuntuazioa(), o2.getPuntuazioa())
				);

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
