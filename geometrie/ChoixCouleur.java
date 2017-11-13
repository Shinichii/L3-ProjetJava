package geometrie;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

public class ChoixCouleur extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8426716855017001128L;
	private Color remplissage;
	private Color contour;
	/**
	 * Interface du choix des couleurs
	 * Se base sur la classe JColorChooser
	 */
	public ChoixCouleur()
	{
		JButton boutonRemplissage = new JButton("Remplissage");	
		JButton boutonContour = new JButton("Contour");
		remplissage = Color.BLACK;
		contour = Color.WHITE;
		boutonRemplissage.setBackground(remplissage);
		boutonContour.setBackground(contour);
		
		add(boutonRemplissage);
		add(boutonContour);
		boutonRemplissage.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				remplissage = JColorChooser.showDialog(null, "Choississez la couleur", remplissage);
				boutonRemplissage.setBackground(remplissage);
			}
			
		});
		boutonContour.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				contour = JColorChooser.showDialog(null, "Choississez la couleur", contour);
				boutonContour.setBackground(contour);
			}
		});
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
	
	
}
