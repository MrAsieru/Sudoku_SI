import bista.SudokuFrame;
import modeloa.sudokua.UnekoSudokua;
import modeloa.support.Irakurlea;
import modeloa.Login;
import modeloa.Partida;
import modeloa.sudokua.SudokuLista;

public class Nagusia {
	public static void main(String[] args) {
		//Partida klasea sortu
		Partida.getPartida();
		UnekoSudokua.getInstantzia().addObserver(new SudokuFrame());

		//Sudokuak kargatu
		SudokuLista.getSudokuLista();
		Irakurlea.getIrakurlea().getHasierakoSudokuGuztiak();
		
		//Login panela sortu, logeatu eta partida abisatu		
		Login.getInstantzia();
	}
}
