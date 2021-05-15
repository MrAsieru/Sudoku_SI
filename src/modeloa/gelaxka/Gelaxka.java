package modeloa.gelaxka;

public abstract class Gelaxka {
	protected int zenbakia;


	public Gelaxka(int pBal) {
		this.zenbakia=pBal;
	}

	public int getBalioa(){
		return this.zenbakia;
	}
}