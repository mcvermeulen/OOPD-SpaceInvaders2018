package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * De container waarin de aliens zitten, zodat deze synchroon bewegen
 */
public class AlienContainer extends Alien implements ICollidableWithGameObjects {
    private SpaceInvaders world;
    private int direction = 90;
    private ArrayList<Alien> aliens;
    private int allHostileProjectiles;
    private int maxHostileProjectiles = 3;
    private float speed;
    private int destroyed, nTotalAliens = 0;

    /**
     * Constructor
     *
     * @param world       referentie naar de hoofdmodule
     * @param x           x-coordinaat van de startpositie van de aliens
     * @param y           y-coordinaat van de startpositie van de aliens
     * @param alienKilled geluid wat afgespeeld wordt als de aliens geraakt zijn
     * @param nSmall      aantal kleine aliens
     * @param nMedium     aantal medium aliens
     * @param nLarge      aantal grote aliens
     */
    public AlienContainer(SpaceInvaders world, float x, float y, Sound alienKilled, int nSmall, int nMedium, int nLarge) {
        super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/MediumAlien.png"), 1, x, y, 0, 0, world, alienKilled);
        setVisible(false);
        this.world = world;
        this.aliens = new ArrayList<>();
        this.allHostileProjectiles = 0;
        this.speed = 0.7f;
        this.nTotalAliens = nSmall + nMedium + nLarge;
        this.generateAliens(nSmall, nMedium, nLarge);
    }

    public void add(Alien alien) {
        aliens.add(alien);
        world.addGameObject(alien);
    }

    public void destroy(Alien alien) {
        aliens.remove(alien);
        world.deleteGameObject(alien);
    }

    public void cleanUpAliens() {
        for (int i = aliens.size() - 1; i >= 0; i--) {
            Alien a = aliens.get(i);
            if (a.getHit()) {
                world.increaseScore(a.getValue());
                destroy(a);
            }
        }
    }

    public int giveAllHostileProjectiles() {
        int number = 0;
        for (Alien a : aliens) {
            number += a.getTotalHostileProjectiles();
        }
        return number;
    }

    public void fireBack() {
        if (aliens.size() > 0) {
            allHostileProjectiles = giveAllHostileProjectiles();
            if (allHostileProjectiles < maxHostileProjectiles) {
                Random rand = new Random();
                int fire = rand.nextInt(250); // bepaalt kans dat de aliens schieten. Dit getal kan later een variabele fireRate worden dat bv. hoger wordt naarmate er minder aliens zijn
                // hier later als we ook de andere twee projectiel typen hebben, nog een random gebruiken om te bepalen welk projectiel het wordt
                if (fire <= 1) {
                    int alien = rand.nextInt(aliens.size());
                    Alien attackingAlien = aliens.get(alien);
                    attackingAlien.fire();
                }
            }
        }
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
    }

    @Override
    public void update() {
        fireBack();
        // Update speed
        updateCurrentGroupSpeed();
        if (aliens.size() == 1) {
            if (direction == 90) {
                speed = 6;
            } else {
                speed = 3.8f;
            }
        }
        // boundaries
        if (direction == 90 && calculateRight() >= (world.width + world.getPlayfieldWidth())/2) {
            direction = 270;
            dropToRowBelow();
        } else if (direction == 270 && calculateLeft() <= (world.width - world.getPlayfieldWidth())/2) {
            direction = 90;
            dropToRowBelow();
        }
        setDirectionSpeed(direction, speed);
        for (Alien alien : aliens) {
            alien.setDirectionSpeed(direction, speed);
        }

        cleanUpAliens();

        // No aliens left, increase level
        if (aliens.size() == 0) {
            world.increaseLevel();
            world.deleteGameObject(this);
        }
    }

    @Override
    protected void dropToRowBelow() {
        for (int i = aliens.size() - 1; i >= 0; i--) {
            aliens.get(i).dropToRowBelow();
        }
    }

    private int calculateRight() {
        float right = 0;
        for (Alien alien : aliens) {
            if (alien.getX() + alien.getWidth() > right) right = alien.getX() + alien.getWidth();
        }
        return Math.round(right);
    }

    private int calculateLeft() {
        float left = world.width;
        for (Alien alien : aliens) {
            if (alien.getX() < left) left = alien.getX();
        }
        return Math.round(left);
    }


    private void updateCurrentGroupSpeed() {
        int destroyedAliens = nTotalAliens - aliens.size();
        if (this.destroyed != destroyedAliens) {
            speed *= 1.025f;
            this.destroyed = destroyedAliens;
        }
    }

    // TODO dit kan beter
    private void generateAliens(int nSmallAliens, int nMediumAliens, int nLargeAliens) {
        int columns = 11;
        int row = 0;
        int margeX = 35;
        int margeY = 35;
        int offset = 0;
        for (int j = 0; j < nSmallAliens; j++) {
            Alien alien = new SmallAlien(world, this.getX() + offset * margeX, this.getY() + row * margeY, alienKilled);
            this.add(alien);
            offset++;
            if (j > 0 && (j + 1) % columns == 0) {
                row++;
                offset = 0;
            }
        }
        for (int j = 0; j < nMediumAliens; j++) {
            Alien alien = new MediumAlien(world, this.getX() + offset * margeX, this.getY() + row * margeY, alienKilled);
            this.add(alien);
            offset++;
            if (j > 0 && (j + 1) % columns == 0) {
                row++;
                offset = 0;
            }
        }
        for (int j = 0; j < nLargeAliens; j++) {
            Alien alien = new LargeAlien(world, this.getX() + offset * margeX, this.getY() + row * margeY, alienKilled);
            this.add(alien);
            offset++;
            if (j > 0 && (j + 1) % columns == 0) {
                row++;
                offset = 0;
            }
        }
    }
}
