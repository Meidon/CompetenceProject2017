package game.gameObjects;

import game.map.MapHandler;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class Ghost extends Entity {

	public Ghost(int spawnX, int spawnY, float speed) {
		pos.set(spawnX, spawnY);
		localSpeed = speed;
		width = 16;
		height = 16;
		rect = new Rectangle(pos.x, pos.y, width, height);
		facingDirection = "down";
		hp = 3;
		isDead = false;

		try {

			spriteUp = new SpriteSheet(new Image(
					"res/entities/ghost/ghostMoveUp.png"), 16, 16);
			spriteDown = new SpriteSheet(new Image(
					"res/entities/ghost/ghostMoveDown.png"), 16, 16);
			spriteLeft = new SpriteSheet(new Image(
					"res/entities/ghost/ghostMoveLeft.png"), 16, 16);
			spriteRight = new SpriteSheet(new Image(
					"res/entities/ghost/ghostMoveRight.png"), 16, 16);
			spriteLeftUp = new SpriteSheet(new Image(
					"res/entities/ghost/ghostMoveLeftUp.png"), 16, 16);
			spriteRightUp = new SpriteSheet(new Image(
					"res/entities/ghost/ghostMoveRightUp.png"), 16, 16);
			spriteLeftDown = new SpriteSheet(new Image(
					"res/entities/ghost/ghostMoveLeftDown.png"), 16, 16);
			spriteRightDown = new SpriteSheet(new Image(
					"res/entities/ghost/ghostMoveRightDown.png"), 16, 16);
			moveUp = new Animation(spriteUp, 300);
			moveLeft = new Animation(spriteLeft, 300);
			moveDown = new Animation(spriteDown, 300);
			moveRight = new Animation(spriteRight, 300);
			moveLeftUp = new Animation(spriteLeftUp, 300);
			moveLeftDown = new Animation(spriteLeftDown, 300);
			moveRightUp = new Animation(spriteRightUp, 300);
			moveRightDown = new Animation(spriteRightDown, 300);

		} catch (SlickException e) {

		}

	}

	@Override
	public String toString() {
		return "The Ghost, who is a " + super.toString();
	}

	public void AI(int dt) {
		if (hp > 0) {
			if ((int) (MapHandler.playerOne.pos.x) > (int) (this.pos.x)
					&& (int) (MapHandler.playerOne.pos.y) > (int) (this.pos.y)) {
				Move("rightDown", dt);
			} else if ((int) (MapHandler.playerOne.pos.x) < (int) (this.pos.x)
					&& (int) (MapHandler.playerOne.pos.y) > (int) (this.pos.y)) {
				Move("leftDown", dt);
			} else if ((int) (MapHandler.playerOne.pos.x) > (int) (this.pos.x)
					&& (int) (MapHandler.playerOne.pos.y) < (int) (this.pos.y)) {
				Move("rightUp", dt);
			} else if ((int) (MapHandler.playerOne.pos.x) < (int) (this.pos.x)
					&& (int) (MapHandler.playerOne.pos.y) < (int) (this.pos.y)) {
				Move("leftUp", dt);
			} else if ((int) (MapHandler.playerOne.pos.x) == (int) (this.pos.x)
					&& (int) (MapHandler.playerOne.pos.y) > (int) (this.pos.y)) {
				Move("down", dt);
			} else if ((int) (MapHandler.playerOne.pos.x) == (int) (this.pos.x)
					&& (int) (MapHandler.playerOne.pos.y) < (int) (this.pos.y)) {
				Move("up", dt);
			} else if ((int) (MapHandler.playerOne.pos.x) > (int) (this.pos.x)
					&& (int) (MapHandler.playerOne.pos.y) == (int) (this.pos.y)) {
				Move("right", dt);
			} else if ((int) (MapHandler.playerOne.pos.x) < (int) (this.pos.x)
					&& (int) (MapHandler.playerOne.pos.y) == (int) (this.pos.y)) {
				Move("left", dt);
			}
		} else {
			isDead = true;
		}
	}
}
