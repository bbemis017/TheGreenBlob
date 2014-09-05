package gameTools;

import java.awt.Graphics;

import aEngine2D.GamePanel;
import aEngine2D.Sprite;
import aEngine2D.Thing;

public class ArrowSign extends Thing{
	
	Sprite sprite;

	public ArrowSign(int x, int y, GamePanel gp) {
		super(x, y, gp);
		sprite = new Sprite(gp, .25, .25, 1, "sign.png");
		setMask(sprite.width*sprite.xscale, sprite.yscale*sprite.height);
	}
	
	
	@Override
	public void Step() {
		super.Step();
		if(gp.man !=null && gp.man.alive){
			if(gp.man.getX() > this.getX() + (sprite.width*sprite.xscale) )
				gp.nextPanel();
		}
	}
	
	@Override
	public void render(Graphics g) {
		sprite.render(g, (int)getBaseX(), (int)getBaseY());
	}

}
