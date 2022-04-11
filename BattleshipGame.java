import java.util.Scanner;

/**
 * Main Class
 */
public class BattleshipGame {

	/**
	 * The main method that will help a player play the game.
	 */
	public static void main(String args[]) {
		BattleshipGame game = new BattleshipGame();
		while (game.runGame()) {
			continue;
		}
	}

	/**
	 * This method will help us with the following: 1) Set up the game 2) Accept
	 * user input as shots fired 3) Display the game board as needed with the
	 * appropriate specifications 4) Prints the final scores when the game concludes
	 * 5) After the game concludes, ask the user if they wants to play again
	 * 
	 * @return TRUE if the user enters a "y". Otherwise, @return FALSE.
	 */
	public boolean runGame() {
		Ocean oceanPlay = new Ocean();
		oceanPlay.placeAllShipsRandomly();
		Scanner scnr = new Scanner(System.in);
		oceanPlay.print();
		while (!oceanPlay.isGameOver()) {
			System.out.println(
					"Please enter two integers, one per line (use Enter for next line) between 0 and 9 (0 and 9 included). \n"
							+ "The first integer will be for horizontal (x-axis) and the second will be for vertical axis (y-axis).");
			int x = checkIfInt(scnr);
			int y = checkIfInt(scnr);
			if (oceanPlay.shootAt(x, y)) {
				System.out.println("hit");
				Ship[][] ships = oceanPlay.getShipArray();
				if (ships[x][y].isSunk()) {
					System.out.println("You just sunk a " + ships[x][y].getShipType() + ".");
				}
			} else {
				System.out.println("miss");
			}
			oceanPlay.print();
		}
		System.out.println("The game has concluded. You took a total of " + oceanPlay.getShotsFired()
				+ ". Your final score is " + oceanPlay.getShotsFired() + ".");
		System.out.println("Do you want to play again?");
		String answer = scnr.nextLine();
		return answer.toLowerCase().startsWith("y");
	}

	/**
	 * To check and catch invalid inputs from the user. It should be an integer.
	 * 
	 * @param scnr. User input (i).
	 * @return The integer (i) that user inputs.
	 */
	int checkIfInt(Scanner scnr) {
		int i;
		while (true) {
			try {
				i = scnr.nextInt();
				if (!checkIfValidInt(i)) {
					System.out.println("Both integers should be between 0 and 9 (0 and 9 included).");
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println("Please enter an integer!");
				scnr.nextLine();
			}
		}
		return i;
	}

	/**
	 * To check and catch invalid inputs from the user. It should be an integer
	 * between 0 and 9 as per the game board array.
	 * 
	 * @param x The integer (i) that user inputs.
	 * @return TRUE, if i is between 0 and 9, inclusive. @return FALSE, if is is not
	 *         between 0 and 9, inclusive.
	 */
	boolean checkIfValidInt(int i) {
		if (i >= 0 && i <= 9) {
			return true;
		} else {
			return false;
		}
	}

}
