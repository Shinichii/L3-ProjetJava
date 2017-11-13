package geometrie;

import static org.junit.Assert.*;

import java.io.InvalidClassException;

import org.junit.Test;

public class DessinTest {

	static Dessin d;
	static Image im;
	static public void initialiser()
	{
		d = new Dessin();
		im = new Image();
		d.ajouterImage(im);
	}
	@Test
	public void testCalculAire() {
		initialiser();
		try {
			d.images.first().ajouterForme(new Carre(0, 0, 10));
		} catch (InvalidClassException | AireImageSuperieureException e) {
			fail("Exception reperee");
		}
		assertEquals(d.calculAire(), 100, 0.0001);
	}

	@Test
	public void testCalculPerimetre() {
		initialiser();
		try {
			d.images.first().ajouterForme(new Carre(0, 0, 10));
		} catch (InvalidClassException | AireImageSuperieureException e) {
			fail("Exception reperee");
		}
		assertEquals(d.calculPerimetre(), 40, 0.0001);
	}

	@Test
	public void testFetchForme() {
		initialiser();
		try {
			d.images.first().ajouterForme(new Cercle(0, 0, 0));
		} catch (InvalidClassException | AireImageSuperieureException e) {
			// TODO Auto-generated catch block
			fail("Exception reperee");
		}
		assertEquals(d.images.first().getName(), d.fetchForme(d.images.first().getName()).getName());
		assertEquals("Cercle0", d.fetchForme("Cercle0").getName());
		assertEquals(null, d.fetchForme("cetesttestobligederater"));
	}

	@Test
	public void testAjouterImage() {
		initialiser();
		assertTrue(d.images.size() == 1);
	}

	@Test
	public void testClone() {
		initialiser();
		Dessin d2 = d.clone();
		
		assertFalse(d == d2);
	}

	@Test
	public void testRetirerImage() {
		initialiser();
		d.retirerImage(d.images.first());
		assertEquals(d.images.size(), 0);
	}

}
