package geometrie;

import java.awt.Color;

public class Cercle extends Ellipse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int nombreditems= 0;
	public Cercle(Cercle c)
	{
		this(c.getX(), c.getY(), c.getHeight(), c.getRemplissage(), c.getContour());
	}
	public Cercle(double x, double y, double hauteur) {
		super(x, y, hauteur, hauteur);
		this.setName("Cercle" + nombreditems);
		nombreditems++;
	}
	public Cercle(double x, double y, double hauteur, Color remplissage, Color contour) {
		super(x, y, hauteur, hauteur, remplissage, contour);
		this.setName("Cercle" + nombreditems);
		nombreditems++;
	}
	public void finalize()
	{
		nombreditems--;
	}
	@Override
	public double calculPerimetre() {
		return 2 * Math.PI * el.height;
	}
	@Override
	public String toString() {
		return "Cercle [name=" + name + ", customX=" + customX + ", customY=" + customY + "]";
	}
	
}
