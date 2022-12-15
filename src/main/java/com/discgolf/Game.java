package com.discgolf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private Hole currentHole;
    private final DiscGolfCourse course;
    private final List<Player> listOfPlayers = new ArrayList<>();
    private final List<Hole> holesPlayed = new ArrayList<>();

    /**
     * Creates a new game with a course and starting hole
     * @param course Course on which this game will be played
     * @param startingHole Hole on which to start the course
     */
    public Game(DiscGolfCourse course, Hole startingHole){
        this.course = course;
        this.currentHole = startingHole;
    }

    /**
     * Creates a new game with a course, starting hole and pre-made list of players
     * @param course Course on which this game will be played
     * @param startingHole Hole on which to start the course
     * @param players List of Players to be added to the card, players will be added sequentially to the card
     */
    public Game(DiscGolfCourse course, Hole startingHole, List<Player> players){
        this(course, startingHole);
        for(Player thisPlayer : players){
            addPlayer(thisPlayer);
        }
    }

    /**
     * Adds players to the card for the round.  Players will be added sequentially and whomever is
     * first will have the box
     * @param playerToAdd Player that will be in this round
     */
    public void addPlayer(Player playerToAdd){
        listOfPlayers.add(playerToAdd);
        listOfPlayers.get(listOfPlayers.size()-1).setCurrentHole(currentHole);
    }

    /**
     * Reorders the card based on prior hole performance.  Follows regular golf rules
     */
    public void updatePlayerOrder(){
        Collections.sort(listOfPlayers);
    }

    /**
     * Orders the players on the card based on performance, adds the last hole played to a running list,
     * resets stroke count for each player to 0 for next hole. Checks if round has finished based on number
     * of holes played.
     */
    public void updatePlayedList(){
        updatePlayerOrder();
        if(holesPlayed.contains(currentHole)){
            holesPlayed.set(holesPlayed.indexOf(currentHole), currentHole);
        }else{
            holesPlayed.add(currentHole);
        }
    }

    /**
     * Resets player strokes to stored strokes on hole or par, whichever we have
     */
    public void updatePlayerStrokes(Hole newHole){
        for(Player currentPlayer : listOfPlayers){
            currentPlayer.setCurrentHole(newHole);
        }
    }

    /**
     * Resets current hole to next hole on course
     */
    public void getNextHole(){
        Hole nextHole = course.getNextHole(currentHole);
        updatePlayedList();
        currentHole = nextHole;
        updatePlayerStrokes(currentHole);
    }

    /**
     * Resets current hole to previous hole on course
     */
    public void getPreviousHole(){
        Hole previousHole = course.getPreviousHole(currentHole);
        updatePlayedList();
        currentHole = previousHole;
        updatePlayerStrokes(currentHole);
    }

    /**
     * Returns current hole players are on
     * @return Hole
     */
    public Hole getCurrentHole() {
        return currentHole;
    }

    /**
     * Returns running par for the course that can generate a user-friendly score
     * @return int
     */
    public int getParSoFar(){
        int totalPar = 0;

        for(Hole holeInList : holesPlayed){
            totalPar += holeInList.getPar();
        }
        return totalPar;
    }

    /**
     * Adds set amount of strokes to the player's score for the current hole
     * @param player Player to add strokes to
     * @param strokes Number of strokes to add
     */
    public void addStroke(Player player, int strokes){
        player.addStroke(currentHole, strokes);
    }

    /**
     * Adds one stroke to player score on current hole
     * @param player Player to add stroke to
     */
    public void addStroke(Player player){
        addStroke(player, 1);
    }

    /**
     * Removes set amount of strokes from player's score for current hole
     * @param player Player to remove strokes from
     * @param strokes Number of strokes to be removed
     */
    public void removeStroke(Player player, int strokes){
        player.removeStroke(currentHole, strokes);
    }

    /**
     * Removes 1 stroke from player's score for the current hole
     * @param player Player to remove stroke from
     */
    public void removeStroke(Player player){
        removeStroke(player, 1);
    }

    /**
     * Returns ordered list of players on the card
     * @return List
     */
    public List<Player> getListOfPlayers() {
        return listOfPlayers;
    }

    /**
     * Returns course being played for this game
     * @return DiscGolfCourse
     */
    public DiscGolfCourse getCourse() {
        return course;
    }

    /**
     * Returns ordered list of all holes played so far
     * @return List
     */
    public List<Hole> getHolesPlayed() {
        return holesPlayed;
    }

    /**
     * Returns the score for the game so far. Displayed in golf standards
     * @param player Player from which to get the running score
     * @return int
     */
    public int getRoundStrokeScore(Player player){
        return player.getTotalStrokes() - getParSoFar();
    }
}
