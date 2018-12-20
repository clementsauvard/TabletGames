package app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class MenuChoixDifficulte extends JFrame{

	protected JPanel panelMenu;
	protected BorderLayout layoutMenu;
	protected JPanel panelBtn;
	protected GridLayout layoutBtn;
	protected JLabel lblDifficulte;
	protected JButton btnFacile;
	protected JButton btnMoyen;
	protected JButton btnDifficile;
	protected JButton btnCancel;
	
	public MenuChoixDifficulte(){
		

		panelMenu = new JPanel();
		layoutMenu = new BorderLayout();
		panelBtn = new JPanel();
		layoutBtn = new GridLayout(1,3);
		lblDifficulte = new JLabel("Choisir un mode de jeu :");
		btnFacile = new JButton("Facile");
		btnMoyen = new JButton("Moyen");
		btnDifficile = new JButton("Difficile");
		btnCancel = new JButton("Annuler");
		
		btnFacile.addActionListener(btnFacilelistener);
		btnMoyen.addActionListener(btnMoyenlistener);
		btnDifficile.addActionListener(btnDifficilelistener);
		btnCancel.addActionListener(btnCancellistener);
		panelBtn.setLayout(layoutBtn);
		panelBtn.add(btnFacile);
		panelBtn.add(btnMoyen);
		panelBtn.add(btnDifficile);
		
		panelMenu.setLayout(layoutMenu);
		panelMenu.add(lblDifficulte,BorderLayout.NORTH);
		panelMenu.add(panelBtn, BorderLayout.CENTER);
		panelMenu.add(btnCancel,BorderLayout.SOUTH);
		add(panelMenu);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	 private ActionListener btnFacilelistener = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("appel tap tablet facile");
				TapTablet tt = new TapTablet(1);
				setVisible(false);
				
			}
			   
};

private ActionListener btnMoyenlistener = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("appel Tap tablet moyen");
			TapTablet tt = new TapTablet(2);
			setVisible(false);
		}
		   
};
private ActionListener btnDifficilelistener = new ActionListener() {

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("appel Tap tablet difficile");
		TapTablet tt = new TapTablet(3);
		setVisible(false);
		
	}
	   
};

private ActionListener btnCancellistener = new ActionListener() {

	public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub	
		System.out.println("fermeture menu du choix des difficultés (tap tablet)");
		 dispose();
	}
	   
};
	
}

