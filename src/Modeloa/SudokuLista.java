package Modeloa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SudokuLista {
    private static SudokuLista sudokuLista;
    private List<SudokuInformazioa> lista;


    private SudokuLista(){
        lista = new ArrayList<>();
    }

    public static SudokuLista getSudokuLista(){
        if (sudokuLista==null){
            sudokuLista = new SudokuLista();
        }
        return sudokuLista;
    }

    public void add(SudokuInformazioa pSudokua){
        lista.add(pSudokua);
    }

    public SudokuInformazioa getSudokua(int pZailtasuna){
        if (lista.size() > 0){
            List<SudokuInformazioa> posibleak = lista.stream().filter(p -> p.getZailtasuna() == pZailtasuna).collect(Collectors.toList());
            return posibleak.get(new Random().nextInt(posibleak.size()));
        } else {
            return null;
        }
    }
}
