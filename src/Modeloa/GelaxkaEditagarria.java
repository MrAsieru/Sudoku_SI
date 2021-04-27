package Modeloa;

public class GelaxkaEditagarria extends Gelaxka{
	private boolean[] hautagiakErab;
	private boolean[] hautagaiakProg;

	public GelaxkaEditagarria(int pEr, int pZu, int pBal) {
		super(pEr, pZu, pBal);
		hautagiakErab = new boolean[9];
		hautagaiakProg = new boolean[9];
	}

	/**
	 * Gelaxkaren zenbakia ezarri
	 * @param pZenbakia
	 */
	public void setZenbakia(int pZenbakia) {
		if (0 <= pZenbakia && pZenbakia <= 9){
			this.zenbakia = pZenbakia;
		}
	}

	/**
	 *
	 * @param hautagiak
	 */
	public void setHautagiakErab(boolean[] hautagiak) {
		hautagiakErab = hautagiak;
		for (int i = 0; i<hautagiak.length; i++){
			hautagiakErab[i] = hautagiak[i] & hautagaiakProg[i];
		}
	}

	/**
	 * Programa kalkulatuko hautagaiak ezarri.
	 * Erabiltzailearen hautagaiak zuzentzen ditu beharrezkoa bada.
	 * 	1 - Hautagaiak aktibatuta egotea bakarrik hautagaiakProg-en aktibatuta badaude.
	 * 	2 - hautagaiakProg-eko hautagaiak berrio aktibatzen bada, hautagaiakErab-en ere aktibatu
	 * @param hautagiak Programa kalkulatutako hautagai berriak
	 */
	public void setHautagiakProg(boolean[] hautagiak) {
		for (int i = 0; i<hautagiak.length; i++){
			// AND logiko bat hautagai berriekin eta erabiltzaileak duen hautagaiekin
			hautagiakErab[i] = hautagiak[i] & hautagiakErab[i];
			// Aurreko programa hautagaiak oraingo hautagaiak dituen balioak ez baditu erabiltzaileak ere ez eta
			// horren ondorioz zapaldu egingo du, beraz hauek berriz true bezala ipiniko ditugu.
			if (!hautagaiakProg[i] && hautagiak[i]) hautagiakErab[i] = true;
		}
		hautagaiakProg = hautagiak;
	}

	public boolean[] getHautagiakErab() {
		return hautagiakErab;
	}
}