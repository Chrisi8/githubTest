package src.githubTest;
import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

public class MovingEye extends JFrame{

	private class MovingEyePanel extends JPanel implements MouseMotionListener{
		
		boolean _blnReacting = true;
		public MovingEyePanel() {
			super();
			setDoubleBuffered(true);
			this.addMouseMotionListener(MovingEyePanel.this);
		}
		
		public boolean getReacting() {
			return _blnReacting;
		}
		
		public void setReacting(boolean bln) {
			_blnReacting=bln;
		}
		
		private final int RADIUS_EYES=50;
		private final int RADIUS_PUPIL=(int)(0.2*RADIUS_EYES);
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int mEyeX=this.getWidth()/2;		//ist mit this das Panel hier gemeint?
			int mEyeY=this.getHeight()/2;
			
			this.drawCircle(g,mEyeX, mEyeY, RADIUS_EYES);
			    
			int distanceX = _mouseX-mEyeX;
			int distanceY = _mouseY-mEyeY;
			double distance = Math.sqrt(distanceX*distanceX+distanceY*distanceY);
			
			int mPupilX, mPupilY;
			if(distance <= 0.001) {
				mPupilX = RADIUS_EYES-RADIUS_PUPIL;
				mPupilY = 0;
			}
			else if(distance>RADIUS_EYES) {
				mPupilX = (int) (distanceX / distance * RADIUS_EYES * 0.8);
				mPupilY = (int) (distanceY / distance * RADIUS_EYES * 0.8);
				this.fillCircle(g, mEyeX+mPupilX,mEyeY+mPupilY, RADIUS_PUPIL);
			}
			
			else {
				this.paintAugenLid(g, mEyeX, mEyeY, RADIUS_EYES);
			}
			

			
		}
		
		public void drawCircle(Graphics g, int x, int y, int radius) {
			g.drawOval(x-radius, y-radius, radius+radius, radius+radius);
		}
		
		public void fillCircle(Graphics g, int x, int y, int radius) {
			g.fillOval(x-radius, y-radius, radius+radius, radius+radius);
		}
		
		public void paintAugenLid(Graphics g, int x, int y, int radius) {
			//g.drawLine(x-radius, y, x+radius, y);
			g.drawArc(x-radius-20, y-radius*2-10, radius*11/4, radius*5/2, 225, 90);
		}
		
		int _mouseX=0;
		int _mouseY=0;
		
		public void mouseMoved(MouseEvent e) {
			_mouseX=e.getX();
			_mouseY=e.getY();
			
			if(_blnReacting==true) {
				repaint();
			}
		}
		public void mouseDragged(MouseEvent e) {}
		 
		
	}
	
	public MovingEye(String s) {
		super(s);
		MovingEyePanel panel = new MovingEyePanel();
		this.getContentPane().add(panel, java.awt.BorderLayout.CENTER);  //hier kann man this.getContentPane(). auch weglassen
		setSize(500,500);
		setVisible(true); 
	}
	
	public static void main(String[] args)throws Exception{
		new MovingEye("MovingEye");
	}
}
