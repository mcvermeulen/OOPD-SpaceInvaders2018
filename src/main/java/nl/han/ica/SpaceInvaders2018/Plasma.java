package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Plasma extends Projectile {

	public Plasma(SpaceInvaders world, AttackCapableGameObject source) {
		super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/Plasma.png"), 1, 8, 20, false, world, source);
	}
	
}
