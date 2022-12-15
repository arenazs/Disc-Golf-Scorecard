package com.discgolf;

import java.math.BigDecimal;

public class PinPosition {
    public static final int DEFAULT_PAR = 3;
    public static final int A = 0;
    public static final int B = 1;
    public static final int C = 2;
    public static final int D = 3;
    private static final char [] PIN_LETTERS = {'A', 'B', 'C', 'D'};

    private int length;
    private int placement;
    private int par;

    /**
     * Sets up a placement for a pin
     * @param pinPlacement There are only 4 pin placements 0 = A, 1 = B, 2 = C, 3 = D
     * @param pinPar Set par for the placement, 3 - 5
     * @param pinLength Length of current pin placement in feet
     */
    public PinPosition(int pinPlacement, int pinPar, int pinLength){
        this.placement = pinPlacement;
        setPar(pinPar);
        this.length = pinLength;
    }

    /**
     * Sets a placement for a pin with a default par of 3
     * @param pinPlacement There are only 4 pin placements 0 = A, 1 = B, 2 = C, 3 = D.
     * @param pinLength Length of current pin placement in feet
     */
    public PinPosition(int pinPlacement, int pinLength){
        this(pinPlacement, DEFAULT_PAR, pinLength);
    }

    /**
     * Sets a single 'A' placement for a pin with a default par of 3
     * @param pinLength Length of current pin placement in feet
     */
    public PinPosition(int pinLength){
        this(A, DEFAULT_PAR, pinLength);
    }

    /**
     * Returns a letter value of current placement for user readability
     * @return
     */
    public char getPlacementAsChar(){
        return PIN_LETTERS[placement];
    }

    public void setPar(int pinPar){
        if(pinPar > 5){
            this.par =  5;
        }else{
            //Determine the higher number with 3 being the minimum amd set to par
            this.par = Math.max(pinPar, DEFAULT_PAR);
        }
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLengthInFeet() {
        return length;
    }

    public int getLengthInMeters(){
        return (int) (getLengthInFeet() * .3048);
    }

    public int getPar() {
        return par;
    }

    public int getPlacement() {
        return placement;
    }

    @Override
    public String toString(){
        return getPlacementAsChar() + " - " + getLengthInFeet() + "ft Par " + getPar();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PinPosition that = (PinPosition) o;

        return placement == that.placement;
    }

    @Override
    public int hashCode() {
        return placement;
    }
}
