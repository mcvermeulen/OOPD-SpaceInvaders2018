package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Ruimteschip extends DestroyableGameObject {
    private int direction = 90;

    public Ruimteschip(float x, float y) {
        super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/Ruimteschip.png"), 1, x, y, 50, 23);

        setCurrentFrameIndex(0);
        setDirectionSpeed(direction, 2);
    }

    @Override
    public void update() {
        nextFrame();
        float x = getX();
        // boundaries
        if (direction == 90 && x == 1140) {
            direction = 270;
        }
        if (direction == 270 && x == 100) {
            direction = 90;
        }
        setDirectionSpeed(direction, 2);
    }
}
