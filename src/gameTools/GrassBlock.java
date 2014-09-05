package gameTools;

import aEngine2D.GamePanel;
import aEngine2D.Sprite;
import aEngine2D.block;

public class GrassBlock extends block{

	public GrassBlock(int x, int y, GamePanel gp) {
		super(x, y, new Sprite(gp, 1, 1, 1, "block.png"), gp);
		
	}

	

}
