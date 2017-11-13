package geometrie;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;


public class Application extends JFrame implements MouseListener{
	private Dessin root;
	private Image im;
	MenuChoixForme menuchoixforme;
	ListeObjets listeobjets;
	/**
	 * Renvoie la racine du dessin
	 * @return Le dessin racine
	 */
	public Dessin getRoot() {
		return root;
	}

	public void setRoot(Dessin root) {
		this.root = root;
	}
	ModeTri modeDessin;
	Canvas canvas;
	ChoixCouleur choixCouleur;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Application()
	{
		setIm(new Image());
		root = new Dessin();
		root.ajouterImage(getIm());
		initialisationFenetre();
	}
	
	/**
	 * Initialise l'application et ses différents composants.
	 * 
	 */
	public void initialisationFenetre()
	{
		setTitle("Dessine moi un mouton - DENIS - ZWETYENGA");
		setSize(1200, 800);
		this.setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		menuchoixforme = new MenuChoixForme();
		add(menuchoixforme, BorderLayout.PAGE_START);
		listeobjets = new ListeObjets(root, this);
		add(listeobjets, BorderLayout.EAST);
		modeDessin = new ModeTri(this.root, this);
		add(modeDessin, BorderLayout.SOUTH);
		canvas = new Canvas(this);
		add(canvas, BorderLayout.CENTER);
		choixCouleur = new ChoixCouleur();
		add(choixCouleur, BorderLayout.WEST);
		System.out.println("Application initialisee");
	}
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(()->{
			Application app = new Application();
			app.setVisible(true);
		});
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	public void paint(Graphics g)
	{
		super.paint(g);
	}

	public Image getIm() {
		return im;
	}

	public void setIm(Image im) {
		this.im = im;
	}

}