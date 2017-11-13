package geometrie;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.InvalidClassException;

import org.junit.Test;

public class ImageTest {

	Image im;
	Ellipse e;
	Rectangle r;
	Polygone p;
	private void initialiser()
	{
		int coordonneesX[] = {10, 10, 20, 10 };
		int coordonneesY[] = {10, 20, 20, 10};
		im = new Image();
		e = new Ellipse(10, 10, 10, 10);
		r = new Rectangle(40, 40, 10, 10);
		p = new Polygone(coordonneesX, coordonneesY, 1, Color.BLACK);
		try {
			im.ajouterForme(e);
			im.ajouterForme(r);
			im.ajouterForme(p);
		} catch (InvalidClassException | AireImageSuperieureException e1) {
			fail("L'initialisation a echoue");
			e1.printStackTrace();
		}

	}
	@Test
	public void testCalculAire() {
		initialiser();
		assertEquals(e.calculAire() + r.calculAire() + p.calculAire()
		,im.calculAire(), 0.0001);
	}

	@Test
	public void testCalculPerimetre() {
		initialiser();
		assertEquals(e.calculPerimetre() + r.calculPerimetre() + p.calculPerimetre(),
				im.calculPerimetre(), 0.0001);
	}

	@Test
	public void testEffectuerHomothetie() {
		initialiser();
		double eLong = e.getWidth();
		double eLarg = e.getHeight();
		im.effectuerHomothetie(10);
		assertEquals(eLong * 10, e.getWidth(), 0.0001);
		assertEquals(eLarg * 10, e.getHeight(), 0.0001);
		
	}

	@Test
	public void testEffectuerTranslationDoubleDouble() {
		initialiser();
		double rX = r.getCustomX();
		double rY = r.getCustomY();
		im.effectuerTranslation(10, 10);
		assertEquals(rX + 10, r.getCustomX(), 0.0001);
		assertEquals(rY + 10, r.getCustomY(), 0.0001);
		im.effectuerTranslation(-20, -20);
		assertEquals(rX - 10, r.getCustomX(), 0.0001);
		assertEquals(rY - 10, r.getCustomY(), 0.0001);
	}

	@Test
	public void testAjouterForme() {
		initialiser();
		Image i = new Image();
		try {
			i.ajouterForme(new Carre(1000,1000,1000));
		} catch (InvalidClassException | AireImageSuperieureException e1) {
			fail("Erreur dans l'initialisation");
		}
		try
		{
			im.ajouterForme(i);
		}
		catch(AireImageSuperieureException | InvalidClassException e1)
		{
			System.out.println(e1.getMessage());
		}
		try {
			im.ajouterForme(new Carre(1,1,1));
		} catch (InvalidClassException | AireImageSuperieureException e) {
			fail("Erreur dans l'implementation");
		}
		assertEquals(4, im.formes.size());
	}

	@Test
	public void testRetirerForme() {
		initialiser();
		im.retirerForme(im.formes.first());
		assertEquals(2, im.formes.size());
	}

	@Test
	public void testFetchForme() {
		initialiser();
		Forme re = (Forme)im.fetchForme(im.formes.first().getName());
		assertNotEquals(null, re);
		
		Forme expectFail = im.fetchForme("0x8182828392093209");
		assertEquals(null, expectFail);
	}

}
