package nl.han.ica.SpaceInvaders2018;

import java.util.List;
import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;

/**
 * Superklasse van de aliens
 */
public abstract class Alien extends AttackCapableGameObject implements ICollidableWithGameObjects {
	protected int value = 0;
	protected boolean hit;
	protected Sound alienKilled;

    /**
     * Constructor
     * @param sprite Afbeelding van de alien
     * @param totalFrames Aantal frames waaruit de afbeeldng bestaat
     * @param x X-positie waarop de alien getekend wordt
     * @param y Y-positie waarop de aliend getekend wordt
     * @param sWidth Breedte van de alien
     * @param sHeight Hoogte van de alien
     * @param world Referentie naar de hoofdmodule
     * @param alienKilled Geluid wat de alien maakt als hij geraakt wordt
     */
    public Alien(Sprite sprite, int totalFrames, float x, float y, int sWidth, int sHeight, SpaceInvaders world, Sound alienKilled) {
        super(sprite, totalFrames, x, y, sWidth, sHeight, world);
        this.hit = false;
        this.alienKilled = alienKilled;
    }

    @Override
    public void update() {
        nextFrame();
        cleanUpProjectiles();
    }
    
    public int getValue() {
    	return value;
    }
    
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g:collidedGameObjects) {
            if (g instanceof Projectile) {
            	Projectile p = (Projectile) g;
            	if(p.getFriendly()) {
            		alienKilled.cue(140);
            		alienKilled.play();
            		AttackCapableGameObject k = p.getSource();
            		k.removeProjectile(p);
            		world.deleteGameObject(g);
                    explode();
	            	hit = true;
            	}
            }
        }
    }
    
    public boolean getHit() {
    	return hit;
    }

    public void fire() {
        Random rand = new Random();
        int type = rand.nextInt(100);
        if (type > 50 && type < 80) {
            generatePlasma();
        } else if (type >= 80) {
            generateProton();
        } else {
            generateLaser(false);
        }
    }
    
    protected void dropToRowBelow() {
    	setY(getY() + getHeight());
    }
    
}
