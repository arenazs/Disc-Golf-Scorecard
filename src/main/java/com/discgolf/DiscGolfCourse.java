package com.discgolf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiscGolfCourse{
    private final String name;
    private final List<Hole> holesOnCourse = new ArrayList<>();

    /**
     * Creates a new disc golf course with only a name, must have holes added!
     * @param name Set the course's name
     */
    public DiscGolfCourse(String name){
        this.name = name;
    }

    /**
     * Creates new disc golf course with provided name and a pre-made List of Holes
     * @param name Set the course's name
     * @param listOfHoles List of holes already generated or pulled from prior play
     */
    public DiscGolfCourse(String name, List<Hole> listOfHoles){
        this(name);
        populateCourse(listOfHoles);
    }

    /**
     * Adds a hole to course and overwrites any already created
     * @param holeToAdd New Hole must follow hole parameters to be instantiated
     */
    public void addHole(Hole holeToAdd){
        int holeNumComparison = holeToAdd.getHoleNumber();

        if(holesOnCourse.contains(holeToAdd)){
            holesOnCourse.set(holesOnCourse.indexOf(holeToAdd), holeToAdd);
        }else{
            holesOnCourse.add(holeToAdd);
        }
    }

    public void populateCourse(List<Hole> listOfHoles){
        for(Hole currentHole : listOfHoles){
            addHole(currentHole);
        }
    }

    /**
     * Returns total of par for all holes on course
     * @return int
     */
    public int getTotalPar(){
        int totalPar = 0;

        for(Hole currentHole : holesOnCourse){
            totalPar += currentHole.getPar();
        }

        return totalPar;
    }

    /**
     * Returns hole based on hole number.  If hole number is beyond holes in list or below returns highest hole number or
     * lowest based on bounds entered
     * @param holeNumber Normally a number between 1 and 18 however some courses have varying number systems
     * @return Hole
     */
    public Hole getHole(int holeNumber) {
        if(holeNumber > holesOnCourse.size()){
            return holesOnCourse.get(holesOnCourse.size() -1);
        }else if(holeNumber <= 1){
            return holesOnCourse.get(0);
        }else {
            return holesOnCourse.get(holeNumber - 1);
        }
    }

    /**
     * Updates par on whichever hole is passed in to the int passed along with it
     * @param holeToSet Hole fo which to set par for
     * @param parToSet Number between 3 & 5 to set par to
     */
    public void setPar(Hole holeToSet, int parToSet){
        int index = holesOnCourse.indexOf(holeToSet);
        holesOnCourse.get(index).setPar(parToSet);
    }

    /**
     * Returns the next hole based on the index of the hole passed in.  If the hole passed is the last
     * hole on the course, returns the first hole.
     * @param currentHole Hole from which next hole will be derived
     * @return Hole
     */
    public Hole getNextHole(Hole currentHole){
        int index = holesOnCourse.indexOf(currentHole) + 1;

        if(index == holesOnCourse.size()){
            return holesOnCourse.get(0);
        }

        return holesOnCourse.get(index);
    }

    /**
     * Returns the previous hole based on the index of the hole passed.  If the hole passed is the first
     * hole on the course, then the last hole on the course is returned
     * @param currentHole The hole from which the previous hole will be derived
     * @return Hole
     */
    public Hole getPreviousHole(Hole currentHole){
        int index = holesOnCourse.indexOf(currentHole) - 1;

        if(index < 0){
            return holesOnCourse.get(holesOnCourse.size() - 1);
        }

        return holesOnCourse.get(index);
    }

    /**
     * Returns total number of holes on course
     * @return int
     */
    public int getNumOfHoles(){
        return holesOnCourse.size();
    }

    /**
     * Returns name of the course
     * @return String
     */
    public String getName() {
        return name;
    }

    public void displayCourse(){
        Collections.sort(holesOnCourse);

        for(Hole currentHole : holesOnCourse){
            System.out.println(currentHole);
        }
    }
}
