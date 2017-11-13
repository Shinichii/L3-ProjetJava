package geometrie;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.io.InvalidClassException;
import java.util.TreeSet;

public class Dessin extends Forme implements Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2896404916283729770L;
	TreeSet<Image> images;
	private static int nombreditems = 0;
	public void triPerimetre()
	{
		for(Image image: images)
		{
			image.triPerimetre();
		}
	}
	public void triAire()
	{
		for(Image image : images)
		{
			image.triAire();
		}
	}
	public void triDistOrigin()
	{
		for(Image image : images)
		{
			image.triDistOrigin();
		}
	}
	/**
	 * Cette methode itere a travers les images du dessin pour retrouver une forme à partir de son nom
	 * @param name le nom de la forme a chercher
	 * @return f une reference vers la forme ou null si la forme n'est pas presente
	 */
	public Forme fetchForme(String name)
	{
		if(name.equals(this.getName()))
		{
			return this;
		}
		for(Image image : images)
		{
			Forme f = image.fetchForme(name);
			if(f != null)
			{
				return f;
			}
		}
		return null;
	}
	public Image fetchImage(int index)
	{
		int i = 0;
		for(Image image : images)
		{
			if(index == i)
				return image;
			else
				i++;
		}
		return null;
	}
	public void finalize()
	{
		nombreditems--;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((images == null) ? 0 : images.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dessin other = (Dessin) obj;
		if (images == null) {
			if (other.images != null)
				return false;
		} else if (!images.equals(other.images))
			return false;
		return true;
	}
	public void ajouterImage(Image im)
	{
		images.add(im);
	}
	@Override
	public Dessin clone()
	{
		Dessin d = null;
		d = new Dessin();
		d.setName(this.getName());
		d.images = new TreeSet<Image>();
		for(Image im : this.images)
		{	
			Image image2 = new Image();
			for(Forme f : im.formes)
			{
				if(Carre.class.isAssignableFrom(f.getClass()))
				{
					try {
						image2.ajouterForme(new Carre((Carre)f));
					} catch (InvalidClassException | AireImageSuperieureException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(Cercle.class.isAssignableFrom(f.getClass()))
				{
					try {
						image2.ajouterForme(new Cercle((Cercle) f));
					} catch (InvalidClassException | AireImageSuperieureException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(Ellipse.class.isAssignableFrom(f.getClass()))
				{
					try {
						image2.ajouterForme(new Ellipse((Ellipse) f));
					} catch (InvalidClassException | AireImageSuperieureException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(Ligne.class.isAssignableFrom(f.getClass()))
				{
					try {
						image2.ajouterForme(new Ligne((Ligne) f));
					} catch (InvalidClassException | AireImageSuperieureException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(Polygone.class.isAssignableFrom(f.getClass()))
				{
					try {
						image2.ajouterForme(new Polygone((Polygone) f));
					} catch (InvalidClassException | AireImageSuperieureException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(Rectangle.class.isAssignableFrom(f.getClass()))
				{
					try {
						image2.ajouterForme(new Rectangle((Rectangle) f));
					} catch (InvalidClassException | AireImageSuperieureException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			d.ajouterImage(image2);
		}
		return d;
	}
	public Dessin()
	{
		images = new TreeSet<Image>();
		this.setName("Dessin" + nombreditems);
		nombreditems++;
	}
	public double calculAire() {
		double sum = 0;
		for(Image image: images)
		{
			sum += image.calculAire();
		}
		return sum;
	}

	public double calculPerimetre() {
		double sum = 0;
		for(Image image: images)
		{
			sum += image.calculPerimetre();
		}
		return sum;
	}

	public void effectuerHomothetie(double k) {
		for(Image image : images)
		{
			image.effectuerHomothetie(k);
		}
	}

	public void effectuerTranslation(double x, double y) {
		for(Image image : images)
		{
			image.effectuerTranslation(x, y);
		}

	}

	public void effectuerTranslation(Point2D z) {
		for(Image image : images)
		{
			image.effectuerTranslation(z);
		}
	}

	public void effectuerRotation(double angle) {
		for(Image image : images)
		{
			image.effectuerRotation(angle);
		}
	}

	public void effectuerSymetrieCentrale(double x, double y) {
		for(Image image : images)
		{
			image.effectuerSymetrieCentrale(x,y);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		for(Image image : images)
		{
			image.paintComponent(g);
		}
	}
	@Override
	public void replacerForme(double x, double y) {
		return;
	}
	public void retirerImage(Image im) {
		images.remove(im);
	}
	@Override
	public void effectuerSymetrieAxialeHorizontale(double hauteur) {
		for(Image image :images)
		{
			image.effectuerSymetrieAxialeHorizontale(hauteur);
		}
		
	}
	@Override
	public void effectuerSymetrieAxialeVerticale(double largeur) {
		for(Image image : images)
		{
			image.effectuerSymetrieAxialeVerticale(largeur);
		}
	}

}
