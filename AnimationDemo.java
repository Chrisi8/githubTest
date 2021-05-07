package src.githubTest;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
   
public class AnimationDemo extends JFrame{
int i;
	
	
	private class AnimationPanel extends JPanel{
		
		
		public AnimationPanel() {
			super();
			setDoubleBuffered(true);
		}
		
		protected final int BALL_WIDTH=10;
		private final int MIN_Y = 10;
		private final int MAX_Y = 240;
		
		private int iCurrentX=0;
		private int  iCurrentY=MIN_Y;
//		private boolean blnFalling=true;
		private int zähler=0;
		Ball[] balls = new Ball[5];
		Ball b1 = new Ball(iCurrentX, iCurrentY, BALL_WIDTH, BALL_WIDTH, Color.RED, MIN_Y, MAX_Y);
		Ball b2 = new Ball(iCurrentX, iCurrentY, BALL_WIDTH, BALL_WIDTH, Color.BLUE, MIN_Y, MAX_Y);
		Ball b3 = new Ball(iCurrentX, iCurrentY, BALL_WIDTH, BALL_WIDTH, Color.GREEN, MIN_Y, MAX_Y);
		Ball b4 = new Ball(iCurrentX, iCurrentY, BALL_WIDTH, BALL_WIDTH, Color.YELLOW, MIN_Y, MAX_Y);
		Ball b5 = new Ball(iCurrentX, iCurrentY, BALL_WIDTH, BALL_WIDTH, Color.BLACK, MIN_Y, MAX_Y);
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int yLine=MAX_Y+BALL_WIDTH+1;								
			g.drawLine(0, yLine,this.getWidth()-1,yLine);
			
			b1.setPanelWidth(this.getWidth());
			b1.paintBall(g);
			b1.flyBall();
			
			if(zähler>10) {
				b2.setPanelWidth(this.getWidth());
				b2.paintBall(g);
				b2.flyBall();
			}
			if(zähler>20) {
				b3.setPanelWidth(this.getWidth());
				b3.paintBall(g);
				b3.flyBall();
			}
			if(zähler>30) {
				b4.setPanelWidth(this.getWidth());
				b4.paintBall(g);
				b4.flyBall();
			}
			if(zähler>40) {
				b5.setPanelWidth(this.getWidth());
				b5.paintBall(g);
				b5.flyBall();
			}
			
			zähler++;
	
//			g.setColor(Color.RED);
//			g.fillOval(iCurrentX, iCurrentY, BALL_WIDTH, BALL_WIDTH);
			
//			if(blnFalling==true) {
//				if(iCurrentY>=MAX_Y) {
//					blnFalling=false;
//				}
//				else {
//					iCurrentY+=BALL_WIDTH;
//				}
//			}
//			else {
//				if(iCurrentY<=MIN_Y) {
//					blnFalling=true;
//				}
//				else {
//					iCurrentY-=BALL_WIDTH;
//				}
//			}
//			if(iCurrentX<this.getWidth()-1) {
//				iCurrentX+=3;								
//			}
//			else {
//				iCurrentX=0;								
//			}
			

			
		}
		
	}
	
	public AnimationDemo(String s) {
		super(s);
		AnimationPanel panel = new AnimationPanel();
		this.getContentPane().add(panel, java.awt.BorderLayout.CENTER);  //hier kann man this.getContentPane(). auch weglassen
		Timer t = new Timer(100,new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				repaint();
			}
		});
		JButton button = new JButton("Start");
		add(button, java.awt.BorderLayout.SOUTH);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//mit was wird hier dirn this angesprochen?
				if(button.getText().equals("Start")) {
					t.start();
					button.setText("Stop");
				}
				else {
					t.stop();
					button.setText("Start");
				}
					
			}
		});
		
		
		setSize(500,500);
		setVisible(true); 
	}
	
	
	public static void main(String[] args)throws Exception{
		new AnimationDemo("AnimationPanel");
	}

}

