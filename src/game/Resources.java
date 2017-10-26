package game;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Resources {

	private static Map<String, Image> images;

	public Resources() {
		images = new HashMap<String, Image>();

		try {
			images.put("lvl0", loadImage("res/images/levels/lvl0.png"));
			images.put("lvl1", loadImage("res/images/levels/lvl1.png"));
			images.put("lvl2", loadImage("res/images/levels/lvl2.png"));
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	public static Image loadImage(String path) throws SlickException {
		return new Image(path, false, Image.FILTER_NEAREST);
	}

	public static Image getImage(String getter) {
		return images.get(getter);
	}
}
