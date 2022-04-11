
public class Cruiser extends Ship {

	/**
	 * Implements a Ship with length 3.
	 */

	int length = 3;

	/**
	 * For Cruiser ship type;
	 * 
	 * @return String "cruiser".
	 */
	@Override
	String getShipType() {
		return "cruiser";
	}

	/**
	 * @return The length of this ship.
	 */
	@Override
	int getLength() {
		return length;
	}

}
