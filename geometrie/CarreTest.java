package geometrie;

import static org.junit.Assert.*;

import org.junit.Test;

public class CarreTest {

	@Test
	public void testCompareTo() {
		Carre c1 = new Carre(10, 10, 200);
		Carre c2 = new Carre(10, 10, 200);
		Carre c3 = new Carre(10, 10, 300);
		assertEquals(0, c1.compareTo(c2));
		assertNotEquals(0, c1.compareTo(c3));
	}

}
