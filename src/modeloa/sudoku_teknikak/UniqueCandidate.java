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

		int c=this.eremua(pErr, pZut);
		if(c==0) {
			int z=3;
			int z2=6;
			int e=3;
			int e2=6;
			if(help3[c] && !help3[z] && !help3[z2] && !help3[e] && !help3[e2]) {
				ema=true;
			}
			
		} else if(c==1) {
			int z_1=4;
			int z_12=7;
			int e_1=0;
			int e_12=2;
			if(help3[c] && !help3[z_1] && !help3[z_12] && !help3[e_1] && !help3[e_12]) {
				ema=true;
			}
			
		} else if(c==2) {
			int z_2=5;
			int z_22=8;
			int e_2=0;
			int e_22=1;
			if(help3[c] && !help3[z_2] && !help3[z_22] && !help3[e_2] && !help3[e_22]) {
				ema=true;
			}
			
		}else if(c==3) {
			int z_3=0;
			int z_32=6;
			int e_3=4;
			int e_32=5;
			if(help3[c] && !help3[z_3] && !help3[z_32] && !help3[e_3] && !help3[e_32]) {
				ema=true;
			}
			
		}else if(c==4) {
			int z_4=1;
			int z_42=7;
			int e_4=3;
			int e_42=5;
			if(help3[c] && !help3[z_4] && !help3[z_42] && !help3[e_4] && !help3[e_42]) {
				ema=true;
			}
			
		}else if(c==5) {
			int z_5=2;
			int z_52=8;
			int e_5=3;
			int e_52=4;
			if(help3[c] && !help3[z_5] && !help3[z_52] && !help3[e_5] && !help3[e_52]) {
				ema=true;
			}
			
		}else if(c==6) {
			int z_6=0;
			int z_62=3;
			int e_6=7;
			int e_62=8;
			if(help3[c] && !help3[z_6] && !help3[z_62] && !help3[e_6] && !help3[e_62]) {
				ema=true;
			}
			
		}else if(c==7) {
			int z_7=1;
			int z_72=4;
			int e_7=6;
			int e_72=8;
			if(help3[c] && !help3[z_7] && !help3[z_72] && !help3[e_7] && !help3[e_72]) {
				ema=true;
			}
			
		}else {
			int z_8=2;
			int z_82=5;
			int e_8=6;
			int e_82=7;
			if(help3[c] && !help3[z_8] && !help3[z_82] && !help3[e_8] && !help3[e_82]) {
				ema=true;
			}
			
		}
		return ema;
	}
	
	private int eremua(int pErr, int pZut) {
		int ema1;
		int ema2;
		int ema;
		
		ema1= pErr%3;
		ema2= pZut%3;
		ema=ema1+ema2;
		
		return ema;
	}
}
