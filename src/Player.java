import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Player extends Entity {

	Image playerOne;
	float speed = 200f;
	Rectangle playerCollider;
	
	public Player(float x, float y) {
		super((int)x, (int) y);
		
		w = 32;
		h = 32;
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
			
		super.render(gc,g);
		playerOne = new Image("res/char/playerOne.png");
		g.drawImage(playerOne, x, y);
		playerCollider = new Rectangle(x,y, 80,80);
		g.draw(playerCollider);
		
	}
	
	public void update(GameContainer gc, int delta) throws SlickException {
		
		super.update(gc,delta);
		Input input = gc.getInput();
		//Move Up
		if(input.isKeyDown(Input.KEY_W)) {
			y -= speed/1000.0f * delta;
			if(y < 0) {
				y+= speed/1000.0f * delta;
			}
		}
		//Move Down
		if(input.isKeyDown(Input.KEY_S)) {
			y += speed/1000.0f * delta;
			if(y > gc.getScreenHeight()) {
			y += speed/1000.0f * delta;
			}
		}
		//Move Left
		if(input.isKeyDown(Input.KEY_A)) {
			x -= speed/1000.0f * delta;
			if(x < 0) {
				x += speed/1000.0f * delta;
			}
		}
		//Move Right
		if(input.isKeyDown(Input.KEY_D)) {
			x += speed/1000.0f * delta;
			if(x > gc.getScreenWidth()) {
				x -= speed/1000.0f * delta;
			}
		}
	}
	
}
