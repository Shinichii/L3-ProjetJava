package geometrie;

import static org.junit.Assert.*;

import org.junit.Test;

public class CercleTest {

	@Test
	public void testCercle() {
		Cercle c1 = new Cercle(100, 10, 10);
		Cercle c2 = new Cercle (200, 20, 20);
		Cercle c3 = new Cercle (300, 30, 30);
		assertEquals("Cercle0", c1.getName());
		assertEquals("Cercle1", c2.getName());
		assertEquals("Cercle2", c3.getName());
	}

}
