import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class Player extends Entity {

	Image playerOne;
	float speed = 0.2f;
	
	public Player(float x, float y) {
		super((int)x, (int) y);
		
		w = 32;
		h = 32;
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
			
		super.render(gc,g);
		playerOne = new Image("res/char/playerOne.png");
		g.drawImage(playerOne, x, y);
	}
	
	public void update(GameContainer gc, int delta) throws SlickException {
		
		super.update(gc,delta);
		Input input = gc.getInput();
		//Move Up
		if(input.isKeyDown(Input.KEY_W)) {
			y -= speed * delta;
			if(y < 0) {
				y+= speed * delta;
			}
		}
		//Move Down
		if(input.isKeyDown(Input.KEY_S)) {
			y += speed * delta;
			if(y > 800) {
				y -= speed * delta;
			}
		}
		//Move Left
		if(input.isKeyDown(Input.KEY_A)) {
			x -= speed * delta;
			if(x < 0) {
				x += speed * delta;
			}
		}
		//Move Right
		if(input.isKeyDown(Input.KEY_D)) {
			x += speed * delta;
			if(x > 800) {
				x -= speed * delta;
			}
		}
	}
	
}
