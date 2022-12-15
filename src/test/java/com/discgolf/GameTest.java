package com.discgolf;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {
    Game game;
    DiscGolfCourse dgc;
    Hole hole1, hole2, hole3, hole4;
    List<Player> players = new ArrayList<>();
    Player s, j;

    @Before
    public void setup(){
        List<PinPosition> pp = new ArrayList<>();
        pp.add(new PinPosition(375));
        pp.add(new PinPosition(PinPosition.B, 3, 420));
        pp.add(new PinPosition(PinPosition.C, 4, 440));
        pp.add(new PinPosition(PinPosition.D, 5, 505));

        List<Hole> holes = new ArrayList<>();
        hole1 = new Hole(1, pp, PinPosition.A);
        holes.add(hole1);
        hole2 = new Hole(2, pp, PinPosition.B);
        holes.add(hole2);
        hole3 = new Hole(3, pp, PinPosition.C);
        holes.add(hole3);
        hole4 = new Hole(4, pp, PinPosition.D);
        holes.add(hole4);

        s = new Player("Steve");
        players.add(s);
        j = new Player("Jared");
        players.add(j);

        dgc = new DiscGolfCourse("Test Course", holes);

        game = new Game(dgc, dgc.getHole(1), players);
    }

    @After
    public void teardown(){
        players = null;
        hole1 = null;
        hole2 = null;
        hole3 = null;
        hole4 = null;
        dgc = null;
        game = null;
    }


    @Test
    public void addPlayer_expectPlayerInList() {
        Player m = new Player("Matt");

        game.addPlayer(m);
        List<Player> list = game.getListOfPlayers();

        assertEquals(3, list.size());
        assertEquals(list.get(0), s);
        assertEquals(list.get(1), j);
        assertEquals(list.get(2), m);
    }

    @Test
    public void updatePlayerOrder() {
        game.addStroke(s, 3);
        game.addStroke(j, 2);

        game.updatePlayerOrder();
        List<Player> list = game.getListOfPlayers();


        assertEquals(list.get(0), j);
        assertEquals(list.get(1), s);
    }

    @Test
    public void getNextHole() {
        game.addStroke(s, 3);
        game.addStroke(j, 2);

        game.getNextHole();
        List<Player> list = game.getListOfPlayers();

        assertEquals(list.get(0), j);
        assertEquals(list.get(1), s);
        assertEquals(game.getCurrentHole(), hole2);
    }

    @Test
    public void getPreviousHole() {
        game.addStroke(s, 3);
        game.addStroke(j, 2);
        game.getNextHole();

        game.addStroke(s, 3);
        game.addStroke(j, 2);
        game.getNextHole();

        game.addStroke(s, 3);
        game.addStroke(j, 3);
        game.getPreviousHole();

        List<Player> list = game.getListOfPlayers();

        assertEquals(list.get(0), j);
        assertEquals(list.get(1), s);
        assertEquals(hole2, game.getCurrentHole());
        assertEquals(2, list.get(0).getStrokesOnHole(game.getCurrentHole()));
        assertEquals(3, list.get(1).getStrokesOnHole(game.getCurrentHole()));
        assertEquals(3, list.get(0).getStrokesOnHole(hole3));
    }

    @Test
    public void getCurrentHole() {
        assertEquals(hole1, game.getCurrentHole());

        game.getPreviousHole();

        assertEquals(hole4, game.getCurrentHole());
    }

    @Test
    public void getParSoFar() {
        game.addStroke(s, 3);
        game.addStroke(j, 2);
        game.getNextHole();

        assertEquals(3, game.getParSoFar());

        game.addStroke(s, 3);
        game.addStroke(j, 2);
        game.getNextHole();

        assertEquals(6, game.getParSoFar());
    }

    @Test
    public void removeStrokes() {
        List<Player> list = game.getListOfPlayers();

        game.addStroke(s, 4);
        game.addStroke(j, 3);
        game.removeStroke(s, 2);
        game.removeStroke(j, 4);

        assertEquals(2, list.get(0).getStrokesOnHole(game.getCurrentHole()));
        assertEquals(0, list.get(1).getStrokesOnHole(game.getCurrentHole()));

    }

    @Test
    public void getCourse() {
        assertEquals(dgc, game.getCourse());
    }

    @Test
    public void getHolesPlayed() {
        game.addStroke(s, 3);
        game.addStroke(j, 2);
        game.getNextHole();

        game.addStroke(s, 3);
        game.addStroke(j, 2);
        game.getNextHole();

        game.addStroke(s, 3);
        game.addStroke(j, 3);
        game.getPreviousHole();

        List<Hole> holes = game.getHolesPlayed();

        assertEquals(hole1, holes.get(0));
        assertEquals(hole2, holes.get(1));
        assertEquals(hole3, holes.get(2));
    }

    @Test
    public void getRoundStrokeScore() {
        game.addStroke(s, 3);
        game.addStroke(j, 2);
        game.getNextHole();

        game.addStroke(s, 3);
        game.addStroke(j, 2);
        game.getNextHole();

        game.addStroke(s, 3);
        game.addStroke(j, 3);
        game.getPreviousHole();

        assertEquals(-1, game.getRoundStrokeScore(s));
        assertEquals(-3, game.getRoundStrokeScore(j));
    }
}