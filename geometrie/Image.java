package geometrie;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.io.InvalidClassException;
import java.util.TreeSet;

public class Image extends Forme {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int nombreditems = 0;
	TreeSet<Forme> formes;
	
	
	public void finalize()
	{
		nombreditems--;
	}
	public void triPerimetre()
	{
		TreeSet<Forme> tmp= new TreeSet<Forme>(ComparatorForme.PERIMETRE_ORDER);
		tmp.addAll(formes);
		formes = tmp;
	}
	public void triAire()
	{
		TreeSet<Forme> tmp= new TreeSet<Forme>(ComparatorForme.AIRE_ORDER);
		tmp.addAll(formes);
		formes = tmp;
	}
	public void triDistOrigin()
	{
		TreeSet<Forme> tmp= new TreeSet<Forme>(ComparatorForme.DIST_ORIGIN_ORDER);
		tmp.addAll(formes);
		formes = tmp;
	}
	public void triName()
	{
		TreeSet<Forme> tmp = new TreeSet<Forme>(ComparatorForme.NAME_ORDER);
		tmp.addAll(formes);
		formes = tmp;
	}
	/** 
	 * @param f une forme a ajouter
	 * @throws AireImageSuperieureException si on tente d'ajouter une image dont l'aire est plus grande
	 * @throws InvalidClassException si on tente d'ajouter autre chose qu'une forme
	 */
	public void ajouterForme(Forme f) throws AireImageSuperieureException, InvalidClassException
	{
		if(!Forme.class.isAssignableFrom(f.getClass()))
		{
			throw new InvalidClassException("L'objet doit etre une forme ou une image");
		}
		if(f.getClass() == Image.class && f.calculAire() > this.calculAire())
		{
			throw new AireImageSuperieureException("Impossible d'ajouter une image plus grande dans cette image");
		}
		else if(this.formes.contains(f))
		{
			throw new InvalidClassException("L'objet existe déjà"); 	
		}
		else
		{
			formes.add(f);
		}
	}
	public void retirerForme(Forme f)
	{
		formes.remove(f);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((formes == null) ? 0 : formes.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		if (formes == null) {
			if (other.formes != null)
				return false;
		} else if (!formes.equals(other.formes))
			return false;
		return true;
	}
	public Image()
	{
		formes = new TreeSet<Forme>();
		this.name = ("Image" + nombreditems);
		nombreditems++;
	}
	public Image(String name)
	{
		formes = new TreeSet<Forme>();
		this.name = name;
		nombreditems++;
	}
	public double calculAire()
	{
		double sum = 0;
		for(Forme forme: formes)
		{
			sum += forme.calculAire();
		}
		return sum;
	}
	@Override
	public String toString() {
		return "Image " + "nom=" + name + ", [formes=" + formes +  "]";
	}	
	public double calculPerimetre()
	{
		double sum = 0;
		for(Forme forme: formes)
		{
			sum += forme.calculPerimetre();
		}
		return sum;
	}
	public void effectuerHomothetie(double k)
	{
		for(Forme forme: formes)
		{
			forme.effectuerHomothetie(k);
		}
	}
	public void effectuerTranslation(double x, double y)
	{
		for(Forme forme: formes)
		{
			forme.effectuerTranslation(x, y);
		}
	}
	public void effectuerTranslation(Point2D z)
	{
		for(Forme forme : formes)
		{
			forme.effectuerTranslation(z);
		}
	}
	public void effectuerRotation(double angle)
	{
		for(Forme forme : formes)
		{
			forme.effectuerRotation(angle);
		}
	}
	@Override
	public void effectuerSymetrieCentrale(double x, double y)
	{
		for(Forme forme : formes)
		{
			forme.effectuerSymetrieCentrale(x, y);
		}
	}

	@Override
	public int compareTo(Object o) {
		if(o.getClass().isAssignableFrom(Image.class))
		{
			Image im = (Image) o;
			return this.hashCode() - im.hashCode();
		}
		return -1;
	}
	@Override
	public void paintComponent(Graphics g) {
		for(Forme forme : formes)
		{
			forme.paintComponent(g);
		}
	}
	/**
	 * Cette methode itere a travers les formes de l'image pour retrouver une forme à partir de son nom
	 * @param name le nom de la forme a chercher
	 * @return forme une reference vers la forme ou null si la forme n'est pas presente
	 */
	public Forme fetchForme(String name) {
		if(name.equals(this.getName()))
		{
			return this;
		}
		else 
		{
			for(Forme forme : formes)
			{
				if(forme.getName().equals(name))
				{
					return forme;
				}
			}
		}
		return null;
	}
	@Override
	public void replacerForme(double x, double y) {
		return;
	}
	@Override
	public void effectuerSymetrieAxialeHorizontale(double hauteur) {
		for(Forme f : this.formes)
		{
			f.effectuerSymetrieAxialeHorizontale(hauteur);
		}
	}
	@Override
	public void effectuerSymetrieAxialeVerticale(double largeur) {
		for(Forme f : this.formes)
		{
			f.effectuerSymetrieAxialeVerticale(largeur);
		}
	}
	
	
}
