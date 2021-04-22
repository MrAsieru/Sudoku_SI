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

    public ArrayList<SudokuRetrasado> getHasierakoSudokuGuztiak(){
        ArrayList<SudokuRetrasado> sudukuak = new ArrayList<>();

        File txtFitxategia = new File(path);
        BufferedReader irakurle;
        try {
            irakurle = new BufferedReader(new FileReader(txtFitxategia));

            int sudokuZenbat = 0;
            while (irakurle.readLine()!=null){
                int zailtasuna = Integer.parseInt(irakurle.readLine());
                Gelaxka[][] hasieraGelaxkak = new Gelaxka[9][9];
                int[][] soluzioMatrizea = new int[9][9];

                for (int i = 0; i<9; i++){
                    char[] lerroa = irakurle.readLine().toCharArray();
                    for (int z=0; z<9; z++){
                        Gelaxka gel;
                        if (lerroa[z]=='0'){
                            gel = GelaxkaFactory.getInstantzia().gelaxkaSortu(GelaxkaMotak.EDITAGARRIA, i, z, 0);
                        } else {
                            gel = GelaxkaFactory.getInstantzia().gelaxkaSortu(GelaxkaMotak.HASIERAKOA, i, z, lerroa[z]-0);
                        }
                        hasieraGelaxkak[i][z] = gel;
                    }
                }

                for (int i = 0; i<9; i++){
                    char[] lerroa = irakurle.readLine().toCharArray();
                    for (int z=0; z<9; z++){
                        soluzioMatrizea[i][z] = lerroa[z];
                    }
                }

                sudukuak.add(new SudokuRetrasado(zailtasuna, hasieraGelaxkak, soluzioMatrizea));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return sudukuak;
    }

    public ArrayList<Integer[][]> getSudokuSoluzioGuztiak(){
        ArrayList<Integer[][]> sudukuak = new ArrayList<>();

        File txtFitxategia = new File(path);
        BufferedReader irakurle;
        try {
            irakurle = new BufferedReader(new FileReader(txtFitxategia));

            int sudokuZenbat = 0;
            while (irakurle.readLine()!=null){

                for (int i = 0; i<9; i++){
                    irakurle.readLine();
                }

                Integer[][] soluzia = new Integer[9][9];

                for (int i = 0; i<9; i++){
                    char[] lerroa = irakurle.readLine().toCharArray();
                    for (int z=0; z<9; z++){
                        soluzia[i][z] = (lerroa[z] - 0);
                    }
                }

                sudukuak.add(soluzia);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return sudukuak;
    }



    @Deprecated
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

    @Deprecated
    /**
     * txt fitxategia irakurriko dugu eta zailtasunaren zenbakia bilatuko dugu.
     * Hau egiteko nahi dugu txt hartu eta karaktere bakarreko zenbakiak bilatuko ditu eta
     * bat haurkitzen badu gorde egingo du ArrayList batean, gero hau bidaltzeko.
     */
    private ArrayList<Integer> getZailtazunLerroak(int zailtasuna){
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

        //Begiratuko dugu matrizerik aurkitu ez dugun
        if(lerroak.size()==0){
            System.exit(0);
        }

        //matrizea zoriz hartuko dugu eta bidali
        return lerroak;
    }
}