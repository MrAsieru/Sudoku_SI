package Egitura;

public class SudokuaGorde {
    private int     zailtasuna;
    private int[][] gelaxkaMat;
    private int[][] soluzioa;

    public SudokuaGorde(int pZailtazuna, int[][] pGelaxkak, int[][] soluzioa){
        this.gelaxkaMat = pGelaxkak;
        this.zailtasuna = pZailtazuna;
        this.soluzioa=soluzioa;
    }

    public int[][] getHasierakoMatrizea() {
        return gelaxkaMat;
    }

    public int[][] getSoluzioa() {
        return soluzioa;
    }

    public int getZailtasuna(){
        return this.zailtasuna;
    }
}
