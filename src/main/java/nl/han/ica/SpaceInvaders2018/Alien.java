package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PApplet;

public abstract class Alien extends AttackCapableGameObject {
    protected int direction = 90;

    public Alien(Sprite sprite, int totalFrames) {
        super(sprite, totalFrames);
    }

    @Override
    public void update() {
        nextFrame();
        float x = getX();
        // boundaries
        if (direction == 90 && x == 960) {
            direction = 270;
        }
        if (direction == 270 && x == 290) {
            direction = 90;
        }
        setDirectionSpeed(direction, 2);
    }
}
