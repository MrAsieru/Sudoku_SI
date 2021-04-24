package Modeloa;

import Bista.SudokuFrame;

import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.Observer;

public class Partida {
	private static Partida instantzia;
	private JFrame sudokuFrame;
	private Sudoku sudoku;
	private int[][] soluzioa;
	private int zailtasuna;
	private final int tamaina = 9;
	private String izena;

	private Partida(){
		this.sudokuFrame = new SudokuFrame();
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

	public Sudoku getSudoku(){
		return this.sudoku;
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

		/**
		SudokuRetrasado sudokuaGuztia = SudokuLista.getSudokuLista().getSudokua(this.zailtasuna);
		this.sudoku = new Sudoku(sudokuaGuztia.getHasierakoMatrizea(), (Observer) this.sudokuFrame);
		this.soluzioa = sudokuaGuztia.getSoluzioa();



		//Matrizea erabaki egingo dugu hasieratik eta ondoren honen matrizeak hartuko ditugu
		int sudokuMatrizePointer = Irakurlea.getIrakurlea().getZailtazunLerroa(this.zailtasuna);

		//Sudoku berria sortzeko Sudoku klaseari hasierako array-a eman
		this.sudoku = new Sudoku(Irakurlea.getIrakurlea().getSudokuArrayHasiera(sudokuMatrizePointer), (Observer) this.sudokuFrame);

		//Soluzio matrizea sortu
		this.soluzioa = Irakurlea.getIrakurlea().getSudokuArrayZuzena(sudokuMatrizePointer);

		System.out.println("[MODELOA.Partida]: Sudoku berria sortuta");
		 */

		SudokuaGorde sudokua = SudokuLista.getSudokuLista().getSudokuaZailtazunes(zailtasuna);
		this.sudoku = new Sudoku(sudokua.getHasierakoMatrizea(), (Observer) this.sudokuFrame);
		this.soluzioa = sudokua.getSoluzioa();
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
		return ondo;
	}
}