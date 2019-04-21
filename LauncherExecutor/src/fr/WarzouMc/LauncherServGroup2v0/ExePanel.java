package fr.WarzouMc.LauncherServGroup2v0;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import fr.theshark34.swinger.util.WindowMover;

@SuppressWarnings("serial")
public class ExePanel extends JPanel {

	private JLabel infoLabel = new JLabel("Files creation", SwingConstants.CENTER);
	
	public ExePanel() {
		
		this.setLayout(null);
		
		infoLabel.setForeground(Color.BLACK);
		infoLabel.setFont(infoLabel.getFont().deriveFont(20f));
		infoLabel.setBounds(0, 10, 475, 25);
		this.add(infoLabel);
		
	}
	public void setInfoText(String text) {
		
		infoLabel.setText(text);
		
	}
	
}
