package Modeloa;

import javax.annotation.processing.FilerException;
import javax.swing.plaf.synth.SynthTextAreaUI;

import org.omg.CORBA.PRIVATE_MEMBER;

import java.io.FileNotFoundException;
import java.util.Observable;
import java.io.File;
import java.util.Scanner;

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

		//Irakurriko dugun fitxategia kargatu eta irakurri
		try {
			File txtFitxategia = new File("/res/sudoku.txt");
			Scanner irakurle = new Scanner(txtFitxategia);

			boolean aurkitua = false;
			String linea = irakurle.next();
			while (irakurle.hasNext() && aurkitua){
				if (linea.length()==1 && linea.equals(this.zailtasuna + "")){
					aurkitua = true;
				}
				linea = irakurle.next();
			}
			if(aurkitua){
				//Agian txt-ko fitxategia ez du 9x9-ko matrizea ondo sortua (bi aldiz)
				try {
					//Jokalariaren matrizea sortuko dugu
					for (int i = 0; i < zutabZenb; i++) {
						for (int inovacion = 0; inovacion < ilaraZenb; inovacion++) {
							//TODO esto de ponerle de donde es un poco chapucilla????
							Gelaxka gel = new Gelaxka(inovacion, i, true, (linea.toCharArray())[inovacion]);
							this.zerGelaxkak[inovacion][i] = gel;
						}
						linea = irakurle.next();
					}
					//Matrize finala sortuko dugu
					for (int i = 0; i < zutabZenb; i++) {
						for (int x = 0; x < ilaraZenb; x++) {
							this.soluzioaIntegers[x][i] = linea.toCharArray()[x];
						}
						linea = irakurle.next();
					}
				} catch (ArrayIndexOutOfBoundsException manolo) {
					System.out.println("txt-ko fitxategia ez dago zuzenki eginda.");
					manolo.printStackTrace();
				}
			}
			else {
				//TODO hobetu abiso hau
				System.out.println("Ez da aurkitu nahi zen zailtazuna");
				System.exit(1);
			}

		}
		catch (FileNotFoundException paco) {
			System.out.println("Ezin izan da fitxategia lortu.");
			paco.printStackTrace();
		}
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
		this.zerGelaxkak[z][e].setZenbakia(pBalioa);
	}
	
	//public int getGelaxkaBalioak(){
		//TODO
		//return 
	
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
