package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

/**
 * Laser, subklasse van Projectile
 */

public class Laser extends Projectile {
	
	/**
	 * Constructor
	 * @param world			Referentie naar de hoofdmodule
	 * @param friendly		Friendly = afgevuurd door de speler, !Friendly = afgevuurd door een alien
	 * @param source		Het object dat het projectiel oorspronkelijk heeft afgevuurd
	 */
	public Laser (SpaceInvaders world, boolean friendly, AttackCapableGameObject source) {
		super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/Laser.png"), 1, 3, 18, friendly, world, source);
		this.isFriendly = friendly;
		this.weight = 1;
	}
	
}
