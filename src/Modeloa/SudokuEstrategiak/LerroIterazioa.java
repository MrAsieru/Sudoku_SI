package Modeloa.SudokuEstrategiak;

import java.util.ArrayList;
import java.util.Random;

public class LerroIterazioa implements LaguntzaMetodoa {
	public Integer laguntza(int pErr, int pZut) {
		int laguntza=0;
		int rand=0;
		Random random= new Random();
		rand=random.nextInt(10);
		rand+=1;
		ArrayList<Integer> aukerak= new ArrayList<Integer>();
		aukerak.add(rand);
		
		
		if (aukerak.size()==1){
			laguntza=aukerak.get(0);
		}
		return laguntza;
	}

	public String getMetodoIzena(){
		return "Lerro Iterazioa";
	}
}
