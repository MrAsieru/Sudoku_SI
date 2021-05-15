package Modeloa;

import egitura.GelaxkaEgitura;
import modeloa.sudokua.SudokuaGorde;
import modeloa.sudokua.UnekoSudokua;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

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
    void sudokuaSortuetaGelaxkaBalioakLortu() {
        UnekoSudokua sudokua = UnekoSudokua.getInstantzia();

        sudokua.sudokuaSortu(null);
        assertNull(sudokua.getGelaxkaBalioak());

        sudokua.sudokuaSortu(new int[][] {{}});
        assertNull(sudokua.getGelaxkaBalioak());

        sudokua.sudokuaSortu(new int[][] {{9,4,6,3,2,1,7,8,5}});
        assertNull(sudokua.getGelaxkaBalioak());


        sudokua.sudokuaSortu(new int[][]{
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}});

        assertTrue(Arrays.stream(sudokua.getGelaxkaBalioak()).
                flatMap(Arrays::stream).
                allMatch(g -> g.balioa == null));

        sudokua.sudokuaSortu(new int[][]{
                {9,4,0,3,0,1,7,8,5},{0,8,5,6,4,7,1,0,9},{1,0,2,9,5,8,4,6,0},
                {5,1,0,4,7,2,3,0,6},{6,9,4,1,8,0,0,7,2},{2,0,7,5,6,9,8,1,0},
                {0,0,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,0,0},{8,0,3,7,9,5,6,0,1}});
        assertTrue(gelaxkaBalioakKonparatu(sudokua.getGelaxkaBalioak(), new int[][]{
                {9,4,0,3,0,1,7,8,5},{0,8,5,6,4,7,1,0,9},{1,0,2,9,5,8,4,6,0},
                {5,1,0,4,7,2,3,0,6},{6,9,4,1,8,0,0,7,2},{2,0,7,5,6,9,8,1,0},
                {0,0,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,0,0},{8,0,3,7,9,5,6,0,1}}));


        sudokua.sudokuaSortu(new int[][] {
                {9,4,6,3,2,1,7,8,5},{3,8,5,6,4,7,1,2,9},{1,7,2,9,5,8,4,6,3},
                {5,1,8,4,7,2,3,9,6},{6,9,4,1,8,3,5,7,2},{2,3,7,5,6,9,8,1,4},
                {4,5,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,5,8},{8,2,3,7,9,5,6,4,1}});
        assertTrue(gelaxkaBalioakKonparatu(sudokua.getGelaxkaBalioak(), new int[][]{
                {9,4,6,3,2,1,7,8,5},{3,8,5,6,4,7,1,2,9},{1,7,2,9,5,8,4,6,3},
                {5,1,8,4,7,2,3,9,6},{6,9,4,1,8,3,5,7,2},{2,3,7,5,6,9,8,1,4},
                {4,5,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,5,8},{8,2,3,7,9,5,6,4,1}}));

    }

    @org.junit.jupiter.api.Test
    void errenkadaHautagaiak() {
        UnekoSudokua sudokua = UnekoSudokua.getInstantzia();
        boolean[] hautagaiak;

        sudokua.sudokuaSortu(new int[][] {
                {9,4,6,3,2,1,7,8,5},{3,8,5,6,4,7,1,2,9},{1,7,2,9,5,8,4,6,3},
                {5,1,8,4,7,2,3,9,6},{6,9,4,1,8,3,5,7,2},{2,3,7,5,6,9,8,1,4},
                {4,5,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,5,8},{8,2,3,7,9,5,6,4,1}});
        //                          1      2,     3,      4,     5,     6,     7,     8,     9
        assertArrayEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(0));
        //                          1      2,     3,      4,     5,     6,     7,     8,     9
        assertArrayEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(8));


        sudokua.sudokuaSortu(new int[][]{
                {9,4,0,3,0,1,7,8,5},{0,8,5,6,4,7,1,0,9},{1,0,2,9,5,8,4,6,0},
                {5,1,0,4,7,2,3,0,6},{6,9,4,1,8,0,0,7,2},{2,0,7,5,6,9,8,1,0},
                {0,0,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,0,0},{8,0,3,7,9,5,6,0,1}});
        /*
            9 4 0   3 0 1   7 8 5
            0 8 5   6 4 7   1 0 9
            1 0 2   9 5 8   4 6 0

            5 1 0   4 7 2   3 0 6
            6 9 4   1 8 0   0 7 2
            2 0 7   5 6 9   8 1 0

            0 0 9   8 1 6   2 3 7
            7 6 1   2 3 4   9 0 0
            8 0 3   7 9 5   6 0 1
         */
        //                          1      2,     3,      4,     5,     6,     7,     8,     9
        assertArrayEquals(new boolean[] {false, true, false,  false, false, true, false, false, false}, sudokua.errenkadaHautagaiak(0));
        //                          1      2,     3,      4,     5,     6,     7,     8,     9
        assertArrayEquals(new boolean[] {false, true, false,  true, false, false, false, false, false}, sudokua.errenkadaHautagaiak(8));


        sudokua.sudokuaSortu(new int[][]{
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}});
        //                              1      2,     3,      4,     5,     6,     7,     8,     9
        assertArrayEquals(new boolean[] {true, true, true,  true, true, true, true, true, true}, sudokua.errenkadaHautagaiak(0));
        //                              1      2,     3,      4,     5,     6,     7,     8,     9
        assertArrayEquals(new boolean[] {true, true, true,  true, true, true, true, true, true}, sudokua.errenkadaHautagaiak(8));    }

    @Test
    void zutabeHautagaiak() {
        UnekoSudokua sudokua = UnekoSudokua.getInstantzia();

        sudokua.sudokuaSortu(new int[][] {
                {9,4,6,3,2,1,7,8,5},{3,8,5,6,4,7,1,2,9},{1,7,2,9,5,8,4,6,3},
                {5,1,8,4,7,2,3,9,6},{6,9,4,1,8,3,5,7,2},{2,3,7,5,6,9,8,1,4},
                {4,5,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,5,8},{8,2,3,7,9,5,6,4,1}});
        /*
            9 4 0   3 0 1   7 8 5
            0 8 5   6 4 7   1 0 9
            1 0 2   9 5 8   4 6 0

            5 1 0   4 7 2   3 0 6
            6 9 4   1 8 0   0 7 2
            2 0 7   5 6 9   8 1 0

            0 0 9   8 1 6   2 3 7
            7 6 1   2 3 4   9 0 0
            8 0 3   7 9 5   6 0 1
         */
        //                          1      2,     3,      4,     5,     6,     7,     8,     9
        assertArrayEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.zutabeHautagaiak(0));
        //                          1      2,     3,      4,     5,     6,     7,     8,     9
        assertArrayEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.zutabeHautagaiak(8));


        sudokua.sudokuaSortu(new int[][]{
                {9,4,0,3,0,1,7,8,5},{0,8,5,6,4,7,1,0,9},{1,0,2,9,5,8,4,6,0},
                {5,1,0,4,7,2,3,0,6},{6,9,4,1,8,0,0,7,2},{2,0,7,5,6,9,8,1,0},
                {0,0,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,0,0},{8,0,3,7,9,5,6,0,1}});
        /*
            9 4 0   3 0 1   7 8 5
            0 8 5   6 4 7   1 0 9
            1 0 2   9 5 8   4 6 0

            5 1 0   4 7 2   3 0 6
            6 9 4   1 8 0   0 7 2
            2 0 7   5 6 9   8 1 0

            0 0 9   8 1 6   2 3 7
            7 6 1   2 3 4   9 0 0
            8 0 3   7 9 5   6 0 1
         */
        //                                  1      2,     3,      4,     5,     6,     7,     8,     9
        assertArrayEquals(new boolean[] {false, false, true,  true, false, false, false, false, false}, sudokua.zutabeHautagaiak(0));
        //                                  1      2,     3,      4,     5,     6,     7,     8,     9
        assertArrayEquals(new boolean[] {false, false, true,  true, false, false, false, true, false}, sudokua.zutabeHautagaiak(8));


        sudokua.sudokuaSortu(new int[][]{
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}});
        //                              1      2,     3,      4,     5,     6,     7,     8,     9
        assertArrayEquals(new boolean[] {true, true, true,  true, true, true, true, true, true}, sudokua.zutabeHautagaiak(0));
        //                              1      2,     3,      4,     5,     6,     7,     8,     9
        assertArrayEquals(new boolean[] {true, true, true,  true, true, true, true, true, true}, sudokua.zutabeHautagaiak(8));
    }

    @Test
    void eremuHautagaiak() {
        UnekoSudokua sudokua = UnekoSudokua.getInstantzia();

        sudokua.sudokuaSortu(new int[][] {
                {9,4,6,3,2,1,7,8,5},{3,8,5,6,4,7,1,2,9},{1,7,2,9,5,8,4,6,3},
                {5,1,8,4,7,2,3,9,6},{6,9,4,1,8,3,5,7,2},{2,3,7,5,6,9,8,1,4},
                {4,5,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,5,8},{8,2,3,7,9,5,6,4,1}});
        //                          1      2,     3,      4,     5,     6,     7,     8,     9
        assertArrayEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.eremuHautagaiak(0,0));
        //                          1      2,     3,      4,     5,     6,     7,     8,     9
        assertArrayEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.eremuHautagaiak(8,8));


        sudokua.sudokuaSortu(new int[][]{
                {9,4,0,3,0,1,7,8,5},{0,8,5,6,4,7,1,0,9},{1,0,2,9,5,8,4,6,0},
                {5,1,0,4,7,2,3,0,6},{6,9,4,1,8,0,0,7,2},{2,0,7,5,6,9,8,1,0},
                {0,0,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,0,0},{8,0,3,7,9,5,6,0,1}});
        /*
            9 4 0   3 0 1   7 8 5
            0 8 5   6 4 7   1 0 9
            1 0 2   9 5 8   4 6 0

            5 1 0   4 7 2   3 0 6
            6 9 4   1 8 0   0 7 2
            2 0 7   5 6 9   8 1 0

            0 0 9   8 1 6   2 3 7
            7 6 1   2 3 4   9 0 0
            8 0 3   7 9 5   6 0 1
         */
        //                              1      2,     3,      4,     5,     6,     7,     8,     9
        assertArrayEquals(new boolean[] {false, false, true,  false, false, true, true, false, false}, sudokua.eremuHautagaiak(2,0));
        //                              1      2,     3,      4,     5,     6,     7,     8,     9
        assertArrayEquals(new boolean[] {false, false, false,  true, true, false, false, true, false}, sudokua.eremuHautagaiak(8,7));



        sudokua.sudokuaSortu(new int[][]{
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}});
        //                              1      2,     3,      4,     5,     6,     7,     8,     9
        assertArrayEquals(new boolean[] {true, true, true,  true, true, true, true, true, true}, sudokua.eremuHautagaiak(0,0));
        //                              1      2,     3,      4,     5,     6,     7,     8,     9
        assertArrayEquals(new boolean[] {true, true, true,  true, true, true, true, true, true}, sudokua.eremuHautagaiak(8,8));
    }

    @Test
    void hautagaiakKalkulatu() {
        UnekoSudokua sudokua = UnekoSudokua.getInstantzia();

        sudokua.sudokuaSortu(new int[][] {
                {9,4,6,3,2,1,7,8,5},{3,8,5,6,4,7,1,2,9},{1,7,2,9,5,8,4,6,3},
                {5,1,8,4,7,2,3,9,6},{6,9,4,1,8,3,5,7,2},{2,3,7,5,6,9,8,1,4},
                {4,5,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,5,8},{8,2,3,7,9,5,6,4,1}});
        //                              1      2,     3,      4,     5,     6,     7,     8,     9
        assertArrayEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.hautagaiakKalkulatu(0,0));
        //                              1      2,     3,      4,     5,     6,     7,     8,     9
        assertArrayEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.hautagaiakKalkulatu(8,8));


        sudokua.sudokuaSortu(new int[][]{
                {9,4,0,3,0,1,7,8,5},{0,8,5,6,4,7,1,0,9},{1,0,2,9,5,8,4,6,0},
                {5,1,0,4,7,2,3,0,6},{6,9,4,1,8,0,0,7,2},{2,0,7,5,6,9,8,1,0},
                {0,0,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,0,0},{8,0,3,7,9,5,6,0,1}});
        /*
            9 4 0   3 0 1   7 8 5   3, 6, 7 erem
            0 8 5   6 4 7   1 0 9   2, 3 erren
            1 0 2   9 5 8   4 6 0   3, 4 zutabe

            5 1 0   4 7 2   3 0 6
            6 9 4   1 8 0   0 7 2
            2 0 7   5 6 9   8 1 0

            0 0 9   8 1 6   2 3 7   4,5,6 ere
            7 6 1   2 3 4   9 0 0   2,4,5,9 zut
            8 0 3   7 9 5   6 0 1   2,4 erre
         */
        //                              1      2,     3,      4,     5,     6,     7,     8,     9
        assertArrayEquals(new boolean[] {false, false, true,  false, false, false, false, false, false}, sudokua.hautagaiakKalkulatu(2,0));
        //                              1      2,     3,      4,     5,     6,     7,     8,     9
        assertArrayEquals(new boolean[] {false, false, false,  true, false, false, false, false, false}, sudokua.hautagaiakKalkulatu(8,7));


        sudokua.sudokuaSortu(new int[][]{
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}});
        //                              1     2,    3,     4,    5,    6,    7,    8,    9
        assertArrayEquals(new boolean[] {true, true, true,  true, true, true, true, true, true}, sudokua.hautagaiakKalkulatu(0,0));
        //                              1     2,    3,     4,    5,    6,    7,    8,    9
        assertArrayEquals(new boolean[] {true, true, true,  true, true, true, true, true, true}, sudokua.hautagaiakKalkulatu(8,8));

    }

    @Test
    void aldatuGelaxkaBalioa() {
        UnekoSudokua sudokua = UnekoSudokua.getInstantzia();

        sudokua.sudokuaSortu(new int[][]{
                {9,4,0,3,0,1,7,8,5},{0,8,5,6,4,7,1,0,9},{1,0,2,9,5,8,4,6,0},
                {5,1,0,4,7,2,3,0,6},{6,9,4,1,8,0,0,7,2},{2,0,7,5,6,9,8,1,0},
                {0,0,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,0,0},{8,0,3,7,9,5,6,0,1}});
        sudokua.aldatuGelaxkaBalioa(1,0,0);
        assertTrue(gelaxkaBalioakKonparatu(sudokua.getGelaxkaBalioak(), new int[][] {
                {9,4,0,3,0,1,7,8,5},{0,8,5,6,4,7,1,0,9},{1,0,2,9,5,8,4,6,0},
                {5,1,0,4,7,2,3,0,6},{6,9,4,1,8,0,0,7,2},{2,0,7,5,6,9,8,1,0},
                {0,0,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,0,0},{8,0,3,7,9,5,6,0,1}}));

        sudokua.sudokuaSortu(new int[][]{
                {9,4,0,3,0,1,7,8,5},{0,8,5,6,4,7,1,0,9},{1,0,2,9,5,8,4,6,0},
                {5,1,0,4,7,2,3,0,6},{6,9,4,1,8,0,0,7,2},{2,0,7,5,6,9,8,1,0},
                {0,0,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,0,0},{8,0,3,7,9,5,6,0,1}});
        sudokua.aldatuGelaxkaBalioa(1,1,1);
        assertTrue(gelaxkaBalioakKonparatu(sudokua.getGelaxkaBalioak(), new int[][]{
                {9,4,0,3,0,1,7,8,5},{0,8,5,6,4,7,1,0,9},{1,0,2,9,5,8,4,6,0},
                {5,1,0,4,7,2,3,0,6},{6,9,4,1,8,0,0,7,2},{2,0,7,5,6,9,8,1,0},
                {0,0,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,0,0},{8,0,3,7,9,5,6,0,1}}));

        sudokua.sudokuaSortu(new int[][]{
                {9,4,0,3,0,1,7,8,5},{0,8,5,6,4,7,1,0,9},{1,0,2,9,5,8,4,6,0},
                {5,1,0,4,7,2,3,0,6},{6,9,4,1,8,0,0,7,2},{2,0,7,5,6,9,8,1,0},
                {0,0,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,0,0},{8,0,3,7,9,5,6,0,1}});
        sudokua.aldatuGelaxkaBalioa(1,0,1);
        assertTrue(gelaxkaBalioakKonparatu(sudokua.getGelaxkaBalioak(), new int[][]{
                {9,4,0,3,0,1,7,8,5},{1,8,5,6,4,7,1,0,9},{1,0,2,9,5,8,4,6,0},
                {5,1,0,4,7,2,3,0,6},{6,9,4,1,8,0,0,7,2},{2,0,7,5,6,9,8,1,0},
                {0,0,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,0,0},{8,0,3,7,9,5,6,0,1}}));

    }


