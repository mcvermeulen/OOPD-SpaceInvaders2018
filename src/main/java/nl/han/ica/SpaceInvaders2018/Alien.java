package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public abstract class Alien extends AttackCapableGameObject {

    public Alien(Sprite sprite, int totalFrames, float x, float y, int sWidth, int sHeight) {
        super(sprite, totalFrames, x, y, sWidth, sHeight);
    }

    @Override
    public void update() {
        nextFrame();
    }
}
