package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PApplet;

public class Ruimteschip extends DestroyableGameObject {
    private PApplet app;
    private int direction = 90;

    public Ruimteschip(PApplet app) {
        super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/Ruimteschip.png"), 1);

        this.app = app;
        setCurrentFrameIndex(0);
        setDirectionSpeed(direction, 2);
    }

    @Override
    public void update() {
        nextFrame();
        float x = getX();
        // boundaries
        if (direction == 90 && x == 970) {
            direction = 270;
        }
        if (direction == 270 && x == 290) {
            direction = 90;
        }
        setDirectionSpeed(direction, 2);
    }
}
