package nl.han.ica.SpaceInvaders2018;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;

public class Ruimteschip extends DestroyableGameObject implements ICollidableWithGameObjects {
    private int direction = 90;
    private Sound UFOShot;
    private Sound UFOTravel;
    private boolean shot;

    public Ruimteschip(SpaceInvaders world, float x, float y, Sound UFOShot, Sound UFOTravel) {
        super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/Ruimteschip.png"), 1, x, y, 50, 23, world);
        this.UFOShot = UFOShot;
        this.UFOTravel = UFOTravel;
        this.shot = false;

        setCurrentFrameIndex(0);
        setDirectionSpeed(direction, 2);
    }

    @Override
    public void update() {
        nextFrame();
        float x = getX();
        outOfViewPort(x);
        // boundaries
        if (direction == 90 && x == 1140) {
        	direction = 270;
        	travel();
        }
        if (direction == 270 && x == 100) {
            direction = 90;
            travel();
        }
        setDirectionSpeed(direction, 2);
    }
    
    public void travel() {
    	System.out.println(isVisible());
    	shot = false;
    	if (isVisible()) {
    		UFOTravel.loop(-1);
    	}
    }
    
    public void outOfViewPort(float x) {
    	if (!shot) {
	    	if (x >= 1140 || x <= 100) {
	        	setVisible(false);
	        }
	        else {
	        	setVisible(true);
	        }
    	}
    }
    
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g:collidedGameObjects) {
            if (g instanceof Projectile) {
            	if(((Projectile) g).getFriendly()) {
            		shot = true;
            		Projectile p = (Projectile) g;
            		AttackCapableGameObject k = p.getSource();
            		k.removeProjectile(p);
            		world.deleteGameObject(g);
            		UFOShot.rewind();
            		UFOShot.play();
            		setVisible(false); //TODO Explosie-animatie, puntenberekening (hoeveel was de UFO waard), puntenoptellen bij totaalscore, etc.
            	}
            }
        }
    }
}
