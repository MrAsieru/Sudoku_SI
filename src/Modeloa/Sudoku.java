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
							Gelaxka gel = new Gelaxka(i, j, zenbakia != 0, zenbakia);
							this.gelaxkaMat[i][j] = gel;
						}
						linea = irakurle.readLine();
					}
					//Matrize finala sortuko dugu
					for (int i = 0; i < ilaraZenb; i++) {
						for (int j = 0; j < zutabZenb; j++) {
							this.soluzioa[i][j] = linea.toCharArray()[i];
						}
						linea = irakurle.readLine();
					}

					//Sudokua sortu bada Panelari notifikatua izango da

					sudokuAldatuDa();

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

	private void sudokuAldatuDa(){
		setChanged();
		notifyObservers(NotifikazioMotak.TAULA_EGUNERATU);
		System.out.println("[MODELOA]: Sudoku-k Bistari aldaketa notifikatu");
	}
	
	public void ondoDago() {
		//TODO
		boolean konproba=true;
		int i=0;
		while (i<9 || !konproba){
			int j=0;
			while (j<9 || !konproba){
				if(gelaxkaMat[i][j].getBalioa()!=soluzioa[i][j]){
					konproba=false;
				}
				j++;
			}
			i++;
		}
		setChanged();
		if (konproba){
			notifyObservers(NotifikazioMotak.EMAITZA_ONDO_DAGO);
		}else{
			notifyObservers(NotifikazioMotak.EMAITZA_TXARTO_DAGO);
			}

	}
	
	public void aldatuGelaxka(int z, int e, int pBalioa) {
		//TODO
		//Onartzen bada, GUI abisatu
		if (this.gelaxkaMat[z][e].setZenbakia(pBalioa)){
			sudokuAldatuDa();
		}
	}
	
	public int[][] getGelaxkaBalioak(){
		int [][] emaitza = new int[9][9];
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				emaitza[i][j] = this.gelaxkaMat[i][j].getBalioa();
			}
		}
		return emaitza;
	}
	
	public boolean[][] getHasierakoBalioMaskara(){
		//TODO
		boolean[][] matrizea = new boolean[9][9];
		int i=0;
		while (i<9){
		    int j=0;
			while (j<9){
				if (this.gelaxkaMat[i][j].hasBalio()){
					matrizea[i][j]=true;
				}
			j++;
			}
		i++;
		}
		return matrizea;
	}
}
