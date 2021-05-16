package modeloa.sudoku_teknikak;

import egitura.AldaketaEgitura;
import egitura.LaguntzaEgitura;
import modeloa.sudokua.UnekoSudokua;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EremuEtaZutLerIterazioa implements LaguntzaTeknika {
	public LaguntzaEgitura laguntzaKalkulatu() {
		Logger logger = Logger.getLogger(UnekoSudokua.getInstantzia().getClass().getName());
		boolean[][][] hautagaiaGuztiakProg = UnekoSudokua.getInstantzia().getHautagaiakProg();
		boolean[][][] hautagaiaGuztiakErab = UnekoSudokua.getInstantzia().getHautagaiakErab();
		Boolean[][] matrizeHutsak = UnekoSudokua.getInstantzia().getGelaxkaHutsak();

		ArrayList<String> laguntzak = new ArrayList<>();
		List<AldaketaEgitura> aldaketak = new ArrayList<>();
		for (int ei = 0; ei < 3; ei++){
			for (int ej = 0; ej < 3; ej++){ //Eremu bakoitzeko
				int err = ei*3;
				int zut = ej*3;
				//Zutabeak
				for (int b = 0; b < 9; b++) { //Balio bakoitzeko
					//Errenkada bakoitza konprobatu
					boolean ezkerra = hautagaiaGuztiakProg[err][zut][b] || hautagaiaGuztiakProg[err+1][zut][b] || hautagaiaGuztiakProg[err+2][zut][b];
					boolean erdi = hautagaiaGuztiakProg[err][zut+1][b] || hautagaiaGuztiakProg[err+1][zut+1][b] || hautagaiaGuztiakProg[err+2][zut+1][b];
					boolean eskuina = hautagaiaGuztiakProg[err][zut+2][b] || hautagaiaGuztiakProg[err+1][zut+2][b] || hautagaiaGuztiakProg[err+2][zut+2][b];

					int zutabea = -1;
					if (ezkerra && !erdi && !eskuina) zutabea = zut; //Bakarrik ezkerrekoa
					else if (!ezkerra && erdi && !eskuina) zutabea = zut+1; //Bakarrik erdikoa
					else if (!ezkerra && !erdi && eskuina) zutabea = zut+2; //Bakarrik eskuinekoa

					if (zutabea != -1) {
						for (int g = 0; g < 9; g++){
							if (matrizeHutsak[g][zutabea] && (g < err || err+2 < g) && hautagaiaGuztiakProg[g][zutabea][b] && hautagaiaGuztiakErab[g][zutabea][b]) {
								laguntzak.add("Teknika: Eremu eta Zut. iter.<br>" +
										String.format("Gelaxka: (%d, %d)<br>", g+1, zutabea+1) +
										String.format("%d hautagaitik kendu<br>", b+1));
								aldaketak.add(new AldaketaEgitura(g, zutabea, -1, b));
								logger.info(String.format("[Laguntza] Eremu eta zutabe iterazioa erabiliz (%d, %d) gelaxkatik %d hautagaia kendu", g+1, zutabea+1, b+1));
							}
						}
					}
				}

				//Errenkadak
				for (int b = 0; b < 9; b++) { //Balio bakoitzeko
					//Errenkada bakoitza konprobatu
					boolean goi = hautagaiaGuztiakProg[err][zut][b] || hautagaiaGuztiakProg[err][zut+1][b] || hautagaiaGuztiakProg[err][zut+2][b];
					boolean erdi = hautagaiaGuztiakProg[err+1][zut][b] || hautagaiaGuztiakProg[err+1][zut+1][b] || hautagaiaGuztiakProg[err+1][zut+2][b];
					boolean behe = hautagaiaGuztiakProg[err+2][zut][b] || hautagaiaGuztiakProg[err+2][zut+1][b] || hautagaiaGuztiakProg[err+2][zut+2][b];

					int errenkada = -1;
					if (goi && !erdi && !behe) errenkada = err; //Bakarrik goikoa
					else if (!goi && erdi && !behe) errenkada = err+1; //Bakarrik erdikoa
					else if (!goi && !erdi && behe) errenkada = err+2; //Bakarrik behekoa

					if (errenkada != -1) {
						for (int g = 0; g < 9; g++){
							if (matrizeHutsak[errenkada][g] && (g < zut || zut+2 < g) && hautagaiaGuztiakProg[errenkada][g][b] && hautagaiaGuztiakErab[errenkada][g][b]) {
								laguntzak.add("Teknika: Eremu eta Err. iter.<br>" +
										String.format("Gelaxka: (%d, %d)<br>", errenkada+1, g+1) +
										String.format("%d hautagaitik kendu<br>", b+1));
								aldaketak.add(new AldaketaEgitura(errenkada, g, -1, b));
								logger.info(String.format("[Laguntza] Eremu eta errenkada iterazioa erabiliz (%d, %d) gelaxkatik %d hautagaia kendu", errenkada+1, g+1, b+1));
							}
						}
					}
				}
			}
		}
		return new LaguntzaEgitura(laguntzak, aldaketak);
	}
}
