package game.util;

import game.GameManager;
import game.Window;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class End extends GameManager {

	public void endScreen(Graphics endScreenGraphics) {
		Image endScreenPic = null;
		try {
			endScreenPic = new Image("res/images/GameOver.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		endScreenGraphics.drawImage(endScreenPic, Window.Width / 4, 50);
		endScreenGraphics.setColor(Color.red);
		endScreenGraphics.setFont(gameFont);
		endScreenGraphics.drawString("You Died.", Window.Width / 2.5f, 150);
		endScreenGraphics.drawString("Game Over", Window.Width / 2.5f, 250);
		endScreenGraphics.drawString("You got " + score + " points",
				Window.Width / 2.5f, 350);
		endScreenGraphics.drawString("Press R To Restart", Window.Width / 2.5f,
				450);
	}
	
	public void victoryScreen(Graphics vSG) {
		Image vSP = null;
		
		try {
			vSP = new Image("res/images/BarbarianVictoryScreen.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		vSG.drawImage(vSP, Window.Width / 6, 200);
		vSG.setColor(Color.blue);
		vSG.setFont(gameFont);
		vSG.drawString("You Won.", Window.Width / 2.5f, 150);
		vSG.drawString("Thanks for Playing", Window.Width / 2.5f, 250);
		vSG.drawString("You got " + score + " points",
				Window.Width / 2.5f, 350);
		vSG.drawString("Press Escape To Return to Menu", Window.Width / 2.5f,
				450);
	}
}
