package geometrie;

import javax.swing.*;

public class SaisieCoordonnees {
	private JTextField valeurX;
	private JTextField valeurY;
	
	public JTextField getValeurX() {
		return valeurX;
	}
	public void setValeurX(JTextField valeurX) {
		this.valeurX = valeurX;
	}
	public JTextField getValeurY() {
		return valeurY;
	}
	public void setValeurY(JTextField valeurY) {
		this.valeurY = valeurY;
	}
	JPanel myPanel;
	/**
	 * Cree une fenetre dans laquelle un utilisateur peut entrer deux valeurs
	 * Une qui correspond à une valeur x, une autre qui correspond à une valeur y
	 * @param aideTexte Texte a afficher a l'utilisateur pour le guider dans sa saisie
	 */
   public SaisieCoordonnees(String aideTexte){
      valeurX = new JTextField(5);
      valeurY = new JTextField(5);

      myPanel = new JPanel();
      myPanel.add(new JLabel("x:"));
      myPanel.add(valeurX);
      myPanel.add(Box.createHorizontalStrut(15)); 
      myPanel.add(new JLabel("y:"));
      myPanel.add(valeurY);

      int result = JOptionPane.showConfirmDialog(null, myPanel, 
               aideTexte, JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
         System.out.println("x value: " + valeurX.getText());
         System.out.println("y value: " + valeurY.getText());
      }
   }
}