package game.gameObjects;

import game.*;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class Player extends Entity {

	public boolean isInvulnerable;

	@Override
	public String toString() {
		return "The Player, who is a " + super.toString();
	}

	public Player(int spawnX, int spawnY, float speed) {
		pos.set(spawnX, spawnY);
		localSpeed = speed;
		isInvulnerable = false;
		width = 14;
		height = 14;
		rect = new Rectangle(pos.x, pos.y, width, height);
		facingDirection = "down";
		hp = 3;

		try {
			spriteUp = new SpriteSheet(new Image(
					"res/entities/characters/barbarian/barbarianMoveUp.png"),
					14, 14);
			spriteDown = new SpriteSheet(new Image(
					"res/entities/characters/barbarian/barbarianMoveDown.png"),
					14, 14);
			spriteLeft = new SpriteSheet(new Image(
					"res/entities/characters/barbarian/barbarianMoveLeft.png"),
					14, 14);
			spriteRight = new SpriteSheet(
					new Image(
							"res/entities/characters/barbarian/barbarianMoveRight.png"),
					14, 14);
			moveUp = new Animation(spriteUp, 300);
			moveLeft = new Animation(spriteLeft, 300);
			moveDown = new Animation(spriteDown, 300);
			moveRight = new Animation(spriteRight, 300);
			moveLeftUp = new Animation(spriteUp, 300);
			moveLeftDown = new Animation(spriteDown, 300);
			moveRightUp = new Animation(spriteUp, 300);
			moveRightDown = new Animation(spriteDown, 300);
		} catch (SlickException e) {
			System.err.println("Missing SpriteSheet");
		}
	}

	public void Shoot() {
		Projectile shot = new Projectile();

		switch (facingDirection) {
		case "up":
			shot.pos.x = this.pos.x + 4;
			shot.pos.y = this.pos.y - 16;
			break;
		case "left":
			shot.pos.x = this.pos.x - 16;
			shot.pos.y = this.pos.y + 4;
			break;
		case "down":
			shot.pos.x = this.pos.x + 4;
			shot.pos.y = this.pos.y + 16;
			break;
		case "right":
			shot.pos.x = this.pos.x + 16;
			shot.pos.y = this.pos.y + 4;
			break;

		case "leftUp":
			shot.pos.x = this.pos.x - 16;
			shot.pos.y = this.pos.y - 16;
			break;
		case "leftDown":
			shot.pos.x = this.pos.x - 16;
			shot.pos.y = this.pos.y + 16;
			break;
		case "rightUp":
			shot.pos.x = this.pos.x + 16;
			shot.pos.y = this.pos.y - 16;
			break;
		case "rightDown":
			shot.pos.x = this.pos.x + 16;
			shot.pos.y = this.pos.y + 16;
			break;
		}

		shot.flyingDirection = facingDirection;
		GameManager.Projectiles.add(shot);
	}
}
