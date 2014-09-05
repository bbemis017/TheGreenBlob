package aEngine2D;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class TextArea {
	public String text;
	
	public Font font;
	public Rectangle Area;
	public boolean highlighted;
	public Color higlightColor;
	public Color normalColor;
	private GamePanel gp;

	public TextArea(String text,Rectangle Area, Font font , boolean highlighted, Color highlightColor,Color normalColor,GamePanel gp){
		this.text = text;
		this.font = font;
		this.Area = Area;
		this.highlighted = highlighted;
		this.higlightColor = highlightColor;
		this.normalColor = normalColor;
		this.gp = gp;
	}
	
	public void mouseMoved(MouseEvent e){
		if( Area.contains( e.getPoint() ) ){
			
			highlighted = true;
		}
		else
			highlighted = false;
			
	}
	
	public boolean inArea(Point p){
		if( Area.contains(p) )
			return true;
		return false;
	}
	
	public void render(Graphics g){
		if(highlighted)
			g.setColor(higlightColor);
		else
			g.setColor(normalColor);
		
		g.setFont(font);
		g.drawString(text, (int)(Area.x-gp.bx), (int)(Area.y-gp.by));
	}
	
	public void showArea(Graphics g){
		g.setColor(Color.black);
		g.drawRect((int)(Area.x-gp.bx), (int)(Area.y-gp.by), Area.width, Area.height);
	}

}
