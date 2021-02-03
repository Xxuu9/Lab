package com.example.mylab;

public class DiceGame {

	public static void main(String[] args) {
		
		//create different sized dice using different constructors
		Dice dice1 = new Dice(6,"6d");
		Dice dice2 = new Dice(10);
		Dice dice3 = new Dice(12,"12d");
		Dice dice4 = new Dice(20);
		
		//roll dice
		dice1.roll();
		dice1.printDice();
		dice2.roll();
		dice2.printDice();
		dice3.roll();
		dice3.printDice();
		dice4.roll();
		dice4.printDice();
		
		//set a dice to a specific side up
		dice1.setSideUp(5);
		dice2.setSideUp(10);
		dice3.setSideUp(12);
		dice4.setSideUp(20);
		System.out.println();
		
		//test if setting is correct
		dice1.printDice();
		dice2.printDice();
		dice3.printDice();
		dice4.printDice();
		System.out.println();
		
		//create 5 six-sided dice. Roll them in a loop until you get a cold-Yahtzee 
		Dice dice6 = new Dice(6,"6d");
		Dice dice7 = new Dice(6,"6d");
		Dice dice8 = new Dice(6,"6d");
		Dice dice9 = new Dice(6,"6d");
		Dice dice10 = new Dice(6,"6d");
		
		int count = 0;
		while (true) {
			count += 1;
			int number = dice6.roll();
			if(number != dice7.roll()) {
				continue;
			}
			else if(number != dice8.roll()) {
				continue;
			}
			else if(number != dice9.roll()) {
				continue;
			}
			else if(number != dice10.roll()) {
				continue;
			} 
			break;
		}
			
		count++;
		System.out.println("It takes " + count + " times to get a cold-Yahtzee.");
		
}
}