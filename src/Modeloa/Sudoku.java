package Modeloa;

import java.util.Observable;

public class Sudoku extends Observable{
	private Gelaxka[][] zerGelaxkak;
	private int[][] soluzioaIntegers;
	private int zailtasuna;
	private static Sudoku nireSudoku;
	
	private Sudoku(int pZailtasuna) {
		//TODO
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
	}
	
	public boolean ondoDago() {
		//TODO
		return false;
	}
	
	public void aldatuGelaxka(int z, int e, int pBalioa) {
		//TODO
		//Onartzen bada, GUI abisatu
	}
	
	public int[][] getGelaxkaBalioak(){
		//TODO
		return null;
	}
	
	public boolean[][] getHasierakoBalioMaskara(){
		//TODO
		return null;
	}
}
