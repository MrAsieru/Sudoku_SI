package ModeloaOld;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SudokuTestOld implements Observer {

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
		
		int i=4;
		int j=3;
	
		Sudoku sud;
		int espero = sud.aldatuGelaxkaBalioa(i, j, 3);
		int emaitza = 3;
				
		assertEquals(espero,emaitza);
		assertNotEquals(espero+1,emaitza);
		assertNotEquals(espero-1,emaitza);
		
		
		
		i=1;
		j=2;
		int espero = sud.aldatuGelaxkaBalioa(i, j, 5);
		int emaitza = 5;
				
		assertEquals(espero,emaitza);
		assertNotEquals(espero+1,emaitza);
		assertNotEquals(espero-1,emaitza);
		
		i=8;
		j=1;
		int espero = sud.aldatuGelaxkaBalioa(i, j, 3);
		int emaitza = 5;
				
		assertNotEquals(espero,emaitza);
		assertNotEquals(espero,emaitza);
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
		int emaitza1 = Partida.getPartida().getSudoku().getKuadranteaZenbakia(1, 1);
		
		assertEquals(espero1, emaitza1);
		assertNotEquals(notespero1, emaitza1);
		
		int espero2 = 2;
		int notespero2 = 4;
		int emaitza2 = Partida.getPartida().getSudoku().getKuadranteaZenbakia(4, 1);
		
		assertEquals(espero2, emaitza2);
		assertNotEquals(notespero2, emaitza2);
	}
	
	@Test
	public void testGetKuadranteBalioak(){
		
		Gelaxka[][] esperotakoa = null;
		
		ArrayList<Integer> emaitza = Partida.getPartida().getSudoku().getKuadranteBalioak(0, 0);
	
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

