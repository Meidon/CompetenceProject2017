package game.gameObjects;


import game.map.MapHandler;


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class GhostSpawner extends staticObject {

	public GhostSpawner(int spawnX, int spawnY) {

		pos.set(spawnX, spawnY);
		width = 16;
		height = 16;
		hp = 3;
		rect = new Rectangle(pos.x,pos.y,width, height);
		
		try {
			objImage = new Image("res/obj/ghostSpawner.png");
		} catch(SlickException e) {
			e.printStackTrace();
		}
	}

	public void spawner() {
		if(hp > 0 ) {
			float x = pos.x;
			float y = pos.y;
			MapHandler.ghosts.add(new Ghost((int)(x+16),(int)(y),0.03f));
		} else if(hp <= 0) {
			isDestroyed = true;
		}
	}
}
