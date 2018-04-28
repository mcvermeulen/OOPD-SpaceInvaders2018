package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public abstract class DestroyableGameObject extends AnimatedSpriteObject {

    public DestroyableGameObject(Sprite sprite, int totalFrames) {
        super(sprite, totalFrames);
    }

}
