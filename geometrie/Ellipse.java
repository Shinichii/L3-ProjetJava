package geometrie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Ellipse extends Forme {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int nombreditems = 0;
	protected Ellipse2D.Double el;
	
	private void updateCoordonnees()
	{
		customX = el.getX();
		customY = el.getY();
	}
	public Ellipse(double x, double y, double largeur, double hauteur) {
		this(x,y,largeur,hauteur,Color.BLACK,Color.WHITE);
	}
	public Ellipse(double x, double y, double largeur, double hauteur, Color remplissage,
			Color contour)
	{
		customX = x;
		customY = y;
		el = new Ellipse2D.Double(x, y, largeur, hauteur);
		if(this.getClass() == Ellipse.class)
		{
			this.setName("Ellipse"+ nombreditems);
			nombreditems++;
		}
		this.remplissage = remplissage;
		this.contour = contour;
	}
	public Ellipse(Ellipse e) {
		this(e.getX(), e.getY(), e.getWidth(), e.getHeight(), e.getRemplissage(), e.getContour());
	}
	public void finalize()
	{
		nombreditems--;
	}

	@Override
	public double calculAire() {
		return el.getWidth() * el.getHeight() * Math.PI;
	}

	@Override
	public double calculPerimetre() {
		return Math.PI * Math.sqrt(2 * (Math.pow(el.width, 2)+ Math.pow(el.height, 2)));
	}

	@Override
	public void effectuerHomothetie(double k) {	
		el = new Ellipse2D.Double(el.getX(),el.getY(),
				el.getWidth()*k,el.getHeight()*k);
		this.updateCoordonnees();
	}

	@Override
	public void effectuerTranslation(double x, double y) {
		el.x += x;
		el.y += y;
		this.updateCoordonnees();
	}

	@Override
	public void effectuerTranslation(Point2D z) {
		el.x += z.getX();
		el.y += z.getY();
		this.updateCoordonnees();
	}



	@Override
	public int compareTo(Object o) {
		return super.compareTo(o); 
		}
	public void paintComponent(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;
		AffineTransform at = new AffineTransform();
		at.rotate(Math.toRadians(angle), el.getX() + el.getWidth()/2,
				el.getY()+ el.getHeight()/2);
		Shape s = at.createTransformedShape(el);
		g2D.setColor(this.remplissage);
		g2D.fill(s);
		g2D.setColor(this.contour);
		g2D.draw(s);
	}
	@Override
	public void replacerForme(double x, double y) {
		el.x = x;
		el.y = y;
		this.updateCoordonnees();
	}

	@Override
	public void effectuerSymetrieAxialeHorizontale(double x) {
		this.effectuerRotation(90);
		double deltaX = x - el.getX();
		el = new Ellipse2D.Double(x + deltaX, el.getY(),el.getWidth(), el.getHeight());
		this.updateCoordonnees();
	}
	@Override
	public void effectuerSymetrieAxialeVerticale(double y) {
		this.effectuerRotation(90);
		double deltaY = y - el.getY();
		el = new Ellipse2D.Double(el.getX(), y + deltaY,el.getWidth(), el.getHeight());
		this.updateCoordonnees();
	}
}
	