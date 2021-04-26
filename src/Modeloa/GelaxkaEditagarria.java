package Modeloa;

public class GelaxkaEditagarria extends Gelaxka{
	private boolean[] hautagiakErab;
	private boolean[] hautagaiakProg;

	public GelaxkaEditagarria(int pEr, int pZu, int pBal) {
		super(pEr, pZu, pBal);
		hautagiakErab = new boolean[9];
		for (int i = 0; i < hautagiakErab.length; i++) {
			hautagiakErab[i] = true;
		}
	}

	public void setZenbakia(int pZenbakia) {
		if (0 <= pZenbakia && pZenbakia <= 9){
			this.zenbakia = pZenbakia;
		}
	}

	public void setHautagiakErab(boolean[] hautagiak) {
		hautagiakErab = hautagiak;
		//FIltro con prog
	}

	public void setHautagiakProg(boolean[] hautagiak) {
		hautagaiakProg = hautagiak;
	}

	public boolean[] getHautagiakErab() {
		return hautagiakErab;
	}

	public boolean[] getHautagaiakProg() {
		boolean[] hautagaiakFiltratuta = this.hautagaiakProg
		for (int i = 0; ){
			// No se usa en la bista
		}
		return hautagaiakProg;
	}

	/**
	public boolean[] getHautagiak(){
		switch (egoera){
			case 1:
				return this.hautagiakErab;
			case 2:
				return this.hautagaiakProg;
		}
		return null;
	}
	 */

}

/** en vez de poner quitar (usuario hautagaiak), kalkulatuHautagaiak kalkula todas las gelaxkaz (cada vez que se cambie un valor)
 *
 */