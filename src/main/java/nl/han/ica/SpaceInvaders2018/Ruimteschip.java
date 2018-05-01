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
    private int value;

    public Ruimteschip(SpaceInvaders world, float x, float y, Sound UFOShot, Sound UFOTravel) {
        super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/Ruimteschip.png"), 1, x, y, 50, 23, world);
        this.UFOShot = UFOShot;
        this.UFOTravel = UFOTravel;
        this.shot = false;
        this.value = 100;

        setCurrentFrameIndex(0);
        setDirectionSpeed(direction, 2);
    }

    @Override
    public void update() {
    	nextFrame();
        float x = getX();
        boolean outOfBounds = outOfViewPort(x);
        if (outOfBounds) {
        	//System.out.println("geen geluid");
        	pauseTravelSound();
        }
        else if (!outOfBounds && !shot) {
        	//System.out.println("We horen geluid te hebben");
        	travelSound();
        }
        travel();
    }
    
    public void resetUFO() {
    	if (shot) {
    		setVisible(true);
    	}
    	shot = false;
    }
    
    public void travel() {
        // boundaries
        if (direction == 90 && x == 1140) {
        	resetUFO();
        	direction = 270;
	    }
	    else if (direction == 270 && x == 100) {
	    	resetUFO();
	    	direction = 90;
	    }
        setDirectionSpeed(direction, 2);
    }
    
    public void travelSound() {
    	if (shot) {
    		//System.out.println("geen geluid");
    		pauseTravelSound();
    	}
    	else if (!shot) {
    		//System.out.println("We horen geluid te hebben");
    		if (!UFOTravel.isPlaying()) {
    			//System.out.println("We horen geluid te hebben");
    			//UFOTravel.loop(-1); // TODO: eventueel op 20 zetten als het geluid steeds so abrupt blijft afkappen
    		}
    	}
    }
    
    public void pauseTravelSound() {
    	if (UFOTravel.isPlaying() || UFOTravel.isLooping()) {
    		UFOTravel.pause();
    	}
    }
    
    public boolean outOfViewPort(float x) {
	    //System.out.println(x);
		if (x >= 990 || x <= 290 - getWidth()) {
			//System.out.println(true);
			return true;
		} else if (x < 990 || x > 290 - getWidth()) {
			//System.out.println(false);
			return false;
		}
		return true;
    }
    
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g:collidedGameObjects) {
            if (g instanceof Projectile) {
            	if(((Projectile) g).getFriendly()) {
            		Projectile p = (Projectile) g;
            		AttackCapableGameObject k = p.getSource();
            		k.removeProjectile(p);
            		world.deleteGameObject(g);
            		shot = true;
            		pauseTravelSound();
            		setVisible(false); //TODO Explosie-animatie, puntenberekening (hoeveel was de UFO waard), puntenoptellen bij totaalscore, etc.
            		UFOShot.cue(140);
            		UFOShot.play();
            		world.increaseScore(value);
            	}
            }
        }
    }
}
