package fr.shiwanMc.LauncherServGroup;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Mouse extends JFrame implements MouseMotionListener {

	private int xm;
	private int zm;

	@Override
	public void mouseDragged(MouseEvent e) {
		
		xm = e.getX()-975;
		zm = e.getY()-625;
		
		Frame.getInstance().getLauncherPanel().setXb(xm);
		Frame.getInstance().getLauncherPanel().setZb(zm);
		Frame.getInstance().getLauncherPanel().repaint();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		xm = e.getX()-975;
		zm = e.getY()-625;
		
		Frame.getInstance().getLauncherPanel().setXb(xm);
		Frame.getInstance().getLauncherPanel().setZb(zm);
		Frame.getInstance().getLauncherPanel().repaint();
		
	}

}
