package game.util;

import game.GameManager;
import game.Window;
import game.map.Maploader;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

public class Menu extends GameManager {

	Circle mouseCheck = new Circle(0, 0, 1);
	Rectangle menuStart = new Rectangle(Window.Width / 3, 300, 175, 35);
	Rectangle menuSettings = new Rectangle(Window.Width / 3, 400, 125, 35);
	Rectangle settingCharacter = new Rectangle(Window.Width / 3, 300, 225, 35);
	Rectangle settingDifficulty = new Rectangle(Window.Width / 3, 400, 125, 35);
	Rectangle settingBack = new Rectangle(Window.Width / 3, 500, 85, 35);
	boolean isHoverSettings = false;
	boolean isHoverStart = false;
	boolean isHoverCharacter = false;
	boolean isHoverDifficulty = false;
	boolean isHoverBack = false;
	Image menuPic = null;
	
	public void menuScreen(GameContainer gc, Graphics mSG) {

		Input input = gc.getInput();
		mouseCheck.setCenterX(input.getMouseX());
		mouseCheck.setCenterY(input.getMouseY());
		mSG.setColor(Color.black);
		mSG.fill(mouseCheck);

		if (Maploader.lvl == 0 && Maploader.GameState == 0) {
			try {
				menuPic = new Image("res/images/Gauntlet_Title_Screen.png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
			mSG.drawImage(menuPic, 0, 0);
			mSG.setFont(gameFont);
			if (!isHoverStart) {
				mSG.setColor(Color.white);
				mSG.drawString("Start (Enter)", Window.Width / 3, 300);
				mSG.draw(menuStart);

			} else {
				mSG.setColor(Color.red);
				mSG.drawString("Start (Enter)", Window.Width / 3, 300);
				mSG.draw(menuStart);
			}

			if (!isHoverSettings) {
				mSG.setColor(Color.white);
				mSG.drawString("Settings", Window.Width / 3, 400);
				mSG.draw(menuSettings);
			} else {
				mSG.setColor(Color.red);
				mSG.drawString("Settings", Window.Width / 3, 400);
				mSG.draw(menuSettings);
			}

			if (input.isKeyDown(Input.KEY_ENTER)) {
				Maploader.LoadMap(0, 1);
				centerCamera();
			}
			if (menuStart.intersects(mouseCheck)
					|| menuStart.contains(mouseCheck)) {
				isHoverStart = true;
				if (input.isMouseButtonDown(0)) {
					Maploader.LoadMap(0, 1);
					centerCamera();

				}

			} else {
				isHoverStart = false;
			}

			if (menuSettings.intersects(mouseCheck)
					|| menuStart.contains(mouseCheck)) {
				isHoverSettings = true;
				if (input.isMouseButtonDown(0)) {
					Maploader.LoadMap(1, 0);
				}

			} else {
				isHoverSettings = false;
			}

		} else if (Maploader.lvl == 1 && Maploader.GameState == 0) {
			mSG.setFont(gameFont);
			if (!isHoverCharacter) {
				mSG.setColor(Color.white);
				mSG.drawString("Character Select", Window.Width / 3, 300);
				mSG.draw(settingCharacter);

			} else {
				mSG.setColor(Color.red);
				mSG.drawString("Character Select", Window.Width / 3, 300);
				mSG.draw(settingCharacter);
			}

			if (!isHoverDifficulty) {
				mSG.setColor(Color.white);
				mSG.drawString("Difficulty", Window.Width / 3, 400);
				mSG.draw(settingDifficulty);
			} else {
				mSG.setColor(Color.red);
				mSG.drawString("Difficulty", Window.Width / 3, 400);
				mSG.draw(settingDifficulty);
			}

			if (!isHoverBack) {
				mSG.setColor(Color.white);
				mSG.drawString("Back", Window.Width / 3, 500);
				mSG.draw(settingBack);
			} else {
				mSG.setColor(Color.red);
				mSG.drawString("Back", Window.Width / 3, 500);
				mSG.draw(settingBack);
			}

			if (settingCharacter.intersects(mouseCheck)
					|| settingCharacter.contains(mouseCheck)) {
				isHoverCharacter = true;
				if (input.isMouseButtonDown(0)) {
					System.out.println("Character Pressed");

				}

			} else {
				isHoverCharacter = false;
			}

			if (settingDifficulty.intersects(mouseCheck)
					|| settingDifficulty.contains(mouseCheck)) {
				isHoverDifficulty = true;
				if (input.isMouseButtonDown(0)) {
					System.out.println("Difficulty Pressed");

				}

			} else {
				isHoverDifficulty = false;
			}

			if (settingBack.intersects(mouseCheck)
					|| settingBack.contains(mouseCheck)) {
				isHoverBack = true;
				if (input.isMouseButtonDown(0)) {
					Maploader.LoadMap(0, 0);

				}

			} else {
				isHoverBack = false;
			}
		}

		if (Maploader.GameState == 0 && isPlayingMusic == false) {
			try {
				menuMusic = new Music("res/music/songA.wav");
			} catch (SlickException e) {
				e.printStackTrace();
			}
			menuMusic.setVolume(1f);
			menuMusic.loop();
			isPlayingMusic = true;
		}

	}
	
}
