package Modeloa;

public abstract class Gelaxka {
	protected int errenkada;
	protected int zutabe;
	protected int zenbakia;
	protected boolean[] hautagiak;

	public Gelaxka(int pEr, int pZu, int pBal) {
		this.errenkada=pEr;
		this.zutabe=pZu;
		this.zenbakia=pBal;
	}

	public abstract void setZenbakia(int pZenbakia) throws GelaxkaEditagarriezinaException;

	public int getBalioa(){
		return this.zenbakia;
	}

	public abstract void setHautagiak(boolean[] hautagiak) throws GelaxkaEditagarriezinaException;

	public abstract boolean[] getHautagiak();
}