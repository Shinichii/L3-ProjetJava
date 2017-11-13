package geometrie;

import static org.junit.Assert.*;

import java.io.InvalidClassException;

import org.junit.Test;

public class TestImage {

	@Test
	public void testCalculAire() {
		Image im = new Image();
		try {
			im.ajouterForme(new Rectangle(100, 100, 100, 100));
		} catch (InvalidClassException e) {
			fail("Probleme avec la classe Rectangle");
			e.printStackTrace();
		} catch (AireImageSuperieureException e) {
			fail("Probleme avec l'image");
			e.printStackTrace();
		}
		assertEquals(im.calculAire(), 10000, 0);
		
	}

	@Test
	public void testCalculPerimetre() {
		fail("Not yet implemented");
	}

	@Test
	public void testEffectuerHomothetie() {
		fail("Not yet implemented");
	}

	@Test
	public void testEffectuerTranslationDoubleDouble() {
		fail("Not yet implemented");
	}

	@Test
	public void testEffectuerTranslationPoint2D() {
		fail("Not yet implemented");
	}

	@Test
	public void testEffectuerRotation() {
		fail("Not yet implemented");
	}

	@Test
	public void testEffectuerSymetrieCentrale() {
		fail("Not yet implemented");
	}

	@Test
	public void testEffectuerSymetrieAxiale() {
		fail("Not yet implemented");
	}

	@Test
	public void testTriPerimetre() {
		fail("Not yet implemented");
	}

	@Test
	public void testTriAire() {
		fail("Not yet implemented");
	}

	@Test
	public void testAjouterForme() {
		Image im = new Image();
		try
		{
			im.ajouterForme(new Rectangle(100, 100, 100, 100));
		}catch(Exception e)
		{
			fail("Exception reperee : " + e.getMessage());
		}
		try
		{
			Image im2 = new Image();
			im2.ajouterForme(new Rectangle(500,500,100,100));
			im.ajouterForme(im2);
		}
		catch(AireImageSuperieureException | InvalidClassException e)
		{
			System.out.println("Ok");
		}
		assertEquals(2, im.formes.size());
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

}
