package modeloa;

import egitura.PuntuazioaEgitura;
import modeloa.support.Irakurlea;
import modeloa.sudokua.UnekoSudokua;
import modeloa.sudokua.SudokuLista;
import modeloa.sudokua.SudokuaGorde;

import java.time.Duration;
import java.time.Instant;

public class Partida {
	private static Partida instantzia;
	private int[][] soluzioa;
	private int zailtasuna;
	private final int tamaina = 9;
	private String izena;
	private Instant sudokuHasiera;
	public int laguntzaKant;

	private Partida(){}

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
	 */
	public void zailtasunaHanditu() {
		setZailtasuna(zailtasuna+1);
	}
	/**
	 * Gordeta dagoen zailtasunarekin sudoku berria sortzen da.
	 */
	public void sudokuBerria() {
		SudokuaGorde sudokua = SudokuLista.getSudokuLista().getSudokua(zailtasuna);
		if (sudokua != null) {
			UnekoSudokua.getInstantzia().sudokuaSortu(sudokua.getHasierakoMatrizea(), zailtasuna);
			this.soluzioa = sudokua.getSoluzioa();
			laguntzaKant = 0;
			sudokuHasiera = Instant.now();
		} else {
			UnekoSudokua.getInstantzia().sudokuaEzinSortu();
		}
	}

	public void laguntzaEskatuta() {
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
		if (ondo) Irakurlea.getIrakurlea().rankingGorde(new PuntuazioaEgitura(izena, zailtasuna, puntuazioaKalkulatu()));
		return ondo;
	}

	private double puntuazioaKalkulatu(){
		long denbora = Duration.between(sudokuHasiera, Instant.now()).getSeconds();
		return (30000.0*zailtasuna/(denbora+(30.0*laguntzaKant)));
	}
}