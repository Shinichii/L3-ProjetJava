package geometrie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class ModeTri extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8148557777885380546L;
	private JToggleButton boutonTriPerimetre;
	private JToggleButton boutonTriAire;
	private JToggleButton boutontriDistanceOrigine;
	private Application app;
	private Dessin root;
	public ModeTri(Dessin root, Application app)
	{
		this.root = root;
		this.app = app;
		initialisationUI();
	}
	/**
	 * Permet de trier ListeObjets selon trois sortes de tri
	 * Selon l'Aire
	 * Selon le perimetre
	 * Selon la Distance à l'origine
	 */
	public void initialisationUI()
	{
		this.boutonTriAire = new JToggleButton("Tri Aire");
		this.boutonTriPerimetre = new JToggleButton("Tri Perimetre");
		this.boutontriDistanceOrigine = new JToggleButton("Tri dist. or.");
		this.boutonTriPerimetre.setSelected(true);
		this.boutonTriAire.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				root.triAire();
				app.listeobjets.updateListModel();
				app.repaint();
				boutonTriPerimetre.setSelected(false);
				boutontriDistanceOrigine.setSelected(false);
			}	
		});
		
		this.boutonTriPerimetre.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				root.triPerimetre();
				app.listeobjets.updateListModel();
				app.repaint();
				boutonTriAire.setSelected(false);
				boutontriDistanceOrigine.setSelected(false);
			}
		});
		this.boutontriDistanceOrigine.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				root.triDistOrigin();
				app.listeobjets.updateListModel();
				app.repaint();
				boutonTriAire.setSelected(false);
				boutonTriPerimetre.setSelected(false);
				
			}
			
		});
		this.add(boutonTriPerimetre);
		this.add(boutonTriAire);
		this.add(boutontriDistanceOrigine);
	}

}
