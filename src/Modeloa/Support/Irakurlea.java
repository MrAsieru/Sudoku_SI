package Modeloa.Support;

import Egitura.PuntuazioaEgitura;
import Modeloa.Sudokua.SudokuLista;
import Modeloa.Sudokua.SudokuaGorde;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Irakurlea {
    private static Irakurlea Irakurlea;
    private String sudokuPath = "res/sudoku.txt";
    private String leaderboardPath = "res/leader_board.txt";
    private final int tamaina = 9;

    private Irakurlea(){}

    public static Irakurlea getIrakurlea() {
        if (Irakurlea==null){
            Irakurlea = new Irakurlea();
        }
        return Irakurlea;
    }

    public void getHasierakoSudokuGuztiak(){
        File txtFitxategia = new File(sudokuPath);
        BufferedReader irakurle;
        try {
            irakurle = new BufferedReader(new FileReader(txtFitxategia));

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

    public List<PuntuazioaEgitura> parseLeaderBoard(){
        List<PuntuazioaEgitura> leaderBoard = new ArrayList<>();

        ArrayList<String> ldFitxategia = this.irakurriFitxategia(leaderboardPath);
        int i = 0;
        while (++i<ldFitxategia.size()){
            //Gu nahi dugun formatua badu
            if(Regex.getRegex().leaderBoardFormatua(ldFitxategia.get(i))){
                String[] datuak = ldFitxategia.get(i).split(";");
                //Izena - zailtazuna - puntuak
                PuntuazioaEgitura puntuazioa = new PuntuazioaEgitura(
                        datuak[0],
                        Integer.parseInt(datuak[1]),
                        Double.parseDouble(datuak[2])
                );
                leaderBoard.add(puntuazioa);
            }
        }
        return leaderBoard;
    }

    private ArrayList<String> irakurriFitxategia(String path){
        ArrayList<String> testua = new ArrayList<>();
        try {
            File txtFitxategia = new File(path);
            BufferedReader irakurle = new BufferedReader(new FileReader(txtFitxategia));

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
    /**
     * Metodo honen bidez, matrizearen hasiera lerroa jakinda bere matrizea lortuko dugu.
     * @param hasierakoLerroa
     * @return int[][]
     */
    @Deprecated
    public int[][] getSudokuArrayHasiera(int hasierakoLerroa){
        int[][] sudokua = new int [tamaina] [tamaina];

        try{
            int hasieraLerroaZenbakia = hasierakoLerroa;
            File txtFitxategia = new File(sudokuPath);
            BufferedReader irakurle = new BufferedReader(new FileReader(txtFitxategia));

            try {
                String lerroa = irakurle.readLine();

                while (hasieraLerroaZenbakia-- != 0) {
                    lerroa = irakurle.readLine();
                }

                lerroa = irakurle.readLine();
                for (int i = 0; i<tamaina; i++){
                    for(int j = 0; j<tamaina; j++){

                        sudokua[i][j] = Character.getNumericValue(lerroa.toCharArray()[j]);

                    }

                    lerroa = irakurle.readLine();
                }
            }
            catch (IOException avast){avast.printStackTrace();}
        }
        catch (FileNotFoundException panda){panda.printStackTrace();}

        return sudokua;
    }
    @Deprecated
    public int[][] getSudokuArrayZuzena(int hasiera){
        int[][] sudokuZuzendua = getSudokuArrayHasiera(hasiera+tamaina);
        return sudokuZuzendua;
    }

    /**
     * beste metodo batek lortzen duen zailtazun berdinak dituzten lerroen artean bat hartuko du auzaz
     */
    @Deprecated
    public int getZailtazunLerroa(int zailtazuna){
        ArrayList<Integer> lerroak = this.getZailtazunLerroak(zailtazuna);
        return lerroak.get(new Random().nextInt(lerroak.size()));
    }

    /**
     * txt fitxategia irakurriko dugu eta zailtasunaren zenbakia bilatuko dugu.
     * Hau egiteko nahi dugu txt hartu eta karaktere bakarreko zenbakiak bilatuko ditu eta
     * bat haurkitzen badu gorde egingo du ArrayList batean, gero hau bidaltzeko.
     */
    @Deprecated
    public ArrayList<Integer> getZailtazunLerroak(int zailtasuna){
        //TODO ez apurtu zailtasuna = 0 izatean
        ArrayList<Integer> lerroak = new ArrayList<>();
        int lineCount = 0;
        try{
            //txt fitxategiko zailtazun berdineko matrizeak lortuko ditugu
            File txtFitxategia = new File(sudokuPath);
            BufferedReader irakurle = new BufferedReader(new FileReader(txtFitxategia));

            String linea;
            while ((linea = irakurle.readLine()) != null){
                if (linea.length()==1 && linea.equals(zailtasuna + "")){
                    lerroak.add(lineCount);
                }
                lineCount++;
            }
        }
        catch (IOException e){
            System.out.println("Ez da lortu txt fitxategia.");
            e.printStackTrace();
        }

        //matrizea zoriz hartuko dugu eta bidali
        return lerroak;
    }
}