package Modeloa;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Egitura.GelaxkaEgitura;


public class Sudoku extends Observable{
	private Gelaxka[][] gelaxkaMat;
	private final int tamaina = 9; //9x9 bada 9, 25x25 bada 25

	public Sudoku(int[][] pGelaxkak, Observer pObs) {
		this.addObserver(pObs);
		this.gelaxkaMat = new Gelaxka[this.tamaina][this.tamaina];

		//Gelaxka matrizea sortu
		for (int i = 0; i < this.tamaina; i++) {
			for (int j = 0; j < this.tamaina; j++) {
				int balioa = pGelaxkak[i][j];
				Gelaxka gelaxka = GelaxkaFactory.getInstantzia().gelaxkaSortu(
						(balioa != 0) ? GelaxkaMotak.HASIERAKOA : GelaxkaMotak.EDITAGARRIA,
						j, i, balioa);
				this.gelaxkaMat[i][j] = gelaxka;
			}
		}
		//Aldakuntzak bistari notifikatu
		hautagaiakEguneratu();
		for (int i = 0; i < this.tamaina; i++){
			for (int j = 0; j < this.tamaina; j++) {
				Gelaxka gel = gelaxkaMat[i][j];
				if (gel instanceof GelaxkaEditagarria && gel.getBalioa() == 0) {
					((GelaxkaEditagarria) gel).setHautagiakErab(((GelaxkaEditagarria) gel).getHautagaiakProg());
				}
			}
		}
		bistaNotifikatu(NotifikazioMotak.TAULA_EGUNERATU, getGelaxkaBalioak(), getHasierakoBalioMaskara());
	}


	/******************************************* Bista erabilitako metodoak *******************************************/

	/**
	 * Bista sudokua ondo ebatzita badagoen jakiteko
	 */
	public void ondoDago() {
		System.out.println("[MODELOA]: Sudokua konprobatuko da");
		int[][] balioak = new int[this.tamaina][this.tamaina];

		//Balioen matrizea lortu
		for (int i = 0; i < this.tamaina; i++){
			for (int j = 0; j < this.tamaina; j++) {
				balioak[i][j] = gelaxkaMat[i][j].getBalioa();
			}
		}

		//Partidari balioak bidali
		if (Partida.getPartida().ondoDago(balioak)) {
			this.bistaNotifikatu(NotifikazioMotak.EMAITZA_ONDO_DAGO);
			new Amaiera();
		} else {
			this.bistaNotifikatu(NotifikazioMotak.EMAITZA_TXARTO_DAGO);
		}
	}

	/**
	 * Bista gelaxka baten balioa aldatu ahal izateko
	 * @param e
	 * @param z
	 * @param pBalioa
	 */
	public void aldatuGelaxkaBalioa(int e, int z, int pBalioa) {
		if (this.gelaxkaMat[e][z] instanceof GelaxkaEditagarria){
			((GelaxkaEditagarria) this.gelaxkaMat[e][z]).setZenbakia(pBalioa);
			hautagaiakEguneratu();
			this.bistaNotifikatu(NotifikazioMotak.TAULA_EGUNERATU, getGelaxkaBalioak(), getHasierakoBalioMaskara());
		} else {
			this.bistaNotifikatu(NotifikazioMotak.EZIN_DA_BALIOA_ALDATU);
		}
	}

	/**
	 * Bista gelaxka baten hautagaiak lortzeko (Kalkulatu gabe)
	 * @param e
	 * @param z
	 */
	public void gelaxkaHautagaiaLortu(int e, int z){
		boolean[] hautagaiak = new boolean[9];
		if (gelaxkaMat[e][z] instanceof GelaxkaEditagarria) {
			hautagaiak = ((GelaxkaEditagarria) gelaxkaMat[e][z]).getHautagiak();
		}
		this.bistaNotifikatu(NotifikazioMotak.HAUTAGAIAK_EGUNERATU, hautagaiak);
	}

	/**
	 * Bista gelaxka baten hautagaiak aldatu ahal izateko
	 * @param e
	 * @param z
	 * @param pHautagaiak
	 */
	public void aldatuGelaxkaHautagaiak(int e, int z, boolean[] pHautagaiak) {
		if (this.gelaxkaMat[e][z] instanceof GelaxkaEditagarria) {
			((GelaxkaEditagarria) this.gelaxkaMat[e][z]).setHautagiak(pHautagaiak);
			this.bistaNotifikatu(NotifikazioMotak.TAULA_EGUNERATU, getGelaxkaBalioak(), getHasierakoBalioMaskara());
		} else {
			this.bistaNotifikatu(NotifikazioMotak.EZIN_DA_BALIOA_ALDATU);
		}
	}

