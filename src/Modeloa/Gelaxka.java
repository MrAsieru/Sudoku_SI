package Modeloa;

import java.util.List;

public abstract class Gelaxka {
	private int errenkada;
	private int zutabe;
	private int zenbakia;
	
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

	public int getBalioa(){
		return this.zenbakia;
	}
}
