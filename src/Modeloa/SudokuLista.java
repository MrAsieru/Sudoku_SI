package Modeloa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SudokuLista {
    private static SudokuLista sudokuLista;
    private List<SudokuaGorde> sudokuak;


    private SudokuLista(){
        sudokuak = new ArrayList<>();
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

    public SudokuaGorde getSudokuaZailtasuna(int pZailtasuna){
        if (sudokuak.size() > 0 && 1 <= pZailtasuna && pZailtasuna <= 3){
            List<SudokuaGorde> posibleak = sudokuak.stream().filter(p -> p.getZailtasuna() == pZailtasuna).collect(Collectors.toList());
            if (posibleak.size() == 0){
                Partida.getPartida().zailtasunaHanditu(true);
                return getSudokuaZailtasuna(pZailtasuna + 1);
            }
            else {
                SudokuaGorde s = posibleak.get(new Random().nextInt(posibleak.size()));
                sudokuak.remove(s);
                return s;
            }
        } else {
            return null;
        }
    }
}
