package Modeloa.SudokuEstrategiak;

import Egitura.AldaketaEgitura;
import Egitura.LaguntzaEgitura;

import java.util.ArrayList;

public class ListaStrategiak {
    private static ListaStrategiak ListaStrategiak;
    private ArrayList<LaguntzaMetodoa> strategiak;

    private ListaStrategiak(){
        strategiak = new ArrayList<>();
        strategiak.add(new SoleCandidate());
        strategiak.add(new UniqueCandidate());
        strategiak.add(new EremuEtaZutLerIterazioa());
        strategiak.add(new EremuEremuIterazioa());
    }

    public static ListaStrategiak getListaStrategiak(){
        if (ListaStrategiak == null){
            ListaStrategiak = new ListaStrategiak();
        }
        return ListaStrategiak;
    }

    public LaguntzaEgitura kalkulatuLaguntzak(){
        ArrayList<String> laguntzak = new ArrayList<>();
        ArrayList<AldaketaEgitura> aldaketak = new ArrayList<>();
        for (LaguntzaMetodoa metodoa : strategiak){
            LaguntzaEgitura temp = metodoa.laguntzaKalkulatu();
            laguntzak.addAll(temp.logger);
            aldaketak.addAll(temp.aldaketak);
        }
        return new LaguntzaEgitura(laguntzak, aldaketak);
    }

}
