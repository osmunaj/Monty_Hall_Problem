import java.util.Scanner;
import java.util.Random;

public class Driver {

	public static void main(String[] args) {		
		
		Scanner scanner = new Scanner(System.in);
		
		int trials; 
		int doors; 
		
		final boolean printTrials = false;
		
		int successes_NoSwitch = 0;
		int successes_Switch = 0;
		
		System.out.println("Enter the number of trials");
		trials = scanner.nextInt();
		System.out.println("Enter the number of doors");
		doors = scanner.nextInt();
		
		scanner.close();
		
		
		// Run the experiement where the guess does not switch
		
		if(printTrials) System.out.println("\nNO SWITCH TRIAL \n--------------------");
		
		for(int i = 0; i < trials; i++) {
			// For each trial
			
			for(int j = 0; j < doors; j++) {
				new Door();
			}
			
			// Assign a random door 'winner'
			int winner = getVals(doors);
			Door.assignWinner(winner);
			
			// The contestant guesses a random door
			int guess = getVals(doors);
			Door.guess(guess);
			
			// The Host opens a random door, not including the winner or the contestants guess
			int openDoor = getVals(doors, winner, guess);
			Door.openDoor(openDoor);
			
			// The guess stays the same
			

			
			// If the guess is a winner, add one point to No Switch Successes
			if(Door.isWinner(guess)) successes_NoSwitch++;
			
			
			if(printTrials)	System.out.println(Door.makeString());

			Door.clear();
		}
		
		// Run the experiment where the guess DOES Switch
		if(printTrials) System.out.println("\nSWITCH TRIAL \n--------------------");

		for(int i = 0; i < trials; i++) {
			// For each trial
			
			for(int j = 1; j <= doors; j++) {
				new Door();
			}
			
			// Assign a random door 'winner'
			int winner = getVals(doors);
			Door.assignWinner(winner);
			
			// The contestant guesses a random door
			int guess = getVals(doors);
			Door.guess(guess);
			
			if(printTrials)	System.out.println("I: " + Door.makeString());

			// The Host opens a random door, not including the winner or the contestants guess
			
			int openDoor = getVals(doors, winner, guess);
			Door.openDoor(openDoor);
			
			
			// The users guess switches, and excludes their current guess and the open door
			int previousGuess = guess;
			Door.changeGuess(previousGuess);
	
			guess = getVals(doors, openDoor, previousGuess);
			Door.guess(guess);
			
			if(printTrials)	System.out.println("F: " + Door.makeString());
			
			// If the guess is a winner, add one point to No Switch Successes
			if(Door.isWinner(guess))successes_Switch++;
			
			Door.clear();
		}
		
		System.out.println("\n\n");
		System.out.println("-----RESULTS-----");
		System.out.println("Number of Trials: " + trials);
		System.out.println("Number of Successes with NO SWITCH: " + successes_NoSwitch + " | " + ((float)successes_NoSwitch/(float)trials)*100 + "%");
		System.out.println("Number of Successes with SWITCH: " + successes_Switch + " | " + ((float)successes_Switch/(float)trials)*100 + "%");
		System.out.println();
		
		
	}
	
	private static int getVals(int maxVal) {
		// Returns random value from 0 to maxVal
		Random random = new Random();
		return random.nextInt(maxVal);
	}
	
	private static int getVals(int maxVal, int x) {
		// Returns random value from 0 to maxVal, exclusive of x
		int val;
		while((val = getVals(maxVal)) != x);
		return val;
	}
	
	private static int getVals(int maxVal, int x, int y) {
		// Returns random value from 0 to maxVal, exclusive of x and y
		int val = getVals(maxVal);
		while(val == x || val == y) {
			val = getVals(maxVal);
		}
		return val;
	}
	
	

}
