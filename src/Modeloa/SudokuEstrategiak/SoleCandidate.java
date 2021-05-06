package Modeloa.SudokuEstrategiak;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Egitura.AldaketaEgitura;
import Egitura.LaguntzaEgitura;
import Modeloa.Sudokua.*;

public class SoleCandidate implements LaguntzaMetodoa {
	public LaguntzaEgitura laguntzaKalkulatu() {
		Boolean[][] matrizeHutsak = UnekoSudokua.getInstantzia().getGelaxkaHutsak();
		List<String> laguntzak = new ArrayList<>();
		List<AldaketaEgitura> aldaketak = new ArrayList<>();
		for (int i = 0; i< UnekoSudokua.getInstantzia().getTamaina(); i++){
			for (int j = 0; j<UnekoSudokua.getInstantzia().getTamaina(); j++){
				Integer emaitza = laguntza(i, j);
				if (emaitza != null && matrizeHutsak[i][j]) {
					laguntzak.add("Estrategia: Sole<br>" +
							"Gelaxka: (%d, %d)<br>".formatted(i+1, j+1) +
							"Balioa: %d<br>".formatted(emaitza));
					aldaketak.add(new AldaketaEgitura(i, j, emaitza, -1));
				}
			}
		}
		return new LaguntzaEgitura(laguntzak, aldaketak);
	}

	private Integer laguntza(int pErr, int pZut) {
		Integer laguntza=null;
		boolean [] help1= new boolean[9];
		boolean [] help2= new boolean[9];
		boolean [] help3= new boolean[9];
		ArrayList <Integer> aukerak = new ArrayList<Integer>();
		help1 = UnekoSudokua.getInstantzia().errenkadaHautagaiak(pErr);
	    help2 = UnekoSudokua.getInstantzia().zutabeHautagaiak(pZut);
		help3 = UnekoSudokua.getInstantzia().eremuHautagaiak(pErr, pZut);
		for(int i=0; i<9 ;i++){
			if(help1[i] && help2[i] && help3[i]){
				aukerak.add(i+1);
			}	
		}
		if (aukerak.size()==1){
			laguntza=aukerak.get(0);
		}
		return laguntza;
	}

	public String getMetodoIzena(){
		return "Sole Candidate";
	}
}
