package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

/**
 * Superklasse van AttackCapableGameObject
 */
public abstract class DestroyableGameObject extends AnimatedSpriteObject implements IAlarmListener {

	protected SpaceInvaders world;
	private Explosion explosion;

	/**
	 * Constructor
	 * @param sprite		Afbeelding van het object
	 * @param totalFrames	Aantal frames waaruit de afbeeldng bestaat
	 * @param x				X-positie waarop het object getekend wordt
	 * @param y				Y-positie waarop het object getekend wordt
	 * @param sWidth		Breedte van de afbeelding
	 * @param sHeight		Hoogte van de afbeelding
	 * @param world			Referentie naar de hoofdmodule
	 */
    public DestroyableGameObject(Sprite sprite, int totalFrames, float x, float y, int sWidth, int sHeight, SpaceInvaders world) {
        super(sprite, totalFrames);
        if (x > 0) this.x = x;
        if (y > 0) this.y = y;
        this.setWidth(sWidth);
        this.setHeight(sHeight);
        setCurrentFrameIndex(0);
        this.world = world;
        explosion = new Explosion();
    }

    public void explode() {
        world.addGameObject(explosion, getX(), getY());
        explosionAlarm();
    }

    /**
     * Zet het alarm voor de explosie
     */
    private void explosionAlarm() {
        Alarm alarm=new Alarm("remove explosion", 0.3);
        alarm.addTarget(this);
        alarm.start();
    }

    /**
     * Zorgt dat de explosie weer verdwijnt
     */
    @Override
    public void triggerAlarm(String alarmName) {
        world.deleteGameObject(explosion);
    }
     
}
