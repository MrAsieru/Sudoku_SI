package Modeloa.SudokuEstrategiak;
import java.util.ArrayList;

import Modeloa.Partida;
import Modeloa.Gelaxka.Gelaxka;
import Modeloa.Sudokua.*;

public class SoleCandidate implements LaguntzaMetodoa {
	public Integer laguntza(int pErr, int pZut) {
		Integer laguntza=null;
		boolean [] help1= new boolean[9];
		boolean [] help2= new boolean[9];
		boolean [] help3= new boolean[9];
		ArrayList <Integer> aukerak = new ArrayList<Integer>();
		help1 = Partida.getPartida().getSudoku().errenkadaHautagaiak(pErr);
	    help2 = Partida.getPartida().getSudoku().zutabeHautagaiak(pZut);
		help3 = Partida.getPartida().getSudoku().eremuHautagaiak(pErr, pZut);
		for(int i=0; i<9 ;i++){
			if(help1[i] && help2[i] && help3[i]){
				aukerak.add(i+1);
			}	
		}
		if (aukerak.size()==1){
			laguntza=aukerak.get(0);
		}
		return laguntza;
	}

	public String getMetodoIzena(){
		return "Sole Candidate";
	}
}
