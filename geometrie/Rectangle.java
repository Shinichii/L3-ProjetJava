package geometrie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Forme {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Rectangle2D.Double rect;
	public static int nombreditems = 0;
	
	private void updateCoordonnees()
	{
		this.customX = rect.getX();
		this.customY = rect.getY();
	}
	public void finalize()
	{
		nombreditems--;
	}
	public Rectangle(Rectangle r)
	{
		this(r.getX(), r.getY(), r.getWidth(), r.getHeight(), r.getContour(), r.getRemplissage());
	}
	public Rectangle(double x, double y, double longueur, double largeur )
	{
		this(x, y, longueur, largeur, Color.WHITE, Color.BLACK);
		
	}
	public Rectangle(double x, double y, double longueur, double largeur,
			Color remplissage, Color contour)
	{
		customX = x;
		customY = y;
		rect = new Rectangle2D.Double(x, y, longueur, largeur); 
		if(this.getClass() == Rectangle.class)
		{
			this.name = ("Rectangle"+nombreditems);
			nombreditems++;
		}
		this.remplissage = remplissage;
		this.contour = contour;
	}
	@Override
	public double calculAire() {
		return rect.getWidth() * rect.getHeight();
	}

	@Override
	public double calculPerimetre() {
		return 2 * (rect.getWidth() + rect.getHeight());
	}
	@Override
	public void effectuerHomothetie(double k) {
		rect = new Rectangle2D.Double(rect.getX(),rect.getY(),
				rect.getWidth()*k,rect.getHeight()*k);
		updateCoordonnees();
	}

	@Override
	public void effectuerTranslation(double x, double y) {
		// TODO Auto-generated method stub
		rect.x += x;
		rect.y += y;
		updateCoordonnees();
	}

	@Override
	public void effectuerTranslation(Point2D z) {
		rect.x += z.getX();
		rect.y += z.getY();
		updateCoordonnees();
	}

	public Rectangle2D getItem()
	{
		return rect;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rect == null) ? 0 : rect.hashCode());
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
		Rectangle other = (Rectangle) obj;
		if (rect == null) {
			if (other.rect != null)
				return false;
		} else if (!rect.equals(other.rect))
			return false;
		return true;
	}
	@Override
	public int compareTo(Object o) {
		return super.compareTo(o);
	}
	public void paintComponent(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;
		AffineTransform at = new AffineTransform();
		at.rotate(Math.toRadians(angle), rect.getX() + rect.getWidth()/2,
				rect.getY()+ rect.getHeight()/2);
		Shape s = at.createTransformedShape(rect);
		g2D.setColor(remplissage);
		g2D.fill(s);
		g2D.setColor(contour);
		g2D.draw(s);
	}
	@Override
	public void replacerForme(double x, double y) {
		// TODO Auto-generated method stub
		rect.x = x;
		rect.y = y;
		updateCoordonnees();
	}
	@Override
	public String toString() {
		return "Rectangle [rect=" + rect + ", name=" + name + ", customX=" + customX + ", customY=" + customY + "]";
	}
	
	@Override
	public void effectuerSymetrieAxialeHorizontale(double x) {
		this.effectuerRotation(90);
		double deltaX = x - rect.getX();
		rect = new Rectangle2D.Double(x	+deltaX, rect.getY(),rect.getWidth(), rect.getHeight());
		updateCoordonnees();
	}
	@Override
	public void effectuerSymetrieAxialeVerticale(double y) {
		// TODO Auto-generated method stub
		this.effectuerRotation(90);
		double deltaY = y - rect.getY();
		rect = new Rectangle2D.Double(rect.getX(), y + deltaY,rect.getWidth(), rect.getHeight());
		updateCoordonnees();
	}

}
