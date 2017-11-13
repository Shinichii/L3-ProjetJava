package geometrie;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;

import org.junit.Test;

public class EllipseTest {

	Ellipse e1, e2, e3;
	
	private void initialiserEllipses()
	{
		e1 = new Ellipse(10, 20, 10, 20);
		e2 = new Ellipse(10, 20, 10, 20);
		e3 = new Ellipse(10, 20, 30, 30);
	}
	@Test
	public void testCompareTo() {
		initialiserEllipses();
		assertEquals(0, e1.compareTo(e2));
		assertNotEquals(0, e1.compareTo(e3));
	}

	@Test
	public void testReplacerForme() {
		initialiserEllipses();
		e1.replacerForme(100, 100);
		
		assertEquals(100, (int)e1.getCustomX());
		assertEquals(100, (int)e1.getCustomY());
	}

	@Test
	public void testCalculAire() {
		initialiserEllipses();
		assertEquals(628.31853, e1.calculAire(), 0.0001);
		assertEquals(e1.calculAire(), e2.calculAire(), 0.0001);
	}

	@Test
	public void testCalculPerimetre() {
		initialiserEllipses();
		assertEquals(99.34,e1.calculPerimetre(), 0.1);
	}

	@Test
	public void testEffectuerHomothetie() {
		initialiserEllipses();
		e1.effectuerHomothetie(10);
		assertEquals(100, e1.el.getWidth(), 0.0001);
		assertEquals(200, e1.el.getHeight(), 0.0001);
		e1.effectuerHomothetie(0.1);
		assertEquals(e2.el.getWidth(), e1.el.getWidth(), 0.0001);
		assertEquals(e2.el.getHeight(), e1.el.getHeight(), 0.0001);
	}

	@Test
	public void testEffectuerTranslationDoubleDouble() {
		initialiserEllipses();
		e1.effectuerTranslation(10, 10);
		assertEquals(20,e1.getCustomX(), 0.0001);
		assertEquals(30,e1.getCustomY(), 0.0001);
		e2.effectuerTranslation(-10,-10);
		assertEquals(0, e2.getCustomX(), 0.0001);
		assertEquals(10, e2.getCustomY(), 0.0001);
	}

	@Test
	public void testEffectuerTranslationPoint2D() {
		initialiserEllipses();
		e1.effectuerTranslation(new Point2D.Double(10, 10));
		assertEquals(20,e1.getCustomX(), 0.0001);
		assertEquals(30,e1.getCustomY(), 0.0001);
		e2.effectuerTranslation(-10,-10);
		assertEquals(0, e2.getCustomX(), 0.0001);
		assertEquals(10, e2.getCustomY(), 0.0001);
	}

	@Test
	public void testEffectuerSymetrieAxialeHorizontale() {
		initialiserEllipses();
		e1.effectuerSymetrieAxialeHorizontale(400);
		assertEquals(0, e1.getCustomX() + e2.getCustomX() - 800, 0.0001);
	}

	@Test
	public void testEffectuerSymetrieAxialeVerticale() {
		initialiserEllipses();
		e1.effectuerSymetrieAxialeVerticale(400);
		assertEquals(0, e1.getCustomY() + e2.getCustomY() - 800, 0.0001);
	}

}
