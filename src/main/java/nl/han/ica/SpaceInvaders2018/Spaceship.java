package nl.han.ica.SpaceInvaders2018;

import java.util.List;
import java.util.Random;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;

/**
 * Spaceship klasse
 */
public class Spaceship extends DestroyableGameObject implements ICollidableWithGameObjects {

	private int direction = 90;
	private Sound UFOShot;
    private boolean isShot;
    private int valueIfShot;

    /**
     * Constructor
     * @param world			Referentie naar de hoofdmodule
     * @param x				X-positie waarop het object getekend wordt
     * @param y				Y-positie waarop het object getekend wordt
     * @param UFOShot		Geluid dat het schip maakt, wanneer het neergeschoten wordt
     */
    public Spaceship(SpaceInvaders world, float x, float y, Sound UFOShot) {
        super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/Ruimteschip.png"), 1, x, y, 50, 23, world);
        this.UFOShot = UFOShot;
        this.isShot = false;
        this.valueIfShot = 100;

        setCurrentFrameIndex(0);
        setDirectionSpeed(direction, 2);
    }

    /**
     * Beweegt het ruimteschip over het scherm
     */
    @Override
    public void update() {
    	nextFrame();
        travel();
    }
    
    /**
     * Geeft een nieuwe waarde aan het Spaceship (tussen de 50 en 300 punten)
     * @return		De punten die toegevoegd worden aan de score van de speler, als het ruimteschip wordt geraakt
     */
    public int generateValue() {
    	Random random = new Random();
    	int value = 50 * (random.nextInt(6) + 1);
    	return value;
    }

    public void resetUFO() {
    	if (isShot) {
    		setVisible(true);
    		valueIfShot = generateValue();
    	}
    	isShot = false;
    }

    public void travel() {
        // boundaries
        if (direction == 90 && x >= world.width) {
        	resetUFO();
        	direction = 270;
	    }
	    else if (direction == 270 && x <= 0) {
	    	resetUFO();
	    	direction = 90;
	    }
        setDirectionSpeed(direction, 2);
    }
    
    /**
     * Toont de bonuspunten op de locatie waar het ruimteschip was geraakt
     */
    private void showBonusPoints() {
    	BonusPointsText bonus = new BonusPointsText((String.format("%02d", valueIfShot)), 20, world);
    	bonus.setForeColor(255, 0, 0, 255);
    	bonus.setX(getX());
    	bonus.setY(getY() + getHeight());
    	world.addGameObject(bonus);
    }
    
    /**
     * Geeft aan wat er moet gebeuren als de speler het ruimteschip raakt
     */
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g:collidedGameObjects) {
            if (g instanceof Projectile) {
                Projectile p = (Projectile) g;
                if (p.getIsFriendly()) {
                    handleCollisionEventWithPlayerProjectile(p);
                }
            }
        }
    }

    private void handleCollisionEventWithPlayerProjectile(Projectile p) {
        AttackCapableGameObject source = p.getSource();
        source.removeProjectile(p);
        isShot = true;
        setVisible(false);
        explode();
        UFOShot.cue(140);
        UFOShot.play();
        showBonusPoints();
        world.increaseScore(valueIfShot);
    }
}
