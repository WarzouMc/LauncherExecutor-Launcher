package fr.shiwanMc.LauncherServGroup;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import fr.theshark34.openauth.AuthenticationException;
import fr.theshark34.openlauncherlib.util.Saver;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.event.SwingerEvent;
import fr.theshark34.swinger.event.SwingerEventListener;
import fr.theshark34.swinger.textured.STexturedButton;
import fr.theshark34.swinger.textured.STexturedProgressBar;

@SuppressWarnings("serial")
public class LauncherPanel extends JPanel implements SwingerEventListener {

	private Image backround = Swinger.getResource("backround.png");
	private Image backround2 = Swinger.getResource("backround2.png");
	private Image version = Swinger.getResource("gameversion.png");
	private BufferedImage ProgressBar = Swinger.getResource("ProgressBar1.png");
	private BufferedImage ProgressBar2 = Swinger.getResource("ProgressBar2.png");
	
	private Saver saver = new Saver(new File(Launcher.LC_DIR, "launcher.properties"));
	
	private JTextField usernameField = new JTextField(this.saver.get("username"));
	private JPasswordField passwordField = new JPasswordField();
	
	private JTextPane infop = new JTextPane();
	private JTextPane savenameT = new JTextPane();
	
	private STexturedButton playBotton = new STexturedButton(Swinger.getResource("play.png"));
	private STexturedButton quitBotton = new STexturedButton(Swinger.getResource("quit.png"));
	private STexturedButton hideBotton = new STexturedButton(Swinger.getResource("invisible.png"));
	private STexturedButton crackBoutton = new STexturedButton(Swinger.getResource("bouttonchoi.png"));
	private STexturedButton crackBoutton2 = new STexturedButton(Swinger.getResource("bouttonchoi2.png"));
	private STexturedButton savefalse = new STexturedButton(Swinger.getResource("savefalse.png"));
	private STexturedButton savetrue = new STexturedButton(Swinger.getResource("savetrue.png"));
	
	private STexturedProgressBar progressBar = new STexturedProgressBar(ProgressBar, ProgressBar2);
	private JLabel infoLabel = new JLabel("Clique sur 'play'", SwingConstants.CENTER);
	
	private int xb = -975;
	private int zb = -625;
	
	private boolean crack = false;
	private boolean savename = false;
	
