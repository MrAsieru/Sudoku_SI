package Modeloa;

public class GelaxkaEditagarria extends Gelaxka{

	public GelaxkaEditagarria(int pEr, int pZu, int pBal) {
		super(pEr, pZu, pBal);
	}

	public void setZenbakia(int pZenbakia) {
		if (0 <= pZenbakia && pZenbakia <= 9){
			this.zenbakia = pZenbakia;
			this.hautagiak = null;
		}
	}

	public void setHautagiak(boolean[] hautagiak) {
		this.zenbakia = 0;
		this.hautagiak = hautagiak;
	}

	public boolean[] getHautagiak(){
		return this.hautagiak;
	}
}