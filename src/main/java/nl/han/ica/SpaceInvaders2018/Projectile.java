package nl.han.ica.SpaceInvaders2018;

import java.util.List;
import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public abstract class Projectile extends DestroyableGameObject implements ICollidableWithGameObjects {

    protected boolean friendly;
    protected int weight;
    private boolean outOfBounds = false;
    private AttackCapableGameObject source;

    public Projectile(Sprite sprite, int totalFrames, int sWidth, int sHeight, boolean friendly, SpaceInvaders world, AttackCapableGameObject source) {
        super(sprite, totalFrames, 0, 0, sWidth, sHeight, world);
        this.friendly = friendly;
        this.source = source;
    }

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

    public boolean getOutOfBounds() {
        return outOfBounds;
    }

    public boolean getFriendly() {
        return friendly;
    }

    public AttackCapableGameObject getSource() {
        return source;
    }

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

    public int getWeight() {
        return weight;
    }

}
