package Modeloa.SudokuEstrategiak;

import Egitura.LaguntzaEgitura;

import java.util.List;

public interface LaguntzaMetodoa {
	//Baliorik lortzen ez badu null bidali
	public LaguntzaEgitura laguntzaKalkulatu();

	public String getMetodoIzena();
}
