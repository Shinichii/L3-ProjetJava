package geometrie;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Ligne extends Forme {

	private static final long serialVersionUID = 1L;
	private static int nombreditems;
	private double largeur;
	private Line2D line;
	public Line2D getLine()
	{
		return new Line2D.Double(line.getX1(), line.getY1(), line.getX2(), line.getY2());
	}
	public Ligne(Ligne l)
	{
		this(l.getLine().getX1(), l.getLine().getY1(), l.getLine().getX2(),
				l.getLine().getY2(), l.getLargeur(), l.getRemplissage());
	}
	public double getLargeur() {
		return largeur;
	}
	public void setLargeur(double largeur) {
		this.largeur = largeur;
	}
	public Ligne(double x, double y, double x2, double y2, double largeur)
	{
		this(x,y,x2,y2,largeur,Color.BLACK);
	}
	public Ligne(double x, double y, double x2, double y2, double largeur,Color remplissage)
	{
		this.largeur = largeur;
		line = new Line2D.Double(x, y, x2, y2);
		this.remplissage = remplissage;
		this.name = "Ligne" + nombreditems;
		nombreditems++;
	}
	public void finalize()
	{
		nombreditems--;
	}
	@Override
	public double calculAire() {
		return 0;
	}

	@Override
	public double calculPerimetre() {
		return 0;
	}

	@Override
	public void effectuerHomothetie(double k) {
		this.largeur *= k;
	}
	
	@Override
	public void effectuerTranslation(double x, double y) {
		line.setLine(line.getX1() + x, line.getY1() + y, 
				line.getX2() + x, line.getY2() + y);
	}

	@Override
	public void effectuerTranslation(Point2D z) {
		line.setLine(line.getX1() + z.getX(), line.getY1() + z.getY(), 
				line.getX2() + z.getX(), line.getY2() + z.getY());
	}

	@Override
	public int compareTo(Object o) {
		if(Ligne.class.isAssignableFrom(o.getClass()))
		{
			Ligne l2 = (Ligne)o;
			return (int) 
			(this.line.getX1() 
			+ this.line.getX2() 
			+ this.line.getY2()
			+ this.line.getY1()
			- (l2.line.getX1()
			+ l2.line.getX2()
			+ l2.line.getY1()
			+ l2.line.getY2()));
		}
		else
		{
			return 1;
		}
	}
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		Stroke valeurDefautStroke = g2D.getStroke();
		g2D.setStroke(new BasicStroke((float) largeur));
		AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angle), line.getX1(), line.getY1());
		Shape s = at.createTransformedShape(line);
		g2D.setColor(this.remplissage);
		g2D.draw(s);
		g2D.setStroke(valeurDefautStroke);
		return;
	}

	@Override
	public void replacerForme(double x, double y) {
		double deltaX = line.getX2() - line.getX1();
		double deltaY = line.getY2() - line.getY1();
		System.out.println("Delta X :" + deltaX + " Delta Y :" + deltaY);
		System.out.println(line.getX1() + " , " + line.getY1());
		System.out.println(line.getX2() + " , " + line.getY2());
		line.setLine(x, y, x+deltaX, y+deltaY);
	}
	@Override
	public void effectuerSymetrieAxialeHorizontale(double x) {
		double deltaX1 = x - line.getX1();
		double deltaX2 = x - line.getX2();
		this.line = new Line2D.Double(x + deltaX1, line.getY1(), x + deltaX2, line.getY2());
		
	}
	@Override
	public void effectuerSymetrieAxialeVerticale(double y) {
		double deltaY1 = y - line.getY1();
		double deltaY2 = y - line.getY2();
		
		this.line = new Line2D.Double(line.getX1(), y + deltaY1, line.getX2(), y + deltaY2);
		
	}

}
	