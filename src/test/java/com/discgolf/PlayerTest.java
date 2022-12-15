package com.discgolf;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerTest {
    Player p1;
    Hole hole1;
    Hole hole2;
    Hole hole3;
    Hole hole4;

    @Before
    public void setup(){
        p1 = new Player("Steve");

        List<PinPosition> pp = new ArrayList<>();
        pp.add(new PinPosition(375));
        pp.add(new PinPosition(PinPosition.B, 3, 420));
        pp.add(new PinPosition(PinPosition.C, 4, 440));
        pp.add(new PinPosition(PinPosition.D, 5, 505));

        hole1 = new Hole(1, pp, PinPosition.A);
        hole2 = new Hole(2, pp, PinPosition.B);
        hole3 = new Hole(3, pp, PinPosition.C);
        hole4 = new Hole(4, pp, PinPosition.D);
    }

    @After
    public void teardown(){
        p1 = null;
        hole1 = null;
        hole2 = null;
        hole3 = null;
        hole4 = null;
    }

    @Test
    public void getTotalStrokes() {
        p1.addStroke(hole1, 3);
        p1.setCurrentHole(hole2);
        p1.addStroke(hole2, 4);
        p1.setCurrentHole(hole3);
        p1.addStroke(hole3, 3);
        p1.setCurrentHole(hole4);
        p1.addStroke(hole4, 6);

        assertEquals(16, p1.getTotalStrokes());
    }

    @Test
    public void getName(){
        assertEquals("Steve", p1.getName());
    }

    @Test
    public void getStrokesOnHole() {
        p1.addStroke(hole1, 4);
        p1.removeStroke(hole1);
        p1.removeStroke(hole1);

        assertEquals(2,p1.getStrokesOnHole(hole1));

        p1.addStroke(hole1);

        assertEquals(3, p1.getStrokesOnHole(hole1));
    }

    @Test
    public void addStroke() {
        p1.addStroke(hole1);
        p1.addStroke(hole1);
        p1.addStroke(hole1);

        assertEquals(3, p1.getStrokesOnHole(hole1));
    }

    @Test
    public void addStroke_addStrokesByNum_expect4() {
        p1.addStroke(hole1, 4);

        assertEquals(4, p1.getStrokesOnHole(hole1));
    }

    @Test
    public void addStroke_addStrokesNegNum_expect4() {
        p1.addStroke(hole1, 4);
        p1.addStroke(hole1, -4);

        assertEquals(4, p1.getStrokesOnHole(hole1));
    }

    @Test
    public void removeStroke() {
        p1.addStroke(hole1, 4);
        p1.removeStroke(hole1);
        p1.removeStroke(hole1);

        assertEquals(2,p1.getStrokesOnHole(hole1));
    }

    @Test
    public void removeStroke_removeByNum_expect1() {
        p1.addStroke(hole1, 4);
        p1.removeStroke(hole1, 3);

        assertEquals(1,p1.getStrokesOnHole(hole1));
    }

    @Test
    public void removeStroke_removeByNumNeg_expect1() {
        p1.addStroke(hole1, 4);
        p1.removeStroke(hole1, -4);

        assertEquals(4,p1.getStrokesOnHole(hole1));
    }

    @Test
    public void startNewHole() {
        p1.addStroke(hole1, 4);

        p1.setCurrentHole(hole2);

        assertEquals(0, p1.getStrokesOnHole(hole2));
    }
}