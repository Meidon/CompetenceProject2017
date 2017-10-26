package game.map;

import game.gameObjects.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class MapHandler extends Maploader {

	static Random rnd = new Random();

	public int[][] Map;

	public int MapWidth;
	public int MapHeight;
	public int PercentAreWalls;
	public boolean isPlayerPlaced = false;
	public boolean isDoorPlaced = false;
	public int enemyCount;
	public int counter = 0;
	public int spawnerCount;

	public static ArrayList<Ghost> ghosts;
	public static ArrayList<Grunt> grunts;
	public static ArrayList<staticObject> rocks;
	public static ArrayList<staticObject> walls;
	public static ArrayList<GhostSpawner> ghostSpawner;
	public static Player playerOne;
	public static Door exitDoor;
	public static boolean IsMapInitialized = false;
	public static ArrayList<Music> rndMusic;
	public static ArrayList<staticObject> floor01;
	public static ArrayList<staticObject> floor02;
	File folder = new File("res/music/");
	File[] listOfFiles = folder.listFiles();
	public int n;

	public MapHandler() {
		MapWidth = 50;
		MapHeight = 50;
		PercentAreWalls = rnd.nextInt(90);
		enemyCount = rnd.nextInt(10);
		spawnerCount = rnd.nextInt(10);

		ghosts = new ArrayList<Ghost>();
		grunts = new ArrayList<Grunt>();
		rocks = new ArrayList<staticObject>();
		walls = new ArrayList<staticObject>();
		ghostSpawner = new ArrayList<GhostSpawner>();
		rnd = new Random();

		rndMusic = new ArrayList<Music>();
		floor01 = new ArrayList<staticObject>();
		floor02 = new ArrayList<staticObject>();
		for (File file : listOfFiles) {
			if (file.isFile()) {
				try {
					rndMusic.add(new Music(file.toString()));
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void MakeCaverns() {
		for (int row = 0; row <= MapHeight - 1; row++) {
			for (int column = 0; column < MapWidth - 1; column++) {
				Map[column][row] = PlaceWallLogic(column, row);
			}
		}
	}

	public int PlaceWallLogic(int x, int y) {
		int numWalls = GetAdjacentWalls(x, y, 1, 1);

		if (Map[x][y] == 1) {
			if (numWalls >= 4) {
				return 1;
			}
			if (numWalls < 2) {
				return 0;
			}
		} else {
			if (numWalls >= 5) {
				return 1;
			}
		}
		return 0;
	}

	public int GetAdjacentWalls(int x, int y, int scopeX, int scopeY) {
		int startX = x - scopeX;
		int startY = y - scopeY;
		int endX = x + scopeX;
		int endY = y + scopeY;

		int iX = startX;
		int iY = startY;

		int wallCounter = 0;

		for (iY = startY; iY <= endY; iY++) {
			for (iX = startX; iX <= endX; iX++) {
				if (!(iX == x && iY == y)) {
					if (IsWall(iX, iY)) {
						wallCounter += 1;
					}
				}
			}
		}
		return wallCounter;
	}

	boolean IsWall(int x, int y) {

		if (IsOutOfBounds(x, y)) {
			return true;
		}
		if (Map[x][y] == 1) {
			return true;
		}
		if (Map[x][y] == 0) {
			return false;
		}
		return false;

	}

	boolean IsOutOfBounds(int x, int y) {
		if (x < 0 || y < 0) {
			return true;
		} else if (x > MapWidth - 1 || y > MapHeight - 1) {
			return true;
		}
		return false;
	}

	public void RenderMap() {
		IsMapInitialized = true;
	}

	public void PlacePlayer() {
		for (int row = rnd.nextInt(MapHeight - 2); row < MapHeight; row++) {
			for (int column = rnd.nextInt(MapWidth - 2); column < MapWidth; column++) {

				if (Map[column][row] == 0 && Map[column][row] != 1
						&& Map[column][row] != 3) {
					if (isPlayerPlaced == false) {
						playerOne = new Player(column * 16, row * 16, 0.06f);
						Map[column][row] = 2;
						isPlayerPlaced = true;
					}
				}
			}
		}
	}

	public void PlaceWalls() {
		for (int row = 0; row < MapHeight; row++) {
			for (int column = 0; column < MapWidth; column++) {

				if (Map[column][row] == 1) {
					walls.add(new Wall(column * 16, row * 16));
				}
			}
		}
	}

	public void PlaceGhosts() {
		for (int row = rnd.nextInt(MapHeight - 1); row < MapHeight; row++) {
			for (int column = rnd.nextInt(MapWidth - 1); column < MapWidth; column++) {

				if (isPlayerPlaced == true && counter <= enemyCount
						&& Map[column][row] != 1 && Map[column][row] != 3
						&& Map[column][row] != 2) {
					ghosts.add(new Ghost(column * 16, row * 16, 0.03f));
					Map[column][row] = 4;
					counter++;
				}
			}
		}
		counter = 0;

	}

	public void PlaceGhostSpawners() {
		for (int row = rnd.nextInt(MapHeight - 2); row < MapHeight; row++) {
			for (int column = rnd.nextInt(MapWidth - 2); column < MapWidth; column++) {

				if (isPlayerPlaced == true && counter <= spawnerCount
						&& Map[column][row] != 1 && Map[column][row] != 3
						&& Map[column][row] != 2) {
					ghostSpawner.add(new GhostSpawner(column * 16, row * 16));
					Map[column][row] = 5;
					counter++;
				}
			}
		}
		counter = 0;
	}

	public void PlaceDoor() {
		for (int row = rnd.nextInt(MapHeight - 2); row < MapHeight; row++) {
			for (int column = rnd.nextInt(MapWidth - 2); column < MapWidth; column++) {
				if (isPlayerPlaced == true && isDoorPlaced == false
						&& Map[column][row] != 1 && Map[column][row] != 3
						&& Map[column][row] != 2) {
					exitDoor = new Door(column * 16, row * 16, true);
					Map[column][row] = 3;
					isDoorPlaced = true;
				}
			}
		}
	}

	public void GetRandomMusic() {
		n = rnd.nextInt(3);
		rndMusic.get(n).play();
		rndMusic.get(n).setVolume(1f);
		rndMusic.get(n).loop();
	}

	public void GetRandomFloorTile() {
		n = rnd.nextInt(2);

		if (n == 0) {
			for (int row = 0; row < MapHeight; row++) {
				for (int column = 0; column < MapWidth; column++) {
					if (Map[column][row] == 0 || Map[column][row] == 2
							|| Map[column][row] == 4) {
						floor01.add(new Floor01(column * 16, row * 16));
					}
				}
			}
		} else if (n == 1) {
			for (int row = 0; row < MapHeight; row++) {
				for (int column = 0; column < MapWidth; column++) {
					if (Map[column][row] == 0 || Map[column][row] == 2
							|| Map[column][row] == 4) {
						floor02.add(new Floor02(column * 16, row * 16));
					}
				}
			}
		}

	}

	public void EmptyMap() {
		for (int row = 0; row < MapHeight; row++) {
			for (int column = 0; column < MapWidth; column++) {
				Map[column][row] = 0;
				isPlayerPlaced = false;
				isDoorPlaced = false;
				IsMapInitialized = false;
				counter = 0;
				walls.clear();
				ghosts.clear();
				ghostSpawner.clear();
				floor01.clear();
				floor02.clear();
			}
		}
	}

	public void RandomFillMap() {
		Map = new int[MapWidth][MapHeight];
		int mapMiddle = 0;
		for (int row = 0; row < MapHeight; row++) {
			for (int column = 0; column < MapWidth; column++) {

				if (column == 0) {
					Map[column][row] = 1;
				} else if (row == 0) {
					Map[column][row] = 1;
				} else if (column == MapWidth - 1) {
					Map[column][row] = 1;
				} else if (row == MapHeight - 1) {
					Map[column][row] = 1;
				} else {
					mapMiddle = (MapHeight / 2);
					if (row == mapMiddle) {
						Map[column][row] = 0;
					} else {
						Map[column][row] = RandomPercent(PercentAreWalls);
					}
				}
			}
		}
	}

	int RandomPercent(int percent) {
		if (percent >= rnd.nextInt(101)) {
			return 1;
		}
		return 0;
	}

	public MapHandler(int mapWidth, int mapHeight, int[][] map,
			int percentWalls, int enemyCount, int spawnCount) {
		this.MapWidth = mapWidth;
		this.MapHeight = mapHeight;
		this.PercentAreWalls = percentWalls;
		this.Map = new int[this.MapWidth][this.MapHeight];
		this.Map = map;
		this.enemyCount = enemyCount;
		this.spawnerCount = spawnCount;
	}

}
