package com.example.mylab;

public class Dice {
	private String diceType;
	private int numSides;
	private int sideUp;
	
	public Dice(){
		diceType = "d6";
		numSides = 6;
		sideUp = (int)(Math.random()*numSides + 1);
	}
	
	public Dice(int numSides){
		diceType = "d" + numSides;
		this.numSides = numSides;
		sideUp = (int)(Math.random()*numSides + 1);
	}
	
	public Dice(int numSides, String diceType) {
		this.numSides = numSides;
		this.diceType = diceType;
	}

	public int roll() {
		sideUp = (int)(Math.random()*this.numSides + 1);
		return sideUp;
	}
	
	public String getDiceType() {
		return diceType;
	}

	public void setDiceType(String diceType) {
		this.diceType = diceType;
	}

	public int getNumSides() {
		return numSides;
	}

	public void setNumSides(int numSides) {
		this.numSides = numSides;
	}

	public int getSideUp() {
		return sideUp;
	}

	public void setSideUp(int sideUp) {
		this.sideUp = sideUp;
	}
	
	public void printDice() {
	System.out.println("The dice has " + this.numSides + " sides. The upside number is " + this.sideUp + ".");
    }	
  }
