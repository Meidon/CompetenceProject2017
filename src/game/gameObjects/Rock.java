package game.gameObjects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Rock extends staticObject {

	public Rock(int spawnX, int spawnY) {

		pos.set(spawnX, spawnY);
		width = 16;
		height = 16;
		rect = new Rectangle(pos.x, pos.y, width, height);

		try {
			objImage = new Image("res/obj/rock.png");
		} catch (SlickException e) {
			System.err.println("Missing Rock Texture");
		}
	}

}
