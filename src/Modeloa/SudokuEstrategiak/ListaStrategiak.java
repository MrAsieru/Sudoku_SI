package Modeloa.SudokuEstrategiak;

import Egitura.LaguntzaEgitura;
import Modeloa.Partida;
import Modeloa.Sudokua.Sudoku;

import java.util.ArrayList;

public class ListaStrategiak {
    private static ListaStrategiak ListaStrategiak;
    private ArrayList<LaguntzaMetodoa> strategiak;

    private ListaStrategiak(){
        strategiak = new ArrayList<>();
        strategiak.add(new EremuIterazioa());
        strategiak.add(new LerroIterazioa());
        strategiak.add(new SoleCandidate());
        strategiak.add(new UniqueCandidate());
    }

    public static ListaStrategiak getListaStrategiak(){
        if (ListaStrategiak == null){
            ListaStrategiak = new ListaStrategiak();
        }
        return ListaStrategiak;
    }

    public ArrayList<LaguntzaEgitura> kalkulatuLaguntzak(){
        ArrayList<LaguntzaEgitura> laguntzak = new ArrayList<>();

        for (LaguntzaMetodoa laguntza : strategiak){
            for (int i = 0; i<Partida.getPartida().getSudoku().getTamaina(); i++){
                for (int j = 0; j<Partida.getPartida().getSudoku().getTamaina(); j++){
                    Integer erantzuna = laguntza.laguntza(i,j);
                    if (erantzuna!=null) laguntzak.add(new LaguntzaEgitura(i, j, erantzuna, laguntza.getMetodoIzena()));
                }
            }
        }

        return laguntzak;
    }

}
