package Modeloa;

public class SudokuRetrasado {
    private int         zailtasuna;
    private Gelaxka[][] matrizdemierdainicialinutil;
    private int[][]     soluzioa;

    public SudokuRetrasado(int zailtasuna, Gelaxka[][] gelaxkas, int[][] soluzioa){
        this.matrizdemierdainicialinutil=gelaxkas;
        this.soluzioa=soluzioa;
    }

    public Gelaxka[][] getHasierakoMatrizea() {
        return matrizdemierdainicialinutil;
    }

    public int[][] getSoluzioa() {
        return soluzioa;
    }

    public int getZailtasuna(){
        return this.zailtasuna;
    }
}
