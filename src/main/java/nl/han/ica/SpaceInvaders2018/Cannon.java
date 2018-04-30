package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;

public class Cannon extends AttackCapableGameObject {
    private Sound shootSound;

    public Cannon(SpaceInvaders world, float x, float y, Sound shoot) {
        super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/Cannon.png"), 1, x, y, 47, 30, world);
        this.shootSound = shoot;
    }

    @Override
    public void update() {
        cleanUpProjectiles();
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
            if (getTotalFriendlyProjectiles() == 0) {
                shootSound.cue(137);
            	shootSound.play();
                generateLaser(world, true);
            }
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
