package nl.han.ica.SpaceInvaders2018;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;

public abstract class Alien extends AttackCapableGameObject implements ICollidableWithGameObjects {
	
	protected int value = 0;
	protected boolean hit;
	protected Sound alienKilled;

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
	            	hit = true;
            	}
            }
        }
    }
    
    public boolean getHit() {
    	return hit;
    }
    
    public void fireLaser() {
    	generateLaser(world, false);
    }
    
    public void dropToRowBelow() {
    	setY(getY() + getHeight());
    }
    
}
