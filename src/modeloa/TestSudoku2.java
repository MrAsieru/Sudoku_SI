package modeloa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Observable;
import java.util.Observer;

import modeloa.sudokua.UnekoSudokua;
import junit.framework.TestCase;
import modeloa.sudokua.SudokuaGorde;

public class TestSudoku2 extends TestCase implements Observer{

	protected void setUp() throws Exception {
		Partida partida;
		Partida.getPartida();
		int zail=1;
		
		int [][] hasi=new int[][] {{9,4,0,3,0,1,7,8,5},{0,8,5,6,4,7,1,0,9},{1,0,2,9,5,8,4,6,0},
			{5,1,0,4,7,2,3,0,6},{6,9,4,1,8,0,0,7,2},{2,0,7,5,6,9,8,1,0},
			{0,0,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,0,0},{8,0,3,7,9,5,6,0,1}};
		
		int [][] fin = new int[][] {{9,4,6,3,2,1,7,8,5},{3,8,5,6,4,7,1,2,9},{1,7,2,9,5,8,4,6,3},
			{5,1,8,4,7,2,3,9,6},{6,9,4,1,8,3,5,7,2},{2,3,7,5,6,9,8,1,4},
			{4,5,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,5,8},{8,2,3,7,9,5,6,4,1}};
		
		
		SudokuaGorde sg= new SudokuaGorde(zail,hasi,fin);
		
		Partida.getPartida().setZailtasuna(zail);
		Partida.getPartida().sudokuBerria();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testOndoDago() {
		fail();
	}

	public void testAldatuGelaxkaBalioa() {
		
		int[][] finT = new int[][] {{9,4,6,3,2,1,7,8,5},{3,8,5,6,4,7,1,2,9},{1,7,2,9,5,8,4,6,3},
			{5,1,8,4,7,2,3,9,6},{6,9,4,1,8,3,5,7,2},{2,3,7,5,6,9,8,1,4},
			{4,5,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,5,8},{8,2,3,7,9,5,6,4,1}};
			
			
		int[][] hasiT = new int[][] {{9,4,0,3,0,1,7,8,5},{0,8,5,6,4,7,1,0,9},{1,0,2,9,5,8,4,6,0},
			{5,1,0,4,7,2,3,0,6},{6,9,4,1,8,0,0,7,2},{2,0,7,5,6,9,8,1,0},
			{0,0,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,0,0},{8,0,3,7,9,5,6,0,1}};
		
		UnekoSudokua sud= UnekoSudokua.getInstantzia();
		sud.aldatuGelaxkaBalioa(0, 2, 6);
		sud.aldatuGelaxkaBalioa(0, 4, 2);
		sud.aldatuGelaxkaBalioa(1, 0, 3);
		sud.aldatuGelaxkaBalioa(1, 7, 2);
		sud.aldatuGelaxkaBalioa(2, 1, 7);
		sud.aldatuGelaxkaBalioa(2, 2, 9);
		sud.aldatuGelaxkaBalioa(3, 2, 8);
		sud.aldatuGelaxkaBalioa(3, 7, 9);
		sud.aldatuGelaxkaBalioa(4, 5, 3);
		sud.aldatuGelaxkaBalioa(4, 6, 5);
		sud.aldatuGelaxkaBalioa(5, 1, 3);
		sud.aldatuGelaxkaBalioa(5, 8, 4);
		sud.aldatuGelaxkaBalioa(6, 0, 4);
		sud.aldatuGelaxkaBalioa(6, 1, 5);
		sud.aldatuGelaxkaBalioa(7, 7, 5);
		sud.aldatuGelaxkaBalioa(7, 8, 8);
		sud.aldatuGelaxkaBalioa(8, 1, 2);
		sud.aldatuGelaxkaBalioa(8, 7, 4);
		
		assertEquals(hasiT, finT);
	}

	public void testGelaxkaHautagaiaLortu() {
		fail("Not yet implemented");
	}

	public void testAldatuGelaxkaHautagaiak() {
		fail("Not yet implemented");
	}

	public void testHautagaiakKalkulatu() {
		fail("Not yet implemented");
	}

	public void testGetTamaina() {
		fail("Not yet implemented");
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}