	private void hautagaiakEguneratu() {
		for (int i = 0; i < tamaina; i++){
			for (int j = 0; j < tamaina; j++){
				Gelaxka gel = gelaxkaMat[i][j];
				if (gel instanceof GelaxkaEditagarria && gel.getBalioa() == 0) {
					((GelaxkaEditagarria) gel).setHautagiakProg(hautagaiakKalkulatu(i, j));
				}
			}
		}
	}

	/**
	 * Bista gelaxka baten hautagaiak kalkulatzeko
	 * @param pErrenkada
	 * @param pZutabea
	 */
	private boolean[] hautagaiakKalkulatu(int pErrenkada, int pZutabea){
		System.out.println("[MODELOA] Huatagaiak kalkulatuko ditugu:");
		String errenkadaSekuentzia = "\t Errenkada: (";
		String zutabeSekuentzia = "\t Zutabea: (";
		String kuadranteSekuentzia = "\t Kuadrantea: (";

		boolean[] aukerak = new boolean[this.tamaina];
		for (int i = 0; i<aukerak.length; i++) {aukerak[i] = true;}

		//Errenkada zenbakiak deskartatu
		for (int i = 0; i<this.tamaina; i++){
			if(gelaxkaMat[pErrenkada][i].getBalioa()!=0){
				aukerak[gelaxkaMat[pErrenkada][i].getBalioa()-1] = false;
			}
			errenkadaSekuentzia += " " + gelaxkaMat[pErrenkada][i].getBalioa();
		}
		//Zutabeko zenbakiak deskartatu
		for (int i = 0; i<this.tamaina; i++){
			if(gelaxkaMat[i][pZutabea].getBalioa()!=0){
				aukerak[gelaxkaMat[i][pZutabea].getBalioa()-1] = false;
			}
			zutabeSekuentzia += " " + gelaxkaMat[i][pZutabea].getBalioa();
		}
		//Kuadranteko zenbakiak deskartatu
		ArrayList<Integer> kBalioak = getKuadranteBalioak(pErrenkada, pZutabea);
		for (Integer balioa : kBalioak) {
			aukerak[balioa-1] = false;
			kuadranteSekuentzia += " " + balioa;
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
				if (this.gelaxkaMat[i][j] instanceof GelaxkaEditagarria && ((GelaxkaEditagarria) this.gelaxkaMat[i][j]).getHautagiak() != null){
					gelaxka = new GelaxkaEgitura(((GelaxkaEditagarria) this.gelaxkaMat[i][j]).getHautagiak());
				} else {
					gelaxka = new GelaxkaEgitura(this.gelaxkaMat[i][j].getBalioa());
				}
				emaitza[i][j] = gelaxka;
			}
		}
		return emaitza;
	}

	/**
	 * Sudokuaren hasieran zeuden balioak lortzeko maskara baten bitartez:
	 * true: Hasierako balio bat zen, false: Ez zen hasierako balio bat (hutsik zegoen)
	 * @return
	 */
	private boolean[][] getHasierakoBalioMaskara(){
		boolean[][] emaitza = new boolean[this.tamaina][this.tamaina];
		for (int i = 0; i < this.tamaina; i++){
			for (int j = 0; j < this.tamaina; j++){
				emaitza[i][j] = this.gelaxkaMat[i][j] instanceof GelaxkaHasierakoa;
			}
		}
		return emaitza;
	}


	/**GelaxkaHautagiak**/
	private ArrayList<Integer> getKuadranteBalioak(int pErrenkada, int pZutabea){
		int pKuadrantea = getKuadranteaZenbakia(pErrenkada, pZutabea);
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

	private int getKuadranteaZenbakia(int pErrenkada, int pZutabea){
		int kZerrenda = pErrenkada/3;
		int kZutabea = pZutabea/3;

		return kZerrenda*3+kZutabea;
	}

	/*************************************** Bistarekin komunikatu *******************************************************/

	/**
	 * Modeloa Bistarekin komunikatzeko
	 * @param pMota Bidali nahi den notifikazioa
	 * @param pArg Notifikazioarekin joango diren objektuak
	 */
	private void bistaNotifikatu(NotifikazioMotak pMota, Object ... pArg){
		Object[] argumentuak = new Object[pArg.length + 1];
		argumentuak[0] = pMota;
		for (int i = 0; i < pArg.length; i++){
			argumentuak[i+1] = pArg[i];
		}
		System.out.println("[MODELOA]: Sudoku-k Bistari aldaketa notifikatu, mota: "+pMota.name());
		setChanged();
		notifyObservers(argumentuak);
	}
}