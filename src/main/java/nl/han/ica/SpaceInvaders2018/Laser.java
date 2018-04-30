package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Laser extends Projectile {
	
	public Laser (boolean friendly) {
		super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/laser.png"), 1,7, 35, friendly);
		this.friendly = friendly;

		System.out.println("Er is een laser gemaakt");
	}
	
}
