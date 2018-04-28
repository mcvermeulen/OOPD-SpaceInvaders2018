package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public abstract class Alien extends AttackCapableGameObject {
    public Alien(Sprite sprite, int totalFrames) {
        super(sprite, totalFrames);
    }
}
