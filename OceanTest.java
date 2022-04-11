import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class OceanTest {

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

	@Test
	public void testOcean() {
		assertEquals(0, ocean.getShotsFired());
		assertEquals(0, ocean.getHitCount());
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				assertEquals(null, ocean.ships[i][j].getShipType());
				assertFalse(ocean.fired[i][j]);
			}
		}
	}

	@Test
	public void testIsOccupied() {
		crs.placeShipAt(1, 1, true, ocean);
		assertTrue(ocean.isOccupied(1, 2));
		assertFalse(ocean.isOccupied(2, 1));
		battleship.placeShipAt(6, 9, false, ocean);
		assertTrue(ocean.isOccupied(9, 9));
		assertFalse(ocean.isOccupied(8, 8));
	}

	@Test
	public void testShootAt() {
		battleship.placeShipAt(6, 9, false, ocean);
		assertTrue(ocean.shootAt(6, 9));
		assertTrue(ocean.shootAt(7, 9));
		assertTrue(ocean.shootAt(8, 9));
		assertTrue(ocean.shootAt(9, 9));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(5, ocean.shotsFired);
		assertEquals(4, ocean.hitCount);
		sub.placeShipAt(5, 7, true, ocean);
		assertTrue(ocean.shootAt(5, 7));
		assertFalse(ocean.shootAt(5, 7));
		assertEquals(7, ocean.shotsFired);
		assertEquals(5, ocean.hitCount);
		// Always false if shooting at EmptySea.
		assertFalse(ocean.shootAt(0, 0));
		assertFalse(ocean.shootAt(7, 3));
		assertEquals(9, ocean.shotsFired);
		assertEquals(5, ocean.hitCount);
	}

	@Test
	public void testGetShotsFired() {
		dest.placeShipAt(1, 6, false, ocean);
		ocean.shootAt(1, 6);
		ocean.shootAt(2, 6);
		assertEquals(2, ocean.getShotsFired());
		ocean.shootAt(3, 6);
		assertEquals(3, ocean.getShotsFired());
		crs.placeShipAt(3, 2, true, ocean);
		ocean.shootAt(3, 1);
		ocean.shootAt(3, 2);
		ocean.shootAt(3, 3);
		ocean.shootAt(3, 4);
		assertEquals(7, ocean.getShotsFired());
	}

	@Test
	public void getHitCount() {
		dest.placeShipAt(1, 6, false, ocean);
		ocean.shootAt(1, 6);
		ocean.shootAt(2, 6);
		ocean.shootAt(3, 6);
		assertEquals(2, ocean.getHitCount());
		crs.placeShipAt(3, 2, true, ocean);
		ocean.shootAt(3, 1);
		ocean.shootAt(3, 2);
		ocean.shootAt(3, 3);
		ocean.shootAt(3, 4);
		assertEquals(5, ocean.getHitCount());
	}

	@Test
	public void testIsGameOver() {
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
		ocean.shootAt(1, 1);
		ocean.shootAt(1, 2);
		ocean.shootAt(1, 3);
		ocean.shootAt(1, 6);
		ocean.shootAt(2, 6);
		ocean.shootAt(2, 9);
		ocean.shootAt(3, 0);
		ocean.shootAt(3, 2);
		ocean.shootAt(3, 3);
		ocean.shootAt(3, 4);
		ocean.shootAt(3, 9);
		ocean.shootAt(5, 1);
		ocean.shootAt(5, 2);
		ocean.shootAt(5, 4);
		ocean.shootAt(5, 7);
		ocean.shootAt(9, 0);
		assertFalse(ocean.isGameOver());
		ocean.shootAt(6, 9);
		ocean.shootAt(7, 9);
		ocean.shootAt(8, 9);
		ocean.shootAt(9, 9);
		assertTrue(ocean.isGameOver());
	}

	@Test
	public void testGetShipArray() {
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
		assertTrue(ocean.getShipArray()[6][9] instanceof Battleship);
		assertTrue(ocean.getShipArray()[1][1] instanceof Cruiser);
		assertFalse(ocean.getShipArray()[1][0] instanceof Cruiser);
		assertTrue(ocean.getShipArray()[2][6] instanceof Destroyer);
		assertTrue(ocean.getShipArray()[9][0] instanceof Submarine);
		assertFalse(ocean.getShipArray()[9][1] instanceof Submarine);
		assertTrue(ocean.getShipArray()[7][5] instanceof EmptySea);
	}

}
