package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

import java.util.ArrayList;

public class AlienContainer extends Alien {
    private SpaceInvaders world;
    protected int direction = 90;
    private ArrayList<Alien> aliens;

    public AlienContainer(SpaceInvaders world, float x, float y) {
        super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/MediumAlien.png"), 1, x, y, 0, 0, world);
        setVisible(false);
        this.world = world;
        aliens = new ArrayList<>();

        setDirectionSpeed(direction, 2);
    }

    public ArrayList<Alien> getAliens() {
        return aliens;
    }

    public void add(Alien alien) {
        aliens.add(alien);
        world.addGameObject(alien);
    }

    public void destroy(Alien alien) {
        aliens.remove(alien);
        world.deleteGameObject(alien);
    }

    @Override
    public void update() {
        // boundaries
        if (direction == 90 && calculateRight() >= 990) {
            direction = 270;
        }
        if (direction == 270 && calculateLeft() <= 290) {
            direction = 90;
        }
        setDirectionSpeed(direction, 1.5f);
        for (Alien alien: aliens) {
            alien.setDirectionSpeed(direction, 1.5f);
        }
    }

    private int calculateRight() {
        float right = 0;
        for (Alien alien: aliens) {
            if (alien.getX() + alien.getWidth() > right) right = alien.getX() + alien.getWidth();
        }
        return Math.round(right);
    }
    private int calculateLeft() {
        float left = 990;
        for (Alien alien: aliens) {
            if (alien.getX() < left) left = alien.getX();
        }
        return Math.round(left);
    }
}
