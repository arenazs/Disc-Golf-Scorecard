package com.discgolf;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HoleTest {
    Hole myHole1;

    @Before
    public void setup(){
        List<PinPosition> pp = new ArrayList<>();

        pp.add(new PinPosition(250));
        pp.add(new PinPosition(PinPosition.B, 320));
        pp.add(new PinPosition(PinPosition.C, 4, 450));
        pp.add(new PinPosition(PinPosition.D, 5, 730));

        myHole1 = new Hole(1, pp);
    }

    @After
    public void tearDown(){
        myHole1 = null;
    }

    @Test
    public void getPinMatrix_putInWhatsThere_expectEqual(){
        List<PinPosition> newPP = new ArrayList<>();

        newPP.add(new PinPosition(250));
        newPP.add(new PinPosition(PinPosition.B, 320));
        newPP.add(new PinPosition(PinPosition.C, 4, 450));
        newPP.add(new PinPosition(PinPosition.D, 5, 730));

        assertEquals(newPP, myHole1.getPinMatrix());
    }

    @Test
    public void getPinMatrix_putOmitPosition_expectFail(){
        List<PinPosition> newPP = new ArrayList<>();

        newPP.add(new PinPosition(250));
        newPP.add(new PinPosition(PinPosition.B, 320));
        newPP.add(new PinPosition(PinPosition.C, 4, 450));

        assertNotEquals(newPP, myHole1.getPinMatrix());
    }

    @Test
    public void getHoleNumber() {
        assertEquals(1, myHole1.getHoleNumber());
    }

    @Test
    public void getPar() {
        assertEquals(3, myHole1.getPar());
    }

    @Test
    public void getPar_changePinPosition_expect4() {
        myHole1.setPinPosition(5);

        assertEquals(3, myHole1.getPar());
    }

    @Test
    public void getPar_changePinPositionPar_expect4() {
        myHole1.setPar(4);

        assertEquals(4, myHole1.getPar());
    }

    @Test
    public void getPar_updateParAndChangePosition_expect5() {
        myHole1.setPar(4);
        myHole1.setPinPosition(PinPosition.D);

        assertEquals(5, myHole1.getPar());
    }

    @Test
    public void getPar_updateParChangePinAndReturn_expect4() {
        myHole1.setPar(4);
        myHole1.setPinPosition(PinPosition.D);
        myHole1.setPinPosition(PinPosition.A);

        assertEquals(4, myHole1.getPar());
    }

    @Test
    public void getLengthInFeet() {
        assertEquals(250, myHole1.getLengthInFeet());
    }

    @Test
    public void getLengthInFeet_changePin_expect450() {
        myHole1.setPinPosition(PinPosition.C);

        assertEquals(450, myHole1.getLengthInFeet());
    }

    @Test
    public void setLengthInFeet(){
        myHole1.setLengthInFeet(355);

        assertEquals(355, myHole1.getLengthInFeet());
    }

    @Test
    public void getPinPosition() {

        assertEquals('A', myHole1.getPinPosition());
    }

    @Test
    public void getPinPosition_changePin_expectD() {
        myHole1.setPinPosition(PinPosition.D);

        assertEquals('D', myHole1.getPinPosition());
    }

    @Test
    public void toStringTest(){
        String out = "1A - 250ft Par 3";

        assertEquals(out, myHole1.toString());
    }
}