//    @org.junit.jupiter.api.Test
//    void aldatuGelaxkaHautagaiak() {
//        UnekoSudokua sudokua = UnekoSudokua.getInstantzia();
//
//        sudokua.sudokuaSortu(new int[][] {{9,4,6,3,2,1,7,8,5}});
//        sudokua.aldatuGelaxkaBalioa(0,0,-1);
//        assertEquals(new GelaxkaEgitura[][] {{
//                        new GelaxkaEgitura(9),
//                        new GelaxkaEgitura(4),
//                        new GelaxkaEgitura(6),
//                        new GelaxkaEgitura(3),
//                        new GelaxkaEgitura(2),
//                        new GelaxkaEgitura(1),
//                        new GelaxkaEgitura(7),
//                        new GelaxkaEgitura(8),
//                        new GelaxkaEgitura(5)
//                }},
//                sudokua.getGelaxkaBalioak());
//    }
    
    private boolean gelaxkaBalioakKonparatu(GelaxkaEgitura[][] benetakoa, int[][] esperotakoa){
        boolean ondo = true;
        int i = 0;
        while (i < 9 && ondo){
            int j = 0;
            while (j < 9 && ondo) {
                if (benetakoa[i][j].balioa == null) {
                    if (esperotakoa[i][j] != 0) ondo = false;
                } else {
                    if (benetakoa[i][j].balioa != esperotakoa[i][j]) ondo = false;
                }
                j++;
            }
            i++;
        }
        return ondo;
    }
}