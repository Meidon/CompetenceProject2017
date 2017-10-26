package game.gameObjects;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Projectile extends GameObject {

	String flyingDirection;
	SpriteSheet ProjectileSprites;
	public Animation projectileAnimation;

	public float projectileSpeed;
	public Shape rect;

	@Override
	public String toString() {
		return "A projectile flying " + flyingDirection + ", which is a "
				+ super.toString();
	}

	public Projectile() {
		projectileSpeed = 0.2f;
		rect = new Rectangle(pos.x, pos.y, 8, 8);
		try {
			projectileAnimation = new Animation(new SpriteSheet(new Image(
					"res/entities/characters/barbarian/playerProjectile.png"),
					8, 8), 150);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void Fly(int delta) {
		switch (flyingDirection) {
		case "up":
			pos.y -= projectileSpeed * delta;
			break;
		case "left":
			pos.x -= projectileSpeed * delta;
			break;
		case "down":
			pos.y += projectileSpeed * delta;
			break;
		case "right":
			pos.x += projectileSpeed * delta;
			break;

		case "leftUp":
			pos.x -= projectileSpeed * delta;
			pos.y -= projectileSpeed * delta;
			break;
		case "leftDown":
			pos.x -= projectileSpeed * delta;
			pos.y += projectileSpeed * delta;
			break;
		case "rightUp":
			pos.x += projectileSpeed * delta;
			pos.y -= projectileSpeed * delta;
			break;
		case "rightDown":
			pos.x += projectileSpeed * delta;
			pos.y += projectileSpeed * delta;
			break;
		}
	}
}