package nl.han.ica.SpaceInvaders2018;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public abstract class Projectile extends DestroyableGameObject implements ICollidableWithGameObjects {

    protected boolean friendly;
    private boolean outOfBounds = false;
    private AttackCapableGameObject source;

    public Projectile(Sprite sprite, int totalFrames, int sWidth, int sHeight, boolean friendly, SpaceInvaders world, AttackCapableGameObject source) {
        super(sprite, totalFrames, 0, 0, sWidth, sHeight, world);
        this.friendly = friendly;
        this.source = source;
        if (friendly) {
            setySpeed(-10);
        } else {
            setySpeed(2);
        }
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


    // TODO Voor nu wordt een vijandelijk projectiel altijd verwijderd als de speler deze schiet. Maar de zwaardere projectielen hebben later
    // een kans om de collision is overleven

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g : collidedGameObjects) {
            if (g instanceof Projectile) {
                if (((Projectile) g).getFriendly()) {
                    Projectile p = (Projectile) g;
                    AttackCapableGameObject k = p.getSource();
                    k.removeProjectile(p);
                    source.removeProjectile(this);
                }
            }
        }
    }

}