	public LauncherPanel() throws IOException{

		this.setLayout(null);
		
		infop.setText("(adresse email si compte premium)");
		infop.setBounds(725, 200, 287, 47);
		infop.setDisabledTextColor(Color.WHITE);
		infop.setFont(infop.getFont().deriveFont(15f));
		infop.setEnabled(false);
		infop.setOpaque(false);
		
		this.add(infop);
		
		usernameField.setForeground(Color.WHITE);
		usernameField.setFont(usernameField.getFont().deriveFont(20f));
		usernameField.setCaretColor(Color.WHITE);
		usernameField.setOpaque(false);
		usernameField.setBorder(null);
		usernameField.setBounds(598, 229, 287, 47);
		
		Path usertxt = Paths.get("C:/Users/asus/AppData/Roaming/.SkyExpender/user.txt");
		File usertxtf = new File("C:/Users/asus/AppData/Roaming/.SkyExpender/user.txt");
		
		if(Files.exists(usertxt)) {
			
			try(BufferedReader usertxtr = new BufferedReader(new FileReader(usertxtf))){
				
				String namesave = usertxtr.readLine();
				namesave = namesave.substring(7);
				
				usernameField.setText(namesave);
				
			}catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		this.add(usernameField);
		
		passwordField.setForeground(Color.WHITE);
		passwordField.setFont(passwordField.getFont().deriveFont(20f));
		passwordField.setCaretColor(Color.WHITE);
		passwordField.setOpaque(false);
		passwordField.setBorder(null);
		passwordField.setBounds(336, 465, 287, 47);
		this.add(passwordField);
		
		playBotton.setBounds(200, 10);
		playBotton.addEventListener(this);
		playBotton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(playBotton);
		
		savefalse.setBounds(563, 229);
		savefalse.addEventListener(this);
		savefalse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(savefalse);
		
		savetrue.setBounds(563, 229);
		savetrue.addEventListener(this);
		savetrue.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		savenameT.setText("save");
		savenameT.setBounds(520, 229, 33, 25);
		savenameT.setDisabledTextColor(Color.WHITE);
		savenameT.setFont(savenameT.getFont().deriveFont(15f));
		savenameT.setEnabled(false);
		savenameT.setOpaque(false);
		
		this.add(savenameT);
		
		quitBotton.setBounds(100, 200);
		quitBotton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		quitBotton.addEventListener(this);
		this.add(quitBotton);
		
		hideBotton.setBounds(800, 50);
		hideBotton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hideBotton.addEventListener(this);
		this.add(hideBotton);
		
		crackBoutton.setBounds(100, 500);
		crackBoutton.addEventListener(this);
		crackBoutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		//this.add(crackBoutton);
		
		crackBoutton2.setBounds(100, 500);
		crackBoutton2.addEventListener(this);
		crackBoutton2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(crackBoutton2);
		
		progressBar.setBounds(5, 593, 965, 20);
		this.add(progressBar);
		
		infoLabel.setForeground(Color.WHITE);
		infoLabel.setFont(infoLabel.getFont().deriveFont(20f));
		infoLabel.setBounds(5, 560, 961, 25);
		this.add(infoLabel);
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEvent(SwingerEvent e) {

		if(e.getSource() == playBotton) {
			
			setFieldsEnabled(false);
			
			if(usernameField.getText().length() > 16 && crack == true) {
				
				JOptionPane.showMessageDialog(this, "Votre nom doit fair moin de 16 caractere");
				
				setFieldsEnabled(true);
				return;
				
			}
			
			if(usernameField.getText().replaceAll(" ", "").length() == 0 || passwordField.getText().length() == 0) {
				
				JOptionPane.showMessageDialog(this, "Erreur, verifiez vos identifiant", "ERREUR", JOptionPane.ERROR_MESSAGE);
				
				setFieldsEnabled(true);
				return;
			}
			
			Thread t = new Thread() {
				
				@Override
				public void run() {
					
					while(true) {
						
						if(crack == false) {
							try {
								Launcher.auth(usernameField.getText(), passwordField.getText());
							} catch (AuthenticationException e) {
								JOptionPane.showMessageDialog(LauncherPanel.this, "Erreur d'authentification, verrifier vos identifiant ou activer le mod crack", "",JOptionPane.ERROR_MESSAGE);
								setFieldsEnabled(true);
								e.printStackTrace();
								return;
							}
						}else if(crack == true) {
							try {
								Launcher.auth(usernameField.getText(), "");
							} catch (AuthenticationException e) {
								setFieldsEnabled(true);
								e.printStackTrace();
								return;
							}	
						}
							
						try {
							Launcher.update();
						} catch (Exception e1) {
							Launcher.interruptThread();
							JOptionPane.showMessageDialog(LauncherPanel.this, "Erreur, Impossible mettre à joure : "+e, "ERREUR", JOptionPane.ERROR_MESSAGE);
							setFieldsEnabled(true);
							return;
						}
						
						try {
							Launcher.launch();
						} catch (Exception e3) {

							JOptionPane.showMessageDialog(LauncherPanel.this, "Erreur, Impossible lancer le jeu : "+e, "ERREUR", JOptionPane.ERROR_MESSAGE);
							setFieldsEnabled(true);
						}
					
					}
				
				}
					
			};
			
			t.start();
			
		}
		
		if(e.getSource() == quitBotton) {
			
			System.exit(0);
			
		}
		
		if(e.getSource() == hideBotton) {
			
			Frame.getInstance().setState(JFrame.ICONIFIED);
			
		}
		
		if(e.getSource() == crackBoutton2) {
			
			this.remove(crackBoutton2);
			this.add(crackBoutton);
			crack = true;
			
		}else if(e.getSource() == crackBoutton) {
			
			this.remove(crackBoutton);
			this.add(crackBoutton2);
			
			crack = false;
			
		}
		
		if(e.getSource() == savefalse) {
			
			this.remove(savefalse);
			this.add(savetrue);
			
			setSavename(true);
			
		}else if(e.getSource() == savetrue) {
			
			this.remove(savetrue);
			this.add(savefalse);
			
			setSavename(false);
			
		}
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		xb = Frame.getInstance().getLauncherPanel().getXb();
		
		g.drawImage(backround2, xb, zb, 1950, 1250, this);
		
		g.drawImage(backround, 0, 0, this.getWidth(), this.getHeight(), this);
		
		g.drawImage(version, 20, 500, 200, 200, this);
		
	}
	
	private void setFieldsEnabled(boolean enabled) {
		usernameField.setEnabled(enabled);
		passwordField.setEnabled(enabled);
		playBotton.setEnabled(enabled);
		crackBoutton.setEnabled(enabled);
		crackBoutton2.setEnabled(enabled);
		quitBotton.setEnabled(enabled);
		savefalse.setEnabled(enabled);
		savetrue.setEnabled(enabled);
	}
	
	public STexturedProgressBar getProgressBar() {
		
		return progressBar;
		
	}
	
	public void setInfoText(String text) {
		
		infoLabel.setText(text);
		
	}

	public int getXb() {
		return xb;
	}

	public void setXb(int xb) {
		this.xb = xb;
	}

	public int getZb() {
		return zb;
	}

	public void setZb(int zb) {
		this.zb = zb;
	}

	public boolean isCrack() {
		return crack;
	}

	public void setCrack(boolean crack) {
		this.crack = crack;
	}

	public boolean isSavename() {
		return savename;
	}

	public void setSavename(boolean savename) {
		this.savename = savename;
	}
	
}
