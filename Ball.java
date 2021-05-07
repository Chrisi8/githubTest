package src.githubTest;
import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	
	int MIN_Y;
	final int MAX_Y;
	int x;
	int y;
	int width;
	int height;
	Color color;
	boolean blnFalling = true;
	int panelWidth;
	
	public Ball(int x, int y, int width, int height, Color color, int MIN_Y, int MAX_Y) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.MIN_Y=MIN_Y;
		this.MAX_Y=MAX_Y;
		blnFalling = true;
	}
	
	public void setPanelWidth(int panelWidth) {
		this.panelWidth=panelWidth;
	}
	
	public void flyBall() {
		if(blnFalling==true) {
			if(y>=MAX_Y) {
				blnFalling=false;
				
				if(MIN_Y>MAX_Y) {
					MIN_Y=MAX_Y;
				}
				else MIN_Y=(int)(MIN_Y*2);
			}
			else if(MIN_Y+(MAX_Y-MIN_Y)*0.25<y){
				y+=width;
			}
			else if(MIN_Y+(MAX_Y-MIN_Y)*0.15<y){
				y+=width*0.75;
			}
			else if(MIN_Y+(MAX_Y-MIN_Y)*0.05<y){
				y+=width*0.5;
			}
			else {
				y+=width*0.25;
			}
		}
		else {
			if(y<=MIN_Y) {
				blnFalling=true;
			}
			else if(MIN_Y+(MAX_Y-MIN_Y)*0.25<y){
				y-=width;
			}
			else if(MIN_Y+(MAX_Y-MIN_Y)*0.15<y){
				y-=width*0.75;
			}
			else if(MIN_Y+(MAX_Y-MIN_Y)*0.05<y){
				y-=width*0.5;
			}
			else {
				y-=width*0.25;
			}
		}
		if(x<panelWidth-1) {
			x+=3;								
		}
		else {
			x=0;								
		}
	}
	
	public void paintBall(Graphics g) {
		g.setColor(color);
		if(y>=MAX_Y && MIN_Y+10<MAX_Y) {
			if(width==10) {
				width=11;
				height=(int) (height*0.75);
			}
			else {
				width=13;
				height=(int) (height*0.5);
			}
		}
		else {
			width=10;
			height=10;
		}
		g.fillOval(x, y, width, height);
	}

}
