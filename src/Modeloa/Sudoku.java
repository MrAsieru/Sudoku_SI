package Modeloa;

import java.io.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;


public class Sudoku extends Observable{
	private Gelaxka[][] gelaxkaMat;
	private int tamaina = 9; //9x9 bada 9, 25x25 bada 25
	private int[][] soluzioa;
	private int zailtasuna = -1;
	private String txtFitxategiaPath = "res/sudoku.txt";
	private static Sudoku nireSudoku;
	
	private Sudoku() {
		this.gelaxkaMat = new Gelaxka[this.tamaina][this.tamaina];
		this.soluzioa = new int[this.tamaina][this.tamaina];
	}
	
	public static Sudoku getNireSudoku() {
		if (nireSudoku == null) {
			nireSudoku = new Sudoku();
		}
		return nireSudoku;
	}

	public void eraiki(int pZailtasuna){
		if (this.zailtasuna == -1){
			this.zailtasuna = pZailtasuna;
			sudokuSortu();
		}
	}

	/**
	 * Metodo honen bidez, zailtazunaren arabera sudokuak matrize bat hartu eta bere balio bezala gordeko ditu.
	 */
	private void sudokuSortu() {
		//Matrizea erabaki egingo dugu hasieratik eta ondoren honen matrizeak hartuko ditugu
		int sudokuMatrizeaLortu = Irakurlea.getIrakurlea().getZailtazunLerroa(this.zailtasuna);
		int[][] hasierakoSudokua = Irakurlea.getIrakurlea().getSudokuArrayHasiera(sudokuMatrizeaLortu);

		//Gelaxka matrizea sortu
		for (int i = 0; i < this.tamaina; i++) {
			for (int j = 0; j < this.tamaina; j++) {
				int balioa = hasierakoSudokua[i][j];
				Gelaxka gelaxka = GelaxkaFactory.getInstantzia().gelaxkaSortu(
						(balioa != 0) ? GelaxkaMotak.HASIERAKOA : GelaxkaMotak.EDITAGARRIA,
						j, i, balioa);
				this.gelaxkaMat[i][j] = gelaxka;
			}
		}

		//Soluzio matrizea sortu
		this.soluzioa = Irakurlea.getIrakurlea().getSudokuArrayZuzena(sudokuMatrizeaLortu);

		bistaNotifikatu(NotifikazioMotak.TAULA_EGUNERATU);
	}
	
	public void ondoDago() {
		boolean ondo = true;
		int i = 0;
		while(i<9 && ondo){
			int j = 0;
			while(j<9 && ondo){
				if(gelaxkaMat[i][j].getBalioa() != soluzioa[i][j]){
					ondo = false;
				}
				j++;
			}
			i++;
		}

		if (ondo) {
			this.bistaNotifikatu(NotifikazioMotak.EMAITZA_ONDO_DAGO);
		} else {
			this.bistaNotifikatu(NotifikazioMotak.EMAITZA_TXARTO_DAGO);
		}
	}
	
	public void aldatuGelaxka(int z, int e, int pBalioa) {
		try{
			this.gelaxkaMat[z][e].setZenbakia(pBalioa);
			//Onartzen bada
			this.bistaNotifikatu(NotifikazioMotak.TAULA_EGUNERATU);
		} catch (GelaxkaEditagarriezinaException gee){
			this.bistaNotifikatu(NotifikazioMotak.EZIN_DA_BALIOA_ALDATU);
		}
	}
	
	public int[][] getGelaxkaBalioak(){
		int [][] emaitza = new int[this.tamaina][this.tamaina];
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				emaitza[i][j] = this.gelaxkaMat[i][j].getBalioa();
			}
		}
		return emaitza;
	}
	
	public boolean[][] getHasierakoBalioMaskara(){
		boolean[][] emaitza = new boolean[this.tamaina][this.tamaina];
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				emaitza[i][j] = this.gelaxkaMat[i][j] instanceof GelaxkaHasierakoa;
			}
		}
		return emaitza;
	}

	private void bistaNotifikatu(NotifikazioMotak pMota){
		setChanged();
		notifyObservers(pMota);
		System.out.println("[MODELOA]: Sudoku-k Bistari aldaketa notifikatu, mota: "+pMota.name());
	}

	public int getTamaina() {
		return tamaina;
	}
}
