import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class PlayState extends BasicGameState {

	int stateID = 3;
	Image background;
	Player player;
	Packages box;
	float x = 800f;
	float y = 800f;
	float speed = 0.2f;
	boolean quit = false;
	
	public PlayState(int stateID) {
		
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg1) throws SlickException {
	
		background = new Image("res/background.jpg");
		player = new Player(400,400);
		box = new Packages(150,150);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg,
			org.newdawn.slick.Graphics g) throws SlickException {
		// TODO Auto-generated method stub
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

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
		Input input = gc.getInput();
		
		if(input.isKeyDown(Input.KEY_ESCAPE)) {
			quit = true;
		}
		
		if(quit == true) {
			if(input.isKeyDown(Input.KEY_R)) {
				quit = false;
			}
			if(input.isKeyDown(Input.KEY_M)) {
				sbg.enterState(0);
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
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}
}


