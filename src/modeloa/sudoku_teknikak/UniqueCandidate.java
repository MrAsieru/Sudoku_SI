package modeloa.sudoku_teknikak;

import egitura.AldaketaEgitura;
import egitura.LaguntzaEgitura;
import modeloa.sudokua.UnekoSudokua;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UniqueCandidate implements LaguntzaTeknika {
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
					laguntzak.add("Teknika: Unique<br>" +
							String.format("Gelaxka: (%d, %d)<br>", j+1, k+1) +
							String.format("Balioa: %d<br>", emaitza));
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
		while(i<9){
			if(errenkadaBegiratu(pErr) && zutabeaBegiratu(pZut) && eremuaBegiratu(pErr,pZut)) {
				aukerak.add(i+1);
			}
			i++;
		}

		if(aukerak.size()==1) {
			lag=aukerak.get(0);
		}
		return lag;

	}

	private boolean errenkadaBegiratu(int pErr) {

		boolean [] help1= new boolean[9];
		boolean ema= false;
		help1 = UnekoSudokua.getInstantzia().errenkadaHautagaiak(pErr);

		int i=0;
		while(i<9){
			int j=0;
			if(j==1) {
				j++;
			}
			while(j<i || (j>i && j<9)) {
				if(!help1[j] && help1[i]) {
					ema=true;
				}
				if(j+1==i) {
					j=j+2;
				}else {
					j++;
				}
			}
			i++;
		}
		return ema;
	}

	private boolean zutabeaBegiratu(int pZut) {
		boolean [] help2= new boolean[9];
		 help2 = UnekoSudokua.getInstantzia().zutabeHautagaiak(pZut);
		boolean ema=false;

		int a=0;
		while(a<9){
			int b=0;
			if(b==a) {
				b++;
			}
			while(b<a || (b>a && b<9)) {
				if(!help2[b] && help2[a]) {
					ema=true;
				}
				if(b+1==a) {
					b=b+2;
				}else {
					b++;
				}
			}
				a++;
		}
		return ema;
	}

	private boolean eremuaBegiratu(int pErr, int pZut) {
		boolean [] help3= new boolean[9];
		help3 = UnekoSudokua.getInstantzia().eremuHautagaiak(pErr, pZut);
		boolean ema=false;

		int c=0;
		while(c<9){
			int d=0;
			if(d==c) {
				d++;
			}
			while(d<c || (d>c && d<9)) {
				if(!help3[d] && help3[c]) {
					ema=true;
				}
				if(d+1==c) {
					d=d+2;
				}else {
					d++;
				}
			}
				c++;
		}
		return ema;	
	}
	
	
}
