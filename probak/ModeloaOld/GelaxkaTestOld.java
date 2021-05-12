package ModeloaOld;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GelaxkaTestOld {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	// setZenbakia void denez ezin da konprobatu
	// egin daiteke zenbaki atributua begiratzen baina beste 
	// klase batean gaudenez ezin da private delako

	@Test
	public void testGetBalioa() throws GelaxkaEditagarriezinaException {
		
		Gelaxka gel=null;
		gel.setZenbakia(1);
		int espero1= gel.getBalioa();
		int notEspero1=3;
		int emaitza1 = 1;
		
		assertEquals(espero1,emaitza1);
		assertNotEquals(notEspero1, emaitza1);
		
		
		gel.setZenbakia(5);
		int espero2=gel.getBalioa();

		int notEspero2=3;
		int emaitza2 = gel.getBalioa();
		
		assertEquals(espero2,emaitza2);
		assertNotEquals(notEspero2, emaitza2);
	}


		// setHautagaiak void denez ezin da konprobatu
		// egin daiteke hautagai atributua begiratzen baina beste 
		// klase batean gaudenez ezin da private delako

	@Test
	public void testGetHautagiak() {
		
		Gelaxka gel = null;
		int luzeera = 9;
		boolean[] espero1 = new boolean[luzeera];
		boolean[] notEspero1= new boolean[luzeera];;
		boolean[] emaitza1 = gel.getHautagiak();
		int i=0;
		
		while(i<luzeera) {
			espero1[1]=emaitza1[i];
			notEspero1[i]=!emaitza1[i];			
			i++;
		}
		
		assertArrayEquals(espero1, emaitza1);
		
		i=0;
		boolean ondo=true;
		
		while(i<luzeera && ondo) {
			if(notEspero1[i]!=emaitza1[i]){
				ondo=false;
			}
			i++;
		}
		assertTrue(!ondo);
	}

}
