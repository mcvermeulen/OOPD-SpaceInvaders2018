package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Laser extends Projectile {
	
	public Laser (SpaceInvaders world, boolean friendly, AttackCapableGameObject source) {
		super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/laser.png"), 1, 7, 35, friendly, world, source);
		this.friendly = friendly;
	}
	
}
