package geometrie;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

public class Polygone extends Forme {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1013048964345669552L;
	private int xPoints[];
	private int yPoints[];
	private double largeur;
	private static int nombreditems = 0;
	public int[] getXPoints()
	{
		int[] toReturn = new int[xPoints.length];
		for(int i = 0; i < xPoints.length; i++)
		{
			toReturn[i] = xPoints[i];
		}
		return toReturn;
	}
	public int[] getYPoints()
	{
		int[] toReturn = new int[yPoints.length];
		for(int i = 0; i < yPoints.length; i++)
		{
			toReturn[i] = yPoints[i];
		}
		return toReturn;
	}
	public Polygone(Polygone p)
	{
		this(p.getXPoints(), p.getYPoints(), p.getLargeur(), p.getRemplissage());
	}
	public double getLargeur()
	{
		double l;
		l = this.getLargeur();
		return l;
	}
	public Polygone(int xPoints[], int yPoints[], double largeur, Color remplissage)
	{
		
		nombreditems++;
		this.name = "Polygone"+nombreditems;
		this.remplissage = remplissage;
		this.xPoints = xPoints;
		this.yPoints = yPoints;
		this.largeur = largeur;
	}
	
	public Polygone(int xPoints[], int yPoints[], double largeur)
	{
		this(xPoints, yPoints, largeur, Color.BLACK);
	}
	@Override
	public void replacerForme(double x, double y) {
		double deltaX = x - this.xPoints[0];
		double deltaY = y - this.yPoints[0];
		this.effectuerTranslation(deltaX, deltaY);
		
	}
		/**
		 * Calcul de l'aire d'un polygone quelconque.
		 * Source : http://www.mathopenref.com/coordpolygonarea2.html
		 */
	@Override
	public double calculAire() {
		double aire = 0;
		int j = xPoints.length - 1;
		for(int i = 0; i < xPoints.length; i++)
		{
			aire += (xPoints[j] + xPoints[i]) * (yPoints[j] - yPoints[i]);
			j = i;
		}
		return aire/2;
	}
	/**
	 * Calcul le périmètre d'un polygone quelconque en sommant la valeur des segments
	 */
	@Override
	public double calculPerimetre() {
		double sum = 0;
		for(int i = 1; i < xPoints.length; i++)
		{
			//Calcul de la longueur de chaque segment du polygone
			sum += Math.sqrt(((Math.pow(xPoints[i]-xPoints[i-1], 2))+(Math.pow(yPoints[i]-yPoints[i-1],2))));
		}
		//Pour revenir au debut
		sum += Math.sqrt(
				((Math.pow(xPoints[xPoints.length-1]-xPoints[0], 2)+
				(Math.pow(yPoints[yPoints.length-1]-yPoints[0], 2)))));
		System.out.println("Perimetre = " + sum);
		return sum;
	}

	@Override
	public void effectuerHomothetie(double k) {
		int moyenneX = 0;
		int moyenneY = 0;
		for(int i = 0; i < xPoints.length; i++)
		{
			moyenneX += xPoints[i];
			moyenneY += yPoints[i];
			
		}
		moyenneX /= xPoints.length;
		moyenneY /= yPoints.length;
		for(int i = 0; i < xPoints.length; i++)
		{
			xPoints[i] = (int) ((xPoints[i]-moyenneX) * k + moyenneX);
			yPoints[i] = (int) ((yPoints[i] - moyenneY) * k + moyenneY);
		}
		
	}

	@Override
	public void effectuerTranslation(double x, double y) {
		for(int i = 0; i < xPoints.length; i++)
		{
			xPoints[i] += x;
			yPoints[i] += y;
		}
		return;
	}

	@Override
	public void effectuerTranslation(Point2D z) {
		effectuerTranslation(z.getX(), z.getY());

	}

	@Override
	public int compareTo(Object o) {
		if(Polygone.class.isAssignableFrom(o.getClass()))
		{
			Polygone p = (Polygone) o;
			if(p.calculAire() != this.calculAire())
			{
				return (int) (this.calculAire() - p.calculAire());
			}
			else if(p.calculPerimetre() != this.calculPerimetre())
			{
				return (int) (this.calculPerimetre() - p.calculPerimetre());
			}
			else
			{
				return this.getName().compareTo(p.getName());
			}
		}
		else
		{
			return 1;
		}
	}
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD,
				xPoints.length);
		polygon.moveTo(xPoints[0], yPoints[0]);
		for(int index = 1; index < xPoints.length; index++)
		{
			polygon.lineTo(xPoints[index], yPoints[index]);
		}
		polygon.closePath();
		Stroke valeurDefautStroke = g2D.getStroke();
		g2D.setStroke(new BasicStroke((float) largeur));
		g2D.setColor(remplissage);
		g2D.draw(polygon);
		g2D.setStroke(valeurDefautStroke);
		
	}
	
	@Override
	public void effectuerSymetrieAxialeHorizontale(double x) {
		for(int i = 0; i < xPoints.length; i++)
		{
			this.effectuerRotation(90);
			double deltaX = x - xPoints[i];
			xPoints[i] = (int) (x + deltaX);
		}
		
	}

	@Override
	public void effectuerSymetrieAxialeVerticale(double y) {
		for(int i = 0; i < yPoints.length; i++)
		{
			double deltaY = y - yPoints[i];
			yPoints[i] = (int)(y + deltaY);
		}
	}

}
