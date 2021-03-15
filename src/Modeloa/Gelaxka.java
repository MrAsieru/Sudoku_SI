package Modeloa;

import java.util.List;

public class Gelaxka {
	private int zutabe;
	private int errenkada;
	private int zenbakia;
	private List<Integer> zerAukerak;
	private boolean hasierakoBalioa;
	
	public Gelaxka(int pEr, int pZu, boolean pHas, int pBal) {
		this.errenkada=pEr;
		this.zutabe=pZu;
		this.hasierakoBalioa=pHas;
		this.zenbakia=pBal;
	}
	
	public void aukerakGehitu(List<Integer> aukerak) {
		//TODO(another sprint)
	}
	
	public boolean setZenbakia(int pZenbakia) {
		if (!(this.hasierakoBalioa)){
			this.zenbakia=pZenbakia;
			return true;
		}
	return false;
	}
	
	public int getBalioa(){
		return this.zenbakia;	
	}
	
	public boolean hasBalio(){
		return this.hasierakoBalioa;
	}
	
	public boolean konprobatu(int pZenb){
		boolean ondo=false;
		if (0<=pZenb && pZenb<=9){
			ondo=true;
		}
	return ondo;
	}
}
