import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public abstract class Entity {

	protected int x, y, w, h, dx, dy;
	Rectangle EntityCollision;
	
	public Entity(int x, int y) {
		
		this.x = x;
		this.y = y;
		dx = 0;
		dy = 0;
		w = 32;
		h = 32;
	}
	
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
	}
	
	public void update(GameContainer gc, int delta) throws SlickException {
		
	}
	
	
	
}
