package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PApplet;

import static processing.core.PConstants.LEFT;

public class Cannon extends AttackCapableGameObject {
    private PApplet app;

    public Cannon(PApplet app) {
        super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/Cannon.png"), 1);

        setCurrentFrameIndex(0);
        this.app = app;
    }

    @Override
    public void update() {
        if (getX() <= 290) {
            setxSpeed(0);
            setX(290);
        }
        if (getX() >= 990 - getWidth()) {
            setxSpeed(0);
            setX(990 - getWidth());
        }
    }

    @Override
    public void keyPressed(int keyCode, char key) {
        final int speed = 5;

        switch (keyCode) {
            case LEFT:
                setDirectionSpeed(270, speed);
                break;
            case RIGHT:
                setDirectionSpeed(90, speed);
                break;
        }

        if (key == ' ') {
            System.out.println("Spatie!");
        }
    }

    @Override
    public void keyReleased(int keyCode, char key) {
        switch (keyCode) {
            case LEFT: case RIGHT:
                setxSpeed(0);
                break;
        }
    }
}
