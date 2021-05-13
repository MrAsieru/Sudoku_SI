package Modeloa;

import static org.junit.jupiter.api.Assertions.*;

import modeloa.gelaxka.Gelaxka;
import modeloa.gelaxka.GelaxkaEditagarria;
import modeloa.sudokua.SudokuaGorde;
import modeloa.sudokua.UnekoSudokua;
import egitura.*;
import org.junit.Before;
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
    void sudokuaSortuetaGelaxkaBalioakLortu() {
        UnekoSudokua sudokua = UnekoSudokua.getInstantzia();

        sudokua.sudokuaSortu(null);
        assertNull(sudokua.getGelaxkaBalioak());

        sudokua.sudokuaSortu(new int[][] {{}});
        assertEquals(new GelaxkaEgitura[][] {{}},
                     sudokua.getGelaxkaBalioak());

        sudokua.sudokuaSortu(new int[][] {{9,4,6,3,2,1,7,8,5}});
        assertEquals(new GelaxkaEgitura[][] {{
                        new GelaxkaEgitura(9),
                        new GelaxkaEgitura(4),
                        new GelaxkaEgitura(6),
                        new GelaxkaEgitura(3),
                        new GelaxkaEgitura(2),
                        new GelaxkaEgitura(1),
                        new GelaxkaEgitura(7),
                        new GelaxkaEgitura(8),
                        new GelaxkaEgitura(5)
                }},
                sudokua.getGelaxkaBalioak());


        sudokua.sudokuaSortu(new int[][]{
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}});

        assertEquals(new GelaxkaEgitura[][] {{
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0)
                },{
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0)
                },{
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0)
                },{
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0)
                },{
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0)
                },{
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0)
                },{
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0)
                },{
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0)
                },{
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0)
                }},
                sudokua.getGelaxkaBalioak());

        sudokua.sudokuaSortu(new int[][]{
                {9,4,0,3,0,1,7,8,5},{0,8,5,6,4,7,1,0,9},{1,0,2,9,5,8,4,6,0},
                {5,1,0,4,7,2,3,0,6},{6,9,4,1,8,0,0,7,2},{2,0,7,5,6,9,8,1,0},
                {0,0,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,0,0},{8,0,3,7,9,5,6,0,1}});
        assertEquals(new GelaxkaEgitura[][] {{
                        new GelaxkaEgitura(9),
                        new GelaxkaEgitura(4),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(3),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(1),
                        new GelaxkaEgitura(7),
                        new GelaxkaEgitura(8),
                        new GelaxkaEgitura(5)
                },{
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(8),
                        new GelaxkaEgitura(5),
                        new GelaxkaEgitura(6),
                        new GelaxkaEgitura(4),
                        new GelaxkaEgitura(7),
                        new GelaxkaEgitura(1),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(9)
                },{
                        new GelaxkaEgitura(1),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(2),
                        new GelaxkaEgitura(9),
                        new GelaxkaEgitura(5),
                        new GelaxkaEgitura(8),
                        new GelaxkaEgitura(4),
                        new GelaxkaEgitura(6),
                        new GelaxkaEgitura(0)
                },{
                        new GelaxkaEgitura(5),
                        new GelaxkaEgitura(1),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(4),
                        new GelaxkaEgitura(7),
                        new GelaxkaEgitura(2),
                        new GelaxkaEgitura(3),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(6)
                },{
                        new GelaxkaEgitura(6),
                        new GelaxkaEgitura(9),
                        new GelaxkaEgitura(4),
                        new GelaxkaEgitura(1),
                        new GelaxkaEgitura(8),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(7),
                        new GelaxkaEgitura(2)
                },{
                        new GelaxkaEgitura(2),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(7),
                        new GelaxkaEgitura(5),
                        new GelaxkaEgitura(6),
                        new GelaxkaEgitura(9),
                        new GelaxkaEgitura(8),
                        new GelaxkaEgitura(1),
                        new GelaxkaEgitura(0)
                },{
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(9),
                        new GelaxkaEgitura(8),
                        new GelaxkaEgitura(1),
                        new GelaxkaEgitura(6),
                        new GelaxkaEgitura(2),
                        new GelaxkaEgitura(3),
                        new GelaxkaEgitura(7)
                },{
                        new GelaxkaEgitura(7),
                        new GelaxkaEgitura(6),
                        new GelaxkaEgitura(1),
                        new GelaxkaEgitura(2),
                        new GelaxkaEgitura(3),
                        new GelaxkaEgitura(4),
                        new GelaxkaEgitura(9),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(0)
                },{
                        new GelaxkaEgitura(8),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(3),
                        new GelaxkaEgitura(7),
                        new GelaxkaEgitura(9),
                        new GelaxkaEgitura(5),
                        new GelaxkaEgitura(6),
                        new GelaxkaEgitura(0),
                        new GelaxkaEgitura(1)
                }},
                sudokua.getGelaxkaBalioak());

        sudokua.sudokuaSortu(new int[][] {
                {9,4,6,3,2,1,7,8,5},{3,8,5,6,4,7,1,2,9},{1,7,2,9,5,8,4,6,3},
                {5,1,8,4,7,2,3,9,6},{6,9,4,1,8,3,5,7,2},{2,3,7,5,6,9,8,1,4},
                {4,5,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,5,8},{8,2,3,7,9,5,6,4,1}});
        assertEquals(new GelaxkaEgitura[][] {{
                        new GelaxkaEgitura(9),
                        new GelaxkaEgitura(4),
                        new GelaxkaEgitura(6),
                        new GelaxkaEgitura(3),
                        new GelaxkaEgitura(2),
                        new GelaxkaEgitura(1),
                        new GelaxkaEgitura(7),
                        new GelaxkaEgitura(8),
                        new GelaxkaEgitura(5)
                },{
                        new GelaxkaEgitura(3),
                        new GelaxkaEgitura(8),
                        new GelaxkaEgitura(5),
                        new GelaxkaEgitura(6),
                        new GelaxkaEgitura(4),
                        new GelaxkaEgitura(7),
                        new GelaxkaEgitura(1),
                        new GelaxkaEgitura(2),
                        new GelaxkaEgitura(9)
                },{
                        new GelaxkaEgitura(1),
                        new GelaxkaEgitura(7),
                        new GelaxkaEgitura(2),
                        new GelaxkaEgitura(9),
                        new GelaxkaEgitura(5),
                        new GelaxkaEgitura(8),
                        new GelaxkaEgitura(4),
                        new GelaxkaEgitura(6),
                        new GelaxkaEgitura(3)
                },{
                        new GelaxkaEgitura(5),
                        new GelaxkaEgitura(1),
                        new GelaxkaEgitura(8),
                        new GelaxkaEgitura(4),
                        new GelaxkaEgitura(7),
                        new GelaxkaEgitura(2),
                        new GelaxkaEgitura(3),
                        new GelaxkaEgitura(9),
                        new GelaxkaEgitura(6)
                },{
                        new GelaxkaEgitura(6),
                        new GelaxkaEgitura(9),
                        new GelaxkaEgitura(4),
                        new GelaxkaEgitura(1),
                        new GelaxkaEgitura(8),
                        new GelaxkaEgitura(3),
                        new GelaxkaEgitura(5),
                        new GelaxkaEgitura(7),
                        new GelaxkaEgitura(2)
                },{
                        new GelaxkaEgitura(2),
                        new GelaxkaEgitura(3),
                        new GelaxkaEgitura(7),
                        new GelaxkaEgitura(5),
                        new GelaxkaEgitura(6),
                        new GelaxkaEgitura(9),
                        new GelaxkaEgitura(8),
                        new GelaxkaEgitura(1),
                        new GelaxkaEgitura(4)
                },{
                        new GelaxkaEgitura(4),
                        new GelaxkaEgitura(5),
                        new GelaxkaEgitura(9),
                        new GelaxkaEgitura(8),
                        new GelaxkaEgitura(1),
                        new GelaxkaEgitura(6),
                        new GelaxkaEgitura(2),
                        new GelaxkaEgitura(3),
                        new GelaxkaEgitura(7)
                },{
                        new GelaxkaEgitura(7),
                        new GelaxkaEgitura(6),
                        new GelaxkaEgitura(1),
                        new GelaxkaEgitura(2),
                        new GelaxkaEgitura(3),
                        new GelaxkaEgitura(4),
                        new GelaxkaEgitura(9),
                        new GelaxkaEgitura(5),
                        new GelaxkaEgitura(8)
                },{
                        new GelaxkaEgitura(8),
                        new GelaxkaEgitura(2),
                        new GelaxkaEgitura(3),
                        new GelaxkaEgitura(7),
                        new GelaxkaEgitura(9),
                        new GelaxkaEgitura(5),
                        new GelaxkaEgitura(6),
                        new GelaxkaEgitura(4),
                        new GelaxkaEgitura(1)
                }},
                sudokua.getGelaxkaBalioak());
    }

    @org.junit.jupiter.api.Test
    void errenkadaHautagaiak() {
        UnekoSudokua sudokua = UnekoSudokua.getInstantzia();
        boolean[] hautagaiak;

        sudokua.sudokuaSortu(null);
        assertNull(sudokua.errenkadaHautagaiak(0));

        sudokua.sudokuaSortu(new int[][] {{}});
        assertNull(sudokua.errenkadaHautagaiak(0));


        sudokua.sudokuaSortu(new int[][] {{9,4,6,3,2,1,7,8,5}});
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(0));
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(8));

        sudokua.sudokuaSortu(new int[][] {
                {9,4,6,3,2,1,7,8,5},{3,8,5,6,4,7,1,2,9},{1,7,2,9,5,8,4,6,3},
                {5,1,8,4,7,2,3,9,6},{6,9,4,1,8,3,5,7,2},{2,3,7,5,6,9,8,1,4},
                {4,5,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,5,8},{8,2,3,7,9,5,6,4,1}});
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(0));
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(8));


        sudokua.sudokuaSortu(new int[][]{
                {9,4,0,3,0,1,7,8,5},{0,8,5,6,4,7,1,0,9},{1,0,2,9,5,8,4,6,0},
                {5,1,0,4,7,2,3,0,6},{6,9,4,1,8,0,0,7,2},{2,0,7,5,6,9,8,1,0},
                {0,0,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,0,0},{8,0,3,7,9,5,6,0,1}});
        /*
            9 4 0   0 8 5   1 0 2
            3 0 1   6 4 7   9 5 8
            7 8 5   1 0 9   4 6 0

            5 1 0   6 9 4   2 0 7
            4 7 2   1 8 0   5 6 9
            3 0 6   0 7 2   8 1 0

            0 0 9   7 6 1   8 0 3
            8 1 6   2 3 4   7 9 5
            2 3 7   9 0 0   6 0 1
         */
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(0));
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(8));


        sudokua.sudokuaSortu(new int[][]{
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}});
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(0));
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(8));
    }

    @org.junit.jupiter.api.Test
    void zutabeHautagaiak() {
        UnekoSudokua sudokua = UnekoSudokua.getInstantzia();

        sudokua.sudokuaSortu(null);

        sudokua.sudokuaSortu(new int[][] {{}});


        sudokua.sudokuaSortu(new int[][] {{9,4,6,3,2,1,7,8,5}});
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(0));
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(8));


        sudokua.sudokuaSortu(new int[][] {
                {9,4,6,3,2,1,7,8,5},{3,8,5,6,4,7,1,2,9},{1,7,2,9,5,8,4,6,3},
                {5,1,8,4,7,2,3,9,6},{6,9,4,1,8,3,5,7,2},{2,3,7,5,6,9,8,1,4},
                {4,5,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,5,8},{8,2,3,7,9,5,6,4,1}});
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(0));
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(8));


        sudokua.sudokuaSortu(new int[][]{
                {9,4,0,3,0,1,7,8,5},{0,8,5,6,4,7,1,0,9},{1,0,2,9,5,8,4,6,0},
                {5,1,0,4,7,2,3,0,6},{6,9,4,1,8,0,0,7,2},{2,0,7,5,6,9,8,1,0},
                {0,0,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,0,0},{8,0,3,7,9,5,6,0,1}});
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(0));
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(8));


        sudokua.sudokuaSortu(new int[][]{
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}});
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(0));
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(8));
    }

    @org.junit.jupiter.api.Test
    void eremuHautagaiak() {
        UnekoSudokua sudokua = UnekoSudokua.getInstantzia();

        sudokua.sudokuaSortu(null);

        sudokua.sudokuaSortu(new int[][] {{}});


        sudokua.sudokuaSortu(new int[][] {{9,4,6,3,2,1,7,8,5}});
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(0));
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(8));


        sudokua.sudokuaSortu(new int[][] {
                {9,4,6,3,2,1,7,8,5},{3,8,5,6,4,7,1,2,9},{1,7,2,9,5,8,4,6,3},
                {5,1,8,4,7,2,3,9,6},{6,9,4,1,8,3,5,7,2},{2,3,7,5,6,9,8,1,4},
                {4,5,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,5,8},{8,2,3,7,9,5,6,4,1}});
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(0));
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(8));


        sudokua.sudokuaSortu(new int[][]{
                {9,4,0,3,0,1,7,8,5},{0,8,5,6,4,7,1,0,9},{1,0,2,9,5,8,4,6,0},
                {5,1,0,4,7,2,3,0,6},{6,9,4,1,8,0,0,7,2},{2,0,7,5,6,9,8,1,0},
                {0,0,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,0,0},{8,0,3,7,9,5,6,0,1}});
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(0));
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(8));


        sudokua.sudokuaSortu(new int[][]{
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}});
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(0));
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(8));
    }

    @org.junit.jupiter.api.Test
    void gelaxkaHautagaiaLortu() {
        UnekoSudokua sudokua = UnekoSudokua.getInstantzia();

        sudokua.sudokuaSortu(null);

        sudokua.sudokuaSortu(new int[][] {{}});


        sudokua.sudokuaSortu(new int[][] {{9,4,6,3,2,1,7,8,5}});
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(0));
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(8));


        sudokua.sudokuaSortu(new int[][] {
                {9,4,6,3,2,1,7,8,5},{3,8,5,6,4,7,1,2,9},{1,7,2,9,5,8,4,6,3},
                {5,1,8,4,7,2,3,9,6},{6,9,4,1,8,3,5,7,2},{2,3,7,5,6,9,8,1,4},
                {4,5,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,5,8},{8,2,3,7,9,5,6,4,1}});
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(0));
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(8));


        sudokua.sudokuaSortu(new int[][]{
                {9,4,0,3,0,1,7,8,5},{0,8,5,6,4,7,1,0,9},{1,0,2,9,5,8,4,6,0},
                {5,1,0,4,7,2,3,0,6},{6,9,4,1,8,0,0,7,2},{2,0,7,5,6,9,8,1,0},
                {0,0,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,0,0},{8,0,3,7,9,5,6,0,1}});
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(0));
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(8));


        sudokua.sudokuaSortu(new int[][]{
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}});
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(0));
        assertEquals(new boolean[] {false, false, false,  false, false, false, false, false, false}, sudokua.errenkadaHautagaiak(8));

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
    void aldatuGelaxkaHautagaiak() {
    }

    @org.junit.jupiter.api.Test
    void laguntzaKalkulatu() {
    }

    @org.junit.jupiter.api.Test
    void ondoDago() {
    }
}