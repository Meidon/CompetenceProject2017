import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class MainSetup extends StateBasedGame {

	public MainSetup(String title) {
		super(title);
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new MainSetup("Procedural Sokoban"));
		app.setDisplayMode(800, 800, false);
		app.setAlwaysRender(true);
		app.setTargetFrameRate(60);
		
		app.start();

	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.addState(new PlayState(3));
		
	}

}
