import java.util.ArrayList;
import java.util.Random;

public class Door {
	
	private static ArrayList<Door> doors = new ArrayList<Door>();	

	private boolean winner;
	private boolean opened;
	private boolean guess;
	
	public Door() {
		this.winner = false;
		this.opened = false;
		doors.add(this);
	}
	// Getters

	public static ArrayList<Door> getDoors() {return doors;}	
	
	public static String makeString() {
		String s = "";
		
		// |W| = winner, |S| = Winning guess, |G| = Loosing Guess, |O| = Open door, |_| = nothing
		for(Door d: doors) {
			if(d.winner) {
				if(d.guess) {
					s += "|S|";
				}else {
					s += "|W|";
				}
			}else if(d.guess){
				s += "|G|";		
			}else if(d.opened) {
				s += "|O|";
			}else {
				s += "|_|";
			}
			s += " ";
		}
		return s;
	}
	
	public static void clear() {doors.clear();}
	
	public static void assignWinner(int winnerID) {doors.get(winnerID).winner = true;}
	
	public static void guess(int id) {doors.get(id).guess = true;}
	
	public static void changeGuess(int id) {doors.get(id).guess = false;}
	
	public static void openDoor(int id) {doors.get(id).opened = true;}
	
	public static boolean isWinner(int id) {return doors.get(id).winner;}
	
}
