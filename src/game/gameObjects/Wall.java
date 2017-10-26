package game.gameObjects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Wall extends staticObject {

	public Wall(int spawnX, int spawnY) {

		pos.set(spawnX, spawnY);
		width = 16;
		height = 16;
		rect = new Rectangle(pos.x, pos.y, width, height);

		try {
			objImage = new Image("res/obj/wall.png");
		} catch (SlickException e) {
			System.err.println("Missing Wall Texture");
		}
	}

}
