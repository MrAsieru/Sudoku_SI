package Modeloa;

import Bista.SudokuFrame;
import Modeloa.Support.Irakurlea;
import Modeloa.Support.NotifikazioMotak;
import Modeloa.Sudokua.UnekoSudokua;
import Modeloa.Sudokua.SudokuLista;
import Modeloa.Sudokua.SudokuaGorde;

import javax.swing.JFrame;
import java.time.Duration;
import java.time.Instant;
import java.util.Observer;

public class Partida {
	private static Partida instantzia;
	private int[][] soluzioa;
	private int zailtasuna;
	private final int tamaina = 9;
	private String izena;
	private Instant sudokuHasiera;
	public int laguntzaKant;

	private Partida(){
		Observer sudokuFrame = new SudokuFrame();
		UnekoSudokua.getInstantzia().addObserver(sudokuFrame);
	}

	public static Partida getPartida(){
		if (instantzia == null) {
			instantzia = new Partida();
		}
		return instantzia;
	}
	
	public void setIzena(String pIzena) {
		this.izena = pIzena;
		System.out.println(String.format("[MODELOA.Partida]: Jokalariaren izena zehaztuta: %s", pIzena));
	}
	
	public void setZailtasuna(int pZailtasuna) {
		if (1 <= pZailtasuna && pZailtasuna <= 3) {
			this.zailtasuna = pZailtasuna;
			System.out.println(String.format("[MODELOA.Partida]: Partidaren zailtasuna zehaztuta: %d", pZailtasuna));
		} else System.out.println(String.format("[MODELOA.Partida]: Ezin izan da partidaren zailtasuna zehaztu: %d", pZailtasuna));

	}

	/**
	 * Partida erabiltzen ari den zailtasuna handitzeko
	 * @param pHanditu true handitu nahi bada, false bestela
	 */
	public void zailtasunaHanditu(boolean pHanditu) {
		if (pHanditu) setZailtasuna(zailtasuna+1);
	}
	/**
	 * Gordeta dagoen zailtasunarekin sudoku berria sortzen da.
	 */
	public void sudokuBerria() {
		SudokuaGorde sudokua = SudokuLista.getSudokuLista().getSudokuaZailtasuna(zailtasuna);
		if (sudokua != null) {
			UnekoSudokua.getInstantzia().sudokuaSortu(sudokua.getHasierakoMatrizea());
			this.soluzioa = sudokua.getSoluzioa();
			laguntzaKant = 0;
			sudokuHasiera = Instant.now();
		} else {
			UnekoSudokua.getInstantzia().sudokuaEzinSortu();
		}
	}

	public void laguntzaEskatuta() { //TODO laguntza eskatzean honi deitu
		laguntzaKant++;
	}

	public boolean ondoDago(int[][] pBalioak) {
		boolean ondo = true;
		int i = 0;
		while(i<9 && ondo){
			int j = 0;
			while(j<9 && ondo){
				if(pBalioak[i][j] != soluzioa[i][j]){
					ondo = false;
				}
				j++;
			}
			i++;
		}
		System.out.println("[MODELOA.Partida]: "+((ondo)?"sudokua ondo ebatzi da":"sudokua ez da ondo ebatzi"));
		if (ondo) Irakurlea.getIrakurlea().rankingGorde(izena, zailtasuna, puntuazioaKalkulatu());
		return ondo;
	}

	private double puntuazioaKalkulatu(){
		long denbora = Duration.between(sudokuHasiera, Instant.now()).toSeconds();
		return (30000*zailtasuna/(denbora+(30*laguntzaKant)));
	}
}