package nl.han.ica.SpaceInvaders2018;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public abstract class Projectile extends DestroyableGameObject implements ICollidableWithGameObjects {
	
	protected boolean friendly;
	private boolean outOfBounds = false;
	private AttackCapableGameObject source;
	
	public Projectile (Sprite sprite, int totalFrames, int sWidth, int sHeight, boolean friendly, SpaceInvaders world, AttackCapableGameObject source) {
		super(sprite, totalFrames, 0, 0, sWidth, sHeight, world);
		this.friendly = friendly;
		this.source = source;
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
		else if (!friendly) {
			if (getY() < 1200) { // TODO, hier moeten we betere oplossingen voor bedenken, ook voor de boundaries van de aliens en het kanon
				//setDirection(180);
				setySpeed(1);
				setY(getY() + getySpeed());
			}
			else {
				//System.out.println("Delete mij");
				outOfBounds = true;
			}
		}
	}
	
	public boolean getOutOfBounds() {
		return outOfBounds;
	}
	
	public boolean getFriendly() {
		return friendly;
	}
	
    public AttackCapableGameObject getSource() {
    	return source;
    }
    
    
    // TODO Voor nu wordt een vijandelijk projectiel altijd verwijderd als de speler deze schiet. Maar de zwaardere projectielen hebben later
    // een kans om de collision is overleven
    
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
    	for (GameObject g:collidedGameObjects) {
            if (g instanceof Projectile) {
            	if(((Projectile) g).getFriendly()) {
            		Projectile p = (Projectile) g;
            		AttackCapableGameObject k = p.getSource();
            		k.removeProjectile(p);
            		world.deleteGameObject(g);
            		
            		source.removeProjectile(this);
            		world.deleteGameObject(this);
            	}
            }
    	}
    }

}
