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

    protected boolean isFriendly;
    protected int weight; // 1 = Laser, 2 = Proton, 3 = Photon
    private boolean outOfBounds = false; // geeft aan of het projectiel buiten beeld is geraakt
    private AttackCapableGameObject source;

    /**
     * Constructor
     * @param sprite      Afbeelding van het projectiel
     * @param totalFrames Aantal frames waaruit de afbeeldng bestaat
     * @param sWidth      Breedte van de afbeelding
     * @param sHeight     Hoogte van de afbeelding
     * @param isFriendly    Friendly = afgevuurd door de speler, !Friendly = afgevuurd door een alien
     * @param world       Referentie naar de hoofdmodule
     * @param source      Het object dat het projectiel oorspronkelijk heeft afgevuurd
     */
    public Projectile(Sprite sprite, int totalFrames, int sWidth, int sHeight, boolean isFriendly, SpaceInvaders world, AttackCapableGameObject source) {
        super(sprite, totalFrames, 0, 0, sWidth, sHeight, world);
        this.isFriendly = isFriendly;
        this.source = source;
    }

    /**
     * Beweegt het projectiel over het scherm
     */
    @Override
    public void update() {
        nextFrame();
        if (isFriendly) {
            updateLocationFriendlyProjectile();
        } else {
            updateLocationHostileProjectile();
        }
    }

    private void updateLocationFriendlyProjectile() {
        if (getY() + getHeight() > (world.height - world.getPlayfieldHeight()) / 2) {
            setySpeed(-10);
        } else {
            outOfBounds = true;
        }
    }

    private void updateLocationHostileProjectile() {
        if (getY() < world.height) {
            setySpeed(2);
        } else {
            outOfBounds = true;
        }
    }

    public boolean getOutOfBounds() {
        return outOfBounds;
    }

    public boolean getIsFriendly() {
        return isFriendly;
    }

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
                handleCollisionEventWithProjectile(g);
            }
        }
    }

    private void handleCollisionEventWithProjectile(GameObject collidingProjectile) {
        Projectile p = (Projectile) collidingProjectile;
        if (p.getIsFriendly()) {
            AttackCapableGameObject source = p.getSource();
            source.removeProjectile(p);
            determineIfAlienProjectileIsDestroyedByImpact(p);
        }
    }

    private void determineIfAlienProjectileIsDestroyedByImpact(Projectile p) {
        if (weight == p.getWeight()) {
            source.removeProjectile(this);
        } else {
            Random rand = new Random();
            int chance = rand.nextInt(weight * 3);
            if (chance < weight) {
                source.removeProjectile(this);
            }
        }
    }

    public int getWeight() {
        return weight;
    }

    /**
     * Geeft aan wat er moet gebeuren wanneer een deel van de bunkers wordt geraakt
     */
    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
        for (CollidedTile ct : collidedTiles) {
            if (ct.theTile instanceof BunkerTile) {
                source.removeProjectile(this);
                handleCollisionEventWithBunker(ct);
            }
        }
    }

    private void handleCollisionEventWithBunker(CollidedTile ct) {
        BunkerTile bt = (BunkerTile) ct.theTile;
        if (bt.getHitPoints() > 0) {
            swapBunkerTile(bt);
        }
        if (bt.getHitPoints() <= 0) {
            destroyBunkerTile(ct);
        }
    }

    private void swapBunkerTile(BunkerTile bt) {
        bt.setHitPoints(bt.getHitPoints() - weight);
        bt.swapSprite();
    }

    private void destroyBunkerTile(CollidedTile ct) {
        PVector vector = world.getTileMap().getTilePixelLocation(ct.theTile);
        world.getTileMap().setTile((int) vector.x / world.getTileMap().getTileSize(), (int) vector.y / world.getTileMap().getTileSize(), -1);
    }

}
