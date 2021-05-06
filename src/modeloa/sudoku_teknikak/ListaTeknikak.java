package modeloa.sudoku_teknikak;

import egitura.AldaketaEgitura;
import egitura.LaguntzaEgitura;

import java.util.ArrayList;

public class ListaTeknikak {
    private static ListaTeknikak listaTeknikak;
    private ArrayList<LaguntzaTeknika> teknikak;

    private ListaTeknikak(){
        teknikak = new ArrayList<>();
        teknikak.add(new SoleCandidate());
        teknikak.add(new UniqueCandidate());
        teknikak.add(new EremuEtaZutLerIterazioa());
        teknikak.add(new EremuEremuIterazioa());
    }

    public static ListaTeknikak getListaTeknikak(){
        if (listaTeknikak == null){
            listaTeknikak = new ListaTeknikak();
        }
        return listaTeknikak;
    }

    public LaguntzaEgitura kalkulatuLaguntzak(){
        ArrayList<String> laguntzak = new ArrayList<>();
        ArrayList<AldaketaEgitura> aldaketak = new ArrayList<>();
        for (LaguntzaTeknika teknika : teknikak){
            LaguntzaEgitura temp = teknika.laguntzaKalkulatu();
            laguntzak.addAll(temp.logger);
            aldaketak.addAll(temp.aldaketak);
        }
        return new LaguntzaEgitura(laguntzak, aldaketak);
    }

}
