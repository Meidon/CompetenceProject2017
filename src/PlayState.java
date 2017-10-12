import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class PlayState extends BasicGameState {


	Image background;
	Player player;
	Packages box;
	float x = 800f;
	float y = 800f;
	float speed = 0.2f;
	boolean quit = false;
	

	public void init(GameContainer gc, StateBasedGame sbg1) throws SlickException {
	
		background = new Image("res/background.jpg");
		player = new Player(400,400);
		box = new Packages(150,150);
	}
	

	public void render(GameContainer gc, StateBasedGame sbg,
			org.newdawn.slick.Graphics g) throws SlickException {
		g.drawImage(background, 0,0);
		player.render(gc,g);
		box.render(gc,g);
		
		if(quit == true) {
			g.drawString("Resume (R)", 250, 100);
			g.drawString("Main Menu (M)", 250, 125);
			g.drawString("Quit Game (Q)", 250, 150);
			if(quit == false) {
				g.clear();
			}
		}
	}


	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		Input input = gc.getInput();
		
		if(input.isKeyDown(Input.KEY_ESCAPE)) {
			quit = true;
		}
		
		if(quit == true) {
			if(input.isKeyDown(Input.KEY_R)) {
				quit = false;
			}
			if(input.isKeyDown(Input.KEY_M)) {
				sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(input.isKeyDown(Input.KEY_Q)) {
				System.exit(0);
			}
		}
		player.update(gc, delta);
		box.update(gc, delta);
		
	}
	

	public int getID() {
		return 2;
	}
}


