package fr.shiwanMc.LauncherServGroup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFrame;

import com.sun.awt.AWTUtilities;

import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.animation.Animator;
import fr.theshark34.swinger.util.WindowMover;

@SuppressWarnings("serial")
public class Frame extends JFrame {

	private static Frame instance;
	private LauncherPanel laucherpanel;
	
	public Frame() throws IOException{
		
		this.setTitle("SkyExpender Launcher alpha 0.0.1");
		this.setSize(975, 625);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setIconImage(Swinger.getResource("icon2.png"));
		this.setContentPane(laucherpanel = new LauncherPanel());
		AWTUtilities.setWindowOpacity(this, 0.0f);
		
		WindowMover mover = new WindowMover(new Mouse());
		this.addMouseListener(mover);
		this.addMouseMotionListener(mover);
		addMouseMotionListener(new Mouse());
		
		this.setVisible(true);
		
		Animator.fadeInFrame(this, 2);
			
	}
	
	public static void main(String[] args) throws Exception{

		Swinger.setSystemLookNFeel();
		Swinger.setResourcePath("/fr/shiwanMc/LauncherServGroup/ressource/");
		
		instance = new Frame();
		
	}
	
	public static Frame getInstance() {
		
		return instance;
		
	}
	
	public LauncherPanel getLauncherPanel() {
		
		return this.laucherpanel;
		
	}
	
}
