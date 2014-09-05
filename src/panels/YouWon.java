package panels;

import aEngine2D.Driver;

public class YouWon extends Splash {

	@SuppressWarnings("unchecked")
	public YouWon(Driver game) {
		super(game);
		text = "You Won!!";
		NextPanel = 0;
		duration = 150;
	}


}
