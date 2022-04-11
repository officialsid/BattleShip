
public class Battleship extends Ship {

	/**
	 * Implements a Ship with length 4.
	 */

	int length = 4;

	/**
	 * For Battleship ship type;
	 * 
	 * @return String "battleship".
	 */
	@Override
	String getShipType() {
		return "battleship";
	}

	/**
	 * @return The length of this ship.
	 */
	@Override
	int getLength() {
		return length;
	}
}
