package modeloa.gelaxka;

public class GelaxkaFactory {
	private static GelaxkaFactory instantzia;

	private GelaxkaFactory() {}

	public static GelaxkaFactory getInstantzia(){
		if (instantzia == null){
			instantzia = new GelaxkaFactory();
		}
		return instantzia;
	}

	public Gelaxka gelaxkaSortu(GelaxkaMotak pMota, int pZenbakia){
		Gelaxka emaitza;

		switch (pMota){
			case HASIERAKOA:
				emaitza = new GelaxkaHasierakoa(pZenbakia);
				break;
			case EDITAGARRIA:
				emaitza = new GelaxkaEditagarria(pZenbakia);
				break;
			default:
				emaitza = null;
				break;
		}

		return emaitza;
	}
}
