package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public abstract class DestroyableGameObject extends AnimatedSpriteObject {

    public DestroyableGameObject(Sprite sprite, int totalFrames, float x, float y, int sWidth, int sHeight) {
        super(sprite, totalFrames);
        if (x > 0) this.x = x;
        if (y > 0) this.y = y;
        this.setWidth(sWidth);
        this.setHeight(sHeight);
        setCurrentFrameIndex(0);
    }

}
