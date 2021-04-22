package Modeloa;

import java.util.ArrayList;

public class SudokuLista {
    private static SudokuLista sudokuLista;
    private ArrayList<SudokuRetrasado>   sudokuak;


    private SudokuLista(){
        sudokuak = Irakurlea.getIrakurlea().getHasierakoSudokuGuztiak();
    }

    public static SudokuLista getSudokuLista(){
        if (sudokuLista==null){
            sudokuLista = new SudokuLista();
        }
        return sudokuLista;
    }

    public SudokuRetrasado getSudokua(int pZailtasuna){
        SudokuRetrasado nahiDugunSudokua = new SudokuRetrasado(0, null, null);

        boolean aurkitua = false;
        for (int i=0; i<sudokuak.size(); i++){
            if(sudokuak.get(i).getZailtasuna()>=pZailtasuna && !aurkitua){
                nahiDugunSudokua = sudokuak.get(i);
            }
        }

        return nahiDugunSudokua;
    }


}
