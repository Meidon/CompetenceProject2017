package game.gameObjects;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public class staticObject extends GameObject {

	public int hp;
	public Image objImage;
	public Shape rect;
	public boolean isDestroyed;

	public staticObject() {
		pos.set(0, 0);
		hp = 0;

	}

	void staticDie() {
		isDestroyed = true;
	}

}
