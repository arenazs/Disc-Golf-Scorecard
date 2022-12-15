package com.discgolf;

import java.math.BigDecimal;
import java.util.List;

public class Hole implements Comparable<Hole>{
    public static final int DEFAULT_PIN_POSITION = PinPosition.A;

    private final List<PinPosition> pinMatrix;
    private final int holeNumber;
    private int pinPosition;

    /**
     * Creates a new hole with a number, List of pin positions and current pin being played
     * @param holeNumber Normally a number between 1 and 18 however some courses have more
     * @param pinMatrix A list containing the info for the various pin positions on this hole
     * @param pinPosition Current pin position being played which derives the current par and length of the hole
     */
    public Hole(int holeNumber, List<PinPosition> pinMatrix, int pinPosition){
        this.holeNumber = holeNumber;
        this.pinMatrix = pinMatrix;
        setPinPosition(pinPosition);
    }

    /**
     * Creates a new hole with a number and List of pin locations, sets current pin to default 'A'
     * @param holeNumber Normally a number between 1 and 18 however some courses have more
     * @param pinMatrix A list containing the info for the various pin positions on this hole
     */
    public Hole(int holeNumber, List<PinPosition> pinMatrix){
        this(holeNumber, pinMatrix, DEFAULT_PIN_POSITION);
    }

    public List<PinPosition> getPinMatrix() {
        return pinMatrix;
    }

    public boolean checkStartingPin(List<PinPosition> pinMatrix, int pinPosition){
        return(pinPosition < pinMatrix.size());
    }

    public int getHoleNumber() {
        return holeNumber;
    }

    public int getPar() {
        return pinMatrix.get(pinPosition).getPar();
    }

    public int getLengthInFeet() {
        return pinMatrix.get(pinPosition).getLengthInFeet();
    }

    public void setPar(int newPar){
        pinMatrix.get(pinPosition).setPar(newPar);
    }

    public void setLengthInFeet(int length){
        pinMatrix.get(pinPosition).setLength(length);
    }

    public int getLengthInMeters(){
        return pinMatrix.get(pinPosition).getLengthInMeters();
    }

    public char getPinPosition() {
        return pinMatrix.get(pinPosition).getPlacementAsChar();
    }

    public void setPinPosition(int pinPosition) {
        if(checkStartingPin(pinMatrix, pinPosition)){
            this.pinPosition = pinPosition;
        }else{
            this.pinPosition = DEFAULT_PIN_POSITION;
        }
    }

    @Override
    public String toString(){
        return holeNumber + pinMatrix.get(pinPosition).toString();
    }

    @Override
    public int compareTo(Hole o) {
        //Used only if creating course and holes are added out of numerical order
        return getHoleNumber() - o.getHoleNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hole hole = (Hole) o;

        return holeNumber == hole.holeNumber;
    }

    @Override
    public int hashCode() {
        return holeNumber;
    }
}
