package Modeloa;

import java.util.ArrayList;

public class SudokuLista {
    private static SudokuLista sudokuLista;
    private ArrayList<SudokuaGorde>  sudokuak;


    private SudokuLista(){
        this.sudokuak = new ArrayList<>();
        System.out.printf("Kaixo");
        ArrayList<Integer> zailtazunLerroak = Irakurlea.getIrakurlea().getZailtazunLerroak(1);
        System.out.printf("hey");
        for (int i=1; i<3 && zailtazunLerroak.size()>=0; i++){
            for (Integer pointer: zailtazunLerroak){
                addSudoku(new SudokuaGorde(i, Irakurlea.getIrakurlea().getSudokuArrayHasiera(pointer), Irakurlea.getIrakurlea().getSudokuArrayZuzena(pointer)));
            }
            zailtazunLerroak = Irakurlea.getIrakurlea().getZailtazunLerroak(i+1);;
        }
    }

    public static SudokuLista getSudokuLista(){
        if (sudokuLista==null){
            sudokuLista = new SudokuLista();
        }
        return sudokuLista;
    }

    public void addSudoku(SudokuaGorde pSudoku){
        this.sudokuak.add(pSudoku);
    }

    public SudokuaGorde getSudokuaZailtazunes(int pZailtasuna){
        SudokuaGorde nahiDugunSudokua = null;

        boolean aurkitua = false;
        for (int i=0; i<sudokuak.size(); i++){
            if(sudokuak.get(i).getZailtasuna()>=pZailtasuna && !aurkitua){
                nahiDugunSudokua = sudokuak.get(i);
            }
        }

        return nahiDugunSudokua;
    }
}
