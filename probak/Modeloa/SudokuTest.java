package Modeloa;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SudokuTest implements Observer {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOndoDago() {
		fail("Not yet implemented");
	}

	@Test
	public void testAldatuGelaxkaBalioa() {
		fail("Not yet implemented");
	}

	@Test
	public void testGelaxkaHautagaiaLortu() {
		fail("Not yet implemented");
	}

	@Test
	public void testAldatuGelaxkaHautagaiak() {
		fail("Not yet implemented");
	}

	@Test
	public void testHautagaiakKalkulatu() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetKoadrantearenZenbakia() {
		int espero1 = 1;
		int notespero1 = 2;
		int emaitza1 = Sudoku.getNireSudoku().getKuadranteaZenbakia(1, 1);
		
		assertEquals(espero1, emaitza1);
		assertNotEquals(notespero1, emaitza1);
		
		int espero2 = 2;
		int notespero2 = 4;
		int emaitza2 = Sudoku.getNireSudoku().getKuadranteaZenbakia(4, 1);
		
		assertEquals(espero2, emaitza2);
		assertNotEquals(notespero2, emaitza2);
	}
	
	@Test
	public void testGetKuadranteBalioak(){
		
		Gelaxka[][] esperotakoa = null;
		
		ArrayList<Integer> emaitza = Sudoku.getNireSudoku().getKuadranteBalioak(0, 0);
	
		int err,zut;
		zut = 0;
		boolean dago = true;
		
		//esperotakoa eta emaitza konparatu
		while(zut<3 && true) {
			err=0;
			while(err<3 && true) {
				if(esperotakoa[zut][err].getBalioa() != emaitza.get(zut)){
					dago=false;
				}else {
					err++;
				}
			}
			zut++;
		}
		
		//if baldintza arabera errorea adierazi
		if(!dago){
			fail();
		}
	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}

