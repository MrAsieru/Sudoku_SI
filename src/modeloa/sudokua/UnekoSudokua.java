package modeloa.sudokua;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import egitura.AldaketaEgitura;
import egitura.GelaxkaEgitura;
import egitura.LaguntzaEgitura;
import modeloa.Amaiera;
import modeloa.gelaxka.*;
import modeloa.Partida;
import modeloa.sudoku_teknikak.ListaTeknikak;
import modeloa.support.NotifikazioMotak;


public class UnekoSudokua extends Observable{
	private Gelaxka[][] gelaxkaMat;
	private final int tamaina = 9; //9x9 bada 9, 25x25 bada 25
	private static UnekoSudokua instantzia;

	private UnekoSudokua(){
		try{
			Logger.getLogger(this.getClass().getName()).setUseParentHandlers(false);
			Logger.getLogger(this.getClass().getName()).
					addHandler(new FileHandler(String.format("log/partida_%s.log",
							DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")
									.withZone(ZoneId.systemDefault()).format(new Date().toInstant())), true));
			Logger.getLogger(this.getClass().getName()).setLevel(Level.INFO);
		} catch (IOException e) {
			System.out.println("Ezin da logger-a sortu");
		}
	}

	public static UnekoSudokua getInstantzia() {
		if (instantzia == null) instantzia = new UnekoSudokua();
		return instantzia;
	}

	public void sudokuaSortu(int[][] pGelaxkak) {
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
			hautagaiak = ((GelaxkaEditagarria) gelaxkaMat[e][z]).getHautagaiakErab();
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
			((GelaxkaEditagarria) this.gelaxkaMat[e][z]).setHautagiakErab(pHautagaiak);
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
		boolean[] aukerak = new boolean[this.tamaina];
		for (int i = 0; i<aukerak.length; i++) {aukerak[i] = true;}

		boolean[] zutabeAukerak = this.zutabeHautagaiak(pZutabea);
		boolean[] errenkadaAukerak = this.errenkadaHautagaiak(pErrenkada);
		boolean[] kuadranteAukerak = this.eremuHautagaiak(pErrenkada, pZutabea);

		for (int i = 0; i<aukerak.length; i++){
			aukerak[i] = zutabeAukerak[i] && errenkadaAukerak[i] && kuadranteAukerak[i];
		}
		return aukerak;
	}

	public boolean[] errenkadaHautagaiak(int pErr){
		boolean[] hautagaiak = new boolean[9];
		for (int i = 0; i<hautagaiak.length; i++) {hautagaiak[i] = true;}
		for (int i = 0; i<this.tamaina; i++){
			if(gelaxkaMat[pErr][i].getBalioa()!=0){
				hautagaiak[gelaxkaMat[pErr][i].getBalioa()-1] = false;
			}
		}
		return hautagaiak;
	}

	public boolean[] zutabeHautagaiak(int pZut){
		boolean[] hautagaiak = new boolean[9];
		for (int i = 0; i<hautagaiak.length; i++) {hautagaiak[i] = true;}
		for (int i = 0; i<this.tamaina; i++){
			if(gelaxkaMat[i][pZut].getBalioa()!=0){
				hautagaiak[gelaxkaMat[i][pZut].getBalioa()-1] = false;
			}
		}
		return hautagaiak;
	}

	public boolean[] eremuHautagaiak(int pErr, int pZut){
		boolean[] hautagaiak = new boolean[9];
		for (int i = 0; i<hautagaiak.length; i++) {hautagaiak[i] = true;}
		boolean[] kuadranteBalioak = getKuadranteBalioak(pErr, pZut);
		for (int i = 0; i < kuadranteBalioak.length; i++) {
			if (kuadranteBalioak[i]) hautagaiak[i] = false;
		}
		return hautagaiak;
	}

	private boolean[] getKuadranteBalioak(int pErrenkada, int pZutabea){
		int pKuadrantea = (pErrenkada/3)*3+(pZutabea/3);
		boolean[] hautagaiak = new boolean[9];
		int hasiZutabea = pKuadrantea/3 * 3;
		int hasiErrenkada = pKuadrantea%3 * 3;//3 * 3;
		for (int errenkada = hasiZutabea; errenkada < hasiZutabea+3; errenkada++){
			for (int zutabea = hasiErrenkada; zutabea < hasiErrenkada+3; zutabea++){
				if(this.gelaxkaMat[errenkada][zutabea].getBalioa()!=0){
					hautagaiak[gelaxkaMat[errenkada][zutabea].getBalioa()-1] = true;
				}
			}
		}
		return hautagaiak;
	}

