package modeloa.sudoku_teknikak;
import java.util.ArrayList;
import java.util.List;

import egitura.AldaketaEgitura;
import egitura.LaguntzaEgitura;
import modeloa.sudokua.*;

public class SoleCandidate implements LaguntzaTeknika {
	public LaguntzaEgitura laguntzaKalkulatu() {
		Boolean[][] matrizeHutsak = UnekoSudokua.getInstantzia().getGelaxkaHutsak();
		List<String> laguntzak = new ArrayList<>();
		List<AldaketaEgitura> aldaketak = new ArrayList<>();
		for (int i = 0; i< UnekoSudokua.getInstantzia().getTamaina(); i++){
			for (int j = 0; j<UnekoSudokua.getInstantzia().getTamaina(); j++){
				Integer emaitza = laguntza(i, j);
				if (emaitza != null && matrizeHutsak[i][j]) {
					laguntzak.add("Teknika: Sole<br>" +
							String.format("Gelaxka: (%d, %d)<br>", i+1, j+1) +
							String.format("Balioa: %d<br>", emaitza));
					aldaketak.add(new AldaketaEgitura(i, j, emaitza, -1));
				}
			}
		}
		return new LaguntzaEgitura(laguntzak, aldaketak);
	}

	private Integer laguntza(int pErr, int pZut) {
		Integer laguntza=null;
		boolean [] errenkadaHaut= new boolean[9];
		boolean [] zutabeHaut= new boolean[9];
		boolean [] eremuHaut= new boolean[9];
		ArrayList <Integer> aukerak = new ArrayList<Integer>();
		errenkadaHaut = UnekoSudokua.getInstantzia().errenkadaHautagaiak(pErr);
	    zutabeHaut = UnekoSudokua.getInstantzia().zutabeHautagaiak(pZut);
		eremuHaut = UnekoSudokua.getInstantzia().eremuHautagaiak(pErr, pZut);
		for(int i=0; i<9 ;i++){
			if(errenkadaHaut[i] && zutabeHaut[i] && eremuHaut[i]){
				aukerak.add(i+1);
			}	
		}
		if (aukerak.size()==1){
			laguntza=aukerak.get(0);
		}
		return laguntza;
	}
}
