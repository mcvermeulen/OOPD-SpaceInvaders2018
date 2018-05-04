package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Laser extends Projectile {
	
	public Laser (SpaceInvaders world, boolean friendly, AttackCapableGameObject source) {
		super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/Laser.png"), 1, 3, 18, friendly, world, source);
		this.friendly = friendly; //TODO is dit nodig
	}
	
}
