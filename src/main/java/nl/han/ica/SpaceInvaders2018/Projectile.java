package nl.han.ica.SpaceInvaders2018;

import java.util.List;
import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PVector;

/**
 * Superklasse Projectile
 */
public abstract class Projectile extends DestroyableGameObject implements ICollidableWithGameObjects, ICollidableWithTiles {

	/**
	 * Friendly = afgevuurd door de speler, !Friendly = afgevuurd door een alien
	 */
    protected boolean friendly;
    /**
     * 1 = Laser, 2 = Proton, 3 = Photon
     */
    protected int weight;
    /**
     * Geeft aan of het projectiel buiten het speelscherm is gegaan
     */
    private boolean outOfBounds = false;
    /**
     * Het object dat het projectiel oorspronkelijk heeft afgevuurd
     */
    private AttackCapableGameObject source;

    /**
     * Constructor
     * @param sprite		Afbeelding van het projectiel
     * @param totalFrames	Aantal frames waaruit de afbeeldng bestaat
     * @param sWidth		Breedte van de afbeelding
     * @param sHeight		Hoogte van de afbeelding
     * @param friendly		Friendly = afgevuurd door de speler, !Friendly = afgevuurd door een alien
     * @param world			Referentie naar de hoofdmodule
     * @param source		Het object dat het projectiel oorspronkelijk heeft afgevuurd
     */
    public Projectile(Sprite sprite, int totalFrames, int sWidth, int sHeight, boolean friendly, SpaceInvaders world, AttackCapableGameObject source) {
        super(sprite, totalFrames, 0, 0, sWidth, sHeight, world);
        this.friendly = friendly;
        this.source = source;
    }

    /**
     * Beweegt het projectiel over het scherm
     */
    @Override
    public void update() {
        nextFrame();
        if (friendly) {
            if (getY() + getHeight() > (world.height - world.getPlayfieldHeight())/2) {
                setySpeed(-10);
            } else {
                outOfBounds = true;
            }
        } else {
            if (getY() < world.height) {
                setySpeed(2);
            } else {
                outOfBounds = true;
            }
        }
    }

    /**
     * Geeft aan of het projectiel buiten beeld is
     * @return		Is het projectiel buiten beeld, true of false
     */
    public boolean getOutOfBounds() {
        return outOfBounds;
    }

    /**
     * Geeft aan of een projectiel Friendly is
     * @return		Is het projectiel Friendly, true of false
     */
    public boolean getFriendly() {
        return friendly;
    }

    /**
     * Geeft aan welk object het Projectiel heeft afgeschoten
     * @return		Object dat het projectiel heeft afgeschoten
     */
    public AttackCapableGameObject getSource() {
        return source;
    }

    /**
     * Geeft aan wat er moet gebeuren wanneer een projectiel een ander projectiel raakt
     */
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g : collidedGameObjects) {
            if (g instanceof Projectile) {
                Projectile p = (Projectile) g;
                if (p.getFriendly()) {
                    AttackCapableGameObject k = p.getSource();
                    k.removeProjectile(p);
                    if (weight == p.getWeight()) {
                        source.removeProjectile(this);
                    } else {
                        Random rand = new Random();
                        int chance = rand.nextInt(weight*3);
                        if (chance < weight) {
                            source.removeProjectile(this);
                        }
                    }
                }
            }
        }
    }

    /**
     * Geeft zwaarte van het projectiel
     * @return		Zwaarte van het projectiel
     */
    public int getWeight() {
        return weight;
    }
    
    /**
     * Geeft aan wat er moet gebeuren wanneer een deel van de bunkers wordt geraakt
     */
    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles)  {
    	PVector vector;
    	for (CollidedTile ct : collidedTiles) {
    		source.removeProjectile(this);
       	 	world.deleteGameObject(this);
             if (ct.theTile instanceof BunkerTile) {
            	 BunkerTile bt = (BunkerTile)ct.theTile;
            	 if (bt.getHitPoints() == 0) {
            		 vector = world.getTileMap().getTilePixelLocation(ct.theTile);
            		 world.getTileMap().setTile((int) vector.x / 20, (int) vector.y / 20, -1);
            	 }
            	 else if (bt.getHitPoints() > 0) {
            		 bt.setHitPoints(bt.getHitPoints() - weight);
            		 bt.swapSprite();
            		 if (bt.getHitPoints() == 0) {
                		 vector = world.getTileMap().getTilePixelLocation(ct.theTile);
                		 world.getTileMap().setTile((int) vector.x / 20, (int) vector.y / 20, -1);
                	 }
            	 }
            }
    	 }
    }

}
