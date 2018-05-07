package nl.han.ica.SpaceInvaders2018;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;

/**
 * Ruimteschip variabele
 */
public class Ruimteschip extends DestroyableGameObject implements ICollidableWithGameObjects {
    /**
     * Beweegrichting van het ruimteschip
     */
	private int direction = 90;
    /**
     * Geluid dat het schip maakt, wanneer het neergeschoten wordt
     */
	private Sound UFOShot;
    /**
     * Geluid dat het schip maakt, wanneer het beweegt
     */
    private Sound UFOTravel;
    /**
     * Geeft aan of het ruimteschip is geraakt door de speler
     */
    private boolean shot;
    /**
     * De punten die toegevoegd worden aan de score van de speler, als het ruimteschip wordt geraakt
     */
    private int value;

    /**
     * Constructor
     * @param world			Referentie naar de hoofdmodule
     * @param x				X-positie waarop het object getekend wordt
     * @param y				Y-positie waarop het object getekend wordt
     * @param UFOShot		Geluid dat het schip maakt, wanneer het neergeschoten wordt
     * @param UFOTravel		Geluid dat het schip maakt, wanneer het beweegt
     */
    public Ruimteschip(SpaceInvaders world, float x, float y, Sound UFOShot, Sound UFOTravel) {
        super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/Ruimteschip.png"), 1, x, y, 50, 23, world);
        this.UFOShot = UFOShot;
        this.UFOTravel = UFOTravel;
        this.shot = false;
        this.value = 100;

        setCurrentFrameIndex(0);
        setDirectionSpeed(direction, 2);
    }

    /**
     * Beweegt het ruimteschip over het scherm
     */
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
    
    /**
     * Reset het ruimteschip (maakt het weer zichtbaar)
     */
    public void resetUFO() {
    	if (shot) {
    		setVisible(true);
    	}
    	shot = false;
    }
    
    /**
     * Laat het ruimteschip bewegen
     */
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
    
    /**
     * Voegt geluid toe aan het ruimteschip, als het in beeld is
     */
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
    
    /**
     * Pauzeert het geluid van het ruimteschip
     */
    public void pauseTravelSound() {
    	if (UFOTravel.isPlaying() || UFOTravel.isLooping()) {
    		UFOTravel.pause();
    	}
    }
    
    /**
     * Geeft aan of het ruimteschip in beeld is
     * @param x		x-positie van het ruimteschip
     * @return		is het ruimteschip in beeld, of niet
     */
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
    
    /**
     * Geeft aan wat er moet gebeuren als de speler het ruimteschip raakt
     */
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
