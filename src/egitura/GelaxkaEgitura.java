package egitura;

public class GelaxkaEgitura {
	public Integer balioa;
	public boolean[] hautagaiak;
	
	public GelaxkaEgitura(Object pArg) {
		if (pArg instanceof Integer) {
			balioa = (Integer) pArg;
		} else if (pArg instanceof boolean[]) {
			hautagaiak = (boolean[]) pArg;
		}
	}
}
