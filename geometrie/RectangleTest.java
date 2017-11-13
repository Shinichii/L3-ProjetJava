package geometrie;

import static org.junit.Assert.*;

import org.junit.Test;

public class RectangleTest {

	Rectangle r, r2, r3;
	
	private void initialiserRectangles()
	{
		 r = new Rectangle(100, 100, 100, 100);
		 r2 = new Rectangle(200, 200, 200, 200);
		 r3 = new Rectangle(100, 100, 100, 100);
	}
	@Test
	public void testHashCode() {
		initialiserRectangles();
		assertNotEquals(r.hashCode(), r2.hashCode());
	}

	@Test
	public void testEqualsObject() {
		initialiserRectangles();
		assertFalse(r.equals(r2));
		assertTrue(r.equals(r3));
	}

	@Test
	public void testCalculAire() {
		initialiserRectangles();
		assertEquals(10000, r.calculAire(), 0.0001);
		assertEquals(40000, r2.calculAire(), 0.0001);
	}

	@Test
	public void testCalculPerimetre() {
		initialiserRectangles();
		assertEquals(400, r.calculPerimetre(), 0.0001);
		assertEquals(800, r2.calculPerimetre(), 0.0001);
	}

	@Test
	public void testEffectuerHomothetie() {
		initialiserRectangles();
		assertTrue(r.equals(r3));
		r.effectuerHomothetie(2);
		assertEquals(0, r.getWidth() + r.getHeight() - r2.getWidth() - r2.getHeight());
		r2.effectuerHomothetie(0.5);
		assertEquals(0, r3.getWidth() + r3.getHeight() - r2.getWidth() - r2.getHeight());
	}

}
