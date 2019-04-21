package fr.WarzouMc.LauncherServGroup2v0;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fr.theshark34.supdate.SUpdate;
import fr.theshark34.supdate.application.integrated.FileDeleter;

public class Frame extends JFrame{
	
	private static Frame instance;
	
	public static Thread uptadeThread;
	
	private ExePanel pan;
	
	private static ArrayList<String> modsname = new ArrayList<>();
	
	@SuppressWarnings("static-access")
	public Frame() throws DirectoryNotEmptyException, Exception{
		
		Path mere = Paths.get("C:/Users/asus/AppData/Roaming/.SkyExpender");
		Path launcher = Paths.get("C:/Users/asus/AppData/Roaming/.SkyExpender/launcher");
		Path exe = Paths.get("C:/Users/asus/AppData/Roaming/.SkyExpender/launcher/launch.bat");
		Path vbs = Paths.get("C:/Users/asus/AppData/Roaming/.SkyExpender/launcher/launch.vbs");
		Path start = Paths.get("C:/Users/asus/AppData/Roaming/.SkyExpender/launcher/start.bat");
		Path mods = Paths.get("C:/Users/asus/AppData/Roaming/.SkyExpender/minecraft/mods");
		
		modsname.add("JEI");
		modsname.add("Chameleon-1.10.2-2.3.0");
		modsname.add("CodeChickenCore-1.10.2-Recommended");
		modsname.add("CodeChickenLib-1.10.2-2.5.9.283-universal");
		modsname.add("infinitylib-0.6.0");
		modsname.add("OptiFine");
		modsname.add("StorageDrawers");
		modsname.add("Xaeros-Minimap-Mod-1.10.2");
		
		if(!Files.exists(mere)) {
			
			Files.createDirectories(mere);
			
		}
		
		this.setTitle("SkyExpender Launcher");
		this.setSize(500, 100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setUndecorated(false);
		this.setResizable(false);
		
		this.setContentPane(pan = new ExePanel());
		
		this.setVisible(true);
		
		if(!Files.exists(launcher)) {
			
			this.update();			
			
			JOptionPane.showMessageDialog(this, "Please restarte the launcher");
			
			System.exit(0);
			
		}else {
			
			getPan().setInfoText("Recherche de mise a jour launcher");
			
			this.update();
			
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		
		instance = new Frame();
		
	}

	public static Frame getInstance() {
		
		return instance;
		
	}
	
	public static void update() throws Exception {
		
		//burlotbertrand@hotmail.fr
		
		File launcherf = new File("C:/Users/asus/AppData/Roaming/.SkyExpender");
		
		final SUpdate su = new SUpdate("http://crazycrafter.shambhalafm.fr/", launcherf);
		su.addApplication(new FileDeleter());
		su.start();
		
		Path mods = Paths.get("C:/Users/asus/AppData/Roaming/.SkyExpender/minecraft/mods");
		Path mc = Paths.get("C:/Users/asus/AppData/Roaming/.SkyExpender/minecraft/minecraft.jar");
		
		if(Files.exists(mods)) {
			
			for(int i = 0; i < modsname.size(); i++) {
				
				if(Files.exists(Paths.get("C:/Users/asus/AppData/Roaming/.SkyExpender/minecraft/mods/"+modsname.get(i)+".jar"))){
					
					Files.delete(Paths.get("C:/Users/asus/AppData/Roaming/.SkyExpender/minecraft/mods/"+modsname.get(i)+".jar"));
					
					System.out.println(Paths.get("C:/Users/asus/AppData/Roaming/.SkyExpender/minecraft/mods/"+modsname.get(i)+".jar").getFileName());
					
				}
				
			}
			
		}
		
		if(Files.exists(mc)) {
			Files.delete(mc);
		}
		
		if(Files.exists(mods)) {
			
			try {
				Files.delete(mods);
				JOptionPane.showMessageDialog(Frame.getInstance(), "Lancer le jeu");
				Process p = Runtime.getRuntime().exec("cmd /c C:/Users/asus/AppData/Roaming/.SkyExpender/launcher/start.bat");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(Frame.getInstance(), "Le dossier des mods est supect");
				e.printStackTrace();
			}
			
		}
		
		System.exit(0);
		
	}

	public ExePanel getPan() {
		return this.pan;
	}
	
}
