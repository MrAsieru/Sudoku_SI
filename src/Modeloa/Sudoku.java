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

	private void sudokuSortu() {
		//Suposatuz Sudokua 9x9-koa izango dela
		int zutabZenb = this.tamaina;
		int ilaraZenb = this.tamaina;

		Integer irakurleLerroa = bilatuMatrizeIrakurlea();

		try{
			File txtFitxategia = new File(txtFitxategiaPath);
			BufferedReader irakurle = new BufferedReader(new FileReader(txtFitxategia));

			try{
				//Bilatu nahi dugun matrizearen lerroa
				irakurle.readLine();
				while (irakurleLerroa!=0){
					irakurle.readLine();
					irakurleLerroa--;
				}

				String linea = irakurle.readLine();

				try {
					//Jokalariaren matrizea sortuko dugu
					for (int i = 0; i < zutabZenb; i++ ) {
						for (int j = 0; j < ilaraZenb; j++) {
							int zenbakia = Character.getNumericValue(linea.toCharArray()[j]);
							Gelaxka gel = GelaxkaFactory.getInstantzia().gelaxkaSortu(
									(zenbakia != 0)?GelaxkaMotak.HASIERAKOA:GelaxkaMotak.EDITAGARRIA,
									j, i, zenbakia);
							this.gelaxkaMat[i][j] = gel;
						}
						linea = irakurle.readLine();
					}
					//Matrize finala sortuko dugu
					for (int i = 0; i < ilaraZenb; i++) {
						for (int j = 0; j < zutabZenb; j++) {
							this.soluzioa[i][j] = Character.getNumericValue(linea.toCharArray()[j]);
						}
						linea = irakurle.readLine();
					}

					//Sudokua sortu bada Panelari notifikatua izango da

					bistaNotifikatu(NotifikazioMotak.TAULA_EGUNERATU);

				} catch (ArrayIndexOutOfBoundsException a) {
					System.out.println("txt-ko fitxategia ez dago zuzenki eginda.");
					a.printStackTrace();
				}
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * txt fitxategia irakurriko dugu eta zailtasunaren zenbakia bilatuko dugu.
	 * Zenbaki hauen lerro zenbakia gordeko da eta Random erabiliz bat hartu egingo da,
	 * ondoren lerro zenbaki hori return egiteko.
	 */
	private Integer bilatuMatrizeIrakurlea(){
		ArrayList<Integer> matrizeGuztiak = new ArrayList<>();
		int lineCount = 0;
		try{
			//txt fitxategiko zailtazun berdineko matrizeak lortuko ditugu
			File txtFitxategia = new File("res/sudoku.txt");
			BufferedReader irakurle = new BufferedReader(new FileReader(txtFitxategia));

			String linea;
			while ((linea = irakurle.readLine()) != null){
				if (linea.length()==1 && linea.equals(this.zailtasuna + "")){
					matrizeGuztiak.add(lineCount);
				}
				lineCount++;
			}
		}
		catch (IOException e){
			System.out.println("Ez da lortu txt fitxategia.");
			e.printStackTrace();
		}

		//Begiratuko dugu matrizerik aurkitu ez dugun
		if(matrizeGuztiak.size()==0){
			System.exit(0);
		}

		//matrizea zoriz hartuko dugu eta bidali
		return matrizeGuztiak.get(new Random().nextInt(matrizeGuztiak.size()));
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
}
