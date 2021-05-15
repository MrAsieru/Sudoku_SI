package modeloa.sudoku_teknikak;

import egitura.AldaketaEgitura;
import egitura.LaguntzaEgitura;
import modeloa.sudokua.UnekoSudokua;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UniqueCandidate implements LaguntzaTeknika {
		private boolean[][][] nirehautagaiak;
		
	public LaguntzaEgitura laguntzaKalkulatu() {
		Logger logger = Logger.getLogger(UnekoSudokua.getInstantzia().getClass().getName());
		nirehautagaiak= UnekoSudokua.getInstantzia().getHautagaiakProg();
		Boolean[][] matrizeHutsak = UnekoSudokua.getInstantzia().getGelaxkaHutsak();
		
		List<String> laguntzak = new ArrayList<>();
		List<AldaketaEgitura> aldaketak = new ArrayList<>();
		
		int j=0;
		while(j<UnekoSudokua.getInstantzia().getTamaina()) {
			int k=0;
			while(k<UnekoSudokua.getInstantzia().getTamaina()) {
				int h=0;
				
				while(h<UnekoSudokua.getInstantzia().getTamaina()) {
					if(matrizeHutsak[j][k] && (this.errenkadaBegiratu(j,k,h) || this.zutabeaBegiratu(j,k,h) || this.eremuaBegiratu(j,k, h))) {
						laguntzak.add("Teknika: Unique<br>" +
								String.format("Gelaxka: (%d, %d)<br>", j+1, k+1) +
								String.format("Balioa: %d<br>", h+1));
						aldaketak.add(new AldaketaEgitura(j, k, h+1, -1));
						logger.info(String.format("[Laguntza] Unique candidate erabiliz (%d, %d) gelaxkan %d balioa ipini da.", j+1, k+1, h+1));
					}
					h++;
				}
				k++;
			}
			j++;
		}
		return new LaguntzaEgitura(laguntzak, aldaketak);
	}

	private boolean errenkadaBegiratu(int pErr,int pZut,int pBal) {
		boolean ema=true;
		
		int i=0;
		while(i<9 && ema) {
			if((i==pZut && !nirehautagaiak[pErr][i][pBal]) || (i!=pZut && nirehautagaiak[pErr][i][pBal])) {
				ema=false;
			}
			i++;
		}
		return ema;
	}

	private boolean zutabeaBegiratu(int pErr,int pZut,int pBal) {
		boolean ema=true;
		
		int i=0;
		while(i<9 && ema) {
			if((i==pErr && !nirehautagaiak[i][pZut][pBal]) || (i!=pErr && nirehautagaiak[i][pZut][pBal])) {
				ema=false;
			}
			i++;
		}
		return ema;
	}

	private boolean eremuaBegiratu(int pErr,int pZut,int pBal) {
		boolean ema=true;
		
		int i;
		int imax;
		if(pErr<3) {
			i=0;
			imax=3;
		}else if(pErr<6) {
			i=3;
			imax=6;
		}else {
			i=6;
			imax=9;
		}
		
		while(i<imax && ema) {
			int j;
			int jmax;
			if(pZut<3) {
				j=0;
				jmax=3;
			}else if(pZut<6) {
				j=3;
				jmax=6;
			}else {
				j=6;
				jmax=9;
			}
			while(j<jmax && ema) {
				if(((i!=pErr ||  j!=pZut) && nirehautagaiak[i][j][pBal]) || (i==pErr &&  j==pZut && !nirehautagaiak[i][j][pBal])) {
					ema=false;
				}
				j++;
			}
			i++;
		}
		return ema;
	}
}
