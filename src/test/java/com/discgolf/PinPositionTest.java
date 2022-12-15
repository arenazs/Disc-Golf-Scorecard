package com.discgolf;

import org.junit.Test;

import static org.junit.Assert.*;

public class PinPositionTest {

    @Test
    public void getPlacementAsChar_noDefinedPlacement_expectA() {
        //arrange
        PinPosition a = new PinPosition(500);

        //act

        //assert
        assertEquals('A', a.getPlacementAsChar());
    }

    @Test
    public void getPlacementAsChar_noDefinedBPlacement_expectB() {
        //arrange
        PinPosition pp = new PinPosition(PinPosition.B, 500);

        //act

        //assert
        assertEquals('B', pp.getPlacementAsChar());
    }

    @Test
    public void getPar_useDefaultConst_expect4() {
        //arrange
        PinPosition pp = new PinPosition(300);

        pp.setPar(4);

        assertEquals(4, pp.getPar());
    }

    @Test
    public void getPar_setParOnConst_expect4() {
        //arrange
        PinPosition pp = new PinPosition(PinPosition.B,5, 400);

        pp.setPar(4);

        assertEquals(4, pp.getPar());
    }

    @Test
    public void setPar_setParUnderBounds_expect3() {
        //arrange
        PinPosition pp = new PinPosition(PinPosition.B,5, 400);

        pp.setPar(2);

        assertEquals(3, pp.getPar());
    }

    @Test
    public void setPar_setParOverBounds_expect5() {
        //arrange
        PinPosition pp = new PinPosition(PinPosition.B,8, 400);

        assertEquals(5, pp.getPar());
    }

    @Test
    public void getLengthInFeet_usedBasicConst_expect500() {
        PinPosition pp = new PinPosition(500);

        assertEquals(500, pp.getLengthInFeet());
    }

    @Test
    public void getLengthInFeet_used2ArgConst_expect500() {
        PinPosition pp = new PinPosition(PinPosition.C,500);

        assertEquals(500, pp.getLengthInFeet());
    }

    @Test
    public void getLengthInFeet_usedFullConst_expect500() {
        PinPosition pp = new PinPosition(PinPosition.C, 4,500);

        assertEquals(500, pp.getLengthInFeet());
    }

    @Test
    public void getLengthInFeet_changeLengthAfterConst_expect320() {
        PinPosition pp = new PinPosition(PinPosition.C, 4,500);

        pp.setLength(320);

        assertEquals(320, pp.getLengthInFeet());
    }

    @Test
    public void getLengthInMeters() {
    }

    @Test
    public void toStringTest(){
        PinPosition pp = new PinPosition(PinPosition.C, 4,500);
        String out = "C - 500ft Par 4";

        assertEquals(out, pp.toString());
    }
}