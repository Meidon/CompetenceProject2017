import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class MenuState extends BasicGameState {

	private Circle mouseCheck;
	private Rectangle menuStart;
	private Rectangle menuSettings;
	private boolean isHoverStart = false;
	private boolean isHoverSettings = false;

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {

		mouseCheck = new Circle(0,0, 1);
		menuStart = new Rectangle(200,100,120,20);
		menuSettings = new Rectangle(200,200,275,20);
		
	}


	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		if(!isHoverStart) {
			g.setColor(Color.white);
			g.drawString("Start (Enter)", 200, 100);
			g.draw(menuStart);
			
		} else {
			g.setColor(Color.red);
			g.drawString("Start (Enter)", 200, 100);
			g.draw(menuStart);
		}
		
		if(!isHoverSettings) {
			g.setColor(Color.white);
			g.drawString("Settings (Not Implemented Yet)", 200, 200);
			g.draw(menuSettings);
		} else {
			g.setColor(Color.red);
			g.drawString("Settings (Not Implemented Yet)", 200, 200);
			g.draw(menuSettings);
		}

		g.setColor(Color.black);
		g.fill(mouseCheck);
		
	}


	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
	
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_ENTER)) {
			sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
		}
		
		mouseCheck.setCenterX(input.getMouseX());
		mouseCheck.setCenterY(input.getMouseY());
		
		if(menuStart.intersects(mouseCheck) || menuStart.contains(mouseCheck)) {
			isHoverStart = true;
			if(input.isMouseButtonDown(0)) {
				sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
			}
			
		} else {
			isHoverStart = false;
		}
		
		if(menuSettings.intersects(mouseCheck) || menuStart.contains(mouseCheck)) {
			isHoverSettings = true;
			
		} else {
			isHoverSettings = false;
		}
		
		
		
	}

	public int getID() {
		return 0;
	}

}
