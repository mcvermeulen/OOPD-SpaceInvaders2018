package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public abstract class Projectile extends DestroyableGameObject {
	
	protected boolean friendly;
	private boolean outOfBounds = false;
	
	public Projectile (Sprite sprite, int totalFrames, int sWidth, int sHeight, boolean friendly) {
		super(sprite, totalFrames, 0, 0, sWidth, sHeight);
		this.friendly = friendly;
	}
	
	@Override
	public void update() {
		if (friendly) {
			if (getY() + getHeight() > 50) { // TODO, hier moeten we betere oplossingen voor bedenken, ook voor de boundaries van de aliens en het kanon
				//setDirection(180);
				setySpeed(-5);
				setY(getY() + getySpeed());
			}
			else {
				outOfBounds = true;
			}
		}
	}
	
	public boolean getOutOfBounds() {
		return outOfBounds;
	}

}
