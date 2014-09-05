package aEngine2D;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Man extends Thing {

	Sprite sprite, deathSprite;
	double size = .5;
	int JumpNum = 0;
	public boolean alive = true, countDownCalled = false;
	public int lives = 3;

	public Man(int x, int y, GamePanel gp) {
		super(x, y, gp);
		size = .5;
		deathSprite = new Sprite(gp, 1, 1, 1, "OvalMan/A2.png",
				"OvalMan/B2.png", "OvalMan/C2.png", "OvalMan/D2.png");
		deathSprite.setSpeed(.2);
		deathSprite.freezeFrame = 3;
		sprite = new Sprite(gp, 1, 1, 1, "OvalMan/A.png", "OvalMan/B.png",
				"OvalMan/C.png", "OvalMan/D.png", "OvalMan/E.png");
		setMask(sprite.width * size, sprite.height * size);
		sprite.setSpeed(.15);

	}

	public void setPanel(GamePanel gp) {
		this.gp = gp;
	}

	public void Death() {
		alive = false;

	}

	public void addBlobium() {
		if (size + .5 <= 1.5) {
			vy -= sprite.height * .5;
			size += .5;
			setMask(sprite.width * size, sprite.height * size);
		}
		else
			++lives;
	}

	public void removeBlobium() {
		size -= .5;
		if (size > 0)
			setMask(sprite.width * size, sprite.height * size);
		if (size <= 0) {

			Death();
		}
	}

	@Override
	public void Step() {
		super.Step();
		if (alive) {
			if (vspeed == 0 && JumpNum > 0) {
				JumpNum = 0;
				sprite.frame = 3;
			}

			if (hspeed == 0)
				sprite.freeze = true;
			else
				sprite.freeze = false;
		}

		gravity();
		scrolling();

		if (!alive) {
			deathSprite.freeze = true;
			if (deathSprite.frame == 3 && !countDownCalled) {
				
				countDownCalled = true;
				gp.countDown();
			}
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (alive) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				if (JumpNum < 2) {
					sprite.frame = 4;
					sprite.freeze = true;
					vspeed = -10;
					JumpNum += 1;
				}
				break;

			case KeyEvent.VK_RIGHT:
				hspeed = 4;
				sprite.orientation = 1;
				break;
			case KeyEvent.VK_LEFT:
				hspeed = -4;
				sprite.orientation = -1;
				break;

			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			hspeed = 0;
			break;
		case KeyEvent.VK_LEFT:
			hspeed = -0;
			break;

		}
	}

	@Override
	public void render(Graphics g) {

		if (alive) {
			sprite.xscale = size;
			sprite.yscale = size;
			setMask(sprite.width * size, sprite.height * size);
			sprite.render(g, (int) getBaseX(), (int) getBaseY());
		} else {
			deathSprite.xscale = .5;
			deathSprite.yscale = .5;
			deathSprite.render(g, (int) getBaseX(), (int) getBaseY());
		}

	}

}
