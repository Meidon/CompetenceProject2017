import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Packages extends Entity {

	Image box;
	float speed = 0.2f;
	Rectangle BoxCollider;
	
	public Packages(int x, int y) {
		super(x,y);
		
		w = 32;
		h = 32;
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
		super.render(gc,g);
		box = new Image("res/obj/box1.png");
		g.drawImage(box,x,y);
		BoxCollider = new Rectangle(x,y,80,80);
		g.draw(BoxCollider);
	}
	
	public void update(GameContainer gc, int delta) throws SlickException {
		
		super.update(gc, delta);
	}
	
}
