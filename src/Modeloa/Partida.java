package Modeloa;

import Bista.LoginPanela;

import java.util.Observer;

public class Partida {
	private static Partida instantzia;
	private Sudoku sudoku;
	private int[][] soluzioa;
	private int zailtasuna;
	private final int tamaina = 9;

	private Partida(){

	}

	public static Partida getPartida(){
		if (instantzia == null) {
			instantzia = new Partida();
		}
		return instantzia;
	}

	public Sudoku getSudoku(){
		return this.sudoku;
	}

	public void sudokuBerria(int pZailtasuna, Observer pObs) {
		//Matrizea erabaki egingo dugu hasieratik eta ondoren honen matrizeak hartuko ditugu
		int sudokuMatrizePointer = Irakurlea.getIrakurlea().getZailtazunLerroa(this.zailtasuna);

		//Sudoku berria sortzeko Sudoku klaseari hasierako array-a eman
		this.sudoku = new Sudoku(Irakurlea.getIrakurlea().getSudokuArrayHasiera(sudokuMatrizePointer), pObs);

		//Soluzio matrizea sortu
		this.soluzioa = Irakurlea.getIrakurlea().getSudokuArrayZuzena(sudokuMatrizePointer);

		System.out.println("[MODELOA]: Sudoku taula sortuta");
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
		return ondo;
	}
}
