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
			this.zailtasuna = 1; //TODO aldatu debug bukatzean
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
		System.out.println("Finished");
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

	//TODO es al reves, [errenkada][zutabea], he cambiado el orden de los parametros para usarlo
	public boolean[] hautagaiakKalkulatu(int pErrenkada, int pZutabea){
		boolean[] aukerak = new boolean[this.tamaina];
		for (int i = 0; i<aukerak.length; i++) {aukerak[i] = true;}

		//Zutabeko zenbakiak deskartatu
		for (int i = 0; i<this.tamaina; i++){
			if(gelaxkaMat[pZutabea][i].getBalioa()!=0){
				aukerak[gelaxkaMat[pZutabea][i].getBalioa()-1] = false;
			}
		}
		//Errenkadeko zenbakiak deskartatu
		for (int i = 0; i<this.tamaina; i++){
			if(gelaxkaMat[i][pErrenkada].getBalioa()!=0){
				aukerak[gelaxkaMat[i][pErrenkada].getBalioa()-1] = false;
			}
		}
		//Kuadranteko zenbakiak deskartatu
		ArrayList<Integer> kBalioak = getKuadranteBalioak(pErrenkada, pZutabea);
		for (Integer balioa : kBalioak) {
			aukerak[balioa-1] = false;
		}
		return aukerak;
	}


	/********************************************* Set/Get-errak *********************************************************/

	public int getTamaina() {
		return tamaina;
	}

	/**GelaxkaBalioak**/
	private GelaxkaEgitura[][] getGelaxkaBalioak(){
		// ToDo
		// Balio bakarra bada: new GelaxkaEgitura(pBalioa); non pBalioa : Integer
		// Hautagaiak baditu: new GelaxkaEgitura(pHautagaiak); non pHautagaiak : boolean[]
		GelaxkaEgitura[][] emaitza = new GelaxkaEgitura[this.tamaina][this.tamaina];
		for (int i = 0; i < this.tamaina; i++){
			for (int j = 0; j < this.tamaina; j++){
				GelaxkaEgitura gelaxka;
				if (this.gelaxkaMat[i][j].getHautagiak()!=null){
					gelaxka = new GelaxkaEgitura(this.hautagaiakKalkulatu(i, j));
				}
				else {
					gelaxka = new GelaxkaEgitura(this.gelaxkaMat[i][j].getBalioa());
				}
				emaitza[i][j] = gelaxka;
			}
		}
		return emaitza;
	}

	private boolean[][] getHasierakoBalioMaskara(){
		boolean[][] emaitza = new boolean[this.tamaina][this.tamaina];
		for (int i = 0; i < this.tamaina; i++){
			for (int j = 0; j < this.tamaina; j++){
				emaitza[i][j] = this.gelaxkaMat[i][j] instanceof GelaxkaHasierakoa;
			}
		}
		return emaitza;
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


	/**GelaxkaHautagiak**/
	/*
	HARDCODE-ado para 9x9
	 */
	private ArrayList<Integer> getKuadranteBalioak(int pErrenkada, int pZutabea){
		int pKuadrantea = getKuadranteaZenbakia(pErrenkada, pZutabea);
		//TODO generalizarlo para todo tipo de sudokus, demomento solo para 9x9. No prioritario
		ArrayList<Integer> gelaxkak = new ArrayList<>();
		int hasiZutabea = pKuadrantea/3 * 3;
		int hasiErrenkada = pKuadrantea%3 * 3;//3 * 3;
		for (int Errenkada = hasiZutabea; Errenkada < hasiZutabea+3; Errenkada++){
			for (int Zutabea = hasiErrenkada; Zutabea < hasiErrenkada+3; Zutabea++){
				if(this.gelaxkaMat[Errenkada][Zutabea].getBalioa()!=0){
					gelaxkak.add(this.gelaxkaMat[Errenkada][Zutabea].getBalioa());
				}
			}
		}
		return gelaxkak;
	}

	/*
	HARDCODE-ado para 9x9
	 */
	private int getKuadranteaZenbakia(int pErrenkada, int pZutabea){
		int kZerrenda = pErrenkada/3;
		int kZutabea = pZutabea/3;

		return kZerrenda+kZutabea*3;
	}

	public void aldatuGelaxkaHautagaiak(int e, int z, boolean[] pHautagaiak) {
		//TODO aldatuGelaxkaBalioa-rekin batu ahal da GelaxkaEgitura erabiliz
		// Taula aldatzen bada TAULA_EGUNERATU eta beharrezko balioak bidali
		try{
			this.gelaxkaMat[e][z].setHautagiak(pHautagaiak);
			//Onartzen bada
			this.bistaNotifikatu(NotifikazioMotak.TAULA_EGUNERATU);
		} catch (GelaxkaEditagarriezinaException gee){
			this.bistaNotifikatu(NotifikazioMotak.EZIN_DA_BALIOA_ALDATU);
		}
	}

	/*************************************** Bistarekin komunikatu *******************************************************/

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
}
