package geometrie;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.geom.Point2D;

public abstract class Forme extends Frame implements Comparable<Object>, Cloneable{

	private static final long serialVersionUID = 1L;
	protected String name;
	double customX, customY;
	
	protected Color remplissage;
	protected Color contour;

	public double getCustomX() {
		return customX;
	}
	public void setCustomX(double customX) {
		this.customX = customX;
	}
	public double getCustomY() {
		return customY;
	}
	public void setCustomY(double customY) {
		this.customY = customY;
	}
	/**
	 * Fonction qui calcule la distance a l'origine de la forme par rapport au coin supérieur gauche
	 * qui représente le point (0,0)
	 * @return La distance a l'origine de la forme
	 */
	public double distanceOrigine()
	{
		return (double)(Math.sqrt(customX * customX + customY * customY));
		
	}
	public String getName() {
		return name;
	}
	public void setName(String nom) {
		this.name = nom;
	}
	@Override
	public int compareTo(Object o)
	{
		if(Forme.class.isAssignableFrom(o.getClass()))
		{
			Forme f = (Forme) o;
			return (int) ((this.getCustomX() + this.getCustomY())-(f.getCustomX() + f.getCustomY()) + this.calculAire() - f.calculAire());
		}
		else
		{
			return -1;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(angle);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(customX	);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(customY);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Forme other = (Forme) obj;
		if (Double.doubleToLongBits(angle) != Double.doubleToLongBits(other.angle))
			return false;
		if (Double.doubleToLongBits(customX) != Double.doubleToLongBits(other.customX))
			return false;
		if (Double.doubleToLongBits(customY) != Double.doubleToLongBits(other.customY))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	protected double angle = 0; //En radians
	/**
	 * Replace la forme aux coordonnees x,y
	 * @param x les coordonnees sur l'axe x
	 * @param y les coordonnees sur l'axe y
	 */
	public abstract void replacerForme(double x, double y);
	public abstract double calculAire();
	public abstract double calculPerimetre();
	/**
	 * Redimensionne la forme par rapport a un facteur donné
	 * @param k le coefficient de transformation
	 */
	public abstract void effectuerHomothetie(double k);
	public abstract void effectuerTranslation(double x, double y);
	public abstract void effectuerTranslation(Point2D z);
	/**
	 * Replace la forme de maniere symetrique par rapport au centre du dessin.
	 * 
	 * @param x le point au milieu de l'axe des abcisses
	 * @param y Le point au milieu de l'axe des ordonnées
	 */
	public void effectuerSymetrieCentrale(double x, double y)
	{
		effectuerSymetrieAxialeHorizontale(x);
		effectuerSymetrieAxialeVerticale(y);
	}
	public Color getRemplissage() {
		return remplissage;
	}
	public void setRemplissage(Color remplissage) {
		this.remplissage = remplissage;
	}
	public Color getContour() {
		return contour;
	}
	public void setContour(Color contour) {
		this.contour = contour;
	}
	/**
	 * Effectue une symetrie en fonction d'un axe d'équation y = x
	 * @param x : le milieu de l'axe horizontal
	 */
	public abstract void effectuerSymetrieAxialeHorizontale(double x);
	/**
	 * Effectue une symmetrie en fonction d'un axe d'equation x = y
	 * @param y le milieu de l'axe vertical
	 */
	public abstract void effectuerSymetrieAxialeVerticale(double y);
	public abstract void paintComponent(Graphics g);
	/**
	 * Effectue une rotation de la forme
	 * Ceci met a jour le champ angle de la forme qui sera utile lors du dessin pour le representer
	 * @param angle qui exprime de combien de degres s'effectuera la rotation
	 */
	public void effectuerRotation(double angle)
	{
		this.angle = (this.angle + angle) % 360;
	}
	@Override
	public String toString() {
		return "Forme [nom=" + name + ", customX=" + customX + ", customY=" + customY + ", remplissage=" + remplissage
				+ ", contour=" + contour + "]";
	}
}
