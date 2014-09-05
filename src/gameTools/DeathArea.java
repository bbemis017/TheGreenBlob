package gameTools;

import java.awt.Color;
import java.awt.Graphics;

import aEngine2D.GamePanel;
import aEngine2D.block;

public class DeathArea extends block{

	public DeathArea(int x, int y, int width, int height, boolean visible,GamePanel gp) {
		super(x, y, width, height,visible, gp);
		
	}
	
	@Override
	public void Step() {
		
		if(gp.man != null && this.collidesWith(gp.man) ){
			gp.man.Death();
		}
		
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int)getBaseX(), (int)getBaseY(), (int)width, (int)height);
	
	}

}
