package game.gameObjects;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class Grunt extends Entity {

	int x1;
	int y1;
	int x2;
	int y2;

	@Override
	public String toString() {
		return "The Grunt who is a " + super.toString();
	}

	public Grunt(int spawnX, int spawnY, int destX, int destY, float speed) {
		// pos.x = spawnX;
		// pos.y = spawnY;
		pos.set(spawnX, spawnY);
		x1 = spawnX;
		y1 = spawnY;
		x2 = destX;
		y2 = destY;
		localSpeed = speed;
		facingDirection = "down";
		width = 16;
		height = 16;
		rect = new Rectangle(pos.x, pos.y, width, height);
		hp = 3;
		isDead = false;

		try {

			spriteUp = new SpriteSheet(new Image(
					"res/entities/grunt/gruntMoveUp.png"), 16, 16);
			spriteLeft = new SpriteSheet(new Image(
					"res/entities/grunt/gruntMoveLeft.png"), 16, 16);
			spriteDown = new SpriteSheet(new Image(
					"res/entities/grunt/gruntMoveDown.png"), 16, 16);
			spriteRight = new SpriteSheet(new Image(
					"res/entities/grunt/gruntMoveRight.png"), 16, 16);
			moveUp = new Animation(spriteUp, 300);
			moveLeft = new Animation(spriteLeft, 300);
			moveDown = new Animation(spriteDown, 300);
			moveRight = new Animation(spriteRight, 300);
			moveLeftUp = new Animation(spriteUp, 300);
			moveLeftDown = new Animation(spriteDown, 300);
			moveRightUp = new Animation(spriteUp, 300);
			moveRightDown = new Animation(spriteDown, 300);

		} catch (SlickException e) {

		}

	}

	int patrolType = 0;

	public void AI(int dt) {
		if (hp > 0) {
			if (patrolType == 0) {
				if (x1 > (int) this.pos.x && y1 > (int) this.pos.y) {
					Move("rightDown", dt);

				} else if (x1 < (int) this.pos.x && y1 > (int) this.pos.y) {
					Move("leftDown", dt);

				} else if (x1 > (int) this.pos.x && y1 < (int) this.pos.y) {
					Move("rightUp", dt);

				} else if (x1 < (int) this.pos.x && y1 < (int) this.pos.y) {
					Move("leftUp", dt);

				} else if (x1 == (int) this.pos.x && y1 > (int) this.pos.y) {
					Move("down", dt);

				} else if (x1 == (int) this.pos.x && y1 < (int) this.pos.y) {
					Move("up", dt);

				} else if (x1 > (int) this.pos.x && y1 == (int) this.pos.y) {
					Move("right", dt);

				} else if (x1 < (int) this.pos.x && y1 == (int) this.pos.y) {
					Move("left", dt);

				} else if (x1 == (int) this.pos.x && y1 == (int) this.pos.y) {
					patrolType = 1;
				}
			}// end of if
			if (patrolType == 1) {

				if (x2 > (int) this.pos.x && y2 > (int) this.pos.y) {
					Move("rightDown", dt);

				} else if (x2 < (int) this.pos.x && y2 > (int) this.pos.y) {
					Move("leftDown", dt);

				} else if (x2 > (int) this.pos.x && y2 < (int) this.pos.y) {
					Move("rightUp", dt);

				} else if (x2 < (int) this.pos.x && y2 < (int) this.pos.y) {
					Move("leftUp", dt);

				} else if (x2 == (int) this.pos.x && y2 > (int) this.pos.y) {
					Move("down", dt);

				} else if (x2 == (int) this.pos.x && y2 < (int) this.pos.y) {
					Move("up", dt);

				} else if (x2 > (int) this.pos.x && y2 == (int) this.pos.y) {
					Move("right", dt);

				} else if (x2 < (int) this.pos.x && y2 == (int) this.pos.y) {
					Move("left", dt);

				} else if (x2 == (int) this.pos.x && y2 == (int) this.pos.y) {
					patrolType = 0;
				}
			}// end of it
		} else {
			isDead = true;
		}
	}
}
