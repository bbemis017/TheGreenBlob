package gameTools;

import aEngine2D.GamePanel;
import aEngine2D.Sprite;
import aEngine2D.block;

public class TanBlock extends block{
	
	public TanBlock(int x, int y, GamePanel gp) {
		super(x, y, new Sprite(gp, .5, .5, 1, "block2.png"), gp);
		setMask(sprite.width*sprite.xscale, sprite.height*sprite.yscale);
		
	}
}
