
public class EmptySea extends Ship {

	/**
	 * Implements an empty ocean tile. It may be unintuitive to have an EmptySea
	 * extend from Ship, but it will allow us to maintain an Ocean as a 2D array of
	 * Ship objects.
	 */

	int length = 1;

	/**
	 * @return Always FALSE; nothing was hit.
	 */
	@Override
	boolean shootAt(int row, int column) {
		return false;
	}

	/**
	 * @return Always FALSE; nothing was sunk.
	 */
	@Override
	boolean isSunk() {
		return false;
	}

	/**
	 * @return A single-character string '-' to indicate a location that you have
	 *         fired upon and found nothing.
	 */
	@Override
	public String toString() {
		return "-";
	}

	@Override
	int getLength() {
		return 0;
	}

	@Override
	String getShipType() {
		return null;
	}
}