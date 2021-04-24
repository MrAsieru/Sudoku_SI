package Modeloa;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Irakurlea {
    private static Irakurlea Irakurlea;
    private String path = "res/sudoku.txt";
    private final int tamaina = 9;

    private Irakurlea(){}

    public static Irakurlea getIrakurlea() {
        if (Irakurlea==null){
            Irakurlea = new Irakurlea();
        }
        return Irakurlea;
    }

    /**
     * Metodo honen bidez, matrizearen hasiera lerroa jakinda bere matrizea lortuko dugu.
     * @param hasierakoLerroa
     * @return int[][]
     */
    public int[][] getSudokuArrayHasiera(int hasierakoLerroa){
        int[][] sudokua = new int [tamaina] [tamaina];

        try{
            int hasieraLerroaZenbakia = hasierakoLerroa;
            File txtFitxategia = new File(path);
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

    public int[][] getSudokuArrayZuzena(int hasiera){
        int[][] sudokuZuzendua = getSudokuArrayHasiera(hasiera+tamaina);
        return sudokuZuzendua;
    }

    /**
     * beste metodo batek lortzen duen zailtazun berdinak dituzten lerroen artean bat hartuko du auzaz
     */

    public int getZailtazunLerroa(int zailtazuna){
        ArrayList<Integer> lerroak = this.getZailtazunLerroak(zailtazuna);
        return lerroak.get(new Random().nextInt(lerroak.size()));
    }

    /**
     * txt fitxategia irakurriko dugu eta zailtasunaren zenbakia bilatuko dugu.
     * Hau egiteko nahi dugu txt hartu eta karaktere bakarreko zenbakiak bilatuko ditu eta
     * bat haurkitzen badu gorde egingo du ArrayList batean, gero hau bidaltzeko.
     */
    public ArrayList<Integer> getZailtazunLerroak(int zailtasuna){
        //TODO ez apurtu zailtasuna = 0 izatean
        ArrayList<Integer> lerroak = new ArrayList<>();
        int lineCount = 0;
        try{
            //txt fitxategiko zailtazun berdineko matrizeak lortuko ditugu
            File txtFitxategia = new File(path);
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