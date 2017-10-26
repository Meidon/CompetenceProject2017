package game.gameObjects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Floor02 extends staticObject {

	public Floor02(int spawnX, int spawnY) {

		pos.set(spawnX, spawnY);
		width = 16;
		height = 16;

		try {
			objImage = new Image("res/Tiles/floorTile02.png");
		} catch (SlickException e) {
			System.err.println("Missing Floor02 Texture");
		}
	}
}
