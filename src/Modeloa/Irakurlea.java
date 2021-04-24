package Modeloa;

import java.io.*;

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

    public void getHasierakoSudokuGuztiak(){
        File txtFitxategia = new File(path);
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

                SudokuLista.getSudokuLista().add(new SudokuInformazioa(zailtasuna, hasieraGelaxkak, soluzioMatrizea));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}