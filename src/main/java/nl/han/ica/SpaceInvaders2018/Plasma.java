package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

/**
 * Plasma, subklasse van Projectile
 */

public class Plasma extends Projectile {
	
	/**
	 * Constructor
	 * @param world			Referentie naar de hoofdmodule
	 * @param source		Het object dat het projectiel oorspronkelijk heeft afgevuurd
	 */
	public Plasma(SpaceInvaders world, AttackCapableGameObject source) {
		super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/Plasma.png"), 1, 8, 20, false, world, source);
		weight = 2;
	}
	
}
