package Modeloa.SudokuEstrategiak;

import Egitura.AldaketaEgitura;
import Egitura.LaguntzaEgitura;

import java.util.ArrayList;
import java.util.List;

public class EremuIterazioa implements LaguntzaMetodoa {
	public LaguntzaEgitura laguntzaKalkulatu() {
		return new LaguntzaEgitura(new ArrayList<String>(), new ArrayList<AldaketaEgitura>());
	}

	public String getMetodoIzena(){
		return "Eremu Interazioa";
	}
}
