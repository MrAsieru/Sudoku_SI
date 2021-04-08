package Modeloa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import Egitura.GelaxkaEgitura;


public class Sudoku extends Observable{
	private Gelaxka[][] gelaxkaMat;
	private int tamaina = 9; //9x9 bada 9, 25x25 bada 25
	private int[][] soluzioa;
	private int zailtasuna = -1;
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

		bistaNotifikatu(NotifikazioMotak.TAULA_EGUNERATU, getGelaxkaBalioak(), getHasierakoBalioMaskara());
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
	
	public void aldatuGelaxkaBalioa(int e, int z, int pBalioa) {
		try{
			this.gelaxkaMat[e][z].setZenbakia(pBalioa);
			//Onartzen bada
			this.bistaNotifikatu(NotifikazioMotak.TAULA_EGUNERATU);
		} catch (GelaxkaEditagarriezinaException gee){
			this.bistaNotifikatu(NotifikazioMotak.EZIN_DA_BALIOA_ALDATU);
		}
	}
	
	public void aldatuGelaxkaHautagaiak(int e, int z, boolean[] pHautagaiak) {
		//TODO aldatuGelaxkaBalioa-rekin batu ahal da GelaxkaEgitura erabiliz
		// Taula aldatzen bada TAULA_EGUNERATU eta beharrezko balioak bidali
	}

	//TODO es al reves, [errenkada][zutabea], he cambiado el orden de los parametros para usarlo
	public void hautagaiakKalkulatu(int pErrenkada, int pZutabea){
		boolean[] haukerak = new boolean[this.tamaina];
		//Zutabeko zenbakiak deskartatu
		for (int i = 0; i<this.tamaina; i++){
			if(gelaxkaMat[pZutabea][i].getBalioa()!=0){
				haukerak[gelaxkaMat[pZutabea][i].getBalioa()-1] = false;
			}
		}
		//Errenkadeko zenbakiak deskartatu
		for (int i = 0; i<this.tamaina; i++){
			if(gelaxkaMat[i][pErrenkada].getBalioa()!=0){
				haukerak[gelaxkaMat[pZutabea][i].getBalioa()-1] = false;
			}
		}
		//Kuadranteko zenbakiak deskartatu
		ArrayList<Integer> kBalioak = getKuadranteBalioak(pZutabea, pErrenkada);
		for (Integer balioa : kBalioak) {
			haukerak[balioa - 1] = false;
		}
	}

	/*
	HARDCODE-ado para 9x9
	 */
	private int getKuadranteaZenbakia(int pErrenkada, int pZutabea){
		int kZerrenda = pErrenkada/3;
		int kZutabea = pZutabea/3;

		return kZerrenda*kZutabea;
	}

	private ArrayList<Integer> getKuadranteBalioak(int pErrenkada, int pZutabea){
		int pKuadrantea = getKuadranteaZenbakia(pErrenkada, pZutabea);
		//TODO generalizarlo para todo tipo de sudokus, demomento solo para 9x9. No prioritario
		ArrayList<Integer> gelaxkak = new ArrayList<>();
		int hasierakoGelaxkaZutabea = pKuadrantea/3 * 3;
		int hasierakoGelaxkaErrenkada = pKuadrantea%3/3 * 3;
		for (int i = hasierakoGelaxkaZutabea; i<3; i++){
			for (int j = hasierakoGelaxkaErrenkada; j<3; j++){
				if(this.gelaxkaMat[i][j].getBalioa()!=0){
					gelaxkak.add(this.gelaxkaMat[i][j].getBalioa());
				}
			}
		}
		return gelaxkak;
	}
	
	private GelaxkaEgitura[][] getGelaxkaBalioak(){
		// TODO
		// Balio bakarra bada: new GelaxkaEgitura(pBalioa); non pBalioa : Integer
		// Hautagaiak baditu: new GelaxkaEgitura(pHautagaiak); non pHautagaiak : int[][]
		int [][] emaitza = new int[this.tamaina][this.tamaina];
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				emaitza[i][j] = this.gelaxkaMat[i][j].getBalioa();
			}
		}
		return null;
	}
	
	private boolean[][] getHasierakoBalioMaskara(){
		boolean[][] emaitza = new boolean[this.tamaina][this.tamaina];
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				emaitza[i][j] = this.gelaxkaMat[i][j] instanceof GelaxkaHasierakoa;
			}
		}
		return emaitza;
	}

	private void bistaNotifikatu(NotifikazioMotak pMota, Object ... pArg){
		Object[] argumentuak = new Object[pArg.length + 1];
		argumentuak[0] = pMota;
		for (int i = 0; i < pArg.length; i++){
			argumentuak[i+1] = pArg[i];
		}
		setChanged();
		notifyObservers(argumentuak);
		System.out.println("[MODELOA]: Sudoku-k Bistari aldaketa notifikatu, mota: "+pMota.name());
	}

	public int getTamaina() {
		return tamaina;
	}
}
