package modeloa.sudoku_teknikak;

import egitura.AldaketaEgitura;
import egitura.LaguntzaEgitura;
import modeloa.sudokua.UnekoSudokua;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EremuEremuIterazioa implements LaguntzaTeknika {
	public LaguntzaEgitura laguntzaKalkulatu() {
		boolean[][][] hautagaiaGuztiak = UnekoSudokua.getInstantzia().getHautagaiakProg();
		int[][] konbinaketak = new int[][] {{0,1,2}, {0,2,1}, {1,2,0}}; //Eremuen arteko konbinaketak. Azken zenbakia aldatu beharreko eremua da
		Boolean[][] matrizeHutsak = UnekoSudokua.getInstantzia().getGelaxkaHutsak(); // Gelaxka hutsen (balio barik) matrizea

		ArrayList<String> laguntzak = new ArrayList<>();
		List<AldaketaEgitura> aldaketak = new ArrayList<>();
		// Aldagaien deskribapena:
		//	i edo j: Eremuen errenkada/zutabea
		//	tmp1 eta 2: Balio jakin batek eremu baten errenkada/zutabe-ko gelaxken hautagaietan agertzen bada 
		
		//Zutabeak
		for (int j = 0; j < 3; j++){ // Eremuen zutabeak
			for (int[] konb : konbinaketak) { // Zutabe bateko eremu konbinaketak
				for (int b = 0; b < 9; b++) { // Balio bakoitzeko 1-9
					boolean[] tmp1 = eremuZutabeak(konb[0], j, b, hautagaiaGuztiak); // 1.Eremua: 0-Ezkerra, 1-Erdi, 2-Eskuina
					boolean[] tmp2 = eremuZutabeak(konb[1], j, b, hautagaiaGuztiak); // 2.Eremua

					int[] zutabeak = aldatzekoErrenkadaZutabea(tmp1, tmp2, j);
					

					if (zutabeak != null) { //Zutabeak aurkitu badira
						for (int zut : zutabeak) { // Zutabe bakoitzeko
							for (int g = konb[2]*3; g < konb[2]*3+3; g++){ // Errenkada bakoitzeko
								if (matrizeHutsak[g][zut] && hautagaiaGuztiak[g][zut][b]) { // Baldin balioa ez badu eta hautagaia jarrita badago
									laguntzak.add("Teknika: Eremu eta eremu iter.<br>" +
											String.format("Gelaxka: (%d, %d)<br>", g+1, zut+1) +
											String.format("%d hautagaitik kendu<br>", b+1));
									aldaketak.add(new AldaketaEgitura(g, zut, -1, b));
									Logger.getLogger(UnekoSudokua.getInstantzia().getClass().getName()).
											info(String.format("[Laguntza] Eremu eta eremu iterazioa erabiliz (%d, %d) gelaxkatik %d hautagaia kendu", g+1, zut+1, b+1));
								}
							}
						}
					}
				}
			}
		}

		//Errenkadak
		for (int i = 0; i < 3; i++){ // Eremuen errenkadak
			for (int[] konb : konbinaketak) { // Errenkada bateko eremu konbinaketak
				for (int b = 0; b < 9; b++) { // Balio bakoitzeko 1-9
					boolean[] tmp1 = eremuErrenkadak(i, konb[0], b, hautagaiaGuztiak); // 1.Eremua: 0-Goi, 1-Erdi, 2-Behe
					boolean[] tmp2 = eremuErrenkadak(i, konb[1], b, hautagaiaGuztiak); // 2.Eremua

					int[] errenkadak = aldatzekoErrenkadaZutabea(tmp1, tmp2, i);

					if (errenkadak != null) { //Errenkadak aurkitu badira
						for (int err : errenkadak) { // Errenkada bakoitzeko
							for (int g = konb[2]*3; g < konb[2]*3+3; g++){ // Zutabe bakoitzeko
								if (matrizeHutsak[err][g] && hautagaiaGuztiak[err][g][b]) { // Baldin balioa ez badu eta hautagaia jarrita badago
									laguntzak.add("Teknika: Eremu eta eremu iter.<br>" +
											String.format("Gelaxka: (%d, %d)<br>", err+1, g+1) +
											String.format("%d hautagaitik kendu<br>", b+1));
									aldaketak.add(new AldaketaEgitura(err, g, -1, b));
									Logger.getLogger(UnekoSudokua.getInstantzia().getClass().getName()).
											info(String.format("[Laguntza] Eremu eta eremu iterazioa erabiliz (%d, %d) gelaxkatik %d hautagaia kendu", err+1, g+1, b+1));
								}
							}
						}
					}
				}
			}
		}
		return new LaguntzaEgitura(laguntzak, aldaketak);
	}

	// pHautPos hautagaia eremuaren zutabe jakin batean true bada, true jarri
	private boolean[] eremuZutabeak(int pI, int pJ, int pHautPos, boolean[][][] pHautagaiak) {
		boolean[] emaitza = new boolean[3];
		emaitza[0] = pHautagaiak[pI*3][pJ*3][pHautPos] || pHautagaiak[pI*3+1][pJ*3][pHautPos] || pHautagaiak[pI*3+2][pJ*3][pHautPos];
		emaitza[1] = pHautagaiak[pI*3][pJ*3+1][pHautPos] || pHautagaiak[pI*3+1][pJ*3+1][pHautPos] || pHautagaiak[pI*3+2][pJ*3+1][pHautPos];
		emaitza[2] = pHautagaiak[pI*3][pJ*3+2][pHautPos] || pHautagaiak[pI*3+1][pJ*3+2][pHautPos] || pHautagaiak[pI*3+2][pJ*3+2][pHautPos];
		return emaitza;
	}

	// pHautPos hautagaia eremuaren errenkada jakin batean true bada, true jarri
	private boolean[] eremuErrenkadak(int pI, int pJ, int pHautPos, boolean[][][] pHautagaiak) {
		boolean[] emaitza = new boolean[3];
		emaitza[0] = pHautagaiak[pI*3][pJ*3][pHautPos] || pHautagaiak[pI*3][pJ*3+1][pHautPos] || pHautagaiak[pI*3][pJ*3+2][pHautPos];
		emaitza[1] = pHautagaiak[pI*3+1][pJ*3][pHautPos] || pHautagaiak[pI*3+1][pJ*3+1][pHautPos] || pHautagaiak[pI*3+1][pJ*3+2][pHautPos];
		emaitza[2] = pHautagaiak[pI*3+2][pJ*3][pHautPos] || pHautagaiak[pI*3+2][pJ*3+1][pHautPos] || pHautagaiak[pI*3+2][pJ*3+2][pHautPos];
		return emaitza;
	}

	// Konprobatu balio jakin bat bakarrik bi errenkada/zutabe baldin badute, horrela izatekotan bi errenkada/zutabe hauek bueltatu
	private int[] aldatzekoErrenkadaZutabea(boolean[] pT1, boolean[] pT2, int pLerroa) {
		int[] emaitza = null;
		if (pT1[0] && pT2[0] && pT1[1] && pT2[1] && !pT1[2] && !pT2[2]) emaitza = new int[]{pLerroa*3+0,pLerroa*3+1}; //b balioa Ezker/Goi eta erdiko lerroetan bakarrik badago (bi eremuetan)
		else if (pT1[0] && pT2[0] && !pT1[1] && !pT2[1] && pT1[2] && pT2[2]) emaitza = new int[]{pLerroa*3+0,pLerroa*3+2}; //b balioa Ezker/Goi eta eskuin/behe lerroetan bakarrik badago (bi eremuetan)
		else if (!pT1[0] && !pT2[0] && pT1[1] && pT2[1] && pT1[2] && pT2[2]) emaitza = new int[]{pLerroa*3+1,pLerroa*3+2}; //b balioa erdi eta eskuin/behe lerroetan bakarrik badago (bi eremuetan)
		return emaitza;
	}
}
