package Modeloa;

public class SudokuInformazioa {
    private int zailtasuna;
    private int[][] hasierakoBalioak;
    private int[][] soluzioa;

    public SudokuInformazioa(int pZailtasuna, int[][] pHasierakoBalioak, int[][] pSoluzioa){
        zailtasuna = pZailtasuna;
        hasierakoBalioak = pHasierakoBalioak;
        soluzioa = pSoluzioa;
    }

    public int[][] getHasierakoMatrizea() {
        return hasierakoBalioak;
    }

    public int[][] getSoluzioa() {
        return soluzioa;
    }

    public int getZailtasuna(){
        return this.zailtasuna;
    }
}
