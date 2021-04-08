package Modeloa;

import java.util.List;

public abstract class Gelaxka {
	private int errenkada;
	private int zutabe;
	private int zenbakia;
	private boolean[] hautagiak;
	
	public Gelaxka(int pEr, int pZu, int pBal) {
		this.errenkada=pEr;
		this.zutabe=pZu;
		this.zenbakia=pBal;
	}
	
	public void setZenbakia(int pZenbakia) throws GelaxkaEditagarriezinaException {
		if (0 <= pZenbakia && pZenbakia <= 9){
			this.zenbakia = pZenbakia;
		}
	}

	public void setHautagiak(boolean[] hautagiak) throws GelaxkaEditagarriezinaException {
		this.hautagiak = hautagiak;
	}

	public int getBalioa(){
		return this.zenbakia;
	}
}
