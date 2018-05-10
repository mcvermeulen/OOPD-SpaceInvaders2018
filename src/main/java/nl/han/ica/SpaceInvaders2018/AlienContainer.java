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
    /**
     * Referentie naar de hoofdmodule
     */
	private SpaceInvaders world;
    /**
     * Beweegrichting van de aliens
     */
    private int direction = 90;
    /**
     * ArrayList met daarin Aliens
     */
    private ArrayList<Alien> aliens;
    /**
     * Totaal aantal vijandelijke projectielen op het hele scherm
     */
    private int allHostileProjectiles;
    /**
     * Maximaal aantal vijandelijke projectielen op het hele scherm
     */
    private int maxHostileProjectiles = 3;
    /**
     * Beweegsnelheid van de aliens
     */
    private float speed;
    /**
     * Aantal vernietigde aliens
     */
    private int destroyed, nTotalAliens = 0;

    /**
     * Constructor
     *
     * @param world       	referentie naar de hoofdmodule
     * @param x           	x-coordinaat van de startpositie van de aliens
     * @param y           	y-coordinaat van de startpositie van de aliens
     * @param alienKilled 	geluid wat afgespeeld wordt als de aliens geraakt zijn
     * @param nSmall      	aantal kleine aliens
     * @param nMedium     	aantal medium aliens
     * @param nLarge      	aantal grote aliens
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

    /**
     * Voegt een alien toe aan de container
     * @param alien		alien
     */
    public void add(Alien alien) {
        aliens.add(alien);
        world.addGameObject(alien);
    }

    /**
     * Verwijderd een alien uit de container
     * @param alien		alien
     */
    public void destroy(Alien alien) {
        aliens.remove(alien);
        world.deleteGameObject(alien);
    }

    /**
     * Verwijderd alle aliens die voor het attribuut hit de waarde 'true' hebben
     */
    public void cleanUpAliens() {
        for (int i = aliens.size() - 1; i >= 0; i--) {
            Alien a = aliens.get(i);
            if (a.getHit()) {
                world.increaseScore(a.getValue());
                destroy(a);
            }
        }
    }

    /**
     * Telt hoeveel vijandelijke projectielen er in totaal op het scherm zijn, door alle aliens in de container na te lopen
     * @return 			  	alle vijandelijke projectielen op het scherm
     */
    public int giveAllHostileProjectiles() {
        int number = 0;
        for (Alien a : aliens) {
            number += a.getTotalHostileProjectiles();
        }
        return number;
    }

    /**
     * Laat de aliens terugschieten op de speler, mits er aan een aantal condities wordt voldaan
     */
    public void fireBack() {
        if (aliens.size() > 0) {
            allHostileProjectiles = giveAllHostileProjectiles();
            if (allHostileProjectiles < maxHostileProjectiles) {
                Random rand = new Random();
                int fire = rand.nextInt(200); // TODO: dit getal nog een variabele maken?
                if (fire <= 1) {
                    int alien = rand.nextInt(aliens.size());
                    Alien attackingAlien = aliens.get(alien);
                    attackingAlien.fire();
                }
            }
        }
    }

    //TODO: Hebben we deze interface hier eigenlijk wel nodig?
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
    }

    /**
     * Bepaalt de beweegrichting en -snelheid van de aliens, of ze schieten, en of de aliens opnieuw moeten worden getekend omdat de speler ze allemaal heeft neergeschoten
     */
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
        // Boundaries
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

    /**
     * De hele groep naar beneden laten bewegen
     */
    @Override
    protected void dropToRowBelow() {
        for (int i = aliens.size() - 1; i >= 0; i--) {
            aliens.get(i).dropToRowBelow();
        }
    }

    /**
     * Berekent de x-positie van de meest rechtse alien
     * @return				meest rechtse x-positie
     */
    private int calculateRight() {
        float right = 0;
        for (Alien alien : aliens) {
            if (alien.getX() + alien.getWidth() > right) right = alien.getX() + alien.getWidth();
        }
        return Math.round(right);
    }

    /**
     * Berekent de x-positie van de meest linkse alien
     * @return				meest linkse x-positie
     */
    private int calculateLeft() {
        float left = world.width;
        for (Alien alien : aliens) {
            if (alien.getX() < left) left = alien.getX();
        }
        return Math.round(left);
    }

    /**
     * Past de snelheid van de groep aan, naarmate er minder aliens in de groep zitten
     */
    private void updateCurrentGroupSpeed() {
        int destroyedAliens = nTotalAliens - aliens.size();
        if (this.destroyed != destroyedAliens) {
            speed *= 1.025f;
            this.destroyed = destroyedAliens;
        }
    }

    // TODO dit kan beter
    /**
     * Vult de ArrayList met nieuw aangemaakte aliens
     * @param nSmallAliens	aantal kleine aliens
     * @param nMediumAliens	aantal medium aliens
     * @param nLargeAliens	aantal grote aliens
     */
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
