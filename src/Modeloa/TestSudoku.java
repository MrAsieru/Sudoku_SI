package Modeloa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Observable;
import java.util.Observer;

import Egitura.GelaxkaEgitura;
import junit.framework.TestCase;

public class TestSudoku extends TestCase implements Observer{

	protected void setUp() throws Exception {
		super.setUp();
		
		Partida partida;
		Partida.getPartida();
		
		Irakurlea irakurlea= new Irakurlea();
		
		
		SudokuaGorde sg= new SudokuaGorde(1,irakurlea.getSudokuArrayHasiera(0),irakurlea.getSudokuArrayZuzena(0));
		
		Sudoku sud= new Sudoku(sg.getSoluzioa(),this);
		
		GelaxkaEditagarria gel = new GelaxkaEditagarria(1,2,3);
		
		int emaitza = 4;
		int notEspero = 10;
		
		
		
		assertEquals(4,gel.getBalioa());
		
		assertNotEquals(1,emaitza);

	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testOndoDago() {
		fail();
	}

	public void testAldatuGelaxkaBalioa() {
		fail();
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
