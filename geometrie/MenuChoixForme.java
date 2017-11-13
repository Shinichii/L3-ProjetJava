package geometrie;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MenuChoixForme extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JToggleButton boutonCercle;
	JToggleButton boutonCarre;
	JToggleButton boutonRectangle;
	JToggleButton boutonEllipse;
	JToggleButton boutonLigne;
	JToggleButton boutonPolygone;
	JSlider sliderLargeur;
	JTextField valeurLargeur;
	JSlider sliderLongueur;
	JTextField valeurLongueur;
	JToggleButton[] boutons;
	String mode;
	double longueur;
	double largeur;
	/**
	 * Permet à l'utilisateur de selectionner sa forme
	 * Et la valeur de la longueur et de la largeur
	 * Pour le carre, le cercle, la ligne et le polygone le slider largeur est desactive
	 */
	public MenuChoixForme()
	{
		longueur = 100;
		largeur = 100;
		mode = new String("Carre");
		sliderLargeur = new JSlider(JSlider.HORIZONTAL,0, 800, 100);
		sliderLongueur = new JSlider(JSlider.HORIZONTAL, 0, 800, 100);
		boutonCercle = new JToggleButton("Cercle", false);
		boutonCarre = new JToggleButton("Carre", false);
		boutonRectangle = new JToggleButton("Rectangle",true);
		boutonEllipse = new JToggleButton("Ellipse", false);
		boutonLigne = new JToggleButton("Ligne", false);
		boutonPolygone = new JToggleButton("Polygone", false);
		valeurLargeur = new JTextField();
		valeurLongueur = new JTextField();
		
		//Initialisation sliders
		this.add("Largeur", sliderLargeur);
		this.add(valeurLargeur, BorderLayout.SOUTH);
		sliderLargeur.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				largeur = sliderLargeur.getValue();
				valeurLargeur.setText(Double.toString(largeur));
			}
		});
		this.add("Longueur", sliderLongueur);
		this.add(valeurLongueur, BorderLayout.SOUTH);
		sliderLongueur.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				longueur = sliderLongueur.getValue();
				valeurLongueur.setText(Double.toString(longueur));	
			}
		});
		sliderLargeur.setPaintLabels(true);
		sliderLargeur.setPaintTicks(true);
		valeurLargeur.setText(Double.toString(largeur));
		valeurLongueur.setText(Double.toString(longueur));

		boutons = new JToggleButton[] {boutonCercle, boutonCarre, boutonRectangle, boutonEllipse, boutonLigne,
				boutonPolygone}; 	
		for(JToggleButton bouton : boutons)
		{
			this.add(bouton);
			bouton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					for(JToggleButton b : boutons)
					{
						b.setSelected(false);
					}
					bouton.setSelected(true);
					mode = bouton.getText();
					System.out.println(mode);
					if(arg0.getSource() == boutonCarre || arg0.getSource() == boutonCercle
							||arg0.getSource() == boutonLigne || arg0.getSource() == boutonPolygone)
					{
						sliderLongueur.setEnabled(false);
						valeurLongueur.setEnabled(false);
					}
					else
					{
						sliderLongueur.setEnabled(true);
						valeurLongueur.setEnabled(true);
					}
				}
			});
		}
	}
}
