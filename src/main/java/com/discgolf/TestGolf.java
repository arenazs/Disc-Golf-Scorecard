package com.discgolf;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestGolf {

    public static void main(String[] args) {
        DiscGolfCourse dgc = new DiscGolfCourse("Testing");

        Random rand = new Random();

        for(int i = 1; i < 6; i++){
            List<PinPosition> pinSet = new ArrayList<>();
            for(int j = 0; j < 4; j++){
                pinSet.add(new PinPosition(j, rand.nextInt(3) + 3, rand.nextInt(400 ) + 200));
            }

            dgc.addHole(new Hole(i, pinSet, rand.nextInt(4)));
        }

        dgc.displayCourse();

        System.out.println(dgc.getTotalPar());

        Game newGame = new Game(dgc, dgc.getHole(1));

        Player jim = new Player("Jim");
        Player tara = new Player("Tara");
        Player andrew = new Player("Andrew");

        newGame.addPlayer(jim);
        newGame.addPlayer(tara);
        newGame.addPlayer(andrew);

        System.out.println(newGame.getListOfPlayers());

        newGame.addStroke(jim, 3);
        newGame.addStroke(tara, 2);
        newGame.addStroke(andrew, 3);

        System.out.println(newGame.getListOfPlayers());

        newGame.getNextHole();
        System.out.println("\nNext Hole -------------------");

        System.out.println(newGame.getListOfPlayers());

        newGame.addStroke(jim, 3);
        newGame.addStroke(tara, 3);
        newGame.addStroke(andrew, 2);

        System.out.println(newGame.getListOfPlayers());

        newGame.getNextHole();
        System.out.println("\nNext Hole -------------------");

        System.out.println(newGame.getListOfPlayers());

        newGame.addStroke(jim, 3);
        newGame.addStroke(tara, 3);
        newGame.addStroke(andrew, 3);

        System.out.println(newGame.getListOfPlayers());

        System.out.println("\nNext Hole -------------------");
        System.out.println(newGame.getListOfPlayers());
    }
}
