/**
 * This is an abstract class. We will never directly instantiate a Ship, only
 * subclasses of the Ship.
 */

public abstract class Ship {

	/** The row (0 to 9) which contains the front ("bow") of the ship. */
	int bowRow;

	/** The column (0 to 9) which contains the front ("bow") of the ship. */
	int bowColumn;

	/**
	 * Each square on the game board is of length 1. Each ship would occupy an
	 * appropriate length as per ship type. A location/tile with no ship has a
	 * length of 1.
	 */
	int length;

	/**
	 * TRUE if the ship occupies a single row. FALSE if it doesn't occupy a single
	 * row.
	 */
	boolean horizontal;

	/**
	 * An array of booleans telling whether that part of the ship has been hit. This
	 * will be according to ship type: 1) Battleships use all four locations. 2)
	 * Cruisers use the first three locations. 3) Destroyers use 2. 4) Submarines
	 * use 1. 5) Empty tiles either one or none.
	 */
	boolean[] hit = { false, false, false, false };

	/**
	 * @return The length of this particular ship type.
	 */
	abstract int getLength();

	/**
	 * @return The given frontRow.
	 */
	int getBowRow() {
		return bowRow;
	}

	/**
	 * @return The given bowColumn.
	 */
	int getBowColumn() {
		return bowColumn;
	}

	/**
	 * @return The given value of horizontal.
	 */
	boolean isHorizontal() {
		return horizontal;
	}

	/**
	 * Set the value of frontRow.
	 * 
	 * @param An integer in the range of 0 to 9.
	 */
	void setBowRow(int row) {
		this.bowRow = row;
	}

	/**
	 * Set the value of frontColumn.
	 * 
	 * @param column An integer in the range of 0 to 9.
	 */
	void setBowColumn(int column) {
		this.bowColumn = column;
	}

	/**
	 * Set the value of the instance variable horizontal.
	 * 
	 * @param column
	 */
	void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	/**
	 * @return The type of ship.
	 */
	abstract String getShipType();

	/**
	 * Check if it is valid to put a ship of this length with its front in this
	 * location. We have a restriction given that a ship cannot be placed next to
	 * another ship. We have to check this for all orientations as per the appendix
	 * diagrams (given). The ship must not be placed next to another (in all
	 * orientations - horizontal, vertical, and diagonal). A ship must also be bound
	 * by the 10x10 array.
	 * 
	 * @param row        An integer in the range of 0 to 9.
	 * @param column     An integer in the range of 0 to 9.
	 * @param horizontal A boolean value.
	 * @param ocean      An Ocean object.
	 * @return TRUE if it is VALID. FALSE, otherwise.
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		if (horizontal) {
			if (column + getLength() > 10) {
				return false;
			}
			for (int column1 = column - 1; column1 <= column + getLength(); column1++) {
				if (ocean.isOccupied(row - 1, column1) || ocean.isOccupied(row, column1)
						|| ocean.isOccupied(row + 1, column1)) {
					return false;
				}
			}
			return true;
		} else {
			if (row + getLength() > 10) {
				return false;
			}
			for (int row1 = row - 1; row1 <= row + getLength(); row1++) {
				if (ocean.isOccupied(row1, column - 1) || ocean.isOccupied(row1, column)
						|| ocean.isOccupied(row1, column + 1)) {
					return false;
				}
			}
			return true;
		}
	}

	/**
	 * "Puts" the ship in the ocean. This involves giving values to the bowRow,
	 * bowColumn, and horizontal instance variables in the ship, and it also
	 * involves putting a reference to the ship in each of 1 or more locations (up
	 * to 4) in the ships array in the Ocean object.
	 * 
	 * @param row        An integer in the range of 0 to 9.
	 * @param column     An integer in the range of 0 to 9.
	 * @param horizontal A boolean value.
	 * @param ocean      An Ocean object.
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		setBowRow(row);
		setBowColumn(column);
		setHorizontal(horizontal);
		Ship[][] ships = ocean.getShipArray();
		if (horizontal) {
			for (int i = column; i < column + getLength(); i++) {
				ships[row][i] = this;
			}
		} else {
			for (int i = row; i < row + getLength(); i++) {
				ships[i][column] = this;
			}
		}
	}

	/**
	 * For checking if a ship occupies the given row and column, and the ship hasn't
	 * already been sunk, mark that part of the ship as "x" and return TRUE,
	 * otherwise return FALSE.
	 * 
	 * @param row    An integer in the range of 0 to 9.
	 * @param column An integer in the range of 0 to 9.
	 * @return TRUE if a part of the ship occupies the given row and column and the
	 *         ship hasn't already been sunk, false otherwise.
	 */
	boolean shootAt(int row, int column) {
		if (!this.isSunk()) {
			if (this.horizontal) {
				hit[column - this.bowColumn] = true;
				return true;
			} else {
				hit[row - this.bowRow] = true;
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * 
	 * Method to check if the ship in has been sunk or not.
	 * 
	 * @return TRUE, if the entire ship has been sunk; FALSE otherwise.
	 */
	boolean isSunk() {
		for (int i = 0; i < getLength(); i++) {
			if (this.hit[i] == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Use 'S' to indicate a location that you have fired upon and hit a (real) ship
	 * 'x' to indicate a location containing a sunken ship.
	 * 
	 */
	public String toString() {
		if (this.isSunk()) {
			return "x";
		} else {
			return "S";
		}
	}

}
