package modeloa.support;

import egitura.PuntuazioaEgitura;
import modeloa.sudokua.SudokuLista;
import modeloa.sudokua.SudokuaGorde;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Irakurlea {
    private static Irakurlea irakurlea;
    private String rankingPath;
    private final int tamaina = 9;

    private Irakurlea(){
        try {
            rankingPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).
                    getParentFile().getAbsolutePath()+"/ranking.txt";
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static Irakurlea getIrakurlea() {
        if (irakurlea ==null){
            irakurlea = new Irakurlea();
        }
        return irakurlea;
    }

    public void getHasierakoSudokuGuztiak(){
        BufferedReader irakurle;
        try {
            irakurle = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/sudoku.txt")));

            // Sudoku guztiak irakurri
            while (irakurle.readLine()!=null){
                int zailtasuna = Integer.parseInt(irakurle.readLine());
                int[][] hasieraGelaxkak = new int[tamaina][tamaina];
                int[][] soluzioMatrizea = new int[tamaina][tamaina];

                // Hasierako matrizea
                for (int i = 0; i<tamaina; i++){
                    char[] lerroa = irakurle.readLine().toCharArray();
                    for (int j=0; j<tamaina; j++){
                        hasieraGelaxkak[i][j] = Character.getNumericValue(lerroa[j]);
                    }
                }

                // Soluzioa
                for (int i = 0; i<tamaina; i++){
                    char[] lerroa = irakurle.readLine().toCharArray();
                    for (int j=0; j<tamaina; j++){
                        soluzioMatrizea[i][j] = Character.getNumericValue(lerroa[j]);
                    }
                }

                SudokuLista.getSudokuLista().addSudoku(new SudokuaGorde(zailtasuna, hasieraGelaxkak, soluzioMatrizea));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<PuntuazioaEgitura> parseRanking(){
        List<PuntuazioaEgitura> ranking = new ArrayList<>();

        ArrayList<String> ldFitxategia = this.irakurriRankingFitxategia();
        int i = 0;
        while (++i<ldFitxategia.size()){
            //Gu nahi dugun formatua badu
            if(Regex.getRegex().rankingFormatua(ldFitxategia.get(i))){
                String[] datuak = ldFitxategia.get(i).split(";");
                //Izena - zailtazuna - puntuak
                PuntuazioaEgitura puntuazioa = new PuntuazioaEgitura(
                        datuak[0],
                        Integer.parseInt(datuak[1]),
                        Double.parseDouble(datuak[2])
                );
                ranking.add(puntuazioa);
            }
        }
        return ranking;
    }

    private ArrayList<String> irakurriRankingFitxategia(){
        ArrayList<String> testua = new ArrayList<>();
        try {
            rankingKonprobatu();
            BufferedReader irakurle = new BufferedReader(new FileReader(rankingPath));

            String lerroa;
            while ( (lerroa = irakurle.readLine()) != null){
                testua.add(lerroa);
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return testua;
    }

    public void rankingGorde(PuntuazioaEgitura pPuntuazioa) {
        try{
            rankingKonprobatu();
            BufferedWriter idazlea = new BufferedWriter(new FileWriter(rankingPath, true));
            idazlea.append("\n"+pPuntuazioa.izena +";"+ pPuntuazioa.zailtasuna +";"+ String.format("%.4f", pPuntuazioa.puntuazioa));
            idazlea.close();
        } catch (IOException e) {
            System.out.println("Ezin izan da puntuazioa gorde");
        }
    }

    private void rankingKonprobatu() {
        File logKokapena = new File(rankingPath);
        if (!logKokapena.exists()){
            try {
                Files.copy(getClass().getResourceAsStream("/ranking.txt"),
                        Paths.get(rankingPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
