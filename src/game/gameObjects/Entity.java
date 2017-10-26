package game.gameObjects;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Shape;

public abstract class Entity extends GameObject {

	public boolean isDead;
	public int hp;
	public float localSpeed;
	public Shape rect;

	protected SpriteSheet spriteUp;
	protected SpriteSheet spriteLeft;
	protected SpriteSheet spriteDown;
	protected SpriteSheet spriteRight;
	protected SpriteSheet spriteRightUp;
	protected SpriteSheet spriteLeftUp;
	protected SpriteSheet spriteLeftDown;
	protected SpriteSheet spriteRightDown;

	public Animation moveUp;
	public Animation moveLeft;
	public Animation moveDown;
	public Animation moveRight;
	public Animation moveLeftUp;
	public Animation moveLeftDown;
	public Animation moveRightUp;
	public Animation moveRightDown;

	public String facingDirection;

	Entity() {
		pos.set(0, 0);
		isDead = false;

	}

	@Override
	public String toString() {
		return hp + " health Unit, a " + super.toString();
	}

	public void Move(String direction, int delta) {

		switch (direction) {
		case "up":
			facingDirection = "up";
			pos.y -= localSpeed * delta;
			break;
		case "left":
			facingDirection = "left";
			pos.x -= localSpeed * delta;
			break;
		case "down":
			facingDirection = "down";
			pos.y += localSpeed * delta;
			break;
		case "right":
			facingDirection = "right";
			pos.x += localSpeed * delta;
			break;

		case "leftUp":
			facingDirection = "leftUp";
			pos.x -= localSpeed * delta;
			pos.y -= localSpeed * delta;
			break;
		case "leftDown":
			facingDirection = "leftDown";
			pos.x -= localSpeed * delta;
			pos.y += localSpeed * delta;
			break;
		case "rightDown":
			facingDirection = "rightDown";
			pos.x += localSpeed * delta;
			pos.y += localSpeed * delta;
			break;
		case "rightUp":
			facingDirection = "rightUp";
			pos.x += localSpeed * delta;
			pos.y -= localSpeed * delta;
			break;
		default:
			System.out.println("Invalid direction was called!");
			break;
		}
	}
}
