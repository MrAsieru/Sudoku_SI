import Modeloa.Irakurlea;
import Modeloa.Login;
import Modeloa.Partida;
import Modeloa.SudokuLista;

public class Nagusia {
	public static void main(String[] args) {
		//Partida klasea sortu
		Partida.getPartida();

		//Sudokuak kargatu
		SudokuLista.getSudokuLista();
		Irakurlea.getIrakurlea().getHasierakoSudokuGuztiak();
		
		//Login panela sortu, logeatu eta partida abisatu		
		Login.getInstantzia();
	}
}
