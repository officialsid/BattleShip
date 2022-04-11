
public class Destroyer extends Ship {

	/**
	 * Implements a Ship with length 2.
	 */

	int length = 2;

	/**
	 * For Destroyer ship type;
	 * 
	 * @return String "destroyer".
	 */
	@Override
	String getShipType() {
		return "destroyer";
	}

	/**
	 * @return The length of this ship.
	 */
	@Override
	int getLength() {
		return length;
	}
}
