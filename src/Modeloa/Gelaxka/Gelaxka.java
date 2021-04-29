package Modeloa.Gelaxka;

public abstract class Gelaxka {
	protected int errenkada;
	protected int zutabe;
	protected int zenbakia;


	public Gelaxka(int pEr, int pZu, int pBal) {
		this.errenkada=pEr;
		this.zutabe=pZu;
		this.zenbakia=pBal;
	}

	public int getBalioa(){
		return this.zenbakia;
	}
}