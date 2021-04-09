package Modeloa;

public class GelaxkaHasierakoa extends Gelaxka {

	public GelaxkaHasierakoa(int pEr, int pZu, int pBal) {
		super(pEr, pZu, pBal);
	}

	@Override
	public void setZenbakia(int pZenbakia) throws GelaxkaEditagarriezinaException {
		throw new GelaxkaEditagarriezinaException();
	}

	@Override
	public void setHautagiak(boolean[] hautagiak) throws GelaxkaEditagarriezinaException {
		throw new GelaxkaEditagarriezinaException();
	}

	@Override
	public boolean[] getHautagiak() {
		return null;
	}
}
