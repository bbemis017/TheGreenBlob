package gameTools;

import java.awt.Graphics;

import aEngine2D.GamePanel;
import aEngine2D.Sprite;
import aEngine2D.block;

public class IceBlock extends block{

	public IceBlock(int x, int y,GamePanel gp) {
		super(x, y, new Sprite(gp, .25, .25, 1, "IceBlock.png"), gp);
		setMask(100,100);
		
	}
	
	@Override
	public void characterCollision() {
		super.characterCollision();
		gp.man.removeBlobium();
	}
	
	@Override
	public void render(Graphics g) {
		sprite.render(g, (int)getBaseX(), (int)getBaseY() );
	}

}
