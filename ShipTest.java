
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ShipTest {

	Ocean ocean = new Ocean();
	Battleship battleship = new Battleship();
	Cruiser crs = new Cruiser();
	Cruiser crs1 = new Cruiser();
	Destroyer dest = new Destroyer();
	Destroyer dest1 = new Destroyer();
	Destroyer dest2 = new Destroyer();
	Submarine sub = new Submarine();
	Submarine sub1 = new Submarine();
	Submarine sub2 = new Submarine();
	Submarine sub3 = new Submarine();
	EmptySea emptysea = new EmptySea();

	@Before
	public void setUp() {
		battleship.placeShipAt(6, 9, false, ocean);
		crs.placeShipAt(1, 1, true, ocean);
		crs1.placeShipAt(3, 2, true, ocean);
		dest.placeShipAt(5, 1, true, ocean);
		dest1.placeShipAt(1, 6, false, ocean);
		dest2.placeShipAt(2, 9, false, ocean);
		sub.placeShipAt(3, 0, true, ocean);
		sub1.placeShipAt(9, 0, false, ocean);
		sub2.placeShipAt(5, 4, true, ocean);
		sub3.placeShipAt(5, 7, false, ocean);
	}

	@Test
	public void testGetLength() {
		assertEquals(4, battleship.getLength());
		assertEquals(3, crs.getLength());
		assertEquals(2, dest.getLength());
		assertEquals(1, sub.getLength());
	}

	@Test
	public void testGetBowRow() {
		assertEquals(6, battleship.getBowRow());
		assertEquals(1, crs.getBowRow());
		assertEquals(5, dest.getBowRow());
		assertEquals(3, sub.getBowRow());
	}

	@Test
	public void testGetBowColumn() {
		assertEquals(9, battleship.getBowColumn());
		assertEquals(1, crs.getBowColumn());
		assertEquals(1, dest.getBowColumn());
		assertEquals(0, sub.getBowColumn());
	}

	@Test
	public void testIsHorizontal() {
		assertFalse(battleship.isHorizontal());
		assertTrue(crs.isHorizontal());
		assertTrue(dest.isHorizontal());
		assertTrue(sub.isHorizontal());
	}

	@Test
	public void testGetShipType() {
		assertEquals("battleship", battleship.getShipType());
		assertEquals("cruiser", crs.getShipType());
		assertEquals("destroyer", dest.getShipType());
		assertEquals("submarine", sub.getShipType());
		assertEquals(null, emptysea.getShipType());
	}

	@Test
	public void testOkToPlaceShipAt() {
		assertTrue(battleship.okToPlaceShipAt(7, 4, true, ocean));
		assertFalse(battleship.okToPlaceShipAt(6, 5, false, ocean));
		assertFalse(battleship.okToPlaceShipAt(0, 0, false, ocean));
		assertFalse(battleship.okToPlaceShipAt(0, 9, true, ocean));
		assertTrue(crs.okToPlaceShipAt(7, 0, true, ocean));
		assertFalse(crs.okToPlaceShipAt(6, 5, true, ocean));
		assertTrue(dest.okToPlaceShipAt(8, 5, false, ocean));
		assertFalse(dest.okToPlaceShipAt(9, 9, false, ocean));
		assertTrue(sub.okToPlaceShipAt(8, 2, true, ocean));
	}

	@Test
	public void testShootAt() {
		assertTrue(battleship.shootAt(7, 9));
		assertTrue(battleship.hit[1]);
		assertTrue(battleship.shootAt(8, 9));
		assertTrue(battleship.shootAt(9, 9));
		assertTrue(battleship.shootAt(6, 9));
		assertTrue(battleship.hit[3]);
		assertFalse(battleship.shootAt(6, 9));
		assertFalse(battleship.shootAt(5, 9));
		assertTrue(dest.shootAt(5, 1));
		assertTrue(dest.shootAt(5, 2));
	}

	@Test
	public void testIsSunk() {
		battleship.shootAt(6, 9);
		battleship.shootAt(7, 9);
		battleship.shootAt(8, 9);
		battleship.shootAt(9, 9);
		assertTrue(battleship.isSunk());
		crs1.shootAt(3, 2);
		crs1.shootAt(3, 3);
		crs1.shootAt(3, 4);
		assertTrue(crs1.isSunk());
		dest2.shootAt(2, 9);
		assertFalse(dest2.isSunk());
		sub3.shootAt(5, 7);
		assertTrue(sub3.isSunk());
	}

	@Test
	public void testToString() {
		battleship.shootAt(6, 9);
		battleship.shootAt(7, 9);
		battleship.shootAt(8, 9);
		battleship.shootAt(9, 9);
		assertEquals("x", battleship.toString());
		crs1.shootAt(3, 2);
		crs1.shootAt(3, 3);
		assertEquals("S", crs1.toString());
	}

}
