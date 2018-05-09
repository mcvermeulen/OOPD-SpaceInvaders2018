package nl.han.ica.SpaceInvaders2018;

import java.util.List;
import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import processing.core.PVector;

/**
 * Superklasse van de aliens
 */
public abstract class Alien extends AttackCapableGameObject implements ICollidableWithGameObjects, ICollidableWithTiles {
	
	/**
	 * De waarde van de alien, die toegevoegd wordt aan de score als de speler hem raakt
	 */
	protected int value = 0;
	/**
	 * Geeft aan of de alien is geraakt
	 */
	protected boolean hit;
	/**
	 * Geluid wat de alien maakt als hij geraakt wordt
	 */
	protected Sound alienKilled;

    /**
     * Constructor
     * @param sprite 		Afbeelding van de alien
     * @param totalFrames 	Aantal frames waaruit de afbeeldng bestaat
     * @param x 			X-positie waarop de alien getekend wordt
     * @param y 			Y-positie waarop de aliend getekend wordt
     * @param sWidth 		Breedte van de alien
     * @param sHeight 		Hoogte van de alien
     * @param world 		Referentie naar de hoofdmodule
     * @param alienKilled 	Geluid wat de alien maakt als hij geraakt wordt
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
    
    /**
     * Geeft de waarde van de alien, die toegevoegd wordt aan de score als de speler hem raakt
     * @return		waarde
     */
    public int getValue() {
    	return value;
    }
    
    /**
     * Als een alien wordt geraakt, worden zowel de alien als het projectiel verwijderd, en er vindt een explosie plaats
     */
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
                    explode();
	            	hit = true;
            	}
            }
        }
    }
    
    /**
     * Geeft aan of de alien is geraakt
     * @return		is de alien geraakt, true of false
     */
    public boolean getHit() {
    	return hit;
    }
    
    /**
     * Bepaalt welk type projectiel door de alien wordt afgevuurd
     */
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
    
    /**
     * Verplaatst de alien naar beneden
     */
    protected void dropToRowBelow() {
    	setY(getY() + getHeight());
    }
    
    /**
     * Zorgt dat de bunker tiles die door een alien worden geraakt, gewist worden
     */
    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles)  {
    	PVector vector;
    	for (CollidedTile ct : collidedTiles) {
    		vector = world.getTileMap().getTilePixelLocation(ct.theTile);
    		world.getTileMap().setTile((int) vector.x / 20, (int) vector.y / 20, -1);
    	}
    }
    
}
