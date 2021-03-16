package Modeloa;

import javax.annotation.processing.FilerException;
import javax.swing.plaf.synth.SynthTextAreaUI;

import java.io.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;
import java.util.Random;


public class Sudoku extends Observable{
	private Gelaxka[][] zerGelaxkak;
	private int[][] soluzioaIntegers;
	private int zailtasuna;
	private static Sudoku nireSudoku;
	
	private Sudoku(int pZailtasuna) {
		this.zailtasuna = pZailtasuna;
		this.sudokuSortu();
	}
	
	public static Sudoku getNireSudoku(int pZailtasuna) {
		if (nireSudoku == null) {
			nireSudoku = new Sudoku(pZailtasuna);
		}
		return nireSudoku;
	}
	
	public static Sudoku getNireSudoku() {
		return nireSudoku;
	}

	private void sudokuSortu() {
		//TODO
		//Suposatuz Sudokua 9x9-koa izango dela
		int zutabZenb = 9;
		int ilaraZenb = 9;


		Integer irakurleLerroa = bilatuMatrizeIrakurlea();
		Scanner irakurle = new Scanner("res/sudoku.txt");

		//Bilatu nahi dugun matrizearen lerroa
		while (irakurle.hasNextLine() && irakurleLerroa!=0){
			irakurle.nextLine();
			irakurleLerroa--;
		}

		String linea = irakurle.nextLine().toString();

		//Agian txt-ko fitxategia ez du 9x9-ko matrizea ondo sortua (bi aldiz)
		try {
			//Jokalariaren matrizea sortuko dugu
			for (int i = 0; i < ilaraZenb; i++ ) {
				for (int jota = 0; jota < zutabZenb; jota++) {
					//TODO esto de ponerle de donde es un poco chapucilla????
					Gelaxka gel = new Gelaxka(i, jota, true, (linea.toCharArray())[i]);
					this.zerGelaxkak[i][jota] = gel;
				}
				linea = irakurle.next();
			}
			//Matrize finala sortuko dugu
			for (int i = 0; i < ilaraZenb; i++) {
				for (int jota = 0; jota < zutabZenb; jota++) {
					this.soluzioaIntegers[i][jota] = linea.toCharArray()[i];
				}
				linea = irakurle.next();
			}

			//Sudokua sortu bada Panelari notifikatua izango da
			sudokuAldatuDa();

		} catch (ArrayIndexOutOfBoundsException a) {
			System.out.println("txt-ko fitxategia ez dago zuzenki eginda.");
			a.printStackTrace();
		}
	}

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
	}
	
	public void ondoDago() {
		//TODO
		boolean konproba=true;
		int i=0;
		while (i<9 || !konproba){
			int j=0;
			while (j<9 || !konproba){
				if(zerGelaxkak[i][j].getBalioa()!=soluzioaIntegers[i][j]){
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
		if (this.zerGelaxkak[z][e].setZenbakia(pBalioa)){
			sudokuAldatuDa();
		}
	}
	
	public int[][] getGelaxkaBalioak(){
		int [][] emaitza = new int[9][9];
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				emaitza[i][j] = this.zerGelaxkak[i][j].getBalioa();
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
				if (this.zerGelaxkak[i][j].hasBalio()){
					matrizea[i][j]=true;
				}
			j++;
			}
		i++;
		}
		return matrizea;
	}
}
