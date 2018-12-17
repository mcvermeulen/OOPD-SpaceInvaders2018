package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import java.util.ArrayList;
import java.util.Random;

/**
 * De container waarin de aliens zitten, zodat deze synchroon bewegen
 */
public class AlienContainer extends Alien {

	private SpaceInvaders world;
    private int movementDirection = 90;
    private ArrayList<Alien> aliens;
    private int maxHostileProjectilesOnScreen = 3;
    private float speed;
    private int nTotalDestroyedAliens, nTotalAliens = 0;
    private int rateOfFire = 200;  // a higher number means a slower rate of fire

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

    public void cleanUpHitAliens() {
        for (int i = aliens.size() - 1; i >= 0; i--) {
            Alien a = aliens.get(i);
            if (a.getIsHit()) {
                world.increaseScore(a.getValueIfHit());
                destroy(a);
            }
        }
    }

    public int giveAllHostileProjectilesOnScreen() {
        int number = 0;
        for (Alien a : aliens) {
            number += a.getTotalHostileProjectiles();
        }
        return number;
    }

    public void fireBack() {
        if (conditionsToFireBackAreMet()) {
            determineRandomAttackingAlien().fire();
        }
    }

    private boolean conditionsToFireBackAreMet() {
        boolean fireBack = false;
        if (aliens.size() > 0) {
            if (giveAllHostileProjectilesOnScreen() < maxHostileProjectilesOnScreen) {
                Random rand = new Random();
                int chanceToFire = rand.nextInt(rateOfFire);
                if (chanceToFire <= 1) {
                    fireBack = true;
                }
            }
        }
        return fireBack;
    }

    private Alien determineRandomAttackingAlien() {
        Random rand = new Random();
        int randomSurvivingAlien = rand.nextInt(aliens.size());
        return aliens.get(randomSurvivingAlien);
    }

    /**
     * Bepaalt de beweegrichting en -snelheid van de aliens, of ze schieten, en of de aliens opnieuw moeten worden getekend
     * omdat de speler ze allemaal heeft neergeschoten
     */
    @Override
    public void update() {
        fireBack();
        // Update speed
        updateCurrentGroupSpeed();
        if (aliens.size() == 1) {
            if (movementDirection == 90) {
                speed = 6;
            } else {
                speed = 3.8f;
            }
        }
        // Boundaries
        if (movementDirection == 90 && calculateXPosOuterRightAlien() >= (world.width + world.getPlayfieldWidth())/2) {
            movementDirection = 270;
            dropToRowBelow();
        } else if (movementDirection == 270 && calculateXPosOuterLeftAlien() <= (world.width - world.getPlayfieldWidth())/2) {
            movementDirection = 90;
            dropToRowBelow();
        }
        setDirectionSpeed(movementDirection, speed);
        for (Alien alien : aliens) {
            alien.setDirectionSpeed(movementDirection, speed);
        }

        cleanUpHitAliens();
        resetFieldIfNoAliensLeft();
    }

    private void resetFieldIfNoAliensLeft() {
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

    private int calculateXPosOuterRightAlien() {
        float right = 0;
        for (Alien alien : aliens) {
            if (alien.getX() + alien.getWidth() > right) right = alien.getX() + alien.getWidth();
        }
        return Math.round(right);
    }

    private int calculateXPosOuterLeftAlien() {
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
        if (this.nTotalDestroyedAliens != destroyedAliens) {
            speed *= 1.025f;
            this.nTotalDestroyedAliens = destroyedAliens;
        }
    }

    /**
     * Vult de ArrayList met nieuw aangemaakte aliens
     * @param nSmallAliens	aantal kleine aliens
     * @param nMediumAliens	aantal medium aliens
     * @param nLargeAliens	aantal grote aliens
     */
    private void generateAliens(int nSmallAliens, int nMediumAliens, int nLargeAliens) {
        int row = 0;
        int margeX = 35;
        int margeY = 35;
        int offset = 0;
        for (int j = 0; j < nSmallAliens; j++) {
            Alien alien = new SmallAlien(world, this.getX() + offset * margeX, this.getY() + row * margeY, alienKilled);
            this.add(alien);
            offset++;
            if (isEndOfRowReached(j)) {
                row++;
                offset = 0;
            }
        }
        for (int j = 0; j < nMediumAliens; j++) {
            Alien alien = new MediumAlien(world, this.getX() + offset * margeX, this.getY() + row * margeY, alienKilled);
            this.add(alien);
            offset++;
            if (isEndOfRowReached(j)) {
                row++;
                offset = 0;
            }
        }
        for (int j = 0; j < nLargeAliens; j++) {
            Alien alien = new LargeAlien(world, this.getX() + offset * margeX, this.getY() + row * margeY, alienKilled);
            this.add(alien);
            offset++;
            if (isEndOfRowReached(j)) {
                row++;
                offset = 0;
            }
        }
    }

    private boolean isEndOfRowReached(int j) {
        int columns = 11;
        if (j > 0 && (j + 1) % columns == 0) {
            return true;

        }
        return false;
    }

}
