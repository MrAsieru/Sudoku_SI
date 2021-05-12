package Modeloa;

import modeloa.Partida;
import modeloa.sudokua.UnekoSudokua;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UnekoSudokuaTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        UnekoSudokua sudokua = UnekoSudokua.getInstantzia();

        int[][] hasiT = new int[][] {{9,4,0,3,0,1,7,8,5},{0,8,5,6,4,7,1,0,9},{1,0,2,9,5,8,4,6,0},
                {5,1,0,4,7,2,3,0,6},{6,9,4,1,8,0,0,7,2},{2,0,7,5,6,9,8,1,0},
                {0,0,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,0,0},{8,0,3,7,9,5,6,0,1}};

        int [][] fin = new int[][] {{9,4,6,3,2,1,7,8,5},{3,8,5,6,4,7,1,2,9},{1,7,2,9,5,8,4,6,3},
                {5,1,8,4,7,2,3,9,6},{6,9,4,1,8,3,5,7,2},{2,3,7,5,6,9,8,1,4},
                {4,5,9,8,1,6,2,3,7},{7,6,1,2,3,4,9,5,8},{8,2,3,7,9,5,6,4,1}};
    }

    @Order(1)
    @org.junit.jupiter.api.AfterEach
    void tearDown() {

    }

    @Order(2)
    @org.junit.jupiter.api.Test
    void getInstantzia() {
    }

    @Order(3)
    @org.junit.jupiter.api.Test
    void sudokuaSortu() {
    }

    @Order(4)
    @org.junit.jupiter.api.Test
    void gelaxkaHautagaiaLortu() {
    }

    @Order(5)
    @org.junit.jupiter.api.Test
    void getGelaxkaBalioak() {
    }

    @Order(6)
    @org.junit.jupiter.api.Test
    void getHautagaiakProg() {
    }

    @Order(7)
    @org.junit.jupiter.api.Test
    void getGelaxkaHutsak() {
    }

    @Order(8)
    @org.junit.jupiter.api.Test
    void aldatuGelaxkaBalioa() {
    }

    @Order(9)
    @org.junit.jupiter.api.Test
    void errenkadaHautagaiak() {
    }

    @Order(10)
    @org.junit.jupiter.api.Test
    void zutabeHautagaiak() {
    }

    @Order(11)
    @org.junit.jupiter.api.Test
    void eremuHautagaiak() {
    }

    @Order(12)
    @org.junit.jupiter.api.Test
    void aldatuGelaxkaHautagaiak() {
    }

    @Order(13)
    @org.junit.jupiter.api.Test
    void laguntzaKalkulatu() {
    }

    @Order(14)
    @org.junit.jupiter.api.Test
    void ondoDago() {
    }
}