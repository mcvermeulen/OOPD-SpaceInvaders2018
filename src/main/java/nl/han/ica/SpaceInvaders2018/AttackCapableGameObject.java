package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.SpaceInvaders2018.DestroyableGameObject;

public abstract class AttackCapableGameObject extends DestroyableGameObject {
    public AttackCapableGameObject(Sprite sprite, int totalFrames) {
        super(sprite, totalFrames);
    }
}
