package geometrie;

import java.awt.Color;

public class Carre extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int nombreditems = 0;
	
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return super.compareTo(arg0);
	}
	public void finalize()
	{
		nombreditems--;
	}
	@Override
	public String toString() {
		return "Carre [name=" + name + ", customX=" + customX + ", customY=" + customY + ", height=" + rect.height
				+ ", width=" + rect.width+"]";
	}	
	public Carre(double x, double y, double cote) {
		this(x,y,cote,Color.BLACK,Color.WHITE);
	}
	public Carre(double x, double y, double cote, Color remplissage, Color contour) {
		super(x, y, cote, cote, remplissage, contour);
		this.setName("Carre" + nombreditems);
		nombreditems++;
	}
	public Carre(Carre c)
	{
		this(c.getX(), c.getY(), c.getWidth(), c.getRemplissage(), c.getContour());
	}

}
