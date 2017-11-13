package geometrie;

import static org.junit.Assert.*;

import org.junit.Test;

public class PolygoneTest {

	Polygone p;
	public void initialiser()
	{
		int xPoints[] = {10,10, 20, 20};
		int yPoints[] = {10,20, 20, 10};
		p = new Polygone(xPoints, yPoints, 1);
	}
	@Test
	public void testReplacerForme() {
		initialiser();
		int[] xPointsExpected = {0,0,10,10};
		p.replacerForme(0, 0);
		int[] xPoints = p.getXPoints();
		for(int i = 0; i < xPointsExpected.length; i++)
		{
			if(xPointsExpected[i] != xPoints[i])
			{
				fail("Not equals");
			}
		}
		
	}

	@Test
	public void testCalculAire() {
		initialiser();
		assertEquals(p.calculAire(), 100, 0.0001);
	}

	@Test
	public void testCalculPerimetre() {
		initialiser();
		assertEquals(p.calculPerimetre(), 40, 0.0001);
	}

}
