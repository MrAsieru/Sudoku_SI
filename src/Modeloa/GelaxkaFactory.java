package Modeloa;

public class GelaxkaFactory {
	private static GelaxkaFactory instantzia;

	private GelaxkaFactory() {}

	public static GelaxkaFactory getInstantzia(){
		if (instantzia == null){
			instantzia = new GelaxkaFactory();
		}
		return instantzia;
	}

	public Gelaxka gelaxkaSortu(GelaxkaMotak pMota, int pEr, int pZu, int pZenbakia){
		Gelaxka emaitza;

		switch (pMota){
			case HASIERAKOA:
				emaitza = new GelaxkaHasierakoa(pEr, pZu, pZenbakia);
				break;
			case EDITAGARRIA:
				emaitza = new GelaxkaEditagarria(pEr, pZu, pZenbakia);
				break;
			default:
				emaitza = null;
				break;
		}

		return emaitza;
	}
}
