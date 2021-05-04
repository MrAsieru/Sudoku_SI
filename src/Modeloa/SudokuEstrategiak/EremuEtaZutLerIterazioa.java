package Modeloa.SudokuEstrategiak;

import Egitura.AldaketaEgitura;
import Egitura.GelaxkaEgitura;
import Egitura.LaguntzaEgitura;
import Modeloa.Gelaxka.GelaxkaEditagarria;
import Modeloa.Sudokua.UnekoSudokua;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EremuEtaZutLerIterazioa implements LaguntzaMetodoa {
	public LaguntzaEgitura laguntzaKalkulatu() {
		boolean[][][] hautagaiaGuztiak = UnekoSudokua.getInstantzia().getHautagaiakProg();
		Boolean[][] matrizeHutsak = new Boolean[9][9];
		for (int i = 0; i < 9; i++){
			matrizeHutsak[i] = Arrays.stream(UnekoSudokua.getInstantzia().getGelaxkaBalioak()[i]).map(p -> (p.balioa == null || p.balioa == 0)).toArray(t -> new Boolean[t]);
		}

		ArrayList<String> laguntzak = new ArrayList<>();
		List<AldaketaEgitura> aldaketak = new ArrayList<>();
		for (int ei = 0; ei < 3; ei++){
			for (int ej = 0; ej < 3; ej++){ //Eremu bakoitzeko
				int err = ei*3;
				int zut = ej*3;
				//Zutabeak
				for (int b = 0; b < 9; b++) { //Balio bakoitzeko
					//Errenkada bakoitza konprobatu
					boolean ezkerra = hautagaiaGuztiak[err][zut][b] || hautagaiaGuztiak[err+1][zut][b] || hautagaiaGuztiak[err+2][zut][b];
					boolean erdi = hautagaiaGuztiak[err][zut+1][b] || hautagaiaGuztiak[err+1][zut+1][b] || hautagaiaGuztiak[err+2][zut+1][b];
					boolean eskuina = hautagaiaGuztiak[err][zut+2][b] || hautagaiaGuztiak[err+1][zut+2][b] || hautagaiaGuztiak[err+2][zut+2][b];

					int zutabea = -1;
					if (ezkerra && !erdi && !eskuina) zutabea = zut; //Bakarrik ezkerrekoa
					else if (!ezkerra && erdi && !eskuina) zutabea = zut+1; //Bakarrik erdikoa
					else if (!ezkerra && !erdi && eskuina) zutabea = zut+2; //Bakarrik eskuinekoa

					if (zutabea != -1) {
						for (int g = 0; g < 9; g++){
							if (matrizeHutsak[g][zutabea] && (g < err || err+2 < g) && hautagaiaGuztiak[g][zutabea][b]) {
								laguntzak.add("Estrategia: Eremu eta Zut. iter.<br>" +
										"Gelaxka: (%d, %d)<br>".formatted(g+1, zutabea+1) +
										"%d hautagaitik kendu<br>".formatted(b+1));
								aldaketak.add(new AldaketaEgitura(g, zutabea, -1, b));
							}
						}
					}
				}

				//Errenkadak
				for (int b = 0; b < 9; b++) { //Balio bakoitzeko
					//Errenkada bakoitza konprobatu
					boolean goi = hautagaiaGuztiak[err][zut][b] || hautagaiaGuztiak[err][zut+1][b] || hautagaiaGuztiak[err][zut+2][b];
					boolean erdi = hautagaiaGuztiak[err+1][zut][b] || hautagaiaGuztiak[err+1][zut+1][b] || hautagaiaGuztiak[err+1][zut+2][b];
					boolean behe = hautagaiaGuztiak[err+2][zut][b] || hautagaiaGuztiak[err+2][zut+1][b] || hautagaiaGuztiak[err+2][zut+2][b];

					int errenkada = -1;
					if (goi && !erdi && !behe) errenkada = err; //Bakarrik goikoa
					else if (!goi && erdi && !behe) errenkada = err+1; //Bakarrik erdikoa
					else if (!goi && !erdi && behe) errenkada = err+2; //Bakarrik behekoa

					if (errenkada != -1) {
						for (int g = 0; g < 9; g++){
							if (matrizeHutsak[errenkada][g] && (g < zut || zut+2 < g) && hautagaiaGuztiak[errenkada][g][b]) {
								laguntzak.add("Estrategia: Eremu eta Err. iter.<br>" +
										"Gelaxka: (%d, %d)<br>".formatted(errenkada+1, g+1) +
										"%d hautagaitik kendu<br>".formatted(b+1));
								aldaketak.add(new AldaketaEgitura(errenkada, g, -1, b));
							}
						}
					}
				}
			}
		}
		return new LaguntzaEgitura(laguntzak, aldaketak);
	}

	public String getMetodoIzena(){
		return "Lerro Iterazioa";
	}
}
