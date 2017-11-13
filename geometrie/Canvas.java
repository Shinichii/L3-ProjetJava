package geometrie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InvalidClassException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Canvas extends JPanel {
	/**
	 * Classe qui permet à l'utilisateur de dessiner
	 * 
	 */
	private static final long serialVersionUID = 70663370682367586L;
	Application app;
	double x;
	double y;
	double x1, y1; // Que pour le dessin d'une ligne
	ArrayList<Integer> xPoints, yPoints;
	boolean clickedFirst;

	public Canvas(Application ap) {
		super();
		clickedFirst = false;
		this.setBorder(BorderFactory.createLineBorder(Color.black, 15));
		this.app = ap;
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}
			
			/**
			 * S'occupe de dessiner la forme specifiee par menuchoixforme dans l'application principale
			 * Si c'est une ligne, la fonction attend que l'utilisateur clique une nouvelle fois pour tracer la ligne entre les deux points
			 * Pour le polygone, la fonction garde en memoire les points cliques par l'utilisateur et ne creera que le polygone qu'une fois qu'il aura detecte un clic droit
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("Click detecte en (" + e.getX() + " , " + e.getY() + ")");
				x = e.getX();
				y = e.getY();
				if (app.menuchoixforme.mode != null && app.listeobjets.getFocused() != null) {
					switch (app.menuchoixforme.mode) {
					case "Cercle":
						Cercle cercle = new Cercle(x, y, app.menuchoixforme.largeur, app.choixCouleur.getRemplissage(),
								app.choixCouleur.getContour());
						try {
							app.listeobjets.getFocused().ajouterForme(cercle);
						} catch (InvalidClassException | AireImageSuperieureException e2) {
							// TODO Auto-generated catch block
							cercle = null;
						}
						break;
					case "Ellipse":
						Ellipse ellipse = new Ellipse(x, y, app.menuchoixforme.largeur, app.menuchoixforme.longueur,
								app.choixCouleur.getRemplissage(), app.choixCouleur.getContour());
						try {
							app.listeobjets.getFocused().ajouterForme(ellipse);
						} catch (InvalidClassException | AireImageSuperieureException e2) {
							// TODO Auto-generated catch block
							ellipse = null;
						}
						break;
					case "Carre":
						Carre carre = new Carre(x, y, app.menuchoixforme.largeur, app.choixCouleur.getRemplissage(),
								app.choixCouleur.getContour());
						try {
							app.listeobjets.getFocused().ajouterForme(carre);
						} catch (InvalidClassException | AireImageSuperieureException e1) {
							carre = null;
						}
						break;
					case "Rectangle":
						Rectangle r = new Rectangle(x, y, app.menuchoixforme.longueur, app.menuchoixforme.largeur,
								app.choixCouleur.getRemplissage(), app.choixCouleur.getContour());
						try {
							app.listeobjets.getFocused().ajouterForme(r);
						} catch (InvalidClassException | AireImageSuperieureException e1) {
							r = null;
						}
						break;
					case "Ligne":
						if (!clickedFirst) {
							x1 = e.getX();
							y1 = e.getY();
						} else {
							try {
								Ligne line = new Ligne(x1, y1, x, y, app.menuchoixforme.largeur / 10,
										app.choixCouleur.getRemplissage());
								app.listeobjets.getFocused().ajouterForme(line);
							} catch (InvalidClassException | AireImageSuperieureException e1) {
								e1.printStackTrace();
							}
						}
						clickedFirst = !clickedFirst;
						break;
					case "Polygone":
						if (!clickedFirst) {
							clickedFirst = true;
							xPoints = new ArrayList<Integer>();
							yPoints = new ArrayList<Integer>();
							xPoints.add(e.getX());
							yPoints.add(e.getY());
						} else {
							if (SwingUtilities.isRightMouseButton(e)) {
								clickedFirst = false;
								Polygone polygone = new Polygone(xPoints.stream().mapToInt(Integer::intValue).toArray(),yPoints.stream().mapToInt(Integer::intValue).toArray(),app.menuchoixforme.largeur / 10, app.choixCouleur.getRemplissage());
								try {
									app.listeobjets.getFocused().ajouterForme(polygone);
								} catch (InvalidClassException | AireImageSuperieureException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} else {
								xPoints.add(e.getX());
								yPoints.add(e.getY());
								System.out.println(xPoints);
								System.out.println(yPoints);
							}
						}
					}
					app.listeobjets.updateListModel();
					app.revalidate();
					app.repaint();
				} else if (app.listeobjets.getFocused() == null) {
					JOptionPane.showConfirmDialog(null, "Choississez une image sur laquelle dessiner", "Erreur",
							JOptionPane.OK_OPTION);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
	}

	public void paint(Graphics g) {
		super.paint(g);
		this.dessinerFormes(g);
	}

	/**
	 * Dessine les formes sur le canvas à l'aide de paintComponent()
	 * 
	 * @param g Les graphics de la methode paint
	 */
	void dessinerFormes(Graphics g) {
		for (Image image : app.getRoot().images) {
			for (Forme forme : image.formes) {
				g.setColor(Color.RED);
				forme.paintComponent(g);

			}
		}

	}

}
