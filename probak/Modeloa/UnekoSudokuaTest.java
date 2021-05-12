package Modeloa;

import modeloa.Partida;
import modeloa.sudokua.SudokuaGorde;
import modeloa.sudokua.UnekoSudokua;
import egitura.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnekoSudokuaTest {

    int[][] hasi;
    int[][] fin;
    SudokuaGorde sudokuaGorde;
    int zail;

    @Before
    protected void setUp() throws Exception {
        zail=1;

        hasi=new int[][] {{9,4,0,3,0,1,7,8,5},{0,8,5,6,4,7,1,0,9},{1,0,2,9,5,8,4,6,0},
                {5,1,0,4,7,2,3,0,6},{6,9,4,1,8,0,0,7,2},{2,0,7,5,6,9,8,1,0},
                {0,0,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,0,0},{8,0,3,7,9,5,6,0,1}};

        fin = new int[][] {{9,4,6,3,2,1,7,8,5},{3,8,5,6,4,7,1,2,9},{1,7,2,9,5,8,4,6,3},
                {5,1,8,4,7,2,3,9,6},{6,9,4,1,8,3,5,7,2},{2,3,7,5,6,9,8,1,4},
                {4,5,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,5,8},{8,2,3,7,9,5,6,4,1}};

        sudokuaGorde = new SudokuaGorde(zail, hasi, fin);
    }

    @Test
    void getInstantzia() {
        UnekoSudokua.getInstantzia();
    }

    @Test
    void sudokuaSortu() {
        UnekoSudokua sudokua = UnekoSudokua.getInstantzia();

        sudokua.sudokuaSortu(null);

        sudokua.sudokuaSortu(new int[][]{
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}});

        sudokua.sudokuaSortu(new int[][]{
                {9,4,0,3,0,1,7,8,5},{0,8,5,6,4,7,1,0,9},{1,0,2,9,5,8,4,6,0},
                {5,1,0,4,7,2,3,0,6},{6,9,4,1,8,0,0,7,2},{2,0,7,5,6,9,8,1,0},
                {0,0,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,0,0},{8,0,3,7,9,5,6,0,1}});

        sudokua.sudokuaSortu(new int[][] {
                {9,4,6,3,2,1,7,8,5},{3,8,5,6,4,7,1,2,9},{1,7,2,9,5,8,4,6,3},
                {5,1,8,4,7,2,3,9,6},{6,9,4,1,8,3,5,7,2},{2,3,7,5,6,9,8,1,4},
                {4,5,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,5,8},{8,2,3,7,9,5,6,4,1}});
    }

    @org.junit.jupiter.api.Test
    void getGelaxkaBalioak() {
    }

    @org.junit.jupiter.api.Test
    void gelaxkaHautagaiaLortu() {
    }

    @org.junit.jupiter.api.Test
    void getHautagaiakProg() {
    }

    @org.junit.jupiter.api.Test
    void getGelaxkaHutsak() {
    }

    @org.junit.jupiter.api.Test
    void aldatuGelaxkaBalioa() {
    }

    @org.junit.jupiter.api.Test
    void errenkadaHautagaiak() {
    }

    @org.junit.jupiter.api.Test
    void zutabeHautagaiak() {
    }

    @org.junit.jupiter.api.Test
    void eremuHautagaiak() {
    }

    @org.junit.jupiter.api.Test
    void aldatuGelaxkaHautagaiak() {
    }

    @org.junit.jupiter.api.Test
    void laguntzaKalkulatu() {
    }

    @org.junit.jupiter.api.Test
    void ondoDago() {
    }
}