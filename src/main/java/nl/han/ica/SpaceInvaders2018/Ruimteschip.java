package nl.han.ica.SpaceInvaders2018;

import java.util.List;
import java.util.Random;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;

/**
 * Ruimteschip klasse
 */
public class Ruimteschip extends DestroyableGameObject implements ICollidableWithGameObjects {
    /**
     * Beweegrichting van het ruimteschip
     */
	private int direction = 90;
    /**
     * Geluid dat het schip maakt, wanneer het neergeschoten wordt
     */
	private Sound UFOShot;
    /**
     * Geeft aan of het ruimteschip is geraakt door de speler
     */
    private boolean shot;
    /**
     * De punten die toegevoegd worden aan de score van de speler, als het ruimteschip wordt geraakt
     */
    private int value;

    /**
     * Constructor
     * @param world			Referentie naar de hoofdmodule
     * @param x				X-positie waarop het object getekend wordt
     * @param y				Y-positie waarop het object getekend wordt
     * @param UFOShot		Geluid dat het schip maakt, wanneer het neergeschoten wordt
     */
    public Ruimteschip(SpaceInvaders world, float x, float y, Sound UFOShot) {
        super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/Ruimteschip.png"), 1, x, y, 50, 23, world);
        this.UFOShot = UFOShot;
        this.shot = false;
        this.value = 100;

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
     * Geeft een nieuwe waarde aan het Ruimteschip (tussen de 50 en 300 punten)
     * @return		De punten die toegevoegd worden aan de score van de speler, als het ruimteschip wordt geraakt
     */
    public int generateValue() {
    	Random random = new Random();
    	int value = 50 * (random.nextInt(6) + 1);
    	return value;
    }
    
    /**
     * Reset het ruimteschip (maakt het weer zichtbaar)
     */
    public void resetUFO() {
    	if (shot) {
    		setVisible(true);
    		value = generateValue();
    	}
    	shot = false;
    }
    
    /**
     * Laat het ruimteschip bewegen
     */
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
    	BonusPointsText bonus = new BonusPointsText((String.format("%02d", value)), 20, world);
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
            	if(p.getFriendly()) {
            		AttackCapableGameObject k = p.getSource();
            		k.removeProjectile(p);
            		shot = true;
            		setVisible(false);
            		explode();
            		UFOShot.cue(140);
            		UFOShot.play();
            		showBonusPoints();
            		world.increaseScore(value);
            	}
            }
        }
    }
}
