package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Proton extends Projectile {

	public Proton(SpaceInvaders world, AttackCapableGameObject source) {
		super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/Proton.png"), 2, 5, 17, false, world, source);
	}
	
}
