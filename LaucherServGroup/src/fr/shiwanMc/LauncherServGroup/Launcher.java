package fr.shiwanMc.LauncherServGroup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import fr.theshark34.openauth.AuthPoints;
import fr.theshark34.openauth.AuthenticationException;
import fr.theshark34.openauth.Authenticator;
import fr.theshark34.openauth.model.AuthAgent;
import fr.theshark34.openauth.model.response.AuthResponse;
import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.openlauncherlib.external.ExternalLaunchProfile;
import fr.theshark34.openlauncherlib.external.ExternalLauncher;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import fr.theshark34.openlauncherlib.minecraft.GameFolder;
import fr.theshark34.openlauncherlib.minecraft.GameInfos;
import fr.theshark34.openlauncherlib.minecraft.GameTweak;
import fr.theshark34.openlauncherlib.minecraft.GameType;
import fr.theshark34.openlauncherlib.minecraft.GameVersion;
import fr.theshark34.openlauncherlib.minecraft.MinecraftLauncher;
import fr.theshark34.supdate.BarAPI;
import fr.theshark34.supdate.SUpdate;
import fr.theshark34.supdate.Updater;
import fr.theshark34.supdate.application.integrated.FileDeleter;
import fr.theshark34.supdate.exception.BadServerResponseException;
import fr.theshark34.supdate.exception.BadServerVersionException;
import fr.theshark34.supdate.exception.ServerDisabledException;
import fr.theshark34.supdate.exception.ServerMissingSomethingException;
import fr.theshark34.swinger.Swinger;

public class Launcher {

	public static final GameVersion LC_VERSION = new GameVersion("1.10", GameType.V1_8_HIGHER);
	public static GameInfos LC_INFOS = new GameInfos("SkyExpender/minecraft", LC_VERSION, new GameTweak[] {GameTweak.FORGE});
	public static final File LC_DIR = LC_INFOS.getGameDir();
	
	public static AuthInfos authInfos;
	public static Thread uptadeThread;
	
	public static void auth(String username, String passworld) throws AuthenticationException {
		
		Authenticator authenticator = new Authenticator(Authenticator.MOJANG_AUTH_URL, AuthPoints.NORMAL_AUTH_POINTS);	
		
		if(Frame.getInstance().getLauncherPanel().isCrack() == false) {
			
			AuthResponse rep = authenticator.authenticate(AuthAgent.MINECRAFT, username, passworld, "");
			authInfos = new AuthInfos(rep.getSelectedProfile().getName(), rep.getAccessToken(), rep.getSelectedProfile().getId());
			
		}else if(Frame.getInstance().getLauncherPanel().isCrack() == true) {
			
			authInfos = new AuthInfos(username, "sry", "nope");	
			
		}
		
	}
	
	public static void update() throws Exception {
		
		//burlotbertrand@hotmail.fr
		
		File launcherf = new File("C:/Users/asus/AppData/Roaming/.SkyExpender");
		
		final SUpdate su = new SUpdate("http://crazycrafter.shambhalafm.fr/", launcherf);
		su.addApplication(new FileDeleter());
		uptadeThread = new Thread() {
		private int val;
		private int max;
		private int recherche = 0;
		private int charge = 0;
		
			@Override
			public void run() {
				
				while(!this.isInterrupted()) {
					
					if(BarAPI.getNumberOfDownloadedFiles() == 0) {
						
						if(recherche > -1 && recherche < 11) {
							Frame.getInstance().getLauncherPanel().setInfoText("Recherche des fichiers .  ");
							
						}else if(recherche > 10 && recherche < 21) {
							
							Frame.getInstance().getLauncherPanel().setInfoText("Recherche des fichiers .. ");
							
						}else if(recherche > 20 && recherche < 31) {
							
							Frame.getInstance().getLauncherPanel().setInfoText("Recherche des fichiers ...");
							
						}else if(recherche > 30 && recherche < 41) {
							
							Frame.getInstance().getLauncherPanel().setInfoText("Recherche des fichiers .. ");
							
						}else if(recherche > 40 && recherche < 51) {
							
							Frame.getInstance().getLauncherPanel().setInfoText("Recherche des fichiers .  ");
							
						}else if(recherche > 50 && recherche < 61){
							
							Frame.getInstance().getLauncherPanel().setInfoText("Recherche des fichiers    ");
							
						}else {
							recherche = 0;
						}
						
						charge++;
						
						if(charge == 1000000) {
							
							recherche++;
							charge = 0;
							
						}
						continue;
						
					}
					
					val = (int) (BarAPI.getNumberOfTotalDownloadedBytes() / 1000);
					max = (int) (BarAPI.getNumberOfTotalBytesToDownload() / 1000);
					
					Frame.getInstance().getLauncherPanel().getProgressBar().setMaximum(max);
					Frame.getInstance().getLauncherPanel().getProgressBar().setValue(val);
					
					Frame.getInstance().getLauncherPanel().setInfoText("Téléchargement des fichier " + 
					BarAPI.getNumberOfDownloadedFiles() + "/" + BarAPI.getNumberOfFileToDownload() + " (" + 
					Swinger.percentage(val, max) + "%)"
							+ " " + val +" octets télécharges");
				}
				
			}
			
		};
		uptadeThread.start();
		su.start();
		uptadeThread.interrupt();
		
	}
	
	public static void launch() throws LaunchException, IOException{
		
		Path usertxt = Paths.get("C:/Users/asus/AppData/Roaming/.SkyExpender/user.txt");
		
		ExternalLaunchProfile gameLauncher = MinecraftLauncher.createExternalProfile(LC_INFOS, GameFolder.BASIC, authInfos);
		
		ExternalLauncher p = new ExternalLauncher(gameLauncher);
		
		Frame.getInstance().setVisible(false);
		
		Process b = (Process) p.launch();
		
		try {
			b.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(Frame.getInstance().getLauncherPanel().isSavename() == true) {
			
			if(!Files.exists(usertxt)) {
				
				Files.createFile(usertxt);
				
			}
			
			File usertxtf = new File("C:/Users/asus/AppData/Roaming/.SkyExpender/user.txt");
			
			try(BufferedWriter userw = new BufferedWriter(new FileWriter(usertxtf))){
				
				userw.write("user : "+authInfos.getUsername());
				userw.newLine();
				
			}catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
		
		System.exit(0);
		
	}
	
	public static void interruptThread() {
		
		uptadeThread.interrupt();
		
	}
	
}
