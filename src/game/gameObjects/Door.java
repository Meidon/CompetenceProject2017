package game.gameObjects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Door extends staticObject {

	public boolean isW = false;
	public boolean isH = false;
	public boolean isOpen = false;

	public Door(int spawnX, int spawnY, boolean WORH) {

		pos.set(spawnX, spawnY);
		hp = 2;
		if (WORH == true) {
			isW = true;
			isH = false;
		} else if (WORH == false) {
			isH = true;
			isW = false;
		}

		if (isW == true) {
			width = 32;
			height = 16;
			try {
				objImage = new Image("res/obj/door/doorClosedW.png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}

		if (hp > 0 && isH == true) {
			width = 16;
			height = 32;

			try {
				objImage = new Image("res/obj/door/doorClosedH.png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		rect = new Rectangle(pos.x, pos.y, width, height);
	}

	public void CheckIfOpen(boolean open) {

		if (open == true && isW == true) {
			try {
				objImage = new Image("res/obj/door/doorOpenW.png");
			} catch (SlickException e) {
				e.printStackTrace();
			}

			isOpen = true;
		}

		if (open == true && isH == true) {
			try {
				objImage = new Image("res/obj/door/doorOpenH.png");
			} catch (SlickException e) {
				e.printStackTrace();
			}

			isOpen = true;
		}

	}
}
