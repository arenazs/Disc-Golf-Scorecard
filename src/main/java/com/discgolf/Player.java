package com.discgolf;

import java.util.HashMap;
import java.util.Map;

public class Player implements Comparable<Player> {
    private final String name;
    private int strokesOnHole;
    private final Map<Hole, Integer> storedStrokesPerHole = new HashMap<>();

    /**
     * Constructs a player with a name. Future plans are for a user to have a unique ID to call back
     * prior gameplay and generate stats
     * @param name Name of the player
     */
    public Player(String name){
        this.name = name;
    }

    /**
     * Returns the name of the player
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the strokes the player has thrown so far.  Using this we can make a user-friendly score
     * based on holes thrown so far
     * @return int
     */
    public int getTotalStrokes() {
        int totalStrokes = 0;

        for(Map.Entry<Hole,Integer> currentEntry : storedStrokesPerHole.entrySet()){
            totalStrokes += currentEntry.getValue();
        }

        return totalStrokes;
    }

    public void setCurrentHole(Hole currentHole) {
        storedStrokesPerHole.putIfAbsent(currentHole, 0);

        strokesOnHole = storedStrokesPerHole.get(currentHole);
    }

    /**
     * Returns strokes thrown on whichever hole is passed in, if hole doesn't exist default to par
     * This covers the running score based on deficit to par in case the player moves the game forward and then back again
     * @param holeWeWant Hole we want strokes for
     * @return int
     */
    public int getStrokesOnHole(Hole holeWeWant) {
        storedStrokesPerHole.putIfAbsent(holeWeWant, holeWeWant.getPar());

        return storedStrokesPerHole.get(holeWeWant);
    }

    /**
     * Increases stroke by 1 for passed hole and updates map with hole and strokes played
     * @param currentHole Hole we want to add strokes on
     */
    public void addStroke(Hole currentHole){
        strokesOnHole++;
        storedStrokesPerHole.put(currentHole, strokesOnHole);
    }

    /**
     * Overloaded method to add a specific number of strokes on a passed hole and
     * updates map with hole and strokes played
     * @param currentHole Hole we want to add strokes on
     * @param strokesToAdd Number of strokes to add to hole
     */
    public void addStroke(Hole currentHole, int strokesToAdd){
        for(int i = 0; i < strokesToAdd; i++){
            addStroke(currentHole);
        }
    }

    /**
     * Subtracts single stroke from player on hole passed in and updates map with hole and strokes played
     * @param currentHole Hole to remove stroke from
     */
    public void removeStroke(Hole currentHole){
        if(strokesOnHole > 0){
            strokesOnHole--;
            storedStrokesPerHole.put(currentHole, strokesOnHole);
        }
    }

    /**
     * Overloaded method to subtract a set amount of strokes from a hole passed in and
     * updates map with hole and strokes played
     * @param currentHole Hole from which we want to remove strokes
     * @param strokesToRemove Number of strokes to remove
     */
    public void removeStroke(Hole currentHole, int strokesToRemove){
        for(int i = 0; i < strokesToRemove; i++){
            removeStroke(currentHole);
        }
    }

    @Override
    public int compareTo(Player o) {
        //Used to reset order per hole based on prior hole performance
        return strokesOnHole - o.strokesOnHole;
    }

    @Override
    public String toString() {
        return name + ", strokesOnHole: " + strokesOnHole;
    }
}

