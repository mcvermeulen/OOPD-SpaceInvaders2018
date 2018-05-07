package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

/**
 * Proton, subklasse van Projectile
 */

public class Proton extends Projectile {
	
	/**
	 * Constructor
	 * @param world			Referentie naar de hoofdmodule
	 * @param source		Het object dat het projectiel oorspronkelijk heeft afgevuurd
	 */
	public Proton(SpaceInvaders world, AttackCapableGameObject source) {
		super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/Proton.png"), 2, 5, 17, false, world, source);
		weight = 3;
	}
	
}
