package gameTools;

import aEngine2D.GamePanel;
import aEngine2D.Sprite;
import aEngine2D.block;

public class FloatingBlock extends block{

	public FloatingBlock(int x, int y, double length,GamePanel gp) {
		super(x, y, new Sprite(gp, length, .25, 1, "FloatingBlock.png"), gp);
		setMask(sprite.width*sprite.xscale, sprite.height*sprite.yscale);
	}

}