	public void laguntzaKalkulatu(){
		Partida.getPartida().laguntzaEskatuta();
		LaguntzaEgitura laguntzak = ListaTeknikak.getListaTeknikak().kalkulatuLaguntzak();
		for (AldaketaEgitura ald: laguntzak.aldaketak) {
			if (ald.balioa != -1) laguntzaGelaxkaBalioaIpini(ald.errenkada, ald.zutabea, ald.balioa);
			else if (ald.hautagaia != -1) laguntzaGelaxkatikHautagaiaKendu(ald.errenkada, ald.zutabea, ald.hautagaia);
		}

		this.bistaNotifikatu(NotifikazioMotak.TAULA_EGUNERATU, getGelaxkaBalioak(), getHasierakoBalioMaskara());
		bistaNotifikatu(NotifikazioMotak.LAGUNTZA_IRUDIKATU, laguntzak.logger);
	}

	private void laguntzaGelaxkaBalioaIpini(int e, int z, int pBalioa){
		if (this.gelaxkaMat[e][z] instanceof GelaxkaEditagarria){
			((GelaxkaEditagarria) this.gelaxkaMat[e][z]).setZenbakia(pBalioa);
		}
	}

	private void laguntzaGelaxkatikHautagaiaKendu(int e, int z, int pHautagaia) {
		if (this.gelaxkaMat[e][z] instanceof GelaxkaEditagarria && 0 <= pHautagaia && pHautagaia <= 8) {
			boolean[] hautagaiakTmp = ((GelaxkaEditagarria) this.gelaxkaMat[e][z]).getHautagaiakErab();
			hautagaiakTmp[pHautagaia] = false;
			((GelaxkaEditagarria) this.gelaxkaMat[e][z]).setHautagiakErab(hautagaiakTmp);
		}
	}

	/********************************************* Set/Get-errak *********************************************************/

	public int getTamaina() {
		return tamaina;
	}

	/**GelaxkaBalioak**/
	public GelaxkaEgitura[][] getGelaxkaBalioak(){
		// ToDo
		// Balio bakarra bada: new GelaxkaEgitura(pBalioa); non pBalioa : Integer
		// Hautagaiak baditu: new GelaxkaEgitura(pHautagaiak); non pHautagaiak : boolean[]
		GelaxkaEgitura[][] emaitza = new GelaxkaEgitura[this.tamaina][this.tamaina];
		for (int i = 0; i < this.tamaina; i++){
			for (int j = 0; j < this.tamaina; j++){
				GelaxkaEgitura gelaxka;
				if (this.gelaxkaMat[i][j] instanceof GelaxkaEditagarria && ((GelaxkaEditagarria) this.gelaxkaMat[i][j]).getBalioa() == 0){
					gelaxka = new GelaxkaEgitura(((GelaxkaEditagarria) this.gelaxkaMat[i][j]).getHautagaiakErab());
				} else {
					gelaxka = new GelaxkaEgitura(this.gelaxkaMat[i][j].getBalioa());
				}
				emaitza[i][j] = gelaxka;
			}
		}
		return emaitza;
	}

	public boolean[][][] getHautagaiakProg() {
		boolean[][][] hautagaiGuztiak = new boolean[9][9][9];
		for (int i = 0; i < this.tamaina; i++){
			for (int j = 0; j < this.tamaina; j++){
				if (this.gelaxkaMat[i][j] instanceof GelaxkaEditagarria && ((GelaxkaEditagarria) this.gelaxkaMat[i][j]).getBalioa() == 0){
					hautagaiGuztiak[i][j] = ((GelaxkaEditagarria) this.gelaxkaMat[i][j]).getHautagaiakErab();
				}
			}
		}
		return hautagaiGuztiak;
	}

	public Boolean[][] getGelaxkaHutsak() {
		Boolean[][] matrizeHutsak = new Boolean[9][9];
		for (int i = 0; i < 9; i++){
			matrizeHutsak[i] = Arrays.stream(UnekoSudokua.getInstantzia().getGelaxkaBalioak()[i]).map(p -> (p.balioa == null || p.balioa == 0)).toArray(t -> new Boolean[t]);
		}
		return matrizeHutsak;
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

	/*************************************** Bistarekin komunikatu *******************************************************/

	public void sudokuaEzinSortu(){
		bistaNotifikatu(NotifikazioMotak.EZIN_IZAN_DA_SUDOKUA_SORTU);
	}

	/**
	 * Modeloa Bistarekin komunikatzeko
	 * @param pMota Bidali nahi den notifikazioa
	 * @param pArg Notifikazioarekin joango diren objektuak
	 */
	private void bistaNotifikatu(NotifikazioMotak pMota, Object ... pArg){
		if (pMota == NotifikazioMotak.TAULA_EGUNERATU) hautagaiakEguneratu();
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