package Modeloa.SudokuEstrategiak;

import Egitura.AldaketaEgitura;
import Egitura.LaguntzaEgitura;
import Modeloa.Sudokua.UnekoSudokua;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UniqueCandidate implements LaguntzaMetodoa {
	public LaguntzaEgitura laguntzaKalkulatu() {
		Boolean[][] matrizeHutsak = new Boolean[9][9];
		int i=0;
		while(i<9) {
			matrizeHutsak[i] = Arrays.stream(UnekoSudokua.getInstantzia().getGelaxkaBalioak()[i]).map(p -> (p.balioa == null || p.balioa == 0)).toArray(t -> new Boolean[t]);
			i++;
		}
		List<String> laguntzak = new ArrayList<>();
		List<AldaketaEgitura> aldaketak = new ArrayList<>();
		int j=0;
		while(j<UnekoSudokua.getInstantzia().getTamaina()) {
			int k=0;
			while(k<UnekoSudokua.getInstantzia().getTamaina()) {
				Integer emaitza = laguntza(k,j);
				if(emaitza!=null && matrizeHutsak[j][k]) {
					laguntzak.add("Estrategia: Unique<br>" +
							"Gelaxka: (%d, %d)<br>".formatted(j+1, k+1) +
							"Balioa: %d<br>".formatted(emaitza));
					aldaketak.add(new AldaketaEgitura(j, k, emaitza, -1));
				}
				k++;
			}
			j++;
		}
		return new LaguntzaEgitura(laguntzak, aldaketak);
	}
	
	private Integer laguntza(int pErr, int pZut){
		Integer lag = null;
		boolean [] help1= new boolean[9];
		boolean [] help2= new boolean[9];
		boolean [] help3= new boolean[9];
		ArrayList <Integer> aukerak = new ArrayList<Integer>();
		help1 = UnekoSudokua.getInstantzia().errenkadaHautagaiak(pErr);
	    help2 = UnekoSudokua.getInstantzia().zutabeHautagaiak(pZut);
		help3 = UnekoSudokua.getInstantzia().eremuHautagaiak(pErr, pZut);
		
		int i=0;
		while(i<9) {
			if(!help1[i] && !help2[i] && !help3[i]) {
				aukerak.add(i+1);
			}
			i++;
		}
		if(aukerak.size()==1) {
			lag=aukerak.get(0);
		}
		return lag;
	}

	public String getMetodoIzena(){
		return "Unique Candidate";
	}
}
