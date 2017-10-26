package game;

import game.map.*;
import game.util.End;
import game.util.Menu;
import game.gameObjects.*;

import java.awt.Font;
//import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;

public class GameManager extends BasicGame {

	public static int Lives = 8;
	public static int Score = 0;
	public int GameStates = 0;
	public boolean isPlayingMusic = false;
	public MapHandler MapRef;
	public Menu menu;
	public End end;
	public int MaxLevel = 10;
	public Random rnd = new Random();
	public boolean isBackground = false;
	public int rndBackground = 0;

	public static ArrayList<Projectile> Projectiles;

	public int delay = 0;
	protected int deltaTime;

	public static Font defaultFont;
	public static TrueTypeFont gameFont, smallPrint;
	protected int score;
	private Image GUIheart;

	private Camera cam = new Camera();

	protected static Music musicA;
	protected static Music musicB;
	protected static Music musicC;
	protected static Music deathMusic;
	protected static Music menuMusic;
	private static Sound ShootSound;
	private static Sound MonsterIsHit;
	private static Sound PlayerIsHit;
	private static Sound DoorSound;

	public static GameContainer gc;

	public GameManager() {
		super("Procedural Gauntlet II");
	}

	public static void main(String[] args) throws SlickException {
//		File f = new File("natives");
//		if (f.exists())
//			System.setProperty("org.lwjgl.librarypath", f.getAbsolutePath());

		try {
			AppGameContainer app = new AppGameContainer(new GameManager());
			app.setDisplayMode(Window.Width, Window.Height, false);
			app.start();

		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void init(GameContainer gc) throws SlickException {

		gc.setMaximumLogicUpdateInterval(60);
		gc.setTargetFrameRate(60);
		gc.setAlwaysRender(true);
		gc.setShowFPS(false);
		gc.setVSync(true);
		new Resources();
		new Maploader();
		MapRef = Maploader.Map;
		menu = new Menu();
		end = new End();

		ShootSound = new Sound("res/SFX/ShootSound.wav");
		DoorSound = new Sound("res/SFX/DoorSound.wav");
		PlayerIsHit = new Sound("res/SFX/PlayerIsHit.wav");
		MonsterIsHit = new Sound("res/SFX/MonsterIsHit.wav");

		defaultFont = new Font("Verdana", Font.BOLD, 24);
		GUIheart = new Image("res/images/heart.png");
		score = 0;
		gameFont = new TrueTypeFont(defaultFont, true);
		smallPrint = new TrueTypeFont(defaultFont, true);

		Projectiles = new ArrayList<Projectile>();

	}

	public void render(GameContainer gc, Graphics g) throws SlickException {

		if (Maploader.GameState == 0) {
			menu.menuScreen(gc, g);
			if (MapHandler.IsMapInitialized == true) {
				MapRef.EmptyMap();
				isBackground = false;
			}
		}

		if (Maploader.GameState == 1) {

			// drawBackground();
			drawStaticObjects();
			drawPlayer();
			drawEnemies();
			drawProjectiles();
			drawGUI();
			centerCamera();
			menu.isPlayingMusic = false;
		}

		if (Maploader.GameState == 2 && Maploader.lvl == 0) {
			end.endScreen(g);
		} else if (Maploader.GameState == 2 && Maploader.lvl == 1) {
			end.victoryScreen(g);
		}
	}

	public void update(GameContainer gc, int delta) throws SlickException {
		deltaTime = delta;

		if (Maploader.GameState == 1) {
			projectileHandling();
			collisionManagement();
			deathHandling();
			enemyActions();
		}

		inputHandling(gc);
	}

	private void collisionManagement() {

		MapHandler.playerOne.rect.setLocation(cam.camX
				+ MapHandler.playerOne.pos.x, cam.camY
				+ MapHandler.playerOne.pos.y);
		MapHandler.exitDoor.rect.setLocation(cam.camX
				+ MapHandler.exitDoor.pos.x, cam.camY
				+ MapHandler.exitDoor.pos.y);

		if (MapHandler.exitDoor.rect.intersects(MapHandler.playerOne.rect)
				&& MapHandler.exitDoor.isOpen == true) {
			endLevel();
		}

		for (int i = 0; i < MapHandler.walls.size(); i++) {
			MapHandler.walls.get(i).rect.setLocation(cam.camX
					+ MapHandler.walls.get(i).pos.x, cam.camY
					+ MapHandler.walls.get(i).pos.y);
		}

		for (int i = 0; i < MapHandler.rocks.size(); i++) {
			MapHandler.rocks.get(i).rect.setLocation(cam.camX
					+ MapHandler.rocks.get(i).pos.x, cam.camY
					+ MapHandler.rocks.get(i).pos.y);
		}

		for (int i = 0; i < MapHandler.ghostSpawner.size(); i++) {
			MapHandler.ghostSpawner.get(i).rect.setLocation(cam.camX
					+ MapHandler.ghostSpawner.get(i).pos.x, cam.camY
					+ MapHandler.ghostSpawner.get(i).pos.y);
		}

		for (int i = 0; i < MapHandler.grunts.size(); i++) {
			MapHandler.grunts.get(i).rect.setLocation(cam.camX
					+ MapHandler.grunts.get(i).pos.x, cam.camY
					+ MapHandler.grunts.get(i).pos.y);

			if (MapHandler.grunts.get(i).rect
					.intersects(MapHandler.playerOne.rect)) {
				MapHandler.grunts.get(i).isDead = true;
				if (!MapHandler.playerOne.isInvulnerable) {
					MapHandler.playerOne.isInvulnerable = true;
					MapHandler.playerOne.hp -= 2;
					PlayerIsHit.play();
				}

			}
		}

		for (int i = 0; i < MapHandler.ghosts.size(); i++) {
			MapHandler.ghosts.get(i).rect.setLocation(cam.camX
					+ MapHandler.ghosts.get(i).pos.x, cam.camY
					+ MapHandler.ghosts.get(i).pos.y);

			if (MapHandler.ghosts.get(i).rect
					.intersects(MapHandler.playerOne.rect)) {
				MapHandler.ghosts.get(i).isDead = true;
				if (!MapHandler.playerOne.isInvulnerable) {
					MapHandler.playerOne.isInvulnerable = true;
					MapHandler.playerOne.hp--;
					PlayerIsHit.play();
				}
			}
		}
		MapHandler.playerOne.isInvulnerable = false;
	}

	private void endLevel() {

		MapRef.EmptyMap();
		isBackground = false;
		Maploader.LoadMap(Maploader.lvl += 1, 1);

		int increment = 1;
		increment++;

		for (int i = 0; i < MapHandler.ghosts.size(); i++) {
			MapHandler.ghosts.get(i).hp += increment;
		}

		for (int i = 0; i < MapHandler.grunts.size(); i++) {
			MapHandler.grunts.get(i).hp += increment;
		}

		for (int i = 0; i < MapHandler.ghostSpawner.size(); i++) {
			MapHandler.ghostSpawner.get(i).hp += increment;
		}

		if (MapHandler.playerOne.hp < 5) {
			MapHandler.playerOne.hp += 1;
		}

		centerCamera();
	}

	private void enemyActions() {

		for (int entity = 0; entity < MapHandler.ghosts.size(); entity++) {
			MapHandler.ghosts.get(entity).AI(deltaTime);
		}
		for (int entity = 0; entity < MapHandler.grunts.size(); entity++) {
			MapHandler.grunts.get(entity).AI(deltaTime);
		}

		if (MapHandler.GameState == 1) {
			delay++;
			if (delay > 500) {
				for (int obj = 0; obj < MapHandler.ghostSpawner.size(); obj++) {
					MapHandler.ghostSpawner.get(obj).spawner();

				}
				delay = 0;
			}
		}
	}

	private void projectileHandling() {

		for (int projectile = 0; projectile < Projectiles.size(); projectile++) {

			if (Projectiles.get(projectile).pos.x > MapHandler.playerOne.pos.x - 150
					&& Projectiles.get(projectile).pos.x < MapHandler.playerOne.pos.x + 150
					&& Projectiles.get(projectile).pos.y > MapHandler.playerOne.pos.y - 150
					&& Projectiles.get(projectile).pos.y < MapHandler.playerOne.pos.y + 150) {
				Projectiles.get(projectile).Fly(deltaTime);
			} else {
				Projectiles.remove(projectile);
			}
		}

		for (int i = 0; i < Projectiles.size(); i++) {
			Projectiles.get(i).rect.setLocation(cam.camX
					+ Projectiles.get(i).pos.x, cam.camY
					+ Projectiles.get(i).pos.y);
			boolean toBeRemoved = false;

			for (int j = 0; j < MapHandler.grunts.size(); j++) {
				if (Projectiles.get(i).rect
						.intersects(MapHandler.grunts.get(j).rect)) {
					toBeRemoved = true;
					MapHandler.grunts.get(j).hp--;
					MonsterIsHit.play();
				}
			}

			for (int j = 0; j < MapHandler.ghosts.size(); j++) {
				if (Projectiles.get(i).rect
						.intersects(MapHandler.ghosts.get(j).rect)) {
					toBeRemoved = true;
					MapHandler.ghosts.get(j).hp--;
					MonsterIsHit.play(1f, 1f / MapHandler.ghosts.size());
				}
			}

			for (int j = 0; j < MapHandler.ghostSpawner.size(); j++) {
				if (Projectiles.get(i).rect.intersects(MapHandler.ghostSpawner
						.get(j).rect)) {
					toBeRemoved = true;
					MapHandler.ghostSpawner.get(j).hp--;
					MonsterIsHit.play();
				}
			}

			for (int j = 0; j < MapHandler.walls.size(); j++) {
				if (Projectiles.get(i).rect
						.intersects(MapHandler.walls.get(j).rect)) {
					toBeRemoved = true;
				}
			}

			if (Projectiles.get(i).rect.intersects(MapHandler.exitDoor.rect)) {
				toBeRemoved = true;
				MapHandler.exitDoor.CheckIfOpen(true);
				DoorSound.play();
			}

			if (toBeRemoved)
				Projectiles.remove(i);
		}
	}

	private void restartGame() {
		MapHandler.playerOne.hp = 3;
		score = 0;
		MapRef.EmptyMap();
		isBackground = false;
		Maploader.lvl -= 1;
		Maploader.GameState = 1;
		Maploader.LoadMap(Maploader.lvl += 1, 1);

	}

	private void inputHandling(GameContainer inputListener) {

		if (inputListener.getInput().isKeyPressed(Input.KEY_R)) {
			restartGame();
			centerCamera();
		}

		if (inputListener.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			Maploader.LoadMap(0, 0);
		}

		Input playerInput = inputListener.getInput();

		if (Maploader.GameState == 1) {
			if (playerInput.isKeyDown(Input.KEY_W)) {
				if (!playerCollisionTest(0, -1)) {
					MapHandler.playerOne.Move("up", deltaTime);
					cam.camY += MapHandler.playerOne.localSpeed * deltaTime;
				}
			}

			if (playerInput.isKeyDown(Input.KEY_S)) {
				if (!playerCollisionTest(0, 1)) {
					MapHandler.playerOne.Move("down", deltaTime);
					cam.camY -= MapHandler.playerOne.localSpeed * deltaTime;
				}
			}

			if (playerInput.isKeyDown(Input.KEY_A)) {
				if (!playerCollisionTest(-1, 0)) {
					MapHandler.playerOne.Move("left", deltaTime);
					cam.camX += MapHandler.playerOne.localSpeed * deltaTime;
				}
			}

			if (playerInput.isKeyDown(Input.KEY_D)) {
				if (!playerCollisionTest(1, 0)) {
					MapHandler.playerOne.Move("right", deltaTime);
					cam.camX -= MapHandler.playerOne.localSpeed * deltaTime;
				}
			}

			if (playerInput.isKeyDown(Input.KEY_D)
					&& playerInput.isKeyDown(Input.KEY_W)) {
				MapHandler.playerOne.facingDirection = "rightUp";

			}

			if (playerInput.isKeyDown(Input.KEY_D)
					&& playerInput.isKeyDown(Input.KEY_S)) {
				MapHandler.playerOne.facingDirection = "rightDown";

			}

			if (playerInput.isKeyDown(Input.KEY_A)
					&& playerInput.isKeyDown(Input.KEY_W)) {
				MapHandler.playerOne.facingDirection = "leftUp";

			}

			if (playerInput.isKeyDown(Input.KEY_A)
					&& playerInput.isKeyDown(Input.KEY_S)) {
				MapHandler.playerOne.facingDirection = "leftDown";

			}

			if (playerInput.isKeyPressed(Input.KEY_SPACE)) {
				MapHandler.playerOne.Shoot();
				ShootSound.play();
			}
		}
	}

	protected void centerCamera() {

		cam.camX = -MapHandler.playerOne.pos.x + Window.Width / 2;
		cam.camY = -MapHandler.playerOne.pos.y + Window.Height / 2;
	}

	private void deathHandling() {

		for (int currGrunt = 0; currGrunt < MapHandler.grunts.size(); currGrunt++) {
			if (MapHandler.grunts.get(currGrunt).isDead) {
				MapHandler.grunts.remove(currGrunt);
				score += 50;
			}
		}

		for (int currGhost = 0; currGhost < MapHandler.ghosts.size(); currGhost++) {
			if (MapHandler.ghosts.get(currGhost).isDead) {
				MapHandler.ghosts.remove(currGhost);
				score += 10;
			}
		}

		for (int currGhostSP = 0; currGhostSP < MapHandler.ghostSpawner.size(); currGhostSP++) {
			if (MapHandler.ghostSpawner.get(currGhostSP).hp <= 0) {
				MapHandler.ghostSpawner.remove(currGhostSP);
				score += 100;
			}
		}

		if (MapHandler.playerOne.hp <= 0) {
			Maploader.LoadMap(0, 2);
			MapRef.EmptyMap();
			try {
				deathMusic = new Music("res/music/DeathSong.wav");
			} catch (SlickException e) {
				e.printStackTrace();
			}
			deathMusic.setVolume(1f);
			deathMusic.loop();
		} else if (MapHandler.lvl >= MaxLevel) {
			Maploader.LoadMap(1, 2);
			MapRef.EmptyMap();
			try {
				menuMusic = new Music("res/music/songA.wav");
			} catch (SlickException e) {
				e.printStackTrace();
			}
			menuMusic.setVolume(1f);
			menuMusic.loop();

		}

	}

	private boolean playerCollisionTest(float testPosX, float testPosY) {

		MapHandler.playerOne.rect.setLocation(cam.camX
				+ MapHandler.playerOne.pos.x + testPosX, cam.camY
				+ MapHandler.playerOne.pos.y + testPosY);

		for (int object = 0; object < MapHandler.walls.size(); object++) {
			if (MapHandler.playerOne.rect.intersects(MapHandler.walls
					.get(object).rect)) {
				return true;
			}
		}

		for (int object = 0; object < MapHandler.rocks.size(); object++) {
			if (MapHandler.playerOne.rect.intersects(MapHandler.rocks
					.get(object).rect)) {
				return true;
			}
		}
		return false;
	}

	private void drawGUI() {
		for (int hearts = 0; hearts < MapHandler.playerOne.hp; hearts++)
			GUIheart.draw(8 + 32 * hearts, 0);

		gameFont.drawString(8, 32, "Score: " + score);

	}

	private void drawStaticObjects() {

		for (int obj = 0; obj < MapHandler.rocks.size(); obj++) {
			staticObject currObject = MapHandler.rocks.get(obj);
			currObject.objImage.draw(cam.camX + currObject.pos.x, cam.camY
					+ currObject.pos.y);
		}

		for (int obj = 0; obj < MapHandler.walls.size(); obj++) {
			staticObject currObject = MapHandler.walls.get(obj);
			currObject.objImage.draw(cam.camX + currObject.pos.x, cam.camY
					+ currObject.pos.y);
		}

		for (int obj = 0; obj < MapHandler.ghostSpawner.size(); obj++) {
			staticObject currObject = MapHandler.ghostSpawner.get(obj);
			currObject.objImage.draw(cam.camX + currObject.pos.x, cam.camY
					+ currObject.pos.y);
		}

		for (int obj = 0; obj < MapHandler.floor01.size(); obj++) {
			staticObject currObject = MapHandler.floor01.get(obj);
			currObject.objImage.draw(cam.camX + currObject.pos.x, cam.camY
					+ currObject.pos.y);
		}
		for (int obj = 0; obj < MapHandler.floor02.size(); obj++) {
			staticObject currObject = MapHandler.floor02.get(obj);
			currObject.objImage.draw(cam.camX + currObject.pos.x, cam.camY
					+ currObject.pos.y);
		}

		MapHandler.exitDoor.objImage.draw(cam.camX + MapHandler.exitDoor.pos.x,
				cam.camY + MapHandler.exitDoor.pos.y);
	}

	private void drawPlayer() {
		switch (MapHandler.playerOne.facingDirection) {
		case "up":
			MapHandler.playerOne.moveUp.draw(cam.camX
					+ MapHandler.playerOne.pos.x, cam.camY
					+ MapHandler.playerOne.pos.y);
			break;
		case "left":
			MapHandler.playerOne.moveLeft.draw(cam.camX
					+ MapHandler.playerOne.pos.x, cam.camY
					+ MapHandler.playerOne.pos.y);
			break;
		case "down":
			MapHandler.playerOne.moveDown.draw(cam.camX
					+ MapHandler.playerOne.pos.x, cam.camY
					+ MapHandler.playerOne.pos.y);
			break;
		case "right":
			MapHandler.playerOne.moveRight.draw(cam.camX
					+ MapHandler.playerOne.pos.x, cam.camY
					+ MapHandler.playerOne.pos.y);
			break;
		case "rightUp":
			MapHandler.playerOne.moveRightUp.draw(cam.camX
					+ MapHandler.playerOne.pos.x, cam.camY
					+ MapHandler.playerOne.pos.y);
			break;
		case "rightDown":
			MapHandler.playerOne.moveRightDown.draw(cam.camX
					+ MapHandler.playerOne.pos.x, cam.camY
					+ MapHandler.playerOne.pos.y);
			break;
		case "leftUp":
			MapHandler.playerOne.moveLeftUp.draw(cam.camX
					+ MapHandler.playerOne.pos.x, cam.camY
					+ MapHandler.playerOne.pos.y);
			break;
		case "leftDown":
			MapHandler.playerOne.moveLeftDown.draw(cam.camX
					+ MapHandler.playerOne.pos.x, cam.camY
					+ MapHandler.playerOne.pos.y);
			break;
		}
	}

	private void drawEnemies() {

		for (int entity = 0; entity < MapHandler.grunts.size(); entity++) {
			Entity currEntity = MapHandler.grunts.get(entity);

			switch (currEntity.facingDirection) {
			case "up":
				currEntity.moveUp.draw(cam.camX + currEntity.pos.x, cam.camY
						+ currEntity.pos.y);
				break;
			case "left":
				currEntity.moveLeft.draw(cam.camX + currEntity.pos.x, cam.camY
						+ currEntity.pos.y);
				break;
			case "down":
				currEntity.moveDown.draw(cam.camX + currEntity.pos.x, cam.camY
						+ currEntity.pos.y);
				break;
			case "right":
				currEntity.moveRight.draw(cam.camX + currEntity.pos.x, cam.camY
						+ currEntity.pos.y);
				break;
			case "rightUp":
				currEntity.moveRightUp.draw(cam.camX + currEntity.pos.x,
						cam.camY + currEntity.pos.y);
				break;
			case "rightDown":
				currEntity.moveRightDown.draw(cam.camX + currEntity.pos.x,
						cam.camY + currEntity.pos.y);
				break;
			case "leftUp":
				currEntity.moveLeftUp.draw(cam.camX + currEntity.pos.x,
						cam.camY + currEntity.pos.y);
				break;
			case "leftDown":
				currEntity.moveLeftDown.draw(cam.camX + currEntity.pos.x,
						cam.camY + currEntity.pos.y);
				break;
			}
		}

		for (int entity = 0; entity < MapHandler.ghosts.size(); entity++) {
			Entity currEntity = MapHandler.ghosts.get(entity);

			switch (currEntity.facingDirection) {
			case "up":
				currEntity.moveUp.draw(cam.camX + currEntity.pos.x, cam.camY
						+ currEntity.pos.y);
				break;
			case "left":
				currEntity.moveLeft.draw(cam.camX + currEntity.pos.x, cam.camY
						+ currEntity.pos.y);
				break;
			case "down":
				currEntity.moveDown.draw(cam.camX + currEntity.pos.x, cam.camY
						+ currEntity.pos.y);
				break;
			case "right":
				currEntity.moveRight.draw(cam.camX + currEntity.pos.x, cam.camY
						+ currEntity.pos.y);
				break;
			case "rightUp":
				currEntity.moveRightUp.draw(cam.camX + currEntity.pos.x,
						cam.camY + currEntity.pos.y);
				break;
			case "rightDown":
				currEntity.moveRightDown.draw(cam.camX + currEntity.pos.x,
						cam.camY + currEntity.pos.y);
				break;
			case "leftUp":
				currEntity.moveLeftUp.draw(cam.camX + currEntity.pos.x,
						cam.camY + currEntity.pos.y);
				break;
			case "leftDown":
				currEntity.moveLeftDown.draw(cam.camX + currEntity.pos.x,
						cam.camY + currEntity.pos.y);
				break;
			}
		}
	}

	private void drawProjectiles() {
		for (int projectile = 0; projectile < Projectiles.size(); projectile++) {
			Projectiles.get(projectile).projectileAnimation.draw(cam.camX
					+ Projectiles.get(projectile).pos.x,
					cam.camY + Projectiles.get(projectile).pos.y);
		}
	}

}
