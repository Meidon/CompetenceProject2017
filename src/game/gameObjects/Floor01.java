package game.gameObjects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Floor01 extends staticObject {

	public Floor01(int spawnX, int spawnY) {

		pos.set(spawnX, spawnY);
		width = 16;
		height = 16;

		try {
			objImage = new Image("res/Tiles/floorTile01.png");
		} catch (SlickException e) {
			System.err.println("Missing Floor01 Texture");
		}
	}

}
