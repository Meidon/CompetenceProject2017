package game.map;

import game.GameManager;


public class Maploader extends GameManager {

	public static int lvl = 0;
	public static int GameState = 0;
	public static int wallPercent = 30;
	public static MapHandler Map = new MapHandler();

	public Maploader() {

		LoadMap(0, 0);
	}

	public static void LoadMap(int level, int GS) {
		lvl = level;
		GameState = GS;
		if (lvl <= 20 && lvl > 0 && GameState == 1) {
			Map.PercentAreWalls = wallPercent;
			Map.RandomFillMap();
			Map.MakeCaverns();
			Map.PlaceWalls();
			Map.PlacePlayer();
			Map.PlaceDoor();
			Map.PlaceGhostSpawners();
			Map.PlaceGhosts();
			Map.GetRandomFloorTile();
			Map.GetRandomMusic();
			Map.RenderMap();
			System.out.println("Current Level: " + lvl);

		} else if (lvl == 0 && GameState == 1) {
			Map.PercentAreWalls = wallPercent;
			Map.RandomFillMap();
			Map.MakeCaverns();
			Map.PlaceWalls();
			Map.PlacePlayer();
			Map.PlaceDoor();
			Map.GetRandomFloorTile();
			Map.GetRandomMusic();
			Map.RenderMap();
			System.out.println("Current Level: Tutorial");
		}
	}

}
