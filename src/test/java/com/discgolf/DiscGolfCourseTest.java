package com.discgolf;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class DiscGolfCourseTest {

    DiscGolfCourse dgc;

    @Before
    public void setup(){
        dgc = new DiscGolfCourse("My Test Course");

        Random rand = new Random();

        for(int i = 1; i < 8; i++){
            List<PinPosition> pinSet = new ArrayList<>();
            for(int j = 0; j < 4; j++){
                pinSet.add(new PinPosition(j, rand.nextInt(3) + 3, rand.nextInt(400 ) + 200));
            }

            dgc.addHole(new Hole(i, pinSet, rand.nextInt(4)));
        }
    }

    @After
    public void teardown(){
        dgc = null;
    }

    @Test
    public void populateCourse(){
        List<Hole> holes = new ArrayList<>();
        List<PinPosition> pp = new ArrayList<>();

        pp.add(new PinPosition(350));
        pp.add(new PinPosition(PinPosition.B, 4, 450));
        pp.add(new PinPosition(PinPosition.C, 3, 401));

        holes.add(new Hole(1,pp,PinPosition.A));
        holes.add(new Hole(2,pp,PinPosition.B));
        holes.add(new Hole(3,pp,PinPosition.C));
        holes.add(new Hole(4,pp,PinPosition.B));
        holes.add(new Hole(5,pp,PinPosition.A));
        holes.add(new Hole(6,pp,PinPosition.B));
        holes.add(new Hole(7,pp,PinPosition.C));

        DiscGolfCourse testdgc = new DiscGolfCourse("Populate Test", holes);

        assertEquals(3, testdgc.getHole(3).getPar());
        assertEquals(401, testdgc.getHole(3).getLengthInFeet());
        assertEquals('C', testdgc.getHole(3).getPinPosition());
    }

    @Test
    public void addHole() {
        List<PinPosition> pp = new ArrayList<>();

        pp.add(new PinPosition(350));
        pp.add(new PinPosition(PinPosition.B, 4, 450));
        pp.add(new PinPosition(PinPosition.C, 3, 401));

        Hole myHole = new Hole(8, pp, PinPosition.B);
        dgc.addHole(myHole);

        assertEquals(myHole, dgc.getHole(8));
    }

    @Test
    public void addHole_replaceHole_expectNewHole() {
        List<PinPosition> pp = new ArrayList<>();

        pp.add(new PinPosition(350));
        pp.add(new PinPosition(PinPosition.B, 4, 450));
        pp.add(new PinPosition(PinPosition.C, 3, 401));

        dgc.addHole(new Hole(4, pp, PinPosition.B));

        String getHoleString = dgc.getHole(4).toString();

        assertEquals("4B - 450ft Par 4", dgc.getHole(4).toString());
    }

    @Test
    public void getTotalPar() {
        dgc = null;
        dgc = new DiscGolfCourse("Test Course");

        List<PinPosition> pp = new ArrayList<>();

        pp.add(new PinPosition(350));
        pp.add(new PinPosition(PinPosition.B, 4, 450));
        pp.add(new PinPosition(PinPosition.C, 3, 401));

        dgc.addHole(new Hole(1, pp, PinPosition.A));
        dgc.addHole(new Hole(2, pp, PinPosition.B));
        dgc.addHole(new Hole(3, pp, PinPosition.C));
        dgc.addHole(new Hole(4, pp, PinPosition.B));

        assertEquals(14, dgc.getTotalPar());
    }

    @Test
    public void getTotalPar_outOfBoundPin_expect13() {
        dgc = null;
        dgc = new DiscGolfCourse("Test Course");

        List<PinPosition> pp = new ArrayList<>();

        pp.add(new PinPosition(350));
        pp.add(new PinPosition(PinPosition.B, 4, 450));
        pp.add(new PinPosition(PinPosition.C, 3, 401));

        dgc.addHole(new Hole(1, pp, PinPosition.A));
        dgc.addHole(new Hole(2, pp, PinPosition.B));
        dgc.addHole(new Hole(3, pp, PinPosition.C));
        dgc.addHole(new Hole(4, pp, PinPosition.D));

        assertEquals(13, dgc.getTotalPar());
    }

    @Test
    public void getHole() {
        List<PinPosition> pp = new ArrayList<>();

        pp.add(new PinPosition(350));
        pp.add(new PinPosition(PinPosition.B, 4, 450));
        pp.add(new PinPosition(PinPosition.C, 3, 401));

        dgc.addHole(new Hole(4, pp, PinPosition.B));

        Hole myHole = new Hole(7, pp, PinPosition.C);

        dgc.addHole(myHole);

        String getHoleString = dgc.getHole(7).toString();

        assertEquals('C', dgc.getHole(7).getPinPosition());
        assertEquals(401, dgc.getHole(7).getLengthInFeet());
        assertEquals(3, dgc.getHole(7).getPar());
    }

    @Test
    public void getHole_holeNumOutOfBounds_expectLastHole() {
        List<PinPosition> pp = new ArrayList<>();

        pp.add(new PinPosition(350));
        pp.add(new PinPosition(PinPosition.B, 4, 450));
        pp.add(new PinPosition(PinPosition.C, 3, 401));

        dgc.addHole(new Hole(4, pp, PinPosition.B));

        Hole myHole = new Hole(7, pp, PinPosition.C);

        dgc.addHole(myHole);

        assertEquals('C', dgc.getHole(9).getPinPosition());
        assertEquals(401, dgc.getHole(9).getLengthInFeet());
        assertEquals(3, dgc.getHole(9).getPar());
    }

    @Test
    public void getHole_holeNumOutOfBounds_expectFirstHole() {
        List<PinPosition> pp = new ArrayList<>();

        pp.add(new PinPosition(350));
        pp.add(new PinPosition(PinPosition.B, 4, 450));
        pp.add(new PinPosition(PinPosition.C, 3, 401));

        dgc.addHole(new Hole(4, pp, PinPosition.B));

        Hole myHole = new Hole(1, pp, PinPosition.C);

        dgc.addHole(myHole);

        assertEquals('C', dgc.getHole(0).getPinPosition());
        assertEquals(401, dgc.getHole(0).getLengthInFeet());
        assertEquals(3, dgc.getHole(-3).getPar());
    }


    @Test
    public void setPar() {
        List<PinPosition> pp = new ArrayList<>();

        pp.add(new PinPosition(350));
        pp.add(new PinPosition(PinPosition.B, 4, 450));
        pp.add(new PinPosition(PinPosition.C, 3, 401));

        Hole myHole = new Hole(5, pp, PinPosition.B);
        dgc.addHole(myHole);
        dgc.setPar(myHole, 5);

        assertEquals(5, dgc.getHole(5).getPar());
    }

    @Test
    public void getNextHole() {
        List<PinPosition> pp = new ArrayList<>();

        pp.add(new PinPosition(350));
        pp.add(new PinPosition(PinPosition.B, 4, 450));
        pp.add(new PinPosition(PinPosition.C, 3, 401));

        Hole myHole = new Hole(5, pp, PinPosition.B);
        Hole nextHole = new Hole(6,pp, PinPosition.C);
        dgc.addHole(myHole);
        dgc.addHole(nextHole);

        assertEquals(nextHole, dgc.getNextHole(myHole));
        assertEquals(6,dgc.getNextHole(myHole).getHoleNumber());
        assertEquals('C', dgc.getNextHole(myHole).getPinPosition());
        assertEquals(401, dgc.getNextHole(myHole).getLengthInFeet());
        assertEquals(3,dgc.getNextHole(myHole).getPar());
    }

    @Test
    public void getNextHole_lastHoleOnCourse_expectHole1() {
        List<PinPosition> pp = new ArrayList<>();

        pp.add(new PinPosition(350));
        pp.add(new PinPosition(PinPosition.B, 4, 450));
        pp.add(new PinPosition(PinPosition.C, 3, 401));

        Hole myHole = new Hole(7, pp, PinPosition.B);
        Hole nextHole = new Hole(1,pp, PinPosition.C);
        dgc.addHole(myHole);
        dgc.addHole(nextHole);

        assertEquals(nextHole, dgc.getNextHole(myHole));
        assertEquals(1,dgc.getNextHole(myHole).getHoleNumber());
        assertEquals('C', dgc.getNextHole(myHole).getPinPosition());
        assertEquals(401, dgc.getNextHole(myHole).getLengthInFeet());
        assertEquals(3,dgc.getNextHole(myHole).getPar());
    }

    @Test
    public void getPreviousHole() {
        List<PinPosition> pp = new ArrayList<>();

        pp.add(new PinPosition(350));
        pp.add(new PinPosition(PinPosition.B, 4, 450));
        pp.add(new PinPosition(PinPosition.C, 3, 401));

        Hole myHole = new Hole(5, pp, PinPosition.C);
        Hole nextHole = new Hole(6,pp, PinPosition.B);
        dgc.addHole(myHole);
        dgc.addHole(nextHole);

        assertEquals(myHole, dgc.getPreviousHole(nextHole));
        assertEquals(5,dgc.getPreviousHole(nextHole).getHoleNumber());
        assertEquals('C', dgc.getPreviousHole(nextHole).getPinPosition());
        assertEquals(401, dgc.getPreviousHole(nextHole).getLengthInFeet());
        assertEquals(3,dgc.getPreviousHole(nextHole).getPar());
    }

    @Test
    public void getPreviousHole_firstHoleOnCourse_expectLastHole() {
        List<PinPosition> pp = new ArrayList<>();

        pp.add(new PinPosition(350));
        pp.add(new PinPosition(PinPosition.B, 4, 450));
        pp.add(new PinPosition(PinPosition.C, 3, 401));

        Hole myHole = new Hole(7, pp, PinPosition.C);
        Hole nextHole = new Hole(1,pp, PinPosition.B);
        dgc.addHole(myHole);
        dgc.addHole(nextHole);

        assertEquals(myHole, dgc.getPreviousHole(nextHole));
        assertEquals(7,dgc.getPreviousHole(nextHole).getHoleNumber());
        assertEquals('C', dgc.getPreviousHole(nextHole).getPinPosition());
        assertEquals(401, dgc.getPreviousHole(nextHole).getLengthInFeet());
        assertEquals(3,dgc.getPreviousHole(nextHole).getPar());
    }

    @Test
    public void getNumOfHoles() {
        assertEquals(7, dgc.getNumOfHoles());
    }

    @Test
    public void getNumOfHoles_addHole_expect8() {
        List<PinPosition> pp = new ArrayList<>();

        pp.add(new PinPosition(350));
        pp.add(new PinPosition(PinPosition.B, 4, 450));
        pp.add(new PinPosition(PinPosition.C, 3, 401));

        Hole myHole = new Hole(8, pp, PinPosition.C);
        dgc.addHole(myHole);

        assertEquals(8, dgc.getNumOfHoles());
    }

    @Test
    public void getNumOfHoles_replaceHole_expect7() {
        List<PinPosition> pp = new ArrayList<>();

        pp.add(new PinPosition(350));
        pp.add(new PinPosition(PinPosition.B, 4, 450));
        pp.add(new PinPosition(PinPosition.C, 3, 401));

        Hole myHole = new Hole(1, pp, PinPosition.C);
        dgc.addHole(myHole);

        assertEquals(7, dgc.getNumOfHoles());
    }

    @Test
    public void getName(){
        assertEquals("My Test Course", dgc.getName());
    }
}