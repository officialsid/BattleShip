
public class Submarine extends Ship {

	/**
	 * Implements a Ship with length 1.
	 */

	int length = 1;

	/**
	 * For Submarine ship type;
	 * 
	 * @return String "submarine".
	 */
	@Override
	String getShipType() {
		return "submarine";
	}

	/**
	 * @return The length of this ship.
	 */
	@Override
	int getLength() {
		return length;
	}
}
