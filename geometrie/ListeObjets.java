package geometrie;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListeObjets extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -173714905502240523L;
	Dessin root;
	Application app;
	private Image focused;
	public Image getFocused() {
		return focused;
	}
	public void setFocused(Image focused) {
		this.focused = focused;
	}
	private DefaultListModel<Object> listModel;
	private JList<Object> jlist;
	private JButton ajouterImage;
	public ListeObjets(Dessin d, Application app)
	{
		root = d;
		this.app = app;
		focused = d.images.first();
		initialisationUI();
	}
	public void initialisationUI()
	{
		listModel= new DefaultListModel<Object>();
		jlist = new JList<Object>(listModel);
		updateListModel();
		jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlist.setLayoutOrientation(JList.VERTICAL);
		jlist.setVisibleRowCount(15);
		jlist.addListSelectionListener(new ListSelectionListener()
				{
					@Override
					public void valueChanged(ListSelectionEvent arg0) {
						if(!arg0.getValueIsAdjusting())
						{
							if(jlist.getSelectedValue() != null)
							{
								Forme f = (Forme)root.fetchForme(jlist.getSelectedValue().toString().substring(1));
								System.out.println(f.getName());
								if(f != null)
								{
									System.out.println(f.getName());
								}
								if(f.getClass().isAssignableFrom(Image.class))
								{
									focused = (Image)f;	
									System.out.println("Image focused : " + f.getName()) ;
								}
								else
								{
									
								}
							}
						}
					}
			
				});
		jlist.addMouseListener(new MouseListener()
				{
			/**
			 * Permet à l'utilisateur d'effectuer diverses transformations sur les formes
			 * En effectuant un clic droit, un JPopUpMenu s'affiche et propose à l'utilisateur les diverses fonctions
			 * La classe ListeObjets est necessaire pour le bon fonctionnement de la methode
			 */
					@Override
					public void mouseClicked(MouseEvent e) {
						if(SwingUtilities.isRightMouseButton(e))
						{
							jlist.setSelectedIndex(jlist.locationToIndex(e.getPoint()));
							JPopupMenu menu = new JPopupMenu();
							JMenuItem supprimer = new JMenuItem("Supprimer");
							supprimer.addActionListener(new ActionListener(){
								@Override
								public void actionPerformed(ActionEvent arg0) {
									System.out.println("L'utilisateur a choisi de supprimer la forme");
									if(jlist.getSelectedIndex() == 0)
									{
										System.out.println("Erreur : L'utilisateur a tente de supprimer le dessin racine");
										JOptionPane.showMessageDialog(null, "Impossible de supprimer le dessin racine.", 
												"Erreur !", JOptionPane.ERROR_MESSAGE);
									}
									else
									{
										int confirmerSuppression = JOptionPane.showConfirmDialog(null, "Etes vous sur ?");
										if(confirmerSuppression == JOptionPane.YES_OPTION)
										{
											Forme f = root.fetchForme(jlist.getSelectedValue().toString().substring(1));
											if(f instanceof Image)
											{
												root.retirerImage((Image) f);
												focused = null;
											}
											else
											{
												focused.retirerForme(f);
											}
											updateListModel();
											app.revalidate();
											app.repaint();
										}
									}
								}
							});
							JMenuItem homothetie = new JMenuItem("Redimensionner");
							homothetie.addActionListener(new ActionListener(){
								@Override
								public void actionPerformed(ActionEvent arg0)
								{
									double facteur =Double.parseDouble(JOptionPane.showInputDialog("Veuillez inserez le nouveau facteur", 100));
									Forme f = root.fetchForme(jlist.getSelectedValue().toString().substring(1));
									f.effectuerHomothetie(facteur/100);
									app.revalidate();
									app.repaint();
								}
							});
							JMenuItem translater = new JMenuItem("Translater");
							translater.addActionListener(new ActionListener()
									{

										@Override
										public void actionPerformed(ActionEvent e) {
											SaisieCoordonnees sc = new SaisieCoordonnees(
													"Indiquez de combien de pixels vous voulez translater");
											Forme f = root.fetchForme(jlist.getSelectedValue().toString().substring(1));
											f.effectuerTranslation(
													Double.parseDouble(sc.getValeurX().getText()), 
													Double.parseDouble(sc.getValeurY().getText()));
											app.revalidate();
											app.repaint();
											System.out.println("Translation de " + sc.getValeurX().getText() + "," +
											sc.getValeurY().getText());
										}
								
									});
							JMenuItem replacer = new JMenuItem("Replacer");
							replacer.addActionListener(new ActionListener(){
								@Override
								public void actionPerformed(ActionEvent arg0)
								{
									SaisieCoordonnees sc = new SaisieCoordonnees("Choississez les nouvelles coordonnes de votre forme");
									Forme f = root.fetchForme(jlist.getSelectedValue().toString().substring(1));
									f.replacerForme(
											Double.parseDouble(sc.getValeurX().getText()), 
											Double.parseDouble(sc.getValeurY().getText()));
									app.revalidate();
									app.repaint();
									System.out.println("Choisi de replacer " + f.getName() + " en " + sc.getValeurX().getText() + "," + sc.getValeurY().getText());
								}
							});
							JMenuItem symetrieCentrale = new JMenuItem("Sym. Centrale");
							symetrieCentrale.addActionListener(new ActionListener(){
								@Override
								public void actionPerformed(ActionEvent arg0)
								{
									Forme f = root.fetchForme(jlist.getSelectedValue().toString().substring(1));
									f.effectuerSymetrieCentrale(app.canvas.getWidth()/2, app.canvas.getHeight()/2);
									app.revalidate();
									app.repaint();
									System.out.println("Symetrie Centrale effectue sur la forme " + f.getName());
								}
							});
							JMenuItem symetrieAxialeHorizontale = new JMenuItem("Sym. Axiale Hor.");
							symetrieAxialeHorizontale.addActionListener(new ActionListener(){
								@Override
								public void actionPerformed(ActionEvent arg0)
								{
									Forme f = root.fetchForme(jlist.getSelectedValue().toString().substring(1));
									f.effectuerSymetrieAxialeHorizontale(app.canvas.getWidth()/2);
									app.revalidate();
									app.repaint();
									System.out.println("Symetrie Ax.Horizontale effectue sur la forme " + f.getName());
								}
							});
							JMenuItem symetrieAxialeVerticale = new JMenuItem("Sym. Axiale Ver.");
							symetrieAxialeVerticale.addActionListener(new ActionListener(){
								@Override
								public void actionPerformed(ActionEvent arg0)
								{
									Forme f = root.fetchForme(jlist.getSelectedValue().toString().substring(1));
									f.effectuerSymetrieAxialeVerticale(app.canvas.getHeight()/2);
									app.revalidate();
									app.repaint();
									System.out.println("Symetrie Ax.Verticale effectue sur la forme " + f.getName());
									
								}
							});
							JMenuItem rotation = new JMenuItem("Rotation");
							rotation.addActionListener(new ActionListener(){

								@Override
								public void actionPerformed(ActionEvent arg0) {
									double s = Double.parseDouble(JOptionPane.showInputDialog("Indiquez l'angle de combien vous voulez faire une rotation"));
									Forme f = root.fetchForme(jlist.getSelectedValue().toString().substring(1));
									f.effectuerRotation(s);
									app.revalidate();
									app.repaint();
								}
								
							});
							menu.add(supprimer);
							menu.add(homothetie);
							menu.add(translater);
							menu.add(rotation);
							menu.add(replacer);
							menu.add(symetrieCentrale);
							menu.add(symetrieAxialeHorizontale);
							menu.add(symetrieAxialeVerticale);
							menu.show(jlist, e.getPoint().x,e.getPoint().y);
							System.out.println("Right click detected !");
						}
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						
					}

					@Override
					public void mousePressed(MouseEvent e) {
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						
					}
			
				});
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(jlist);
		this.add(scrollPane, BorderLayout.NORTH);
		
		ajouterImage = new JButton("Ajouter Image");
		ajouterImage.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = JOptionPane.showInputDialog("Nom de l'image ?");
				root.ajouterImage(new Image(name));
				System.out.println(root.images.size());
				updateListModel();
			}
			
		});
		this.add(ajouterImage, BorderLayout.SOUTH);
		
	}
	/**
	 * Met a jour le ListModel en transformant un TreeSet d'image en une JList
	 * Part du dessin racine et parcourt recursivement les formes contenues dedans
	 */
	public void updateListModel()
	{
		System.out.println("Mise a jour du ListModel");
		this.listModel = new DefaultListModel<Object>();
		listModel.addElement("*"+root.getName());
		for(Image image : root.images)
		{
			listModel.addElement("+" + image.getName());
			for(Forme f : image.formes)
			{
				listModel.addElement("-" + f.getName());
			}
			System.out.println(image);
			System.out.println("Nombre de formes dans l'image : " + image.formes.size());
		}
		this.jlist.setModel(listModel);
	}
}
