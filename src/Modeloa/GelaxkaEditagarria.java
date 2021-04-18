package Modeloa;

public class GelaxkaEditagarria extends Gelaxka{

	public GelaxkaEditagarria(int pEr, int pZu, int pBal) {
		super(pEr, pZu, pBal);
	}

	@Override
	public void setZenbakia(int pZenbakia) throws GelaxkaEditagarriezinaException {
		if (0 <= pZenbakia && pZenbakia <= 9){
			this.zenbakia = pZenbakia;
			this.hautagiak = null;
		}
	}

	@Override
	public void setHautagiak(boolean[] hautagiak) throws GelaxkaEditagarriezinaException {
		this.zenbakia = 0;
		this.hautagiak = hautagiak;
	}

	@Override
	public boolean[] getHautagiak(){
		return this.hautagiak;
	}
}