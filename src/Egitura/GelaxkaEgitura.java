package Egitura;

public class GelaxkaEgitura {
	public Integer balioa;
	public boolean[] hautagaiak;
	
	public GelaxkaEgitura() {}
	
	public GelaxkaEgitura(Object pArg) {
		if (pArg instanceof Integer) {
			balioa = (Integer) pArg;
		} else if (pArg instanceof int[][]) {
			hautagaiak = (boolean[]) pArg;
		}
	}
}
