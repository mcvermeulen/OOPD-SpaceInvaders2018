package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public abstract class DestroyableGameObject extends AnimatedSpriteObject {
	protected SpaceInvaders world;

    public DestroyableGameObject(Sprite sprite, int totalFrames, float x, float y, int sWidth, int sHeight, SpaceInvaders world) {
        super(sprite, totalFrames);
        if (x > 0) this.x = x;
        if (y > 0) this.y = y;
        this.setWidth(sWidth);
        this.setHeight(sHeight);
        setCurrentFrameIndex(0);
        this.world = world;
    }

}
